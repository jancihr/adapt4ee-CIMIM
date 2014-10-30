package eu.adapt4ee.cimim.rest.client;

import java.io.File;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import eu.adapt4ee._2012.schema.cim.BuildingExport;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.ListOfEquipments;
import eu.adapt4ee._2012.schema.cim.ListOfEvents;
import eu.adapt4ee._2012.schema.cim.ListOfMeasurementFileIDs;
import eu.adapt4ee._2012.schema.cim.ListOfProcesses;
import eu.adapt4ee._2012.schema.cim.ListOfSensors;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee._2012.schema.event.TCurrentEvent;
import eu.adapt4ee._2012.schema.event.TEnergyEvent;
import eu.adapt4ee._2012.schema.event.TEquipmentUsedEvent;
import eu.adapt4ee._2012.schema.event.TEvent;
import eu.adapt4ee._2012.schema.event.TExternalEnvironmentEvent;
import eu.adapt4ee._2012.schema.event.THVACEvent;
import eu.adapt4ee._2012.schema.event.TLightingEvent;
import eu.adapt4ee._2012.schema.event.TOccupantMovementEvent;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;

/**
 * @author jan hreno
 *
 */
public class TestClientCERTH {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

	//	String serverURL = "http://localhost:8080/Adapt4EE_CIMIM/rest";
		String serverURL = "http://147.232.202.15:8080/Adapt4EE_CIMIM/rest";
	//	String serverURL = "http://147.232.5.5:8080/Adapt4EE_CIMIM/rest";
		String param = "1";
		String serviceURL = serverURL + "";
		Client c = Client.create();
		WebResource r = c.resource(serviceURL);
		String root = System.getProperty("user.dir");
		String filepath = root+"/path/to/xml/file.xml"; // in case of Windows: "\\path\\to\\yourfile.txt
		String resp = "";
		
		


		
		
		
//		serviceURL = serverURL + "/measurementfile"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		ListOfMeasurementFileIDs res1 = r.type(MediaType.APPLICATION_XML).get(ListOfMeasurementFileIDs.class);
//		System.out.println("List of measurementFiles:\n"+StorageUtils.serializeObjectToStringXML(res1));
//		
//		
//		serviceURL = serverURL + "/measurementfile"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/1.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+ resp);
//		
//		serviceURL = serverURL + "/measurementfile"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/2.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+ resp);
//		
//		serviceURL = serverURL + "/measurementfile"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/certh.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+ resp);
//		
//		serviceURL = serverURL + "/measurementfile"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/1.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+ resp);
		
		
//		
//		
//		serviceURL = serverURL + "/measurementfile"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/2.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+ resp);
//		
//		serviceURL = serverURL + "/measurementfile"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/3.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+ resp);
//		
//		serviceURL = serverURL + "/measurementfile"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/4.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+ resp);
		
//		
//		serviceURL = serverURL + "/measurementfile"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/test_certh.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+ resp);
//		
//		param = "1";
//		
//		serviceURL = serverURL + "/measurementfile"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		System.out.println("result:");
//		r = c.resource(serviceURL);
//		ListOfMeasurementFileIDs res2 = r.type(MediaType.APPLICATION_XML).get(ListOfMeasurementFileIDs.class);
//		System.out.println("List of measurementFiles:\n"+StorageUtils.serializeObjectToStringXML(res2));
//		
//		
//		serviceURL = serverURL + "/measurementfile/" + param + ""; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		MeasurementFile mf1 = r.type(MediaType.APPLICATION_XML).get(MeasurementFile.class);
//		System.out.println("MeasurementFile with ID "+param+":\n"+StorageUtils.serializeObjectToStringXML(mf1));
//		
		
//		param="test_certh";
//		serviceURL = serverURL + "/measurementfile/" + param + ""; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		MeasurementFile mf1 = r.type(MediaType.APPLICATION_XML).get(MeasurementFile.class);
//		System.out.println("MeasurementFile with ID "+param+":\n"+StorageUtils.serializeObjectToStringXML(mf1));
		
		
		
		
//		param="1";
//		
//		serviceURL = serverURL + "/measurementfile/" + param + ""; // DELETE";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n DELETE");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		r.delete(ClientResponse.class);
//		
//param="2";
//		
//		serviceURL = serverURL + "/measurementfile/" + param + ""; // DELETE";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n DELETE");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		r.delete(ClientResponse.class);
//		
//		serviceURL = serverURL + "/measurementfile"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		ListOfMeasurementFileIDs res3 = r.type(MediaType.APPLICATION_XML).get(ListOfMeasurementFileIDs.class);
//		System.out.println("List of measurementFiles:\n"+StorageUtils.serializeObjectToStringXML(res3));
//		
//		
//		param="1";
//		serviceURL = serverURL + "/measurementfile/" + param + "/events"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		ListOfEvents ev = r.type(MediaType.APPLICATION_XML).get(ListOfEvents.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(ev));
//		
	
//		param= "1";
//		serviceURL = serverURL + "/measurementfile/" + param + "/importbuilding"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/BuildingImport.xml";
//		System.out.println("With XML: "+filepath+"\n");
//		System.out.println("################");
//		 resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		
//		
		
		
		
//		serviceURL = serverURL + "/measurementfile/" + 2 + "/events"; // DELETE";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n DELETE");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		r.delete(ClientResponse.class);
//		
//		int iter1 = 13000;
//		int iter2 = 10;
//		
//		long begintest = System.currentTimeMillis();
//		
//		for (int i=0;i<iter2;i++){
//		serviceURL = serverURL + "/measurementfile/" + 2 + "/importevents"; // PUT";
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/EventsImport.xml";
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		
//		
//		
//		
//		}
//		long endtest = System.currentTimeMillis();
//		System.out.println("Writing "+ iter1 + " events repeatedly "+iter2+" times via restful service takes:" + (endtest-begintest)/1000.0+" seconds");
//		
//		
//		begintest = System.currentTimeMillis();
//		param="1";
//		serviceURL = serverURL + "/measurementfile/" + 2 + "/events"; // GET";
//		//System.out.println("\n\n################");
//		//System.out.println("calling "+serviceURL+"\n GET");
//		//System.out.println("################");
//		r = c.resource(serviceURL);
//		ListOfEvents ev = r.type(MediaType.APPLICATION_XML).get(ListOfEvents.class);
//		//System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(ev));
//		endtest = System.currentTimeMillis();
//		System.out.println("Reading measurement file with "+ iter1*iter2 + " events via restful service takes:" + (endtest-begintest)/1000.0+" seconds");
//		
		
		
		
//		serviceURL = serverURL + "/sensortypes"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/sensorsList_new.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+resp);
//		
//		param="1";
//		serviceURL = serverURL + "/measurementfile/" + param + "/sensordiscovered"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/DeviceDiscovered.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+resp);
//		
//		param="1";
//		serviceURL = serverURL + "/measurementfile/" + param + "/sensorsdiscovered"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/ListOfSensors.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+resp);
//		
//		param="1";
//		serviceURL = serverURL + "/measurementfile/" + param + "/importmeasurements"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/Measurements.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+resp);
//		
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/events"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		 ev = r.type(MediaType.APPLICATION_XML).get(ListOfEvents.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(ev));
//		
//		
////		serviceURL = serverURL + "/measurementfile/" + param + "/events"; // DELETE";
////		System.out.println("\n\n################");
////		System.out.println("calling "+serviceURL+"\n DELETE");
////		System.out.println("################");
////		r = c.resource(serviceURL);
////		r.delete(ClientResponse.class);
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/events"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		 ev = r.type(MediaType.APPLICATION_XML).get(ListOfEvents.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(ev));
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/processes"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		ListOfProcesses lop = r.type(MediaType.APPLICATION_XML).get(ListOfProcesses.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(lop));
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/importprocess"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n DELETE");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/BPMImport.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+resp);
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/processes"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		 lop = r.type(MediaType.APPLICATION_XML).get(ListOfProcesses.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(lop));
//		
////		serviceURL = serverURL + "/measurementfile/" + param + "/processes"; // DELETE";
////		System.out.println("\n\n################");
////		System.out.println("calling "+serviceURL+"\n DELETE");
////		System.out.println("################");
////		r = c.resource(serviceURL);
////		r.delete(ClientResponse.class);
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/processes"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		 lop = r.type(MediaType.APPLICATION_XML).get(ListOfProcesses.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(lop));
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/building"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		BuildingExport be = r.type(MediaType.APPLICATION_XML).get(BuildingExport.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(be));
//		

		 
//		 param= "1";
//			serviceURL = serverURL + "/measurementfile/" + param + "/importbuilding"; // PUT";
//			System.out.println("\n\n################");
//			System.out.println("calling "+serviceURL+"\n PUT");
//			System.out.println("################");
//			r = c.resource(serviceURL);
//			filepath = root+"/test/xml/exmpl.xml";
//			System.out.println("With XML: "+filepath+"\n");
//			System.out.println("################");
//			 resp = r.type(MediaType.APPLICATION_XML)
//			.put(String.class, new File(filepath));
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/building"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		 be = r.type(MediaType.APPLICATION_XML).get(BuildingExport.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(be));
//		
//		
////		serviceURL = serverURL + "/measurementfile/" + param + "/building";// DELETE";
////		System.out.println("\n\n################");
////		System.out.println("calling "+serviceURL+"\n DELETE");
////		System.out.println("################");
////		r = c.resource(serviceURL);
////		r.delete(ClientResponse.class);
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/building"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		 be = r.type(MediaType.APPLICATION_XML).get(BuildingExport.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(be));
//		
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/sensors"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		ListOfSensors los = r.type(MediaType.APPLICATION_XML).get(ListOfSensors.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(los));
//		
		
//		param="certh";
//		serviceURL = serverURL + "/measurementfile/" + param + "/sensordiscovered"; // PUT";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n PUT");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		filepath = root+"/test/xml/DeviceDiscovered.xml";
//		System.out.println("With XML: "+filepath);
//		System.out.println("################");
//		resp = r.type(MediaType.APPLICATION_XML)
//		.put(String.class, new File(filepath));
//		System.out.println("response: "+resp);
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/sensors"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		 los = r.type(MediaType.APPLICATION_XML).get(ListOfSensors.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(los));
//		
////		serviceURL = serverURL + "/measurementfile/" + param + "/sensors"; // DELETE";
////		System.out.println("\n\n################");
////		System.out.println("calling "+serviceURL+"\n DELETE");
////		System.out.println("################");
////		r = c.resource(serviceURL);
////		r.delete(ClientResponse.class);
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/sensors"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		 los = r.type(MediaType.APPLICATION_XML).get(ListOfSensors.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(los));
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/equipments"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		ListOfEquipments loe = r.type(MediaType.APPLICATION_XML).get(ListOfEquipments.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(loe));
//		
////		serviceURL = serverURL + "/measurementfile/" + param + "/equipments"; // DELETE";
////		System.out.println("\n\n################");
////		System.out.println("calling "+serviceURL+"\n DELETE");
////		System.out.println("################");
////		r = c.resource(serviceURL);
////		r.delete(ClientResponse.class);
//		
//		serviceURL = serverURL + "/measurementfile/" + param + "/equipments"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		 loe = r.type(MediaType.APPLICATION_XML).get(ListOfEquipments.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(loe));
//		
		

//		
//
//		String createdID = "";
//
//		
//		
//		System.out.println("===== Create measurement file ID=id =====");
//
//		
//		
//		System.out.println("Resp: " + resp);
//		createdID = resp;
//
//		System.out.println("===== Create measurement file ID=1 =====");
//
//		filepath = "/test/xml/MeasurementFileID1.xml"; // in case of Windows:
//														// "\\path
//														// \\to\\yourfile.txt
//		
//
//		System.out.println("===== skusime nevalidne XMLko uploadnut =====");
//		// skusime nevalidne XMLko uploadnut
//		c = Client.create();
//		r = c.resource(serverURL + "measurement");
//		System.out.println("===== Create measurement file =====");
//		root = System.getProperty("user.dir");
//		// System.out.println("root: "+root);
//		filepath = "/test/xml/SimulationFile.xml";
//		String abspath = root + filepath;
//		resp = r.type(MediaType.APPLICATION_XML).accept(MediaType.TEXT_XML)
//				.put(String.class, new File(abspath));
//		System.out.println("Non valid XML Resp: " + resp);
//
//		c = Client.create();
//		r = c.resource(serverURL + "measurement/" + createdID + "/importevents");
//		System.out.println("===== Create eventsResource III =====");
//
//		filepath = "/test/xml/EventsImport.xml"; // in case of Windows:
//		abspath = root + filepath;
//
//		resp = r.type(MediaType.APPLICATION_XML).accept(MediaType.TEXT_XML)
//				.put(String.class, new File(abspath));
//		System.out.println("Resp: " + resp);
//
//		c = Client.create();
//		r = c.resource(serverURL + "measurement/" + createdID
//				+ "/importprocess");
//		System.out.println("===== Create processResource =====");
//		// System.out.println("root: "+root);
//		filepath = "/test/xml/BPMImport.xml"; // in case of Windows: "\\path
//												// \\to\\yourfile.txt
//		abspath = root + filepath;
//
//		resp = r.type(MediaType.APPLICATION_XML).accept(MediaType.TEXT_XML)
//				.put(String.class, new File(abspath));
//		System.out.println("Resp: " + resp);
//
//		System.out.println("===== Create processResource II =====");
//		// System.out.println("root: "+root);
//		filepath = "/test/xml/BP_UNAV_Meal_Delivery_xml.xml"; // in case of
//																// Windows:
//																// "\\path
//																// \\to\\yourfile.txt
//		abspath = root + filepath;
//
//		resp = r.type(MediaType.APPLICATION_XML).accept(MediaType.TEXT_XML)
//				.put(String.class, new File(abspath));
//		System.out.println("Resp: " + resp);

		// System.out.println("===== Create eventsResource =====");
		// Events evs=new Events();
		// evs.setId("fromClient");
		// evs.setName("name2");
		// List<Event> evl = new LinkedList<Event>();
		// evl.add(new Event("daco","nieco"));
		// evl.add(new Event("nieco", "vselico"));
		// evs.setEvents(evl);
		// ClientResponse response =
		// r.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, c);
		// System.out.println(response.getStatus());
		//
		// System.out.println("===== Create eventsResource II =====");
		// EventsImport evim = new EventsImport();
		// TEvent ev1 = new TEvent();
		// TEvent ev2 = new TEvent();
		// List<TEvent> list = evim.getEvent();
		// list.add(ev1);
		// list.add(ev2);
		// TEquipmentUsedEvent tequ = new TEquipmentUsedEvent();
		// tequ.setEquipmentRef("equipmentID");
		// ev1.setEquipmentUsed(tequ);
		// TSpaceEnvironmentEvent sen = new TSpaceEnvironmentEvent();
		// ev1.setSpaceEnvironmentChange(sen);

		// ClientResponse responseIII =
		// r.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, evim);
		// System.out.println(responseIII.getStatus());
		
		

//		serviceURL = serverURL + "/measurementfile/" + 2 + "/zones"; // GET";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n GET");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		ListOfEvents ev = r.type(MediaType.APPLICATION_XML).get(ListOfEvents.class);
//		System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(ev));
//		
//	
		serviceURL = serverURL + "/measurementfile/" + 2 + "/zones"; // PUT";
		System.out.println("\n\n################");
		System.out.println("calling "+serviceURL+"\n PUT");
		System.out.println("################");
		r = c.resource(serviceURL);
		filepath = root+"/test/xml/zones.xml";
		System.out.println("With XML: "+filepath+"\n");
		System.out.println("################");
		 resp = r.type(MediaType.APPLICATION_XML)
		.put(String.class, new File(filepath));
//	
	
//		serviceURL = serverURL + "/measurementfile/" + 2 + "/zones"; // DELETE";
//		System.out.println("\n\n################");
//		System.out.println("calling "+serviceURL+"\n DELETE");
//		System.out.println("################");
//		r = c.resource(serviceURL);
//		r.delete(ClientResponse.class);
//		
		

	}

}
