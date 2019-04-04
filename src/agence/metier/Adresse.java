/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agence.metier;

/**
 *
 * @author Linda TEMGOUA
 */
public class Adresse {
  /**
   * identifiant unique de l'adresse
   */
	protected int idadr;
  /**
   * code postal
   */

	protected int cp;
   /**
    * localité
    */
	protected String localite;
   /**
    * rue
    */
	protected String rue;
   /**
    * numéro de rue
    */
        protected String num;


/**
 * constructeur par défaut
 */
     public Adresse() {
         
    }

/**
 * constructeur paramétré
 * @param idadr identifiant unique de l'adresse
 * @param cp code postal de l'adresse
 * @param localite localité de l'adresse
 * @param rue rue de l'adresse
 * @param num numéro de l'adresse
 */

    public Adresse(int idclient, String nom, String prenom, int cp, String localite, String rue, String num, String tel) {
        this.idadr = idclient;
        this.cp = cp;
        this.localite = localite;
        this.rue = rue;
        this.num = num;
    }


   /**
    * getter idadr
    * @return identifiant de l'adresse
    */
    public int getIdclient() {
        return idadr;
    }

    /**
     * setter idadr
     * @param idadr identifiant du client
     */
    public void setIdclient(int idclient) {
        this.idadr = idadr;
    }
  
    /**
     * getter code postal
     * @return code postal
     */
    public int getCp() {
        return cp;
    }

    /**
     * setter code postal
     * @param cp code postal
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * getter localite
     * @return localite
     */
    public String getLocalite() {
        return localite;
    }

    /**
     * setter localite
     * @param localite localité
     */
    public void setLocalite(String localite) {
        this.localite = localite;
    }
    
    /**
     * getter numéro de rue
     * @return numéro de rue
     */
    public String getNum() {
        return num;
    }

    /**
     * setter numéro de rue
     * @param num numéro de rue
     */
    public void setNum(String num) {
        this.num = num;
    }


    /**
     * getter rue
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * setter rue
     * @param rue rue de l'adresse
     */
    public void setRue(String rue) {
        this.rue = rue;
    }


 /**
 * méthode toString
 * @return informations complètes
 */
    @Override
    public String toString() { 
        return "Adresse{" + "idadr=" + idadr + " , cp=" + cp + ", localite=" + localite + ", rue=" + rue + ", num=" + num +  '}';
    }
  
}

    

