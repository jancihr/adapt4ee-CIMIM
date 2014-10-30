//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.units;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tTimeSerieIlluminanceValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tTimeSerieIlluminanceValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="timeStart" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="timeEnd" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="value" type="{http://www.adapt4ee.eu/2012/schema/units/}tIlluminanceValue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTimeSerieIlluminanceValue", propOrder = {
    "timeStart",
    "timeEnd",
    "value"
})
public class TTimeSerieIlluminanceValue {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStart;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeEnd;
    @XmlElement(required = true)
    protected TIlluminanceValue value;

    /**
     * Gets the value of the timeStart property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStart() {
        return timeStart;
    }

    /**
     * Sets the value of the timeStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStart(XMLGregorianCalendar value) {
        this.timeStart = value;
    }

    /**
     * Gets the value of the timeEnd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeEnd() {
        return timeEnd;
    }

    /**
     * Sets the value of the timeEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeEnd(XMLGregorianCalendar value) {
        this.timeEnd = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link TIlluminanceValue }
     *     
     */
    public TIlluminanceValue getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIlluminanceValue }
     *     
     */
    public void setValue(TIlluminanceValue value) {
        this.value = value;
    }

}
