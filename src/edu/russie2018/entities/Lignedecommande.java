/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.entities;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 *
 * @author Sami
 */
public class Lignedecommande {
    
    
  private ObservableMap<Integer,Produits> LignedeCommande ;
   // private Commandes commande;
  //  private User idUser;
   // private Produits idProduit;

    public Lignedecommande() {
    }

    public ObservableMap<Integer, Produits> getLignedeCommande() {
        return LignedeCommande;
    }

    public void setLignedeCommande(ObservableMap<Integer, Produits> LignedeCommande) {
        this.LignedeCommande = LignedeCommande;
    } 
 
    
}
