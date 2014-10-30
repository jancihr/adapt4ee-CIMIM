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
import eu.adapt4ee._2012.schema.units.SpaceTypeEnum;
import eu.adapt4ee._2012.schema.units.TAreaValue;
import eu.adapt4ee._2012.schema.units.TVolumeValue;


/**
 * tSpace is the mail representation of a space inside of the building. 
 * 
 * <p>Java class for tSpace complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tSpace">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gbXMLRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.adapt4ee.eu/2012/schema/units/}spaceTypeEnum" minOccurs="0"/>
 *         &lt;element name="floorSurface" type="{http://www.adapt4ee.eu/2012/schema/units/}tAreaValue" minOccurs="0"/>
 *         &lt;element name="volume" type="{http://www.adapt4ee.eu/2012/schema/units/}tVolumeValue" minOccurs="0"/>
 *         &lt;element name="maxOccupancyCapacity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="occupancyAdjacentSpaceRef" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="floorRef" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="zoneRef" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="equipments">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EquipmentDevice" type="{http://www.adapt4ee.eu/2012/schema/bim/}tEquipment" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="lightings">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LightingDevice" type="{http://www.adapt4ee.eu/2012/schema/bim/}tLighting" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "tSpace", propOrder = {
    "id",
    "gbXMLRef",
    "type",
    "floorSurface",
    "volume",
    "maxOccupancyCapacity",
    "occupancyAdjacentSpaceRef",
    "floorRef",
    "zoneRef",
    "equipments",
    "lightings"
})
public class TSpace {

    @XmlElement(required = true)
    protected String id;
    protected String gbXMLRef;
    protected SpaceTypeEnum type;
    protected TAreaValue floorSurface;
    protected TVolumeValue volume;
    protected Integer maxOccupancyCapacity;
    protected List<String> occupancyAdjacentSpaceRef;
    protected List<String> floorRef;
    protected List<String> zoneRef;
    @XmlElement(required = true)
    protected TSpace.Equipments equipments;
    @XmlElement(required = true)
    protected TSpace.Lightings lightings;

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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link SpaceTypeEnum }
     *     
     */
    public SpaceTypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpaceTypeEnum }
     *     
     */
    public void setType(SpaceTypeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the floorSurface property.
     * 
     * @return
     *     possible object is
     *     {@link TAreaValue }
     *     
     */
    public TAreaValue getFloorSurface() {
        return floorSurface;
    }

    /**
     * Sets the value of the floorSurface property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAreaValue }
     *     
     */
    public void setFloorSurface(TAreaValue value) {
        this.floorSurface = value;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link TVolumeValue }
     *     
     */
    public TVolumeValue getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link TVolumeValue }
     *     
     */
    public void setVolume(TVolumeValue value) {
        this.volume = value;
    }

    /**
     * Gets the value of the maxOccupancyCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxOccupancyCapacity() {
        return maxOccupancyCapacity;
    }

    /**
     * Sets the value of the maxOccupancyCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxOccupancyCapacity(Integer value) {
        this.maxOccupancyCapacity = value;
    }

    /**
     * Gets the value of the occupancyAdjacentSpaceRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the occupancyAdjacentSpaceRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOccupancyAdjacentSpaceRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOccupancyAdjacentSpaceRef() {
        if (occupancyAdjacentSpaceRef == null) {
            occupancyAdjacentSpaceRef = new ArrayList<String>();
        }
        return this.occupancyAdjacentSpaceRef;
    }

    /**
     * Gets the value of the floorRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the floorRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFloorRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFloorRef() {
        if (floorRef == null) {
            floorRef = new ArrayList<String>();
        }
        return this.floorRef;
    }

    /**
     * Gets the value of the zoneRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zoneRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZoneRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getZoneRef() {
        if (zoneRef == null) {
            zoneRef = new ArrayList<String>();
        }
        return this.zoneRef;
    }

    /**
     * Gets the value of the equipments property.
     * 
     * @return
     *     possible object is
     *     {@link TSpace.Equipments }
     *     
     */
    public TSpace.Equipments getEquipments() {
        return equipments;
    }

    /**
     * Sets the value of the equipments property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSpace.Equipments }
     *     
     */
    public void setEquipments(TSpace.Equipments value) {
        this.equipments = value;
    }

    /**
     * Gets the value of the lightings property.
     * 
     * @return
     *     possible object is
     *     {@link TSpace.Lightings }
     *     
     */
    public TSpace.Lightings getLightings() {
        return lightings;
    }

    /**
     * Sets the value of the lightings property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSpace.Lightings }
     *     
     */
    public void setLightings(TSpace.Lightings value) {
        this.lightings = value;
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
     *         &lt;element name="EquipmentDevice" type="{http://www.adapt4ee.eu/2012/schema/bim/}tEquipment" maxOccurs="unbounded" minOccurs="0"/>
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
        "equipmentDevice"
    })
    public static class Equipments {

        @XmlElement(name = "EquipmentDevice")
        protected List<TEquipment> equipmentDevice;

        /**
         * Gets the value of the equipmentDevice property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the equipmentDevice property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEquipmentDevice().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TEquipment }
         * 
         * 
         */
        public List<TEquipment> getEquipmentDevice() {
            if (equipmentDevice == null) {
                equipmentDevice = new ArrayList<TEquipment>();
            }
            return this.equipmentDevice;
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
     *         &lt;element name="LightingDevice" type="{http://www.adapt4ee.eu/2012/schema/bim/}tLighting" maxOccurs="unbounded" minOccurs="0"/>
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
        "lightingDevice"
    })
    public static class Lightings {

        @XmlElement(name = "LightingDevice")
        protected List<TLighting> lightingDevice;

        /**
         * Gets the value of the lightingDevice property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the lightingDevice property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLightingDevice().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TLighting }
         * 
         * 
         */
        public List<TLighting> getLightingDevice() {
            if (lightingDevice == null) {
                lightingDevice = new ArrayList<TLighting>();
            }
            return this.lightingDevice;
        }

    }

}
