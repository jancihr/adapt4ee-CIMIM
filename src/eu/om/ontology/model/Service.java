package eu.om.ontology.model;

import eu.om.ontology.schema.ResourceProperty;

public class Service {
	public final static ResourceProperty serviceInput = 
		new ResourceProperty(Namespace.ebbits + "serviceInput");

	public final static ResourceProperty serviceOutput = 
		new ResourceProperty(Namespace.ebbits + "serviceOutput");

	public final static ResourceProperty hasPrototype = 
		new ResourceProperty(Namespace.ebbits + "hasPrototype");


	public final static ResourceProperty serviceGrounding = 
		new ResourceProperty(Namespace.ebbits + "serviceGrounding");

	public final static ResourceProperty endpointRenderer = 
		new ResourceProperty(Namespace.ebbits + "endpointRenderer");

}
