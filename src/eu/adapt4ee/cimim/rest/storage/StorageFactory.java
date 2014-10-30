package eu.adapt4ee.cimim.rest.storage;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipInputStream;

import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import eu.adapt4ee._2012.schema.cim.BPMImport;
import eu.adapt4ee._2012.schema.cim.BuildingExport;
import eu.adapt4ee._2012.schema.cim.BuildingImport;
import eu.adapt4ee._2012.schema.cim.DeviceDiscovered;
import eu.adapt4ee._2012.schema.cim.EventTraceImport;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.EventsStats;
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
import eu.adapt4ee._2012.schema.cim.Properties;
import eu.adapt4ee._2012.schema.cim.SimulationFile;
import eu.adapt4ee._2012.schema.device.SensorType;

/**
 * @author Jan Hreno
 *
 */
public interface StorageFactory {

	public void saveMeasurementFile(InputStream m, String measurementID);

	// public MeasurementFile getMeasurementFileByID(String measurementID);
	public void getMeasurementFileByID(String measurementID, OutputStream output);

	public String saveMeasurementFileNoStream(MeasurementFile ms);

	public void getMeasurementFileByIDTransformed(String ms,
			OutputStream output, Boolean spaces)
			throws TransformerConfigurationException, TransformerException,
			FileNotFoundException;

	public void getMeasurementFileByIDWithoutBigData(String ms,
			OutputStream output);

	public void getMeasurementEventsStats(String measurementID, boolean includeMinMax, OutputStream os);

	String saveMeasurementFileLowMem(InputStream m) throws XMLStreamException;

	public void getMeasurementFileByIDFilteredByTime(String measurementID,
			String startTime, String endTime, OutputStream output);

	public void saveSimulationFile(InputStream m, String object);

	public void getSimulationFileByID(String simulationID, OutputStream output);

	public String saveSimulationFileNoStream(SimulationFile ms);

	public void getMeasurementFiles(OutputStream os);

	public void removeMeasurementFile(String measurementID);

	public void addMeasurements(MeasurementsImport mi, String measurementID);

	// public MeasurementsImport getMeasurementsInMeasurementFile(String
	// measurementID);
	public void getMeasurementsInMeasurementFile(String measurementID,
			final OutputStream output);

	public void removeMeasurements(String measurementID);

	public void addEvents(EventsImport ei, String measurementID);

	// public ListOfEvents getEventsFromMeasurementFile(String measurementID);
	public void getEventsFromMeasurementFile(String measurementID,
			OutputStream output);

	public String getGBXML(String measurementID);

	public void getGbXMLNotFormatted(String measurementID, OutputStream os);

	public void removeEvents(String measurementID);

	public void saveBuilding(BuildingImport b, String measurementID);

	public BuildingExport getBuildingFromMeasurementFile(String measurementID);

	public void removeBuilding(String measurementID);

	public ListOfEquipments getEquipmentsInMeasurementFile(String measurementID);

	public void removeEquipments(String measurementID);

	public void addDevice(DeviceDiscovered b, String measurementID);

	public void addDevices(ListOfSensors b, String measurementID);

	public ListOfSensors getSensorsByMeasurementFileID(String measurementID);

	public ListOfSensors getSensorsOfTypeByMeasurementFileID(
			String measurementID, String sensorType);

	public void removeSensors(String measurementID);

	public void addProcess(BPMImport b, String measurementID);

	public ListOfProcesses getProcessesInMeasurementFile(String measurementID);

	public void removeProcesses(String measurementID);

	// public SimulationFile getSimulationFileByID(String simulationID);

	// public String saveSimulationFile(SimulationFile m);

	// public void removeSimulationFile(String simulationID);

	public void dropDB();

	public void saveSensorTypes(InputStream sensorTypes);

	public SensorType getSensorTypes();

	public void removeSensorTypes();

	public ListOfZones getZones(String measurementID);

	public void removeZones(String measurementID);

	public void addZones(ListOfZones z, String measurementID);

	public ListOfEquipments getEquipments(String measurementID, String spaceID);

	public void removeEquipments(String measurementID, String spaceID);

	public void addEquipments(ListOfEquipments ei, String measurementID,
			String spaceID);

	public void replaceEquipments(ListOfEquipments ei, String measurementID,
			String spaceID);

	public void addRoles(ListOfRoles lor, String measurementID);

	public void replaceRoles(ListOfRoles lor, String measurementID);

	public ListOfRoles getRoles(String measurementID);

	public void removeRoles(String measurementID);

	public void addLightings(ListOfLightings los, String measurementID,
			String spaceID);

	public void replaceLightings(ListOfLightings los, String measurementID,
			String spaceID);

	public void removeLightings(String measurementID, String spaceID);

	public ListOfLightings getLightings(String measurementID, String spaceID);

	public void replaceHVACs(ListOfHVACs loh, String measurementID);

	public void addHVACs(ListOfHVACs los, String measurementID);

	public void removeHVACs(String measurementID);

	public ListOfHVACs getHVACs(String measurementID, String spaceID);

	public ListOfHVACs getHVACs(String measurementID);

	public ListOfLightings getLightings(String measurementID);

	public void addEventTracesMeasurementFile(EventTraceImport ei, String simulationID, String simulationCaseID);

	public void getSimulationFiles(OutputStream output);

	public void removeSimulationFile(String simID);

	public void getEventTracesByIDLimited(String simID, String caseID, String from,
			String to, OutputStream output);

	public void getSimulationFileByIDMintime(String ms, String caseID, OutputStream output);

	public void getSimulationFileByIDMaxtime(String ms, String caseID, OutputStream output);

	void removeEquipmentUsedEvents(String measurementID);

	public void getSimulationFileByIDCount(String ms, String caseID, OutputStream output);
	
	public void getEventsOftype(String ms, String type, OutputStream output);

	public String fixUnits(String measurementID, String unit);

	public void saveMeasurementFileZip(ZipInputStream inputXML, String measurementID);

	public void getExtensions(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName,
			String scope, String application, String version, OutputStream output);

	public void removeExtensions(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName,
			String scope, String application, String version );

	public void getPropertySets(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName,
			String scope, String application, String version, OutputStream output);

	public void removePropSets(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName, String scope,
			String application, String version);

	public void getProperties(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName,
			String scope, String application, String version,
			OutputStream output);

	public void removeProperties(String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName,
			String scope, String application, String version);

	void addExtensions(String extid, Extensions ei );


	void replaceExtensions(String extid, Extensions ext);

	void addProperties(Properties props, String extid, String rootElementName,
			String referenceID, String propertySetName, String propertyName,
			String scope, String application, String version);

	public void getExtensionFiles(OutputStream output);

	
	


}
