package eu.om.ontology.model;

import eu.om.ontology.schema.LiteralProperty;
import eu.om.ontology.schema.ResourceProperty;

public class ServiceParameter {
	public final static LiteralProperty parameterName = 
		new LiteralProperty(Namespace.ebbits + "parameterName");

	public final static ResourceProperty parameterReference = 
		new ResourceProperty(Namespace.ebbits + "parameterReference");

}
