/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.ILignedecommande;
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

                String requete = "INSERT INTO lignedecommande (id_user,idproduit,commande,prix,etat) VALUES(?,?,?,?,?)";

                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1, 2);
                pst.setInt(2, p.getIdProduit());
                pst.setInt(3, c.getId());
                pst.setFloat(4, p.getPrix());
                pst.setString(5, "En cours");
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public boolean supprimerLigneDecommande(int Id, int idComm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
