package eu.adapt4ee.cimim.rest.client;

import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee._2012.schema.event.TEvent;
import eu.adapt4ee._2012.schema.event.TOccupantTrajectory;
import eu.adapt4ee._2012.schema.units.LengthUnitEnum;
import eu.adapt4ee._2012.schema.units.TLengthValue;
import eu.adapt4ee._2012.schema.units.TPossitionValue;
import eu.adapt4ee._2012.schema.units.TTrajectoryPoint;
import eu.adapt4ee.cimim.rest.storage.StorageFactory;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;
import eu.adapt4ee.cimim.rest.util.xml.UtilFactory;

/**
 * @author jan hreno
 *
 */
public class ImportEventsIntoTheMF {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StorageFactory sf = StorageFactoryImplementationXbase.getInstance();
		 
			 EventsImport ei = (new ObjectFactory()).createEventsImport();
			 TEvent ev = new TEvent();
			 
			 TOccupantTrajectory ot = new TOccupantTrajectory();
			 
			 
			 TTrajectoryPoint tt = new TTrajectoryPoint();
			 TPossitionValue tp = new TPossitionValue();
			 TLengthValue tl = new TLengthValue();
			 tl.setValue(55);
			 tl.setUnit(LengthUnitEnum.METERS);
			 tp.setX(tl);
			 tp.setY(tl);
			 tp.setZ(tl);
			 tt.setPossition(tp);
			 ev.setOccupantTrajectory(ot);

	ei.getEvent().add(ev);
		
					 
					 for (int i=1;i<60000;i++){
						 sf.addEvents(ei, "test");
						 if(i%100==0)System.out.println(i+". ");
					 }
		
	}

}
