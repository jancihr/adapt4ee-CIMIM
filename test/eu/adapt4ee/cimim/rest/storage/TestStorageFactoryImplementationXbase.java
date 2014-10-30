/**
 * 
 */
package eu.adapt4ee.cimim.rest.storage;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee._2012.schema.event.TEvent;

/**
 * @author janci hreno
 *
 */
public class TestStorageFactoryImplementationXbase {
	static StorageFactoryImplementationXbase sf;
    static ObjectFactory of;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
 sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase.getInstance(true);
 of = new ObjectFactory();
		
//		MeasurementFile mf = StorageUtils.unserializeDocument(
//				new FileInputStream("test/xml/MeasurementFile1.xml"),
//				MeasurementFile.class);
//		EventsImport evimport = StorageUtils.unserializeDocument(
//				new FileInputStream("test/xml/EventsImport.xml"),
//				EventsImport.class);
//		
//		String mid = sf.saveMeasurementFile(mf);
//		System.out.println(mf.getEvents().getEvent().size());
//		
//		sf.addEvents(evimport, mid);
//		
//		mf = sf.getMeasurementFileByID(mid);
//		System.out.println(mf.getEvents().getEvent().size());
//		
//		sf.removeEvents(mid);
//		
//		mf = sf.getMeasurementFileByID(mid);
//		System.out.println(mf.getEvents().getEvent().size());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getInstance(boolean)}.
	 */
	@Test
	public void testGetInstanceBoolean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#close()}.
	 */
	@Test
	public void testClose() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getMeasurementFileByID(java.lang.String)}.
	 */
	@Test
	public void testGetMeasurementFileByID() {
		//MeasurementFile mfIn  = new ObjectFactory().createMeasurementFile();
		//mfIn.setId("testik");
		//sf.saveMeasurementFile(mfIn);
		//ByteArrayOutputStream mfOut = sf.getMeasurementFileByID("testik");
		//assertEquals(mfIn.getId(),mfOut.getId());
		
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#saveMeasurementFile(eu.adapt4ee._2012.schema.cim.MeasurementFile)}.
	 */
	@Test
	public void testSaveMeasurementFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#removeMeasurementFile(java.lang.String)}.
	 */
	@Test
	public void testRemoveMeasurementFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getMeasurementFiles()}.
	 */
	@Test
	public void testGetMeasurementFiles() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#addEvents(eu.adapt4ee._2012.schema.cim.EventsImport, java.lang.String)}.
	 */
	@Test
	public void testAddEvents() {
	//	MeasurementFile mf = of.createMeasurementFile();
	//	mf.setId("testik");
	//	sf.saveMeasurementFile(mf);
	//	EventsImport ie = of.createEventsImport();
		
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#removeEvents(java.lang.String)}.
	 */
	@Test
	public void testRemoveEvents() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#addBuilding(eu.adapt4ee._2012.schema.cim.BuildingImport, java.lang.String)}.
	 */
	@Test
	public void testAddBuilding() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#removeBuilding(java.lang.String)}.
	 */
	@Test
	public void testRemoveBuilding() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#addProcess(eu.adapt4ee._2012.schema.cim.BPMImport, java.lang.String)}.
	 */
	@Test
	public void testAddProcess() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#removeProcesses(java.lang.String)}.
	 */
	@Test
	public void testRemoveProcesses() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#addDevice(eu.adapt4ee._2012.schema.cim.DeviceDiscovered, java.lang.String)}.
	 */
	@Test
	public void testAddDevice() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#addDevices(eu.adapt4ee._2012.schema.cim.ListOfSensors, java.lang.String)}.
	 */
	@Test
	public void testAddDevices() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#removeSensors(java.lang.String)}.
	 */
	@Test
	public void testRemoveSensors() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#removeEquipments(java.lang.String)}.
	 */
	@Test
	public void testRemoveEquipments() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getSensorsByMeasurementFileID(java.lang.String)}.
	 */
	@Test
	public void testGetSensorsByMeasurementFileID() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getMeasurementsInMeasurementFile(java.lang.String)}.
	 */
	@Test
	public void testGetMeasurementsInMeasurementFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getEventsFromMeasurementFile(java.lang.String)}.
	 */
	@Test
	public void testGetEventsFromMeasurementFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getProcessesInMeasurementFile(java.lang.String)}.
	 */
	@Test
	public void testGetProcessesInMeasurementFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getEquipmentsInMeasurementFile(java.lang.String)}.
	 */
	@Test
	public void testGetEquipmentsInMeasurementFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getBuildingFromMeasurementFile(java.lang.String)}.
	 */
	@Test
	public void testGetBuildingFromMeasurementFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#addMeasurements(eu.adapt4ee._2012.schema.cim.MeasurementsImport, java.lang.String)}.
	 */
	@Test
	public void testAddMeasurements() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#removeMeasurements(java.lang.String)}.
	 */
	@Test
	public void testRemoveMeasurements() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#getSimulationFileByID(java.lang.String)}.
	 */
	@Test
	public void testGetSimulationFileByID() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#saveSimulationFile(eu.adapt4ee._2012.schema.cim.SimulationFile)}.
	 */
	@Test
	public void testSaveSimulationFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#removeSimulationFile(java.lang.String)}.
	 */
	@Test
	public void testRemoveSimulationFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase#dropDB()}.
	 */
	@Test
	public void testDropDB() {
		fail("Not yet implemented"); // TODO
	}



}
