/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.IServices;

import edu.russie2018.entities.Commandes;
import java.util.List;

/**
 *
 * @author Sami
 */
public interface ICommandes {
    
    public void ajouterCommande(Commandes c);
    public void supprimerCommande(Commandes c);
    public void modifierCommande(Commandes c);
    public List<Commandes> consulterCommandes();
    
}
