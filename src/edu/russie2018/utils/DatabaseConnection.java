/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.utils;

import java.sql.*;

/**
 *
 * @author Sami
 */
public class DatabaseConnection {

    private Connection connection;
    private static DatabaseConnection data;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fosuser", "root", "");
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() {
        if (data == null) {
            data = new DatabaseConnection();
        }
        return data;
    }

}
