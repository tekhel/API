/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agencedao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import agence.DAO.TaxiDAO;
import agence.DAO.DAO;
import agence.metier.Taxi;
import myconnections.DBConnection;
import agence.metier.Vue_adresse;

/**
 *
 * @author Linda TEMGOUA
 */
public class GestTaxi {

    Scanner sc = new Scanner(System.in);
    Taxi tActuel = null;
    TaxiDAO taxiDAO = new TaxiDAO();
    List<Taxi> ListeTaxisActuels = null;
    List<Vue_adresse> ListeAdresses = null;
    public GestTaxi() {

    }

    public void gestion() {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }

        System.out.println("connexion établie");
        taxiDAO.setConnection(dbConnect);

        int ch = 0;
        do {
            System.out.println("1.Créer nouveau taxi\n2.Recherche sur immatriculation\n3.Recherche sur description\n4.Modification\n5.Effacement\n6.Calcul prix total\n7.Trajet location\n8.Fin");
            System.out.print("choix :");
            ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    nouveau();
                    break;
                case 2:
                    rechercheImm();
                    break;
                case 3:
                    rechercheDesc();
                    break;
                case 4:
                    modif();
                    break;
                case 5:
                    sup();
                    break;
                case 6:
                    calculPrix();
                    break;
                case 7:
                    //afficheLocation();
                    break;
                case 8:
                    System.out.println("Aurevoir");
                default:
                    System.out.println("choix incorrect");
            }

        } while (ch != 8);
        DBConnection.closeConnection();
    }

    public void nouveau() {
        System.out.print("Immatriculation :");
        String nimm = sc.nextLine();
        System.out.print("Carburant :");
        String carb = sc.nextLine();
        System.out.print("Prix/km :");
        Float prix = sc.nextFloat();
        sc.skip("\n");
        System.out.print("Description :");
        String desc = sc.nextLine();
        tActuel = new Taxi(0, nimm, carb, prix, desc);
        try {
            tActuel = taxiDAO.create(tActuel);
            System.out.println("Taxi actuel : " + tActuel);
        } catch (SQLException e) {
            System.out.println("Erreur :" + e);
        }

    }

    public void rechercheImm() {
        try {
            System.out.println("Numéro d'immatriculation :");
            String nimm = sc.nextLine().toLowerCase();
            tActuel = ((TaxiDAO) taxiDAO).rechImma(nimm);
            System.out.println("Voici le taxi recherché: " + tActuel);

        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }
    }

    public void rechercheDesc() {
        System.out.println("Entrez une description :");
        String descrech = sc.nextLine().toLowerCase();//Conversion lower inspirée du code de Walid
        try {
            List<Taxi> alt = ((TaxiDAO) taxiDAO).rechercheDesc(descrech);
            for (Taxi t : alt) {
                System.out.println(t);
            }
        }  catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }

    }
     public void modif() {//SQL09
        try {
            System.out.println("Quelle immatriculation ? :");
            String nimm = sc.nextLine();
            tActuel = ((TaxiDAO) taxiDAO).rechImma(nimm);
            System.out.println("Le taxi actuel est: "+tActuel);
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }   
    }

    public void sup() {//SQL06
        try {
            System.out.println("Numéro d'immatriculation à effacer :");
            String nimm = sc.nextLine().toUpperCase();
            tActuel=new Taxi(nimm);
            taxiDAO.delete(tActuel);
        } catch (SQLException e) {
            System.out.println("Erreur " + e.getMessage());
        }
    }
    public void calculPrix(){/* Code non fonctionnel
        try {
            System.out.println("id de la location :");
            int idloc = sc.nextInt();
            vActuel = vue_adresseDAO.read(idloc);
            System.out.println("Location actuelle : " + tActuel);

        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }*/
    }
      public void affichageTotal() {
        try {
            System.out.println("Veuillez entrer l'id de la location");
            int id = sc.nextInt();
            ListeAdresses = taxiDAO.rechloc(id);
            for (Vue_adresse va : ListeAdresses) {
                System.out.println(va);
            }

        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());

        }

    }
  

    public static void main(String[] args) {
        GestTaxi gc = new GestTaxi();
        gc.gestion();

    }

}