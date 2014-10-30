package eu.adapt4ee.cimim.ontology;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import eu.adapt4ee._2012.schema.event.TEvent;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"equipmentRef", 
    "event"
})
@XmlRootElement(name = "EquipmentEvents")
public class EquipmentEvents {
	
	protected String equipmentRef;
	
    @XmlElement(required = true)
    protected List<TEvent> event;
    
    public String getEquipmentRef() {
        return equipmentRef;
    }

    /**
     * Sets the value of the id property.
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
