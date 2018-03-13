/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.IServices;

import edu.russie2018.entities.Lignedecommande;
import java.util.List;

/**
 *
 * @author Sami
 */
public interface ILignedecommande {
    
     public void ajouterLigneDeCommande(Lignedecommande lc);
    public void supprimerLigneDecommande(Lignedecommande lc);
    public void modifierLigneDeCommande(Lignedecommande lc);
    public List<Lignedecommande> consulterLigneDeCommandes();
    
}
