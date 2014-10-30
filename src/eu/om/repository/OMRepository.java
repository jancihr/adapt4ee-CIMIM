package eu.om.repository;

import eu.om.ontology.Graph;
import eu.om.ontology.model.Namespace;
import eu.om.ontology.model.Rdf;
import eu.om.ontology.model.Rdfs;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.Value;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.rio.RDFFormat;

public class OMRepository {

    private Repository repository;

    public OMRepository(Repository repository) {
        this.repository = repository;
    }

    public RepositoryConnection getConnection() {
        try {
            return this.repository.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            this.repository.shutDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        File file = new File(System.getProperty("ontologies.dir"));
        File[] files = file.listFiles();
        RDFFormat format = RDFFormat.N3;//TURTLE;
        RepositoryConnection conn = getConnection();
        try {
            conn.clear();
            if (files != null) {
            	for (File f : files) {
            		if (!f.getName().equals(".svn") && !f.isDirectory() && f.getName().endsWith(".n3")) {
            			conn.add(f, "", format);
            		}
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Retrieves list of super classes for taxonomy class;
     *
     * @param Class URI.
     * @return Collection of super classes;
     */
    public Set<String> getSuperClassesOf(String classURI) {
        Set<String> superClasses = new HashSet<String>();
        RepositoryConnection conn = getConnection();
        try {
            RepositoryResult<Statement> result
                    = conn.getStatements(new URIImpl(classURI), Rdfs.subClassOf, null, true);
            while (result.hasNext()) {
                Statement st = result.next();
                String sc = st.getObject().stringValue();
                // DIRTY BLANK NODE HACK
                if (!sc.startsWith("_") && !sc.startsWith("node")) {
                    if (!Namespace.isStaticType(new URIImpl(sc)) && !sc.equals(classURI)) {
                        superClasses.add(sc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return superClasses;
    }

    /**
     * Retrieves direct super class for taxonomy class;
     *
     * @param Class URI.
     * @return Direct super classes;
     */
    public String getDirectSuperClassOf(String classURI) {
        String superClass = null;
        RepositoryConnection conn = getConnection();
        try {
            RepositoryResult<Statement> result
                    = conn.getStatements(new URIImpl(classURI), Rdfs.subClassOf, null, false);
            if (result.hasNext()) {
                superClass = result.next().getObject().stringValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return superClass;
    }

    /**
     * Retrieves list of sub classes for taxonomy class;
     *
     * @param Class URI.
     * @return Collection of super classes;
     */
    public Set<String> getSubClassesOf(String classURI, boolean inferred) {
        Set<String> subClasses = new HashSet<String>();
        RepositoryConnection conn = getConnection();
        try {
            RepositoryResult<Statement> result
                    = conn.getStatements(null, Rdfs.subClassOf, new URIImpl(classURI), inferred);
            while (result.hasNext()) {
                String sc = result.next().getSubject().stringValue();
                if (!Namespace.isStaticType(new URIImpl(sc)) && !sc.equals(classURI)) {
                    subClasses.add(sc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return subClasses;
    }

    public Set<String> getSubClassesOf(String classURI) {
        return getSubClassesOf(classURI, true);
    }

    /**
     * Retrieves list of instances for taxonomy class;
     *
     * @param classURI Class URI.
     * @param inferred Include also inferred instances.
     * @return Collection of instances;
     */
    public Set<String> getInstancesOf(String classURI, boolean inferred) {
        System.out.println("instances for: " + classURI);
        Set<String> instances = new HashSet<String>();
        RepositoryConnection conn = getConnection();
        try {
            RepositoryResult<Statement> result
                    = conn.getStatements(null, Rdf.rdfType, new URIImpl(classURI), inferred);
            while (result.hasNext()) {
                Statement st = result.next();
                System.out.println("adding: " + st);
                instances.add(st.getSubject().stringValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return instances;
    }

    /**
     * Retrieves direct instance rdf:type;
     *
     * @param instanceURI Instance URI.
     * @return uri of instance rdf:type;
     */
    public String getDirectInstanceType(String instanceURI) {
        RepositoryConnection conn = getConnection();
        try {
            RepositoryResult<Statement> result
                    = conn.getStatements(new URIImpl(instanceURI), Rdf.rdfType, null, false);
            if (result.hasNext()) {
                return result.next().getObject().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Retrieves all instance rdf:types;
     *
     * @param instanceURI Instance URI.
     * @return set of instance rdf:types;
     */
    public Set<String> getInstanceTypes(String instanceURI) {
        RepositoryConnection conn = getConnection();
        try {
            RepositoryResult<Statement> result
                    = conn.getStatements(new URIImpl(instanceURI), Rdf.rdfType, null, true);
            Set<String> types = new HashSet<String>();
            while (result.hasNext()) {
                types.add(result.next().getObject().toString());
            }
            return types;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Retrieves graph of all values of all instance properties including
     * inferred, but does not return the instance rdf:type;
     *
     * @param instanceURI Instance URI.
     * @return Property Instance plain property graph;
     */
    public Graph getInstancePropertiesIncludingInferred(String instanceURI) {
        RepositoryConnection conn = getConnection();
        try {
            Graph instance = new Graph(instanceURI);
            RepositoryResult<Statement> result
                    = conn.getStatements(new URIImpl(instanceURI), null, null, true);
            while (result.hasNext()) {
                Statement s = result.next();
                if (!s.getPredicate().toString().equals(Rdf.rdfType.toString())) {
                    instance.add(s);
                }
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Retrieves graph of all values of direct instance properties including
     * inverse properties;
     *
     * @param instanceURI Instance URI.
     * @return Property Instance plain property graph;
     */
    public Graph getInstanceProperties(String instanceURI) {
        RepositoryConnection conn = getConnection();
        try {
            Graph instance = new Graph(instanceURI);
            RepositoryResult<Statement> result
                    = conn.getStatements(new URIImpl(instanceURI), null, null, false);
            while (result.hasNext()) {
                instance.add(result.next());
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private Graph getResource(RepositoryConnection conn, Value base, Graph g) throws Exception {
        if (base instanceof Resource) {
            RepositoryResult<Statement> result = conn.getStatements((Resource) base, null, null, false);
            while (result.hasNext()) {
                Statement s = result.next();
                g.add(s);
//				if(!Namespace.isStaticProperty(s.getPredicate()) &&
                if ((!(s.getObject() instanceof Literal))
                        && !g.createsCycle(g, s)) {
                    getResource(conn, s.getObject(), g);
                } else {
                }
            }
        }
        return g;
    }

    /**
     * Recursively retrieves the full graph for the root resource specified as
     * instance URI.
     *
     * @param instanceURI URI of instance for which the graph is retrieved.
     * @return Instance Graph.
     */
    public Graph getResource(String instanceURI) {
        RepositoryConnection conn = getConnection();
        try {
            Graph device = getResource(conn, new URIImpl(instanceURI), new Graph(instanceURI));
            if (device.getStmts().size() == 0) {
                return null;
            }
            return device;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private String value(String var, BindingSet b) {
        Value value = b.getValue(var);
        if (value == null) {
            return "";
        }
        return value.stringValue();
    }

    public TupleQueryResult sparql(String queryString) throws Exception {
        RepositoryConnection conn = this.getConnection();
        return sparql(conn, queryString);
    }

    public TupleQueryResult sparql(
            RepositoryConnection conn,
            String queryString) throws Exception {
        String q = Namespace.queryPrefixes() + queryString;
//    	System.out.println("running sparql: \n"+q);
        TupleQuery query = conn.prepareTupleQuery(
                QueryLanguage.SPARQL,
                q);
        return query.evaluate();
    }

    /**
     * Returns the XML of services matching service and entity type
     *
     * @param serviceType name of ontology class of service type in prefixed
     * form (ebbits:BasicDataService)
     * @param entityType name of ontology instance of entity to which the
     * service reffers - in prefixed form (ebbits:cow)
     * @return XML of service description including map of output parameters
     */
    /*
    public String match(String serviceType, String entityType) {
        System.out.println("MATCH: [" + serviceType + ":" + entityType + "]");
        RepositoryConnection conn = getConnection();
        try {
            String q = "SELECT ?service  ?prototype WHERE {\n";
            q += "?service rdf:type " + serviceType + " .\n";
            q += "?service ebbits:hasPrototype ?prototype .\n";
            q += "?prototype ebbits:reffersTo " + entityType + " .\n";
            q += "}\n";
            TupleQueryResult result = sparql(conn, q);
            if (result != null) {
                HashSet<Graph> services = new HashSet<Graph>();
                while (result.hasNext()) {
                    BindingSet b = result.next();
                    String serviceURI = value("service", b);
                    Graph service = getResource(serviceURI);
                    System.out.println(service.describe());
                    if (service != null) {
                        services.add(service);
                    }
                }
                ServiceGenerator g = new ServiceGenerator();
                return g.matchedServicesXML(services);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    */

    /**
     * Clears the whole repository.
     *
     * @return Notification on operation success.
     */
    public boolean clear() {
        System.out.println("\n\n===================");
        System.out.println("CLEARING REPOSITORY\n");
        RepositoryConnection conn = getConnection();
        try {
            conn.clear();
            return true;
        } catch (RepositoryException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String list() {
        System.out.println("\n\n===================");
        String out = "LISTING: \n";
        RepositoryConnection conn = getConnection();
        try {
            RepositoryResult<Statement> result = conn.getStatements(null, Rdf.rdfType, null, true);
            while (result.hasNext()) {
                Statement st = result.next();
                out += "[";
                out += st.getSubject().stringValue() + " : ";
                out += st.getPredicate().stringValue() + " : ";
                out += st.getObject().stringValue();
                out += "]\n";
            }
            System.out.println(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return out;
    }


}
