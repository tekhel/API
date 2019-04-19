/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agence.DAO;

import java.sql.Connection;
import java.sql.SQLException;
//import java.time.LocalDate;
import agence.metier.Taxi;
import myconnections.DBConnection;
import java.util.List;
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
        DBConnection.closeConnection();
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
        TaxiDAO instance = new TaxiDAO() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        instance.setConnection(dbConnect);
        
        Taxi obj = new Taxi(0, "Immatriculation", "Carburant", 1, "Description");
        Taxi expResult = new Taxi(0, "Immatriculation", "Carburant", 1, "Description");
        Taxi result = instance.create(obj);

        assertEquals("Immatriculations différentes", expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals("Carburants différents", expResult.getCarburant(), result.getCarburant());
        assertEquals("Prix au km différents", expResult.getPrixkm(), result.getPrixkm(), 0);
        assertEquals("Descriptions différentes", expResult.getDescription(), result.getDescription());
        assertNotEquals("Idtaxi restée a 0", result.getIdtaxi(), expResult.getIdtaxi());

        //Test of creating an existing object
        obj = new Taxi(0, "Immatriculation2", "Carburant2", 2, "Description2");
        try {
            Taxi resultat_2 = instance.create(obj);
            fail("Exception de doublon non générée");
            instance.delete(resultat_2);
        } catch (SQLException e) {
            instance.delete(result);
        }

        //Test of creating an object with incorrect "Prixkm"
        obj = new Taxi(0, "Immatriculation", "Carburant2", -1, "Description2");
        try {
            Taxi resultat_3 = instance.create(obj);
            fail("Exception de check du prix au km non déclenchée");
            instance.delete(resultat_3);
        } catch (SQLException e) {

        }
    }

    /**
     * Test of read method, of class TaxiDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        String immatriculation = "";
        TaxiDAO instance = new TaxiDAO() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        instance.setConnection(dbConnect);
        Taxi obj = new Taxi(0, "Immatriculation", "Carburant", 1, "Description");
        Taxi expResult = instance.create(obj);
        immatriculation = expResult.getImmatriculation();
        Taxi result = instance.read(immatriculation);
        assertEquals("Id de taxi différents", expResult.getIdtaxi(), result.getIdtaxi());
        assertEquals("Immatriculations différentes", expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals("Carburants différents", expResult.getCarburant(), result.getCarburant());
        assertEquals("Prix au km différents", expResult.getPrixkm(), result.getPrixkm(), 0);
        assertEquals("Descriptions différentes", expResult.getDescription(), result.getDescription());

        try {
            result = instance.read(0);
            fail("Exception d'id inconnu non générée! ");
        } catch (SQLException e) {
            instance.delete(result);
        }
    }

    /**
     * Test of update method, of class TaxiDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        TaxiDAO instance = new TaxiDAO() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        instance.setConnection(dbConnect);
        Taxi obj = new Taxi(0, "Immatriculation", "Carburant", 1, "Description");
        obj = instance.create(obj);
        obj.setImmatriculation("Immatriculation2");
        obj.setCarburant("Carburant2");
        obj.setPrixkm(2);
        obj.setDescription("Description2");

        Taxi expResult = obj;
        Taxi result = instance.update(obj);

        assertEquals("Id de taxi différents", expResult.getIdtaxi(), result.getIdtaxi());
        assertEquals("Immatriculations différentes", expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals("Carburants différents", expResult.getCarburant(), result.getCarburant());
        assertEquals("Prix au km différents", expResult.getPrixkm(), result.getPrixkm(), 0);
        assertEquals("Descriptions différentes", expResult.getDescription(), result.getDescription());

        instance.delete(obj);

        //Test of an update based on an existing object
        Taxi obj1 = new Taxi(0, "Immatriculation", "Carburant", 1, "Description");
        obj1 = instance.create(obj1);
        Taxi obj2 = new Taxi(0, "Immatriculation", "Carburant", 1, "Description");
        obj2 = instance.create(obj2);

        obj1.setImmatriculation("Immatriculation2");

        try {
            instance.update(obj1);
            fail("Exception de doublon non générée");
        } catch (SQLException e) {
            instance.delete(obj1);
            instance.delete(obj2);
        }

        Taxi obj3 = new Taxi(0, "Immatriculation", "Carburant", 1, "Description");
        obj3 = instance.create(obj3);

        obj3.setPrixkm(-1);

        try {
            instance.update(obj3);
            fail("Exception de check du prix au km non déclanchée");
        } catch (SQLException e) {
            instance.delete(obj3);
        }
    }

    /**
     * Test of delete method, of class TaxiDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        TaxiDAO instance = new TaxiDAO() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        instance.setConnection(dbConnect);
        Taxi obj = new Taxi(0, "Immatriculation", "Carburant", 1, "Description");
        obj = instance.create(obj);
        int idtaxi = obj.getIdtaxi();
        instance.delete(obj);
        try {
            instance.read(idtaxi);
            fail("Exception de record introuvable non générée");
        } catch (SQLException e) {
            
        }    }

    /**
     * Test of rechercheDesc method, of class TaxiDAO.
     */
    @Test
    public void testRechercheDesc() throws Exception {
        System.out.println("rechercheDesc");
        String descrech = "";
        TaxiDAO instance = new TaxiDAO() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        List<Taxi> expResult = null;
        List<Taxi> result = instance.rechercheDesc(descrech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    //public class TaxiDAO extends TaxiDAO {
    //}
    
}
