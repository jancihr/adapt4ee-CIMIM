package eu.adapt4ee.cimim.ontology;

import eu.adapt4ee._2012.schema.bim.TBuilding;
import eu.adapt4ee._2012.schema.bim.TEquipment;
import eu.adapt4ee._2012.schema.bim.TSpace;
import eu.adapt4ee._2012.schema.bim.TSpace.Equipments;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.basex.core.cmd.XQuery;
import eu.adapt4ee._2012.schema.event.TEvent;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;

import org.basex.core.cmd.XQuery;

import eu.adapt4ee._2012.schema.units.*;

public class PositionMatcher {
	
	private String timeFrom = null;
	private String timeTo = null; // in XML format of DateTime
	private double round = 1.0; // 1.0 means one meter
	private int multiplier = 1000; // to solve problem with units mismatch
	private Map<TEquipment, List<TEvent>> resultMap = new HashMap<TEquipment, List<TEvent>>();

	public PositionMatcher() {
		this.round = 1.0;
	}

	public PositionMatcher(double round) {
		if (round <= 0)
			this.round = 1.0;
		else
			this.round = round;
	}

	public PositionMatcher(String timeFrom, String timeTo) {
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	public PositionMatcher(String timeFrom, String timeTo, double round) {
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		if (round <= 0)
			this.round = 1.0;
		else
			this.round = round;
	}

	public Map<TEquipment, List<TEvent>> getClocePositions(String timeFrom,
			String timeTo, double round, int multiplier) throws Exception {
		if (multiplier <= 0)
			this.multiplier = 1;
		else
			this.multiplier = multiplier;
		return this.getClocePositions(timeFrom, timeTo, round);
	}

	public Map<TEquipment, List<TEvent>> getClocePositions(String timeFrom,
			String timeTo, double round) throws Exception {
		if (round <= 0)
			this.round = 1.0;
		else
			this.round = round;
		return this.getClocePositions(timeFrom, timeTo);
	}

	public Map<TEquipment, List<TEvent>> getClocePositions(String timeFrom,
			String timeTo) throws Exception {
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		return this.getClocePositions();
	}

	public Map<TEquipment, List<TEvent>> getClocePositions() throws Exception {
		MeasurementFile measurementFile = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/measurementfileStatic.xml"),
				MeasurementFile.class);
		TBuilding building = measurementFile.getBuilding();
		List<TSpace> spaces = building.getSpace();
		int size = spaces.size(); // System.out.println("size: " + size);
		for (int i = 0; i < size; i++) {
			TSpace.Equipments equipmentsObject = spaces.get(i).getEquipments();
			List<TEquipment> equipmentDevices = equipmentsObject.getEquipmentDevice();
			comparePositions(equipmentDevices, spaces.get(i));
		}
		return resultMap;
	}
	
	public List<TEvent> getClosePositions(TEquipment eq, String spaceID, String measurementID) throws Exception {
		List<TEvent> events = null;
		if (eq.getPosition() != null) {
			events = getClosePositionEvents(eq.getPosition(), spaceID, measurementID);
		}
		if (events == null) {
			events = Collections.EMPTY_LIST;
		}
		return events; 
	}


	private void comparePositions(List<TEquipment> equipmentDevices,
			TSpace currentSpace) throws Exception {
		TEquipment currentEqDevice = new TEquipment();
		int sizeEq = equipmentDevices.size(); // System.out.println("sizeEq: " +
												// sizeEq);
		for (int j = 0; j < sizeEq; j++) {
			comparePosition(equipmentDevices.get(j), currentSpace.getId());
		}
	}

	private void comparePosition(TEquipment currentEqDevice, String currenSpacetID) throws Exception {
		String currentEqID = currentEqDevice.getId();
		String currentType = currentEqDevice.getType();
		List<String> refSensors = currentEqDevice.getSensorRef();
		TPossitionValue equipmentPosition = currentEqDevice.getPosition();
		List<TEvent> closeEvents = getClosePositionEvents(equipmentPosition, currenSpacetID, "ISA_MAIN_10-11_2013");
		if (closeEvents != null) {
			resultMap.put(currentEqDevice, closeEvents);
		} // TLengthValue eqX = equipmentPosition.getX();TLengthValue eqY =
			// equipmentPosition.getY();TLengthValue eqZ =
			// equipmentPosition.getZ();
		/*
		 * System.out.println("Space: " + currenSpacetID + " Id: " + currentEqID
		 * + " ... type: " + currentType + " ... pos X: " + eqX.getValue() + " "
		 * + eqX.getUnit() + " ... pos Y: " + eqY.getValue() + " " +
		 * eqY.getUnit() + " ... pos Z: " + eqZ.getValue() + " " +
		 * eqZ.getUnit());
		 */
	}

	protected List<TEvent> getClosePositionEvents(TPossitionValue equipmentPosition, String currentSpaceID, String measurementFileID) throws Exception {
		String q;
		StorageFactoryImplementationXbase sf = (StorageFactoryImplementationXbase) StorageFactoryImplementationXbase.getInstance();

		ByteArrayOutputStream queryResult = new ByteArrayOutputStream();
		// QUERY
		q = sf.NAMESPACE_DECLARATION + getQuery(equipmentPosition, currentSpaceID, measurementFileID);
		sf.executeCommand(new XQuery(q), queryResult);

		// System.out.println("query result:" + queryResult.toString());
		if (queryResult
				.toString()
				.equals("<ns2:EventsExport xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\"/>")) {
			return null;
		}
		EventsImport evimport = StorageUtils.unserializeDocument(queryResult,
				EventsImport.class);
		// OPTIONAL check kruh (zatial najde stvorec okolo pozicie devicu)
		return evimport.getEvent();
	}

	private String getQuery(TPossitionValue equipmentPosition, String currentSpaceID, String measurementFileID) {
		double roundX = round;
		double roundY = round;
		double upperX = (equipmentPosition.getX().getValue() + roundX)
				* multiplier; // X Y boundaries
		double lowerX = (equipmentPosition.getX().getValue() - roundX)
				* multiplier;
		double upperY = (equipmentPosition.getY().getValue() + roundY)
				* multiplier;
		double lowerY = (equipmentPosition.getY().getValue() - roundY)
				* multiplier;
		// System.out.println("(upperX, lowerX) ,  (upperY, lowerY ) - ("
		// +upperX+ ", " +lowerX+ ") , " + "(" +upperY+ ", " +lowerY+ ")" +
		// " SPACE: " + currentSpacetID);
		String query = null;
		String dateTimeQueryPart = null;
		if (timeFrom != null && timeTo != null) {
			dateTimeQueryPart = "[time>=\"" + timeFrom + "\" and time<=\""
					+ timeTo + "\"]";
		}
		if (timeFrom != null && timeTo == null) {
			dateTimeQueryPart = "[time>=\"" + timeFrom + "\"]";
		}
		if (timeFrom == null && timeTo != null) {
			dateTimeQueryPart = "[time<=\"" + timeTo + "\"]";
		}
		if (timeFrom == null && timeTo == null) {
			dateTimeQueryPart = "";
		}
		query = "<ns2:EventsImport xmlns:ns2=\"http://www.adapt4ee.eu/2012/schema/cim/\">"
				+ "{/ns2:MeasurementFile[id=\"" + measurementFileID + "\"]/events/event/SpaceOccupantPossitions[spaceref=\""
				+ currentSpaceID
				+ "\"]"
				+ dateTimeQueryPart
				+ "/occupantPosition/possition[x[value>="
				+ lowerX
				+ " and value<="
				+ upperX
				+ "]][y[value>="
				+ lowerY
				+ " and value<="
				+ upperY
				+ "]]/../../.. }"
				+ "</ns2:EventsImport>";
		// System.out.println("query:" + query);
		return query;
	}
}