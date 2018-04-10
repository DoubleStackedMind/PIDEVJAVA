/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import edu.russie2018.services.ServiceUser;
import static edu.russie2018.services.ServiceUser.currentUser;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author Jawhar
 */
public class indexController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    public JFXDrawer drawer;
    public static AnchorPane AnchorDrower;
    public static Stage register = new Stage();
    public static Stage loginWindow = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Load first drawer currentUser is empty
            AnchorDrower = FXMLLoader.load(getClass().getResource("DrawerNotConnected.fxml"));
            AnchorDrower.setBackground(new Background(new BackgroundFill(Paint.valueOf("#C5C5EE"), CornerRadii.EMPTY, Insets.EMPTY)));
            drawer.setSidePane(AnchorDrower);
            //get all buttons from DrawerNotConnected
            for (Node node : AnchorDrower.getChildren()) {   //add events to buttons Drower not connected
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    switch (node.getAccessibleText()) {
                        case "se_connecter":
                            //user pressed se connecter button
                            System.out.println("register");

                            //register.initModality(Modality.APPLICATION_MODAL);
                            drawer.close();
                            register.setTitle("Register - new account");

                             {
                                try {
                                    AnchorPane anchor1 = new AnchorPane();
                                    System.out.println("register pos1");
                                    anchor1 = FXMLLoader.load(getClass().getResource("RegisterScreen.fxml"));
                                    Scene log = new Scene(anchor1);
                                    register.setScene(log);
                                    register.showAndWait();
                                } catch (IOException ex) {
                                    Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                        case "login":
                            // user pressed login button
                            loginWindow.setTitle("Login - Existing account");
                            drawer.close();
                             {
                                try {
                                    AnchorPane anchor2 = new AnchorPane();
                                    System.out.println("login pos1");
                                    anchor2 = FXMLLoader.load(getClass().getResource("login.fxml"));
                                    System.out.println("login pos2");
                                    Scene log1 = new Scene(anchor2);
                                    loginWindow.setScene(log1);
                                    loginWindow.showAndWait() ;
                                } catch (IOException ex) {
                                    Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                //currentUser have value 
                                if (ServiceUser.isLogedIn(currentUser)) {
                                    System.out.println(currentUser.toString());
                                    drawer.close();
                                    try {
                                        //load drowerconnected inside drower
                                        AnchorDrower = FXMLLoader.load(getClass().getResource("DrawerConnected.fxml"));
                                        drawer.setSidePane(AnchorDrower);
                                    } catch (IOException ex) {
                                        Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    //get drower connected buttons 
                                    for (Node nod : AnchorDrower.getChildren()) {
                                        nod.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent e) {
                                                switch (nod.getAccessibleText()) {
                                                    case "disconnect": //user pressed deconnecter 
                                                    {
                                                        drawer.close();
                                                        try {
                                                            AnchorDrower = FXMLLoader.load(getClass().getResource("DrawerNotConnected.fxml"));
                                                        } catch (IOException ex) {
                                                            Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                        drawer.setSidePane(AnchorDrower);
                                                        System.out.println("disconnected");
                                                        initialize(url, rb);
                                                        break;
                                                    }
                                                    case "exit":
                                                        System.exit(0);
                                                        break;

                                                }
                                            }
                                        });
                                    }

                                }
                            }
                            break;

                        case "exit":

                            System.exit(0);
                            break;
                    }

                });

            }

        } catch (IOException ex) {
            Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });

    }

}
