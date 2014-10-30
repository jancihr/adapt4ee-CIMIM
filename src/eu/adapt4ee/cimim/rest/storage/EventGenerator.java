package eu.adapt4ee.cimim.rest.storage;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.basex.core.cmd.XQuery;

import eu.adapt4ee._2012.schema.bim.TEquipment;
import eu.adapt4ee._2012.schema.cim.BuildingExport;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.ListOfEquipments;
import eu.adapt4ee._2012.schema.cim.ListOfOccupants;
import eu.adapt4ee._2012.schema.cim.ListOfOccupants.Occupant;
import eu.adapt4ee._2012.schema.cim.ListOfSensors;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee._2012.schema.device.TSensor;
import eu.adapt4ee._2012.schema.event.TEquipmentUsedEvent;
import eu.adapt4ee._2012.schema.event.TEquipmentUsedEvent.RelatedOccupant;
import eu.adapt4ee._2012.schema.event.TEvent;

/**
 * @author Jan Hreno
 *
 */
public class EventGenerator {

	static int count = 0;
	StorageFactoryImplementationXbase sf;

	public EventGenerator() {
		sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase
				.getInstance(false);
	}

	public EventsImport generateUsageEventsAll(String measurementID,
			String treshold, double fixConstant, double distance,
			boolean occupants, boolean writeToMF) {
		return generateUsageEvents(null, null, measurementID, treshold,
				fixConstant, distance, occupants, writeToMF);
	}

	public EventsImport generateUsageEvents(String startTime, String endTime,
			String measurementID, String treshold, double fixConstant,
			double distance, boolean occupants, boolean writeToMF) {

		EventsImport lofev = (new ObjectFactory()).createEventsImport();
		String getSensorsQuery = StorageFactoryImplementationXbase.NAMESPACE_DECLARATION
				+ "<ns2:ListOfSensors> "
				+ "{ns2:MeasurementFile[id=\""
				+ measurementID
				+ "\"]/building/sensors/sensor} "
				+ "</ns2:ListOfSensors>";
		ByteArrayOutputStream queryResult = new ByteArrayOutputStream();
		sf.executeCommand(new XQuery(getSensorsQuery), queryResult);
		// System.out.println(q);
		// System.out.println(queryResult);
		ListOfSensors listOfSensors = StorageUtils.unserializeDocument(
				queryResult, ListOfSensors.class);

		for (TSensor sensor : listOfSensors.getSensor()) {

			String getEquipmentsQuery = StorageFactoryImplementationXbase.NAMESPACE_DECLARATION
					+ "<ns2:ListOfEquipments>"
					+ "{"
					+ "  for $equipment"
					+ "  in ns2:MeasurementFile[id=\""
					+ measurementID
					+ "\"]/building/space/equipments/EquipmentDevice[sensorRef=\""
					+ sensor.getId()
					+ "\"]"
					+ "  return $equipment"
					+ "}"
					+ "</ns2:ListOfEquipments>";
			queryResult = new ByteArrayOutputStream();
			sf.executeCommand(new XQuery(getEquipmentsQuery), queryResult);
			// System.out.println(q);
			// System.out.println(queryResult);
			ListOfEquipments listOfEquipments = StorageUtils
					.unserializeDocument(queryResult, ListOfEquipments.class);
			for (TEquipment equip : listOfEquipments.getEquipmentDevice()) {
				List<TEvent> evlist = generateUsageEventsBasex(measurementID,
						equip, sensor, startTime, endTime, treshold,
						fixConstant, distance, occupants);

				lofev.getEvent().addAll(evlist);

			}
		}

		if (writeToMF) {
			sf.removeEquipmentUsedEvents(measurementID);
			sf.addEvents(lofev, measurementID);
		}

		return lofev;
	}

	public List<TEvent> generateUsageEventsBasex(String measurementID,
			TEquipment equipment, TSensor sensorRef, String startTime,
			String endTime, String treshold, double fixConstant,
			double distance, boolean occupants) {
		String spaceRef = getSpaceForEquipment(equipment, measurementID);
		System.out.println("Testovali parne valce: senzor:" + sensorRef.getId()
				+ " equipment:" + equipment.getId() + " cas od:" + startTime
				+ " do " + endTime);
		List<TEvent> result = new LinkedList<TEvent>();

		List<TEvent> events;

		if (startTime == null)
			events = getEnergyEvents(sensorRef, measurementID);
		else
			events = getEnergyEvents(sensorRef, startTime, endTime,
					measurementID);

		// sort

		boolean inUse = false;
		XMLGregorianCalendar from = null, to = null;

		for (int i = 0; i < events.size(); i++) {
			double value = events.get(i).getEnergy().getValue().getValue();
			to = events.get(i).getEnergy().getTime();

			if (value > (Double.parseDouble(treshold))) {
				if (!inUse) {
					from = events.get(i).getEnergy().getTime();
				}
				inUse = true;
			} else if (inUse) {
				inUse = false;

				if (from != null) {
					TEvent event = new TEvent();
					event.setEquipmentUsed(new TEquipmentUsedEvent());
					event.getEquipmentUsed().setTime(from);
					event.getEquipmentUsed().setEquipmentRef(equipment.getId());
					event.getEquipmentUsed().setDurationOfUse(
							getDuration(from, to));

					if (occupants) {
						Map<String, String> mp = getOccupantsAroundUsedEquipment(
								measurementID, equipment.getId(), spaceRef,
								distance, fixConstant, from.toXMLFormat(),
								to.toXMLFormat());
						Iterator<Entry<String, String>> it = mp.entrySet()
								.iterator();

						while (it.hasNext()) {
							RelatedOccupant ro = new RelatedOccupant();
							Map.Entry<String, String> pairs = (Map.Entry<String, String>) it
									.next();

							ro.setOccupantRef(pairs.getKey());
							try {
								ro.setTime(DatatypeFactory.newInstance()
										.newXMLGregorianCalendar(
												pairs.getValue()));
							} catch (DatatypeConfigurationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							event.getEquipmentUsed().getRelatedOccupant()
									.add(ro);
							it.remove(); // avoids a
											// ConcurrentModificationException
						}
					}

					event.getEquipmentUsed().setSpaceRef(spaceRef);
					result.add(event);
					//System.out.println("Pouzite zariadenie: "
					//		+ EventGenerator.count++ + " :"
					//		+ event.getEquipmentUsed().getEquipmentRef());

				}

			}

		}

		if (inUse && from != null) {
			TEvent event = new TEvent();
			event.setEquipmentUsed(new TEquipmentUsedEvent());
			event.getEquipmentUsed().setTime(from);
			event.getEquipmentUsed().setEquipmentRef(equipment.getId());
			event.getEquipmentUsed().setDurationOfUse(getDuration(from, to));
			Map<String, String> mp = getOccupantsAroundUsedEquipment(
					measurementID, equipment.getId(), spaceRef, distance,
					fixConstant, from.toXMLFormat(), to.toXMLFormat());
			Iterator<Entry<String, String>> it = mp.entrySet().iterator();

			
			
			while (it.hasNext()) {
				RelatedOccupant ro = new RelatedOccupant();
				Map.Entry<String, String> pairs = (Map.Entry<String, String>) it
						.next();

				ro.setOccupantRef(pairs.getKey());
				try {
					ro.setTime(DatatypeFactory.newInstance()
							.newXMLGregorianCalendar(pairs.getValue()));
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				event.getEquipmentUsed().getRelatedOccupant().add(ro);
				it.remove(); // avoids a ConcurrentModificationException
			}
			event.getEquipmentUsed().setSpaceRef(spaceRef);
			result.add(event);

			System.out.println("Pouzite zariadenie: " + EventGenerator.count++
					+ " :" + event.getEquipmentUsed().getEquipmentRef());
		}

		return result;
	}

	public List<TEvent> getEnergyEvents(TSensor sensorRef, String startTime,
			String endTime, String measurementID) {
		String q = StorageFactoryImplementationXbase.NAMESPACE_DECLARATION
				+ "let $min := xs:dateTime('"
				+ startTime
				+ "') let $max := xs:dateTime('"
				+ endTime
				+ "') "
				+ "return "
				+ "<ns2:EventsImport xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
				+ "{/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/events/event/Energy[sensorRef=\"" + sensorRef.getId()
				+ "\" and " + "time > $min and time < $max" + "]/.. }"
				+ "</ns2:EventsImport>";

		ByteArrayOutputStream queryResult = new ByteArrayOutputStream();
		sf.executeCommand(new XQuery(q), queryResult);

		// System.out.println(q);
		// System.out.println(queryResult);

		EventsImport evimport = StorageUtils.unserializeDocument(queryResult,
				EventsImport.class);
		System.out.println("For duration: from: " + startTime + " to: "
				+ endTime);
		System.out.println("For sensor: " + sensorRef.getId());
		System.out.println("Number of events: " + evimport.getEvent().size());

		return evimport.getEvent();
	}

	public Map<String, String> getOccupantsAroundUsedEquipment(
			String measurementID, String equipmentID, String spaceID,
			double squareSurround, double fixValue, String startTime,
			String endTime) {
		String q = StorageFactoryImplementationXbase.NAMESPACE_DECLARATION
				+ "let $id := xs:string(\""
				+ measurementID
				+ "\")"
				+ "		let $equipmentID := xs:string(\""
				+ equipmentID
				+ "\")"
				+ "		let $surround := xs:float("
				+ squareSurround
				+ ")"
				+ "		let $fixconstant := xs:float("
				+ fixValue
				+ ")"
				+ "		let $startTime := xs:dateTime(\""
				+ startTime
				+ "\")"
				+ "		let $endTime := xs:dateTime(\""
				+ endTime
				+ "\")"
				+ "		let $spaceID := xs:string(\""
				+ spaceID
				+ "\")"
				+ "		return"
				+ "		<ns2:ListOfOccupants>"
				+ "		{"
				+ "		let $equipment := ns2:MeasurementFile[id=$id]/building/space[id=$spaceID]/equipments/EquipmentDevice[id=$equipmentID]"
				+ "		let $minX := (($equipment/position/x/value/xs:float(.))*$fixconstant)-$surround"
				+ "		let $maxX := (($equipment/position/x/value/xs:float(.))*$fixconstant)+$surround"
				+ "		let $minY := (($equipment/position/y/value/xs:float(.))*$fixconstant)-$surround"
				+ "		let $maxY := (($equipment/position/y/value/xs:float(.))*$fixconstant)+$surround"
				+ "		let $minZ := (($equipment/position/z/value/xs:float(.))*$fixconstant)-$surround"
				+ "		let $maxZ := (($equipment/position/z/value/xs:float(.))*$fixconstant)+$surround"
				+ "		let $occEventsWithPositions := "
				+ "		ns2:MeasurementFile[id=$id]/events/event[SpaceOccupantPossitions and SpaceOccupantPossitions/time/xs:dateTime(.) >= $startTime and SpaceOccupantPossitions/time/xs:dateTime(.) <= $endTime]"
				+ ""
				+ "		for $occEvent in $occEventsWithPositions"
				+ "		for $item in $occEvent/SpaceOccupantPossitions/occupantPosition[possition/x/value/xs:float(.)<=$maxX"
				+ "		    and possition/x/value/xs:float(.)>=$minX"
				+ "           and possition/y/value/xs:float(.)<=$maxY"
				+ "		    and possition/y/value/xs:float(.)>=$minY"
				+ "		    and possition/z/value/xs:float(.)<=$maxZ"
				+ "		    and possition/z/value/xs:float(.)>=$minZ]"
				+ "		     return "
				+ "		       <occupant>"
				+ "		       <id>{$item/occupantid/xs:string(.)}"
				+ "		       </id><time>{$occEvent/SpaceOccupantPossitions/time/xs:dateTime(.)}</time>"
				+ "		       </occupant>     " + "		  }		</ns2:ListOfOccupants>";
		// System.out.println(q);

		ByteArrayOutputStream queryResult = new ByteArrayOutputStream();
		sf.executeCommand(new XQuery(q), queryResult);

		// System.out.println(q);
		// System.out.println(queryResult);

		ListOfOccupants looc = StorageUtils.unserializeDocument(queryResult,
				ListOfOccupants.class);
		HashMap<String, String> result = new HashMap<String, String>();
		for (Occupant occ : looc.getOccupant()) {
			//System.out.println("id "+occ.getId());
			//System.out.println("time "+occ.getTime());
			result.put(occ.getId(), occ.getTime().toXMLFormat());
		}
		return result;

	}

	public String getSpaceForEquipment(TEquipment equip, String measurementID) {
		String q = StorageFactoryImplementationXbase.NAMESPACE_DECLARATION
				+ "		let $id := xs:string(\""
				+ measurementID
				+ "\")"
				+ "	let $eq := xs:string(\""
				+ equip.getId()
				+ "\") "
				+ "for $spaceID in ns2:MeasurementFile[id=$id]/building/space/id/xs:string(.) "
				+ "for $equipment in ns2:MeasurementFile[id=$id]/building/space[id=$spaceID]/equipments/EquipmentDevice[id=$eq] "
				+ "return <ns2:BuildingExport><building><space><id>{$spaceID}</id></space></building></ns2:BuildingExport>";
		ByteArrayOutputStream queryResult = new ByteArrayOutputStream();
		sf.executeCommand(new XQuery(q), queryResult);

		// System.out.println(q);
		// System.out.println(queryResult);

		BuildingExport building = StorageUtils.unserializeDocument(queryResult,
				BuildingExport.class);

		return building.getBuilding().getSpace().get(0).getId();
	}

	public List<TEvent> getEnergyEvents(TSensor sensorRef, String measurementID) {
		String q = StorageFactoryImplementationXbase.NAMESPACE_DECLARATION
				+ "<ns2:EventsImport xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
				+ "{/ns2:MeasurementFile[id=\"" + measurementID
				+ "\"]/events/event[Energy/sensorRef=\"" + sensorRef.getId()
				+ "\"]}" + "</ns2:EventsImport>";

		ByteArrayOutputStream queryResult = new ByteArrayOutputStream();
		sf.executeCommand(new XQuery(q), queryResult);

		// System.out.println(q);
		// System.out.println(queryResult);

		EventsImport evimport = StorageUtils.unserializeDocument(queryResult,
				EventsImport.class);

		return evimport.getEvent();
	}

	private Duration getDuration(XMLGregorianCalendar from,
			XMLGregorianCalendar to) {
		if (from == null || to == null) {
			return null;
		}

		long start = from.toGregorianCalendar().getTimeInMillis();
		long end = to.toGregorianCalendar().getTimeInMillis();

		try {
			return DatatypeFactory.newInstance()
					.newDurationDayTime(end - start);
		} catch (DatatypeConfigurationException e) {
			// ignored
		}

		return null;
	}

}
