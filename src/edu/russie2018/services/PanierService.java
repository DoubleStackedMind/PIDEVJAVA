/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.entities.Panier;
import edu.russie2018.entities.Produits;
import edu.russie2018.utils.DatabaseConnection;
import java.sql.Connection;
import java.lang.Integer;
import javafx.collections.ObservableMap;
import edu.russie2018.IServices.IPanier;

/**
 *
 * @author Sami
 */
public class PanierService implements IPanier {

    Connection cnx;
    
    public static Panier lc = new Panier();

    public PanierService() {
        cnx = DatabaseConnection.getInstance().getConnection();
        
    }

    @Override
    public void ajouterLigneDeCommande(Produits p, int qt) {
        if (lc.getLignedeCommande().containsKey(p)) {
            lc.getLignedeCommande().put(p, lc.getLignedeCommande().get(p) + qt);
        } else {
            lc.getLignedeCommande().put(p, qt);
        }
        System.out.println("Produit ajouté!");
//        HttpCookie session = new HttpCookie("panier", null);
//        session = HttpCookie.parse("panier");
//        System.out.println(session.getValue());
    }

    @Override
    public boolean supprimerLigneDecommande(Produits p, int qt) {
        if(lc.getLignedeCommande().containsKey(p))
        {
        if (lc.getLignedeCommande().get(p) >= qt) {
            lc.getLignedeCommande().put(p, lc.getLignedeCommande().get(p) - qt);
            if(lc.getLignedeCommande().get(p)<=0)
            {
                lc.getLignedeCommande().remove(p);
            }
            return true;
        }
        }
        return false;
        
    }


    @Override
    public ObservableMap<Produits, Integer> consulterLigneDeCommandes() {
    return (ObservableMap) lc.getLignedeCommande();
    }

    @Override
    public void modifierLigneDeCommande(Produits p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
