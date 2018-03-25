/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.russie2018.entities.Produits;
import edu.russie2018.services.ProduitsService;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class CrudProduitsController implements Initializable {

    @FXML
    private JFXButton Ajouter;
    @FXML
    private JFXButton afficherButton;
    @FXML
    private JFXButton modifierProduit;
    @FXML
    private JFXButton supprimerButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterProduit(ActionEvent event) {
        GaussianBlur effect = new GaussianBlur();
        Ajouter.getScene().getRoot().setEffect(effect);
        Dialog<Produits> dialog = new Dialog();
        Label label1 = new Label("Nom : ");
        TextField text1 = new TextField();
        Label label2 = new Label("Prix : ");
        TextField text2 = new TextField();
        Label label3 = new Label("Categorie");
        ComboBox catg = new ComboBox(FXCollections.observableArrayList("Maillot", "Chaussures", "Accessoires"));
        catg.setPromptText("Choisissez une categorie ..");
        Label label4 = new Label("Quantite");
        TextField text4 = new TextField();
        Label img = new Label("Image");
        Button Img = new Button("Ajouter un Image");

        Img.setOnAction((ActionEvent event1) -> {
            String myString = setNewPhotoButton(event1);
            Img.setAccessibleText(myString);
        }
        );
        JFXColorPicker picker = new JFXColorPicker();
        String colour = "#" + Integer.toHexString(picker.getValue().hashCode());
        Label label5 = new Label("Description :");
        TextArea text5 = new TextArea();
        ComboBox marque = new ComboBox(FXCollections.observableArrayList("Nike", "Adidas"));
        marque.setPromptText("Choisissez une marque");
        Label label6 = new Label("Compoisition");
        TextField text6 = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(text2, 2, 2);
        grid.add(label3, 1, 3);
        grid.add(catg, 2, 3);
        grid.add(label4, 1, 4);
        grid.add(text4, 2, 4);
        grid.add(img, 1, 5);
        grid.add(Img, 2, 5);
        Label couleur = new Label("Couleur");
        grid.add(couleur, 1, 6);
        grid.add(picker, 2, 6);
        Label mar = new Label("Marque");
        grid.add(mar, 1, 7);
        grid.add(marque, 2, 7);
        grid.add(label5, 1, 8);
        grid.add(text5, 2, 8);
        grid.add(label6, 1, 9);
        grid.add(text6, 2, 9);
        dialog.getDialogPane().setContent(grid);
        ButtonType Ajouter = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(Ajouter);

        dialog.setResultConverter(new Callback<ButtonType, Produits>() {
            @Override
            public Produits call(ButtonType param) {

                if (param == Ajouter) {

                    Produits P = new Produits(new SimpleStringProperty(text1.getText()), Float.parseFloat(text2.getText()), new SimpleStringProperty(catg.getValue().toString()), new SimpleStringProperty(colour), new SimpleStringProperty(text5.getText()), new SimpleStringProperty(marque.getValue().toString()), new SimpleStringProperty(text6.getText()), Integer.parseInt(text4.getText()), new SimpleStringProperty(Img.getAccessibleText()));
                    ProduitsService ps = new ProduitsService();
                    System.out.println(P);
                    ps.ajouterProduit(P);
                    Notifications notifs = Notifications.create()
                            .title("Produit ajouté")
                            .text("Le produit a été ajouter avec succées!")
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

                    return P;
                }

                return new Produits();
            }
        });
        dialog.showAndWait();
        if (!dialog.isShowing()) {
            this.Ajouter.getScene().getRoot().setEffect(null);
        }
    }

    private String setNewPhotoButton(ActionEvent event) {

        Stage currentStage = (Stage) Ajouter.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisissez une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File f = new File("C:/Users/samia/Desktop");
        fileChooser.setInitialDirectory(f);
        File selectedFile = fileChooser.showOpenDialog(currentStage);

        if (selectedFile != null) {
            //System.out.println("C:/" + selectedFile.getPath());
            //System.out.println("userfiles/"+UNAME+"/"+ANAME+"/");
            File src = new File(selectedFile.getPath());
            File dest = new File("C:/wamp64/www/PIDEV/web/imagesShop/");
            java.nio.file.Path sr = src.toPath();
            java.nio.file.Path ds = new File(dest, src.getName()).toPath();
            File newDes = new File("C:/wamp64/www/PIDEV/web/imagesShop/" + selectedFile.getName());
            selectedFile.renameTo(newDes);
        }
        return "C:/wamp64/www/PIDEV/web/imagesShop/" + selectedFile.getName();
    }

    @FXML
    private void consulterProduits(ActionEvent event) {

        GaussianBlur effect = new GaussianBlur();
        Ajouter.getScene().getRoot().setEffect(effect);

        JFXTreeTableColumn<Produits, String> nomProd = new JFXTreeTableColumn<>("Nom");
        nomProd.setPrefWidth(150);
        nomProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return param.getValue().getValue().getNom();
            }
        });

        JFXTreeTableColumn<Produits, String> prixProd = new JFXTreeTableColumn<>("Prix");
        prixProd.setPrefWidth(250);
        prixProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return new SimpleStringProperty(Float.toString(param.getValue().getValue().getPrix()));
            }
        });

        JFXTreeTableColumn<Produits, String> CatgProd = new JFXTreeTableColumn<>("Categorie");
        CatgProd.setPrefWidth(250);
        CatgProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return param.getValue().getValue().getCategorie();
            }
        });

        JFXTreeTableColumn<Produits, String> QteProd = new JFXTreeTableColumn<>("Quantite");
        QteProd.setPrefWidth(250);
        QteProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getQuantite()));
            }
        });

        JFXTreeTableColumn<Produits, String> MarqueProd = new JFXTreeTableColumn<>("Marque");
        MarqueProd.setPrefWidth(250);
        MarqueProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return param.getValue().getValue().getMarque();
            }
        });

        ProduitsService ps = new ProduitsService();
        List<Produits> myLst;
        myLst = ps.consulterProduits();
        ObservableList<Produits> produits = FXCollections.observableArrayList();

        myLst.forEach(p -> produits.add(p));

        JFXTreeTableView<Produits> treeview = new JFXTreeTableView<>();
        final TreeItem<Produits> root = new RecursiveTreeItem<Produits>(produits, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(nomProd, prixProd, CatgProd, QteProd, MarqueProd);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.getStylesheets().add(getClass().getResource("crudproduits.css").toExternalForm());
        Dialog diag = new Dialog();
        GridPane grid = new GridPane();
        grid.add(treeview, 0, 0);
        TextField input = new TextField();
        input.setPromptText("Rechercher ..");
        grid.add(input, 0, 1);
        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<Produits>>() {
                    @Override
                    public boolean test(TreeItem<Produits> t) {

                        boolean flag = t.getValue().getCategorie().getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
        diag.getDialogPane().setContent(grid);
        ButtonType Ajouter = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        diag.getDialogPane().getButtonTypes().add(Ajouter);

        diag.showAndWait();
        if (!diag.isShowing()) {
            this.Ajouter.getScene().getRoot().setEffect(null);
        }
    }

    @FXML
    private void modifierProduits(ActionEvent event) {

        ProduitsService ps = new ProduitsService();

        JFXTreeTableColumn<Produits, String> nomProd = new JFXTreeTableColumn<>("Nom");
        nomProd.setPrefWidth(150);
        nomProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return param.getValue().getValue().getNom();
            }
        });

        JFXTreeTableColumn<Produits, String> prixProd = new JFXTreeTableColumn<>("Prix");
        prixProd.setPrefWidth(250);
        prixProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return new SimpleStringProperty(Float.toString(param.getValue().getValue().getPrix()));
            }
        });

        JFXTreeTableColumn<Produits, String> CatgProd = new JFXTreeTableColumn<>("Categorie");
        CatgProd.setPrefWidth(250);
        CatgProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return param.getValue().getValue().getCategorie();
            }
        });

        JFXTreeTableColumn<Produits, String> QteProd = new JFXTreeTableColumn<>("Quantite");
        QteProd.setPrefWidth(250);
        QteProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getQuantite()));
            }
        });

        JFXTreeTableColumn<Produits, String> MarqueProd = new JFXTreeTableColumn<>("Marque");
        MarqueProd.setPrefWidth(250);
        MarqueProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return param.getValue().getValue().getMarque();
            }
        });

        nomProd.setCellFactory((TreeTableColumn<Produits, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        nomProd.setOnEditCommit((CellEditEvent<Produits, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getIdProduit();

            String newValue = t.getNewValue();
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().getNom().set(t.getNewValue());
            ps.modifierProduit(id, "nom", newValue);
        });

        prixProd.setCellFactory((TreeTableColumn<Produits, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        prixProd.setOnEditCommit((CellEditEvent<Produits, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setPrix(Float.parseFloat(t.getNewValue()));
        });

        QteProd.setCellFactory((TreeTableColumn<Produits, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        QteProd.setOnEditCommit((CellEditEvent<Produits, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setQuantite(Integer.parseInt(t.getNewValue()));
        });

        MarqueProd.setCellFactory((TreeTableColumn<Produits, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        MarqueProd.setOnEditCommit((CellEditEvent<Produits, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().getMarque().set(t.getNewValue());
        });

        CatgProd.setCellFactory((TreeTableColumn<Produits, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        CatgProd.setOnEditCommit((CellEditEvent<Produits, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().getCategorie().set(t.getNewValue());
        });

        List<Produits> myLst;
        myLst = ps.consulterProduits();
        ObservableList<Produits> produits = FXCollections.observableArrayList();

        myLst.forEach(p -> produits.add(p));

        JFXTreeTableView<Produits> treeview = new JFXTreeTableView<>();
        final TreeItem<Produits> root = new RecursiveTreeItem<Produits>(produits, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(nomProd, prixProd, CatgProd, QteProd, MarqueProd);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);
//        treeview.textProperty()
//                                  .bind(Bindings.createStringBinding(() -> PREFIX + treeview.getCurrentItemsCount() + POSTFIX,
//                                                                     treeview.currentItemsCountProperty()));
        treeview.getStylesheets().add(getClass().getResource("crudproduits.css").toExternalForm());
        Dialog diag = new Dialog();
        GridPane grid = new GridPane();
        grid.add(treeview, 0, 0);
        TextField input = new TextField();
        input.setPromptText("Rechercher ..");
        grid.add(input, 0, 1);
        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<Produits>>() {
                    @Override
                    public boolean test(TreeItem<Produits> t) {

                        boolean flag = t.getValue().getCategorie().getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
        diag.getDialogPane().setContent(grid);
        ButtonType Ajouter = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        diag.getDialogPane().getButtonTypes().add(Ajouter);
        diag.setResultConverter(new Callback() {
            @Override
            public Object call(Object param) {
                if (param == ButtonType.OK) {

                }
                return null;
            }
        });
        diag.showAndWait();
        if (!diag.isShowing()) {
            this.Ajouter.getScene().getRoot().setEffect(null);
        }

    }

    @FXML
    private void supprimerProduits(ActionEvent event) {

        GaussianBlur effect = new GaussianBlur();
        Ajouter.getScene().getRoot().setEffect(effect);

        JFXTreeTableColumn<Produits, String> nomProd = new JFXTreeTableColumn<>("Nom");
        nomProd.setPrefWidth(150);
        nomProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return param.getValue().getValue().getNom();
            }
        });

        JFXTreeTableColumn<Produits, String> prixProd = new JFXTreeTableColumn<>("Prix");
        prixProd.setPrefWidth(250);
        prixProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return new SimpleStringProperty(Float.toString(param.getValue().getValue().getPrix()));
            }
        });

        JFXTreeTableColumn<Produits, String> CatgProd = new JFXTreeTableColumn<>("Categorie");
        CatgProd.setPrefWidth(250);
        CatgProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return param.getValue().getValue().getCategorie();
            }
        });

        JFXTreeTableColumn<Produits, String> QteProd = new JFXTreeTableColumn<>("Quantite");
        QteProd.setPrefWidth(250);
        QteProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getQuantite()));
            }
        });

        JFXTreeTableColumn<Produits, String> MarqueProd = new JFXTreeTableColumn<>("Marque");
        MarqueProd.setPrefWidth(250);
        MarqueProd.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Produits, String> param) {
                return param.getValue().getValue().getMarque();
            }
        });

        ProduitsService ps = new ProduitsService();
        List<Produits> myLst;
        myLst = ps.consulterProduits();
        ObservableList<Produits> produits = FXCollections.observableArrayList();

        myLst.forEach(p -> produits.add(p));

        JFXTreeTableView<Produits> treeview = new JFXTreeTableView<>();
        final TreeItem<Produits> root = new RecursiveTreeItem<Produits>(produits, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(nomProd, prixProd, CatgProd, QteProd, MarqueProd);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.getStylesheets().add(getClass().getResource("crudproduits.css").toExternalForm());
        Dialog diag = new Dialog();
        GridPane grid = new GridPane();
        grid.add(treeview, 0, 0);
        TextField input = new TextField();
        input.setPromptText("Rechercher ..");
        grid.add(input, 0, 1);
        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<Produits>>() {
                    @Override
                    public boolean test(TreeItem<Produits> t) {

                        boolean flag = t.getValue().getCategorie().getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });

        ButtonType Ajouter = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        JFXButton Supprimer = new JFXButton("Supprimer");
        Supprimer.setOnAction(e -> {
            Dialog confirmation = new Dialog();
            GridPane grid2 = new GridPane();
            Label l1 = new Label("Voulez-vous vraiment supprimer ce produit?");
            grid2.add(l1, 2, 2);
            confirmation.setTitle("Confirmation de suppression!");
            confirmation.getDialogPane().setContent(grid2);
            ButtonType Confi = new ButtonType("Supprimer", ButtonBar.ButtonData.OK_DONE);
            ButtonType Ann = new ButtonType("Annuler", ButtonBar.ButtonData.OK_DONE);
            confirmation.getDialogPane().getButtonTypes().add(Confi);
            confirmation.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

            confirmation.setResultConverter(new Callback<ButtonType, Produits>() {
                @Override
                public Produits call(ButtonType param) {
                    if (param == Confi) {
                        Produits p = treeview.getSelectionModel().getSelectedItem().getValue();
                        ps.supprimerProduit(p);
                        diag.close();
                         Button cancelButton = (Button)confirmation.getDialogPane().lookupButton(ButtonType.CLOSE);
                        cancelButton.fire();
                        supprimerProduits(event);
                    }

                    return null;
                }
            });
            
            
            
            confirmation.showAndWait();
        });

        grid.add(Supprimer, 3, 3);
        diag.getDialogPane().setContent(grid);

        diag.getDialogPane().getButtonTypes().add(Ajouter);

        diag.showAndWait();
        if (!diag.isShowing()) {
            this.Ajouter.getScene().getRoot().setEffect(null);
        }
    }

}
