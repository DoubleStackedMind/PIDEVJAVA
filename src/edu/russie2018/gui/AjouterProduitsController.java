/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class AjouterProduitsController implements Initializable {

    @FXML
    private Pane AjouterPane;
    @FXML
    private ImageView Error;
    @FXML
    private ImageView Error2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Error.setVisible(false);
        Error2.setVisible(false);
        Label ImgPath = new Label();
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

        Img.setOnAction((ActionEvent event1) -> {
            String myString = setNewPhotoButton(event1);
            Img.setAccessibleText(myString);
            ImgPath.setText("C:/wamp64/www/PIDEV/web/imagesShop/" + myString);
        }
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
        grid.add(ImgPath, 3, 5);
        Label couleur = new Label("Couleur");
        grid.add(couleur, 1, 6);
        grid.add(picker, 2, 6);
        Label mar = new Label("Marque");
        grid.add(mar, 1, 7);
        grid.add(marque, 2, 7);
        grid.add(label5, 1, 8);
        grid.add(text5, 2, 8);
        grid.add(label6, 1, 9);
        grid.add(text6, 2, 9);
        JFXButton Ajouter = new JFXButton("Ajouter");

        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(text1.textProperty(),
                        text2.textProperty(),
                        text4.textProperty(),
                        text5.textProperty(),
                        text6.textProperty(),
                        new SimpleStringProperty(String.valueOf(catg.getValue()))
                );
            }

            @Override
            protected boolean computeValue() {
                return (text1.getText().isEmpty()
                        || text2.getText().isEmpty()
                        || text4.getText().isEmpty()
                        || text5.getText().isEmpty()
                        || text6.getText().isEmpty());
            }
        };

        text2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!newValue.matches("\\d*")) {
                        text2.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                    if (Integer.parseInt(newValue.toString()) == 0 || newValue.startsWith(String.valueOf(0))) {
                        Error.setLayoutX(0);
                        Error.setLayoutY(0);
                        Error.setLayoutX(text2.getLayoutX() + text2.getWidth() + 100);
                        Error.setLayoutY(text2.getLayoutY() - 30);
                        Error.setVisible(true);
                        text2.setStyle(" -fx-border-color : red");
                    } else {
                        Error.setVisible(false);
                        text2.setStyle(" -fx-border-color : transparent");
                    }
                } catch (Exception e) {

                }
            }
        });
        text4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!newValue.matches("\\d*")) {
                        text4.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                    if (Integer.parseInt(newValue.toString()) == 0 || newValue.startsWith(String.valueOf(0))) {
                        Error2.setLayoutX(0);
                        Error2.setLayoutY(0);
                        Error2.setLayoutX(text4.getLayoutX() + text4.getWidth() + 100);
                        Error2.setLayoutY(text4.getLayoutY() - 30);
                        Error2.setVisible(true);
                        text4.setStyle(" -fx-border-color : red");
                       
                    } else {
                        Error2.setVisible(false);
                        
                        text4.setStyle(" -fx-border-color : transparent");
                    }
                } catch (Exception e) {

                }
            }
        });
        Ajouter.disableProperty().bind(bb);
        grid.add(Ajouter, 3, 10);

        AjouterPane.getChildren().add(grid);
    }

    private String setNewPhotoButton(ActionEvent event) {

        Stage currentStage = (Stage) AjouterPane.getScene().getWindow();
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
            java.nio.file.Path ds = new File(dest, src.getName()).toPath();
            File newDes = new File("C:/wamp64/www/PIDEV/web/imagesShop/" + selectedFile.getName());
            selectedFile.renameTo(newDes);
        }
        return selectedFile.getName();
    }
}
