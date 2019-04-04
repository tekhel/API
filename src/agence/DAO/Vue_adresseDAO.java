package agence.DAO;

/**
 *
 * @author Linda TEMGOUA
 * @version 1.0
 */
import java.sql.*;
//import java.time.LocalDate;
import java.util.*;
import agence.metier.Vue_adresse;


public abstract class Vue_adresseDAO extends DAO<Vue_adresse> {

   // @Override
    public Vue_adresse read(int idloc) throws SQLException {

        String req = "select * from vue_adresse where idlocation = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, idloc);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int iddep = rs.getInt("ADRESSE DEPART");
                    int idarr = rs.getInt("ADRESSE FIN");
                    //Date dateloc = rs.getFloat("PRIXKM");
                    Float kmtot = rs.getFloat("KMTOTAL");
                    Float acompte = rs.getFloat("ACOMPTE");
                    return new Vue_adresse(idloc, iddep, idarr, kmtot, acompte);

                } else {
                    throw new SQLException("Code inconnu");
                }

            }
        }
    }
}

   