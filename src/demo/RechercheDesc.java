/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import myconnections.DBConnection;

/**
 *
 * @author Linda TEMGOUA
 */
public class RechercheDesc {
    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        try (PreparedStatement pstm = dbConnect.prepareStatement("SELECT * FROM PROJ_TAXI WHERE lower(DESCRIPTION) LIKE ? ")) {
            System.out.print("Entrez une description :");
            String descrech = sc.nextLine().toLowerCase();
            pstm.setString(1,"%"+ descrech +"%"); //IGNORE_CASE ?????
            boolean trouve = false;
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    trouve = true;
                    int num = rs.getInt("IDTAXI");
                    String imm = rs.getString("IMMATRICULATION");
                    String carburant = rs.getString("CARBURANT");
                    float prix = rs.getFloat("PRIXKM");
                    String desc = rs.getString("DESCRIPTION");
                    System.out.println(num+" "+imm+" "+carburant+" "+prix+" "+desc);
                }
                if (!trouve) {
                    System.out.println("description inconnue");
                }

            }

        } 
        catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        DBConnection.closeConnection();

    }

    public static void main(String[] args) {
        RechercheDesc pgm = new RechercheDesc();
        pgm.demo();
    }
    
}
