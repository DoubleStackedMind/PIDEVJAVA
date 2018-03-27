/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.ICommandes;
import edu.russie2018.entities.Commandes;
import edu.russie2018.entities.Lignedecommande;
import edu.russie2018.entities.Produits;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Sami
 */
public class CommandesService implements ICommandes{

    @Override
    public void ajouterCommande(Commandes c, Lignedecommande lc) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            //Object to JSON in file
            mapper.writeValue(new File("c:\\file.json"), lc);
        } catch (IOException ex) {
            Logger.getLogger(CommandesService.class.getName()).log(Level.SEVERE, null, ex);
        }

//Object to JSON in String
//String jsonInString="";
//for(Integer i : lc.getLignedeCommande().keySet())
//{
//            try {
//                jsonInString += mapper.writeValueAsString(lc.getLignedeCommande().get(i).toString());
//            } catch (IOException ex) {
//                Logger.getLogger(CommandesService.class.getName()).log(Level.SEVERE, null, ex);
//            } }

}

    @Override
    public void supprimerCommande(Commandes c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCommande(Commandes c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commandes> consulterCommandes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
