package eu.om.ontology;

import eu.om.ontology.model.Namespace;
import eu.om.ontology.model.Rdf;
import eu.om.ontology.schema.Property;
import eu.om.ontology.schema.ResourceProperty;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.GraphImpl;
import org.openrdf.model.impl.URIImpl;

public class Graph extends GraphImpl {

    private String baseURI = "";
    private Set<Statement> stmts = new HashSet<Statement>();

    public Graph(String baseURI, Collection<Statement> s) {
        this.baseURI = baseURI;
        addAll(s);
    }

    public Graph(String baseURI) {
        this.baseURI = baseURI;
    }

    @Override
    public Iterator<Statement> iterator() {
        return this.stmts.iterator();
    }

    @Override
    public boolean add(Statement st) {
        return stmts.add(st);
    }

    /**
     * Adds another graph to this graph.
     *
     * @param g Graph to add.
     * @return This extended graph.
     */
    public Graph add(Graph g) {
        this.stmts.addAll(g.stmts);
        return this;
    }

    @Override
    public int size() {
        return stmts.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Graph) {
            Graph g = (Graph) o;
            return (this.baseURI.equals(g.baseURI)
                    && this.stmts.equals(g.stmts));
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[GRAPH: " + this.baseURI + ":" + this.stmts.size() + "]";
    }

    /**
     * Helper method which serializes the graph content to string including all
     * triplets.
     *
     * @return Graph serialization.
     */
    public String show() {
        String out = "\n\n[GRAPH: " + this.baseURI + ":" + this.stmts.size() + "]\n";
        Iterator<Statement> i = this.stmts.iterator();
        while (i.hasNext()) {
            Statement s = i.next();
            out += "  [" + s.getSubject() + ":" + s.getPredicate() + ":" + s.getObject() + ":" + s.getContext() + "]\n";
        }
        return out;
    }

    @Override
    public int hashCode() {
        return stmts.hashCode();
    }

    public String getBaseURI() {
        return this.baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public Set<Statement> getStmts() {
        return this.stmts;
    }

    /**
     * Returns the set of property values for triplet: baseURI property value.
     * If values of property are instances, the URIs are retrieved. For
     * literals, the value of literals are retrieved.
     *
     * @param property Ontology property.
     * @return Set of property values.
     */
    public HashSet<String> values(Property property) {
        HashSet<String> values = new HashSet<String>();
        Iterator<Statement> i = match(new URIImpl(baseURI), property, null);
        while (i.hasNext()) {
            values.add(i.next().getObject().stringValue().trim());
        }
        return values;
    }

    /**
     * Returns the property value for triplet: baseURI property value. If value
     * of property is instance, the URI is retrieved. For literal, the value of
     * literal is retrieved. If the property has more values, first is returned
     * (values in set are in random order, so, first returned means - one of the
     * values).
     *
     * @param property Ontology property.
     * @return Property value.
     */
    public String value(Property property) {
        HashSet<String> values = values(property);
        if (values.size() == 0) {
            return null;
        }
        return values.iterator().next();
    }

    public String value(String propertyName) {
        return value(new Property(Namespace.uriFromPrefixedForm(propertyName)));
    }

    public HashSet<String> values(String propertyName) {
        return values(new Property(Namespace.uriFromPrefixedForm(propertyName)));
    }

    public String rdfType() {
        Graph gtype = subGraph(Rdf.rdfType);
        if (gtype != null) {
            return Namespace.uriToPrefixedForm(gtype.getBaseURI());
        }
        return null;
    }

    private String statementString(Statement st) {
        return "[" + st.getSubject().stringValue() + ":" + st.getPredicate().stringValue() + ":" + st.getObject().stringValue() + "]";
    }

    private Graph subGraph(Value base, Graph g) {
        if (base instanceof Resource) {
            Iterator<Statement> i = match((Resource) base, null, null);
            while (i.hasNext()) {
                Statement s = i.next();
                if (g.contains(s)) {
//					System.out.println("not adding recursive statement: ["+statementString(s)+"]");
                } else {
                    g.add(s);
                    subGraph(s.getObject(), g);
                }
            }
        }
        return g;
    }

    private Graph subGraph(Statement s) {
        Value base = s.getObject();
        Graph g = new Graph(base.stringValue());
        g.add(s);
        return subGraph(base, g);
    }

    /**
     * Returns the all values of property for triplet: baseURI property value.
     * Value must be resource (instance). For each instance value of property,
     * full subgraph is recursively generated.
     *
     * @param property Ontology property.
     * @return Set of full subgraphs of property values.
     */
    public HashSet<Graph> subGraphs(ResourceProperty property) {
        HashSet<Graph> graphs = new HashSet<Graph>();
        Iterator<Statement> i = match(new URIImpl(baseURI), property, null);
        while (i.hasNext()) {
            Statement s = i.next();
            graphs.add(subGraph(s));
        }
        return graphs;
    }

    /**
     * Returns the value of property for triplet: baseURI property value. Value
     * must be resource (instance). For instance value of property, full
     * subgraph is recursively generated. If there were more values matched, one
     * of them is returned (as the values are in set containing the retrieved
     * values in random order).
     *
     * @param property Ontology property.
     * @return Set of full subgraphs of property values.
     */
    public Graph subGraph(ResourceProperty property) {
        HashSet<Graph> graphs = subGraphs(property);
        if (graphs.size() == 0) {
            return null;
        }
        return graphs.iterator().next();
    }

    public Graph subGraph(String propertyName) {
        return subGraph(new ResourceProperty(Namespace.uriFromPrefixedForm(propertyName)));
    }

    public HashSet<Graph> subGraphs(String propertyName) {
        return subGraphs(new ResourceProperty(Namespace.uriFromPrefixedForm(propertyName)));
    }

    public String indent(int indent, String cnt) {
        String out = "";
        for (int i = 0; i < indent; i++) {
            out += "  ";
        }
        return out + "> " + cnt + "\n";
    }

    private String describe(Value base, int indent, HashSet<String> used) {
        String out = "";
        if (base instanceof Resource && !used.contains(base.stringValue())) {
            used.add(base.stringValue());
            URI baseURI = new URIImpl(base.stringValue());
            String basePrefix = Namespace.prefix(baseURI.getNamespace());
            String baseValue = "";
            if (basePrefix == null) {
                baseValue = baseURI.stringValue();
            } else {
                baseValue = basePrefix + ":" + baseURI.getLocalName();
            }
            out += indent(indent, baseValue);
            indent += 2;
            Iterator<Statement> i = match((Resource) base, null, null);
            while (i.hasNext()) {
                Statement s = i.next();
                Value obj = s.getObject();
                String value = obj.stringValue();
                if (obj instanceof Resource) {
                    URI objURI = new URIImpl(obj.stringValue());
                    String prefix = Namespace.prefix(objURI.getNamespace());

                    if (prefix == null) {
                        value = obj.stringValue();
                    } else {
                        value = prefix + ":" + objURI.getLocalName();
                    }
                }
                if (obj instanceof Literal) {
                    Literal literal = (Literal) obj;
                    URI dt = literal.getDatatype();
                    value = "\""
                            + literal.stringValue()
                            + "\" type of "
                            + Namespace.prefix(dt.getNamespace()) + ":"
                            + dt.getLocalName();
                }
                URI p = s.getPredicate();

                out += indent(indent, (Namespace.prefix(p.getNamespace()) + ":" + p.getLocalName() + ": " + value));
                if (!p.stringValue().equals(Rdf.rdfType.stringValue())) {
                    out += describe(s.getObject(), (indent + 2), used);
                }
            }
        }
        return out;
    }

    /**
     * Returns the human readable serialization of graph. The serialization is
     * recursively generated, for each instance, the values of all properties
     * are listed.
     *
     * @return Graph serialization.
     */
    public String describe() {
        return describe(new URIImpl(baseURI), 0, new HashSet<String>());
    }

    public String describe(int indent) {
        return describe(new URIImpl(baseURI), indent, new HashSet<String>());
    }

    public boolean createsCycle(Graph g, Statement s) {
        Iterator<Statement> i = g.match((Resource) s.getObject(), null, null);
        boolean exists = i.hasNext();
        return exists;
    }

}
