/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.adapt4ee.cimim.ontology;

import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;
import eu.om.repository.OMRepository;
import eu.om.repository.RepositoryFactory;
import java.io.FileInputStream;
import org.junit.Test;

/**
 *
 * @author Peter Bednar
 */
public class OntologyMapperTest {
    
    @Test
    public void test1() throws Exception {
        OMRepository rep = RepositoryFactory.owlim("tmp/repository");
        OntologyMapper map = new OntologyMapper(rep.getConnection());
        
        MeasurementFile measurementFile = StorageUtils.unserializeDocument(
				new FileInputStream("test/xml/measurementfile.xml"),
				MeasurementFile.class);
        
        rep.clear();
        map.apply(measurementFile);
        rep.list();
        rep.close();
    }
    
}
