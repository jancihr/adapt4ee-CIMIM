package eu.om.ontology.model;

import eu.om.ontology.schema.LiteralProperty;

public class ServiceGrounding {
	public final static LiteralProperty groundingEndpoint = 
		new LiteralProperty(Namespace.ebbits + "groundingEndpoint");

	public final static LiteralProperty groundingMethod = 
		new LiteralProperty(Namespace.ebbits + "groundingMethod");

	public final static LiteralProperty groundingProtocol = 
		new LiteralProperty(Namespace.ebbits + "groundingProtocol");

	public final static LiteralProperty groundingOutputFormat = 
		new LiteralProperty(Namespace.ebbits + "groundingOutputFormat");
}
