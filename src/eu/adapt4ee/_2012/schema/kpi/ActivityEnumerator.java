//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.kpi;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActivityEnumerator.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActivityEnumerator">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Occupant enters Building Element (BE corresponds to BE in Event Record)"/>
 *     &lt;enumeration value="Occupant leaves Building Element (BE corresponds to BE in Event Record)"/>
 *     &lt;enumeration value="Occupant starts using Equipment Artifact (EA corresponds to EA in Event Record)"/>
 *     &lt;enumeration value="Occupants stops using Equipment Artifact (EA corresponds to EA in Event Record)"/>
 *     &lt;enumeration value="Occupant starts executing some Business Activity"/>
 *     &lt;enumeration value="Occupant stops executing some Business Activity"/>
 *     &lt;enumeration value="Occupant turns on Lighting within Building Element"/>
 *     &lt;enumeration value="Occupant turns off Lighting within Building Element"/>
 *     &lt;enumeration value="Business process started"/>
 *     &lt;enumeration value="Business proces ended"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActivityEnumerator")
@XmlEnum
public enum ActivityEnumerator {

    @XmlEnumValue("Occupant enters Building Element (BE corresponds to BE in Event Record)")
    OCCUPANT_ENTERS_BUILDING_ELEMENT_BE_CORRESPONDS_TO_BE_IN_EVENT_RECORD("Occupant enters Building Element (BE corresponds to BE in Event Record)"),
    @XmlEnumValue("Occupant leaves Building Element (BE corresponds to BE in Event Record)")
    OCCUPANT_LEAVES_BUILDING_ELEMENT_BE_CORRESPONDS_TO_BE_IN_EVENT_RECORD("Occupant leaves Building Element (BE corresponds to BE in Event Record)"),
    @XmlEnumValue("Occupant starts using Equipment Artifact (EA corresponds to EA in Event Record)")
    OCCUPANT_STARTS_USING_EQUIPMENT_ARTIFACT_EA_CORRESPONDS_TO_EA_IN_EVENT_RECORD("Occupant starts using Equipment Artifact (EA corresponds to EA in Event Record)"),
    @XmlEnumValue("Occupants stops using Equipment Artifact (EA corresponds to EA in Event Record)")
    OCCUPANTS_STOPS_USING_EQUIPMENT_ARTIFACT_EA_CORRESPONDS_TO_EA_IN_EVENT_RECORD("Occupants stops using Equipment Artifact (EA corresponds to EA in Event Record)"),
    @XmlEnumValue("Occupant starts executing some Business Activity")
    OCCUPANT_STARTS_EXECUTING_SOME_BUSINESS_ACTIVITY("Occupant starts executing some Business Activity"),
    @XmlEnumValue("Occupant stops executing some Business Activity")
    OCCUPANT_STOPS_EXECUTING_SOME_BUSINESS_ACTIVITY("Occupant stops executing some Business Activity"),
    @XmlEnumValue("Occupant turns on Lighting within Building Element")
    OCCUPANT_TURNS_ON_LIGHTING_WITHIN_BUILDING_ELEMENT("Occupant turns on Lighting within Building Element"),
    @XmlEnumValue("Occupant turns off Lighting within Building Element")
    OCCUPANT_TURNS_OFF_LIGHTING_WITHIN_BUILDING_ELEMENT("Occupant turns off Lighting within Building Element"),
    @XmlEnumValue("Business process started")
    BUSINESS_PROCESS_STARTED("Business process started"),
    @XmlEnumValue("Business proces ended")
    BUSINESS_PROCES_ENDED("Business proces ended");
    private final String value;

    ActivityEnumerator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ActivityEnumerator fromValue(String v) {
        for (ActivityEnumerator c: ActivityEnumerator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}