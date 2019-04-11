/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agence.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import agence.metier.Taxi;
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
        DBConnection.closeConnection();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class TaxiDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Taxi obj = new Taxi(0,"TestImmatriculation","TestCarburant",1,"TestDescription");
        TaxiDAO instance = new TaxiDAO() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }  ;
        instance.setConnection(dbConnect);
        Taxi expResult = new Taxi(0,"TestImmatriculation","TestCarburant",1,"TestDescription");
        Taxi result = instance.create(obj);
        
        assertEquals("Immatriculations différents",expResult.getImmatriculation(), result.getImmatriculation());
        assertEquals("Carburants différents",expResult.getCarburant(), result.getCarburant());
        assertEquals("Descriptions différentes",expResult.getDescription(), result.getDescription());
        
        //assertNotEquals("id non généré",expResult.getIdtaxi(),result.getIdtaxi());
        //int idtaxi=result.getIdtaxi;
        obj=new Taxi(0,"TestImmatriculation","TestCarburant",2,"TestDescription");
        try{
            Taxi result2 = instance.create(obj);
            fail("exception de doublon non déclenchée");
            instance.delete(result2);
        }
        catch(SQLException e){}
        instance.delete(result);
        
          obj=new Taxi(0,"TestImmatriculation","TestCarburant",3,"TestDescription");
        try{
            Taxi result3 = instance.create(obj);
            fail("exception de code postal non déclenchée");
            instance.delete(result3);
        }
        catch(SQLException e){}
       
    }

    /**
     * Test of read method, of class TaxiDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        String nimm = "";
        TaxiDAO instance = new TaxiDAOImpl() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        Taxi expResult = null;
        Taxi result = instance.read(nimm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class TaxiDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Taxi obj = null;
        TaxiDAO instance = new TaxiDAOImpl() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        Taxi expResult = null;
        Taxi result = instance.update(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class TaxiDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Taxi obj = null;
        TaxiDAO instance = new TaxiDAOImpl() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rechercheDesc method, of class TaxiDAO.
     */
    @Test
    public void testRechercheDesc() throws Exception {
        System.out.println("rechercheDesc");
        String descrech = "";
        TaxiDAO instance = new TaxiDAOImpl() {
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

    public abstract class TaxiDAOImpl extends TaxiDAO {
    }
    
}
