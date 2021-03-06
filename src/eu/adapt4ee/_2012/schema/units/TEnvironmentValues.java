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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tEnvironmentValues complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tEnvironmentValues">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="temperature" type="{http://www.adapt4ee.eu/2012/schema/units/}tTemperatureValue" minOccurs="0"/>
 *         &lt;element name="humidity" type="{http://www.adapt4ee.eu/2012/schema/units/}tRelativeHumidityValue" minOccurs="0"/>
 *         &lt;element name="airflow" type="{http://www.adapt4ee.eu/2012/schema/units/}tVelocityValue" minOccurs="0"/>
 *         &lt;element name="luminance" type="{http://www.adapt4ee.eu/2012/schema/units/}tIlluminanceValue" minOccurs="0"/>
 *         &lt;element name="CO2" type="{http://www.adapt4ee.eu/2012/schema/units/}tConcentrationOfGasValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tEnvironmentValues", propOrder = {
    "temperature",
    "humidity",
    "airflow",
    "luminance",
    "co2"
})
public class TEnvironmentValues {

    protected TTemperatureValue temperature;
    protected TRelativeHumidityValue humidity;
    protected TVelocityValue airflow;
    protected TIlluminanceValue luminance;
    @XmlElement(name = "CO2")
    protected TConcentrationOfGasValue co2;

    /**
     * Gets the value of the temperature property.
     * 
     * @return
     *     possible object is
     *     {@link TTemperatureValue }
     *     
     */
    public TTemperatureValue getTemperature() {
        return temperature;
    }

    /**
     * Sets the value of the temperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTemperatureValue }
     *     
     */
    public void setTemperature(TTemperatureValue value) {
        this.temperature = value;
    }

    /**
     * Gets the value of the humidity property.
     * 
     * @return
     *     possible object is
     *     {@link TRelativeHumidityValue }
     *     
     */
    public TRelativeHumidityValue getHumidity() {
        return humidity;
    }

    /**
     * Sets the value of the humidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRelativeHumidityValue }
     *     
     */
    public void setHumidity(TRelativeHumidityValue value) {
        this.humidity = value;
    }

    /**
     * Gets the value of the airflow property.
     * 
     * @return
     *     possible object is
     *     {@link TVelocityValue }
     *     
     */
    public TVelocityValue getAirflow() {
        return airflow;
    }

    /**
     * Sets the value of the airflow property.
     * 
     * @param value
     *     allowed object is
     *     {@link TVelocityValue }
     *     
     */
    public void setAirflow(TVelocityValue value) {
        this.airflow = value;
    }

    /**
     * Gets the value of the luminance property.
     * 
     * @return
     *     possible object is
     *     {@link TIlluminanceValue }
     *     
     */
    public TIlluminanceValue getLuminance() {
        return luminance;
    }

    /**
     * Sets the value of the luminance property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIlluminanceValue }
     *     
     */
    public void setLuminance(TIlluminanceValue value) {
        this.luminance = value;
    }

    /**
     * Gets the value of the co2 property.
     * 
     * @return
     *     possible object is
     *     {@link TConcentrationOfGasValue }
     *     
     */
    public TConcentrationOfGasValue getCO2() {
        return co2;
    }

    /**
     * Sets the value of the co2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link TConcentrationOfGasValue }
     *     
     */
    public void setCO2(TConcentrationOfGasValue value) {
        this.co2 = value;
    }

}
