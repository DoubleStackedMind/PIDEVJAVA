/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import edu.russie2018.entities.Lignedecommande;
import edu.russie2018.entities.Produits;
import edu.russie2018.services.LignedecommandeService;
import edu.russie2018.services.ProduitsService;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class CommandeController implements Initializable {

    @FXML
    private ScrollPane sp;

    public static float prixt;
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

    private int i = 0;
    @FXML
    private Button Pay;
    @FXML
    private JFXButton close;
    @FXML
    private JFXButton minimize;
    @FXML
    private JFXButton maximize;
    @FXML
    private JFXButton Commandes;
    @FXML
    private JFXButton Tick;
    @FXML
    private JFXButton Tous;
    @FXML
    private JFXButton Maillots;
    @FXML
    private JFXButton Accessoires;
    @FXML
    private JFXNodesList node;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Pay.getStylesheets().add(getClass().getResource("commande.css").toExternalForm());
        Popup.setVisible(false);
        Labels.setExpanded(true);
        Labels.depthProperty().set(1);
        Labels.getStylesheets().add(getClass().getResource("commande.css").toExternalForm());
      //  Labels.setStyle("-fx-background-color: transparent;");
        sp.getStylesheets().add(getClass().getResource("commande.css").toExternalForm());
        Labels.setPrefSize(600, 600);
        sp.setContent(Labels);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        displayCommandes();
        MouseAction();
        node.addAnimatedNode(Shop);
        node.addAnimatedNode(Tous);
        node.addAnimatedNode(Maillots);
        node.addAnimatedNode(Accessoires);
    }

    public void displayCommandes() {
        LignedecommandeService lcs = new LignedecommandeService();
        Map<Integer, List<Lignedecommande>> myMap = new HashMap<>();
        if (lcs.ConsulterLigneDeCommandes().isEmpty()) {
            Label label = new Label("Pas de commande trouvé ..");
            Labels.getItems().add(label);
        }
        myMap.putAll(lcs.ConsulterLigneDeCommandes());

        for (Integer x : myMap.keySet()) {
            Label lb = new Label("Commande : " + i);
            Labels.getItems().add(lb);
            for (Lignedecommande e : myMap.get(x)) {
                ProduitsService ps = new ProduitsService();
                Produits p = new Produits();
                p = ps.getProduitsById(e.getIdProduit());
                Label lbl = new Label(String.valueOf(p.getNom().get()));
                Label plbl = new Label(String.valueOf(e.getPrix()));
                plbl.setFont(Font.font("Futura Condensed Oblique"));
                lbl.setFont(Font.font("Futura Condensed Oblique"));
                lbl.setPrefWidth(300);
                plbl.setId("Pricelabel");
                lbl.setGraphic(plbl);
                lbl.setContentDisplay(ContentDisplay.RIGHT);
                lbl.setGraphicTextGap(Labels.getWidth() + 150);
                Labels.getItems().add(lbl);
                prix = prix + (e.getPrix() * e.getQuantite());

                PrixTotal.setText(String.valueOf(prix));
            }
            i++;
        }
//        myMap.values().stream().reduce((a,b) -> { a.addAll(b) ; return a; }).get().stream().forEach(e -> {
// 
//
//          Label lb = new Label("Commande : " + i);
//            Labels.getItems().add(lb); 
//ProduitsService ps = new ProduitsService();
//
//                Label lbl = new Label(String.valueOf(e.getIdProduit()));
//                Label plbl = new Label(String.valueOf(e.getPrix()));
//                plbl.setFont(Font.font("Futura Condensed Oblique"));
//                lbl.setFont(Font.font("Futura Condensed Oblique"));
//                plbl.setTextAlignment(TextAlignment.CENTER);
//                plbl.setId("Pricelabel");
//                lbl.setGraphic(plbl);
//                lbl.setContentDisplay(ContentDisplay.RIGHT);
//                lbl.setGraphicTextGap(Labels.getWidth() + 200);
//
//                Labels.getItems().add(lbl);
//                prix = prix + (e.getPrix() * e.getQuantite());
//            
//            i++;
//            PrixTotal.setText(String.valueOf(prix));
//        
//        });
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

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

    @FXML
    public void CreatePayment(ActionEvent event) {
        try {
            prixt = Float.valueOf(PrixTotal.getText());

            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("Payment.fxml"));

            Scene newScene = new Scene(SecondView);
            Stage currStage = (Stage) Commandes.getScene().getWindow();
            currStage.setScene(newScene);
//            DecimalFormat df = new DecimalFormat("##.##");
//
//            Stripe.apiKey = "sk_test_lQaCLxOqSJhbeLGE3G5OPEVO";
//
//            //
//            Map<String, Object> chargeParams = new HashMap<>();
//            chargeParams.put("amount", Integer.parseInt(String.valueOf(df.format(Integer.parseInt(PrixTotal.getText())))));
//            chargeParams.put("currency", "cad");
//            chargeParams.put("description", "test@esprit.tn");
//            chargeParams.put("source", "tok_mastercard");
//
//            RequestOptions rs = RequestOptions.builder().setIdempotencyKey("cus_" + getSaltString()).build();
//
//            Charge.create(chargeParams, rs);
//
//// Token is created using Checkout or Elements!
//// Get the payment token ID submitted by the form:
//Map<String, Object> customerParams = new HashMap<String, Object>();
//customerParams.put("description", "Customer for emma.thompson@example.com");
//customerParams.put("source", "tok_amex");
//// ^ obtained with Stripe.js
//Customer.create(customerParams);
//
//            Map<String, Object> params = new HashMap<>();
//            params.put("amount", 999);
//            params.put("currency", "usd");
//            params.put("description", "Example charge");
//            params.put("source", ServiceUser.currentUser.getEmail());
//            params.put("capture", false);
//            Charge charge = Charge.create(params);
     //   } catch (APIException | CardException | APIConnectionException | InvalidRequestException | AuthenticationException ex) {
       //     Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        Stage w = (Stage) minimize.getScene().getWindow();
        w.setIconified(true);
    }

    @FXML
    private void maximizeWindow(ActionEvent event) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Stage primaryStage = (Stage) maximize.getScene().getWindow();
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
    private void showCommandes(ActionEvent event) {
        try {
            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("Commande.fxml"));
            Scene newScene = new Scene(SecondView);
            Stage currStage = (Stage) Commandes.getScene().getWindow();
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
            Stage currStage = (Stage) Tick.getScene().getWindow();
            currStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showAll(ActionEvent event) {
        try {
            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("TousLesProduits.fxml"));
            Scene newScene = new Scene(SecondView);
            Stage currStage = (Stage) Tous.getScene().getWindow();
            currStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showMaillots(ActionEvent event) {
        try {
            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("AfficherMaillots.fxml"));
            Scene newScene = new Scene(SecondView);
            Stage currStage = (Stage) Maillots.getScene().getWindow();
            currStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showAccessoires(ActionEvent event) {
        try {
            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("AfficherAccessoires.fxml"));
            Scene newScene = new Scene(SecondView);
            Stage currStage = (Stage) Accessoires.getScene().getWindow();
            currStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
