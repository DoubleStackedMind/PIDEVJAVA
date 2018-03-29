/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.IServices;

import edu.russie2018.entities.Produits;
import java.util.Set;

/**
 *
 * @author Sami
 */
public interface ILignedecommande {

    public void ajouterLigneDeCommande(int Id, Set<Produits> mySet);

    public boolean supprimerLigneDecommande(int Id, int idComm);

//    public void modifierLigneDeCommande();
//
//    public ObservableMap<Produits, Integer> consulterLigneDeCommandes();
}
