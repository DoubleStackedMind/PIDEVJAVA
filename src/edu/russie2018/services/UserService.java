/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.services;

import edu.russie2018.IServices.IUser;
import edu.russie2018.entities.User;
import edu.russie2018.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sami
 */
public class UserService implements IUser {

    Connection cnx;

    public UserService() {
        cnx = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean VerifyUser(User usr) {
        try {
            PreparedStatement myStmt = cnx.prepareStatement("SELECT * from user where username = ?");
            myStmt.setString(1, usr.getUsername());

            ResultSet myRes = myStmt.executeQuery();
            if (myRes.first()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean VerifyIfAdmin(User usr) {

        try {
             PreparedStatement myStmt = cnx.prepareStatement("SELECT * from user where username= ? and roles = 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}'");
            myStmt.setString(1, usr.getUsername());

            ResultSet myRes = myStmt.executeQuery();
            if (myRes.first()) {
                return true;
            }      
        } catch (Exception e) {

        }
        return false;
    }

}
