/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sami
 */
@XmlRootElement(name = "produits")
@XmlAccessorType(XmlAccessType.FIELD)
public class Produits extends RecursiveTreeObject<Produits> implements Serializable {

     private static final long serialVersionUID = 1;
    
    private  int idProduit;
    private  StringProperty nom;
    private  float prix;
    private  StringProperty categorie;
    private  StringProperty couleur;
    private  StringProperty description;
    private  StringProperty marque;
    private  StringProperty composition;
    private  int quantite;
    private  StringProperty image;

    public Produits() {
    }

    private void writeObject(ObjectOutputStream out)
            throws IOException {

        out.defaultWriteObject();   // always call this first

        out.writeObject(idProduit);
        out.writeObject(nom.get());
        out.writeObject(prix);
        out.writeObject(categorie.get());
        out.writeObject(couleur.get());
        out.writeObject(description.get());
        out.writeObject(marque.get());
        out.writeObject(composition.get());
        out.writeObject(quantite);
        out.writeObject(image.get());
    }
    
       private void readObject(ObjectInputStream in)
    throws IOException,
           ClassNotFoundException {

        in.defaultReadObject();    // always call this first

        idProduit =  in.readInt();
        nom = new SimpleStringProperty((String) in.readObject());
        prix = in.readFloat();
        categorie = new SimpleStringProperty((String) in.readObject());
        couleur = new SimpleStringProperty((String) in.readObject());
        description = new SimpleStringProperty((String) in.readObject());
        marque = new SimpleStringProperty((String) in.readObject());
        composition = new SimpleStringProperty((String) in.readObject());
        quantite = in.readInt();
        image = new SimpleStringProperty((String) in.readObject());
    }

    public Produits(StringProperty nom, float prix, StringProperty categorie, StringProperty couleur, StringProperty description, StringProperty marque, StringProperty composition, int quantite, StringProperty image) {
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

    public StringProperty getNom() {
        return nom;
    }

    public void setNom(StringProperty nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public StringProperty getCategorie() {
        return categorie;
    }

    public void setCategorie(StringProperty categorie) {
        this.categorie = categorie;
    }

    public StringProperty getCouleur() {
        return couleur;
    }

    public void setCouleur(StringProperty couleur) {
        this.couleur = couleur;
    }

    public StringProperty getDescription() {
        return description;
    }

    public void setDescription(StringProperty description) {
        this.description = description;
    }

    public StringProperty getMarque() {
        return marque;
    }

    public void setMarque(StringProperty marque) {
        this.marque = marque;
    }

    public StringProperty getComposition() {
        return composition;
    }

    public void setComposition(StringProperty composition) {
        this.composition = composition;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public StringProperty getImage() {
        return image;
    }

    public void setImage(StringProperty image) {
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
