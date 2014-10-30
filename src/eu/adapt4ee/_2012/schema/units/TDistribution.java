//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.units;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tDistribution complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tDistribution">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="property" type="{http://www.adapt4ee.eu/2012/schema/units/}tProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://www.adapt4ee.eu/2012/schema/units/}distributionTypeEnum" />
 *       &lt;attribute name="otherType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="processRef" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="valueRef" type="{http://www.adapt4ee.eu/2012/schema/units/}processValueRefEnum" />
 *       &lt;attribute name="activityRef" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="usedEquipmentRef" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="usedSpaceTypeRef" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="otherRef" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tDistribution", propOrder = {
    "property"
})
public class TDistribution {

    protected List<TProperty> property;
    @XmlAttribute(name = "type")
    protected DistributionTypeEnum type;
    @XmlAttribute(name = "otherType")
    protected String otherType;
    @XmlAttribute(name = "processRef")
    protected String processRef;
    @XmlAttribute(name = "valueRef")
    protected ProcessValueRefEnum valueRef;
    @XmlAttribute(name = "activityRef")
    protected String activityRef;
    @XmlAttribute(name = "usedEquipmentRef")
    protected String usedEquipmentRef;
    @XmlAttribute(name = "usedSpaceTypeRef")
    protected String usedSpaceTypeRef;
    @XmlAttribute(name = "otherRef")
    protected String otherRef;

    /**
     * Gets the value of the property property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TProperty }
     * 
     * 
     */
    public List<TProperty> getProperty() {
        if (property == null) {
            property = new ArrayList<TProperty>();
        }
        return this.property;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link DistributionTypeEnum }
     *     
     */
    public DistributionTypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionTypeEnum }
     *     
     */
    public void setType(DistributionTypeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the otherType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherType() {
        return otherType;
    }

    /**
     * Sets the value of the otherType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherType(String value) {
        this.otherType = value;
    }

    /**
     * Gets the value of the processRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessRef() {
        return processRef;
    }

    /**
     * Sets the value of the processRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessRef(String value) {
        this.processRef = value;
    }

    /**
     * Gets the value of the valueRef property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessValueRefEnum }
     *     
     */
    public ProcessValueRefEnum getValueRef() {
        return valueRef;
    }

    /**
     * Sets the value of the valueRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessValueRefEnum }
     *     
     */
    public void setValueRef(ProcessValueRefEnum value) {
        this.valueRef = value;
    }

    /**
     * Gets the value of the activityRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityRef() {
        return activityRef;
    }

    /**
     * Sets the value of the activityRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityRef(String value) {
        this.activityRef = value;
    }

    /**
     * Gets the value of the usedEquipmentRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsedEquipmentRef() {
        return usedEquipmentRef;
    }

    /**
     * Sets the value of the usedEquipmentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsedEquipmentRef(String value) {
        this.usedEquipmentRef = value;
    }

    /**
     * Gets the value of the usedSpaceTypeRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsedSpaceTypeRef() {
        return usedSpaceTypeRef;
    }

    /**
     * Sets the value of the usedSpaceTypeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsedSpaceTypeRef(String value) {
        this.usedSpaceTypeRef = value;
    }

    /**
     * Gets the value of the otherRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherRef() {
        return otherRef;
    }

    /**
     * Sets the value of the otherRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherRef(String value) {
        this.otherRef = value;
    }

}
