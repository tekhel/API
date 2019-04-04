package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import myconnections.DBConnection;

public class ModifImm {
     public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "update proj_taxi set idtaxi = ?, carburant = ?, prixkm = ?, description = ?  where immatriculation=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {

            System.out.print("Numero d'immatriculation dont les informations doivent changer:");
            String nimm = sc.nextLine();
            System.out.print("Nouvel ID :");
            int num = sc.nextInt();
            sc.skip("\n");
            System.out.println("Carburant: ");
            String carb = sc.nextLine();
            System.out.println("Prix : ");
            float prix = sc.nextFloat(); 
            sc.skip("\n");
            System.out.println("Description : ");
            String desc = sc.nextLine();
            pstm.setString(5, nimm);
            pstm.setInt(1, num);
            pstm.setString(2, carb);
            pstm.setFloat(3, prix);
            pstm.setString(4, desc);
            int nl = pstm.executeUpdate();
            System.out.println("nombre de lignes mises à jour = " + nl);

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }

        DBConnection.closeConnection();
    }

    public static void main(String args[]) {
        ModifImm pgm = new ModifImm();
        pgm.demo();
    }
    
}
