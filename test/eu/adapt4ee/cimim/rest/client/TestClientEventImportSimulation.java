package eu.adapt4ee.cimim.rest.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.MediaType;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.basex.core.cmd.Replace;
import org.basex.core.cmd.XQuery;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import eu.adapt4ee._2012.schema.cim.BuildingExport;
import eu.adapt4ee._2012.schema.cim.ListOfEquipments;
import eu.adapt4ee._2012.schema.cim.ListOfEvents;
import eu.adapt4ee._2012.schema.cim.ListOfMeasurementFileIDs;
import eu.adapt4ee._2012.schema.cim.ListOfProcesses;
import eu.adapt4ee._2012.schema.cim.ListOfSensors;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;
import eu.adapt4ee.cimim.rest.util.xml.UtilFactory;

/**
 * @author jan hreno
 *
 */
public class TestClientEventImportSimulation {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		String serverURL = "http://localhost:8080/Adapt4EE_CIMIM/rest";
		// String serverURL = "http://147.232.202.15:8080/Adapt4EE_CIMIM/rest";
		String param = "1";
		String serviceURL = serverURL + "";
		Client c = Client.create();
		WebResource r = c.resource(serviceURL);
		String root = System.getProperty("user.dir");
		String filepath = root + "/path/to/xml/file.xml"; // in case of Windows:
															// "\\path\\to\\yourfile.txt
		String resp = "";

		

		
		
		
		param = "simid";
		serviceURL = serverURL + "/simulationfile/" + param + ""; //

		System.out.println("\n\n################");
		System.out.println("calling " + serviceURL + "\n DELETE");
		System.out.println("################");
		r = c.resource(serviceURL);
		r.delete(ClientResponse.class);
	
	
		serviceURL = serverURL + "/simulationfile"; // PUT";
		// serviceURL = serverURL + "/simulationfile/streamimport"; // PUT";
		System.out.println("\n\n2################");
		System.out.println("calling " + serviceURL + "\n PUT");
		System.out.println("################");
		r = c.resource(serviceURL);
		filepath = "/Users/rodinka/Desktop/XMLs/simfile.xml";
		System.out.println("With XML: " + filepath);
		System.out.println("################");
		resp = r.type(MediaType.APPLICATION_XML).put(String.class,
				new File(filepath));
		System.out.println("response2: " + resp);
		
		
		
		serviceURL = serverURL + "/simulationfile/simid/caseid/event/import"; // PUT";
		System.out.println("\n\n2################");
		System.out.println("calling " + serviceURL + "\n PUT");
		System.out.println("################");
		r = c.resource(serviceURL);
		filepath = "/Users/rodinka/Desktop/XMLs/simEventsImport.xml";
		System.out.println("With XML: " + filepath);
		System.out.println("################");
		resp = r.type(MediaType.APPLICATION_XML).put(String.class,
				new File(filepath));
		System.out.println("response2: " + resp);
		
		
		
		

	}

}
