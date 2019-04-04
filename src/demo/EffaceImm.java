package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import myconnections.DBConnection;

public class EffaceImm {
    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");

        System.out.print("N° d'immatriculation à effacer : ");
        String imm = sc.nextLine();
        String query1 = "DELETE FROM PROJ_TAXI WHERE IMMATRICULATION = ?";
        String query2 = "DELETE FROM PROJ_LOCATION WHERE IDTAXI = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {

            pstm1.setString(1, imm);
            int nl = pstm1.executeUpdate();
            System.out.println(nl + " lignes effacees Dans la table PROJ_TAXI");

            pstm2.setString(1, imm);
            nl = pstm2.executeUpdate();
            System.out.println(nl + " lignes effacees Dans la table PROJ_LOCATION");

        } catch (SQLException e) {
            System.out.println("erreur " + e);
        }
         DBConnection.closeConnection();
    }

    public static void main(String[] args) {

        EffaceImm pgm = new EffaceImm();
        pgm.demo();

    }
    
}
