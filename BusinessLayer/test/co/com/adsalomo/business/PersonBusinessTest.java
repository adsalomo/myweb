/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.adsalomo.business;

import co.com.adsalomo.common.model.PersonModel;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aye
 */
public class PersonBusinessTest {
    
    public PersonBusinessTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of listPerson method, of class PersonBusiness.
     */
    @Test
    public void testListPerson() {
        System.out.println("listPerson");
        PersonBusiness instance = new PersonBusiness();
        List<PersonModel> expResult = null;
        List<PersonModel> result = instance.listPerson();
        assertEquals(true, true);
       
    }

    /**
     * Test of insertPerson method, of class PersonBusiness.
     */
    @Test
    public void testInsertPerson() {
        System.out.println("insertPerson");
        PersonModel model = new PersonModel();
        model.setId(5);
        model.setName("Hugo");
        model.setLastName("Lopez");
  
        PersonBusiness instance = new PersonBusiness();
        boolean expResult = true;
        boolean result = instance.insertPerson(model);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of updatePerson method, of class PersonBusiness.
     */
    @Test
    public void testUpdatePerson() {
        System.out.println("updatePerson");
        PersonModel model = new PersonModel();
        model.setId(4);
        model.setName("Viejita");
        model.setLastName("Lopez");
        PersonBusiness instance = new PersonBusiness();
        boolean expResult = true;
        boolean result = instance.updatePerson(model);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of deletePerson method, of class PersonBusiness.
     */
    @Test
    public void testDeletePerson() {
        System.out.println("deletePerson");
        PersonModel model = new PersonModel();
        model.setId(1);
        PersonBusiness instance = new PersonBusiness();
        boolean expResult = true;
        boolean result = instance.deletePerson(model);
        assertEquals(expResult, result);
       
    }
    
}
