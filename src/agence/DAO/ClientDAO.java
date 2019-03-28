package agence.DAO;

/**
 * classe de mappage poo-relationnel client
 *
 * @author Michel Poriaux
 * @version 1.0
 * @see Client
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import agence.metier.Client;

public class ClientDAO extends DAO<Client> {

    /**
     * création d'un client sur base des valeurs de son objet métier
     *
     * @throws SQLException erreur de création
     * @param obj client à créer
     * @return client créé
     */
    @Override
    public Client create(Client obj) throws SQLException {

        String req1 = "insert into proj_client(nom,prenom,tel, idadr) values(?,?,?,?)";
        String req2 = "select idclient from proj_client where nom=? and prenom=? and tel=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, obj.getNom());
            pstm1.setString(2, obj.getPrenom());
            pstm1.setString(3, obj.getTel());
            pstm1.setInt(4, obj.getIdadresse());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation client, aucune ligne créée");
            }
            pstm2.setString(1, obj.getNom());
            pstm2.setString(2, obj.getPrenom());
            pstm2.setString(3, obj.getTel());
            //pstm2.setInt(4, obj.getIdadresse());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idclient = rs.getInt(1);
                    obj.setIdclient(idclient);
                    return read(idclient);
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
     * @param idclient identifiant du client
     * @return client trouvé
     */
    @Override
    public Client read(int idclient) throws SQLException {

        String req = "select * from api_client where idclient = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, idclient);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    String tel = rs.getString("TEL");
                    int idadresse = rs.getInt("IDADR");
                    return new Client(idclient, nom, prenom, tel, idadresse);

                } else {
                    throw new SQLException("Code inconnu");
                }

            }
        }
    }

    /**
     * mise à jour des données du client sur base de son identifiant
     *
     * @return Client
     * @param obj client à mettre à jour
     * @throws SQLException erreur de mise à jour
     */
    @Override
    public Client update(Client obj) throws SQLException {
        String req = "update api_client set nom=?,prenom=?,cp=?,localite=?,rue=?,num=?,tel=? where idclient= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(5, obj.getIdclient());
            pstm.setString(1, obj.getNom());
            pstm.setString(2, obj.getPrenom());
            pstm.setString(3, obj.getTel());
            pstm.setInt(4, obj.getIdadresse());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne client mise à jour");
            }
            return read(obj.getIdclient());
        }
    }

    /**
     * effacement du client sur base de son identifiant
     *
     * @throws SQLException erreur d'effacement
     * @param obj client à effacer
     */
    @Override
    public void delete(Client obj) throws SQLException {

        String req = "delete from proj_client where idclient= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdclient());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne client effacée");
            }

        }
    }

    /**
     * méthode permettant de récupérer tous les clients portant un
     * certain nom
     * @param nomrech nom recherché
     * @return liste de clients
     * @throws SQLException nom inconnu
     */
    public List<Client> rechNom(String nomrech) throws SQLException {
        List<Client> plusieurs = new ArrayList<>();
        String req = "select * from proj_client where nom = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setString(1, nomrech);
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idclient = rs.getInt("IDCLIENT");
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    String tel = rs.getString("TEL");
                    int idadresse = rs.getInt("IDADR");
                    plusieurs.add(new Client(idclient, nom, prenom, tel, idadresse));
                }

                if (!trouve) {
                    throw new SQLException("nom inconnu");
                } else {
                    return plusieurs;
                }
            }
        }
        
        
    }
    
    
    /**
     * méthode permettant de récupérer la date de la dernière commande d'un client
     * @param obj client recherché
     * @return  date de la dernière commande
     * @throws SQLException client sans commande
     */
    public LocalDate dern_com(Client obj) throws SQLException{
         String req = "select derniere_com from CLIDATE where idclient = ?";
         try(PreparedStatement pstm = dbConnect.prepareStatement(req)){
             pstm.setInt(1,obj.getIdclient());
             try(ResultSet rs = pstm.executeQuery()){
                 if(rs.next()){
                     LocalDate dt = rs.getDate(1).toLocalDate();
                     return dt;
                 }
                 else throw new SQLException("aucune commande enregistrée pour ce client");
             }
         }
    }

}
