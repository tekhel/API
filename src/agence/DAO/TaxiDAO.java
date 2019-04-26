package agence.DAO;

/**
 * classe de mappage poo-relationnel taxi
 *
 * @author Linda TEMGOUA
 * @version 1.0
 * @see Taxi
 */
import java.sql.*;
//import java.time.LocalDate;
import java.util.*;
import agence.metier.Taxi;

public abstract class TaxiDAO extends DAO<Taxi> {

    /**
     * création d'un taxi sur base des valeurs de son objet métier
     *
     * @throws SQLException erreur de création
     * @param obj client à créer
     * @return client créé
     */
    @Override
    public Taxi create(Taxi obj) throws SQLException {

        String req1 = "insert into proj_taxi(idtaxi, immatriculation, carburant, prixkm, description) values(?,?,?,?,?)";
        String req2 = "select idtaxi from proj_taxi where immatriculation=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setInt(1, obj.getIdtaxi());
            pstm1.setString(2, obj.getImmatriculation());
            pstm1.setString(3, obj.getCarburant());
            pstm1.setFloat(4, obj.getPrixkm());
            pstm1.setString(5, obj.getDescription());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation taxi, aucune ligne créée");
            }
            pstm2.setString(1, obj.getImmatriculation());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    String nimm = rs.getString(1);
                    obj.setImmatriculation(nimm);
                    return read(nimm);
                } else {
                    throw new SQLException("erreur de création client, record introuvable");
                }
            }
        }
    }

    /**
     * récupération des données d'un client sur base de son identifiant
     *
     * @throws SQLException code inconnu
     * @param nimm numero d'immatriculation du taxi
     * @return taxi trouvé
     */
    @Override
    public Taxi read(String nimm) throws SQLException {

        String req = "select * from proj_taxi where lower(immatriculation) = ?";//lower inspirée du code de Walid

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setString(1, nimm);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int num = rs.getInt("IDTAXI");
                    String carb = rs.getString("CARBURANT");
                    Float prix = rs.getFloat("PRIXKM");
                    String desc = rs.getString("DESCRIPTION");
                    return new Taxi(num, nimm, carb, prix, desc);

                } else {
                    throw new SQLException("Code inconnu");
                }

            }
        }
    }

    /**
     * mise à jour des données du taxi sur base de son immatriculation
     *
     * @return Taxi
     * @param obj Taxi à mettre à jour
     * @throws SQLException erreur de mise à jour
     */
    @Override
    public Taxi update(Taxi obj) throws SQLException {
        String req = "update proj_taxi set idtaxi=?,carburant=?,prixkm=?,description=? where immatriculation= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(1, obj.getIdtaxi());
            pstm.setString(2, obj.getCarburant());
            pstm.setFloat(3, obj.getPrixkm());
            pstm.setString(4, obj.getDescription());
            pstm.setString(5, obj.getImmatriculation());
            int n = pstm.executeUpdate();
            System.out.println("nombre de lignes mises à jour = " + n);
            if (n == 0) {
                throw new SQLException("aucune ligne taxi mise à jour");
            }
            return read(obj.getImmatriculation());
        }
    }

    /**
     * effacement d'un taxi sur base de son immatriculation
     *
     * @throws SQLException erreur d'effacement
     * @param obj client à effacer
     */
    @Override
    public void delete(Taxi obj) throws SQLException {

        String req1 = "delete from proj_taxi where UPPER(immatriculation)= ?";
        String req2 = "delete from proj_location where idtaxi= ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {

            pstm1.setString(1, obj.getImmatriculation());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                //throw new SQLException("aucune ligne client effacée");
            }
            pstm2.setInt(1, obj.getIdtaxi());
            int m = pstm2.executeUpdate();
            if (m == 0) {
               // throw new SQLException("aucune ligne client effacée");
            }

        }
    }

    /**
     * méthode permettant de récupérer tous les taxi portant un
     * certaine description
     * @param descrech nom recherché
     * @return liste de taxis
     * @throws SQLException nom inconnu
     */
    public List<Taxi> rechercheDesc(String descrech) throws SQLException {
        List<Taxi> plusieurs = new ArrayList<>();
        String req = "select * from proj_taxi where lower(description) like ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setString(1, "%"+descrech+"%");
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int num = rs.getInt("IDTAXI");
                    String nimm = rs.getString("IMMATRICULATION");
                    String carb = rs.getString("CARBURANT");
                    Float prix = rs.getFloat("PRIXKM");
                    String desc = rs.getString("DESCRIPTION");
                    plusieurs.add(new Taxi(num, nimm, carb, prix, desc));
                }

                if (!trouve) {
                    throw new SQLException("nom inconnu");
                } else {
                    return plusieurs;
                }
            }
        }
        
        
    }
   

}
