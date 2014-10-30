package eu.adapt4ee.cimim.rest.storage;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.cxf.helpers.IOUtils;
import org.basex.core.BaseXException;
import org.basex.core.Command;
import org.basex.core.Context;
import org.basex.core.cmd.Check;
import org.basex.core.cmd.Close;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.Delete;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.Open;
import org.basex.core.cmd.Replace;
import org.basex.core.cmd.XQuery;
import org.w3c.dom.Element;

import eu.adapt4ee._2012.schema.cim.BPMImport;
import eu.adapt4ee._2012.schema.cim.BuildingExport;
import eu.adapt4ee._2012.schema.cim.BuildingImport;
import eu.adapt4ee._2012.schema.cim.DeviceDiscovered;
import eu.adapt4ee._2012.schema.cim.EventTraceImport;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.EventsStats;
import eu.adapt4ee._2012.schema.cim.ExtensionFile;
import eu.adapt4ee._2012.schema.cim.Extensions;
import eu.adapt4ee._2012.schema.cim.ListOfEquipments;
import eu.adapt4ee._2012.schema.cim.ListOfHVACs;
import eu.adapt4ee._2012.schema.cim.ListOfLightings;
import eu.adapt4ee._2012.schema.cim.ListOfProcesses;
import eu.adapt4ee._2012.schema.cim.ListOfRoles;
import eu.adapt4ee._2012.schema.cim.ListOfSensors;
import eu.adapt4ee._2012.schema.cim.ListOfZones;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.cim.MeasurementsImport;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee._2012.schema.cim.Properties;
import eu.adapt4ee._2012.schema.cim.SimulationFile;
import eu.adapt4ee._2012.schema.device.SensorType;
import eu.adapt4ee.cimim.rest.util.xml.UtilFactory;

/**
 * @author Jan Hreno Not used currently, the non server version is implemented and
 *         some methods need to be implemented here to be fully compatible. See
 *         the TODO comments
 */

public class StorageFactoryImplementationXbase implements StorageFactory {

	public static final String DATABASE_NAME = "Adapt4EE";

	protected static final Logger log = Logger
			.getLogger(StorageFactoryImplementationXbase.class.getName());

	protected static final String NAMESPACE = "http://www.adapt4ee.eu/2012/schema/cim/";
	protected static final String NAMESPACEDEVICE = "http://www.adapt4ee.eu/2012/schema/device/";
	public static final String NAMESPACE_DECLARATION = "declare namespace ns2=\""
			+ NAMESPACE + "\";";
	protected static final String NAMESPACEDEVICE_DECLARATION = "declare namespace ns2=\""
			+ NAMESPACEDEVICE + "\";";

	protected static final String SENSOR_TYPES_COLLECION_NAME = "SensorTypes";
	protected static final String EXTENSIONS_COLLECION_NAME = "Extensions";

	protected Context context = new Context();
	protected static StorageFactoryImplementationXbase instance;

	private StorageFactoryImplementationXbase(boolean createDatabase) {
		if (createDatabase) {
			executeCommand(new CreateDB(DATABASE_NAME));
		} else {
			executeCommand(new Check(DATABASE_NAME));
		}
	}

	public synchronized static StorageFactory getInstance() {
		if (instance == null) {
			instance = new StorageFactoryImplementationXbase(false);
		}
		return instance;
	}

	public static StorageFactory getInstance(boolean createDatabase) {
		if (instance != null) {
			instance.close();
		}
		instance = new StorageFactoryImplementationXbase(createDatabase);
		return instance;
	}

	public Context getContext() {
		return context;
	}

	public synchronized void close() {
		executeCommand(new Close());
	}

	// @Override
	// public synchronized MeasurementFile getMeasurementFileByID(String
	// measurementID) {
	// XQuery cmd = new XQuery("collection(\"" + DATABASE_NAME + "/" +
	// measurementID + "\")");
	// ByteArrayOutputStream os = new ByteArrayOutputStream();
	// executeCommand(cmd, os);
	// return StorageUtils.unserializeDocument(os, MeasurementFile.class);
	// }

	@Override
	public void getMeasurementFileByID(String measurementID, OutputStream output) {
		String q = NAMESPACE_DECLARATION + "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]";
		executeCommand(new XQuery(q), output);
	}

	@Override
	public void saveMeasurementFile(InputStream m, String id) {
		if (id == null)
			id = UUID.randomUUID().toString();

		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + id + "\"]";
		executeCommand(new XQuery(q));

		Replace cmd = new Replace(id);
		cmd.setInput(m);
		executeCommand(cmd);

	}

	@Override
	public String saveMeasurementFileLowMem(InputStream m)
			throws XMLStreamException {

		int count = 0;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		String measurementID = "";
		XMLStreamReader streamReader = factory.createXMLStreamReader(m);

		int i = 0;

		String noEventsData = "";

		while (streamReader.hasNext()) {
			streamReader.next();
			if (streamReader.isStartElement()) {

				// get ID
				if (streamReader.getLocalName() == "id" && i == 0) {
					// only first ID
					// System.out.println("######parsing ID#######");
					i = 1;
					while (!(streamReader.isEndElement() && streamReader
							.getLocalName() == "id")) {
						streamReader.next();
						if (streamReader.isCharacters()) {
							measurementID = measurementID.concat(streamReader
									.getText());
						}
					}
					// System.out.println("###########data teraz:");
					// System.out.println(noEventsData);
					noEventsData = noEventsData.concat("<id>" + measurementID
							+ "</id>");
					// System.out.println("###########data teraz:");
					// System.out.println(noEventsData);

					// remove existing measurement file with the same ID
					removeMeasurementFile(measurementID);

					MeasurementFile mf = new ObjectFactory()
							.createMeasurementFile();

					mf.setId(measurementID);
					Replace cmd = new Replace(mf.getId());
					cmd.setInput(UtilFactory.serializeDocument(mf));
					executeCommand(cmd);

				} else

				// parse Events
				if (streamReader.getLocalName() == "events") {
					System.out.println("######parsing EVENTS#######");

					int bunchCount = 0;
					String eventBunch = "";

					while (streamReader.hasNext()
							&& !(streamReader.isEndElement() && streamReader
									.getLocalName() == "events")) {
						streamReader.next();

						String singleEvent = "";

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
											+ streamReader.getAttributeValue(y)
													.replaceAll("&", "&amp;")
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

						// System.out.println("##########event#########for ID:"+measurementID);
						// System.out.println(singleEvent);

						if (bunchCount++ < 10000) {
							eventBunch = eventBunch.concat(singleEvent);
						} else {
							eventBunch = eventBunch.concat(singleEvent);
							bunchCount = 0;
							insertIfNotExist("/ns2:MeasurementFile[id=\""
									+ measurementID + "\"]/events",
									"/ns2:MeasurementFile[id=\""
											+ measurementID + "\"]",
									"<events></events>");
							String q = NAMESPACE_DECLARATION
									+ "declare variable $c:=<events>"
									+ eventBunch
									+ "</events>;"
									+ "insert nodes $c/event into /ns2:MeasurementFile[id=\""
									+ measurementID + "\"]/events";
							executeCommand(new XQuery(q));
							eventBunch = "";
							System.out.println(++count * 10000
									+ " events added");
						}

					}
					{

						insertIfNotExist("/ns2:MeasurementFile[id=\""
								+ measurementID + "\"]/events",
								"/ns2:MeasurementFile[id=\"" + measurementID
										+ "\"]", "<events></events>");
						String q = NAMESPACE_DECLARATION
								+ "declare variable $c:=<events>"
								+ eventBunch
								+ "</events>;"
								+ "insert nodes $c/event into /ns2:MeasurementFile[id=\""
								+ measurementID + "\"]/events";
						executeCommand(new XQuery(q));
						eventBunch = "";
						System.out.println(count * 10000 + bunchCount
								+ " events added");
						bunchCount = 0;
					}

					// System.out.println("###########data teraz:");
					// System.out.println(noEventsData);
					noEventsData = noEventsData.concat("<events/>");
					// System.out.println("###########data teraz:");
					// System.out.println(noEventsData);
				} else {
					// System.out.println("######parsing Element Start#######");
					String s = "";

					s = s.concat("<"
							+ (streamReader.getPrefix().equalsIgnoreCase("") ? ""
									: streamReader.getPrefix() + ":")
							+ streamReader.getLocalName());
					int x = streamReader.getNamespaceCount();
					int y = streamReader.getAttributeCount();
					while (--x > -1) {

						s = s.concat(" xmlns:"
								+ streamReader.getNamespacePrefix(x) + "=\""
								+ streamReader.getNamespaceURI(x) + "\"");
					}

					while (--y > -1) {
						s = s.concat(" "
								+ (streamReader.getAttributePrefix(y)
										.equalsIgnoreCase("") ? ""
										: streamReader.getAttributePrefix(y)
												+ ":")
								+ streamReader.getAttributeLocalName(y) + "=\""
								+ streamReader.getAttributeValue(y) + "\"");
					}

					s = s.concat(">");

					// System.out.println("start:" + s);
					// System.out.println("startElement element attribute count:"+streamReader.getAttributeCount());
					noEventsData = noEventsData.concat(s);

				}

			} else if (streamReader.isWhiteSpace()) {
				// System.out.println("######parsing whitespace#######");
			} else if (streamReader.isCharacters()) {
				// System.out.println("######parsing Character#######");
				String s = (streamReader.getText());
				// System.out.println(s);
				noEventsData = noEventsData.concat(s);
			} else if (streamReader.isEndElement()) {
				// System.out.println("######parsing End Element#######");
				String s = ("</"
						+ (streamReader.getPrefix().equalsIgnoreCase("") ? ""
								: streamReader.getPrefix() + ":")
						+ streamReader.getLocalName() + ">");
				// System.out.println(s);
				noEventsData = noEventsData.concat(s);
			}

		}

		// System.out.println("###########data teraz:");
		// System.out.println(noEventsData);

		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/processes", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<processes></processes>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/measurements", "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]", "<measurements></measurements>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/roles", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<roles></roles>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/gbXML", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<gbXML></gbXML>");
		// System.out.println("############## noEventsDataFinal");
		// System.out.println(noEventsData);

		String q = NAMESPACE_DECLARATION
				+ "declare variable $c:="
				+ noEventsData
				+ ";"
				+ "insert nodes $c/processes/process into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/processes";
		// System.out.println("############## query");
		// System.out.println(q);

		executeCommand(new XQuery(q));

		q = NAMESPACE_DECLARATION
				+ "declare variable $c:="
				+ noEventsData
				+ ";"
				+ "insert nodes $c/measurements/measurement into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/measurements";
		executeCommand(new XQuery(q));

		q = NAMESPACE_DECLARATION + "declare variable $c:=" + noEventsData
				+ ";"
				+ "insert nodes $c/roles/role into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/roles";
		executeCommand(new XQuery(q));

		q = NAMESPACE_DECLARATION + "delete node /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building";
		executeCommand(new XQuery(q));

		q = NAMESPACE_DECLARATION + "declare variable $c:=" + noEventsData
				+ ";"
				+ "insert node $c/building into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]";
		executeCommand(new XQuery(q));

		q = NAMESPACE_DECLARATION + "delete node /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/gbXML";
		executeCommand(new XQuery(q));

		q = NAMESPACE_DECLARATION + "declare variable $c:=" + noEventsData
				+ ";" + "insert node $c/gbXML into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]";
		executeCommand(new XQuery(q));
		streamReader.close();
		return measurementID;

	}

	@Override
	public String saveMeasurementFileNoStream(MeasurementFile mf) {

		if (mf.getId() == null)
			mf.setId(UUID.randomUUID().toString());
		Replace cmd = new Replace(mf.getId());
		cmd.setInput(UtilFactory.serializeDocument(mf));
		executeCommand(cmd);
		return mf.getId();

	}

	@Override
	public void removeMeasurementFile(String measurementID) {

		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]";
		executeCommand(new XQuery(q));
	}

	@Override
	public void getMeasurementFiles(OutputStream os) {
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfMeasurementFileIDs>{/ns2:MeasurementFile/id}</ns2:ListOfMeasurementFileIDs>";

		executeCommand(new XQuery(q), os);

	}

	@Override
	public void getMeasurementFileByIDWithoutBigData(String measurementFileID,
			OutputStream os) {
		String q = NAMESPACE_DECLARATION + "<ns2:MeasurementFile><id>"
				+ measurementFileID + "</id><events/><measurements/>"
				+ "{/ns2:MeasurementFile[id=\"" + measurementFileID
				+ "\"]/processes}" + "{/ns2:MeasurementFile[id=\""
				+ measurementFileID + "\"]/roles}"
				+ "{/ns2:MeasurementFile[id=\"" + measurementFileID
				+ "\"]/building}" + "{/ns2:MeasurementFile[id=\""
				+ measurementFileID + "\"]/gbXML} </ns2:MeasurementFile>";

		executeCommand(new XQuery(q), os);

	}

	@Override
	public void getMeasurementFileByIDFilteredByTime(String measurementFileID,
			String startTime, String endTime, OutputStream output) {
		String q = NAMESPACE_DECLARATION
				+

				"let $min :=xs:dateTime('"
				+ startTime
				+ "') let $max :=xs:dateTime('"
				+ endTime
				+ "') "
				+ "return 	"
				+ "<ns2:MeasurementFile><id>imported</id>"
				+ "<events>"
				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[OccupantTrajectory/occupantTrajectory/timeStamp>$min and OccupantTrajectory/occupantTrajectory/timeStamp<$max]}"

				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[OccupantTrajectory/a/@t>$min and OccupantTrajectory/a/@t<$max]}"

				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[occupantMoved/time>$min and occupantMoved/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[equipmentUsed/time>$min and equipmentUsed/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[spaceEnvironmentChange/time>$min and spaceEnvironmentChange/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[externalEnvironmentChanged/time>$min and externalEnvironmentChanged/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[HVACChange/time>$min and HVACChange/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[LightingChange/time>$min and LightingChange/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[SpaceOccupancy/time>$min and SpaceOccupancy/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[SpaceMovement/time>$min and SpaceMovement/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\""
				+ measurementFileID
				+ "\"]/events/event[SpaceOccupantPossitions/time>$min and SpaceOccupantPossitions/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\"" + measurementFileID
				+ "\"]/events/event[Current/time>$min and Current/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\"" + measurementFileID
				+ "\"]/events/event[Voltage/time>$min and Voltage/time<$max]}"
				+ "{/ns2:MeasurementFile[id=\"" + measurementFileID
				+ "\"]/events/event[Energy/time>$min and Energy/time<$max]}"
				+ "</events><measurements/>" + "{/ns2:MeasurementFile[id=\""
				+ measurementFileID + "\"]/processes}"
				+ "{/ns2:MeasurementFile[id=\"" + measurementFileID
				+ "\"]/roles}" + "{/ns2:MeasurementFile[id=\""
				+ measurementFileID + "\"]/building}"
				+ "{/ns2:MeasurementFile[id=\"" + measurementFileID
				+ "\"]/gbXML} </ns2:MeasurementFile>";

		executeCommand(new XQuery(q), output);

	}

	@Override
	public void addEvents(EventsImport ei, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/events", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<events></events>");
		String events = StorageUtils.serializeObjectToStringXML(ei, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + events
				+ ";" + "insert nodes $c/event into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/events";
		executeCommand(new XQuery(q));
	}

	@Override
	public void removeEvents(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/events/event";
		executeCommand(new XQuery(q));
	}

	@Override
	public void saveBuilding(BuildingImport b, String measurementID) {
		String building = StorageUtils.serializeObjectToStringXML(b, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + building
				+ ";" + "replace node /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building with $c/building";
		executeCommand(new XQuery(q));
	}

	@Override
	public void removeBuilding(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building";
		executeCommand(new XQuery(q));
	}

	@Override
	public void addProcess(BPMImport b, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/processes", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<processes></processes>");

		String process = StorageUtils.serializeObjectToStringXML(b, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + process
				+ ";"
				+ "insert nodes $c/process into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/processes";
		executeCommand(new XQuery(q));
	}

	@Override
	public void removeProcesses(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/processes/process";
		executeCommand(new XQuery(q));
	}

	@Override
	public void addDevice(DeviceDiscovered d, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/sensors", "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building", "<sensors></sensors>");

		String deviceDisco = StorageUtils.serializeObjectToStringXML(d, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:="
				+ deviceDisco + ";"
				+ "insert nodes $c/sensor into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building/sensors";
		executeCommand(new XQuery(q));
	}

	@Override
	public void addDevices(ListOfSensors d, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/sensors", "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building", "<sensors></sensors>");
		String deviceDisco = StorageUtils.serializeObjectToStringXML(d, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:="
				+ deviceDisco + ";"
				+ "insert nodes $c/sensor into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building/sensors";
		executeCommand(new XQuery(q));
	}

	@Override
	public void removeSensors(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/sensors/sensor";
		executeCommand(new XQuery(q));
	}

	@Override
	public ListOfSensors getSensorsByMeasurementFileID(String measurementID) {
		ListOfSensors los = new ObjectFactory().createListOfSensors();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfSensors> {/ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/building/sensors/sensor} </ns2:ListOfSensors> ";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			los = StorageUtils.unserializeDocument(os, ListOfSensors.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return los;
	}

	@Override
	public ListOfSensors getSensorsOfTypeByMeasurementFileID(
			String measurementID, String sensorType) {
		ListOfSensors los = new ObjectFactory().createListOfSensors();
		String q;
		if ("other".equalsIgnoreCase(sensorType)) {
			q = NAMESPACE_DECLARATION
					+ " <ns2:ListOfSensors> {for $x in /ns2:MeasurementFile[id=\""
					+ measurementID
					+ "\"]/building/sensors/sensor "
					+ "where $x/sensorTypeRef!=\"Kinect\" and $x/sensorTypeRef!=\"RFID\" return  $x } </ns2:ListOfSensors>";
		} else {
			q = NAMESPACE_DECLARATION
					+ " <ns2:ListOfSensors> {for $x in /ns2:MeasurementFile[id=\""
					+ measurementID + "\"]/building/sensors/sensor "
					+ "where $x/sensorTypeRef=\"" + sensorType
					+ "\" return  $x } </ns2:ListOfSensors>";
		}

		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			los = StorageUtils.unserializeDocument(os, ListOfSensors.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return los;
	}

	// @Override
	// public synchronized MeasurementsImport
	// getMeasurementsInMeasurementFile(String measurementID) {
	// MeasurementsImport mim = new ObjectFactory().createMeasurementsImport();
	// String q = NAMESPACE_DECLARATION +
	// "<ns2:MeasurementsImport> {/ns2:MeasurementFile[id=\"" + measurementID +
	// "\"]/measurements/measurement} </ns2:MeasurementsImport>";
	// try {
	// ByteArrayOutputStream os = new ByteArrayOutputStream();
	// executeCommand(new XQuery(q), os);
	// mim = StorageUtils.unserializeDocument(os, MeasurementsImport.class);
	// } catch (Exception e) {
	// log.log(Level.SEVERE, e.getMessage(), e);
	// }
	//
	// return mim;
	// }

	@Override
	public void getMeasurementsInMeasurementFile(String measurementID,
			final OutputStream output) {
		// String q = NAMESPACE_DECLARATION
		// + "<ns2:MeasurementsImport> {/ns2:MeasurementFile[id=\""
		// + measurementID
		// + "\"]/measurements/measurement} </ns2:MeasurementsImport>";
		// executeCommand(new XQuery(q), output);

		String q = NAMESPACE_DECLARATION + "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/measurements/measurement";
		try {
			output.write("<ns2:MeasurementsImport xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
					.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executeCommand(new XQuery(q), output);
		try {
			output.write("</ns2:MeasurementsImport>".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Override
	// public synchronized ListOfEvents getEventsFromMeasurementFile(String
	// measurementID) {
	// ListOfEvents loe = new ObjectFactory().createListOfEvents();
	// String q = NAMESPACE_DECLARATION +
	// "<ns2:ListOfEvents> {/ns2:MeasurementFile[id=\"" + measurementID +
	// "\"]/events/event} </ns2:ListOfEvents>";
	// try {
	// ByteArrayOutputStream os = new ByteArrayOutputStream();
	// executeCommand(new XQuery(q), os);
	// loe = StorageUtils.unserializeDocument(os, ListOfEvents.class);
	// } catch (Exception e) {
	// log.log(Level.SEVERE, e.getMessage(), e);
	// }
	//
	// return loe;
	// }

	@Override
	public void getEventsFromMeasurementFile(String measurementID,
			OutputStream output) {
		// String q = NAMESPACE_DECLARATION
		// + "<ns2:ListOfEvents> {/ns2:MeasurementFile[id=\""
		// + measurementID + "\"]/events/event} </ns2:ListOfEvents>";

		String q = NAMESPACE_DECLARATION + "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/events/event";
		try {
			output.write("<ns2:ListOfEvents xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
					.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executeCommand(new XQuery(q), output);
		try {
			output.write("</ns2:ListOfEvents>".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ListOfProcesses getProcessesInMeasurementFile(String measurementID) {
		ListOfProcesses lop = new ObjectFactory().createListOfProcesses();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfProcesses> {/ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/processes/process} </ns2:ListOfProcesses>";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			lop = StorageUtils.unserializeDocument(os, ListOfProcesses.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return lop;
	}

	@Override
	public ListOfEquipments getEquipmentsInMeasurementFile(String measurementID) {
		ListOfEquipments loe = new ObjectFactory().createListOfEquipments();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfEquipments> {/ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/building/space/equipments/EquipmentDevice} </ns2:ListOfEquipments>";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			loe = StorageUtils.unserializeDocument(os, ListOfEquipments.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return loe;
	}

	@Override
	public BuildingExport getBuildingFromMeasurementFile(String measurementID) {
		BuildingExport be = new ObjectFactory().createBuildingExport();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:BuildingExport> {/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building} </ns2:BuildingExport>";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			be = StorageUtils.unserializeDocument(os, BuildingExport.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return be;
	}

	@Override
	public String getGBXML(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ " <ns2:MeasurementFile> {/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/gbXML} </ns2:MeasurementFile>";

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		executeCommand(new XQuery(q), os);
		MeasurementFile mf = new ObjectFactory().createMeasurementFile();
		mf = StorageUtils.unserializeDocument(os, MeasurementFile.class);
		Element el = (Element) mf.getGbXML();
		if (el == null)
			return "";
		else {
			String res = el.getTextContent();
			res = res.replace(".openBR.", "<");
			res = res.replace(".closeBR.", ">");
			return res;
		}

	}

	@Override
	public void getGbXMLNotFormatted(String measurementID, OutputStream os) {
		String q = NAMESPACE_DECLARATION + "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/gbXML/node()";

		executeCommand(new XQuery(q), os);

	}

	@Override
	public void addMeasurements(MeasurementsImport mi, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/measurements", "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]", "<measurements></measurements>");
		String measurementsImp = StorageUtils.serializeObjectToStringXML(mi,
				true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:="
				+ measurementsImp + ";"
				+ "insert nodes $c/measurement into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/measurements";
		executeCommand(new XQuery(q));
	}

	@Override
	public void removeMeasurements(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/measurements/measurement";
		executeCommand(new XQuery(q));
	}

	/*
	 * @Override public synchronized SimulationFile getSimulationFileByID(String
	 * simulationID) { XQuery cmd = new XQuery("collection(\"" + DATABASE_NAME +
	 * "/" + simulationID + "\")"); ByteArrayOutputStream os = new
	 * ByteArrayOutputStream(); executeCommand(cmd, os); return
	 * StorageUtils.unserializeDocument( new
	 * ByteArrayInputStream(os.toByteArray()), SimulationFile.class); }
	 * 
	 * @Override public synchronized String saveSimulationFile(SimulationFile m)
	 * { String simulationID = m.getId(); if (simulationID == null) {
	 * simulationID = UUID.randomUUID().toString(); } Replace cmd = new
	 * Replace(simulationID); cmd.setInput(StorageUtils.serializeDocument(m));
	 * executeCommand(cmd); return simulationID; }
	 * 
	 * @Override public synchronized void removeSimulationFile(String
	 * simulationID) { executeCommand(new Delete(simulationID)); }
	 */

	@Override
	public void saveSensorTypes(InputStream st) {
		Replace cmd = new Replace(SENSOR_TYPES_COLLECION_NAME);
		cmd.setInput(st);
		executeCommand(cmd);
	}

	@Override
	public SensorType getSensorTypes() {
		XQuery cmd = new XQuery("collection(\"" + DATABASE_NAME + "/"
				+ SENSOR_TYPES_COLLECION_NAME + "\")");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		executeCommand(cmd, os);
		return StorageUtils.unserializeDocument(os, SensorType.class);
	}

	@Override
	public void removeSensorTypes() {
		executeCommand(new Delete(SENSOR_TYPES_COLLECION_NAME));
	}

	@Override
	public void removeZones(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/zones/zone";
		executeCommand(new XQuery(q));
	}

	@Override
	public void addZones(ListOfZones loz, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/zones", "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building", "<zones></zones>");

		String zones = StorageUtils.serializeObjectToStringXML(loz, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + zones
				+ ";" + "insert nodes $c/zone into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building/zones";
		executeCommand(new XQuery(q));
	}

	@Override
	public ListOfZones getZones(String measurementID) {
		ListOfZones loz = new ObjectFactory().createListOfZones();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfZones> {/ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/building/zones/zone} </ns2:ListOfZones> ";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			loz = StorageUtils.unserializeDocument(os, ListOfZones.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return loz;
	}

	@Override
	public void addEquipments(ListOfEquipments ei, String measurementID,
			String spaceID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID + "\"]",
				"/ns2:MeasurementFile[id=\"" + measurementID + "\"]/building",
				"<space><id>" + spaceID + "</id></space>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID + "\"]/equipments",
				"/ns2:MeasurementFile[id=\"" + measurementID
						+ "\"]/building/space[id=\"" + spaceID + "\"]",
				"<equipments></equipments>");
		String equipments = StorageUtils.serializeObjectToStringXML(ei, true);
		String q = NAMESPACE_DECLARATION
				+ "declare variable $c:="
				+ equipments
				+ ";"
				+ "insert nodes $c/EquipmentDevice into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building/space[id=\"" + spaceID
				+ "\"]/equipments";
		executeCommand(new XQuery(q));
	}

	@Override
	public void replaceEquipments(ListOfEquipments ei, String measurementID,
			String spaceID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID + "\"]",
				"/ns2:MeasurementFile[id=\"" + measurementID + "\"]/building",
				"<space><id>" + spaceID + "</id></space>");

		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID + "\"]/equipments",
				"/ns2:MeasurementFile[id=\"" + measurementID
						+ "\"]/building/space[id=\"" + spaceID + "\"]",
				"<equipments></equipments>");

		String equipments = StorageUtils.serializeObjectToStringXML(ei, true);
		String q = NAMESPACE_DECLARATION
				+ "declare variable $c:="
				+ equipments
				+ ";"
				+ "replace node /ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/building/space[id=\""
				+ spaceID
				+ "\"]/equipments with <equipments>{$c/EquipmentDevice}</equipments>";
		executeCommand(new XQuery(q));
	}

	@Override
	public ListOfEquipments getEquipments(String measurementID, String spaceID) {
		ListOfEquipments loe = new ObjectFactory().createListOfEquipments();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfEquipments> {/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building/space[id=\"" + spaceID
				+ "\"]/equipments/EquipmentDevice} </ns2:ListOfEquipments>";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			loe = StorageUtils.unserializeDocument(os, ListOfEquipments.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return loe;
	}

	@Override
	public void removeEquipments(String measurementID, String spaceID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID
				+ "\"]/equipments/EquipmentDevice";
		executeCommand(new XQuery(q));
	}

	@Override
	public void removeEquipments(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space/equipments/EquipmentDevice";
		executeCommand(new XQuery(q));
	}

	@Override
	public void addRoles(ListOfRoles lor, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/roles", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<roles></roles>");
		String roles = StorageUtils.serializeObjectToStringXML(lor, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + roles
				+ ";" + "insert nodes $c/role into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/roles";
		executeCommand(new XQuery(q));
	}

	@Override
	public void replaceRoles(ListOfRoles lor, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/roles", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<roles></roles>");
		String roles = StorageUtils.serializeObjectToStringXML(lor, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + roles
				+ ";" + "replace node /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/roles with <roles>{$c/role}</roles>";
		executeCommand(new XQuery(q));
	}

	@Override
	public ListOfRoles getRoles(String measurementID) {
		ListOfRoles lor = new ObjectFactory().createListOfRoles();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfRoles> {/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/roles/role} </ns2:ListOfRoles> ";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			lor = StorageUtils.unserializeDocument(os, ListOfRoles.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return lor;
	}

	@Override
	public void removeRoles(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/roles";
		executeCommand(new XQuery(q));

	}

	// ######### HVAC

	@Override
	public void addHVACs(ListOfHVACs ei, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");

		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/HVACs", "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building", "<HVACs></HVACs>");
		String HVACs = StorageUtils.serializeObjectToStringXML(ei, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + HVACs
				+ ";"
				+ "insert nodes $c/HVACDevice into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building/HVACs";
		executeCommand(new XQuery(q));
	}

	@Override
	public void replaceHVACs(ListOfHVACs ei, String measurementID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");

		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/HVACs", "/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building", "<HVACs></HVACs>");

		String HVACs = StorageUtils.serializeObjectToStringXML(ei, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + HVACs
				+ ";" + "replace node /ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/building/HVACs with <HVACs>{$c/HVACDevice}</HVACs>";
		executeCommand(new XQuery(q));
	}

	@Override
	public ListOfHVACs getHVACs(String measurementID, String spaceID) {
		ListOfHVACs loe = new ObjectFactory().createListOfHVACs();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfHVACs> {/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building/HVACs/HVACDevice[spaceRef=\""
				+ spaceID + "\"]} </ns2:ListOfHVACs>";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			loe = StorageUtils.unserializeDocument(os, ListOfHVACs.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return loe;
	}

	@Override
	public ListOfHVACs getHVACs(String measurementID) {
		ListOfHVACs loe = new ObjectFactory().createListOfHVACs();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfHVACs> {/ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/building/HVACs/HVACDevice} </ns2:ListOfHVACs>";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			loe = StorageUtils.unserializeDocument(os, ListOfHVACs.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return loe;
	}

	@Override
	public void removeHVACs(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/HVACs";
		executeCommand(new XQuery(q));
	}

	// ######### Lighting

	@Override
	public void addLightings(ListOfLightings ei, String measurementID,
			String spaceID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID + "\"]",
				"/ns2:MeasurementFile[id=\"" + measurementID + "\"]/building",
				"<space><id>" + spaceID + "</id></space>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID + "\"]/lightings",
				"/ns2:MeasurementFile[id=\"" + measurementID
						+ "\"]/building/space[id=\"" + spaceID + "\"]",
				"<lightings></lightings>");
		String lightings = StorageUtils.serializeObjectToStringXML(ei, true);
		String q = NAMESPACE_DECLARATION
				+ "declare variable $c:="
				+ lightings
				+ ";"
				+ "insert nodes $c/LightingDevice into /ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building/space[id=\"" + spaceID
				+ "\"]/lightings";
		executeCommand(new XQuery(q));
	}

	@Override
	public void replaceLightings(ListOfLightings ei, String measurementID,
			String spaceID) {
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building", "/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]", "<building></building>");
		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID + "\"]",
				"/ns2:MeasurementFile[id=\"" + measurementID + "\"]/building",
				"<space><id>" + spaceID + "</id></space>");

		insertIfNotExist("/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID + "\"]/lightings",
				"/ns2:MeasurementFile[id=\"" + measurementID
						+ "\"]/building/space[id=\"" + spaceID + "\"]",
				"<lightings></lightings>");

		String lightings = StorageUtils.serializeObjectToStringXML(ei, true);
		String q = NAMESPACE_DECLARATION
				+ "declare variable $c:="
				+ lightings
				+ ";"
				+ "replace node /ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/building/space[id=\""
				+ spaceID
				+ "\"]/lightings with <lightings>{$c/LightingDevice}</lightings>";
		executeCommand(new XQuery(q));
	}

	@Override
	public ListOfLightings getLightings(String measurementID, String spaceID) {
		ListOfLightings loe = new ObjectFactory().createListOfLightings();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfLightings> {/ns2:MeasurementFile[id=\""
				+ measurementID + "\"]/building/space[id=\"" + spaceID
				+ "\"]/lightings/LightingDevice} </ns2:ListOfLightings>";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			loe = StorageUtils.unserializeDocument(os, ListOfLightings.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return loe;
	}

	@Override
	public ListOfLightings getLightings(String measurementID) {
		ListOfLightings loe = new ObjectFactory().createListOfLightings();
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfLightings> {/ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/building/space/lightings/LightingDevice} </ns2:ListOfLightings>";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			executeCommand(new XQuery(q), os);
			loe = StorageUtils.unserializeDocument(os, ListOfLightings.class);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		return loe;
	}

	@Override
	public void removeLightings(String measurementID, String spaceID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/building/space[id=\"" + spaceID
				+ "\"]/lightings/LightingDevice";
		executeCommand(new XQuery(q));
	}

	@Override
	public synchronized void dropDB() {
		try {
			executeCommand(new Close());
			executeCommand(new DropDB(DATABASE_NAME));
			executeCommand(new CreateDB(DATABASE_NAME));
			executeCommand(new Open(DATABASE_NAME));
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
	}

	protected void insertIfNotExist(String pathToCheck, String pathOrigin,
			String newElements) {
		String q = NAMESPACE_DECLARATION + "if (exists(" + pathToCheck
				+ ")) then () else insert node " + newElements + " into "
				+ pathOrigin;
		executeCommand(new XQuery(q));
	}

	private void executeCommand(Command command) {
		executeCommand(command, null);
	}

	public void executeCommand(Command command, OutputStream out) {
		try {
			if (out == null) {
				command.execute(context);
			} else {
				command.execute(context, out);
			}
		} catch (BaseXException e) {
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void getMeasurementFileByIDTransformed(String ms,
			OutputStream output, Boolean spaces) throws TransformerException,
			FileNotFoundException {

		URL fileUrl = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation();
		File file = new File(fileUrl.getPath());
		String grandParent = file.getParentFile().getParentFile()
				.getParentFile().getParentFile().getParentFile().getParent();
		System.out.println(grandParent);
		File tempfile = new File(System.getProperty("java.io.tmpdir") + "daco"
				+ System.currentTimeMillis() + ".xml");
		File transformfile = new File(grandParent, spaces ? "mfs.stx"
				: "mfa.stx");

		getMeasurementFileByID(ms, new FileOutputStream(tempfile));

		// use Joost as transformation engine
		System.setProperty("javax.xml.transform.TransformerFactory",
				"net.sf.joost.trax.TransformerFactoryImpl");

		// Create a transform factory instance.
		TransformerFactory tfactory = TransformerFactory.newInstance();
		// Create a transformer for the stylesheet.

		Transformer transformer = tfactory.newTransformer(new StreamSource(
				transformfile));

		transformer.transform(new StreamSource(tempfile), new StreamResult(
				output));
		tempfile.delete();

		// new
		// File("/Users/janci/Develop/adapt4ee/eclipse_juno/workspace/Adapt4EE_CIMIM/test/joost-0.9.1/examples/mf_transformed.xml")));

	}

	@Override
	public void getMeasurementEventsStats(String measurementID,
			boolean includeMinMax, OutputStream os) {
		URL fileUrl = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation();
		File file = new File(fileUrl.getPath());
		String grandParent = file.getParentFile().getParentFile()
				.getParentFile().getParentFile().getParentFile().getParent();
		String namespaceStr = fileToString(new File(grandParent,
				"queryNamespace.xq"));
		String minMaxFunctStr = fileToString(new File(grandParent,
				"queryMinMaxFunctions.xq"));
		String idStr = "let $id:=\"" + measurementID + "\"";
		String eventsStatsStr;
		if (includeMinMax)
			eventsStatsStr = fileToString(new File(grandParent,
					"queryEventStats.xq"));
		else
			eventsStatsStr = fileToString(new File(grandParent,
					"queryEventStatsOnlyCounts.xq"));

		String q = namespaceStr + " " + minMaxFunctStr + " " + idStr + " "
				+ eventsStatsStr;
		executeCommand(new XQuery(q), os);

	}

	private String fileToString(File file) {
		String out = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF8"));

			String str;

			while ((str = in.readLine()) != null) {
				out = out + " " + (str);
			}

			in.close();

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return out;

	}

	@Override
	public void saveSimulationFile(InputStream m, String simID) {

		if (simID == null)
			simID = UUID.randomUUID().toString();
		removeSimulationFile(simID);

		Replace cmd = new Replace(simID);
		cmd.setInput(m);
		executeCommand(cmd);

	}

	@Override
	public void getSimulationFileByID(String simulationID, OutputStream output) {

		String q = NAMESPACE_DECLARATION + "/ns2:SimulationFile[id=\""
				+ simulationID + "\"]";
		executeCommand(new XQuery(q), output);

	}

	@Override
	public String saveSimulationFileNoStream(SimulationFile ms) {
		if (ms.getId() == null)
			ms.setId(UUID.randomUUID().toString());
		Replace cmd = new Replace(ms.getId());
		cmd.setInput(UtilFactory.serializeDocument(ms));
		executeCommand(cmd);
		return ms.getId();
	}

	@Override
	public void addEventTracesMeasurementFile(EventTraceImport ei,
			String simulationID, String simulationCaseID) {

		String events = StorageUtils.serializeObjectToStringXML(ei, true);

		String q = NAMESPACE_DECLARATION
				+ "declare variable $c:="
				+ events
				+ ";"
				+ "insert nodes $c/eventTrace/eventRecord into /ns2:SimulationFile[id=\""
				+ simulationID + "\"]/simulations/simulationCase[id=\""
				+ simulationCaseID + "\"]/simulationResult/eventTrace";
		// System.out.println(q);

		executeCommand(new XQuery(q));

	}

	@Override
	public void getSimulationFiles(OutputStream output) {
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfSimulationFileIDs>{/ns2:SimulationFile/id}</ns2:ListOfSimulationFileIDs>";

		executeCommand(new XQuery(q), output);

	}

	@Override
	public void removeSimulationFile(String simID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node /ns2:SimulationFile[id=\"" + simID + "\"]";
		executeCommand(new XQuery(q));

	}

	@Override
	public void getEventTracesByIDLimited(String simID, String caseID,
			String from, String to, OutputStream output) {
		String q = NAMESPACE_DECLARATION
				+ "let $min:= xs:dateTime('"
				+ from
				+ "')"
				+ "  let $max:= xs:dateTime('"
				+ to
				+ "')"
				+ "return ns2:SimulationFile[id=\""
				+ simID
				+ "\"]/simulations/simulationCase[id=\""
				+ caseID
				+ "\"]/simulationResult/eventTrace/eventRecord[timeStamp<=$max "
				+ "and timeStamp>=$min]";

		try {
			output.write("<ns2:ListOfEventTraces xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
					.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executeCommand(new XQuery(q), output);
		try {
			output.write("</ns2:ListOfEventTraces>".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void getSimulationFileByIDMintime(String ms, String caseID,
			OutputStream output) {

		String q = NAMESPACE_DECLARATION
				+ "<ns2:EventTraceMinTime>{"
				+ "min(ns2:SimulationFile[id=\""
				+ ms
				+ "\"]/simulations/simulationCase[id=\""
				+ caseID
				+ "\"]/simulationResult/eventTrace/eventRecord/timeStamp/xs:dateTime(.))"
				+ "}</ns2:EventTraceMinTime>";
		// System.out.println(q);
		executeCommand(new XQuery(q), output);

	}

	@Override
	public void getSimulationFileByIDMaxtime(String ms, String caseID,
			OutputStream output) {
		String q = NAMESPACE_DECLARATION
				+ "<ns2:EventTraceMaxTime>{"
				+ "max(ns2:SimulationFile[id=\""
				+ ms
				+ "\"]/simulations/simulationCase[id=\""
				+ caseID
				+ "\"]/simulationResult/eventTrace/eventRecord/timeStamp/xs:dateTime(.))"
				+ "}</ns2:EventTraceMaxTime>";
		// System.out.println(q);
		executeCommand(new XQuery(q), output);

	}

	@Override
	public void removeEquipmentUsedEvents(String measurementID) {
		String q = NAMESPACE_DECLARATION
				+ "delete node ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/events/event[equipmentUsed]";
		executeCommand(new XQuery(q));

	}

	@Override
	public void getSimulationFileByIDCount(String ms, String caseID,
			OutputStream output) {
		String q = NAMESPACE_DECLARATION + "<ns2:EventTraceCount>{"
				+ "count(ns2:SimulationFile[id=\"" + ms
				+ "\"]/simulations/simulationCase[id=\"" + caseID
				+ "\"]/simulationResult/eventTrace/eventRecord)"
				+ "}</ns2:EventTraceCount>";
		// System.out.println(q);
		executeCommand(new XQuery(q), output);

	}

	@Override
	public void getEventsOftype(String ms, String type, OutputStream output) {
		String q = NAMESPACE_DECLARATION + "let $id := \"" + ms + "\""
				+ " return" + " ns2:MeasurementFile[id=$id]/events/event["
				+ type + "]";

		try {
			output.write("<ns2:ListOfEvents xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
					.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executeCommand(new XQuery(q), output);
		try {
			output.write("</ns2:ListOfEvents>".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String fixUnits(String measurementID, String unit) {
		String q = NAMESPACE_DECLARATION
				+ "for $node in  ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/events/event/SpaceOccupantPossitions/occupantPosition/possition/*/unit "
				+ " return replace node $node with <unit>" + unit + "</unit>";
		executeCommand(new XQuery(q));
		return "Units Fixed";
	}

	@Override
	public void saveMeasurementFileZip(ZipInputStream inputZIP,
			String measurementID) {

		try {
			for (ZipEntry e; (e = inputZIP.getNextEntry()) != null;) {
				if (e.getName().equals("measurementfile.xml")) {
					Replace cmd = new Replace(measurementID);
					cmd.setInput(inputZIP);
					executeCommand(cmd);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	

	

	@Override
	public void getExtensions(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName, String scope, String application,
			String version, OutputStream output) {

		String q = NAMESPACE_DECLARATION
				+ "ns2:ExtensionFile[id=xs:string(\""
				+ extid
				+ "\")]/extension"
				+ (rootElementName == null ? ""
						: "[@rootElementName/xs:string(.)=xs:string(\""
								+ rootElementName + "\")]")
				+ (referenceID == null ? ""
						: "[@referenceID/xs:string(.)=xs:string(\""
								+ referenceID + "\")]")
				+ (scope == null ? "" : "[@scope/xs:string(.)=xs:string(\""
						+ scope + "\")]")
				+ (application == null ? ""
						: "[@application/xs:string(.)=xs:string(\""
								+ application + "\")]")
				+ (version == null ? "" : "[@version/xs:string(.)=xs:string(\""
						+ version + "\")]")+
						(propertySetName == null ? "" : "[properties/@name/xs:string(.)=xs:string(\""
						+ propertySetName + "\")]")+
						(propertyName == null ? "" : "[properties/property/@name/xs:string(.)=xs:string(\""
						+ propertyName + "\")]");
		
		System.out.println(q);

		try {
			output.write("<ns2:Extensions xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
					.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executeCommand(new XQuery(q), output);
		try {
			output.write("</ns2:Extensions>".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removeExtensions(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName, String scope, String application, String version) {

		String q = NAMESPACE_DECLARATION
				+ "delete node ns2:ExtensionFile[id=xs:string(\""
				+ extid
				+ "\")]/extension"
				+ (rootElementName == null ? ""
						: "[@rootElementName/xs:string(.)=xs:string(\""
								+ rootElementName + "\")]")
				+ (referenceID == null ? ""
						: "[@referenceID/xs:string(.)=xs:string(\""
								+ referenceID + "\")]")
				+ (scope == null ? "" : "[@scope/xs:string(.)=xs:string(\""
						+ scope + "\")]")
				+ (application == null ? ""
						: "[@application/xs:string(.)=xs:string(\""
								+ application + "\")]")
				+ (version == null ? "" : "[@version/xs:string(.)=xs:string(\""
						+ version + "\")]")+
						(propertySetName == null ? "" : "[properties/@name/xs:string(.)=xs:string(\""
						+ propertySetName + "\")]")+
						(propertyName == null ? "" : "[properties/property/@name/xs:string(.)=xs:string(\""
						+ propertyName + "\")]")
						
						;
		
		if ( rootElementName==null &&
				 referenceID==null  && scope==null && application==null && version==null)
			q = NAMESPACE_DECLARATION
					+ "delete node ns2:ExtensionFile[id=xs:string(\""
					+ extid
					+ "\")]";
		System.out.println(q);
		executeCommand(new XQuery(q));

	}

	@Override
	public void getPropertySets(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName, String scope,
			String application, String version, OutputStream output) {
		String q = NAMESPACE_DECLARATION
				+ "ns2:ExtensionFile[id=xs:string(\""
				+ extid
				+ "\")]/extension"
				+ (rootElementName == null ? ""
						: "[@rootElementName/xs:string(.)=xs:string(\""
								+ rootElementName + "\")]")
				+ (referenceID == null ? ""
						: "[@referenceID/xs:string(.)=xs:string(\""
								+ referenceID + "\")]")
				+ (scope == null ? "" : "[@scope/xs:string(.)=xs:string(\""
						+ scope + "\")]")
				+ (application == null ? ""
						: "[@application/xs:string(.)=xs:string(\""
								+ application + "\")]")
				+ (version == null ? "" : "[@version/xs:string(.)=xs:string(\""
						+ version + "\")]")
				+ "/properties" +
				(propertySetName==null?"":"[@name/xs:string(.)=xs:string(\""
				+ propertySetName + "\")]") 
				+
				(propertyName==null?"":"[property/@name/xs:string(.)=xs:string(\""
				+ propertyName + "\")]")
				;
		System.out.println(q);
		try {
			output.write("<ns2:PropertySets xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
					.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executeCommand(new XQuery(q), output);
		try {
			output.write("</ns2:PropertySets>".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removePropSets(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName, String scope,
			String application, String version) {

		String q = NAMESPACE_DECLARATION
				+ "delete node ns2:ExtensionFile[id=xs:string(\""
				+ extid
				+ "\")]/extension"
				+ (rootElementName == null ? ""
						: "[@rootElementName/xs:string(.)=xs:string(\""
								+ rootElementName + "\")]")
				+ (referenceID == null ? ""
						: "[@referenceID/xs:string(.)=xs:string(\""
								+ referenceID + "\")]")
				+ (scope == null ? "" : "[@scope/xs:string(.)=xs:string(\""
						+ scope + "\")]")
				+ (application == null ? ""
						: "[@application/xs:string(.)=xs:string(\""
								+ application + "\")]")
				+ (version == null ? "" : "[@version/xs:string(.)=xs:string(\""
						+ version + "\")]")
				+ "/properties" +
				(propertySetName==null?"":"[@name/xs:string(.)=xs:string(\""
				+ propertySetName + "\")]")+(propertyName==null?"":"[property/@name/xs:string(.)=xs:string(\""
						+ propertyName + "\")]");
		System.out.println(q);
		executeCommand(new XQuery(q));

	}

	@Override
	public void getProperties(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName,
			String scope, String application, String version,
			OutputStream output) {
		String q = NAMESPACE_DECLARATION
				+ "ns2:ExtensionFile[id=xs:string(\""
				+ extid
				+ "\")]/extension"
				+ (rootElementName == null ? ""
						: "[@rootElementName/xs:string(.)=xs:string(\""
								+ rootElementName + "\")]")
				+ (referenceID == null ? ""
						: "[@referenceID/xs:string(.)=xs:string(\""
								+ referenceID + "\")]")
				+ (scope == null ? "" : "[@scope/xs:string(.)=xs:string(\""
						+ scope + "\")]")
				+ (application == null ? ""
						: "[@application/xs:string(.)=xs:string(\""
								+ application + "\")]")
				+ (version == null ? "" : "[@version/xs:string(.)=xs:string(\""
						+ version + "\")]")
				+ "/properties" +
				(propertySetName==null?"":"[@name/xs:string(.)=xs:string(\""
				+ propertySetName + "\")]")
				+ "/property" +
				(propertyName==null?"":"[@name/xs:string(.)=xs:string(\"" + propertyName
				+ "\")]");
		System.out.println(q);
		try {
			output.write("<ns2:Properties xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
					.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executeCommand(new XQuery(q), output);
		try {
			output.write("</ns2:Properties>".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removeProperties(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName,
			String scope, String application, String version) {
		String q = NAMESPACE_DECLARATION
				+ "delete node ns2:ExtensionFile[id=xs:string(\""
				+ extid
				+ "\")]/extension"
				+ (rootElementName == null ? ""
						: "[@rootElementName/xs:string(.)=xs:string(\""
								+ rootElementName + "\")]")
				+ (referenceID == null ? ""
						: "[@referenceID/xs:string(.)=xs:string(\""
								+ referenceID + "\")]")
				+ (scope == null ? "" : "[@scope/xs:string(.)=xs:string(\""
						+ scope + "\")]")
				+ (application == null ? ""
						: "[@application/xs:string(.)=xs:string(\""
								+ application + "\")]")
				+ (version == null ? "" : "[@version/xs:string(.)=xs:string(\""
						+ version + "\")]")
				+ "/properties" +
				(propertySetName==null?"":"[@name/xs:string(.)=xs:string(\""
				+ propertySetName + "\")]")
				+ "/property" +
				(propertyName==null?"":"[@name/xs:string(.)=xs:string(\"" + propertyName
				+ "\")]");
		System.out.println(q);
		executeCommand(new XQuery(q));

	}
	
	@Override
	public void replaceExtensions(String extid, Extensions ext) {

		removeExtensions(extid,null,null,null,null,null,null,null);

		ExtensionFile exf = new ObjectFactory().createExtensionFile();
		exf.setId(extid);
		exf.getExtension().addAll(ext.getExtension());

		String col = UUID.randomUUID().toString();

		Replace cmd = new Replace(col);
		cmd.setInput(UtilFactory.serializeDocument(exf));
		executeCommand(cmd);

	}
	
	@Override
	public void addExtensions(String extid, Extensions exts) {
		
		String extstxt = StorageUtils.serializeObjectToStringXML(exts, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + extstxt
				+ ";" + "insert nodes $c/extension into /ns2:ExtensionFile[id=\""
				+ extid + "\"]";
		System.out.println(q);
		executeCommand(new XQuery(q));
	}
	
	
	@Override
	public void addProperties(Properties props, String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName,
			String scope, String application, String version ) {
		
		if (propertyName!= null)
			removeProperties( extid,  rootElementName,
					 referenceID,  propertySetName,  propertyName,
					 scope,  application,  version);
		
		
		insertIfNotExist("/ns2:ExtensionFile[id=\"" + extid
				+ "\"]/properties[@name=\""+propertySetName+"\"]", "/ns2:ExtensionFile[id=\"" + extid
				+ "\"]", "<properties name=\""+propertySetName+"\"></properties>");
		String propsstr = StorageUtils.serializeObjectToStringXML(props, true);
		String q = NAMESPACE_DECLARATION + "declare variable $c:=" + propsstr
				+ ";" + "let $into := " +
						"" +
						"" +
						"" +
				"ns2:ExtensionFile[id=xs:string(\""
				+ extid
				+ "\")]/extension"
				+ (rootElementName == null ? ""
						: "[@rootElementName/xs:string(.)=xs:string(\""
								+ rootElementName + "\")]")
				+ (referenceID == null ? ""
						: "[@referenceID/xs:string(.)=xs:string(\""
								+ referenceID + "\")]")
				+ (scope == null ? "" : "[@scope/xs:string(.)=xs:string(\""
						+ scope + "\")]")
				+ (application == null ? ""
						: "[@application/xs:string(.)=xs:string(\""
								+ application + "\")]")
				+ (version == null ? "" : "[@version/xs:string(.)=xs:string(\""
						+ version + "\")]")
				+ "/properties" 
				+ (propertySetName == null ? "" : "[@name/xs:string(.)=xs:string(\""
				+ propertySetName + "\")]") +
				" for $x in $into return (insert nodes $c/property into $x)" +
				"" +
				"";
		System.out.println(q);
		executeCommand(new XQuery(q));
	}
	
	
	@Override
	public void getExtensionFiles(OutputStream output) {
		String q = NAMESPACE_DECLARATION
				+ "<ns2:ListOfExtensionIDs>{/ns2:ExtensionFile/id}</ns2:ListOfExtensionIDs>";

		executeCommand(new XQuery(q), output);

	}

}
