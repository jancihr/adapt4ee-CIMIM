//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.units;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for buildingTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="buildingTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="AutomotiveFacility"/>
 *     &lt;enumeration value="ConventionCenter"/>
 *     &lt;enumeration value="Courthouse"/>
 *     &lt;enumeration value="DiningBarLoungeOrLeisure"/>
 *     &lt;enumeration value="DiningCafeteriaFastFood"/>
 *     &lt;enumeration value="DiningFamily"/>
 *     &lt;enumeration value="Dormitory"/>
 *     &lt;enumeration value="ExerciseCenter"/>
 *     &lt;enumeration value="FireStation"/>
 *     &lt;enumeration value="Gymnasium"/>
 *     &lt;enumeration value="HospitalOrHealthcare"/>
 *     &lt;enumeration value="Hotel"/>
 *     &lt;enumeration value="Library"/>
 *     &lt;enumeration value="Manufacturing"/>
 *     &lt;enumeration value="Motel"/>
 *     &lt;enumeration value="MotionPictureTheatre"/>
 *     &lt;enumeration value="MultiFamily"/>
 *     &lt;enumeration value="Museum"/>
 *     &lt;enumeration value="Office"/>
 *     &lt;enumeration value="ParkingGarage"/>
 *     &lt;enumeration value="Penitentiary"/>
 *     &lt;enumeration value="PerformingArtsTheater"/>
 *     &lt;enumeration value="PoliceStation"/>
 *     &lt;enumeration value="PostOffice"/>
 *     &lt;enumeration value="ReligiousBuilding"/>
 *     &lt;enumeration value="Retail"/>
 *     &lt;enumeration value="SchoolOrUniversity"/>
 *     &lt;enumeration value="SingleFamily"/>
 *     &lt;enumeration value="SportsArena"/>
 *     &lt;enumeration value="TownHall"/>
 *     &lt;enumeration value="Transportation"/>
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="Warehouse"/>
 *     &lt;enumeration value="Workshop"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "buildingTypeEnum")
@XmlEnum
public enum BuildingTypeEnum {

    @XmlEnumValue("AutomotiveFacility")
    AUTOMOTIVE_FACILITY("AutomotiveFacility"),
    @XmlEnumValue("ConventionCenter")
    CONVENTION_CENTER("ConventionCenter"),
    @XmlEnumValue("Courthouse")
    COURTHOUSE("Courthouse"),
    @XmlEnumValue("DiningBarLoungeOrLeisure")
    DINING_BAR_LOUNGE_OR_LEISURE("DiningBarLoungeOrLeisure"),
    @XmlEnumValue("DiningCafeteriaFastFood")
    DINING_CAFETERIA_FAST_FOOD("DiningCafeteriaFastFood"),
    @XmlEnumValue("DiningFamily")
    DINING_FAMILY("DiningFamily"),
    @XmlEnumValue("Dormitory")
    DORMITORY("Dormitory"),
    @XmlEnumValue("ExerciseCenter")
    EXERCISE_CENTER("ExerciseCenter"),
    @XmlEnumValue("FireStation")
    FIRE_STATION("FireStation"),
    @XmlEnumValue("Gymnasium")
    GYMNASIUM("Gymnasium"),
    @XmlEnumValue("HospitalOrHealthcare")
    HOSPITAL_OR_HEALTHCARE("HospitalOrHealthcare"),
    @XmlEnumValue("Hotel")
    HOTEL("Hotel"),
    @XmlEnumValue("Library")
    LIBRARY("Library"),
    @XmlEnumValue("Manufacturing")
    MANUFACTURING("Manufacturing"),
    @XmlEnumValue("Motel")
    MOTEL("Motel"),
    @XmlEnumValue("MotionPictureTheatre")
    MOTION_PICTURE_THEATRE("MotionPictureTheatre"),
    @XmlEnumValue("MultiFamily")
    MULTI_FAMILY("MultiFamily"),
    @XmlEnumValue("Museum")
    MUSEUM("Museum"),
    @XmlEnumValue("Office")
    OFFICE("Office"),
    @XmlEnumValue("ParkingGarage")
    PARKING_GARAGE("ParkingGarage"),
    @XmlEnumValue("Penitentiary")
    PENITENTIARY("Penitentiary"),
    @XmlEnumValue("PerformingArtsTheater")
    PERFORMING_ARTS_THEATER("PerformingArtsTheater"),
    @XmlEnumValue("PoliceStation")
    POLICE_STATION("PoliceStation"),
    @XmlEnumValue("PostOffice")
    POST_OFFICE("PostOffice"),
    @XmlEnumValue("ReligiousBuilding")
    RELIGIOUS_BUILDING("ReligiousBuilding"),
    @XmlEnumValue("Retail")
    RETAIL("Retail"),
    @XmlEnumValue("SchoolOrUniversity")
    SCHOOL_OR_UNIVERSITY("SchoolOrUniversity"),
    @XmlEnumValue("SingleFamily")
    SINGLE_FAMILY("SingleFamily"),
    @XmlEnumValue("SportsArena")
    SPORTS_ARENA("SportsArena"),
    @XmlEnumValue("TownHall")
    TOWN_HALL("TownHall"),
    @XmlEnumValue("Transportation")
    TRANSPORTATION("Transportation"),

    /**
     * Enumeration value to be used when there is no building type information available.
     * 
     */
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("Warehouse")
    WAREHOUSE("Warehouse"),
    @XmlEnumValue("Workshop")
    WORKSHOP("Workshop");
    private final String value;

    BuildingTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BuildingTypeEnum fromValue(String v) {
        for (BuildingTypeEnum c: BuildingTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
