//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.bim;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import eu.adapt4ee._2012.schema.device.TSensor;
import eu.adapt4ee._2012.schema.units.BuildingTypeEnum;
import eu.adapt4ee._2012.schema.units.TAreaValue;


/**
 * <p>Java class for tBuilding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tBuilding">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="buildingAreaSize" type="{http://www.adapt4ee.eu/2012/schema/units/}tAreaValue" minOccurs="0"/>
 *         &lt;element name="space" type="{http://www.adapt4ee.eu/2012/schema/bim/}tSpace" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="gbXMLRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="group" type="{http://www.adapt4ee.eu/2012/schema/bim/}tGroupOfSpaces" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="buildingType" type="{http://www.adapt4ee.eu/2012/schema/units/}buildingTypeEnum" minOccurs="0"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zones" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sensors" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="sensor" type="{http://www.adapt4ee.eu/2012/schema/device/}tSensor" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="equipmentTypes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="equipmentType" type="{http://www.adapt4ee.eu/2012/schema/bim/}tEquipmentTypeCharacteristics" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="HVACs">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="HVACDevice" type="{http://www.adapt4ee.eu/2012/schema/bim/}tHVAC" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tBuilding", propOrder = {
    "id",
    "buildingAreaSize",
    "space",
    "gbXMLRef",
    "group",
    "buildingType",
    "location",
    "zones",
    "sensors",
    "equipmentTypes",
    "hvaCs"
})
public class TBuilding {

    @XmlElement(required = true)
    protected String id;
    protected TAreaValue buildingAreaSize;
    protected List<TSpace> space;
    protected String gbXMLRef;
    protected List<TGroupOfSpaces> group;
    protected BuildingTypeEnum buildingType;
    protected String location;
    protected TBuilding.Zones zones;
    protected TBuilding.Sensors sensors;
    @XmlElement(required = true)
    protected TBuilding.EquipmentTypes equipmentTypes;
    @XmlElement(name = "HVACs", required = true)
    protected TBuilding.HVACs hvaCs;

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
     * Gets the value of the buildingAreaSize property.
     * 
     * @return
     *     possible object is
     *     {@link TAreaValue }
     *     
     */
    public TAreaValue getBuildingAreaSize() {
        return buildingAreaSize;
    }

    /**
     * Sets the value of the buildingAreaSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAreaValue }
     *     
     */
    public void setBuildingAreaSize(TAreaValue value) {
        this.buildingAreaSize = value;
    }

    /**
     * Gets the value of the space property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the space property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSpace }
     * 
     * 
     */
    public List<TSpace> getSpace() {
        if (space == null) {
            space = new ArrayList<TSpace>();
        }
        return this.space;
    }

    /**
     * Gets the value of the gbXMLRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGbXMLRef() {
        return gbXMLRef;
    }

    /**
     * Sets the value of the gbXMLRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGbXMLRef(String value) {
        this.gbXMLRef = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the group property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TGroupOfSpaces }
     * 
     * 
     */
    public List<TGroupOfSpaces> getGroup() {
        if (group == null) {
            group = new ArrayList<TGroupOfSpaces>();
        }
        return this.group;
    }

    /**
     * Gets the value of the buildingType property.
     * 
     * @return
     *     possible object is
     *     {@link BuildingTypeEnum }
     *     
     */
    public BuildingTypeEnum getBuildingType() {
        return buildingType;
    }

    /**
     * Sets the value of the buildingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BuildingTypeEnum }
     *     
     */
    public void setBuildingType(BuildingTypeEnum value) {
        this.buildingType = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the zones property.
     * 
     * @return
     *     possible object is
     *     {@link TBuilding.Zones }
     *     
     */
    public TBuilding.Zones getZones() {
        return zones;
    }

    /**
     * Sets the value of the zones property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBuilding.Zones }
     *     
     */
    public void setZones(TBuilding.Zones value) {
        this.zones = value;
    }

    /**
     * Gets the value of the sensors property.
     * 
     * @return
     *     possible object is
     *     {@link TBuilding.Sensors }
     *     
     */
    public TBuilding.Sensors getSensors() {
        return sensors;
    }

    /**
     * Sets the value of the sensors property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBuilding.Sensors }
     *     
     */
    public void setSensors(TBuilding.Sensors value) {
        this.sensors = value;
    }

    /**
     * Gets the value of the equipmentTypes property.
     * 
     * @return
     *     possible object is
     *     {@link TBuilding.EquipmentTypes }
     *     
     */
    public TBuilding.EquipmentTypes getEquipmentTypes() {
        return equipmentTypes;
    }

    /**
     * Sets the value of the equipmentTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBuilding.EquipmentTypes }
     *     
     */
    public void setEquipmentTypes(TBuilding.EquipmentTypes value) {
        this.equipmentTypes = value;
    }

    /**
     * Gets the value of the hvaCs property.
     * 
     * @return
     *     possible object is
     *     {@link TBuilding.HVACs }
     *     
     */
    public TBuilding.HVACs getHVACs() {
        return hvaCs;
    }

    /**
     * Sets the value of the hvaCs property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBuilding.HVACs }
     *     
     */
    public void setHVACs(TBuilding.HVACs value) {
        this.hvaCs = value;
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
     *         &lt;element name="equipmentType" type="{http://www.adapt4ee.eu/2012/schema/bim/}tEquipmentTypeCharacteristics" maxOccurs="unbounded" minOccurs="0"/>
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
        "equipmentType"
    })
    public static class EquipmentTypes {

        protected List<TEquipmentTypeCharacteristics> equipmentType;

        /**
         * Gets the value of the equipmentType property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the equipmentType property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEquipmentType().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TEquipmentTypeCharacteristics }
         * 
         * 
         */
        public List<TEquipmentTypeCharacteristics> getEquipmentType() {
            if (equipmentType == null) {
                equipmentType = new ArrayList<TEquipmentTypeCharacteristics>();
            }
            return this.equipmentType;
        }

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
     *         &lt;element name="HVACDevice" type="{http://www.adapt4ee.eu/2012/schema/bim/}tHVAC" maxOccurs="unbounded" minOccurs="0"/>
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
        "hvacDevice"
    })
    public static class HVACs {

        @XmlElement(name = "HVACDevice")
        protected List<THVAC> hvacDevice;

        /**
         * Gets the value of the hvacDevice property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the hvacDevice property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHVACDevice().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link THVAC }
         * 
         * 
         */
        public List<THVAC> getHVACDevice() {
            if (hvacDevice == null) {
                hvacDevice = new ArrayList<THVAC>();
            }
            return this.hvacDevice;
        }

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
     *         &lt;element name="sensor" type="{http://www.adapt4ee.eu/2012/schema/device/}tSensor" maxOccurs="unbounded" minOccurs="0"/>
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
        "sensor"
    })
    public static class Sensors {

        protected List<TSensor> sensor;

        /**
         * Gets the value of the sensor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the sensor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSensor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TSensor }
         * 
         * 
         */
        public List<TSensor> getSensor() {
            if (sensor == null) {
                sensor = new ArrayList<TSensor>();
            }
            return this.sensor;
        }

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
     *         &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "zone"
    })
    public static class Zones {

        protected List<String> zone;

        /**
         * Gets the value of the zone property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the zone property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getZone().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getZone() {
            if (zone == null) {
                zone = new ArrayList<String>();
            }
            return this.zone;
        }

    }

}