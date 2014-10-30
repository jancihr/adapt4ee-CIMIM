//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.device;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import eu.adapt4ee._2012.schema.units.TPossitionValue;


/**
 * <p>Java class for tSensor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tSensor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gbXMLMeterRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sensorTypeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sensorPossition" type="{http://www.adapt4ee.eu/2012/schema/units/}tPossitionValue" minOccurs="0"/>
 *         &lt;element name="sensorLookAt" type="{http://www.adapt4ee.eu/2012/schema/units/}tPossitionValue" minOccurs="0"/>
 *         &lt;element name="calibration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sensorNameID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GatewayID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Measure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spaceRef" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSensor", propOrder = {
    "id",
    "gbXMLMeterRef",
    "sensorTypeRef",
    "sensorPossition",
    "sensorLookAt",
    "calibration",
    "sensorNameID",
    "gatewayID",
    "measure",
    "spaceRef"
})
public class TSensor {

    @XmlElement(required = true)
    protected String id;
    protected String gbXMLMeterRef;
    protected String sensorTypeRef;
    protected TPossitionValue sensorPossition;
    protected TPossitionValue sensorLookAt;
    protected String calibration;
    protected String sensorNameID;
    @XmlElement(name = "GatewayID")
    protected String gatewayID;
    @XmlElement(name = "Measure")
    protected String measure;
    protected List<String> spaceRef;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the gbXMLMeterRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGbXMLMeterRef() {
        return gbXMLMeterRef;
    }

    /**
     * Sets the value of the gbXMLMeterRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGbXMLMeterRef(String value) {
        this.gbXMLMeterRef = value;
    }

    /**
     * Gets the value of the sensorTypeRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensorTypeRef() {
        return sensorTypeRef;
    }

    /**
     * Sets the value of the sensorTypeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensorTypeRef(String value) {
        this.sensorTypeRef = value;
    }

    /**
     * Gets the value of the sensorPossition property.
     * 
     * @return
     *     possible object is
     *     {@link TPossitionValue }
     *     
     */
    public TPossitionValue getSensorPossition() {
        return sensorPossition;
    }

    /**
     * Sets the value of the sensorPossition property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPossitionValue }
     *     
     */
    public void setSensorPossition(TPossitionValue value) {
        this.sensorPossition = value;
    }

    /**
     * Gets the value of the sensorLookAt property.
     * 
     * @return
     *     possible object is
     *     {@link TPossitionValue }
     *     
     */
    public TPossitionValue getSensorLookAt() {
        return sensorLookAt;
    }

    /**
     * Sets the value of the sensorLookAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPossitionValue }
     *     
     */
    public void setSensorLookAt(TPossitionValue value) {
        this.sensorLookAt = value;
    }

    /**
     * Gets the value of the calibration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalibration() {
        return calibration;
    }

    /**
     * Sets the value of the calibration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalibration(String value) {
        this.calibration = value;
    }

    /**
     * Gets the value of the sensorNameID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensorNameID() {
        return sensorNameID;
    }

    /**
     * Sets the value of the sensorNameID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensorNameID(String value) {
        this.sensorNameID = value;
    }

    /**
     * Gets the value of the gatewayID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayID() {
        return gatewayID;
    }

    /**
     * Sets the value of the gatewayID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayID(String value) {
        this.gatewayID = value;
    }

    /**
     * Gets the value of the measure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * Sets the value of the measure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasure(String value) {
        this.measure = value;
    }

    /**
     * Gets the value of the spaceRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spaceRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpaceRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSpaceRef() {
        if (spaceRef == null) {
            spaceRef = new ArrayList<String>();
        }
        return this.spaceRef;
    }

}
