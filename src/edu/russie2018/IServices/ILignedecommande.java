/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.IServices;

import edu.russie2018.entities.Lignedecommande;
import java.util.List;
import javafx.collections.ObservableMap;

/**
 *
 * @author Sami
 */
public interface ILignedecommande {
    
     public void ajouterLigneDeCommande(Lignedecommande lc, int idProduit, int qt);
    public void supprimerLigneDecommande(Lignedecommande lc, int id);
    public void modifierLigneDeCommande(Lignedecommande lc);
    public ObservableMap<Integer,Lignedecommande> consulterLigneDeCommandes();
    
}
