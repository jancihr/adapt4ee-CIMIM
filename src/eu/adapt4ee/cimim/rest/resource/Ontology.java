package eu.adapt4ee.cimim.rest.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import eu.adapt4ee._2012.schema.bim.TBuilding;
import eu.adapt4ee._2012.schema.bim.TEquipment;
import eu.adapt4ee._2012.schema.bim.TEquipmentTypeCharacteristics;
import eu.adapt4ee._2012.schema.bim.TSpace;
import eu.adapt4ee._2012.schema.bpm.TProcessType;
import eu.adapt4ee._2012.schema.bpm.TSkeletonActivityType;
import eu.adapt4ee._2012.schema.bpm.TSkeletonActivityType.UsedEquipment;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.ListOfEvents;
import eu.adapt4ee._2012.schema.cim.ListOfProcesses;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.device.TSensor;
import eu.adapt4ee._2012.schema.event.TEvent;
import eu.adapt4ee.cimim.ontology.ConsumptionMatcher;
import eu.adapt4ee.cimim.ontology.EquipmentEvents;
import eu.adapt4ee.cimim.ontology.ListOfEquipmentEvents;
import eu.adapt4ee.cimim.ontology.OntologyManager;
import eu.adapt4ee.cimim.ontology.PositionMatcher;
import eu.adapt4ee.cimim.ontology.Properties;
import eu.adapt4ee.cimim.rest.storage.EventGenerator;
import eu.adapt4ee.cimim.rest.storage.StorageFactory;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;

/**
 * @author peter bednar
 *
 */
@Path("/ontology")
public class Ontology {
	
	protected OntologyManager manager;
	
	public Ontology() {
		manager = OntologyManager.getInstance();
	}
	
	@GET
	@Path("/roles/sub/{param}")
	public String getSubRoles(@PathParam("param") String roleID) {
		String type = Properties.NAMESPACE + "/role#" + roleID;
		return listOfItems("ListOfRoles", "role", manager.getSubClassesOf(type));
	}
	
	@GET
	@Path("/roles/super/{param}")
	public String getSuperRoles(@PathParam("param") String roleID) {
		String type = Properties.NAMESPACE + "/role#" + roleID;
		return listOfItems("ListOfRoles", "role", manager.getSuperClassesOf(type));
	}
	
	@GET
	@Path("/equipmenttype/sub/{param}")
	public String getSubEquipmentTypes(@PathParam("param") String equipmentTypeID) {
		String type = Properties.NAMESPACE + "/equipmentType#" + equipmentTypeID;
		return listOfItems("ListOfEquipmentTypes", "equipmentType", manager.getSubClassesOf(type));
	}
	
	@GET
	@Path("/equipmenttype/super/{param}")
	public String getSuperEquipmentTypes(@PathParam("param") String equipmentTypeID) {
		String type = Properties.NAMESPACE + "/equipmentType#" + equipmentTypeID;
		return listOfItems("ListOfEquipmentTypes", "equipmentType", manager.getSubClassesOf(type));
	}
	
	@GET
	@Path("/spacetype/sub/{param}")
	public String getSubSpaceTypes(@PathParam("param") String spaceTypeID) {
		String type = Properties.NAMESPACE + "/spaceType#" + spaceTypeID;
		return listOfItems("ListOfSpaceTypes", "spaceType", manager.getSubClassesOf(type));
	}
	
	@GET
	@Path("/spacetype/super/{param}")
	public String getSuperSpaceTypes(@PathParam("param") String spaceTypeID) {
		String type = Properties.NAMESPACE + "/spaceType#" + spaceTypeID;
		return listOfItems("ListOfSpaceTypes", "spaceType", manager.getSubClassesOf(type));
	}
	
	@GET
	@Path("/events/{param}/activity/{activity}/space/{space}")
	public EventsImport getActivityEvents(
			@PathParam("param") String measurementID,
			@PathParam("activity") String activityID,
			@PathParam("space") String spaceID) {
		
		//@todo
		MeasurementFile mf = getMeasurementFile(measurementID);
		
		EventsImport response = new EventsImport();
		Set<String> types = getUsedEquipmentTypes(getActivity(mf, activityID));
		List<TEquipment> equipments = getBuildingEquipments(mf.getBuilding(), spaceID, types);
		
		PositionMatcher matcher = new PositionMatcher();
		try {
			for (TEquipment eq : equipments) {
				response.getEvent().addAll(matcher.getClosePositions(eq, spaceID, measurementID));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	//
	@GET
	@Path("/occupancyEvents/{param}/space/{space}")
	public ListOfEquipmentEvents getOccupancyEvents(
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {
		
		MeasurementFile mf = getMeasurementFile(measurementID);
		
		ListOfEquipmentEvents response = new ListOfEquipmentEvents();
		List<TEquipment> equipments = getBuildingEquipments(mf.getBuilding(), spaceID);
		
		PositionMatcher matcher = new PositionMatcher();
		try {
			for (TEquipment eq : equipments) {
				EquipmentEvents eqev = new EquipmentEvents();
				eqev.setEquipmentRef(eq.getId());
				eqev.getEvent().addAll(matcher.getClosePositions(eq, spaceID, measurementID));
				response.getEquipmentEventsEvent().add(eqev);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@GET
	@Path("/occupancyEvents/{param}/space/{space}/equipment/{equipment}")
	public ListOfEquipmentEvents getOccupancyEvents(
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID,
			@PathParam("equipment") String equipmentID) {
		
		MeasurementFile mf = getMeasurementFile(measurementID);
		
		ListOfEquipmentEvents response = new ListOfEquipmentEvents();
		List<TEquipment> equipments = getBuildingEquipments(mf.getBuilding(), spaceID);
		
		PositionMatcher matcher = new PositionMatcher();
		try {
			for (TEquipment eq : equipments) {
				if (equipmentID.equals(eq.getId())) {
					EquipmentEvents eqev = new EquipmentEvents();
					eqev.setEquipmentRef(eq.getId());
					eqev.getEvent().addAll(matcher.getClosePositions(eq, spaceID, measurementID));
					response.getEquipmentEventsEvent().add(eqev);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@GET
	@Path("/consumptionEvents/{param}/space/{space}")
	public ListOfEquipmentEvents getConsumptionEvents(
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {
		
		MeasurementFile mf = getMeasurementFile(measurementID);
		
		ListOfEquipmentEvents response = new ListOfEquipmentEvents();
		List<TEquipment> equipments = getBuildingEquipments(mf.getBuilding(), spaceID);
		
		ConsumptionMatcher matcher = new ConsumptionMatcher();
		for (TEquipment eq : equipments) {
			EquipmentEvents eqev = new EquipmentEvents();
			eqev.setEquipmentRef(eq.getId());
			eqev.getEvent().addAll(matcher.getConsumptionEvents(eq, spaceID, measurementID));
			response.getEquipmentEventsEvent().add(eqev);
		}
		
		return response;
	}

	@GET
	@Path("/consumptionEvents/{param}/space/{space}/equipment/{equipment}")
	public ListOfEquipmentEvents getConsumptionEvents(
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID,
			@PathParam("equipment") String equipmentID) {
		
		MeasurementFile mf = getMeasurementFile(measurementID);
		
		ListOfEquipmentEvents response = new ListOfEquipmentEvents();
		List<TEquipment> equipments = getBuildingEquipments(mf.getBuilding(), spaceID);
		
		ConsumptionMatcher matcher = new ConsumptionMatcher();
		for (TEquipment eq : equipments) {
			if (equipmentID.equals(eq.getId())) {
				EquipmentEvents eqev = new EquipmentEvents();
				eqev.setEquipmentRef(eq.getId());
				eqev.getEvent().addAll(matcher.getConsumptionEvents(eq, spaceID, measurementID));
				response.getEquipmentEventsEvent().add(eqev);
			}
		}
		
		return response;
	}
	

	@GET
	@Path("/init/{param}")
	public void init(@PathParam("param") String measurementID) {
		manager.update(getMeasurementFile(measurementID));
	}
	
	@GET
	@Path("/clear")
	public void clear() {
		manager.clear();
	}
	
	@GET
	@Path("/sparql/{param}")
	public StreamingOutput query(@PathParam("param") final String query) throws Exception{
		return new StreamingOutput() {

			@Override
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				if (query == null) {
					manager.getAllStatements(output);
				} else {
					manager.query(query, output);
				}
			}
			
		};
	}
	
	
	//############# generate equipment usage
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/nooccup")
	public EventsImport generateEqUsedNoOccupancy(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold ) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold,1000.0,1000.0,false,true);
	}
	
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/nooccup/nowrite")
	public EventsImport generateEqUsedNoOccupancyNowrite(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold ) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold,1000.0,1000.0,false,false);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/nooccup")
	public EventsImport generateEqUsedNoOccupancy(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold,1000.0,1000.0,false,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/nooccup/nowrite")
	public EventsImport generateEqUsedNoOccupancyNoWrite(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold,1000.0,1000.0,false,false);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/dis/{dis}/fix/{fix}/nooccup")
	public EventsImport generateEqUsedDisNoOccupancy(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold 
			, @PathParam("fix") double fixConstant, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold, fixConstant, distance,false,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/dis/{dis}/fix/{fix}/nooccup/nowrite")
	public EventsImport generateEqUsedDisNoOccupancyNowrite(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold 
			, @PathParam("fix") double fixConstant, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold, fixConstant, distance,false,false);
	}
	
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/dis/{dis}/fix/{fix}/nooccup")
	public EventsImport generateEqUsedDisNoOccupancy(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold, @PathParam("fix") double fixConstant, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold, fixConstant, distance,false,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/dis/{dis}/fix/{fix}/nooccup/nowrite")
	public EventsImport generateEqUsedDisNoOccupancyNowrite(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold, @PathParam("fix") double fixConstant, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold, fixConstant, distance,false,false);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/dis/{dis}/nooccup")
	public EventsImport generateEqUsedDisNoFixNoOccupancy(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold 
			, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold, 1.0, distance,false,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/dis/{dis}/nooccup/nowrite")
	public EventsImport generateEqUsedDisNoFixNoOccupancyNowrite(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold 
			, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold, 1.0, distance,false,false);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/dis/{dis}/nooccup")
	public EventsImport generateEqUsedDisNoFixNoOccupancy(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold,  @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold, 1.0, distance,false,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/dis/{dis}/nooccup/nowrite")
	public EventsImport generateEqUsedDisNoFixNoOccupancyNowrite(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold,  @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold, 1.0, distance,false,false);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}")
	public EventsImport generateEqUsed(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold ) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold,1.0,1000.0,true,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/nowrite")
	public EventsImport generateEqUsedNowrite(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold ) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold,1.0,1000.0,true,false);
	}
	
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}")
	public EventsImport generateEqUsed(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold,1.0,1000.0,true,false);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/nowrite")
	public EventsImport generateEqUsedNowrite(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold,1.0,1000.0,true,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/dis/{dis}/fix/{fix}")
	public EventsImport generateEqUsedDis(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold 
			, @PathParam("fix") double fixConstant, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold, fixConstant, distance,true,true);
	}
	
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/dis/{dis}/fix/{fix}/nowrite")
	public EventsImport generateEqUsedDisNowrite(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold 
			, @PathParam("fix") double fixConstant, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold, fixConstant, distance,true,false);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/dis/{dis}/fix/{fix}")
	public EventsImport generateEqUsedDis(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold, @PathParam("fix") double fixConstant, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold, fixConstant, distance,true,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/dis/{dis}/fix/{fix}/nowrite")
	public EventsImport generateEqUsedDisNowrite(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold, @PathParam("fix") double fixConstant, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold, fixConstant, distance,true,false);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/dis/{dis}")
	public EventsImport generateEqUsedDisNoFix(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold 
			, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold, 1.0, distance,true,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/from/{from}/to/{to}/treshold/{treshold}/dis/{dis}/nowrite")
	public EventsImport generateEqUsedDisNoFixNowrite(@PathParam("param")  String measurementID,@PathParam("from") String from, 
			@PathParam("to") String to, @PathParam("treshold")  String treshold 
			, @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEvents(from, to, measurementID, treshold, 1.0, distance,true,false);
	}
	
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/dis/{dis}")
	public EventsImport generateEqUsedDisNoFix(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold,  @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold, 1.0, distance,true,true);
	}
	
	@GET
	@Path("/measurementfile/{param}/event/equipmentuse/generate/treshold/{treshold}/dis/{dis}/nowrite")
	public EventsImport generateEqUsedDisNoFixNowrite(@PathParam("param")  String measurementID, 
			@PathParam("treshold")  String treshold,  @PathParam("dis") double distance) {
		
		return (new EventGenerator()).generateUsageEventsAll(measurementID, treshold, 1.0, distance,true,false);
	}
	
	
	@GET
	// add events to the measurement file
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Path("/measurementfile/{param}/fixunits/{unit}")
	public String getEventsOfType(@PathParam("param") String measurementID, @PathParam("unit") String unit) {
		StorageFactory sf = StorageFactoryImplementationXbase.getInstance();
				return	sf.fixUnits(measurementID, unit);
				
	}
	
	
	
	
	private TSkeletonActivityType getActivity(MeasurementFile mf, String activityID) {
		for (TProcessType process : mf.getProcesses().getProcess()) {
			for (TSkeletonActivityType ac : process.getActivity()) {
				if (activityID.equals(ac.getId())) {
					return ac;
				}
			}
		}
		return null;
	}
	
	private Set<String> getUsedEquipmentTypes(TSkeletonActivityType activity) {
		Set<String> types = new HashSet<String>();
		if (activity != null) {
			for (UsedEquipment eq : activity.getUsedEquipment()) {
				types.add(eq.getEquipmentTypeRef().value());
			}
		}
		return types;
	}
	
	private List<TEquipment> getBuildingEquipments(TBuilding building, String spaceID) {
		List<TEquipment> equipments = new LinkedList<TEquipment>();
		for (TSpace sp : building.getSpace()) {
			if (spaceID.equals(sp.getId())) {
				equipments.addAll(sp.getEquipments().getEquipmentDevice());
			}
		}
		return equipments;
	}
	
	private List<TEquipment> getBuildingEquipments(TBuilding building, String spaceID, Set<String> types) {
		return getBuildingEquipments(building, spaceID);
	}
	
	public MeasurementFile getMeasurementFile(String measurementID) {
		StorageFactory sf = StorageFactoryImplementationXbase.getInstance();
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		sf.getMeasurementFileByIDWithoutBigData(measurementID, os);
		MeasurementFile mf = StorageUtils.unserializeDocument(os, MeasurementFile.class);
		return mf;
	}
	
	private static String listOfItems(String listElm, String itemElm, Set<String> items) {
		StringBuilder str = new StringBuilder();
		str.append("<" + listElm + ">");
		
		for (String item : items) {
			str.append("<" + itemElm + ">");
			String id = item.substring(item.lastIndexOf("#"));
			str.append(id);
			str.append("</" + itemElm + ">");
		}
		
		str.append("</" + listElm + ">");		
		return str.toString();
	}

}
