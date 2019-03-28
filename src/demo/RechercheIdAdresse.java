package demo;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.*;
import myconnections.DBConnection;
import java.util.*;
import java.util.Date;

public class RechercheIdAdresse {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        System.out.println("Quelle id de location ? : ");
        int idrech = sc.nextInt();

        try (Statement stmt = dbConnect.createStatement();
             ResultSet rs = stmt.executeQuery(
                        "select ad.idadr, ad.cp, ad.localite, ad.rue, ad.num, loc.idloc, loc.dateloc from PROJ_ADRESSE ad, PROJ_LOCATION loc where idadr= id_adr_deb or idadr = id_adr_fin = '"
                        + idrech +  "'");) {
            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                int n = rs.getInt("IDADR");
                int codePostal = rs.getInt("CP");
                String local = rs.getString("LOCALITE");
                String nomRue =  rs.getString("RUE");
                int numRue =  rs.getInt("NUM");
                int idLocation = rs.getInt("IDLOC");
                Date dateLocation = rs.getDate("DATELOC");
                System.out.println("Coordonnées de la location "+idrech + ":" + "IDADR :"+n + "  Code postal:  " + codePostal+" Localité: "+local+" Rue: "+nomRue+"Numéro: "+numRue+" Id location: "+idLocation+" Date Location: "+dateLocation);
            }
            if (!trouve) {
                System.out.println("Id inconnue ");
            }

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        RechercheIdAdresse pgm = new RechercheIdAdresse();
        pgm.demo();
    }
}
