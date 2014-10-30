/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.adapt4ee.cimim.ontology;

import eu.adapt4ee._2012.schema.bim.TBuilding;
import eu.adapt4ee._2012.schema.bim.TEquipment;
import eu.adapt4ee._2012.schema.bim.TSpace;
import eu.adapt4ee._2012.schema.bpm.TProcessType;
import eu.adapt4ee._2012.schema.bpm.TRole;
import eu.adapt4ee._2012.schema.bpm.TSkeletonActivityType;
import eu.adapt4ee._2012.schema.bpm.TSkeletonActivityType.RoleInvolved;
import eu.adapt4ee._2012.schema.bpm.TSkeletonActivityType.UsedEquipment;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import static eu.adapt4ee.cimim.ontology.Properties.NAMESPACE;

/**
 *
 * @author Peter Bednar
 */
public class OntologyMapper {

    private final URI context[];
    private final RepositoryConnection conn;
    private final ValueFactory factory;
    
    public OntologyMapper(RepositoryConnection conn, URI ... context) {
        this.conn = conn;
        this.context = context;
        this.factory = conn.getValueFactory();
    }
    
    public void apply(MeasurementFile mf) {
        // building
        apply(mf.getBuilding());
        // process types
        for (TProcessType process : mf.getProcesses().getProcess()) {
            apply(process);
        }
        // roles
        for (TRole role : mf.getRoles().getRole()) {
            apply(role);
        }
    }
    
    public void apply(TBuilding building) {
        for (TSpace space : building.getSpace()) {
            add(id(building), "#hasSpace", id(space));
            apply(space);
        }
    }
    
    public void apply(TSpace space) {
        if (space.getType() != null) {
            add(id(space), "#hasSpaceType", literal(space.getType().name()));
        }
        for (TEquipment equipment : space.getEquipments().getEquipmentDevice()) {
            add(id(space), "#hasEquipment", id(equipment));
            apply(equipment);
        }
    }
    
    public void apply(TEquipment equipment) {
        if (equipment.getType() != null) {
            add(id(equipment), "#hasEquimentType", literal(equipment.getType()));
        }
    }
    
    public void apply(TProcessType process) {
        for (TSkeletonActivityType activity : process.getActivity()) {
            add(id(process), "#hasActivity", id(activity));
            apply(activity);
        }
    }
    
    public void apply(TSkeletonActivityType activity) {
        for (RoleInvolved role : activity.getRoleInvolved()) {
            add(id(activity), "#hasInvoledRole", id(role));
        }
        for (UsedEquipment equipment : activity.getUsedEquipment()) {
            add(id(activity), "#hasUsedEquipmentType", id(equipment));
        }
    }
    
    public void apply(TRole role) {
        // empty for now (name property is used as the identifier)
    }
    
    private URI URI(String uri) {
        return factory.createURI(NAMESPACE + uri);
    }

    private Value literal(boolean value) {
        return factory.createLiteral(value);
    }
    
    private Value literal(int value) {
        return factory.createLiteral(value);
    }

    private Value literal(String value) {
        return factory.createLiteral(value);
    }
    
    private URI id(TBuilding building) {
        return URI("/building#" + encode(building.getId()));
    }
    
    private URI id(TSpace space) {
        return URI("/space#" + space.getId());
    }
    
    private URI id(TEquipment equipment) {
        return URI("/equipment#" + equipment.getId());
    }
    
    private URI id(TProcessType process) {
        return URI("/process#" + process.getId());
    }
    
    private URI id(TSkeletonActivityType activity) {
        return URI("/activity#" + activity.getId());
    }
    
    private URI id(RoleInvolved role) {
        return URI("/role#" + role.getRoleRef());
    }
    
    private URI id(UsedEquipment equipment) {
        return URI("/equipmentType#" + equipment.getEquipmentTypeRef().value());
    }
    
    private String encode(String uri) {
        try {
            return URLEncoder.encode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // ignored, UTF-8 should be always supported
        }
        return null;
    }
    
    private void add(URI subject, String predicate, Value object) {
        try {
            Statement stmt = factory.createStatement(subject, URI(predicate), object);
            conn.add(stmt, context);
        } catch (RepositoryException re) {
            throw new OntologyException(re);
        }
    }
    
}
