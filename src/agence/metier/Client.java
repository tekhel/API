
package agence.metier;

/**
 * classe métier de gestion d'un client
 * @author Linda Temgoua
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

     /**
      * identifiant de l'adresse
      */
	protected int idadr;
      
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
 * @param cp code postal de l'adresse
 * @param localite localité de l'adresse
 * @param rue rue de l'adresse
 * @param num numéro de l'adresse
 * @param tel téléphone du client
 */

    public Client(int idclient, String nom, String prenom, String tel, int idadr) {
        this.idclient = idclient;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.idadr = idadr;
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
     * getter prénom du client
     * @return prénom du client
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
     * getter téléphone
     * @return téléphone du client
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

    public int getIdadr() {
        return idadr;
    }

    public void setIdadr(int idadr) {
        this.idadr = idadr;
    }

    @Override
    public String toString() {
        return "Client{" + "idclient=" + idclient + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", idadr=" + idadr + '}';
    }
    

 /**
 * méthode toString
 * @return informations complètes
 */
    
  
}
