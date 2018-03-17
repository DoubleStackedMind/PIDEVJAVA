/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import edu.russie2018.entities.User;
import edu.russie2018.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.activation.DataSource;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfusername;

    @FXML
    private TextField tfpassword;
    @FXML
    private Button forgotpwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfusername.setPromptText("Username ...");
        tfusername.setFocusTraversable(false);
        tfpassword.setPromptText("Password ...");
        tfpassword.setFocusTraversable(false);
    }

    public void Authentification(ActionEvent event) {
        UserService us = new UserService();
        User usr = new User();
        usr.setUsername(tfusername.getText());
        usr.setPassword(tfpassword.getText());
        boolean verify = us.VerifyUser(usr);
        if (verify) {
            try {
                JOptionPane.showMessageDialog(null, "привет "+usr.getUsername()+"!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
                Parent root = loader.load();
                tfusername.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        } else {

        }
    }
}
