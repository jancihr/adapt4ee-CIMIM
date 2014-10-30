/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.om.repository;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Peter Bednar
 */
public class OMRepositoryTest {
    
    @Test
    public void test1() {
        System.setProperty("ontologies.dir", "ontology");
        OMRepository rep = RepositoryFactory.owlim("repository");
        rep.load();
        rep.close();
    }
    
}
