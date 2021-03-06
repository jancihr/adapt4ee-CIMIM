//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.units;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tEnvironmentComfortValues complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tEnvironmentComfortValues">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="minTemperature" type="{http://www.adapt4ee.eu/2012/schema/units/}tTemperatureValue" minOccurs="0"/>
 *         &lt;element name="maxTemperature" type="{http://www.adapt4ee.eu/2012/schema/units/}tTemperatureValue" minOccurs="0"/>
 *         &lt;element name="minHumidity" type="{http://www.adapt4ee.eu/2012/schema/units/}tRelativeHumidityValue" minOccurs="0"/>
 *         &lt;element name="maxHumidity" type="{http://www.adapt4ee.eu/2012/schema/units/}tRelativeHumidityValue" minOccurs="0"/>
 *         &lt;element name="minAirflow" type="{http://www.adapt4ee.eu/2012/schema/units/}tVelocityValue" minOccurs="0"/>
 *         &lt;element name="maxAirflow" type="{http://www.adapt4ee.eu/2012/schema/units/}tVelocityValue" minOccurs="0"/>
 *         &lt;element name="minLuminance" type="{http://www.adapt4ee.eu/2012/schema/units/}tIlluminanceValue" minOccurs="0"/>
 *         &lt;element name="maxLuminance" type="{http://www.adapt4ee.eu/2012/schema/units/}tIlluminanceValue" minOccurs="0"/>
 *         &lt;element name="minCO2" type="{http://www.adapt4ee.eu/2012/schema/units/}tConcentrationOfGasValue" minOccurs="0"/>
 *         &lt;element name="maxCO2" type="{http://www.adapt4ee.eu/2012/schema/units/}tConcentrationOfGasValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tEnvironmentComfortValues", propOrder = {
    "minTemperature",
    "maxTemperature",
    "minHumidity",
    "maxHumidity",
    "minAirflow",
    "maxAirflow",
    "minLuminance",
    "maxLuminance",
    "minCO2",
    "maxCO2"
})
public class TEnvironmentComfortValues {

    protected TTemperatureValue minTemperature;
    protected TTemperatureValue maxTemperature;
    protected TRelativeHumidityValue minHumidity;
    protected TRelativeHumidityValue maxHumidity;
    protected TVelocityValue minAirflow;
    protected TVelocityValue maxAirflow;
    protected TIlluminanceValue minLuminance;
    protected TIlluminanceValue maxLuminance;
    protected TConcentrationOfGasValue minCO2;
    protected TConcentrationOfGasValue maxCO2;

    /**
     * Gets the value of the minTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link TTemperatureValue }
     *     
     */
    public TTemperatureValue getMinTemperature() {
        return minTemperature;
    }

    /**
     * Sets the value of the minTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTemperatureValue }
     *     
     */
    public void setMinTemperature(TTemperatureValue value) {
        this.minTemperature = value;
    }

    /**
     * Gets the value of the maxTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link TTemperatureValue }
     *     
     */
    public TTemperatureValue getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * Sets the value of the maxTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTemperatureValue }
     *     
     */
    public void setMaxTemperature(TTemperatureValue value) {
        this.maxTemperature = value;
    }

    /**
     * Gets the value of the minHumidity property.
     * 
     * @return
     *     possible object is
     *     {@link TRelativeHumidityValue }
     *     
     */
    public TRelativeHumidityValue getMinHumidity() {
        return minHumidity;
    }

    /**
     * Sets the value of the minHumidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRelativeHumidityValue }
     *     
     */
    public void setMinHumidity(TRelativeHumidityValue value) {
        this.minHumidity = value;
    }

    /**
     * Gets the value of the maxHumidity property.
     * 
     * @return
     *     possible object is
     *     {@link TRelativeHumidityValue }
     *     
     */
    public TRelativeHumidityValue getMaxHumidity() {
        return maxHumidity;
    }

    /**
     * Sets the value of the maxHumidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRelativeHumidityValue }
     *     
     */
    public void setMaxHumidity(TRelativeHumidityValue value) {
        this.maxHumidity = value;
    }

    /**
     * Gets the value of the minAirflow property.
     * 
     * @return
     *     possible object is
     *     {@link TVelocityValue }
     *     
     */
    public TVelocityValue getMinAirflow() {
        return minAirflow;
    }

    /**
     * Sets the value of the minAirflow property.
     * 
     * @param value
     *     allowed object is
     *     {@link TVelocityValue }
     *     
     */
    public void setMinAirflow(TVelocityValue value) {
        this.minAirflow = value;
    }

    /**
     * Gets the value of the maxAirflow property.
     * 
     * @return
     *     possible object is
     *     {@link TVelocityValue }
     *     
     */
    public TVelocityValue getMaxAirflow() {
        return maxAirflow;
    }

    /**
     * Sets the value of the maxAirflow property.
     * 
     * @param value
     *     allowed object is
     *     {@link TVelocityValue }
     *     
     */
    public void setMaxAirflow(TVelocityValue value) {
        this.maxAirflow = value;
    }

    /**
     * Gets the value of the minLuminance property.
     * 
     * @return
     *     possible object is
     *     {@link TIlluminanceValue }
     *     
     */
    public TIlluminanceValue getMinLuminance() {
        return minLuminance;
    }

    /**
     * Sets the value of the minLuminance property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIlluminanceValue }
     *     
     */
    public void setMinLuminance(TIlluminanceValue value) {
        this.minLuminance = value;
    }

    /**
     * Gets the value of the maxLuminance property.
     * 
     * @return
     *     possible object is
     *     {@link TIlluminanceValue }
     *     
     */
    public TIlluminanceValue getMaxLuminance() {
        return maxLuminance;
    }

    /**
     * Sets the value of the maxLuminance property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIlluminanceValue }
     *     
     */
    public void setMaxLuminance(TIlluminanceValue value) {
        this.maxLuminance = value;
    }

    /**
     * Gets the value of the minCO2 property.
     * 
     * @return
     *     possible object is
     *     {@link TConcentrationOfGasValue }
     *     
     */
    public TConcentrationOfGasValue getMinCO2() {
        return minCO2;
    }

    /**
     * Sets the value of the minCO2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link TConcentrationOfGasValue }
     *     
     */
    public void setMinCO2(TConcentrationOfGasValue value) {
        this.minCO2 = value;
    }

    /**
     * Gets the value of the maxCO2 property.
     * 
     * @return
     *     possible object is
     *     {@link TConcentrationOfGasValue }
     *     
     */
    public TConcentrationOfGasValue getMaxCO2() {
        return maxCO2;
    }

    /**
     * Sets the value of the maxCO2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link TConcentrationOfGasValue }
     *     
     */
    public void setMaxCO2(TConcentrationOfGasValue value) {
        this.maxCO2 = value;
    }

}
