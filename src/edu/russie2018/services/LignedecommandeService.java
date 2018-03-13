/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.ILignedecommande;
import edu.russie2018.entities.Lignedecommande;
import edu.russie2018.entities.Produits;
import edu.russie2018.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.lang.Integer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 *
 * @author Sami
 */
public class LignedecommandeService implements ILignedecommande {

    Connection cnx;
    ObservableMap<Integer, Lignedecommande> lc;

    public LignedecommandeService() {
        cnx = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void ajouterLigneDeCommande(Lignedecommande lc, int idProduit, int qt) {

        Produits p = new Produits();
        Object prod = new Object();
        try {
            Statement myStmt = cnx.createStatement();
            ResultSet myRes = myStmt.executeQuery("SELECT * from produits where id_produit = " + idProduit);
            prod = (Produits) myRes.getObject(1);
            ObservableMap<Integer, Produits> myList = lc.getLignedeCommande();
            if (!myList.containsValue(prod)) {
                while (myRes.next()) {
                    p.setIdProduit(myRes.getInt("id_produit"));
                    p.setNom(myRes.getString("nom"));
                    p.setPrix(myRes.getDouble("prix"));
                    p.setImage(myRes.getString("image"));
                    myList.put(qt, p);
                }
            } else {
               for(Integer i : myList.keySet())
               {
                   if(myList.get(i).equals(prod))
                   {
                       i += qt;
                       break;
                   }
               }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LignedecommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerLigneDecommande(Lignedecommande lc, int idProduit) {

        Object p = new Object();
        try {
            Statement myStmt = cnx.createStatement();
            ResultSet myRes = myStmt.executeQuery("SELECT * from produits where id = " + idProduit);
            p = /* (User) */ myRes.getObject(1);

            if (lc.getLignedeCommande().containsValue(p)) {
                lc.getLignedeCommande().remove(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LignedecommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierLigneDeCommande(Lignedecommande lc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableMap<Integer,Lignedecommande> consulterLigneDeCommandes() {
    
        return this.lc;
    }

}
