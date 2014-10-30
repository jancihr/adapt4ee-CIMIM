package eu.adapt4ee.cimim.rest.resource;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import javax.ws.rs.core.StreamingOutput;

import org.basex.core.cmd.XQuery;
import org.junit.Test;

import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee.cimim.ontology.ListOfEquipmentEvents;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;

/**
 * @author jan hreno
 *
 */
public class OntologyTest {

    //@Test
    public void test1() throws Exception {
    	init();
    	Ontology ontology = new Ontology();
    	
    	String measurementID = "imported";
    	String activityID = "id";
    	String spaceID = "1";
    	
    	ontology.clear();
    	ontology.init(measurementID);
    	StreamingOutput output = ontology.query(null);
    	output.write(System.out);
    	
    	EventsImport events = ontology.getActivityEvents(measurementID, activityID, spaceID);
    	System.out.println(StorageUtils.serializeObjectToStringXML(events));
    }
    
    //@Test
    public void test2() throws Exception {
    	init();
    	Ontology ontology = new Ontology();
    	
    	String measurementID = "imported";
    	String spaceID = "1";
    	
    	ListOfEquipmentEvents events = ontology.getOccupancyEvents(measurementID, spaceID);
    	System.out.println(StorageUtils.serializeObjectToStringXML(events));
    }
    
    //@Test
    public void test3() throws Exception {
    	init();
    	Ontology ontology = new Ontology();
    	
    	String measurementID = "imported";
    	String spaceID = "1";
    	
    	ListOfEquipmentEvents events = ontology.getConsumptionEvents(measurementID, spaceID);
    	System.out.println(StorageUtils.serializeObjectToStringXML(events));
    }
    
    @Test
    public void test4() throws Exception {
    	init();
    	Ontology ontology = new Ontology();
    	
    	String measurementID = "ISA_MAIN_10-11_2013_mini";
    	String spaceID = "FINANCIAL";
    	
    	ListOfEquipmentEvents events = ontology.getConsumptionEvents(measurementID, spaceID);
    	System.out.println(StorageUtils.serializeObjectToStringXML(events));
    }
    
    @Test
    public void test5() throws Exception {
    	init();

    	Ontology ontology = new Ontology();
    	
    	String measurementID = "ISA_MAIN_10-11_2013_mini";
    	String spaceID = "FINANCIAL";
    	String equipmentID = "Copier_FIN";
    	
    	ListOfEquipmentEvents events = ontology.getConsumptionEvents(measurementID, spaceID, equipmentID);
    	System.out.println(StorageUtils.serializeObjectToStringXML(events));
    }

    private void init() throws Exception {
		StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase
				.getInstance(true);
		//sf.saveMeasurementFile(new FileInputStream("test/xml/_MeasurementFile.xml"));
		sf.saveMeasurementFile(new FileInputStream("d:/ISA_MAIN_10-11_2013_mini_measurementfile.xml"),"test");
        System.setProperty("ontologies.dir", "test/ontology");
        System.setProperty("repository.dir", "tmp/repository");
    }

}
