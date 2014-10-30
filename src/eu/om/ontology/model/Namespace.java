package eu.om.ontology.model;

import java.util.*;

import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

public class Namespace {
	private static Map<String, String> prefixes = new HashMap<String, String>();
	private static Set<String> staticType = new HashSet<String>();

	public final static String ontologyURI = "http://ebbits.eu/demo36#";

	public final static String owl = "http://www.w3.org/2002/07/owl#";
	public final static String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public final static String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
	public final static String xsd = "http://www.w3.org/2001/XMLSchema#";

	public final static String ebbits = ontologyURI;
	public final static String inertia = "http://inertia.eu/demo#";
	public final static String moje = "http://www.adapt4ee.eu/2012/schema/unnamed#";
	public final static String demo = "http://adapt4ee.eu/demo#";

	static {
		prefixes.put("owl", owl);
		prefixes.put("rdf", rdf);
		prefixes.put("rdfs", rdfs);
		prefixes.put("xsd", xsd);
		prefixes.put("ebbits", ebbits);

		prefixes.put("inertia", inertia);
		prefixes.put("moje", moje);
		prefixes.put("demo", demo);
	}
	static {
		staticType.add(rdf);
		staticType.add(rdfs);
		staticType.add(owl);
	}

	public static String uriFromPrefixedForm(String prefixed){
		String[] parts = prefixed.split(":");
		if(parts.length == 2 && !parts[0].trim().equals("") && !parts[1].trim().equals("")){
			String prefixURI = prefixes.get(parts[0].trim());
			if(prefixURI != null){
				return prefixURI + parts[1];
			}
		}
		return prefixed;
	}
	
	public static String uriToPrefixedForm(String uri, String delimiter){
		String[] parts = uri.split("#");
		return prefix(parts[0]+"#")+delimiter+parts[1];
	}

	public static String uriToPrefixedForm(String uri){
		return uriToPrefixedForm(uri, ":");
	}

	/**
	 * Returns the prefix of specified ontology URI.
	 * @param uri Ontology URI.
	 * @return Namespace refix for ontology URI or null.
	 */
	public static String prefix(String uri){
		Iterator<String> i = prefixes.keySet().iterator();
		while(i.hasNext()){
			String prefix = i.next();
			if(prefixes.get(prefix).equals(uri)) return prefix; 
		}
		return null;
	}

	/**
	 * Identifies, if URI is static type. Static types are currently ontology classes from
	 * RDF and RDSF ontologies. Purpose of static type identification is to stop recursive
	 * searches on the top of taxonomies defined in ontologies.
	 * @param t Type URI.
	 * @return Notification if URI is static type.
	 */
	public static boolean isStaticType(URI t){
		if(staticType.contains(t.getNamespace())) return true;
		return false;
	}

	/**
	 * Check, if the property is static. Static properties are used in graph operations, for example
	 * not to remove or clone static values, such as ontology classes or static instances 
	 * (e.g. units or security mechanisms). Static instances must not be multiplied in the repository and
	 * must not be removed.
	 * @param p Ontology property.
	 * @return Notification if property is static.
	 */
	public static boolean isStaticProperty(URI p){
		if(staticType.contains(p.getNamespace())) return true;
		return false;
	}

	public static String queryPrefixes(){
		String out = "";
		Iterator<String> i = prefixes.keySet().iterator();
		while(i.hasNext()){
			String prefix = i.next();
			out += "PREFIX "+prefix+": <"+prefixes.get(prefix)+"> \n"; 
		}
		return out;
	}
}
