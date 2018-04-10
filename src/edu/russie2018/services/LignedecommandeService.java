/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.ILignedecommande;
import edu.russie2018.entities.Commandes;
import edu.russie2018.entities.Lignedecommande;
import edu.russie2018.entities.Produits;
import edu.russie2018.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Sami
 */
public class LignedecommandeService implements ILignedecommande {

    Connection cnx;

    public LignedecommandeService() {
        cnx = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void ajouterLigneDeCommande(int id, Set<Produits> mySet) {

        try {
            for (Produits p : mySet) {
                CommandesService cs = new CommandesService();
                Commandes c = cs.consulterCommandes();

                String requete = "INSERT INTO lignedecommande (id_user,idproduit,commande,prix,etat, quantite) VALUES(?,?,?,?,?,?)";

                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1, 2);
                pst.setInt(2, p.getIdProduit());
                pst.setInt(3, c.getId());
                pst.setFloat(4, p.getPrix());
                pst.setString(5, "En cours");
                pst.setInt(6, p.getQuantite());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public boolean supprimerLigneDecommande(int id) {

        try {
            String requete = "DELETE FROM lignedecommande WHERE idProduit=? ";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Produits supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Map<Integer, List<Lignedecommande>> ConsulterLigneDeCommandes() {
        Map<Integer, List<Lignedecommande>> myMap = new HashMap<>();
       
        List<Lignedecommande> myList = new ArrayList<>();
       
        ProduitsService ps = new ProduitsService();

        try {
            Statement myStmt1 = cnx.createStatement();
            ResultSet myRes1 = myStmt1.executeQuery("SELECT commande FROM lignedecommande ");
            while (myRes1.next()) {
                myMap.put(myRes1.getInt("commande"), null);
            }
            Statement myStmt = cnx.createStatement();
            ResultSet myRes = myStmt.executeQuery("SELECT lignedecommande.commande,lignedecommande.etat, lignedecommande.idproduit, produits.nom, lignedecommande.quantite, lignedecommande.prix FROM `lignedecommande`JOIN produits ON lignedecommande.idproduit = produits.id_produit GROUP BY `idLigne`");
            while (myRes.next()) {
                Lignedecommande lc = new Lignedecommande();
                lc.setIdCommande(myRes.getInt("commande"));
                lc.setIdProduit(myRes.getInt("idproduit"));
                lc.setQuantite(myRes.getInt("quantite"));
                lc.setNomP(myRes.getString("nom"));
                lc.setEtat(myRes.getString("etat"));
             //  myMap.put(lc.getIdCommande(), myMap.get(lc.getIdCommande()).add(lc));
            }

            return myMap;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
