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
public class Tickets {
    
    private int id;
    private String etat;
    private double prix;
    private int idStade;
    private int idMatch;
    private int idUser;

    public Tickets() {
    }

    public Tickets(int id, String etat, double prix, int idStade, int idMatch, int idUser) {
        this.id = id;
        this.etat = etat;
        this.prix = prix;
        this.idStade = idStade;
        this.idMatch = idMatch;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getIdStade() {
        return idStade;
    }

    public void setIdStade(int idStade) {
        this.idStade = idStade;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Tickets{" + "id=" + id + ", etat=" + etat + ", prix=" + prix + ", idStade=" + idStade + ", idMatch=" + idMatch + ", idUser=" + idUser + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
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
        final Tickets other = (Tickets) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
