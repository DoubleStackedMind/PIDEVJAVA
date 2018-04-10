/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.russie2018.entities.User;
import edu.russie2018.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Jawhar
 */
public class CreateAccountController implements Initializable {
    @FXML
    private JFXTextField TFName;
    @FXML
    private JFXTextField TFFamilyName;
    @FXML
    private JFXTextField TFEmail;
    @FXML
    private JFXPasswordField TFPassword;
    @FXML
    private JFXPasswordField TFPasswordConfirmation;
    @FXML
    private JFXComboBox<?> CBEquipeFavo;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Cancel;
  

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    
    private void ajouter(ActionEvent event) throws IOException, SQLException {
        ObservableList<String> equipes = FXCollections.observableArrayList(

"Tunisie" ,
"Russie" , 
"Allemagne" , 
"Arabie Saoudite" , 
"Australie" , 
"Bresil"  , 
"Costa Rica" , 
"Danemark" , "Espagne"  , 
"Islande" , "Maroc" ,
"Nigeria" , "Perou" , 
"Portugal" , "RI Iran" , 
"Senegal" , "Suede" , "Argentine" , 
"Belgique" , "Colombie" , "Croatie" , 
"Egypte" , "France" , "Japon", "Mexique" , 
"Panama" , "Pologne" ,"Republique de Corée" ,
"Serbie","Suisse" ,"Uruguay"
);

CBEquipeFavo = new JFXComboBox(equipes) ;
        User U = null ;
        ServiceUser ServiceUser=new ServiceUser();
        ServiceUser.addUser(U);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
        Parent root;
        root=loader.load();
        Save.getScene().setRoot(root);
        /*/
        Parent root=FXMLLoader.load(getClass().getResource("./List.fxml"));
        Scene scene =new  Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        //*/
        new Alert(Alert.AlertType.INFORMATION, "sucess").show();
    }         


}
