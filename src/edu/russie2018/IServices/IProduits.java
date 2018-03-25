/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.IServices;

import edu.russie2018.entities.Produits;
import java.util.List;

/**
 *
 * @author Sami
 */
public interface IProduits {
    
    public void ajouterProduit(Produits p);
    public void supprimerProduit(Produits p);
    public void modifierProduit(int id,String object, Object obj);
    public List<Produits> consulterProduits();
    
}
