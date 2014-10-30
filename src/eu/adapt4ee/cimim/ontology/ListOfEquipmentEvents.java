package eu.adapt4ee.cimim.ontology;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"equipmentEvents", 
})
@XmlRootElement(name = "ListOfEquipmentEvents")
public class ListOfEquipmentEvents {
	
    @XmlElement(required = true)
    protected List<EquipmentEvents> equipmentEvents;
    

    public List<EquipmentEvents> getEquipmentEventsEvent() {
        if (equipmentEvents == null) {
        	equipmentEvents = new ArrayList<EquipmentEvents>();
        }
        return this.equipmentEvents;
    }

}
