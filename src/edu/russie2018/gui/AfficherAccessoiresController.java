/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import edu.russie2018.entities.Produits;
import edu.russie2018.services.CommandesService;
import edu.russie2018.services.PanierService;
import edu.russie2018.services.ProduitsService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.round;
import java.net.URL;
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
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class AfficherAccessoiresController implements Initializable {

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
    Pagination page = new Pagination(round((ps.consulterProduitsParType("Accessoires").size() / 6) + 1));
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

    Map<Produits, Integer> myMap = PanierService.lc.getLignedeCommande();
    @FXML
    private AnchorPane PanierAnchor;
    @FXML
    private Pane PaneTag1;
    @FXML
    private ImageView ImageTag;
    @FXML
    private Label NameTag;
    @FXML
    private Label PriceTag;
    @FXML
    private JFXNodesList ns;
    @FXML
    private JFXButton ValiderPanier;
    @FXML
    private JFXButton DisplayCommandes;
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
    private JFXButton Shop1;
    @FXML
    private JFXButton Shop11;
    @FXML
    private ScrollPane ScrollP;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TabPane.getStylesheets().add(getClass().getResource("affichermaillots.css").toExternalForm());
        AddToCart1.getStylesheets().add(getClass().getResource("affichermaillots.css").toExternalForm());
        Node.addAnimatedNode(Shop);
        Node.addAnimatedNode(Tous);
        Node.addAnimatedNode(Maillots);
        Node.addAnimatedNode(Chaussures);
        Node.addAnimatedNode(Accessoires);
        Node.setSpacing(5);
        ns.addAnimatedNode(ConsulterPanier);
        ScrollP.setContent(PanierAnchor);
        ScrollP.setPrefSize(1000, 200);
        ScrollP.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        ScrollP.setFitToHeight(true);
        ns.addAnimatedNode(ScrollP);

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
                    DisplayAllProducts(ps.consulterProduitsParType("Accessoires"), 0);
                }
                if (Integer.parseInt(newValue) == 1) {
                    DisplayAllProducts(ps.consulterProduitsParType("Accessoires"), 6);
                }
                if (Integer.parseInt(newValue) == 2) {
                    DisplayAllProducts(ps.consulterProduitsParType("Accessoires"), 12);
                }
                if (Integer.parseInt(newValue) == 3) {
                    DisplayAllProducts(ps.consulterProduitsParType("Accessoires"), 18);
                }
                if (Integer.parseInt(newValue) == 4) {
                    DisplayAllProducts(ps.consulterProduitsParType("Accessoires"), 24);
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
                                lc.ajouterLigneDeCommande(myList.get(CurrentPage), 1);
                                int value = Integer.parseInt(PanierNUM.getText());
                                value++;
                                PanierNUM.setText(String.valueOf(value));
                            }
                        });
                        image1.setImage(img1);
                        label1.setText(myList.get(CurrentPage).getNom().getValue());
                        Details1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
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
                    if (myList.get(i) != null) {
                        Details2.setVisible(true);
                        AddToCart2.setVisible(true);
                        Image img2 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 1).getImage().getValue()), 688, 688, false, false);
                        AddToCart2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                lc.ajouterLigneDeCommande(myList.get(CurrentPage + 1), 1);
                                int value = Integer.parseInt(PanierNUM.getText());
                                value++;
                                PanierNUM.setText(String.valueOf(value));
                            }
                        });
                        image2.setImage(img2);
                        label2.setText(myList.get(CurrentPage + 1).getNom().getValue());
                        Details2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
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

                    if (myList.get(i) != null) {
                        Details3.setVisible(true);
                        AddToCart3.setVisible(true);
                        Image img3 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 2).getImage().getValue()), 688, 688, false, false);
                        AddToCart3.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                            }
                        });
                        image3.setImage(img3);
                        label3.setText(myList.get(CurrentPage + 2).getNom().getValue());
                        Details3.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
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
                    if (myList.get(i) != null) {
                        Details4.setVisible(true);
                        AddToCart4.setVisible(true);
                        Image img4 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 3).getImage().getValue()), 688, 688, false, false);
                        AddToCart4.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                            }
                        });
                        image4.setImage(img4);
                        label4.setText(myList.get(CurrentPage + 3).getNom().getValue());
                        Details4.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
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

                    if (myList.get(i) != null) {
                        Details5.setVisible(true);
                        AddToCart5.setVisible(true);
                        Image img5 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 4).getImage().getValue()), 688, 688, false, false);
                        AddToCart5.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                            }
                        });
                        image5.setImage(img5);
                        label5.setText(myList.get(CurrentPage + 4).getNom().getValue());
                        Details5.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println(myList.get(CurrentPage + 4).getIdProduit());
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

                    if (myList.get(i) != null) {
                        Details6.setVisible(true);
                        AddToCart6.setVisible(true);
                        Image img6 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 5).getImage().getValue()), 688, 688, false, false);
                        AddToCart6.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                            }
                        });
                        image6.setImage(img6);
                        label6.setText(myList.get(CurrentPage + 5).getNom().getValue());
                        Details6.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
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

        for (Produits p : myMap.keySet()) {
            try {
                ImageTag.setImage(new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + p.getImage().get())));
                NameTag.setText(p.getNom().get());
                PriceTag.setText(String.valueOf(p.getPrix()));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }

    @FXML
    private void ValiderPanier(ActionEvent event) {
        myMap.keySet().stream().forEach(e -> e.setQuantite(myMap.get(e)));
        CommandesService cs = new CommandesService();
        cs.ajouterCommande(myMap.keySet());
    }

    @FXML
    private void DisplayComm(ActionEvent event) {
//        CommandesService cs = new CommandesService();
//        List<Commandes> myList = cs.consulterCommandes();
//        ObjectMapper mapper = new ObjectMapper();
//        for(Commandes c : myList)
//        {
//            try {
//                Commandes com = mapper.readValue(c.getCommandes(), Commandes.class);
//                System.out.println(com);
//            } catch (IOException ex) {
//                Logger.getLogger(TousLesProduitsController.class.getName()).log(Level.SEVERE, null, ex);
//            }
    }

    @FXML
    private void closeDetails(ActionEvent event) {
        Details.setVisible(false);
        VB.getChildren().clear();
    }

    @FXML
    private void showAccessoires(ActionEvent event) {
        try {
            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("AfficherAccessoires.fxml"));
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
            Stage currStage = (Stage) Tous.getScene().getWindow();
            currStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showChaussures(ActionEvent event) {
        try {
            Parent SecondView;
            SecondView = (Pane) FXMLLoader.load(getClass().getResource("AfficherChassures.fxml"));
            Scene newScene = new Scene(SecondView);
            Stage currStage = (Stage) Tous.getScene().getWindow();
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

}
