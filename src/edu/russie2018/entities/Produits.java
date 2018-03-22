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
public class Produits {
    
    private int idProduit;
    private String nom;
    private float prix;
    private String categorie;
    private String couleur;
    private String description;
    private String marque;
    private String composition;
    private int quantite;
    private String image;

    public Produits() {
    }

    public Produits(String nom, float prix, String categorie, String couleur, String description, String marque, String composition, int quantite, String image) {
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
        this.couleur = couleur;
        this.description = description;
        this.marque = marque;
        this.composition = composition;
        this.quantite = quantite;
        this.image = image;
    }
    
    

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produits{" + "idProduit=" + idProduit + ", nom=" + nom + ", prix=" + prix + ", categorie=" + categorie + ", couleur=" + couleur + ", description=" + description + ", marque=" + marque + ", composition=" + composition + ", quantite=" + quantite + ", image=" + image + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idProduit;
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
        final Produits other = (Produits) obj;
        if (this.idProduit != other.idProduit) {
            return false;
        }
        return true;
    }
    
    
    
}
