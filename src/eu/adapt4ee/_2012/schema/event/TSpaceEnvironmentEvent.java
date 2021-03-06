//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import eu.adapt4ee._2012.schema.units.TEnvironmentValues;


/**
 * 
 * 				TODO all values to be defined in Units
 * 			
 * 
 * <p>Java class for tSpaceEnvironmentEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tSpaceEnvironmentEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="spaceRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sensorRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="values" type="{http://www.adapt4ee.eu/2012/schema/units/}tEnvironmentValues"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSpaceEnvironmentEvent", propOrder = {
    "time",
    "spaceRef",
    "sensorRef",
    "values"
})
public class TSpaceEnvironmentEvent {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;
    protected String spaceRef;
    protected String sensorRef;
    @XmlElement(required = true)
    protected TEnvironmentValues values;

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Gets the value of the spaceRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpaceRef() {
        return spaceRef;
    }

    /**
     * Sets the value of the spaceRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpaceRef(String value) {
        this.spaceRef = value;
    }

    /**
     * Gets the value of the sensorRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensorRef() {
        return sensorRef;
    }

    /**
     * Sets the value of the sensorRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensorRef(String value) {
        this.sensorRef = value;
    }

    /**
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link TEnvironmentValues }
     *     
     */
    public TEnvironmentValues getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link TEnvironmentValues }
     *     
     */
    public void setValues(TEnvironmentValues value) {
        this.values = value;
    }

}
