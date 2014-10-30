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
 * <p>Java class for energyUnitEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="energyUnitEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="KilowattHours"/>
 *     &lt;enumeration value="Joules"/>
 *     &lt;enumeration value="BTU"/>
 *     &lt;enumeration value="Therms"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "energyUnitEnum")
@XmlEnum
public enum EnergyUnitEnum {

    @XmlEnumValue("KilowattHours")
    KILOWATT_HOURS("KilowattHours"),
    @XmlEnumValue("Joules")
    JOULES("Joules"),
    BTU("BTU"),
    @XmlEnumValue("Therms")
    THERMS("Therms");
    private final String value;

    EnergyUnitEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnergyUnitEnum fromValue(String v) {
        for (EnergyUnitEnum c: EnergyUnitEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}