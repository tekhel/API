/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agence.metier;

import java.util.Date;

/**
 *
 * @author Linda TEMGOUA
 */
public class Vue_adresse {

  /**
   * identifiant unique de la location
   */
	protected int idlocation;
        protected int adresse_depart;
        protected int adresse_fin;
        
  /**
   * date de la location
   */
	//protected java.util.Date dateloc;
  /**
   * Kilomètre total parcouru en une location
   */
	protected float kmtotal;
  /**
   * acompte versé avant location
   */
	protected float acompte;
  

/**
 * constructeur par défaut
 */
     public Vue_adresse() {
         
    }/**
 * constructeur paramétré
 * @param idloc identifiant unique de la location
 * @param dateloc date de la location
 * @param kmtotal km total
 * @param acompte acompte versée
 */

    public Vue_adresse(int idlocation, int adresse_depart, int adresse_fin, float kmtotal, float acompte) {
        this.idlocation = idlocation;
        this.adresse_depart = adresse_depart;
        this.adresse_fin = adresse_fin;
        this.kmtotal = kmtotal;
        this.acompte = acompte;
    }

    public int getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(int idlocation) {
        this.idlocation = idlocation;
    }

    public int getAdresse_depart() {
        return adresse_depart;
    }

    public void setAdresse_depart(int adresse_depart) {
        this.adresse_depart = adresse_depart;
    }

    public int getAdresse_fin() {
        return adresse_fin;
    }

    public void setAdresse_fin(int adresse_fin) {
        this.adresse_fin = adresse_fin;
    }

    public float getKmtotal() {
        return kmtotal;
    }

    public void setKmtotal(float kmtotal) {
        this.kmtotal = kmtotal;
    }

    public float getAcompte() {
        return acompte;
    }

    public void setAcompte(float acompte) {
        this.acompte = acompte;
    }

    @Override
    public String toString() {
        return "Vue_adresse{" + "idlocation=" + idlocation + ", adresse_depart=" + adresse_depart + ", adresse_fin=" + adresse_fin + ", kmtotal=" + kmtotal + ", acompte=" + acompte + '}';
    }
    
     
}