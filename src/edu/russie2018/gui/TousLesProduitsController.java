/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import edu.russie2018.entities.Lignedecommande;
import edu.russie2018.entities.Produits;
import edu.russie2018.services.CommandesService;
import edu.russie2018.services.PanierService;
import edu.russie2018.services.ProduitsService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.round;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class TousLesProduitsController implements Initializable {

    @FXML
    private Pane rootpane;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXButton minimizeButton;
    @FXML
    private JFXButton maximizeButton;
    @FXML
    private JFXButton closeButton;
    @FXML
    private Pane pane1;
    @FXML
    private ImageView image1;
    @FXML
    private Label label1;
    @FXML
    private Pane pane2;
    @FXML
    private ImageView image2;
    @FXML
    private Label label2;
    @FXML
    private Pane pane3;
    @FXML
    private ImageView image3;
    @FXML
    private Label label3;
    @FXML
    private Pane pane4;
    @FXML
    private ImageView image4;
    @FXML
    private Label label4;
    @FXML
    private Pane pane5;
    @FXML
    private ImageView image5;
    @FXML
    private Label label5;
    @FXML
    private Pane pane6;
    @FXML
    private ImageView image6;
    @FXML
    private Label label6;
    ProduitsService ps = new ProduitsService();
    Pagination page = new Pagination(round((ps.consulterProduits().size() / 6) + 1));
    @FXML
    private JFXButton AddToCart1;
    @FXML
    private JFXButton AddToCart2;
    @FXML
    private JFXButton AddToCart3;
    @FXML
    private JFXButton AddToCart4;
    @FXML
    private JFXButton AddToCart5;
    @FXML
    private JFXButton AddToCart6;

    PanierService lignedeCommande = new PanierService();

    TextField pageNumber = new TextField();
    @FXML
    private Label PanierNUM;
    @FXML
    private JFXButton ConsulterPanier;

    public static Map<Produits, Integer> myMap = PanierService.lc.getLignedeCommande();
    private AnchorPane PanierAnchor;

    private JFXNodesList ns;
    @FXML
    private JFXButton ValiderPanier;
    @FXML
    private JFXButton Tous;
    @FXML
    private JFXButton Chaussures;
    @FXML
    private JFXButton Accessoires;
    @FXML
    private JFXButton Shop;
    @FXML
    private JFXNodesList Node;
    @FXML
    private JFXButton Maillots;
    @FXML
    private JFXButton Details1;
    @FXML
    private AnchorPane Details;
    @FXML
    private ImageView ImageD;
    @FXML
    private VBox VB;
    @FXML
    private Label label;
    @FXML
    private Label marque;
    @FXML
    private JFXButton closeDetails;
    @FXML
    private TabPane TabPane;
    @FXML
    private Label Prix;
    @FXML
    private JFXButton Details2;
    @FXML
    private JFXButton Details3;
    @FXML
    private JFXButton Details4;
    @FXML
    private JFXButton Details5;
    @FXML
    private JFXButton Details6;

    public Image imageName;

    String scene;

    public static int PanNUM = 0;
    @FXML
    private JFXButton CustomizeButton;
    @FXML
    private JFXColorPicker ColorsPicker;
    @FXML
    private ImageView WillChange;
    @FXML
    private Rectangle Colors;
    @FXML
    private ImageView FirstView;
    @FXML
    private ImageView WillNotChange;
    @FXML
    private AnchorPane CustomizePane;
    @FXML
    private JFXButton CloseCustmoize;

    public String imageURL;

    public Boolean color;

    public int qt;
    @FXML
    private AnchorPane AffPanier;
    @FXML
    private ScrollPane ScrollPanier;
    @FXML
    private JFXListView<Label> ListPanier;
    @FXML
    private JFXButton Commandes;
    @FXML
    private JFXButton Tick;

    public static Map<Produits,Integer> myM = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AffPanier.setVisible(false);

        CustomizePane.setVisible(false);

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

        CustomizeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    imageURL = imageURL.replaceFirst(".png", "2.png");
                    String imglogo = imageURL.replaceFirst("2.png", "logo.png");
                    Image custimg = new Image(new FileInputStream("C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/Customize/" + imageURL));
                    Image custlogo = new Image(new FileInputStream("C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/Customize/" + imglogo));
                    FirstView.setImage(imageName);
                    WillChange.setImage(custimg);
                    WillNotChange.setImage(custlogo);
                    WillChange.setVisible(false);
                    WillNotChange.setVisible(false);
                    ColorsPicker.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            WillChange.setVisible(true);
                            WillNotChange.setVisible(true);
                            FirstView.setVisible(false);
                            String hex3 = Integer.toHexString(ColorsPicker.getValue().hashCode()).substring(0, 6).toUpperCase();
                            //Colors.setStyle("-fx-fill: rgb("+(int) Colors.getValue().getRed() * 255+","+(int) Colors.getValue().getGreen()*255+","+(int) Colors.getValue().getBlue()*255+")");
                            Colors.setStyle("-fx-fill: #" + hex3);
                            if (color == true) {
                                Colors.setBlendMode(BlendMode.OVERLAY);
                            } else {
                                Colors.setBlendMode(BlendMode.SCREEN);
                            }
                        }
                    });
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TousLesProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                CustomizePane.setVisible(true);
            }
        });

        TabPane.getStylesheets().add(getClass().getResource("touslesproduits.css").toExternalForm());
        AddToCart1.getStylesheets().add(getClass().getResource("touslesproduits.css").toExternalForm());
        Node.addAnimatedNode(Shop);
        Node.addAnimatedNode(Tous);
        Node.addAnimatedNode(Maillots);
        Node.addAnimatedNode(Chaussures);
        Node.addAnimatedNode(Accessoires);
        Node.setSpacing(5);

        rootpane.setOpacity(0);
        makeFadeIn();

        page.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                pageNumber.setText(Integer.toString(param));
                return new VBox();
            }
        });
        AnchorPane.setTopAnchor(page, 50.0);
        AnchorPane.setBottomAnchor(page, 10.0);
        AnchorPane.setLeftAnchor(page, 10.0);
        AnchorPane.setRightAnchor(page, 10.0);
        AnchorPane.getChildren().add(page);

        pageNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (Integer.parseInt(newValue) == 0) {
                    DisplayAllProducts(ps.consulterProduits(), 0);
                }
                if (Integer.parseInt(newValue) == 1) {
                    DisplayAllProducts(ps.consulterProduits(), 6);
                }
                if (Integer.parseInt(newValue) == 2) {
                    DisplayAllProducts(ps.consulterProduits(), 12);
                }
                if (Integer.parseInt(newValue) == 3) {
                    DisplayAllProducts(ps.consulterProduits(), 18);
                }
                if (Integer.parseInt(newValue) == 4) {
                    DisplayAllProducts(ps.consulterProduits(), 24);
                }

            }
        });

    }

    private void makeFadeIn() {
        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.millis(1000));
        fadeOut.setNode(rootpane);
        fadeOut.setFromValue(0);
        fadeOut.setToValue(1);
        fadeOut.play();
        if(!myMap.isEmpty())
        myM.putAll(myMap);
    }

    public void DisplayAllProducts(List<Produits> myList, int CurrentPage) {

        Details.setVisible(false);

        image1.setImage(null);
        image2.setImage(null);
        image3.setImage(null);
        image4.setImage(null);
        image5.setImage(null);
        image6.setImage(null);

        label1.setText(null);
        label2.setText(null);
        label3.setText(null);
        label4.setText(null);
        label5.setText(null);
        label6.setText(null);

        AddToCart1.setVisible(false);
        AddToCart2.setVisible(false);
        AddToCart3.setVisible(false);
        AddToCart4.setVisible(false);
        AddToCart5.setVisible(false);
        AddToCart6.setVisible(false);

        Details1.setVisible(false);
        Details2.setVisible(false);
        Details3.setVisible(false);
        Details4.setVisible(false);
        Details5.setVisible(false);
        Details6.setVisible(false);

        PanierService lc = new PanierService();
        try {
            for (int i = CurrentPage; i < myList.size(); i++) {
                try {
                    if (myList.get(i) != null) {
                        Details1.setVisible(true);
                        AddToCart1.setVisible(true);
                        Image img1 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage).getImage().getValue()), 688, 688, false, false);
                        AddToCart1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Dialog dialog = new Dialog();
                                TextField tf = new TextField();
                                tf.setPromptText("Quantite");
                                GridPane grid = new GridPane();
                                grid.add(tf, 2, 2);
                                ButtonType Ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().add(Ok);
                                dialog.getDialogPane().setContent(grid);
                                dialog.setResultConverter(new Callback() {
                                    @Override
                                    public Object call(Object param) {
                                        if (param == Ok) {
                                            ProduitsService ps = new ProduitsService();
                                            if (ps.Update_CheckQuantity(myList.get(CurrentPage), Integer.parseInt(tf.getText()))) {
                                                lc.ajouterLigneDeCommande(myList.get(CurrentPage), Integer.parseInt(tf.getText()));
                                                int value = Integer.parseInt(PanierNUM.getText());
                                                value++;
                                                PanNUM = value;
                                                PanierNUM.setText(String.valueOf(value));
                                            } else {
                                                Notifications notifs = Notifications.create()
                                                        .title("Qunatite invalide")
                                                        .text("La quantite que vous avez ajouter n'est pas disponible!")
                                                        .graphic(new ImageView("file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/TickErr.png"))
                                                        .hideAfter(Duration.seconds(5))
                                                        .position(Pos.BOTTOM_RIGHT);

                                                notifs.darkStyle();
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        notifs.show();
                                                    }
                                                });
                                            }
                                        }
                                        return null;
                                    }
                                });
                                dialog.showAndWait();
                            }
                        });
                        image1.setImage(img1);
                        label1.setText(myList.get(CurrentPage).getNom().getValue());
                        label1.setWrapText(true);
                        Details1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                color = myList.get(CurrentPage).getCouleur().get().toUpperCase().contains("BLANC") || myList.get(CurrentPage).getCouleur().get().toUpperCase().contains("#FFFFFF");
                                imageName = img1;
                                imageURL = myList.get(CurrentPage).getImage().getValue();
                                ImageD.setImage(img1);
                                Label desc = new Label(myList.get(CurrentPage).getDescription().get());
                                Label mrq = new Label(myList.get(CurrentPage).getMarque().get());
                                desc.setWrapText(true);
                                Label prixTag = new Label(String.valueOf(myList.get(CurrentPage).getPrix()) + "");
                                VB.setSpacing(5);
                                VB.getChildren().addAll(label, desc, marque, mrq, Prix, prixTag);
                                Details.setVisible(true);
                            }
                        });
                    } else {

                        image1.setImage(null);
                        label1.setText("");
                    }
                    if (myList.get(i + 1) != null) {
                        Details2.setVisible(true);
                        AddToCart2.setVisible(true);
                        Image img2 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 1).getImage().getValue()), 688, 688, false, false);

                        AddToCart2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                               Dialog dialog = new Dialog();
                                TextField tf = new TextField();
                                tf.setPromptText("Quantite");
                                GridPane grid = new GridPane();
                                grid.add(tf, 2, 2);
                                ButtonType Ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().add(Ok);
                                dialog.getDialogPane().setContent(grid);
                                dialog.setResultConverter(new Callback() {
                                    @Override
                                    public Object call(Object param) {
                                        if (param == Ok) {
                                            ProduitsService ps = new ProduitsService();
                                            if (ps.Update_CheckQuantity(myList.get(CurrentPage+1), Integer.parseInt(tf.getText()))) {
                                                lc.ajouterLigneDeCommande(myList.get(CurrentPage+1), Integer.parseInt(tf.getText()));
                                                int value = Integer.parseInt(PanierNUM.getText());
                                                value++;
                                                PanNUM = value;
                                                PanierNUM.setText(String.valueOf(value));
                                            } else {
                                                Notifications notifs = Notifications.create()
                                                        .title("Qunatite invalide")
                                                        .text("La quantite que vous avez ajouter n'est pas disponible!")
                                                        .graphic(new ImageView("file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/TickErr.png"))
                                                        .hideAfter(Duration.seconds(5))
                                                        .position(Pos.BOTTOM_RIGHT);

                                                notifs.darkStyle();
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        notifs.show();
                                                    }
                                                });
                                            }
                                        }
                                        return null;
                                    }
                                });
                                dialog.showAndWait();
                            }
                        });
                        image2.setImage(img2);
                        label2.setText(myList.get(CurrentPage + 1).getNom().getValue());
                        label2.setWrapText(true);
                        Details2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                color = myList.get(CurrentPage + 1).getCouleur().get().toUpperCase().contains("BLANC") || myList.get(CurrentPage + 1).getCouleur().get().toUpperCase().contains("#FFFFFF");
                                imageName = img2;
                                imageURL = myList.get(CurrentPage + 1).getImage().getValue();
                                ImageD.setImage(img2);
                                Label desc = new Label(myList.get(CurrentPage + 1).getDescription().get());
                                Label mrq = new Label(myList.get(CurrentPage + 1).getMarque().get());
                                desc.setWrapText(true);
                                Label prixTag = new Label(String.valueOf(myList.get(CurrentPage + 1).getPrix()) + " ‎₽");
                                VB.setSpacing(5);
                                VB.getChildren().addAll(label, desc, marque, mrq, Prix, prixTag);
                                Details.setVisible(true);
                            }
                        });
                    } else {
                        image2.setImage(null);
                        label2.setText("");
                    }

                    if (myList.get(i + 2) != null) {
                        Details3.setVisible(true);
                        AddToCart3.setVisible(true);
                        Image img3 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 2).getImage().getValue()), 688, 688, false, false);
                        AddToCart3.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Dialog dialog = new Dialog();
                                TextField tf = new TextField();
                                tf.setPromptText("Quantite");
                                GridPane grid = new GridPane();
                                grid.add(tf, 2, 2);
                                ButtonType Ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().add(Ok);
                                dialog.getDialogPane().setContent(grid);
                                dialog.setResultConverter(new Callback() {
                                    @Override
                                    public Object call(Object param) {
                                        if (param == Ok) {
                                            ProduitsService ps = new ProduitsService();
                                            if (ps.Update_CheckQuantity(myList.get(CurrentPage+2), Integer.parseInt(tf.getText()))) {
                                                lc.ajouterLigneDeCommande(myList.get(CurrentPage+2), Integer.parseInt(tf.getText()));
                                                int value = Integer.parseInt(PanierNUM.getText());
                                                value++;
                                                PanNUM = value;
                                                PanierNUM.setText(String.valueOf(value));
                                            } else {
                                                Notifications notifs = Notifications.create()
                                                        .title("Qunatite invalide")
                                                        .text("La quantite que vous avez ajouter n'est pas disponible!")
                                                        .graphic(new ImageView("file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/TickErr.png"))
                                                        .hideAfter(Duration.seconds(5))
                                                        .position(Pos.BOTTOM_RIGHT);

                                                notifs.darkStyle();
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        notifs.show();
                                                    }
                                                });
                                            }
                                        }
                                        return null;
                                    }
                                });
                                dialog.showAndWait();

                            }
                        });
                        image3.setImage(img3);
                        label3.setText(myList.get(CurrentPage + 2).getNom().getValue());
                        label3.setWrapText(true);
                        Details3.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                color = myList.get(CurrentPage + 2).getCouleur().get().toUpperCase().contains("BLANC") || myList.get(CurrentPage + 2).getCouleur().get().toUpperCase().contains("#FFFFFF");
                                imageName = img3;
                                imageURL = myList.get(CurrentPage + 2).getImage().getValue();
                                ImageD.setImage(img3);
                                Label desc = new Label(myList.get(CurrentPage + 2).getDescription().get());
                                Label mrq = new Label(myList.get(CurrentPage + 2).getMarque().get());
                                desc.setWrapText(true);
                                Label prixTag = new Label(String.valueOf(myList.get(CurrentPage + 2).getPrix()) + " ‎₽");
                                VB.setSpacing(5);
                                VB.getChildren().addAll(label, desc, marque, mrq, Prix, prixTag);
                                Details.setVisible(true);
                            }
                        });

                    } else {
                        label3.setText("");
                        image3.setImage(null);
                    }
                    if (myList.get(i + 3) != null) {
                        Details4.setVisible(true);
                        AddToCart4.setVisible(true);
                        Image img4 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 3).getImage().getValue()), 688, 688, false, false);
                        AddToCart4.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                              Dialog dialog = new Dialog();
                                TextField tf = new TextField();
                                tf.setPromptText("Quantite");
                                GridPane grid = new GridPane();
                                grid.add(tf, 2, 2);
                                ButtonType Ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().add(Ok);
                                dialog.getDialogPane().setContent(grid);
                                dialog.setResultConverter(new Callback() {
                                    @Override
                                    public Object call(Object param) {
                                        if (param == Ok) {
                                            ProduitsService ps = new ProduitsService();
                                            if (ps.Update_CheckQuantity(myList.get(CurrentPage+3), Integer.parseInt(tf.getText()))) {
                                                lc.ajouterLigneDeCommande(myList.get(CurrentPage+3), Integer.parseInt(tf.getText()));
                                                int value = Integer.parseInt(PanierNUM.getText());
                                                value++;
                                                PanNUM = value;
                                                PanierNUM.setText(String.valueOf(value));
                                            } else {
                                                Notifications notifs = Notifications.create()
                                                        .title("Qunatite invalide")
                                                        .text("La quantite que vous avez ajouter n'est pas disponible!")
                                                        .graphic(new ImageView("file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/TickErr.png"))
                                                        .hideAfter(Duration.seconds(5))
                                                        .position(Pos.BOTTOM_RIGHT);

                                                notifs.darkStyle();
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        notifs.show();
                                                    }
                                                });
                                            }
                                        }
                                        return null;
                                    }
                                });
                                dialog.showAndWait();
                            }
                        });
                        image4.setImage(img4);
                        label4.setText(myList.get(CurrentPage + 3).getNom().getValue());
                        label4.setWrapText(true);
                        Details4.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                color = myList.get(CurrentPage + 3).getCouleur().get().toUpperCase().contains("BLANC") || myList.get(CurrentPage + 3).getCouleur().get().toUpperCase().contains("#FFFFFF");
                                imageName = img4;
                                imageURL = myList.get(CurrentPage + 3).getImage().getValue();
                                imageName = img4;
                                imageURL = myList.get(CurrentPage + 3).getImage().getValue();
                                ImageD.setImage(img4);
                                Label desc = new Label(myList.get(CurrentPage + 3).getDescription().get());
                                Label mrq = new Label(myList.get(CurrentPage + 3).getMarque().get());
                                desc.setWrapText(true);
                                Label prixTag = new Label(String.valueOf(myList.get(CurrentPage + 3).getPrix()) + " ‎₽");
                                VB.setSpacing(5);
                                VB.getChildren().addAll(label, desc, marque, mrq, Prix, prixTag);
                                Details.setVisible(true);

                            }
                        });
                    } else {

                        image4.setImage(null);
                        label4.setText(null);
                    }

                    if (myList.get(i + 4) != null) {
                        Details5.setVisible(true);
                        AddToCart5.setVisible(true);
                        Image img5 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 4).getImage().getValue()), 688, 688, false, false);
                        AddToCart5.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                               Dialog dialog = new Dialog();
                                TextField tf = new TextField();
                                tf.setPromptText("Quantite");
                                GridPane grid = new GridPane();
                                grid.add(tf, 2, 2);
                                ButtonType Ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().add(Ok);
                                dialog.getDialogPane().setContent(grid);
                                dialog.setResultConverter(new Callback() {
                                    @Override
                                    public Object call(Object param) {
                                        if (param == Ok) {
                                            ProduitsService ps = new ProduitsService();
                                            if (ps.Update_CheckQuantity(myList.get(CurrentPage+4), Integer.parseInt(tf.getText()))) {
                                                lc.ajouterLigneDeCommande(myList.get(CurrentPage+4), Integer.parseInt(tf.getText()));
                                                int value = Integer.parseInt(PanierNUM.getText());
                                                value++;
                                                PanNUM = value;
                                                PanierNUM.setText(String.valueOf(value));
                                            } else {
                                                Notifications notifs = Notifications.create()
                                                        .title("Qunatite invalide")
                                                        .text("La quantite que vous avez ajouter n'est pas disponible!")
                                                        .graphic(new ImageView("file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/TickErr.png"))
                                                        .hideAfter(Duration.seconds(5))
                                                        .position(Pos.BOTTOM_RIGHT);

                                                notifs.darkStyle();
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        notifs.show();
                                                    }
                                                });
                                            }
                                        }
                                        return null;
                                    }
                                });
                                dialog.showAndWait();

                            }
                        });
                        image5.setImage(img5);
                        label5.setText(myList.get(CurrentPage + 4).getNom().getValue());
                        label5.setWrapText(true);
                        Details5.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                color = myList.get(CurrentPage + 4).getCouleur().get().toUpperCase().contains("BLANC") || myList.get(CurrentPage + 4).getCouleur().get().toUpperCase().contains("#FFFFFF");
                                imageName = img5;
                                imageURL = myList.get(CurrentPage + 4).getImage().getValue();
                                ImageD.setImage(img5);
                                Label desc = new Label(myList.get(CurrentPage + 4).getDescription().get());
                                Label mrq = new Label(myList.get(CurrentPage + 4).getMarque().get());
                                desc.setWrapText(true);
                                Label prixTag = new Label(String.valueOf(myList.get(CurrentPage + 4).getPrix()) + " ‎₽");
                                VB.setSpacing(5);
                                VB.getChildren().addAll(label, desc, marque, mrq, Prix, prixTag);
                                Details.setVisible(true);
                            }
                        });
                    } else {
                        image5.setImage(null);
                        label5.setText("");
                    }

                    if (myList.get(i + 5) != null) {
                        Details6.setVisible(true);
                        AddToCart6.setVisible(true);
                        Image img6 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 5).getImage().getValue()), 688, 688, false, false);
                        AddToCart6.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                              Dialog dialog = new Dialog();
                                TextField tf = new TextField();
                                tf.setPromptText("Quantite");
                                GridPane grid = new GridPane();
                                grid.add(tf, 2, 2);
                                ButtonType Ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().add(Ok);
                                dialog.getDialogPane().setContent(grid);
                                dialog.setResultConverter(new Callback() {
                                    @Override
                                    public Object call(Object param) {
                                        if (param == Ok) {
                                            ProduitsService ps = new ProduitsService();
                                            if (ps.Update_CheckQuantity(myList.get(CurrentPage+5), Integer.parseInt(tf.getText()))) {
                                                lc.ajouterLigneDeCommande(myList.get(CurrentPage+5), Integer.parseInt(tf.getText()));
                                                int value = Integer.parseInt(PanierNUM.getText());
                                                value++;
                                                PanNUM = value;
                                                PanierNUM.setText(String.valueOf(value));
                                            } else {
                                                Notifications notifs = Notifications.create()
                                                        .title("Qunatite invalide")
                                                        .text("La quantite que vous avez ajouter n'est pas disponible!")
                                                        .graphic(new ImageView("file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/TickErr.png"))
                                                        .hideAfter(Duration.seconds(5))
                                                        .position(Pos.BOTTOM_RIGHT);

                                                notifs.darkStyle();
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        notifs.show();
                                                    }
                                                });
                                            }
                                        }
                                        return null;
                                    }
                                });
                                dialog.showAndWait();
                            }
                        });
                        image6.setImage(img6);
                        label6.setText(myList.get(CurrentPage + 5).getNom().getValue());
                        label6.setWrapText(true);
                        Details6.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                color = myList.get(CurrentPage + 5).getCouleur().get().toUpperCase().contains("BLANC") || myList.get(CurrentPage + 5).getCouleur().get().toUpperCase().contains("#FFFFFF");
                                imageName = img6;
                                imageURL = myList.get(CurrentPage + 5).getImage().getValue();
                                ImageD.setImage(img6);
                                Label desc = new Label(myList.get(CurrentPage + 5).getDescription().get());
                                Label mrq = new Label(myList.get(CurrentPage + 5).getMarque().get());
                                desc.setWrapText(true);
                                Label prixTag = new Label(String.valueOf(myList.get(CurrentPage + 5).getPrix()) + " ‎₽");
                                VB.setSpacing(5);
                                VB.getChildren().addAll(label, desc, marque, mrq, Prix, prixTag);
                                Details.setVisible(true);
                            }
                        });
                    } else {
                        image6.setImage(null);
                        label6.setText("");
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println("File does not exist");
                }

            }
        } catch (Exception e) {

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

    @FXML
    private void AfficherPanier(ActionEvent event) {

        if (myMap.isEmpty()) {

            Notifications notifs = Notifications.create()
                    .title("Panier")
                    .text("Votre Panier est vide!")
                    .graphic(new ImageView("file:C:/Users/samia/Documents/NetBeansProjects/PIDEV/Images/TickErr.png"))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);

            notifs.darkStyle();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    notifs.show();
                }
            });

        }
        else {
        AffPanier.setVisible(true);
        AnchorPane.setVisible(false);
        for (Produits p : myMap.keySet()) {
            try {
                ImageView ImageTag = new ImageView();
                Label NameTag = new Label();

                ImageTag.setImage(new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + p.getImage().get())));
                ImageTag.setFitHeight(50);
                ImageTag.setFitWidth(50);
                NameTag.setText(p.getNom().get() + " " + String.valueOf(p.getPrix()));
                NameTag.setGraphic(ImageTag);
                NameTag.setGraphicTextGap(20);

                AffPanier.setStyle("-fx-background-color: transparent");
                ListPanier.setStyle("-fx-background-color: transparent");
                ListPanier.getItems().add(NameTag);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        }
        }

    }

    @FXML
    private void ValiderPanier(ActionEvent event) {
        try {
            myMap.keySet().stream().forEach(e -> e.setQuantite(myMap.get(e)));
            CommandesService cs = new CommandesService();
            cs.ajouterCommande(myMap.keySet());
            myMap.clear();
            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("Commande.fxml"));
            
            Scene newScene = new Scene(SecondView);
            Stage currStage = (Stage) rootpane.getScene().getWindow();
            currStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(TousLesProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeDetails(ActionEvent event) {
        Details.setVisible(false);
        VB.getChildren().clear();
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
    private void CloseCustmoizePane(ActionEvent event) {
        CustomizePane.setVisible(false);

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

    @FXML
    private void closePanier(ActionEvent event) {
        AffPanier.setVisible(false);
        AnchorPane.setVisible(true);
    }
}
