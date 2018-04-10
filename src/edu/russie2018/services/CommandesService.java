/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.ICommandes;
import edu.russie2018.entities.Commandes;
import edu.russie2018.entities.Produits;
import edu.russie2018.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

/**
 *
 * @author Sami
 */
public class CommandesService implements ICommandes {

    Connection cnx;
    public CommandesService() {
        cnx = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void ajouterCommande(Set<Produits> mySet) {
        try {
            String requete = "INSERT INTO commandes (id_user) VALUES(?)";
            ServiceUser us = new ServiceUser();
            int id = us.currentUser.getId();
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        LignedecommandeService lcs = new LignedecommandeService();
        lcs.ajouterLigneDeCommande(2, mySet);

    }

    @Override
    public void supprimerCommande(Commandes c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCommande(Commandes c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commandes consulterCommandes() {
        try {
            Commandes c = new Commandes();
            Statement myStmt = cnx.createStatement();
            ServiceUser usr = new ServiceUser();
            int id = usr.currentUser.getId();
            ResultSet myRes = myStmt.executeQuery("SELECT * from commandes where id_user="+id+" order by id DESC");
            while (myRes.next()) {
                c.setId(myRes.getInt("id"));
                break;
            }
            return c;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
