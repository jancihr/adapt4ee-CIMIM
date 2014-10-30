package eu.om.ontology.model;

import eu.om.ontology.schema.OntologyClass;
import eu.om.ontology.schema.ResourceProperty;

public class Owl {
	public final static OntologyClass owlThing = 
		new OntologyClass(Namespace.owl + "Thing");
	
	public final static ResourceProperty datatypeProperty = 
		new ResourceProperty(Namespace.owl + "DatatypeProperty");
	
	public final static ResourceProperty objectProperty = 
		new ResourceProperty(Namespace.owl + "ObjectProperty");
	
}
