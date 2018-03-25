/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import edu.russie2018.entities.Produits;
import edu.russie2018.services.ProduitsService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
public class TousLesProduitsController implements Initializable {

    @FXML
    private Pane rootpane;
    private Pane ProductPane;
    private ImageView productImage;
    private Label productName;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXButton minimizeButton;
    @FXML
    private JFXButton maximizeButton;
    @FXML
    private JFXButton closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootpane.setOpacity(0);
        makeFadeIn();
       // DisplayAllProducts();
    }

    private void makeFadeIn() {
          FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.millis(1000));
        fadeOut.setNode(rootpane);
        fadeOut.setFromValue(0);
        fadeOut.setToValue(1);
        fadeOut.play();
    }
    
    public void DisplayAllProducts() throws IOException
    {
        ProduitsService ps = new ProduitsService();
        List<Produits> myList = ps.consulterProduits();
        List<Pane> myListOfPanes = new ArrayList<>();
        for(Produits p : myList)
        {
            
            File f = new File("C:/wamp64/www/PIDEV/web/imagesShop/"+p.getImage());
            Image Img = new Image(f.toURI().toString());
            productImage.setImage(Img);
            productImage.setX(80);
            productImage.setY(400);
            productName.setText(p.getNom().toString());
            ProductPane.getChildren().add(productImage);
            ProductPane.getChildren().add(productName);        
            myListOfPanes.add(ProductPane);
        }
        
        for(Pane p : myListOfPanes)
        {
            
            AnchorPane.getChildren().add(p);
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
    private void maximizeWindow(ActionEvent event) {
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
