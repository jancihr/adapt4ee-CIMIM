package eu.adapt4ee.cimim.ontology;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.basex.core.cmd.XQuery;

import eu.adapt4ee._2012.schema.bim.TEquipment;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.event.TEvent;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;

public class ConsumptionMatcher {
	
	
	public List<TEvent> getConsumptionEvents(TEquipment eq, String spaceID, String measurementID) {
		String q;
		StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase.getInstance(false);

		ByteArrayOutputStream queryResult = new ByteArrayOutputStream();
		// QUERY
		q = sf.NAMESPACE_DECLARATION + getQuery(eq, spaceID, measurementID);
		sf.executeCommand(new XQuery(q), queryResult);

		if (queryResult
				.toString()
				.equals("<ns2:EventsExport xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\"/>")) {
			return null;
		}

		//System.out.println(q);
		//System.out.println(queryResult);
		
		EventsImport evimport = StorageUtils.unserializeDocument(queryResult, EventsImport.class);
		return evimport.getEvent();
	}
	
	private String getQuery(TEquipment eq, String spaceID, String measurementFileID) {
		String query;
		
		String sq = "";
		for (String sensorRef : eq.getSensorRef()) {
			if (! sq.isEmpty()) {
				sq += " or ";
			}
			sq += "sensorRef = \"" + sensorRef + "\"";
		}
		
		if (! sq.isEmpty()) {
			sq = "[" + sq + "]";
		}
		
		query = "<ns2:EventsImport xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
				+ "{/ns2:MeasurementFile[id=\"" + measurementFileID + "\"]/events/event/Energy[spaceRef=\""
				+ spaceID
				+ "\"]"
				+ sq
				+ "/.. }"
				+ "</ns2:EventsImport>";
		//System.out.println("query:" + query);
		return query;
	}

}
