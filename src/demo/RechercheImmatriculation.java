package demo;

import java.sql.*;
import myconnections.DBConnection;
import java.util.*;

public class RechercheImmatriculation {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        System.out.println("Quelle immatriculation ? : ");
        String immrech = sc.nextLine();

        try (Statement stmt = dbConnect.createStatement();
             ResultSet rs = stmt.executeQuery(
                        "SELECT *  FROM PROJ_TAXI WHERE IMMATRICULATION = '"
                        + immrech +  "'");) {
            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                int n = rs.getInt("IDTAXI");
                String carburant = rs.getString("CARBURANT");
                float prix = rs.getFloat("PRIXKM");
                String desc =  rs.getString("DESCRIPTION");
                System.out.println("Coordonnées du taxi ayant pour immatriculation "+immrech + ":" + "ID :"+n + "  Carburant:  " + carburant+" Prix au Km: "+prix+" Description: "+desc);
            }
            if (!trouve) {
                System.out.println("Immatriculation inconnue ");
            }

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        RechercheImmatriculation pgm = new RechercheImmatriculation();
        pgm.demo();
    }
}
