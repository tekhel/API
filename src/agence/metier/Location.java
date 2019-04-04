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
public class Location {

  /**
   * identifiant unique de la location
   */
	protected int idloc;
  /**
   * date de la location
   */
	protected java.util.Date dateloc;
  /**
   * Kilomètre total parcouru en une location
   */
	protected float kmtotal;
  /**
   * acompte versé avant location
   */
	protected float acompte;
   /**
    * montant total d'une location
    */
	protected float total;
   /**
    * identifiant du point début d'une location
    */
	protected int id_adr_deb;
   /**
    * identifiant du point fin d'une location
    */
        protected int id_adr_fin;
   /**
    * identifiant du client qui effectue la location
    */
	protected int id_client;
    /**
    * identifiant du taxi qui effectue la location
    */
	protected int idtaxi;

/**
 * constructeur par défaut
 */
     public Location() {
         
    }

/**
 * constructeur paramétré
 * @param idloc identifiant unique de la location
 * @param dateloc date de la location
 * @param kmtotal km total
 * @param acompte acompte versée
 * @param total montant total à payer
 * @param id_adr_deb identifiant de l'adresse de début
 * @param id_adr_fin identifiant de l'adresse de fin
 * @param id_client identifiant du client
 * @param idtaxi identifiant du taxi
 */
    public Location(int idloc, Date dateloc, float kmtotal, float acompte, float total, int id_adr_deb, int id_adr_fin, int id_client, int idtaxi) {
        this.idloc = idloc;
        this.dateloc = dateloc;
        this.kmtotal = kmtotal;
        this.acompte = acompte;
        this.total = total;
        this.id_adr_deb = id_adr_deb;
        this.id_adr_fin = id_adr_fin;
        this.id_client = id_client;
        this.idtaxi = idtaxi;
    }

    public int getIdloc() {
        return idloc;
    }

    public void setIdloc(int idloc) {
        this.idloc = idloc;
    }

    public Date getDateloc() {
        return dateloc;
    }

    public void setDateloc(Date dateloc) {
        this.dateloc = dateloc;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getId_adr_deb() {
        return id_adr_deb;
    }

    public void setId_adr_deb(int id_adr_deb) {
        this.id_adr_deb = id_adr_deb;
    }

    public int getId_adr_fin() {
        return id_adr_fin;
    }

    public void setId_adr_fin(int id_adr_fin) {
        this.id_adr_fin = id_adr_fin;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getIdtaxi() {
        return idtaxi;
    }

    public void setIdtaxi(int idtaxi) {
        this.idtaxi = idtaxi;
    }

    @Override
    public String toString() {
        return "Location{" + "idloc=" + idloc + ", dateloc=" + dateloc + ", kmtotal=" + kmtotal + ", acompte=" + acompte + ", total=" + total + ", id_adr_deb=" + id_adr_deb + ", id_adr_fin=" + id_adr_fin + ", id_client=" + id_client + ", idtaxi=" + idtaxi + '}';
    }


}

    

