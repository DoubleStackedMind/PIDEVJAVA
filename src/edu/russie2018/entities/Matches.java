/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.entities;

import java.util.Date;

/**
 *
 * @author Sami
 */
public class Matches {

    private int id_match;
    private String id_groupe;
    private int phase;
    private Date date_match;
    private String lieu_match;
    private int Resultat;
    private int EquipeA;
    private int EquipeB;
    private int id_stade;
    private int butsequipe1;
    private int butsequipe2;

    public Matches() {
    }

    public Matches(int id_match, String id_groupe, int phase, Date date_match, String lieu_match, int Resultat, int EquipeA, int EquipeB, int id_stade, int butsequipe1, int butsequipe2) {
        this.id_match = id_match;
        this.id_groupe = id_groupe;
        this.phase = phase;
        this.date_match = date_match;
        this.lieu_match = lieu_match;
        this.Resultat = Resultat;
        this.EquipeA = EquipeA;
        this.EquipeB = EquipeB;
        this.id_stade = id_stade;
        this.butsequipe1 = butsequipe1;
        this.butsequipe2 = butsequipe2;
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public String getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(String id_groupe) {
        this.id_groupe = id_groupe;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public Date getDate_match() {
        return date_match;
    }

    public void setDate_match(Date date_match) {
        this.date_match = date_match;
    }

    public String getLieu_match() {
        return lieu_match;
    }

    public void setLieu_match(String lieu_match) {
        this.lieu_match = lieu_match;
    }

    public int getResultat() {
        return Resultat;
    }

    public void setResultat(int Resultat) {
        this.Resultat = Resultat;
    }

    public int getEquipeA() {
        return EquipeA;
    }

    public void setEquipeA(int EquipeA) {
        this.EquipeA = EquipeA;
    }

    public int getEquipeB() {
        return EquipeB;
    }

    public void setEquipeB(int EquipeB) {
        this.EquipeB = EquipeB;
    }

    public int getId_stade() {
        return id_stade;
    }

    public void setId_stade(int id_stade) {
        this.id_stade = id_stade;
    }

    public int getButsequipe1() {
        return butsequipe1;
    }

    public void setButsequipe1(int butsequipe1) {
        this.butsequipe1 = butsequipe1;
    }

    public int getButsequipe2() {
        return butsequipe2;
    }

    public void setButsequipe2(int butsequipe2) {
        this.butsequipe2 = butsequipe2;
    }

    @Override
    public String toString() {
        return "Matches{" + "id_match=" + id_match + ", id_groupe=" + id_groupe + ", phase=" + phase + ", date_match=" + date_match + ", lieu_match=" + lieu_match + ", Resultat=" + Resultat + ", EquipeA=" + EquipeA + ", EquipeB=" + EquipeB + ", id_stade=" + id_stade + ", butsequipe1=" + butsequipe1 + ", butsequipe2=" + butsequipe2 + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id_match;
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
        final Matches other = (Matches) obj;
        if (this.id_match != other.id_match) {
            return false;
        }
        return true;
    }
    
    
    
    
    


}
