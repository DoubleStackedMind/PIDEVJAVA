/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import edu.russie2018.entities.Matches;
import edu.russie2018.entities.Tickets;
import edu.russie2018.services.EquipesService;
import edu.russie2018.services.MatchesService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class ReserverTicketsController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MatchesService ms = new MatchesService();
        EquipesService es = new EquipesService();
        List<Matches> myList = new ArrayList<>();
        myList.addAll(ms.consulterMatches());

        VBox BV = new VBox();
        for (Matches m : myList) {
            try {
                List<String> myStrings = new ArrayList<>();
                myStrings.addAll(es.getNameById(m.getId_match()));
                Pane rootpane = new AnchorPane();
                rootpane.setPrefSize(200, 200);
                rootpane.setLayoutX(19);
                rootpane.setLayoutY(255);
                ImageView Eq1 = new ImageView(new Image(new FileInputStream("C:\\Users\\samia\\Documents\\NetBeansProjects\\PIDEV\\Images\\Flags\\" + myStrings.get(0) + "Drapeau.png")));
                ImageView VSBAR = new ImageView(new Image(new FileInputStream("C:\\Users\\samia\\Documents\\NetBeansProjects\\PIDEV\\Images\\VSBAR.png")));
                String ch = myStrings.get(1).replaceAll("\\s+", "");
                ImageView Eq2 = new ImageView(new Image(new FileInputStream("C:\\Users\\samia\\Documents\\NetBeansProjects\\PIDEV\\Images\\Flags\\" + ch + "Drapeau.png")));
                Eq2.setFitWidth(77);
                Eq2.setFitHeight(73);
                Eq1.setLayoutX(395);
                Eq1.setLayoutY(39);
                Eq2.setFitWidth(77);
                Eq2.setFitHeight(73);
                Eq2.setLayoutX(570);
                Eq2.setLayoutY(39);
                Label nameEq1 = new Label(myStrings.get(0));
                Label nameEq2 = new Label(myStrings.get(1));
                JFXButton VSB = new JFXButton("Réserver");
                VSB.setStyle("-fx-background-image: url('file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/Needy.png') "
                        + "-fx-background-color: transparent; "
                        + "-fx-background-repeat: no-repeat; "
                        + "-fx-backgroud-position: 90% ");
                VSB.setLayoutX(480);
                VSB.setLayoutY(130);
                VSB.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                   
                        
                    }
                });
                nameEq1.setLayoutX(300);
                nameEq1.setLayoutY(50);
                nameEq1.setFont(Font.font("Dusha v5", 30));
                nameEq1.setStyle(" -fx-text-fill: white; ");
                nameEq2.setLayoutX(660);
                nameEq2.setLayoutY(50);
                nameEq2.setFont(Font.font("Dusha v5", 30));
                nameEq2.setStyle(" -fx-text-fill: white; ");

                rootpane.getChildren().addAll(Eq1, Eq2, VSBAR, nameEq1, nameEq2, VSB);
                BV.getChildren().add(rootpane);
            } catch (FileNotFoundException ex) {
                System.out.println("404 - File not found!");  
            }
        }
        
       

        AnchorPane.getChildren().add(BV);
    }

}
