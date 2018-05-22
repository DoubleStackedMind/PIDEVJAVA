/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class ShopController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ImageView backslide1;
    @FXML
    private ImageView Backslide2;
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton Shop;
    @FXML
    private JFXButton Maillots;
    @FXML
    private JFXButton Chaussures;
    @FXML
    private JFXButton Accessoires;
    @FXML
    private JFXButton Tous;
    @FXML
    private JFXNodesList nodeList;
    @FXML
    private Pane rootpane;
    @FXML
    private JFXButton minimizeButton;
    @FXML
    private JFXButton maximizeButton;
    @FXML
    private ImageView Backgroundslide2;
    @FXML
    private ImageView BackgroundSlide1;

    String scene;
    @FXML
    private JFXButton Commandes;
    @FXML
    private JFXButton Tick;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//
//        badge1.setOnMouseClicked((e) -> {
//            int value = Integer.parseInt(badge1.getText());
//            if (e.getButton() == MouseButton.PRIMARY) {
//                value++;
//                System.out.println("Clicked");
//            }
//            cardno.setText(String.valueOf(value));
//            badge1.setText(String.valueOf(value));
//        });
        slideshow();

        nodeList.addAnimatedNode(Shop);
        nodeList.addAnimatedNode(Tous);
        nodeList.addAnimatedNode(Maillots);
        nodeList.addAnimatedNode(Chaussures);
        nodeList.addAnimatedNode(Accessoires);
        nodeList.setSpacing(5);

        Tous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene = "Tous";
                VoirTous(event, scene);
            }
        });
        Maillots.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene = "Maillots";
                VoirTous(event, scene);
            }
        });
        Chaussures.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene = "Chaussures";
                VoirTous(event, scene);
            }
        });
        Accessoires.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene = "Accessoires";
                VoirTous(event, scene);
            }
        });

    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Platform.exit();
    }

    public void slideshow() {

        new Thread() {
            @Override
            public void run() {
                try {
                    TranslateTransition transition = new TranslateTransition();
                    transition.setDuration(Duration.seconds(5));
                    transition.setNode(backslide1);
                    transition.setToX(-1024);
                    transition.setAutoReverse(true);
                    transition.setDelay(Duration.millis(3000));
                    transition.setCycleCount(transition.INDEFINITE);

                    TranslateTransition transition2 = new TranslateTransition();
                    transition2.setDuration(Duration.seconds(5));
                    transition2.setNode(Backslide2);
                    transition2.setToX(-1024);
                    transition2.setAutoReverse(true);
                    transition2.setDelay(Duration.millis(3000));
                    transition2.setCycleCount(transition.INDEFINITE);

                    TranslateTransition transition3 = new TranslateTransition();
                    transition3.setDuration(Duration.seconds(5));
                    transition3.setNode(BackgroundSlide1);
                    transition3.setToX(-1024);
                    transition3.setDelay(Duration.millis(3000));
                    transition3.setCycleCount(transition.INDEFINITE);

                    TranslateTransition transition4 = new TranslateTransition();
                    transition4.setDuration(Duration.seconds(10));
                    transition4.setNode(Backgroundslide2);
                    transition4.setToX(-1024);
                    transition4.setAutoReverse(true);
                    transition4.setDelay(Duration.millis(3000));
                    transition4.setCycleCount(transition.INDEFINITE);

                    ParallelTransition p = new ParallelTransition();
                    ParallelTransition p1 = new ParallelTransition();
                    ParallelTransition p2 = new ParallelTransition();

                    p1.getChildren().add(transition3);
                    p2.getChildren().add(transition4);
                    p.getChildren().add(transition);
                    p.getChildren().add(transition2);
                    p.play();
                    p2.play();
                    p1.play();

                    while (true) {
                        if (-1023 > BackgroundSlide1.translateXProperty().getValue()) {
                            if (p.getStatus() == Animation.Status.RUNNING) {
                                p.pause();
                            }
                        }

                        if (-1023 > Backgroundslide2.translateXProperty().getValue()) {
                            if (p.getStatus() == Animation.Status.PAUSED) {
                                p.play();
                            }
                        }

                    }
                } catch (Exception e) {

                }
            }
        }.start();
    }

    private void VoirTous(ActionEvent event, String scene) {
        makeFadeOut(scene);
    }

    private void makeFadeOut(String scene) {
        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.millis(1000));
        fadeOut.setNode(rootpane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished((ActionEvent event) -> {
            LoadNextScene(scene);
        });
        fadeOut.play();
    }

    private void LoadNextScene(String sample) {

        try {
            if (sample.compareTo("Tous") == 0) {
                Parent SecondView;
                SecondView = (Pane) FXMLLoader.load(getClass().getResource("TousLesProduits.fxml"));

                Scene newScene = new Scene(SecondView);
                Stage currStage = (Stage) rootpane.getScene().getWindow();
                currStage.setScene(newScene);
            }
            if (sample.compareTo("Maillots") == 0) {
                Parent SecondView;
                SecondView = (Pane) FXMLLoader.load(getClass().getResource("AfficherMaillots.fxml"));

                Scene newScene = new Scene(SecondView);
                Stage currStage = (Stage) rootpane.getScene().getWindow();
                currStage.setScene(newScene);
            }
            if (sample.compareTo("Chaussures") == 0) {
                Parent SecondView;
                SecondView = (Pane) FXMLLoader.load(getClass().getResource("AfficherChaussures.fxml"));

                Scene newScene = new Scene(SecondView);
                Stage currStage = (Stage) rootpane.getScene().getWindow();
                currStage.setScene(newScene);
            }
            if (sample.compareTo("Accessoires") == 0) {
                Parent SecondView;
                SecondView = (Pane) FXMLLoader.load(getClass().getResource("AfficherAccessoires.fxml"));

                Scene newScene = new Scene(SecondView);
                Stage currStage = (Stage) rootpane.getScene().getWindow();
                currStage.setScene(newScene);
            }
        } catch (IOException ex) {
        }

    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        Stage w = (Stage) minimizeButton.getScene().getWindow();
        w.setIconified(true);
    }

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

    @FXML
    private void maximizeWindow(ActionEvent event) {
    }

    @FXML
    private void ShowCommandes(ActionEvent event) {
        try {
            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("Commande.fxml"));
            Scene newScene = new Scene(SecondView);
            Stage currStage = (Stage) rootpane.getScene().getWindow();
            currStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showTickets(ActionEvent event) {
         try {
            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("ReserverTickets.fxml"));
            Scene newScene = new Scene(SecondView);
            Stage currStage = (Stage) rootpane.getScene().getWindow();
            currStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
