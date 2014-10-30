package eu.om.ontology.model;

import eu.om.ontology.schema.LiteralProperty;

public class EndpointRenderer {
	public final static LiteralProperty startCharacter = 
		new LiteralProperty(Namespace.ebbits + "startCharacter");

	public final static LiteralProperty parameterDelimiter = 
		new LiteralProperty(Namespace.ebbits + "parameterDelimiter");
}
