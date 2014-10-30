package eu.adapt4ee.cimim.rest.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.basex.core.Context;
import org.basex.core.cmd.XQuery;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;

import eu.adapt4ee._2012.schema.cim.BPMImport;
import eu.adapt4ee._2012.schema.cim.BuildingImport;
import eu.adapt4ee._2012.schema.cim.DeviceDiscovered;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee._2012.schema.cim.SimulationFile;

/**
 * @author jan hreno
 *
 */
public class TestStorageFactory {

	public static final String DATABASE_NAME = "Adapt4EE";

	protected static final Logger log = Logger
			.getLogger(StorageFactoryImplementationXbase.class.getName());

	protected static final String NAMESPACE = "http://www.adapt4ee.eu/2012/schema/cim/";
	protected static final String NAMESPACEDEVICE = "http://www.adapt4ee.eu/2012/schema/device/";
	protected static final String NAMESPACE_DECLARATION = "declare namespace ns2=\""
			+ NAMESPACE + "\";";
	protected static final String NAMESPACEDEVICE_DECLARATION = "declare namespace ns2=\""
			+ NAMESPACEDEVICE + "\";";

	protected static final String SENSOR_TYPES_COLLECION_NAME = "SensorTypes";

	public void test1() throws Exception {
		String q;
		StorageFactory sf = StorageFactoryImplementationXbase.getInstance();
		MeasurementFile measurementFile = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/MeasurementFile1.xml"),
				MeasurementFile.class);
		SimulationFile simfile = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/SimulationFile.xml"),
				SimulationFile.class);
		EventsImport evimport = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/EventsImport.xml"),
				EventsImport.class);
		BuildingImport buildimport = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/BuildingImport.xml"),
				BuildingImport.class);
		BPMImport bpmimport = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/BPMImport.xml"), BPMImport.class);
		MeasurementFile measurementFileCerth = StorageUtils
				.unserializeDocument(new FileInputStream(
						"test/xml/test_certh.xml"), MeasurementFile.class);

		// String id = sf.saveMeasurementFile(measurementFileCerth);
		// System.out.println(StorageUtils.serializeObjectToStringXML(sf
		// .getMeasurementFileByID(id)));

		// System.out.println(sf.getMeasurementFileByID(id).getGbXML());

		// measurementFile.setId("1");
		// sf.saveMeasurementFile(measurementFile);
		// System.out.println("ulozeny mf:"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFileByID("1")));
		//
		// measurementFile.setId("2");
		// sf.saveMeasurementFile(measurementFile);
		// System.out.println("ulozeny mf:"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFileByID("2")));
		//
		// System.out.println("zoznam ulozenych mf:"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFiles()));
		//
		// sf.removeMeasurementFile("1");
		// //sf.removeMeasurementFile("2");
		//
		// System.out.println("zoznam ulozenych mf po vymazani:"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFiles()));
		//
		//
		//
		//
		//
		// simfile.setId("aaa");
		// sf.saveSimulationFile(simfile);
		//
		// System.out.println("saved simfile, after reading it is: \n"+StorageUtils.serializeObjectToStringXML(sf.getSimulationFileByID("aaa")));
		//
		//
		// sf.addEvents(evimport, "2");
		// System.out.println("Pridane eventy do MF:\n"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFileByID("2")));
		//
		// sf.addBuilding(buildimport, "2");
		// System.out.println("Pridana budova do MF:\n"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFileByID("2")));
		//
		// sf.addProcess(bpmimport, "2");
		// System.out.println("Pridany proces do MF:\n"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFileByID("2")));
		//
		// sf.removeEvents("2");
		// System.out.println("Zmazane eventy do MF:\n"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFileByID("2")));
		//
		// sf.removeBuilding( "2");
		// System.out.println("Zmazana budova do MF:\n"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFileByID("2")));
		//
		// sf.removeProcesses("2");
		// System.out.println("Zmazany proces do MF:\n"+StorageUtils.serializeObjectToStringXML(sf.getMeasurementFileByID("2")));
		//
		//

		// System.out.println("\n* Convert each result to its Java representation:");

		// declare namespace ns2="http://www.adapt4ee.eu/2012/schema/cim/";
		// for $x in db:open('Adapt4EE')//ns2:MeasurementFile
		// where $x/id="1"
		// return $x/events

		// String query =
		// "declare namespace ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\";"+
		// " for $x in db:open('Adapt4EE')//ns2:MeasurementFile/id/string()"+
		// " return $x";
		// iterate(query,((StorageFactoryImplementationXbase)sf).context );

		// String q = "collection(\"Adapt4EE/" + measurementID + "\")";
		// q =
		// "declare namespace ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\";"
		// + "/ns2:MeasurementFile/id";
		// new XQuery(q).execute(sf.context, System.out);

		// ((StorageFactoryImplementationXbase) sf).close();
	}

	public void test2() throws Exception {
		String q;
		StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase
				.getInstance(true);

		MeasurementFile mf = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/MeasurementFile1.xml"),
				MeasurementFile.class);
		EventsImport evimport = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/EventsImport.xml"),
				EventsImport.class);

		// String mid = sf.saveMeasurementFile(mf);

		// String q = "collection(\"Adapt4EE/" + mid + "\")";
		// new XQuery(q).execute(sf.context, System.out);

		// System.out.println(StorageUtils.serializeObjectToStringXML(evimport));

		String events = StorageUtils.serializeObjectToStringXML(evimport, true);
		System.out.println(events);

		// q = sf.NAMESPACE_DECLARATION + "/ns2:MeasurementFile[id=\"" + mid
		// + "\"]/events";
		// new XQuery(q).execute(sf.context, System.out);

		System.out
				.println("\n============================================================");

		// insert imported events
		// q = sf.NAMESPACE_DECLARATION + "declare variable $c:=" + events + ";"
		// + "insert nodes $c/event into /ns2:MeasurementFile[id=\"" + mid
		// + "\"]/events";
		// new XQuery(q).execute(sf.context, System.out);

		// query events
		// q = sf.NAMESPACE_DECLARATION + "/ns2:MeasurementFile[id=\"" + mid
		// + "\"]/events";
		// new XQuery(q).execute(sf.context, System.out);

		// delete all events
		// q = sf.NAMESPACE_DECLARATION +
		// "delete node /ns2:MeasurementFile[id=\""
		// + mid + "\"]/events/event";
		// new XQuery(q).execute(sf.context, System.out);

		System.out
				.println("\n============================================================");

		// q = sf.NAMESPACE_DECLARATION + "/ns2:MeasurementFile[id=\"" + mid
		// + "\"]/*";
		// new XQuery(q).execute(sf.context, System.out);

		// sf.close();
	}

	public void test3() throws Exception {
		StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase
				.getInstance(true);

		MeasurementFile mf = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/MeasurementFile1.xml"),
				MeasurementFile.class);
		EventsImport evimport = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/EventsImport.xml"),
				EventsImport.class);

		// String mid = sf.saveMeasurementFile(mf);
		System.out.println(mf.getEvents().getEvent().size());

		// sf.addEvents(evimport, mid);

		// mf = sf.getMeasurementFileByID(mid);
		System.out.println(mf.getEvents().getEvent().size());

		// sf.removeEvents(mid);

		// mf = sf.getMeasurementFileByID(mid);
		System.out.println(mf.getEvents().getEvent().size());
	}

	public void test4() throws Exception {
		String q;
		String mid;
		StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase
				.getInstance(true);
		MeasurementFile mf = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/MeasurementFile1.xml"),
				MeasurementFile.class);

		q = sf.NAMESPACE_DECLARATION + "/ns2:MeasurementFile";
		new XQuery(q).execute(sf.context, System.out);

		System.out
				.println("\n============================================================");

		// mid = sf.saveMeasurementFile(mf);
		// sf.saveMeasurementFile(mf);

		q = sf.NAMESPACE_DECLARATION + "/ns2:MeasurementFile";
		new XQuery(q).execute(sf.context, System.out);
	}

	public void test5() throws Exception {
		String q;
		String mid;
		StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase
				.getInstance(true);
		MeasurementFile mf = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/MeasurementFile1.xml"),
				MeasurementFile.class);

		DeviceDiscovered dd = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/DeviceDiscovered.xml"),
				DeviceDiscovered.class);

		// mid = sf.saveMeasurementFile(mf);
		// sf.addDevice(dd, mid);

		// sf.insertIfNotExist(mid, "events", "test");
		// sf.insertIfNotExist(mid, "events", "test");

		// q = sf.NAMESPACE_DECLARATION
		// + "copy $c := <ns2:ListOfSensors> {/ns2:MeasurementFile[id=\""
		// + mid
		// + "\"]/building/sensorDevices/sensorDevice} </ns2:ListOfSensors> "
		// + "modify rename node $c/sensorDevice as \"sensor\""
		// + "return $c";

		// q = sf.NAMESPACE_DECLARATION +
		// "if (exists(/ns2:MeasurementFile[id=\"1\"]/events/test)) then <true/> else <false/>";
		// new XQuery(q).execute(sf.context, System.out);

		// System.out.println(StorageUtils.serializeObjectToStringXML(sf
		// .getSensorsByMeasurementFileID(mid)));
	}

	public void test6() throws Exception {
		String mid;
		// StorageFactoryImplementationXbase sf =
		// (StorageFactoryImplementationXbase)
		// StorageFactoryImplementationXbase.getInstance(false);
		/*
		 * MeasurementFile mf = StorageUtils.unserializeDocument( new
		 * FileInputStream("test/xml/_MeasurementFile.xml"),
		 * MeasurementFile.class);
		 * 
		 * ListOfEvents le = StorageUtils.unserializeDocument( new
		 * FileInputStream("test/xml/_ListOfEvents.xml"), ListOfEvents.class);
		 * 
		 * EventsImport ei = new EventsImport();
		 * ei.getEvent().addAll(le.getEvent());
		 * 
		 * mid = sf.saveMeasurementFile(mf); for (int i = 0; i < 10000; i++) {
		 * sf.addEvents(ei, mid); if (i % 100 == 0) System.out.println(i); }
		 */
		// sf.getEventsFromMeasurementFile("1", System.out);

		// for (int i = 0; i < 2; i++) {
		try {
			String q;

			// create session
			final BaseXClient session = new BaseXClient("localhost", 1984,
					"admin", "admin");

			// session.execute("drop db " + DATABASE_NAME);

			session.execute("check " + DATABASE_NAME);
			// System.out.println(session.execute("xquery doc('"+DATABASE_NAME+"')"));

			q = NAMESPACE_DECLARATION
					+ "insert node <event><daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco>"
					+ "<daco>dfgh</daco></event> into /ns2:MeasurementFile[id=\"test\"]/events";
			// for (int i=1;i<60000;i++){
			// session.execute("xquery " + q);
			// if(i%100==0)System.out.println(i+". ");
			// }

			StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase
					.getInstance(true);

			

			q = NAMESPACE_DECLARATION + "/ns2:MeasurementFile";

			FileOutputStream output = new FileOutputStream("d:/testovacie.zip");
			
			ZipOutputStream zip = new ZipOutputStream(output);
			zip.putNextEntry(new ZipEntry("data.xml"));
			sf.getMeasurementFileByID("test", zip);
			zip.closeEntry();
			zip.close();
			
			System.out.println("done...");
		

			// for (int i=1;i<100;i++)

			/*
			 * String inputString =
			 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><a4eecim:MeasurementFile xmlns:a4eecim=\"http://www.adapt4ee.eu/2012/schema/cim/\"><id>"
			 * +"id"+"</id></a4eecim:MeasurementFile>"; InputStream stream = new
			 * ByteArrayInputStream(inputString.getBytes("UTF-8"));
			 * session.add("id", stream);
			 * //System.out.println(session.execute("xquery doc('"
			 * +DATABASE_NAME+"')"));
			 * 
			 * 
			 * 
			 * 
			 * q = NAMESPACE_DECLARATION + "/ns2:MeasurementFile";
			 * System.out.println("pridali sme jedno\n");
			 * session.execute("xquery " + q, System.out);
			 * 
			 * 
			 * inputString =
			 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><a4eecim:MeasurementFile xmlns:a4eecim=\"http://www.adapt4ee.eu/2012/schema/cim/\"><id>"
			 * +"id"+"</id></a4eecim:MeasurementFile>"; stream = new
			 * ByteArrayInputStream(inputString.getBytes("UTF-8"));
			 * session.replace("deges", stream);
			 * 
			 * q = NAMESPACE_DECLARATION + "/ns2:MeasurementFile";
			 * System.out.println("pridali sme druhe s rovnakym id\n");
			 * session.execute("xquery " + q, System.out);
			 * 
			 * 
			 * q = NAMESPACE_DECLARATION +
			 * "replace node /ns2:MeasurementFile[id=\"" + "id" +
			 * "\"] with <ns2:MeasurementFile><id>novy</id></ns2:MeasurementFile>"
			 * ;
			 * 
			 * 
			 * q = NAMESPACE_DECLARATION + "/ns2:MeasurementFile";
			 * System.out.println("vymenili sme  sme s IDckom jedno\n");
			 * session.execute("xquery " + q, System.out);
			 * 
			 * 
			 * // q = NAMESPACE_DECLARATION // +
			 * "delete node /ns2:MeasurementFile[id=\"" + "id" // + "\"]"; //
			 * session.execute("xquery " + q); // q = NAMESPACE_DECLARATION +
			 * "/ns2:MeasurementFile";
			 * System.out.println("vymazali sme  sme s IDckom jedno\n");
			 * session.execute("xquery " + q, System.out);
			 */

			// close session
			session.close();

		} catch (final IOException ex) {
			// print exception
			ex.printStackTrace();
		}
		// }

	}

	public static void main(String[] args) throws Exception {
		new TestStorageFactory().test6();
	}

	static void iterate(final String query, Context context)
			throws QueryException {
		// ------------------------------------------------------------------------
		// Create a query processor
		QueryProcessor proc = new QueryProcessor(query, context);

		// ------------------------------------------------------------------------
		// Store the pointer to the result in an iterator:
		Iter iter = proc.iter();

		// ------------------------------------------------------------------------
		// Iterate through all items and serialize
		for (Item item; (item = iter.next()) != null;) {
			System.out.println(item.toJava());
		}

		// ------------------------------------------------------------------------
		// Close the query processor
		proc.close();
	}

}
