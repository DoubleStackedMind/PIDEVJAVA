/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.ILignedecommande;
import edu.russie2018.entities.Lignedecommande;
import edu.russie2018.entities.Produits;
import edu.russie2018.utils.DatabaseConnection;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.lang.Integer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Sami
 */
public class LignedecommandeService implements ILignedecommande {

    Connection cnx;
    Lignedecommande lc = new Lignedecommande();

    public LignedecommandeService() {
        cnx = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void ajouterLigneDeCommande(Produits p, int qt) {
        try {

            File f = new File("C:/Users/samia/Documents/NetBeansProjects/PIDEV/Ligne.xml");
            if (!f.exists()) {
                FileOutputStream fos = new FileOutputStream(f);
                if (lc.getLignedeCommande() != null) {
                    if (!lc.getLignedeCommande().containsKey(p)) {
                        lc.getLignedeCommande().put(p, qt);
                    } else {
                        lc.getLignedeCommande().put(p, qt + lc.getLignedeCommande().get(p));
                    }
                } else {
                    Map<Produits, Integer> myMap = new HashMap<Produits, Integer>();
                    myMap.put(p, qt);
                    lc.getLignedeCommande().putAll(myMap);
                }
                try (XMLEncoder encoder = new XMLEncoder(fos)) {
                    for (Produits x : lc.getLignedeCommande().keySet()) {
                        x.setQuantite(qt);
                        encoder.writeObject(x);
                    }
                    encoder.close();
                }

                System.out.println("File Created! ");
            } else {
                try (FileInputStream fis = new FileInputStream(f)) {
                    XMLDecoder decoder = new XMLDecoder(fis);
                    
                    	try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    dbf.setValidating(false);
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(new FileInputStream(f));
                    NodeList entries = doc.getChildNodes();

                    for (int i = 0; i < entries.getLength(); i++) {
                        
                        Produits element = (Produits) entries.item(i);
                        System.out.println(element);
                        lc.getLignedeCommande().put(element, element.getQuantite());
                    }
}catch (Exception ex) {
}

//                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//                    Document doc = docBuilder.parse(fis);
//
//                    NodeList list = doc.getElementsByTagName("staff");
//                    System.out.println(list.item(0));
//                    for (int i = 0; i < list.getLength(); i++) {
//                        Produits prod = (Produits) list.item(i);
//                        lc.getLignedeCommande().put(prod, prod.getQuantite());
//
//                    }

                    if (lc.getLignedeCommande() != null) {
                        if (!lc.getLignedeCommande().containsKey(p)) {
                            lc.getLignedeCommande().put(p, qt);
                        } else {
                            int x = lc.getLignedeCommande().keySet().stream().filter(e -> e.equals(p)).findFirst().get().getQuantite();

                            lc.getLignedeCommande().put(p, qt + x);
                        }
                    }
                    decoder.close();
                    //  lc.getLignedeCommande().put(Prod, Prod.getQuantite());
                    FileOutputStream fos = new FileOutputStream(f);
                    XMLEncoder encoder = new XMLEncoder(fos);
                    for (Produits produits : lc.getLignedeCommande().keySet()) {
                        produits.setQuantite(produits.getQuantite() + qt);
                        encoder.writeObject(produits);
                    }
                    encoder.close();
                }
            }
        } catch (IOException ex) {
        }
    }

    @Override
    public boolean supprimerLigneDecommande(Produits p, int qt
    ) {
        if (lc.getLignedeCommande().get(p) >= qt) {
            lc.getLignedeCommande().put(p, lc.getLignedeCommande().get(p) - qt);
            return true;
        }
        return false;
    }

    @Override
    public void modifierLigneDeCommande(Produits p
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableMap<Produits, Integer> consulterLigneDeCommandes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
