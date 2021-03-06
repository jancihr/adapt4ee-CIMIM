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
 * <p>Java class for emissionUnitEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="emissionUnitEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="PoundsPerHour"/>
 *     &lt;enumeration value="KilogramsPerHour"/>
 *     &lt;enumeration value="GramsPerHour"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "emissionUnitEnum")
@XmlEnum
public enum EmissionUnitEnum {

    @XmlEnumValue("PoundsPerHour")
    POUNDS_PER_HOUR("PoundsPerHour"),
    @XmlEnumValue("KilogramsPerHour")
    KILOGRAMS_PER_HOUR("KilogramsPerHour"),
    @XmlEnumValue("GramsPerHour")
    GRAMS_PER_HOUR("GramsPerHour");
    private final String value;

    EmissionUnitEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EmissionUnitEnum fromValue(String v) {
        for (EmissionUnitEnum c: EmissionUnitEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
