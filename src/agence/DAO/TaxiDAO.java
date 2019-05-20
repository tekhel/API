package agence.DAO;

/**
 * classe de mappage poo-relationnel taxi
 *
 * @author Linda TEMGOUA
 * @version 1.0
 * @see Taxi
 */
import java.sql.*;
import java.util.*;
import agence.metier.Taxi;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import agence.metier.Vue_adresse;

public class TaxiDAO extends DAO<Taxi> {

    /**
     * création d'un taxi sur base des valeurs de son objet métier
     *
     * @throws SQLException erreur de création
     * @param obj client à créer
     * @return client créé
     */
    @Override
    public Taxi create(Taxi obj) throws SQLException {

        String req1 = "insert into proj_taxi(immatriculation, carburant, prixkm, description)" + "values(?,?,?,?)";
        String req2 = "select IDTAXI from proj_taxi where IMMATRICULATION=? and CARBURANT=? and PRIXKM=? and DESCRIPTION=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            //pstm1.setInt(1, obj.getIdtaxi());
            pstm1.setString(1, obj.getImmatriculation());
            pstm1.setString(2, obj.getCarburant());
            pstm1.setFloat(3, obj.getPrixkm());
            pstm1.setString(4, obj.getDescription());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation taxi, aucune ligne créée");
            }
           System.out.println(n + "ligne insérée");
            pstm2.setString(1, obj.getImmatriculation());
            pstm2.setString(2, obj.getCarburant());
            pstm2.setFloat(3, obj.getPrixkm());
            pstm2.setString(4, obj.getDescription());

            try (ResultSet rs = pstm2.executeQuery()) {

                if (rs.next()) {
                    int num = rs.getInt(1);
                    return read(num);

                } else {
                    throw new SQLException("erreur de création de taxi, record introuvable");
                }

            }
        }
    }

    /**
     * récupération des données d'un client sur base de son identifiant
     *
     * @throws SQLException code inconnu
     * @param numm identifiant du taxi
     * @return taxi trouvé
     */
    @Override
    public Taxi read(int num) throws SQLException {

        String req = "select * from proj_taxi where idtaxi = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, num);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String nimm = rs.getString("IMMATRICULATION");
                    String carb = rs.getString("CARBURANT");
                    float prix = rs.getFloat("PRIXKM");
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
        String req = "update proj_taxi set immatriculation=?,carburant=?,prixkm=?,description=? where idtaxi= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setString(1, obj.getImmatriculation());
            pstm.setString(2, obj.getCarburant());
            pstm.setFloat(3, obj.getPrixkm());
            pstm.setString(4, obj.getDescription());
            pstm.setInt(5, obj.getIdtaxi());
            int n = pstm.executeUpdate();
            System.out.println("nombre de lignes mises à jour = " + n);
            if (n == 0) {
                throw new SQLException("aucune ligne taxi mise à jour");
            }
            return read(obj.getIdtaxi());
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

            pstm2.setInt(1, obj.getIdtaxi());
            int n = pstm2.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne location effacée");
            }
            System.out.println(n+" lignes de locations supprimées");
            pstm1.setString(1, obj.getImmatriculation());
            int nl = pstm1.executeUpdate();
            if (nl == 0) {
                throw new SQLException("aucun taxi effacé");
            }
            System.out.println("Taxi suprimé");
        }
    }
      /**
     * effacement du taxi sur base de son identifiant
     *
     * @throws SQLException erreur d'effacement de taxi
     * @param obj API_TAXI à effacer
     */
    public void deleteSeul(Taxi obj) throws SQLException{
        String query1 = "DELETE FROM PROJ_TAXI WHERE IDTAXI = ?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query1)){
            pstm.setInt(1, obj.getIdtaxi());
            int nl = pstm.executeUpdate();
            if (nl == 0) {
                throw new SQLException("aucun taxi effacé");
            }
            System.out.println("Taxi supprimé");
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
                    throw new SQLException("description inconnu");
                } else {
                    return plusieurs;
                }
            }
        }
        
        
    }
    /**
     * méthode permettant de récupérer toutes les locations ayant un certain id_taxi
     *
     * @param obj Taxi recherché
     * @return entier variant en fonction de la recherche de location
     */
    public int rechercheLocations(Taxi obj) throws SQLException{
        String query1="select * from proj_location where idtaxi=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query1)){
            pstm.setInt(1, obj.getIdtaxi());
            
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Il existe une ou plusieurs locations liées à ce taxi");
                    return 1;

                } else {
                    System.out.println("Il n'existe aucune location liée à ce taxi");
                    System.out.println("Taxi supprimé");
                    return 0;
                }

            }
        }
    }
   
/**
     * récupération des données d'un taxi sur base de son immatriculation
     *
     * @throws SQLException immatriculation inconnue
     * @param imma immatriculation du taxi
     * @return API_TAXI trouvé
     */

    public Taxi rechImma(String nimm) throws SQLException{
        String req= "select * from proj_taxi where immatriculation=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(req)){
            pstm.setString(1, nimm);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int num=rs.getInt("IDTAXI");
                    String carb = rs.getString("CARBURANT");
                    float prix = rs.getFloat("PRIXKM");
                    String desc = rs.getString("DESCRIPTION");
                    return new Taxi(num, nimm, carb, prix, desc);

                } else {
                    throw new SQLException("Immatriculation inconnue");
                }

            }
        }
    }
    
       public List<Vue_adresse> rechloc(int idloc) throws SQLException{
        List<Vue_adresse> loca = new ArrayList<>();
        String req = "select * from vue_adresse where idlocation = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(1,idloc);
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    LocalDate dateloc = rs.getDate("DATELOC").toLocalDate();
                    int kmtotal = rs.getInt("KMTOTAL");
                    Float acompte = rs.getFloat("ACOMPTE");
                    Float total = rs.getFloat("TOTAL");
                    int idclient=rs.getInt("IDCLIENT");
                    int idtaxi=rs.getInt("IDTAXI");
                    int cp=rs.getInt("CP");
                    String localite = rs.getString("LOCALITE");
                    String rue = rs.getString("RUE");
                    String num = rs.getString("NUM");
                    int cp_retour=rs.getInt("cp_retour");
                    String localite_retour = rs.getString("loc_retour");
                    String rue_retour = rs.getString("rue_retour");
                    String num_retour = rs.getString("num_retour");
                    loca.add(new Vue_adresse(idloc,dateloc,kmtotal,acompte,total,idclient,idtaxi,cp,localite,rue,num,cp_retour,localite_retour,rue_retour,num_retour));
                }

                if (!trouve) {
                    throw new SQLException("idlocation inconnu");
                } else {
                    return loca;
                }
            }
        }
    }
       public float prixTotalLoc(int idloc) throws SQLException{
        String req = "select * from vue_prixtotal where idloc = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, idloc);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Float prixtot=rs.getFloat("TOTAL");
                    return prixtot;

                } else {
                    throw new SQLException("Id inconnu");
                }

            }
        }
    }
}
