/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.IProduits;
import edu.russie2018.entities.Produits;
import edu.russie2018.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Sami
 */
public class ProduitsService implements IProduits {

    Connection cnx;

    public ProduitsService() {
        cnx = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void ajouterProduit(Produits p) {

        try {
            String requete = "INSERT INTO produits (nom,prix,categorie,quantite,image,couleur,description,marque,composition) VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, p.getNom().toString());
            pst.setFloat(2, p.getPrix());
            pst.setString(3, p.getCategorie().toString());
            pst.setInt(4, p.getQuantite());
            pst.setString(5, p.getImage().toString());
            pst.setString(6, p.getCouleur().toString());
            pst.setString(7, p.getDescription().toString());
            pst.setString(8, p.getMarque().toString());
            pst.setString(9, p.getComposition().toString());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerProduit(Produits p) {

        try {
            String requete = "DELETE FROM produits WHERE id_produit=? ";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, p.getIdProduit());
            pst.executeUpdate();
            System.out.println("Produits supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifierProduit(int id, String object, Object obj) {

        try {
            String requete = "UPDATE produits SET ? = ? WHERE id_produit= ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           pst.setString(1, object);
            pst.setObject(2, obj);
            pst.setInt(3, id);
            String ch = pst.toString().replaceFirst("\'","");
            String ch2 = ch.replaceFirst("\'","");
            int pos = ch2.indexOf("UPDATE");
            String ch3 = ch2.substring(pos, ch2.length());
             pst = cnx.prepareStatement(ch3);
            pst.executeUpdate();
            System.out.println("Produits modifié avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Produits> consulterProduits() {

        List<Produits> myList = new ArrayList<>();
        try {

            Statement pst = cnx.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * from produits");
            while (rs.next()) {

                StringProperty nom = new SimpleStringProperty(rs.getString("nom"));
                Float prix = rs.getFloat("prix");
                StringProperty categorie = new SimpleStringProperty(rs.getString("categorie"));
                int quantite = rs.getInt("quantite");
                StringProperty image = new SimpleStringProperty(rs.getString("image"));
                StringProperty couleur = new SimpleStringProperty(rs.getString("couleur"));
                StringProperty description = new SimpleStringProperty(rs.getString("description"));
                StringProperty marque = new SimpleStringProperty(rs.getString("marque"));
                StringProperty composition = new SimpleStringProperty(rs.getString("composition"));
                Produits p = new Produits(nom, prix, categorie, couleur, description, marque, composition, quantite, image);
                p.setIdProduit(rs.getInt("id_produit"));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

}
