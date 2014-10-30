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
 * <p>Java class for peopleNumberUnitEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="peopleNumberUnitEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="NumberOfPeople"/>
 *     &lt;enumeration value="SquareFtPerPerson"/>
 *     &lt;enumeration value="SquareMPerPerson"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "peopleNumberUnitEnum")
@XmlEnum
public enum PeopleNumberUnitEnum {

    @XmlEnumValue("NumberOfPeople")
    NUMBER_OF_PEOPLE("NumberOfPeople"),
    @XmlEnumValue("SquareFtPerPerson")
    SQUARE_FT_PER_PERSON("SquareFtPerPerson"),
    @XmlEnumValue("SquareMPerPerson")
    SQUARE_M_PER_PERSON("SquareMPerPerson");
    private final String value;

    PeopleNumberUnitEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PeopleNumberUnitEnum fromValue(String v) {
        for (PeopleNumberUnitEnum c: PeopleNumberUnitEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
