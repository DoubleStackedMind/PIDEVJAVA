/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import edu.russie2018.entities.Produits;
import edu.russie2018.services.ProduitsService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.Integer.min;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

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
//    @FXML
//    private JFXListView<Label> ListView;
//
//    Label ProductName = new Label();
//
//    ImageView ProductImage = new ImageView();

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
    Pagination page = new Pagination(10);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootpane.setOpacity(0);
        makeFadeIn();
        ProduitsService ps = new ProduitsService();
        DisplayAllProducts(ps.consulterProduits(),page.getCurrentPageIndex());

        page.setPageFactory(pageIndex -> createPage(pageIndex));
        AnchorPane.setTopAnchor(page, 50.0);
        AnchorPane.setBottomAnchor(page, 10.0);
        AnchorPane.setLeftAnchor(page, 10.0);
        AnchorPane.setRightAnchor(page, 10.0);

        AnchorPane.getChildren().add(page);
        
    }

    private void makeFadeIn() {
        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.millis(1000));
        fadeOut.setNode(rootpane);
        fadeOut.setFromValue(0);
        fadeOut.setToValue(1);
        fadeOut.play();
    }

    public void DisplayAllProducts(List<Produits> myList,int CurrentPage) {
        
        try {
            if(myList.isEmpty())
            {
                
            }
           
          if(myList.size()>=1) {
            Image img1=new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/"+myList.get(CurrentPage).getImage().getValue()),520,300,false,false);
            AddToCart1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                
                }
            });
         image1.setImage(img1);
        label1.setText(myList.get(CurrentPage).getNom().getValue());
        
          }
          if(myList.size() >= 2) {
            Image img2=new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/"+myList.get(CurrentPage+1).getImage().getValue()),520,300,false,false);
            AddToCart2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
               
                }
            });
            image2.setImage(img2);
        label2.setText(myList.get(CurrentPage+1).getNom().getValue());
        
          }
          
          if(myList.size() >= 3) {
            Image img3=new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/"+myList.get(CurrentPage+2).getImage().getValue()),520,300,false,false);
            AddToCart3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
               
                }
            });
            image3.setImage(img3);
        label3.setText(myList.get(CurrentPage+2).getNom().getValue());
        
          }
          if(myList.size() >= 4) {
            Image img4=new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/"+myList.get(CurrentPage+3).getImage().getValue()),520,300,false,false);
            AddToCart4.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
               
                }
            });
            image4.setImage(img4);
        label4.setText(myList.get(CurrentPage+3).getNom().getValue());
          }
        
        if(myList.size() >= 5 ) {
            Image img5=new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/"+myList.get(CurrentPage+4).getImage().getValue()),520,300,false,false);
            AddToCart5.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
               
                }
            });
            image5.setImage(img5);
        label5.setText(myList.get(CurrentPage+4).getNom().getValue());
        }
        
        if(myList.size() >= 6 ) {
            Image img6=new Image(new FileInputStream("C:/wamp64/www/PIDEV/web/imagesShop/"+myList.get(CurrentPage+5).getImage().getValue()),520,300,false,false);
            AddToCart6.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
               
                }
            });
            image6.setImage(img6);
        label6.setText(myList.get(CurrentPage+5).getNom().getValue());
        }
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TousLesProduitsController.class.getName()).log(Level.SEVERE, null, ex);
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

 

    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);     
            Label l = new Label("PAGE INDEX " + pageIndex);
            box.getChildren().add(l);
        return box;
    }
}
