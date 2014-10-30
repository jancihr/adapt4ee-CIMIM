package eu.adapt4ee.cimim.ontology;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.basex.core.cmd.XQuery;

import eu.adapt4ee._2012.schema.bim.TBuilding;
import eu.adapt4ee._2012.schema.bim.TEquipment;
import eu.adapt4ee._2012.schema.bim.TSpace;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.event.TEvent;
import eu.adapt4ee._2012.schema.units.TPossitionValue;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;

/**
 * @author marek skokan
 *
 */
public class PositionMatcherTest {
	
	public void test1() throws Exception {
		String q;
		String mid;
		StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase
				.getInstance(true);
		MeasurementFile mf = StorageUtils.unserializeDocument(
				//new FileInputStream("c:/MS/ADAPT/xml_measurement/measurementfile.xml"),
				new FileInputStream("test/xml/measurementfile.xml"),
				//new FileInputStream("test/xml/measurementfileStatic.xml"),
				MeasurementFile.class);

		mid = sf.saveMeasurementFileNoStream(mf);
		
		String currentSpaceID = "FINANCIAL";
		//String FILE = "test/xml/_tmp.xml";
		//Result output = new StreamResult(FILE);
		double upperX = 19065.7;
		double lowerX = 19065.3;
		double  upperY = 16588.5;
		double  lowerY = 16588.1;
		
		q = sf.NAMESPACE_DECLARATION +
"/ns2:MeasurementFile[id=\"ISA_MAIN_10-11_2013\"]/events/event/SpaceOccupantPossitions[spaceref='"+ currentSpaceID + "']/occupantPosition/possition/x[value>'"+ lowerX +"']/../../../.. ";
		
		sf.executeCommand(new XQuery(q), System.out);
		
		/*declare namespace ns2 = "http://www.adapt4ee.eu/2012/schema/cim/";
<ns2:EventsExport xmlns:ns2="http://www.adapt4ee.eu/2012/schema/cim/" >
{/ns2:MeasurementFile[id="ISA_MAIN_10-11_2013"]/events/event/SpaceOccupantPossitions[spaceref="FINANCIAL"]/occupantPosition/possition/x[value=19065.5]/../../../.. }
</ns2:EventsExport>*/
		/*declare namespace ns2 = "http://www.adapt4ee.eu/2012/schema/cim/";
<ns2:EventsExport xmlns:ns2="http://www.adapt4ee.eu/2012/schema/cim/" >
{/ns2:MeasurementFile[id="ISA_MAIN_10-11_2013"]/events/event/SpaceOccupantPossitions[spaceref="FINANCIAL"][time>="2013-10-28T10:29:11.625" and time<="2013-10-28T10:29:11.627"][spaceref="FINANCIAL"]/occupantPosition/possition[x[value>16588.2 and value<26666.0]][y[value>16588.2 and value<16666.0]]/../../..  }
</ns2:EventsExport>*/
		
		// to get values before query is executed - to revine round
		/*declare namespace ns2 = "http://www.adapt4ee.eu/2012/schema/cim/";
<ns2:EventsImport xmlns:ns2="http://www.adapt4ee.eu/2012/schema/cim/" >
{/ns2:MeasurementFile[id="ISA_MAIN_10-11_2013"]/events/event/SpaceOccupantPossitions[spaceref="FINANCIAL"][time>="2013-10-28T10:29:11.625" and time<="2013-10-28T10:29:11.627"][spaceref="FINANCIAL"]/occupantPosition/possition/x/value  }
</ns2:EventsImport>*/
		
		//System.out.println(result);
	}
	
	
	public void test2() throws Exception { // event between "2013-10-28T10:29:11.624","2013-10-28T10:29:11.626"-e.g. in FINANCIAL, big round - 100000 to eliminate unit problem 
											//- aimed at test of query
		
		PositionMatcher matcher = new PositionMatcher();
		Map<TEquipment, List<TEvent>> map = matcher.getClocePositions("2013-10-28T10:29:11.624","2013-10-28T10:29:11.626",100000);
		if(map!=null)
			System.out.println("something" + map.toString());
		else 
			System.out.println("nothing");
			
		//<ns2:EventsExport xmlns:ns2="http://www.adapt4ee.eu/2012/schema/cim/" >{/ns2:MeasurementFile[id="ISA_MAIN_10-11_2013"]/events/event/SpaceOccupantPossitions[spaceref='ENERGY_MKT'][time>='2013-10-28T10:29:11.624' and time<='2013-10-28T10:29:11.626']/occupantPosition/possition[x[value>='30.499399185180664' and value<='32.499399185180664']][y[value>='16.939699172973633' and value<='18.939699172973633']]/../../.. }</ns2:EventsExport>
	}
	
	
	public void test3() throws Exception { // FINANCIAL, big round - 20000 to eliminate unit problem 
										   //- aimed at test of query
		
	MeasurementFile measurementFile = StorageUtils.unserializeDocument(
	new FileInputStream("test/xml/measurementfileStatic.xml"),MeasurementFile.class);

	TBuilding building = measurementFile.getBuilding();
	List<TSpace> spaces = building.getSpace();
	int size =  spaces.size();    	
	System.out.println("size: " + size);
	
	for( int i=0;i<size;i++){
		//System.out.println("ID : " + spaces.get(i).getId());
		//if (spaces.get(i).getId()=="FINANCIAL")
		if(i==5)
		{			
			TSpace.Equipments equipmentsObejct = spaces.get(i).getEquipments();
			List<TEquipment> equipmentDevices = equipmentsObejct.getEquipmentDevice();    		
			TEquipment currentEqDevice = new TEquipment();
			int sizeEq =  equipmentDevices.size(); 	//System.out.println("sizeEq: " + sizeEq);
			for(int j=0;j<sizeEq;j++){ 				
				TPossitionValue equipmentPosition  = equipmentDevices.get(j).getPosition();
				PositionMatcher matcher = new PositionMatcher(20000);
				List<TEvent>evens= matcher.getClosePositionEvents(equipmentPosition, "FINANCIAL", measurementFile.getId());
    		
    		//getClosePositionEvents(TPossitionValue equipmentPosition, String currentSpacetID)
    	}
		}
	}   
	}
	
	public void test4() throws Exception { // a real test event between "2013-10-28T10:29:11.624","2013-10-28T10:29:11.626"-e.g. in FINANCIAL,
		//- multiplier (positions of devices are in fact in meters but events are in milimeters)

		PositionMatcher matcher = new PositionMatcher();
		Map<TEquipment, List<TEvent>> map = matcher.getClocePositions("2013-10-28T10:29:11.624","2013-10-28T10:29:11.626",1.0,1000);
		if(map!=null)
			System.out.println("something" + map.toString());
		else 
			System.out.println("nothing");
	}

	public static void main(String argv[]) throws Exception {
		new PositionMatcherTest().test4(); //FINANCIAL, MEETING_ROOM_1, MEETING_ROOM_2, RECEPTION, CUSTOMER_SUPPORT, 
	}

}