/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import static edu.russie2018.services.ServiceUser.currentUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static edu.russie2018.gui.indexController.AnchorDrower;

/**
 * FXML Controller class
 *
 * @author Jawhar
 */
public class DrawerConnectedController implements Initializable {
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton profile;
    @FXML
    private ImageView UserImage;
    @FXML
    private JFXButton disconnect;
    @FXML
    private Label nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
     nom.setText("Connected as :"+currentUser.getUser_name());
        disconnect.addEventHandler(MouseEvent.MOUSE_CLICKED,( MouseEvent e)->{
            System.out.println("disconnect button");
            currentUser.setConfirmation_token("");
            currentUser.setEmail("");
            currentUser.setEnabled(0);
            currentUser.setFirst_name("");
            currentUser.setId(0);
            currentUser.setLast_login("");
            currentUser.setLast_name("");
            currentUser.setLikes(0);
            currentUser.setPassword("");
            currentUser.setPassword_request_at("");
            currentUser.setdislikes(0);
            currentUser.setRoles("");
            currentUser.setSolde(50);
            currentUser.setUser_name("");
     
            try {
                AnchorDrower=FXMLLoader.load(getClass().getResource("DrawerConnected.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DrawerConnectedController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(currentUser.toString());
        });
    
    
    
    }    
    
}
