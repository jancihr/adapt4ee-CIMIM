package eu.om.ontology.model;

import eu.om.ontology.schema.OntologyClass;
import eu.om.ontology.schema.ResourceProperty;

public class Rdfs {
	public final static OntologyClass rdfsClass = 
		new OntologyClass(Namespace.rdfs + "Class");

	public final static ResourceProperty subClassOf = 
		new ResourceProperty(Namespace.rdfs + "subClassOf");

	public final static ResourceProperty domain = 
		new ResourceProperty(Namespace.rdfs + "domain");

	public final static ResourceProperty range = 
		new ResourceProperty(Namespace.rdfs + "range");
}
