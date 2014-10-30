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
public class TestClientAddEventByEventWithConsoleCheck {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		//String serverURL = "http://localhost:8080/Adapt4EE_CIMIM/rest";
		String serverURL = "http://147.232.202.15:8080/Adapt4EE_CIMIM/rest";
		String param = "1";
		String serviceURL = serverURL + "";
		Client c = Client.create();
		WebResource r = c.resource(serviceURL);
		String root = System.getProperty("user.dir");
		String filepath = root + "/path/to/xml/file.xml"; // in case of Windows:
															// "\\path\\to\\yourfile.txt
		String resp = "";

		//
		//
		//
		//
		//
		// serviceURL = serverURL + "/measurementfile"; // GET";
		// System.out.println("\n\n################");
		// System.out.println("calling "+serviceURL+"\n GET");
		// System.out.println("################");
		// r = c.resource(serviceURL);
		// ListOfMeasurementFileIDs res1 =
		// r.type(MediaType.APPLICATION_XML).get(ListOfMeasurementFileIDs.class);
		// System.out.println("List of measurementFiles:\n"+StorageUtils.serializeObjectToStringXML(res1));
		//
		//

		// new Thread(new Runnable() {
		// public void run() {
		// String serverURL = "http://localhost:8080/Adapt4EE_CIMIM/rest/";
		// String param = "1";
		// String serviceURL = serverURL + "";
		// Client c = Client.create();
		// WebResource r = c.resource(serviceURL);
		// String root = System.getProperty("user.dir");
		// String filepath = root+"/path/to/xml/file.xml"; // in case of
		// Windows: "\\path\\to\\yourfile.txt
		// String resp = "";
		//
		// serviceURL = serverURL + "/measurementfile"; // PUT";
		// System.out.println("\n\n1################");
		// System.out.println("calling "+serviceURL+"\n PUT");
		// System.out.println("################");
		// r = c.resource(serviceURL);
		// filepath = root+"/test/xml/MeasurementFile1.xml";
		// System.out.println("With XML: "+filepath);
		// System.out.println("################");
		// resp = r.type(MediaType.APPLICATION_XML)
		// .put(String.class, new File(filepath));
		// System.out.println("response1: "+ resp);
		//
		// }
		// }).start();
		//
		//
		//
		// new Thread(new Runnable() {
		// public void run() {
		// String serverURL = "http://localhost:8080/Adapt4EE_CIMIM/rest/";
		// String param = "1";
		// String serviceURL = serverURL + "";
		// Client c = Client.create();
		// WebResource r = c.resource(serviceURL);
		// String root = System.getProperty("user.dir");
		// String filepath = root+"/path/to/xml/file.xml"; // in case of
		// Windows: "\\path\\to\\yourfile.txt
		// String resp = "";
		//
		// serviceURL = serverURL + "/measurementfile"; // PUT";
		// System.out.println("\n\n2################");
		// System.out.println("calling "+serviceURL+"\n PUT");
		// System.out.println("################");
		// r = c.resource(serviceURL);
		// filepath = root+"/test/xml/test_certh.xml";
		// System.out.println("With XML: "+filepath);
		// System.out.println("################");
		// resp = r.type(MediaType.APPLICATION_XML)
		// .put(String.class, new File(filepath));
		// System.out.println("response2: "+ resp);
		//
		//
		// }
		// }).start();
		//
		//
		//
		// new Thread(new Runnable() {
		// public void run() {
		// String serverURL = "http://localhost:8080/Adapt4EE_CIMIM/rest/";
		// String param = "1";
		// String serviceURL = serverURL + "";
		// Client c = Client.create();
		// WebResource r = c.resource(serviceURL);
		// String root = System.getProperty("user.dir");
		// String filepath = root+"/path/to/xml/file.xml"; // in case of
		// Windows: "\\path\\to\\yourfile.txt
		// String resp = "";
		// serviceURL = serverURL + "/measurementfile/" + param +
		// "/importevents"; // PUT";
		// System.out.println("\n\n3################");
		// System.out.println("calling "+serviceURL+"\n PUT");
		// System.out.println("################");
		// r = c.resource(serviceURL);
		// filepath = root+"/test/xml/EventsImport.xml";
		// System.out.println("With XML: "+filepath);
		// System.out.println("################");
		// resp = r.type(MediaType.APPLICATION_XML)
		// .put(String.class, new File(filepath));
		// System.out.println("response3: "+resp);
		//
		// }
		// }).start();
		//
		//
		//
		// new Thread(new Runnable() {
		// public void run() {
		// String serverURL = "http://localhost:8080/Adapt4EE_CIMIM/rest/";
		// String param = "1";
		// String serviceURL = serverURL + "";
		// Client c = Client.create();
		// WebResource r = c.resource(serviceURL);
		// String root = System.getProperty("user.dir");
		// String filepath = root+"/path/to/xml/file.xml"; // in case of
		// Windows: "\\path\\to\\yourfile.txt
		// String resp = "";
		//
		// serviceURL = serverURL + "/measurementfile"; // PUT";
		// System.out.println("\n\n4################");
		// System.out.println("calling "+serviceURL+"\n PUT");
		// System.out.println("################");
		// r = c.resource(serviceURL);
		// filepath = root+"/test/xml/MeasurementFile1.xml";
		// System.out.println("With XML: "+filepath);
		// System.out.println("################");
		// resp = r.type(MediaType.APPLICATION_XML)
		// .put(String.class, new File(filepath));
		// System.out.println("response4: "+ resp);
		//
		// }
		// }).start();
		//
		//
		//

		// param="ISA_MAIN_10-11_2013";
		// serviceURL = serverURL + "/measurementfile/" + param + ""; //
		//
		// System.out.println("\n\n################");
		// System.out.println("calling "+serviceURL+"\n DELETE");
		// System.out.println("################");
		// r = c.resource(serviceURL);
		// r.delete(ClientResponse.class);

		param = "ISA_MAIN_Filled";
		serviceURL = serverURL + "/measurementfile/" + param + ""; //

		System.out.println("\n\n################");
		System.out.println("calling " + serviceURL + "\n DELETE");
		System.out.println("################");
		r = c.resource(serviceURL);
		r.delete(ClientResponse.class);
		serviceURL = serverURL + "/measurementfile"; // PUT";
		// serviceURL = serverURL + "/measurementfile/streamimport/lowmem"; //
		// PUT";
		// serviceURL = serverURL + "/measurementfile/streamimport"; // PUT";
		System.out.println("\n\n2################");
		System.out.println("calling " + serviceURL + "\n PUT");
		System.out.println("################");
		r = c.resource(serviceURL);
		filepath = "/Users/rodinka/Desktop/XMLs/ISA_MAIN_Filled_nobigdata.xml";
		System.out.println("With XML: " + filepath);
		System.out.println("################");
		resp = r.type(MediaType.APPLICATION_XML).put(String.class,
				new File(filepath));
		System.out.println("response2: " + resp);

		try {
			InputStream m = new FileInputStream(new File(
					"/Users/rodinka/Desktop/XMLs/events.xml"));

			double count = 0;
			double skipCount = 0;
			XMLInputFactory factory = XMLInputFactory.newInstance();
			String measurementID = "";
			XMLStreamReader streamReader = factory.createXMLStreamReader(m);

			int i = 0;
			int bunchCount = 0;
			String noEventsData = "";

			String eventBunch = "<ns2:ListOfEvents xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">";
			
			while (streamReader.hasNext()) {
				streamReader.next();
				if (streamReader.isStartElement()) {

					

					// parse Events
					if (streamReader.getLocalName() == "event") {
						// System.out.println("######parsing EVENTS#######");
						bunchCount++;
						skipCount++;

						count++;
						
						String singleEvent = "<event>";

						while (streamReader.hasNext()
								&& !(streamReader.isEndElement() && streamReader
										.getLocalName() == "event")) {
							streamReader.next();

							if (streamReader.isStartElement()) {
								String s = "";

								s = s.concat("<"
										+ (streamReader.getPrefix()
												.equalsIgnoreCase("") ? ""
												: streamReader.getPrefix()
														+ ":")
										+ streamReader.getLocalName());
								int x = streamReader.getNamespaceCount();
								int y = streamReader.getAttributeCount();
								while (--x > -1) {

									s = s.concat(" xmlns:"
											+ streamReader
													.getNamespacePrefix(x)
											+ "=\""
											+ streamReader.getNamespaceURI(x)
											+ "\"");
								}

								while (--y > -1) {
									s = s.concat(" "
											+ (streamReader.getAttributePrefix(
													y).equalsIgnoreCase("") ? ""
													: streamReader
															.getAttributePrefix(y)
															+ ":")
											+ streamReader
													.getAttributeLocalName(y)
											+ "=\""
											+ streamReader.getAttributeValue(y).replaceAll("&", "&amp;")
											+ "\"");
								}

								s = s.concat(">");

								singleEvent = singleEvent.concat(s);

							}

							else if (streamReader.isWhiteSpace()) {
							} else if (streamReader.isCharacters()) {
								String s = (streamReader.getText());
								s = s.replaceAll("&", "&amp;");
								singleEvent = singleEvent.concat(s);
							} else if (streamReader.isEndElement()) {
								if (streamReader.getLocalName() == "events")
									break;

								String s = ("</"
										+ (streamReader.getPrefix()
												.equalsIgnoreCase("") ? ""
												: streamReader.getPrefix()
														+ ":")
										+ streamReader.getLocalName() + ">");
								singleEvent = singleEvent.concat(s);
							}

						}
						
						eventBunch = eventBunch + singleEvent;

						//skip some
					/*	if (skipCount< 2600000 && bunchCount > 499) {
							
							
							System.out.println("skipping:" + count);
							
							
							
							bunchCount = 0;
							eventBunch = "<ns2:ListOfEvents xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">";
						}
						*/
						if (bunchCount > 499) {
							
							eventBunch = eventBunch + "</ns2:ListOfEvents>";
							System.out.println("events so far:" + count);
							//System.out.println(" CURRENT bunch: " + eventBunch);
							
							serviceURL = serverURL + "/measurementfile/" + param
									+ "/importevents"; // PUT";

							r = c.resource(serviceURL);
							r.type(MediaType.APPLICATION_XML).put(String.class,eventBunch);
							
							bunchCount = 0;
							eventBunch = "<ns2:ListOfEvents xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">";
						}
						

					}
				}
			}

			
			
				
				eventBunch = eventBunch + "</ns2:ListOfEvents>";
				System.out.println("events so far:" + count);
				//System.out.println(" CURRENT bunch: " + eventBunch);
				
				serviceURL = serverURL + "/measurementfile/" + param
						+ "/importevents"; // PUT";

				r = c.resource(serviceURL);
				r.type(MediaType.APPLICATION_XML).put(String.class,eventBunch);
				
			
			
			
			streamReader.close();
			try {
				m.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * serviceURL = serverURL + "/measurementfile"; // PUT"; // serviceURL =
		 * serverURL + "/measurementfile/streamimport"; // PUT";
		 * System.out.println("\n\n2################");
		 * System.out.println("calling " + serviceURL + "\n PUT");
		 * System.out.println("################"); r = c.resource(serviceURL);
		 * filepath =
		 * "/Users/rodinka/Desktop/XMLs/ISA_MAIN_10-11_2013_mini_measurementfile.xml"
		 * ; System.out.println("With XML: " + filepath);
		 * System.out.println("################"); resp =
		 * r.type(MediaType.APPLICATION_XML).put(String.class, new
		 * File(filepath)); System.out.println("response2: " + resp);
		 * 
		 * serviceURL = serverURL + "/measurementfile"; // PUT"; // serviceURL =
		 * serverURL + "/measurementfile/streamimport"; // PUT";
		 * System.out.println("\n\n2################");
		 * System.out.println("calling " + serviceURL + "\n PUT");
		 * System.out.println("################"); r = c.resource(serviceURL);
		 * filepath =
		 * "/Users/rodinka/Desktop/XMLs/EYE_CLINIC_measurementfile.xml";
		 * System.out.println("With XML: " + filepath);
		 * System.out.println("################"); resp =
		 * r.type(MediaType.APPLICATION_XML).put(String.class, new
		 * File(filepath)); System.out.println("response2: " + resp); //
		 * serviceURL = serverURL + "/measurementfile"; // PUT"; // serviceURL =
		 * serverURL + "/measurementfile/streamimport"; // PUT";
		 * System.out.println("\n\n2################");
		 * System.out.println("calling " + serviceURL + "\n PUT");
		 * System.out.println("################"); r = c.resource(serviceURL);
		 * filepath =
		 * "/Users/rodinka/Desktop/XMLs/ISA_TECH_measurementfile.xml";
		 * System.out.println("With XML: " + filepath);
		 * System.out.println("################"); resp =
		 * r.type(MediaType.APPLICATION_XML).put(String.class, new
		 * File(filepath)); System.out.println("response2: " + resp);
		 * 
		 * 
		 * 
		 * 
		 * serviceURL = serverURL + "/measurementfile"; // PUT"; // serviceURL =
		 * serverURL + "/measurementfile/streamimport"; // PUT";
		 * System.out.println("\n\n2################");
		 * System.out.println("calling " + serviceURL + "\n PUT");
		 * System.out.println("################"); r = c.resource(serviceURL);
		 * filepath =
		 * "/Users/rodinka/Desktop/XMLs/ISA_MAIN_measurementfile.xml";
		 * System.out.println("With XML: " + filepath);
		 * System.out.println("################"); resp =
		 * r.type(MediaType.APPLICATION_XML).put(String.class, new
		 * File(filepath)); System.out.println("response2: " + resp);
		 * 
		 * 
		 * 
		 * // serviceURL = serverURL + "/measurementfile"; // PUT"; //
		 * serviceURL = serverURL + "/measurementfile/streamimport"; // PUT";
		 * System.out.println("\n\n2################");
		 * System.out.println("calling " + serviceURL + "\n PUT");
		 * System.out.println("################"); r = c.resource(serviceURL);
		 * filepath = "/Users/rodinka/Desktop/XMLs/UNAV_measurementfile.xml";
		 * System.out.println("With XML: " + filepath);
		 * System.out.println("################"); resp =
		 * r.type(MediaType.APPLICATION_XML).put(String.class, new
		 * File(filepath)); System.out.println("response2: " + resp);
		 * 
		 * // serviceURL = serverURL + "/measurementfile"; // PUT"; //
		 * System.out.println("\n\n5################"); //
		 * System.out.println("calling " + serviceURL + "\n PUT"); //
		 * System.out.println("################"); // r =
		 * c.resource(serviceURL); // filepath = root +
		 * "/test/xml/_MeasurementFile.xml"; // System.out.println("With XML: "
		 * + filepath); // System.out.println("################"); // resp =
		 * r.type(MediaType.APPLICATION_XML).put(String.class, // new
		 * File(filepath)); // System.out.println("response5: " + resp);
		 * 
		 * // #################importevents
		 * 
		 * // for (int i = 0; i < 20; i++) { // serviceURL = serverURL +
		 * "/measurementfile/test/importevents"; // // PUT"; //
		 * System.out.println("\n\n5################"); //
		 * System.out.println("calling " + serviceURL + "\n PUT"); //
		 * System.out.println("################"); // r =
		 * c.resource(serviceURL); // filepath = root +
		 * "/test/xml/_EventsImport.xml"; // System.out.println("With XML: " +
		 * filepath); // System.out.println("################"); // resp =
		 * r.type(MediaType.APPLICATION_XML).put(String.class, // new
		 * File(filepath)); // System.out.println(i + ". events added: " +
		 * resp); // }
		 * 
		 * // // serviceURL = serverURL + "/simulationfile"; // PUT";
		 * System.out.println("\n\n################");
		 * System.out.println("calling "+serviceURL+"\n PUT");
		 * System.out.println("################"); r = c.resource(serviceURL);
		 * filepath = root+"/test/xml/_SimulationFile.xml";
		 * System.out.println("With XML: "+filepath);
		 * System.out.println("################"); resp =
		 * r.type(MediaType.APPLICATION_XML) .put(String.class, new
		 * File(filepath)); System.out.println("response: "+ resp);
		 */

		// //
		// // param = "1";
		// //
		// // serviceURL = serverURL + "/measurementfile"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // System.out.println("result:");
		// // r = c.resource(serviceURL);
		// // ListOfMeasurementFileIDs res2 =
		// r.type(MediaType.APPLICATION_XML).get(ListOfMeasurementFileIDs.class);
		// //
		// System.out.println("List of measurementFiles:\n"+StorageUtils.serializeObjectToStringXML(res2));
		// //
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param + ""; //
		// GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // MeasurementFile mf1 =
		// r.type(MediaType.APPLICATION_XML).get(MeasurementFile.class);
		// //
		// System.out.println("MeasurementFile with ID "+param+":\n"+StorageUtils.serializeObjectToStringXML(mf1));
		// //
		// //// param="2";
		// //// serviceURL = serverURL + "/measurementfile/" + param + ""; //
		// DELETE";
		// //// System.out.println("\n\n################");
		// //// System.out.println("calling "+serviceURL+"\n DELETE");
		// //// System.out.println("################");
		// //// r = c.resource(serviceURL);
		// //// r.delete(ClientResponse.class);
		// //
		// // serviceURL = serverURL + "/measurementfile"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // ListOfMeasurementFileIDs res3 =
		// r.type(MediaType.APPLICATION_XML).get(ListOfMeasurementFileIDs.class);
		// //
		// System.out.println("List of measurementFiles:\n"+StorageUtils.serializeObjectToStringXML(res3));
		// //
		// //
		// // param="1";
		// // serviceURL = serverURL + "/measurementfile/" + param + "/events";
		// // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // ListOfEvents ev =
		// r.type(MediaType.APPLICATION_XML).get(ListOfEvents.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(ev));
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/importevents"; // PUT";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n PUT");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // filepath = root+"/test/xml/EventsImport.xml";
		// // System.out.println("With XML: "+filepath);
		// // System.out.println("################");
		// // resp = r.type(MediaType.APPLICATION_XML)
		// // .put(String.class, new File(filepath));
		// // System.out.println("response: "+resp);
		// //
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param + "/events";
		// // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // ev = r.type(MediaType.APPLICATION_XML).get(ListOfEvents.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(ev));
		// //
		// //
		// //// serviceURL = serverURL + "/measurementfile/" + param +
		// "/events"; // DELETE";
		// //// System.out.println("\n\n################");
		// //// System.out.println("calling "+serviceURL+"\n DELETE");
		// //// System.out.println("################");
		// //// r = c.resource(serviceURL);
		// //// r.delete(ClientResponse.class);
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param + "/events";
		// // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // ev = r.type(MediaType.APPLICATION_XML).get(ListOfEvents.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(ev));
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/processes"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // ListOfProcesses lop =
		// r.type(MediaType.APPLICATION_XML).get(ListOfProcesses.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(lop));
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/importprocess"; // PUT";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n DELETE");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // filepath = root+"/test/xml/BPMImport.xml";
		// // System.out.println("With XML: "+filepath);
		// // System.out.println("################");
		// // resp = r.type(MediaType.APPLICATION_XML)
		// // .put(String.class, new File(filepath));
		// // System.out.println("response: "+resp);
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/processes"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // lop =
		// r.type(MediaType.APPLICATION_XML).get(ListOfProcesses.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(lop));
		// //
		// //// serviceURL = serverURL + "/measurementfile/" + param +
		// "/processes"; // DELETE";
		// //// System.out.println("\n\n################");
		// //// System.out.println("calling "+serviceURL+"\n DELETE");
		// //// System.out.println("################");
		// //// r = c.resource(serviceURL);
		// //// r.delete(ClientResponse.class);
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/processes"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // lop =
		// r.type(MediaType.APPLICATION_XML).get(ListOfProcesses.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(lop));
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/building"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // BuildingExport be =
		// r.type(MediaType.APPLICATION_XML).get(BuildingExport.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(be));
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/importbuilding"; // PUT";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n PUT");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // filepath = root+"/test/xml/BuildingImport.xml";
		// // System.out.println("With XML: "+filepath+"\n");
		// // System.out.println("################");
		// // resp = r.type(MediaType.APPLICATION_XML)
		// // .put(String.class, new File(filepath));
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/building"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // be = r.type(MediaType.APPLICATION_XML).get(BuildingExport.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(be));
		// //
		// //
		// //// serviceURL = serverURL + "/measurementfile/" + param +
		// "/building";// DELETE";
		// //// System.out.println("\n\n################");
		// //// System.out.println("calling "+serviceURL+"\n DELETE");
		// //// System.out.println("################");
		// //// r = c.resource(serviceURL);
		// //// r.delete(ClientResponse.class);
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/building"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // be = r.type(MediaType.APPLICATION_XML).get(BuildingExport.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(be));
		// //
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param + "/sensors";
		// // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // ListOfSensors los =
		// r.type(MediaType.APPLICATION_XML).get(ListOfSensors.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(los));
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/sensordiscovered"; // PUT";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n PUT");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // filepath = root+"/test/xml/DeviceDiscovered.xml";
		// // System.out.println("With XML: "+filepath);
		// // System.out.println("################");
		// // resp = r.type(MediaType.APPLICATION_XML)
		// // .put(String.class, new File(filepath));
		// // System.out.println("response: "+resp);
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param + "/sensors";
		// // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // los = r.type(MediaType.APPLICATION_XML).get(ListOfSensors.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(los));
		// //
		// //// serviceURL = serverURL + "/measurementfile/" + param +
		// "/sensors"; // DELETE";
		// //// System.out.println("\n\n################");
		// //// System.out.println("calling "+serviceURL+"\n DELETE");
		// //// System.out.println("################");
		// //// r = c.resource(serviceURL);
		// //// r.delete(ClientResponse.class);
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param + "/sensors";
		// // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // los = r.type(MediaType.APPLICATION_XML).get(ListOfSensors.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(los));
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/equipments"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // ListOfEquipments loe =
		// r.type(MediaType.APPLICATION_XML).get(ListOfEquipments.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(loe));
		// //
		// //// serviceURL = serverURL + "/measurementfile/" + param +
		// "/equipments"; // DELETE";
		// //// System.out.println("\n\n################");
		// //// System.out.println("calling "+serviceURL+"\n DELETE");
		// //// System.out.println("################");
		// //// r = c.resource(serviceURL);
		// //// r.delete(ClientResponse.class);
		// //
		// // serviceURL = serverURL + "/measurementfile/" + param +
		// "/equipments"; // GET";
		// // System.out.println("\n\n################");
		// // System.out.println("calling "+serviceURL+"\n GET");
		// // System.out.println("################");
		// // r = c.resource(serviceURL);
		// // loe =
		// r.type(MediaType.APPLICATION_XML).get(ListOfEquipments.class);
		// //
		// System.out.println("result: \n"+StorageUtils.serializeObjectToStringXML(loe));
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		// //
		// //
		// // String createdID = "";
		// //
		// //
		// //
		// // System.out.println("===== Create measurement file ID=id =====");
		// //
		// //
		// //
		// // System.out.println("Resp: " + resp);
		// // createdID = resp;
		// //
		// // System.out.println("===== Create measurement file ID=1 =====");
		// //
		// // filepath = "/test/xml/MeasurementFileID1.xml"; // in case of
		// Windows:
		// // // "\\path
		// // // \\to\\yourfile.txt
		// //
		// //
		// //
		// System.out.println("===== skusime nevalidne XMLko uploadnut =====");
		// // // skusime nevalidne XMLko uploadnut
		// // c = Client.create();
		// // r = c.resource(serverURL + "measurement");
		// // System.out.println("===== Create measurement file =====");
		// // root = System.getProperty("user.dir");
		// // // System.out.println("root: "+root);
		// // filepath = "/test/xml/SimulationFile.xml";
		// // String abspath = root + filepath;
		// // resp =
		// r.type(MediaType.APPLICATION_XML).accept(MediaType.TEXT_XML)
		// // .put(String.class, new File(abspath));
		// // System.out.println("Non valid XML Resp: " + resp);
		// //
		// // c = Client.create();
		// // r = c.resource(serverURL + "measurement/" + createdID +
		// "/importevents");
		// // System.out.println("===== Create eventsResource III =====");
		// //
		// // filepath = "/test/xml/EventsImport.xml"; // in case of Windows:
		// // abspath = root + filepath;
		// //
		// // resp =
		// r.type(MediaType.APPLICATION_XML).accept(MediaType.TEXT_XML)
		// // .put(String.class, new File(abspath));
		// // System.out.println("Resp: " + resp);
		// //
		// // c = Client.create();
		// // r = c.resource(serverURL + "measurement/" + createdID
		// // + "/importprocess");
		// // System.out.println("===== Create processResource =====");
		// // // System.out.println("root: "+root);
		// // filepath = "/test/xml/BPMImport.xml"; // in case of Windows:
		// "\\path
		// // // \\to\\yourfile.txt
		// // abspath = root + filepath;
		// //
		// // resp =
		// r.type(MediaType.APPLICATION_XML).accept(MediaType.TEXT_XML)
		// // .put(String.class, new File(abspath));
		// // System.out.println("Resp: " + resp);
		// //
		// // System.out.println("===== Create processResource II =====");
		// // // System.out.println("root: "+root);
		// // filepath = "/test/xml/BP_UNAV_Meal_Delivery_xml.xml"; // in case
		// of
		// // // Windows:
		// // // "\\path
		// // // \\to\\yourfile.txt
		// // abspath = root + filepath;
		// //
		// // resp =
		// r.type(MediaType.APPLICATION_XML).accept(MediaType.TEXT_XML)
		// // .put(String.class, new File(abspath));
		// // System.out.println("Resp: " + resp);
		//
		// // System.out.println("===== Create eventsResource =====");
		// // Events evs=new Events();
		// // evs.setId("fromClient");
		// // evs.setName("name2");
		// // List<Event> evl = new LinkedList<Event>();
		// // evl.add(new Event("daco","nieco"));
		// // evl.add(new Event("nieco", "vselico"));
		// // evs.setEvents(evl);
		// // ClientResponse response =
		// // r.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, c);
		// // System.out.println(response.getStatus());
		// //
		// // System.out.println("===== Create eventsResource II =====");
		// // EventsImport evim = new EventsImport();
		// // TEvent ev1 = new TEvent();
		// // TEvent ev2 = new TEvent();
		// // List<TEvent> list = evim.getEvent();
		// // list.add(ev1);
		// // list.add(ev2);
		// // TEquipmentUsedEvent tequ = new TEquipmentUsedEvent();
		// // tequ.setEquipmentRef("equipmentID");
		// // ev1.setEquipmentUsed(tequ);
		// // TSpaceEnvironmentEvent sen = new TSpaceEnvironmentEvent();
		// // ev1.setSpaceEnvironmentChange(sen);
		//
		// // ClientResponse responseIII =
		// // r.accept(MediaType.APPLICATION_XML).put(ClientResponse.class,
		// evim);
		// // System.out.println(responseIII.getStatus());

	}

}
