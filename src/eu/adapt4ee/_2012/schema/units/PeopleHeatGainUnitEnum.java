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
 * <p>Java class for peopleHeatGainUnitEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="peopleHeatGainUnitEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="WattPerPerson"/>
 *     &lt;enumeration value="BtuPerHourPerson"/>
 *     &lt;enumeration value="WattPerSquareMeter"/>
 *     &lt;enumeration value="Met"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "peopleHeatGainUnitEnum")
@XmlEnum
public enum PeopleHeatGainUnitEnum {

    @XmlEnumValue("WattPerPerson")
    WATT_PER_PERSON("WattPerPerson"),
    @XmlEnumValue("BtuPerHourPerson")
    BTU_PER_HOUR_PERSON("BtuPerHourPerson"),
    @XmlEnumValue("WattPerSquareMeter")
    WATT_PER_SQUARE_METER("WattPerSquareMeter"),
    @XmlEnumValue("Met")
    MET("Met");
    private final String value;

    PeopleHeatGainUnitEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PeopleHeatGainUnitEnum fromValue(String v) {
        for (PeopleHeatGainUnitEnum c: PeopleHeatGainUnitEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
