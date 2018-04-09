/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sami
 */
public class EquipesService {

    private Connection cnx;

    public EquipesService() {
        cnx = DatabaseConnection.getInstance().getConnection();
    }

    public List<String> getNameById(int id) {
        try {
            List<String> myList = new ArrayList<>();
            Statement myStmt = cnx.createStatement();
            ResultSet myRes = myStmt.executeQuery("SELECT equipe.Nom from equipe join matches on equipe.IDEquipe = matches.EquipeA or equipe.IDEquipe = matches.EquipeB where matches.Id_Match="+id);
            while (myRes.next()) {
                myList.add(myRes.getString("Nom"));
            }
            return myList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
