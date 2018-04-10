/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import edu.russie2018.entities.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jawhar
 */
public class MainFx extends Application {

     private Stage stage;
    private User loggedUser;

    @Override
    public void start(Stage primaryStage)  throws IOException{
        
        
       Parent root=FXMLLoader.load(getClass().getResource("index.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
       

    }  
    private static MainFx instance;

    public MainFx() {
        instance = this;
    }

    public static MainFx getInstance() {
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



    public User getLoggedUser() {
        return loggedUser;
    }

  

    public void userLogout(){
        loggedUser = null;
        gotoLogin();
    }

    private void gotoProfile() {
        try {
            replaceSceneContent("profile.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            replaceSceneContent("login.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(MainFx.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 550);
            scene.getStylesheets().add(MainFx.class.getResource("Menu.css").toExternalForm());
            stage.setScene(scene) ;
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }
}
