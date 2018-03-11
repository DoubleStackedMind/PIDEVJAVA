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
public class Commandes {
    
    private int id;
    private double prix;
    private String commandes;
    //private User idUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCommandes() {
        return commandes;
    }

    public void setCommandes(String commandes) {
        this.commandes = commandes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
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
        final Commandes other = (Commandes) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

 
    
}
