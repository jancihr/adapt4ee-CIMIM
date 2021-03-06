//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.event;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import eu.adapt4ee._2012.schema.units.TUnitlessValue;


/**
 * <p>Java class for tEquipmentUsedEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tEquipmentUsedEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="durationOfUse" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/>
 *         &lt;element name="spaceRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equipmentRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="relatedOccupant" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="occupantRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="intensityOfUse" type="{http://www.adapt4ee.eu/2012/schema/units/}tUnitlessValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tEquipmentUsedEvent", propOrder = {
    "time",
    "durationOfUse",
    "spaceRef",
    "equipmentRef",
    "relatedOccupant",
    "intensityOfUse"
})
public class TEquipmentUsedEvent {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;
    protected Duration durationOfUse;
    protected String spaceRef;
    @XmlElement(required = true)
    protected String equipmentRef;
    protected List<TEquipmentUsedEvent.RelatedOccupant> relatedOccupant;
    protected TUnitlessValue intensityOfUse;

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
     * Gets the value of the durationOfUse property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getDurationOfUse() {
        return durationOfUse;
    }

    /**
     * Sets the value of the durationOfUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setDurationOfUse(Duration value) {
        this.durationOfUse = value;
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
     * Gets the value of the equipmentRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipmentRef() {
        return equipmentRef;
    }

    /**
     * Sets the value of the equipmentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipmentRef(String value) {
        this.equipmentRef = value;
    }

    /**
     * Gets the value of the relatedOccupant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedOccupant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedOccupant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TEquipmentUsedEvent.RelatedOccupant }
     * 
     * 
     */
    public List<TEquipmentUsedEvent.RelatedOccupant> getRelatedOccupant() {
        if (relatedOccupant == null) {
            relatedOccupant = new ArrayList<TEquipmentUsedEvent.RelatedOccupant>();
        }
        return this.relatedOccupant;
    }

    /**
     * Gets the value of the intensityOfUse property.
     * 
     * @return
     *     possible object is
     *     {@link TUnitlessValue }
     *     
     */
    public TUnitlessValue getIntensityOfUse() {
        return intensityOfUse;
    }

    /**
     * Sets the value of the intensityOfUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link TUnitlessValue }
     *     
     */
    public void setIntensityOfUse(TUnitlessValue value) {
        this.intensityOfUse = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="occupantRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "occupantRef",
        "time"
    })
    public static class RelatedOccupant {

        @XmlElement(required = true)
        protected String occupantRef;
        @XmlElement(required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar time;

        /**
         * Gets the value of the occupantRef property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOccupantRef() {
            return occupantRef;
        }

        /**
         * Sets the value of the occupantRef property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOccupantRef(String value) {
            this.occupantRef = value;
        }

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

    }

}
