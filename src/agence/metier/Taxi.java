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
public class Taxi {
    /**
     * identifiant unique du taxi
     */
	protected int idtaxi;
    /**
     * numéro d'immatriculation du taxi
     */
	protected String immatriculation;
        /**
      * type de carburant utilisé
      */
	protected String carburant;
      
        /**
      * prix/km parcourue tva comprise
      */
	protected float prixkm;
      
    /**
     *  description du taxi
     */
	protected String description;
     
  /**
 * constructeur par défaut
 */
    public Taxi() {
    }
    public Taxi(String immatriculation) {
        this.immatriculation = immatriculation;
    }
/**
 * constructeur paramétré
 * @param idtaxi id unique du taxi
 * @param immatriculation numéro d'immatriculation
 * @param carburant carburant utilisé
 * @param prixkm prix/km
 * @param description description du taxi/**
 * constructeur paramétré
 */
    public Taxi(int idtaxi, String immatriculation, String carburant, float prixkm, String description) {
        this.idtaxi = idtaxi;
        this.immatriculation = immatriculation;
        this.carburant = carburant;
        this.prixkm = prixkm;
        this.description = description;
    }

    public int getIdtaxi() {
        return idtaxi;
    }

    public void setIdtaxi(int idtaxi) {
        this.idtaxi = idtaxi;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public float getPrixkm() {
        return prixkm;
    }

    public void setPrixkm(float prixkm) {
        this.prixkm = prixkm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Taxi{" + "idtaxi=" + idtaxi + ", immatriculation=" + immatriculation + ", carburant=" + carburant + ", prixkm=" + prixkm + ", description=" + description + '}';
    }

     
}


    
    

