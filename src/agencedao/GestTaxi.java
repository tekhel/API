/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agencedao;

import java.sql.Connection;
import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.List;
//import java.util.Locale;
import java.util.Scanner;
import agence.DAO.TaxiDAO;
import agence.DAO.DAO;
import agence.metier.Taxi;
import myconnections.DBConnection;
//import  agence.DAO.Vue_adresseDAO;
import agence.metier.Vue_adresse;

/**
 *
 * @author Linda TEMGOUA
 */
public class GestTaxi {

    Scanner sc = new Scanner(System.in);
    Taxi tActuel = null;
    Vue_adresse vActuel = null;
    DAO<Taxi> taxiDAO = null;
    DAO<Vue_adresse> vue_adresseDAO = null;
    public GestTaxi() {

    }

    public void gestion() {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }

        System.out.println("connexion établie");

        taxiDAO = new TaxiDAO() {
            @Override
            public Taxi read(int idloc) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            /*@Override
            public Taxi read(String nimm) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }*/
        };
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
                    afficheLocation();
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
        System.out.println("Id du taxi :");
        int num = sc.nextInt();
        sc.skip("\n");
        System.out.print("Immatriculation :");
        String nimm = sc.nextLine();
        System.out.print("Carburant :");
        String carb = sc.nextLine();
        System.out.print("Prix/km :");
        Float prix = sc.nextFloat();
        sc.skip("\n");
        System.out.print("Description :");
        String desc = sc.nextLine();
        tActuel = new Taxi(num, nimm, carb, prix, desc);
        try {
            tActuel = taxiDAO.create(tActuel);
            System.out.println("Taxi actuel : " + tActuel);
        } catch (SQLException e) {
            //System.out.println("Erreur :" + e);
        }

    }

    public void rechercheImm() {
        try {
            System.out.println("Numéro d'immatriculation :");
            String nimm = sc.nextLine().toLowerCase();
            tActuel = taxiDAO.read(nimm);
            System.out.println("Taxi actuel : " + tActuel);

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
            System.out.println("Nouvel id :");
            int num = sc.nextInt();
            sc.skip("\n");
            System.out.println("Carburant :");
            String carb = sc.nextLine();
            System.out.println("Prix : ");
            Float prix = sc.nextFloat();
            sc.skip("\n");
            System.out.println("Description :");
            String desc = sc.nextLine();
            tActuel.setIdtaxi(num);
            tActuel.setImmatriculation(nimm);
            tActuel.setCarburant(carb);
            tActuel.setPrixkm(prix);
            tActuel.setDescription(desc);
            taxiDAO.update(tActuel);
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }
    }

    public void sup() {//SQL06
        try {
            System.out.println("Numéro d'immatriculation à effacer :");
            String nimm = sc.nextLine();

            taxiDAO.delete(tActuel);
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
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
    public void afficheLocation(){/* Code non fonctionnel
        try {
            System.out.println("id de la location :");
            int idloc = sc.nextInt();
            vActuel = vue_adresseDAO.read(idloc);
            System.out.println("Location actuelle : " + tActuel);

        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }*/
    }
  

    public static void main(String[] args) {
        GestTaxi gc = new GestTaxi();
        gc.gestion();

    }

}