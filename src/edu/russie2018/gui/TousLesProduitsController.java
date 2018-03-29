/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import edu.russie2018.entities.Commandes;
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
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
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
import org.codehaus.jackson.map.ObjectMapper;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
        ns.addAnimatedNode(ConsulterPanier);
        ns.addAnimatedNode(PanierAnchor);

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
                if (Integer.parseInt(newValue) == 2) {
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
    }

    public void DisplayAllProducts(List<Produits> myList, int CurrentPage) {

        PanierService lc = new PanierService();
        try {

            if (myList.size() >= CurrentPage) {
                Image img1 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage).getImage().getValue()), 520, 300, false, false);
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
            } else {
                image1.setImage(null);
                label1.setText("");
            }
            if (myList.size() > CurrentPage + 1) {
                Image img2 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 1).getImage().getValue()), 520, 300, false, false);
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
            } else {
                image2.setImage(null);
                label2.setText("");
            }

            if (myList.size() > CurrentPage + 2) {
                Image img3 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 2).getImage().getValue()), 520, 300, false, false);
                AddToCart3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }
                });
                image3.setImage(img3);
                label3.setText(myList.get(CurrentPage + 2).getNom().getValue());

            } else {
                label3.setText("");
                image3.setImage(null);
            }
            if (myList.size() > CurrentPage + 3) {
                Image img4 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 3).getImage().getValue()), 520, 300, false, false);
                AddToCart4.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }
                });
                image4.setImage(img4);
                label4.setText(myList.get(CurrentPage + 3).getNom().getValue());
            } else {
                image4.setImage(null);
                label4.setText("");
            }

            if (myList.size() > CurrentPage + 4) {
                Image img5 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 4).getImage().getValue()), 520, 300, false, false);
                AddToCart5.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }
                });
                image5.setImage(img5);
                label5.setText(myList.get(CurrentPage + 4).getNom().getValue());
            } else {
                image5.setImage(null);
                label5.setText("");
            }

            if (myList.size() > CurrentPage + 5) {
                Image img6 = new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + myList.get(CurrentPage + 5).getImage().getValue()), 520, 300, false, false);
                AddToCart6.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }
                });
                image6.setImage(img6);
                label6.setText(myList.get(CurrentPage + 5).getNom().getValue());
            } else {
                image6.setImage(null);
                label6.setText("");
            }

        } catch (FileNotFoundException ex) {

        }

    }

//    public void DisplayAllProducts() {
//        ProduitsService ps = new ProduitsService();
//        List<Produits> myList = new ArrayList<>();
//        myList.addAll(ps.consulterProduits());
//
//        List<Pane> myListOfPanes = new ArrayList<>();
//        for (Produits p : myList) {
//            try {
//                HBox hb = new HBox();
//                Label lbl = new Label("    "+p.getNom().getValue());
//                FileInputStream f = new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/" + p.getImage().getValue());
//                Image img = new Image(f);
//                ImageView v = new ImageView(img);
//                v.setFitHeight(250);
//                v.setFitWidth(200);
//                lbl.setGraphic(v);
//                
////            File f = new File("C:/wamp64/www/PIDEV/web/imagesShop/" + p.getImage().getValue());
////            Image Img = new Image(f.toURI().toString());
////            ProductImage.setImage(Img);
////            ProductImage.setFitHeight(200);
////            ProductImage.setFitWidth(200);
////            ProductName.setText(p.getNom().getValue());
////            hb.getChildren().add(ProductImage);
////            hb.getChildren().add(ProductName);
////hb.getChildren().add(lbl);
//
//                ListView.getItems().add(lbl);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(TousLesProduitsController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//        ListView.getStylesheets().add(getClass().getResource("touslesproduits.css").toExternalForm());
//        ListView.setExpanded(true);
//        ListView.depthProperty().set(1);
//    }
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
    }

