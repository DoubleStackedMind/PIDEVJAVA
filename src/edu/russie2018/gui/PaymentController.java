/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class PaymentController implements Initializable {

    @FXML
    private JFXButton Pay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    @FXML
    private void createPayment(ActionEvent event) {
        
        try {
            CommandeController cm = new CommandeController();
            float prix = cm.prixt;
            DecimalFormat df = new DecimalFormat("##.##");
            
            Stripe.apiKey = "sk_test_lQaCLxOqSJhbeLGE3G5OPEVO";

            //
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", df.format(prix));
            chargeParams.put("currency", "cad");
            chargeParams.put("description", "test@esprit.tn");
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
    
}
