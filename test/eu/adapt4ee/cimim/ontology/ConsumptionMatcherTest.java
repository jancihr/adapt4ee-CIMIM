package eu.adapt4ee.cimim.ontology;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import org.basex.core.cmd.XQuery;
import org.junit.Test;

import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;

/**
 * @author jan hreno
 *
 */
public class ConsumptionMatcherTest {
	
    @Test
    public void test1() throws Exception {
    	init();
    	StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase.getInstance(false);
    	
    	//String q = "declare namespace ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\";<ns2:EventsImport xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">{/ns2:MeasurementFile[id=\"ISA_MAIN_10-11_2013_mini\"]/events/event/Energy[spaceRef=\"FINANCIAL\"][sensorRef=\"1749\" or sensorRef=\"1751\"]/..}</ns2:EventsImport>";
    	String q = "declare namespace ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\";<ns2:EventsImport xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">{/ns2:MeasurementFile[id=\"ISA_MAIN_10-11_2013_mini\"]/events/event/Energy[spaceRef=\"FINANCIAL\"][sensorRef = \"1749\" or sensorRef = \"1751=\"]/.. }</ns2:EventsImport>";

    	System.out.println(q);
    	ByteArrayOutputStream queryResult = new ByteArrayOutputStream();
    	sf.executeCommand(new XQuery(q), queryResult);
    	
    	System.out.println(queryResult);
    }
    
    private static void init() throws Exception {
		StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase.getInstance(true);
		//sf.saveMeasurementFile(new FileInputStream("test/xml/_MeasurementFile.xml"));
		sf.saveMeasurementFile(new FileInputStream("d:/ISA_MAIN_10-11_2013_mini_measurementfile.xml"),"test");
        System.setProperty("ontologies.dir", "test/ontology");
        System.setProperty("repository.dir", "tmp/repository");
    }

}
