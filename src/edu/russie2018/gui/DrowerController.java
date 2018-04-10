/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import edu.russie2018.entities.User;
import edu.russie2018.services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static edu.russie2018.gui.indexController.register;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author USER-PC
 */
public class DrowerController implements Initializable {
    
    private Label label;
    @FXML
    private JFXButton inscrire;
    
    @FXML
    private JFXButton cancel;
    @FXML
    private ChoiceBox<?> role;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      
        inscrire.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            System.out.println(nom.getText());
            ServiceUser user=new ServiceUser();
            User u=new User(0, nom.getText(), 0,email.getText(), password.getText(), "", "", "", role.getValue().toString(), nom.getText(), prenom.getText(), "", 0, 0, 0);
                     //User(int id, String Use, int Enabled, String Email, String Password, String Last_login, String Confirmation_token, String Password_request_at, String Roles, String First_name, String Last_name, String Preferance, int Warning, int Stars, int NbRating) {
            ServiceUser userservice=new ServiceUser();
            try {
                        userservice.addUser(u);
                    } catch (SQLException ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
            register.close();
        
        });
     
        
        
       
    }    
    
}
