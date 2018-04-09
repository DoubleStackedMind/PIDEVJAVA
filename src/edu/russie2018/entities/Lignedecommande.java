/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.entities;

/**
 *
 * @author Sami
 */
public class Lignedecommande {
   
    private int idLigne;
    private int idUser;
    private int idCommande;
    private int idProduit;
    private float prix;
    private String etat;
    private int quantite;
    
    private String nomP;

    public Lignedecommande() {
    }

    public Lignedecommande(int idLigne, int idUser, int idCommande, int idProduit, float prix, String etat, int quantite) {
        this.idLigne = idLigne;
        this.idUser = idUser;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.prix = prix;
        this.etat = etat;
        this.quantite=quantite;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }
    
    

    @Override
    public String toString() {
        return "Lignedecommande{" + "idLigne=" + idLigne + ", idUser=" + idUser + ", idCommande=" + idCommande + ", idProduit=" + idProduit + ", prix=" + prix + ", etat=" + etat + ", quantite=" + quantite + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.idLigne;
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
        if (this.idLigne != other.idLigne) {
            return false;
        }
        return true;
    }
    
    
}
