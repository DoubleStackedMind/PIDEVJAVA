/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.IServices;

import edu.russie2018.entities.Panier;
import edu.russie2018.entities.Produits;
import java.util.List;
import javafx.collections.ObservableMap;

/**
 *
 * @author Sami
 */
public interface IPanier {
    
     public void ajouterLigneDeCommande(Produits p, int qt);
    public boolean supprimerLigneDecommande(Produits p, int qt);
    public void modifierLigneDeCommande(Produits p);
    public ObservableMap<Produits,Integer> consulterLigneDeCommandes();
    
}
