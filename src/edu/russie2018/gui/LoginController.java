/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import edu.russie2018.entities.User;
import edu.russie2018.services.ServiceAdmin;
import edu.russie2018.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    private Button login;
    @FXML
    private Button closeButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button maximizeButton;
    @FXML
    private AnchorPane AnchorPane2;

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

    @FXML
    public void Authentification(ActionEvent event) {
        try {
            ServiceUser us = new ServiceUser();
            User usr = new User();
            usr.setUser_name(tfusername.getText());
            usr.setPassword(tfpassword.getText());
            
            boolean verify = us.Autentifier(usr.getUser_name(), usr.getPassword());
            boolean verifyAd = false;//us.VerifyIfAdmin(usr);
            int UserID = us.currentUser.getId();
            if (verify) {
                try {
                    JOptionPane.showMessageDialog(null, "привет " + usr.getUser_name() + "!");
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
                    Parent root = loader.load();
                    tfusername.getScene().setRoot(root);
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    JOptionPane.showMessageDialog(null, "привет " + usr.getUser_name()+ "!");
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
//                Preferences pref = Preferences.userNodeForPackage(User.class);
//                pref.put("User_id",String.valueOf(UserID));
Parent root = loader.load();
tfusername.getScene().setRoot(root);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        Stage w = (Stage) minimizeButton.getScene().getWindow();
        w.setIconified(true);
    }

    @FXML
    private void maximizeButton(ActionEvent event) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Stage primaryStage = (Stage) maximizeButton.getScene().getWindow();
        // System.out.println(primaryStage.getHeight()+" "+primaryStage.getWidth()+" "+primaryStage.getX()+" "+primaryStage.getY());
        if (primaryStage.getHeight() != bounds.getHeight()) {
            primaryStage.setX(bounds.getMinX());
            primaryStage.setY(bounds.getMinY());
            primaryStage.setWidth(bounds.getWidth());
            primaryStage.setHeight(bounds.getHeight());
        } else {
            primaryStage.setHeight(803);
            primaryStage.setWidth(1025);
            primaryStage.setX(171);
            primaryStage.setY(-12);
        }
    }
}
