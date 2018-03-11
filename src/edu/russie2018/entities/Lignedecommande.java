/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.entities;

import java.util.Objects;

/**
 *
 * @author Sami
 */
public class Lignedecommande {
    
    private int idligne;
    private int quantite;
    private double prix;
    private String etat;
   // private Commandes commande;
  //  private User idUser;
   // private Produits idProduit;

    public Lignedecommande(int idligne, int quantite, double prix, String etat) {
        this.idligne = idligne;
        this.quantite = quantite;
        this.prix = prix;
        this.etat = etat;
    }

    public Lignedecommande() {
    }

    
    public int getIdligne() {
        return idligne;
    }

    public void setIdligne(int idligne) {
        this.idligne = idligne;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Lignedecommande{" + "idligne=" + idligne + ", quantite=" + quantite + ", prix=" + prix + ", etat=" + etat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.idligne;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lignedecommande other = (Lignedecommande) obj;
        if (this.idligne != other.idligne) {
            return false;
        }
        return true;
    }


    
}
