package eu.adapt4ee.cimim.ontology;

import java.io.OutputStream;
import java.util.Set;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.QueryLanguage;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.rio.n3.N3Writer;

import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.om.repository.OMRepository;
import eu.om.repository.RepositoryFactory;

import static eu.adapt4ee.cimim.ontology.Properties.NAMESPACE;

public class OntologyManager {
	
	private static final String DEFAULT_REPOSITORY_DIR = "repository";
	
	private RepositoryConnection conn;
	private ValueFactory factory;
	
	private OntologyManager(RepositoryConnection conn) {
		this.conn = conn;
		factory = conn.getValueFactory();
	}
	
	public boolean isEmpty() {
		try {
			RepositoryResult<Statement> stms = conn.getStatements(null, null, null, false, getContext(null));
			return ! stms.hasNext(); 
		} catch (RepositoryException re) {
		}
		return true;
	}
	
	public Set<String> getSuperClassesOf(String type) {
		return repository.getSuperClassesOf(type);
	}
	
	public Set<String> getSubClassesOf(String type) {
		return repository.getSubClassesOf(type);
	}
	
	public void getAllStatements(OutputStream output) {
		try {
			N3Writer writer = new N3Writer(output);
			writer.startRDF();
			for (Statement stmt : conn.getStatements(null, null, null, true).asList()) {
				writer.handleStatement(stmt);
			}
			writer.endRDF();
		} catch (Exception e) {
			throw new OntologyException(e);
		}
	}
	
	public void query(String sparql, OutputStream output) {
		try {
			GraphQuery query = conn.prepareGraphQuery(QueryLanguage.SPARQL, sparql);
			query.evaluate(new N3Writer(output));
		} catch (Exception e) {
			throw new OntologyException(e);
		}
	}
	
	public void update(MeasurementFile mf) {
		URI[] context = getContext(mf);
		try {
			conn.remove(null, null, null, context);
			new OntologyMapper(conn, context).apply(mf);
		} catch (RepositoryException re) {
			throw new OntologyException(re);
		}
	}
	
	public void clear() {
		repository.clear();
	}
	
	private URI[] getContext(MeasurementFile mf) {
		return mf != null ? new URI[] {
					factory.createURI(NAMESPACE + "/measurementFile#" + mf.getId()),
					factory.createURI(NAMESPACE + "/measurementFile")
				} : new URI[] {
					factory.createURI(NAMESPACE + "/measurementFile")
				};
	}
	
	private static OMRepository repository;
	
	public static synchronized OntologyManager getInstance() {
		if (repository == null) {
			String dir = System.getProperty("repository.dir");
			if (dir == null) {
				dir = DEFAULT_REPOSITORY_DIR;
			}
			repository = RepositoryFactory.owlim(dir);
			if (System.getProperty("ontologies.dir") != null) {
				repository.load();
			}
		}
		return new OntologyManager(repository.getConnection());
	}
	
}
