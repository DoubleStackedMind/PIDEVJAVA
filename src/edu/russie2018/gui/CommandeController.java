/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import edu.russie2018.entities.Lignedecommande;
import edu.russie2018.entities.Produits;
import edu.russie2018.services.LignedecommandeService;
import edu.russie2018.services.ProduitsService;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class CommandeController implements Initializable {

    @FXML
    private ScrollPane sp;

    JFXListView<Label> Labels = new JFXListView<>();
    JFXListView<Label> PriceLabels = new JFXListView<>();
    @FXML
    private JFXButton Supprimer;
    @FXML
    private JFXButton Modifier;
    @FXML
    private AnchorPane Popup;

    boolean clicked = true;
    @FXML
    private JFXButton Shop;

    private String nomP;

    private float prix;
    @FXML
    private TextField PrixTotal;
    
    private int i=0;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Popup.setVisible(false);
        Labels.setExpanded(true);
        Labels.depthProperty().set(1);
        Labels.getStylesheets().add(getClass().getResource("commande.css").toExternalForm());
        Labels.setPrefSize(600, 600);
        sp.setContent(Labels);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        displayCommandes();
        MouseAction();
    }

    public void displayCommandes() {
        LignedecommandeService lcs = new LignedecommandeService();
        Map<Integer, List<Produits>> myMap = new HashMap<>();
        myMap.putAll(lcs.ConsulterLigneDeCommandes());
     
        
      
        myMap.values().stream().reduce((a,b) -> { a.addAll(b) ; return a; }).get().stream().forEach(e -> {
//        for (List<Produits> l : myMap.values()) {

//          Label lb = new Label("Commande : " + i);
//            Labels.getItems().add(lb); 
     
//            l.forEach( pro -> {
                Label lbl = new Label(e.getNom().get());
                Label plbl = new Label(String.valueOf(e.getPrix()));
                plbl.setFont(Font.font("Futura Condensed Oblique"));
                lbl.setFont(Font.font("Futura Condensed Oblique"));
                plbl.setTextAlignment(TextAlignment.CENTER);
                plbl.setId("Pricelabel");
                lbl.setGraphic(plbl);
                lbl.setContentDisplay(ContentDisplay.RIGHT);
                lbl.setGraphicTextGap(Labels.getWidth() + 200);

                Labels.getItems().add(lbl);
                prix = prix + (e.getPrix() * e.getQuantite());
            
            i++;
            PrixTotal.setText(String.valueOf(prix));
        
        });
        System.out.println(myMap);
    }

    public void MouseAction() {
        Labels.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (clicked) {

                    Popup.setLayoutX(event.getSceneX());
                    Popup.setLayoutY(event.getSceneY());
                    Popup.setVisible(true);
                    clicked = false;
                } else {
                    clicked = true;
                    Popup.setVisible(false);
                }
            }
        });
    }

    @FXML
    private void SupprimerItem(ActionEvent event) {
        GaussianBlur effect = new GaussianBlur();
        Dialog dialog = new Dialog();
        GridPane grid = new GridPane();
        Label lbl = new Label("Voulez-vous supprimer ce produit de votre commande?");
        Shop.getScene().getRoot().setEffect(effect);
        grid.add(lbl, 2, 2);
        ButtonType Confirmation = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
        ButtonType Annuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(Confirmation);
        dialog.getDialogPane().getButtonTypes().add(Annuler);
        dialog.setResultConverter(new Callback() {
            @Override
            public Object call(Object param) {
                if (param == Confirmation) {
                    LignedecommandeService lcs = new LignedecommandeService();
                    ProduitsService ps = new ProduitsService();
                    Produits p = ps.findProduitsByName(Labels.getSelectionModel().getSelectedItem().getText());
                    dialog.close();
                    Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
                    cancelButton.fire();
                    lcs.supprimerLigneDecommande(p.getIdProduit());

                }
                return null;
            }
        });
        dialog.showAndWait();
        if (!dialog.isShowing()) {
            this.Shop.getScene().getRoot().setEffect(null);
        }
    }

    @FXML
    private void ModifierItem(ActionEvent event) {
    }
}
