/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import agence.DAO.ClientDAO;
import agence.DAO.DAO;
import agence.metier.Client;
import myconnections.DBConnection;

/**
 *
 * @author Michel
 */
public class GestClient {

    Scanner sc = new Scanner(System.in);
    Client clActuel = null;
    DAO<Client> clientDAO = null;

    public GestClient() {

    }

    public void gestion() {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }

        System.out.println("connexion établie");

        clientDAO = new ClientDAO();
        clientDAO.setConnection(dbConnect);

        int ch = 0;
        do {
            System.out.println("1.nouveau \n2.recherche\n3.modification\n4.suppression\n5.recherche sur nom\n6.dernière commande\n7.fin");
            System.out.print("choix :");
            ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    nouveau();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modif();
                    break;
                case 4:
                    sup();
                    break;
                case 5:
                    rechnom();
                    break;
                case 6:
                    derncom();
                    break;
                case 7:
                    System.out.println("bye");
                    break;
                default:
                    System.out.println("choix incorrect");
            }

        } while (ch != 7);
        DBConnection.closeConnection();
    }

    public void nouveau() {

        System.out.print("nom :");
        String nom = sc.nextLine();
        System.out.print("prénom :");
        String prenom = sc.nextLine();
        System.out.print("tel :");
        String tel = sc.nextLine();
        System.out.println("Id de l'adresse : ");
        int idadresse = sc.nextInt();
        clActuel = new Client(0, nom, prenom, tel, idadresse);
        try {
            clActuel = clientDAO.create(clActuel);
            System.out.println("client actuel : " + clActuel);
        } catch (SQLException e) {
            System.out.println("erreur :" + e);
        }

    }

    public void recherche() {
        try {
            System.out.println("numéro recherché :");
            int nc = sc.nextInt();
            clActuel = clientDAO.read(nc);
            System.out.println("client actuel : " + clActuel);

        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }
    }

    public void modif() {
        try {
            System.out.println("tel :");
            String tel = sc.next();
            clActuel.setTel(tel);
            clientDAO.update(clActuel);
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }

    }

    public void sup() {
        try {
            clientDAO.delete(clActuel);
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }
    }

    public void rechnom() {
        System.out.println("nom recherché : ");
        String nom = sc.nextLine();
        try {
            List<Client> alc = ((ClientDAO) clientDAO).rechNom(nom);
            for (Client cl : alc) {
                System.out.println(cl);
            }
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }
    }

    public void derncom() {
        try {
            LocalDate dt = ((ClientDAO) clientDAO).dern_com(clActuel);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E d MMMM yyyy", Locale.FRENCH);
            String aff = dt.format(dtf);
            System.out.println("date de la denière commande de " + clActuel + " = " + aff);
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GestClient gc = new GestClient();
        gc.gestion();

    }

}
