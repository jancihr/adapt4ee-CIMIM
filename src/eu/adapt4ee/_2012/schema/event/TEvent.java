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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="occupantMoved" type="{http://www.adapt4ee.eu/2012/schema/event/}tOccupantMovementEvent" minOccurs="0"/>
 *           &lt;element name="equipmentUsed" type="{http://www.adapt4ee.eu/2012/schema/event/}tEquipmentUsedEvent" minOccurs="0"/>
 *           &lt;element name="spaceEnvironmentChange" type="{http://www.adapt4ee.eu/2012/schema/event/}tSpaceEnvironmentEvent" minOccurs="0"/>
 *           &lt;element name="externalEnvironmentChanged" type="{http://www.adapt4ee.eu/2012/schema/event/}tExternalEnvironmentEvent" minOccurs="0"/>
 *           &lt;element name="HVACChange" type="{http://www.adapt4ee.eu/2012/schema/event/}tHVACEvent" minOccurs="0"/>
 *           &lt;element name="LightingChange" type="{http://www.adapt4ee.eu/2012/schema/event/}tLightingEvent" minOccurs="0"/>
 *           &lt;element name="SpaceOccupancy" type="{http://www.adapt4ee.eu/2012/schema/event/}tSpaceOccupancy" minOccurs="0"/>
 *           &lt;element name="SpaceMovement" type="{http://www.adapt4ee.eu/2012/schema/event/}tSpaceMovement" minOccurs="0"/>
 *           &lt;element name="SpaceOccupantPossitions" type="{http://www.adapt4ee.eu/2012/schema/event/}tSpaceOccupantPossitions" minOccurs="0"/>
 *           &lt;element name="OccupantTrajectory" type="{http://www.adapt4ee.eu/2012/schema/event/}tOccupantTrajectory" minOccurs="0"/>
 *           &lt;element name="Current" type="{http://www.adapt4ee.eu/2012/schema/event/}tCurrentEvent" minOccurs="0"/>
 *           &lt;element name="Voltage" type="{http://www.adapt4ee.eu/2012/schema/event/}tVoltageEvent" minOccurs="0"/>
 *           &lt;element name="Energy" type="{http://www.adapt4ee.eu/2012/schema/event/}tEnergyEvent" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tEvent", propOrder = {
    "id",
    "occupantMoved",
    "equipmentUsed",
    "spaceEnvironmentChange",
    "externalEnvironmentChanged",
    "hvacChange",
    "lightingChange",
    "spaceOccupancy",
    "spaceMovement",
    "spaceOccupantPossitions",
    "occupantTrajectory",
    "current",
    "voltage",
    "energy"
})
public class TEvent {

    protected String id;
    protected TOccupantMovementEvent occupantMoved;
    protected TEquipmentUsedEvent equipmentUsed;
    protected TSpaceEnvironmentEvent spaceEnvironmentChange;
    protected TExternalEnvironmentEvent externalEnvironmentChanged;
    @XmlElement(name = "HVACChange")
    protected THVACEvent hvacChange;
    @XmlElement(name = "LightingChange")
    protected TLightingEvent lightingChange;
    @XmlElement(name = "SpaceOccupancy")
    protected TSpaceOccupancy spaceOccupancy;
    @XmlElement(name = "SpaceMovement")
    protected TSpaceMovement spaceMovement;
    @XmlElement(name = "SpaceOccupantPossitions")
    protected TSpaceOccupantPossitions spaceOccupantPossitions;
    @XmlElement(name = "OccupantTrajectory")
    protected TOccupantTrajectory occupantTrajectory;
    @XmlElement(name = "Current")
    protected TCurrentEvent current;
    @XmlElement(name = "Voltage")
    protected TVoltageEvent voltage;
    @XmlElement(name = "Energy")
    protected TEnergyEvent energy;

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
     * Gets the value of the occupantMoved property.
     * 
     * @return
     *     possible object is
     *     {@link TOccupantMovementEvent }
     *     
     */
    public TOccupantMovementEvent getOccupantMoved() {
        return occupantMoved;
    }

    /**
     * Sets the value of the occupantMoved property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOccupantMovementEvent }
     *     
     */
    public void setOccupantMoved(TOccupantMovementEvent value) {
        this.occupantMoved = value;
    }

    /**
     * Gets the value of the equipmentUsed property.
     * 
     * @return
     *     possible object is
     *     {@link TEquipmentUsedEvent }
     *     
     */
    public TEquipmentUsedEvent getEquipmentUsed() {
        return equipmentUsed;
    }

    /**
     * Sets the value of the equipmentUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link TEquipmentUsedEvent }
     *     
     */
    public void setEquipmentUsed(TEquipmentUsedEvent value) {
        this.equipmentUsed = value;
    }

    /**
     * Gets the value of the spaceEnvironmentChange property.
     * 
     * @return
     *     possible object is
     *     {@link TSpaceEnvironmentEvent }
     *     
     */
    public TSpaceEnvironmentEvent getSpaceEnvironmentChange() {
        return spaceEnvironmentChange;
    }

    /**
     * Sets the value of the spaceEnvironmentChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSpaceEnvironmentEvent }
     *     
     */
    public void setSpaceEnvironmentChange(TSpaceEnvironmentEvent value) {
        this.spaceEnvironmentChange = value;
    }

    /**
     * Gets the value of the externalEnvironmentChanged property.
     * 
     * @return
     *     possible object is
     *     {@link TExternalEnvironmentEvent }
     *     
     */
    public TExternalEnvironmentEvent getExternalEnvironmentChanged() {
        return externalEnvironmentChanged;
    }

    /**
     * Sets the value of the externalEnvironmentChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link TExternalEnvironmentEvent }
     *     
     */
    public void setExternalEnvironmentChanged(TExternalEnvironmentEvent value) {
        this.externalEnvironmentChanged = value;
    }

    /**
     * Gets the value of the hvacChange property.
     * 
     * @return
     *     possible object is
     *     {@link THVACEvent }
     *     
     */
    public THVACEvent getHVACChange() {
        return hvacChange;
    }

    /**
     * Sets the value of the hvacChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link THVACEvent }
     *     
     */
    public void setHVACChange(THVACEvent value) {
        this.hvacChange = value;
    }

    /**
     * Gets the value of the lightingChange property.
     * 
     * @return
     *     possible object is
     *     {@link TLightingEvent }
     *     
     */
    public TLightingEvent getLightingChange() {
        return lightingChange;
    }

    /**
     * Sets the value of the lightingChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLightingEvent }
     *     
     */
    public void setLightingChange(TLightingEvent value) {
        this.lightingChange = value;
    }

    /**
     * Gets the value of the spaceOccupancy property.
     * 
     * @return
     *     possible object is
     *     {@link TSpaceOccupancy }
     *     
     */
    public TSpaceOccupancy getSpaceOccupancy() {
        return spaceOccupancy;
    }

    /**
     * Sets the value of the spaceOccupancy property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSpaceOccupancy }
     *     
     */
    public void setSpaceOccupancy(TSpaceOccupancy value) {
        this.spaceOccupancy = value;
    }

    /**
     * Gets the value of the spaceMovement property.
     * 
     * @return
     *     possible object is
     *     {@link TSpaceMovement }
     *     
     */
    public TSpaceMovement getSpaceMovement() {
        return spaceMovement;
    }

    /**
     * Sets the value of the spaceMovement property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSpaceMovement }
     *     
     */
    public void setSpaceMovement(TSpaceMovement value) {
        this.spaceMovement = value;
    }

    /**
     * Gets the value of the spaceOccupantPossitions property.
     * 
     * @return
     *     possible object is
     *     {@link TSpaceOccupantPossitions }
     *     
     */
    public TSpaceOccupantPossitions getSpaceOccupantPossitions() {
        return spaceOccupantPossitions;
    }

    /**
     * Sets the value of the spaceOccupantPossitions property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSpaceOccupantPossitions }
     *     
     */
    public void setSpaceOccupantPossitions(TSpaceOccupantPossitions value) {
        this.spaceOccupantPossitions = value;
    }

    /**
     * Gets the value of the occupantTrajectory property.
     * 
     * @return
     *     possible object is
     *     {@link TOccupantTrajectory }
     *     
     */
    public TOccupantTrajectory getOccupantTrajectory() {
        return occupantTrajectory;
    }

    /**
     * Sets the value of the occupantTrajectory property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOccupantTrajectory }
     *     
     */
    public void setOccupantTrajectory(TOccupantTrajectory value) {
        this.occupantTrajectory = value;
    }

    /**
     * Gets the value of the current property.
     * 
     * @return
     *     possible object is
     *     {@link TCurrentEvent }
     *     
     */
    public TCurrentEvent getCurrent() {
        return current;
    }

    /**
     * Sets the value of the current property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCurrentEvent }
     *     
     */
    public void setCurrent(TCurrentEvent value) {
        this.current = value;
    }

    /**
     * Gets the value of the voltage property.
     * 
     * @return
     *     possible object is
     *     {@link TVoltageEvent }
     *     
     */
    public TVoltageEvent getVoltage() {
        return voltage;
    }

    /**
     * Sets the value of the voltage property.
     * 
     * @param value
     *     allowed object is
     *     {@link TVoltageEvent }
     *     
     */
    public void setVoltage(TVoltageEvent value) {
        this.voltage = value;
    }

    /**
     * Gets the value of the energy property.
     * 
     * @return
     *     possible object is
     *     {@link TEnergyEvent }
     *     
     */
    public TEnergyEvent getEnergy() {
        return energy;
    }

    /**
     * Sets the value of the energy property.
     * 
     * @param value
     *     allowed object is
     *     {@link TEnergyEvent }
     *     
     */
    public void setEnergy(TEnergyEvent value) {
        this.energy = value;
    }

}
