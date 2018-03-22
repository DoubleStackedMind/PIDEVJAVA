/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import edu.russie2018.entities.Produits;
import edu.russie2018.services.ProduitsService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class CrudProduitsController implements Initializable {

    @FXML
    private JFXButton Ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterProduit(ActionEvent event) {

        Dialog<Produits> dialog = new Dialog();
        Label label1 = new Label("Nom : ");
        TextField text1 = new TextField();
        Label label2 = new Label("Prix : ");
        TextField text2 = new TextField();
        Label label3 = new Label("Categorie");
        ComboBox catg = new ComboBox(FXCollections.observableArrayList("Maillot", "Chaussures", "Accessoires"));
        catg.setPromptText("Choisissez une categorie ..");
        Label label4 = new Label("Quantite");
        TextField text4 = new TextField();
        Label img = new Label("Image");
        Button Img = new Button("Ajouter un Image");

        Img.setOnAction((ActionEvent event1) -> { String myString  = setNewPhotoButton(event1); 
        Img.setAccessibleText(myString);}
        );
        JFXColorPicker picker = new JFXColorPicker();
        String colour = "#" + Integer.toHexString(picker.getValue().hashCode());
        Label label5 = new Label("Description :");
        TextArea text5 = new TextArea();
        ComboBox marque = new ComboBox(FXCollections.observableArrayList("Nike", "Adidas"));
        marque.setPromptText("Choisissez une marque");
        Label label6 = new Label("Compoisition");
        TextField text6 = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(text2, 2, 2);
        grid.add(label3, 1, 3);
        grid.add(catg, 2, 3);
        grid.add(label4, 1, 4);
        grid.add(text4, 2, 4);
        grid.add(img, 1, 5);
        grid.add(Img, 2, 5);
        Label couleur = new Label("Couleur");
        grid.add(couleur, 1, 6);
        grid.add(picker, 2, 6);
        Label mar = new Label("Marque");
        grid.add(mar,1, 7);
        grid.add(marque,2, 7);
        grid.add(label5, 1, 8);
        grid.add(text5, 2, 8);
        grid.add(label6, 1, 9);
        grid.add(text6, 2, 9);
        dialog.getDialogPane().setContent(grid);
        ButtonType Ajouter = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(Ajouter);
           
           
        dialog.setResultConverter(new Callback<ButtonType, Produits>() {
            @Override
            public Produits call(ButtonType param) {

                if (param == Ajouter) {
                    Produits P =  new Produits(text1.getText(), Float.parseFloat(text2.getText()), catg.getValue().toString(), colour, text5.getText(), marque.getValue().toString(), text6.getText(), Integer.parseInt(text4.getText()), Img.getAccessibleText());
                    ProduitsService ps  = new ProduitsService();
                    System.out.println(P);
                    ps.ajouterProduit(P); 
                                  Notifications notifs = Notifications.create()
                    .title("Produit ajouté")
                    .text("Le produit a été ajouter avec succées!")
                    .graphic(new ImageView("file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/Tick.png"))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
                  
            notifs.darkStyle();
            Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                           notifs.show();
                        }
                    });
             
                    return P;
                }
                
                return new Produits();
            }
        });
        dialog.showAndWait();
    }
    
     @FXML
    private String setNewPhotoButton(ActionEvent event) {

        Stage currentStage = (Stage) Ajouter.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisissez une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File f = new File("C:/Users/samia/Desktop");
        fileChooser.setInitialDirectory(f);
        File selectedFile = fileChooser.showOpenDialog(currentStage);

        if (selectedFile != null) {
            //System.out.println("C:/" + selectedFile.getPath());
            //System.out.println("userfiles/"+UNAME+"/"+ANAME+"/");
            File src = new File(selectedFile.getPath());
            File dest = new File("C:/wamp64/www/PIDEV/web/imagesShop/");
            java.nio.file.Path sr = src.toPath();
            java.nio.file.Path ds =  new File(dest, src.getName()).toPath();
            File newDes = new File("C:/wamp64/www/PIDEV/web/imagesShop/"+selectedFile.getName());
            selectedFile.renameTo(newDes);
        }
        return "C:/wamp64/www/PIDEV/web/imagesShop/"+selectedFile.getName();
    }

    
}
                                       
