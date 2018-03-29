/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.ICommandes;
import edu.russie2018.entities.Commandes;
import edu.russie2018.entities.Panier;
import edu.russie2018.entities.Produits;
import static edu.russie2018.services.PanierService.lc;
import edu.russie2018.utils.DatabaseConnection;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

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

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, 2);
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
            ResultSet myRes = myStmt.executeQuery("SELECT * from commandes where id_user=2 order by id DESC");
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
