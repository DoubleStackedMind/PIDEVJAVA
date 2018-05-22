/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import edu.russie2018.services.ServiceUser;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class PaymentController implements Initializable {

    @FXML
    private JFXTextField OrderN;
    @FXML
    private Label PriceTag;
    @FXML
    private JFXTextField Name;
    @FXML
    private ComboBox<String> Months = new ComboBox<>();
    @FXML
    private ComboBox<String> Years = new ComboBox<>();
    @FXML
    private TextField cvv;
    @FXML
    private Button Payement;
    @FXML
    private Button CloseButton;
    @FXML
    private Button MinimizeButton;
    @FXML
    private Button maximizeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Name.setText(ServiceUser.currentUser.getLast_name() + ServiceUser.currentUser.getFirst_name());
        Months.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "Nouvember", "December");
        Years.getItems().addAll("2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
        Name.setText("Sami Asfouri");
        Name.setStyle("-fx-text-fill: white;");
        Name.setDisable(true);
        OrderN.setText(getSaltInteger());
        OrderN.setStyle("-fx-text-fill: white;");
        OrderN.setDisable(true);
        cvv.focusedProperty().addListener(e -> {
            if (cvv.getText().length() != 3) {
                cvv.setStyle(" -fx-border-color: red;");
                Payement.setDisable(true);
            } else {
                cvv.setStyle(" -fx-border-color: transparent;");
                Payement.setDisable(false);
            }
        });
        PriceTag.setText("$215");

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

    protected String getSaltInteger() {
        String SALTCHARS = "0123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @FXML
    private void createPayment(ActionEvent event) {

        try {
            CommandeController cm = new CommandeController();
            float prix = cm.prixt;
            DecimalFormat df = new DecimalFormat("##.##");

            Stripe.apiKey = "sk_test_lQaCLxOqSJhbeLGE3G5OPEVO";

            //
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", 300);
            chargeParams.put("currency", "cad");
            chargeParams.put("description", ServiceUser.currentUser.getEmail());
            chargeParams.put("source", "tok_mastercard");

            RequestOptions rs = RequestOptions.builder().setIdempotencyKey("cus_" + getSaltString()).build();

            Notifications notifs = Notifications.create()
                    .title("Payment effectué")
                    .text("Votre payment a été effectué avec succées!")
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
            Charge.create(chargeParams, rs);

        } catch (AuthenticationException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRequestException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIConnectionException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CardException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        Stage w = (Stage) MinimizeButton.getScene().getWindow();
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
