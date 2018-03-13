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
            pst.setString(1, p.getNom());
            pst.setDouble(2, p.getPrix());
            pst.setString(3, p.getCategorie());
            pst.setInt(4, p.getQuantite());
            pst.setString(5, p.getImage());
            pst.setString(6, p.getCouleur());
            pst.setString(7, p.getDescription());
            pst.setString(8, p.getMarque());
            pst.setString(9, p.getComposition());

            pst.executeUpdate();
            System.out.println("Produit ajouté avec succées");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerProduit(Produits p) {

        try {
            String requete = "DELETE FROM produits WHERE id=? ";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, p.getIdProduit());
            pst.executeUpdate();
            System.out.println("Produits supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifierProduit(Produits p, int qt) {

        try {
            String requete = "UPDATE produits SET quantite=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, qt);
            pst.setInt(2, p.getIdProduit());
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

                String nom = rs.getString("nom");
                Double prix = rs.getDouble("prix");
                String categorie = rs.getString("categorie");
                int quantite = rs.getInt("quantite");
                String image = rs.getString("image");
                String couleur = rs.getString("couleur");
                String description = rs.getString("description");
                String marque = rs.getString("marque");
                String composition = rs.getString("composition");

                myList.add(new Produits(nom, prix, categorie, couleur, description, marque, composition, quantite, image));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

}
