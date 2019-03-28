
package agence.metier;

/**
 * classe métier de gestion d'un client
 * @author Michel Poriaux
 * @version 1.0
 * 
 */


public class Client {
  /**
   * identifiant unique du client
   */
	protected int idclient;
  /**
   * nom du client
   */
	protected String nom;
  /**
   * prénom du client
   */
	protected String prenom;
   /**
    * numéro de téléphone du client
    */
	protected String tel;
        protected int idadresse;

/**
 * constructeur par défaut
 */
     public Client() {
         
    }

/**
 * constructeur paramétré
 * @param idclient identifiant unique du client, affecté par la base de données
 * @param nom nom du client
 * @param prenom prénom du client
 * @param tel téléphone du client
 * @param idadresse 
 */

    public Client(int idclient, String nom, String prenom, String tel, int idadresse) {
        this.idclient = idclient;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.idadresse = idadresse;
    }


   /**
    * getter idclient
    * @return identifiant du client
    */
    public int getIdclient() {
        return idclient;
    }

    /**
     * setter idclient
     * @param idclient identifiant du client
     */
    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }
  

    /**
     * getter nom
     * @return nom du client
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom du client
     * @param nom nom du client
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter numéro de rue
     * @return numéro de rue
     */
    
    public String getPrenom() {
        return prenom;
    }

    /**
     * setter nom du client
     * @param prenom prénom du client
     */

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * getter rue
     * @return rue
     */
 
    public String getTel() {
        return tel;
    }

    /**
     * setter téléphone
     * @param tel téléphone du client
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIdadresse() {
        return idadresse;
    }

    public void setIdadresse(int idadresse) {
        this.idadresse = idadresse;
    }

 /**
 * méthode toString
 * @return informations complètes
 */
    @Override
    public String toString() { 
        return "Client{" + "idclient=" + idclient + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel +", idadresse= "+idadresse+'}';
    }
  
}
