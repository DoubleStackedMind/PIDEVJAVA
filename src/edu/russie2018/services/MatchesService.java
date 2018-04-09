/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.entities.Matches;
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
public class MatchesService {

    private Connection cnx;

    public MatchesService() {
        cnx = DatabaseConnection.getInstance().getConnection();
    }

    public List<Matches> consulterMatches() {

        try {
            List<Matches> myList = new ArrayList<>();
            Statement myStmt = cnx.createStatement();
            ResultSet myRes = myStmt.executeQuery("SELECT * FROM matches ");
            while (myRes.next()) {
                Matches m = new Matches();
                m.setId_match(myRes.getInt("Id_Match"));
                m.setId_groupe(myRes.getString("Id_groupe"));
                m.setPhase(myRes.getInt("Phase"));
                m.setDate_match(myRes.getDate("Date_Match"));
                m.setLieu_match(myRes.getString("Lieu_match"));
                m.setResultat(myRes.getInt("Resultat"));
                m.setEquipeA(myRes.getInt("EquipeA"));
                m.setEquipeB(myRes.getInt("EquipeB"));
                m.setId_stade(myRes.getInt("id_stade"));
                m.setButsequipe1(myRes.getInt("butequipe1"));
                m.setButsequipe2(myRes.getInt("butesuipe2"));
                myList.add(m);
            }
            return myList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
