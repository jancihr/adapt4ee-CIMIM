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
 * <p>Java class for energyPerAreaUnitEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="energyPerAreaUnitEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="KilowattHoursPerSquareMeter"/>
 *     &lt;enumeration value="JoulesPerSquareMeter"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "energyPerAreaUnitEnum")
@XmlEnum
public enum EnergyPerAreaUnitEnum {

    @XmlEnumValue("KilowattHoursPerSquareMeter")
    KILOWATT_HOURS_PER_SQUARE_METER("KilowattHoursPerSquareMeter"),
    @XmlEnumValue("JoulesPerSquareMeter")
    JOULES_PER_SQUARE_METER("JoulesPerSquareMeter");
    private final String value;

    EnergyPerAreaUnitEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnergyPerAreaUnitEnum fromValue(String v) {
        for (EnergyPerAreaUnitEnum c: EnergyPerAreaUnitEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
