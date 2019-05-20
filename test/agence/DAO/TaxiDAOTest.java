/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agence.DAO;

import agence.metier.Taxi;
import agence.metier.Vue_adresse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import myconnections.DBConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author linda
 */
public class TaxiDAOTest {
    
    static Connection dbConnect;
    
    public TaxiDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }
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
     * Test of create method, of class TaxiDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        TaxiDAO instance = new TaxiDAO();
        instance.setConnection(dbConnect);
        Taxi obj = new Taxi(0, "ImmaTest", "Carb", 1, "DescriTest");
        Taxi expResult = new Taxi(0, "ImmaTest", "Carb", 1, "DescriTest");
        Taxi result = instance.create(obj);

        assertEquals("Immatriculations différentes", expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals("Carburants différents", expResult.getCarburant(), result.getCarburant());
        assertEquals("Prix au km différents", expResult.getPrixkm(), result.getPrixkm(), 0);
        assertEquals("Descriptions différentes", expResult.getDescription(), result.getDescription());
        assertNotEquals("Idtaxi restée a 0", result.getIdtaxi(), expResult.getIdtaxi());

        //Test of creating an existing object
        obj = new Taxi(0, "ImmaTest", "Car2", 2, "DescriTest2");
        try {
            Taxi resultat_2 = instance.create(obj);
            fail("Exception de doublon non générée");
            instance.deleteSeul(resultat_2);
        } catch (SQLException e) {
            instance.deleteSeul(result);
        }

        //Test of creating an object with incorrect "Prixkm"
        obj = new Taxi(0, "ImmaTest", "Car2", -1, "DescriTest2");
        try {
            Taxi resultat_3 = instance.create(obj);
            fail("Exception de check du prix au km non déclanchée");
            instance.deleteSeul(resultat_3);
        } catch (SQLException e) {

        }
    }

    /**
     * Test of read method, of class TaxiDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int num = 0;
        TaxiDAO instance = new TaxiDAO();
        instance.setConnection(dbConnect);
        Taxi obj = new Taxi(0, "ImmaTest", "Carb", 1, "DescriTest");
        Taxi expResult = instance.create(obj);
        num = expResult.getIdtaxi();
        Taxi result = instance.read(num);
        assertEquals("Id de taxi différents", expResult.getIdtaxi(), result.getIdtaxi());
        assertEquals("Immatriculations différentes", expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals("Carburants différents", expResult.getCarburant(), result.getCarburant());
        assertEquals("Prix au km différents", expResult.getPrixkm(), result.getPrixkm(), 0);
        assertEquals("Descriptions différentes", expResult.getDescription(), result.getDescription());

        try {
            result = instance.read(0);
            fail("Exception d'id inconnu non générée! ");
        } catch (SQLException e) {
            instance.deleteSeul(result);
        }
    }

    /**
     * Test of update method, of class TaxiDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        TaxiDAO instance = new TaxiDAO();
        instance.setConnection(dbConnect);
        Taxi obj = new Taxi(0, "ImmaTest", "Carb", 1, "DescriTest");
        obj = instance.create(obj);
        obj.setImmatriculation("ImmaTest2");
        obj.setCarburant("Car2");
        obj.setPrixkm(2);
        obj.setDescription("DescriTest2");

        Taxi expResult = obj;
        Taxi result = instance.update(obj);

        assertEquals("Id de taxi différents", expResult.getIdtaxi(), result.getIdtaxi());
        assertEquals("Immatriculations différentes", expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals("Carburants différents", expResult.getCarburant(), result.getCarburant());
        assertEquals("Prix au km différents", expResult.getPrixkm(), result.getPrixkm(), 0);
        assertEquals("Descriptions différentes", expResult.getDescription(), result.getDescription());

        instance.deleteSeul(obj);

        //Test of an update based on an existing object
        Taxi obj1 = new Taxi(0, "ImmaTest", "Carb", 1, "DescriTest");
        obj1 = instance.create(obj1);
        Taxi obj2 = new Taxi(0, "ImmaTest2", "Carb", 1, "DescriTest");
        obj2 = instance.create(obj2);

        obj1.setImmatriculation("ImmaTest2");

        try {
            instance.update(obj1);
            fail("Exception de doublon non générée");
        } catch (SQLException e) {
            instance.deleteSeul(obj1);
            instance.deleteSeul(obj2);
        }

        //test of an update based on an incorrect CP
        Taxi obj3 = new Taxi(0, "ImmaTest", "Carb", 1, "DescriTest");
        obj3 = instance.create(obj3);

        obj3.setPrixkm(-1);

        try {
            instance.update(obj3);
            fail("Exception de check du prix au km non déclanchée");
        } catch (SQLException e) {
            instance.deleteSeul(obj3);
        }
    }

    /**
     * Test of delete method, of class TaxiDAO.
     */
    //@Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Taxi obj = null;
        TaxiDAO instance = new TaxiDAO();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSeul method, of class TaxiDAO.
     */
    @Test
    public void testDeleteSeul() throws Exception {
        System.out.println("deleteSeul");
        TaxiDAO instance = new TaxiDAO();
        instance.setConnection(dbConnect);
        Taxi obj = new Taxi(0, "ImmaTest", "Carb", 1, "DescriTest");
        obj = instance.create(obj);
        int idtaxi = obj.getIdtaxi();
        instance.deleteSeul(obj);
        try {
            instance.read(idtaxi);
            fail("Exception de record introuvable non générée");
        } catch (SQLException e) {
            
        }
    }

    /**
     * Test of rechercheDesc method, of class TaxiDAO.
     */
    //@Test
    public void testRechercheDesc() throws Exception {
        System.out.println("rechercheDesc");
        String descrech = "";
        TaxiDAO instance = new TaxiDAO();
        List<Taxi> expResult = null;
        List<Taxi> result = instance.rechercheDesc(descrech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rechercheLocations method, of class TaxiDAO.
     */
    //@Test
    public void testRechercheLocations() throws Exception {
        System.out.println("rechercheLocations");
        Taxi obj = null;
        TaxiDAO instance = new TaxiDAO();
        int expResult = 0;
        int result = instance.rechercheLocations(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rechImma method, of class TaxiDAO.
     */
    //@Test
    public void testRechImma() throws Exception {
        System.out.println("rechImma");
        String nimm = "";
        TaxiDAO instance = new TaxiDAO();
        Taxi expResult = null;
        Taxi result = instance.rechImma(nimm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rechloc method, of class TaxiDAO.
     */
    //@Test
    public void testRechloc() throws Exception {
        System.out.println("rechloc");
        int idloc = 0;
        TaxiDAO instance = new TaxiDAO();
        List<Vue_adresse> expResult = null;
        List<Vue_adresse> result = instance.rechloc(idloc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prixTotalLoc method, of class TaxiDAO.
     */
    //@Test
    public void testPrixTotalLoc() throws Exception {
        System.out.println("prixTotalLoc");
        int idloc = 0;
        TaxiDAO instance = new TaxiDAO();
        float expResult = 0.0F;
        float result = instance.prixTotalLoc(idloc);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
