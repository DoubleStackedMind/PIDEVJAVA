/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXColorPicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class CustomizeProductController implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private Rectangle rect;
    @FXML
    private ImageView img11;
    @FXML
    private JFXColorPicker Colors;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 

        ChangeColor();
    }

    public void ChangeColor() {
        Colors.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String hex3 =  Integer.toHexString(Colors.getValue().hashCode()).substring(0, 6).toUpperCase();
                System.out.println(hex3);
                String hex = hex3.replaceAll(hex3, hex3);
                rect.setStyle("-fx-fill: rgb("+(int) Colors.getValue().getRed() * 255+","+(int) Colors.getValue().getGreen()*255+","+(int) Colors.getValue().getBlue()*255+")");
                rect.setStyle("-fx-fill: #"+hex3);
                rect.setBlendMode(BlendMode.SCREEN);
            }
        });

    }

}
