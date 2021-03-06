//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.02 at 11:00:40 PM CEST 
//


package eu.adapt4ee._2012.schema.cim;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import eu.adapt4ee._2012.schema.bim.TBuilding;
import eu.adapt4ee._2012.schema.bpm.TProcessType;
import eu.adapt4ee._2012.schema.bpm.TRole;
import eu.adapt4ee._2012.schema.device.TSensorMeasurement;
import eu.adapt4ee._2012.schema.event.TEvent;


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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="events">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="event" type="{http://www.adapt4ee.eu/2012/schema/event/}tEvent" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="measurements">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="measurement" type="{http://www.adapt4ee.eu/2012/schema/device/}tSensorMeasurement" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="processes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="process" type="{http://www.adapt4ee.eu/2012/schema/bpm/}tProcessType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="roles">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="role" type="{http://www.adapt4ee.eu/2012/schema/bpm/}tRole" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="building" type="{http://www.adapt4ee.eu/2012/schema/bim/}tBuilding"/>
 *         &lt;element name="gbXML" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
    "id",
    "events",
    "measurements",
    "processes",
    "roles",
    "building",
    "gbXML"
})
@XmlRootElement(name = "MeasurementFile")
public class MeasurementFile {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected MeasurementFile.Events events;
    @XmlElement(required = true)
    protected MeasurementFile.Measurements measurements;
    @XmlElement(required = true)
    protected MeasurementFile.Processes processes;
    @XmlElement(required = true)
    protected MeasurementFile.Roles roles;
    @XmlElement(required = true)
    protected TBuilding building;
    @XmlElement(required = true)
    protected Object gbXML;

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
     * Gets the value of the events property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementFile.Events }
     *     
     */
    public MeasurementFile.Events getEvents() {
        return events;
    }

    /**
     * Sets the value of the events property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementFile.Events }
     *     
     */
    public void setEvents(MeasurementFile.Events value) {
        this.events = value;
    }

    /**
     * Gets the value of the measurements property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementFile.Measurements }
     *     
     */
    public MeasurementFile.Measurements getMeasurements() {
        return measurements;
    }

    /**
     * Sets the value of the measurements property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementFile.Measurements }
     *     
     */
    public void setMeasurements(MeasurementFile.Measurements value) {
        this.measurements = value;
    }

    /**
     * Gets the value of the processes property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementFile.Processes }
     *     
     */
    public MeasurementFile.Processes getProcesses() {
        return processes;
    }

    /**
     * Sets the value of the processes property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementFile.Processes }
     *     
     */
    public void setProcesses(MeasurementFile.Processes value) {
        this.processes = value;
    }

    /**
     * Gets the value of the roles property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementFile.Roles }
     *     
     */
    public MeasurementFile.Roles getRoles() {
        return roles;
    }

    /**
     * Sets the value of the roles property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementFile.Roles }
     *     
     */
    public void setRoles(MeasurementFile.Roles value) {
        this.roles = value;
    }

    /**
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link TBuilding }
     *     
     */
    public TBuilding getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBuilding }
     *     
     */
    public void setBuilding(TBuilding value) {
        this.building = value;
    }

    /**
     * Gets the value of the gbXML property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getGbXML() {
        return gbXML;
    }

    /**
     * Sets the value of the gbXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setGbXML(Object value) {
        this.gbXML = value;
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
     *         &lt;element name="event" type="{http://www.adapt4ee.eu/2012/schema/event/}tEvent" maxOccurs="unbounded" minOccurs="0"/>
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
        "event"
    })
    public static class Events {

        protected List<TEvent> event;

        /**
         * Gets the value of the event property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the event property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEvent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TEvent }
         * 
         * 
         */
        public List<TEvent> getEvent() {
            if (event == null) {
                event = new ArrayList<TEvent>();
            }
            return this.event;
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
     *         &lt;element name="measurement" type="{http://www.adapt4ee.eu/2012/schema/device/}tSensorMeasurement" maxOccurs="unbounded" minOccurs="0"/>
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
        "measurement"
    })
    public static class Measurements {

        protected List<TSensorMeasurement> measurement;

        /**
         * Gets the value of the measurement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the measurement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMeasurement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TSensorMeasurement }
         * 
         * 
         */
        public List<TSensorMeasurement> getMeasurement() {
            if (measurement == null) {
                measurement = new ArrayList<TSensorMeasurement>();
            }
            return this.measurement;
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
     *         &lt;element name="process" type="{http://www.adapt4ee.eu/2012/schema/bpm/}tProcessType" maxOccurs="unbounded" minOccurs="0"/>
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
        "process"
    })
    public static class Processes {

        protected List<TProcessType> process;

        /**
         * Gets the value of the process property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the process property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProcess().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TProcessType }
         * 
         * 
         */
        public List<TProcessType> getProcess() {
            if (process == null) {
                process = new ArrayList<TProcessType>();
            }
            return this.process;
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
     *         &lt;element name="role" type="{http://www.adapt4ee.eu/2012/schema/bpm/}tRole" maxOccurs="unbounded" minOccurs="0"/>
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
        "role"
    })
    public static class Roles {

        protected List<TRole> role;

        /**
         * Gets the value of the role property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the role property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRole().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TRole }
         * 
         * 
         */
        public List<TRole> getRole() {
            if (role == null) {
                role = new ArrayList<TRole>();
            }
            return this.role;
        }

    }

}
