/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.ITickets;
import edu.russie2018.entities.Tickets;
import edu.russie2018.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Sami
 */
public class TicketsService implements ITickets {

    private Connection cnx;

    public TicketsService() {
        cnx = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void ajouterTicket(Tickets t) {
        try {
            String requete = "INSERT INTO produits (id_match,id_stade,prix,id_user,adresse,nom,prenom,telephone) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdMatch());
            pst.setInt(2, t.getIdStade());
            pst.setFloat(3, Float.valueOf(String.valueOf(t.getPrix())));
            pst.setInt(4, t.getIdUser());
            pst.setString(5, t.getAdresse());
            pst.setString(6, t.getNom());
            pst.setString(7, t.getNom());
            pst.setLong(8, t.getTelephone());

            pst.executeUpdate();
            Notifications notifs = Notifications.create()
                    .title("Ticket Réservé")
                    .text("Ticket a été réservé avec succes!")
                    .graphic(new ImageView("file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/Tick.png"))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);

            notifs.darkStyle();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    notifs.show();
                }
            });
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerTicket(Tickets t) {
        try {
            Statement myStmt = cnx.createStatement();
            myStmt.executeUpdate("DELETE FROM ticket where id=" + t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modifierTicket(Tickets t) {

    }

    @Override
    public List<Tickets> consulterTickets() {
        try {
            List<Tickets> myList = new ArrayList<>();
            Statement myStmt = cnx.createStatement();
            ResultSet myRes = myStmt.executeQuery("SELECT * FROM ticket");
            while (myRes.next()) {
                Tickets t = new Tickets();
                t.setIdMatch(myRes.getInt("id_match"));
                t.setIdStade(myRes.getInt("id_stade"));
                t.setIdUser(myRes.getInt("id_user"));
                t.setPrix(myRes.getFloat("prix"));
                t.setAdresse(myRes.getString("adresse"));
                t.setTelephone(myRes.getLong("telephone"));
                myList.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
