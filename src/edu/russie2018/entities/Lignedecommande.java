/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.entities;

import com.sun.javafx.collections.ObservableMapWrapper;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sami
 */
@XmlRootElement(name = "Lignedecommande")
@XmlAccessorType (XmlAccessType.FIELD)
public class Lignedecommande implements Serializable{
    
    @XmlElement(name = "Ligne")
  private Map<Produits,Integer> LignedeCommande = new HashMap<Produits, Integer>() ;
   // private Commandes commande;
  //  private User idUser;
   // private Produits idProduit;

    public Lignedecommande() {
    }

    public Lignedecommande(Map<Produits, Integer> LignedeCommande) {
        this.LignedeCommande = LignedeCommande;
    }

    public Map<Produits, Integer> getLignedeCommande() {
        return LignedeCommande;
    }

    public void setLignedeCommande(Map<Produits, Integer> LignedeCommande) {
        this.LignedeCommande = LignedeCommande;
    }

  
    
 
    
}
