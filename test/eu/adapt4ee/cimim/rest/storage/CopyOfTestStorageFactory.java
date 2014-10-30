package eu.adapt4ee.cimim.rest.storage;

import java.io.FileInputStream;
import java.util.Iterator;

import org.basex.core.Context;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;

import eu.adapt4ee._2012.schema.cim.BPMImport;
import eu.adapt4ee._2012.schema.cim.BuildingImport;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.ListOfEvents;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee._2012.schema.cim.SimulationFile;
import eu.adapt4ee._2012.schema.event.TEvent;

/**
 * @author jan hreno
 *
 */
public class CopyOfTestStorageFactory {
	
	public static void main(String[] args) throws Exception {
		String q;
		StorageFactory sf = StorageFactoryImplementationXbase.getInstance();
		MeasurementFile measurementFile = StorageUtils.unserializeDocument(new FileInputStream("test/xml/MeasurementFile1.xml"), MeasurementFile.class);
		SimulationFile simfile = StorageUtils.unserializeDocument(new FileInputStream("test/xml/SimulationFile.xml"), SimulationFile.class);
		EventsImport evimport = StorageUtils.unserializeDocument(new FileInputStream("test/xml/EventsImport.xml"), EventsImport.class);
		BuildingImport buildimport = StorageUtils.unserializeDocument(new FileInputStream("test/xml/BuildingImport.xml"), BuildingImport.class);
		BPMImport bpmimport = StorageUtils.unserializeDocument(new FileInputStream("test/xml/BPMImport.xml"), BPMImport.class);
		
		
		
		
		sf.addEvents(evimport, "2");
		ListOfEvents loe = new ObjectFactory().createListOfEvents();
		//loe.getEvent().addAll(sf.getMeasurementFileByID("2").getEvents().getEvent());
		System.out.println("Pridane eventy do MF:\n"+StorageUtils.serializeObjectToStringXML(loe));
		//Iterator<TEvent> i = sf.getMeasurementFileByID("2").getEvents().getEvent().iterator();
		int a = 0;
		//while (i.hasNext()){
		//	System.out.println ("event c."+ a++ +" id:"+i.next().getId());
		//}
		
		
		
		((StorageFactoryImplementationXbase)sf).close();
	}
}
