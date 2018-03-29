/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.IServices;

import edu.russie2018.entities.Commandes;
import edu.russie2018.entities.Produits;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sami
 */
public interface ICommandes {
    
    public void ajouterCommande(Set<Produits> mySet);
    public void supprimerCommande(Commandes c);
    public void modifierCommande(Commandes c);
    public Commandes consulterCommandes();
    
}
