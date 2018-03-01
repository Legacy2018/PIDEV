/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.Login_viewController.u;
import Entities.Annonce_collocation;
import Entities.Annonce_covoiturage;
import Entities.UploadImage;
import Services.CollocationService;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.lynden.gmapsfx.GoogleMapView;
import com.sun.javaws.ui.UpdateDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medma
 */
public class GestionCollocationController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    @FXML
    private Button btn_retour;
    @FXML
    private JFXTextField IdAnn;

    @FXML
    private JFXTextField titre;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXListView<String> ListeImages;

    @FXML
    private DatePicker fin;

    @FXML
    private ImageView Img1;

    @FXML
    private ImageView Img2;

    @FXML
    private ImageView Img3;

    @FXML
    private ImageView Img4;

    @FXML
    private Button btn_supprimer_photo;

    @FXML
    private TableColumn<?, ?> TabDescription;

    @FXML
    private TableColumn<?, ?> TabAnnonce;

    @FXML
    private TableColumn<?, ?> Tabfin;
    @FXML
    private TableColumn<?, ?> TabDebut;

    @FXML
    private TableColumn<Annonce_collocation, Number> TabAnnonceur;
    @FXML
    private Button Btn_SupprimerAnnonce;

    @FXML
    private Button btn_modifierAnnonce;

    @FXML
    private Button btn_ajout;

    @FXML
    private DatePicker debut;

    @FXML
    private JFXTextField tarif;

    @FXML
    private TableColumn<?, ?> TabTitre;

    @FXML
    private TableColumn<?, ?> TabTarif;

    @FXML
    private JFXTextField adresse;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private TableView<Annonce_collocation> Tab;
    @FXML
    private Button btn_Vder;
 private void initDrawer() {
        try {
            AnchorPane SP = FXMLLoader.load(getClass().getResource("/GUI/SidePannel.fxml"));

            
            
            SP.getStylesheets().add(getClass().getResource("/Asset/Style.css").toExternalForm());
            
            SidePannel.setSidePane(SP);

        } catch (IOException ex) {
           
            System.out.println(ex.getMessage());
        }
        
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(Sp);
        task.setRate(-1);
        Sp.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            if (SidePannel.isHidden()) {
                SidePannel.open();
            } else {
                SidePannel.close();
            }
        });
    }

    @FXML
    private Button btn_ajouter_photo;
    public List<String> UpdateImageList= new ArrayList();
    public int SelectedImage = -1;
    public int selectedAnn;
    Stage stage1;
    List<Annonce_collocation> LAC;
    Annonce_collocation AC;
    Annonce_collocation AnnCo;
    CollocationService CS = new CollocationService();
    private ObservableList dataa
            = FXCollections.observableArrayList();
    private ObservableList data
            = FXCollections.observableArrayList();

    public Boolean ValidateFloat() {
        try {
            Float.parseFloat(tarif.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @FXML
    void ajouter(ActionEvent event) throws IOException {
//AC = new Annonce_covoiturage(1, 1, java.sql.Date.valueOf(Datedapart_txt1.getValue()), java.sql.Date.valueOf(Datearrivee_txt.getValue()), Adressedepart_txt.getText()
//, Adressearrivee_txt.getText(), Integer.parseInt(Tarif_txt.getText()));   
//getid user from Ali's code

        if ((debut.getValue() != null) && (fin.getValue() != null) && (!adresse.getText().trim().isEmpty()) && (!tarif.getText().trim().isEmpty()) && (!description.getText().trim().isEmpty())) {
            if (ValidateFloat()) {
                Annonce_collocation anc = new Annonce_collocation(u, 1, titre.getText(), description.getText(), Float.parseFloat(tarif.getText()), adresse.getText(), java.sql.Date.valueOf(debut.getValue()), java.sql.Date.valueOf(fin.getValue()));
                System.out.println("anc"+anc);
                List<Annonce_collocation> lsa = CS.findByIdAnnonceur(u);
                if (lsa.contains(anc)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ajouter Annonce");
                    alert.setHeaderText(null);
                    alert.setContentText("Annonce dupliquée");
                    alert.showAndWait();
                } else if ((debut.getValue().isBefore(LocalDate.now())) || (fin.getValue().isBefore(LocalDate.now())) || (fin.getValue().isBefore(LocalDate.now()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Date");
                    alert.setHeaderText(null);
                    alert.setContentText("Date saisite inferieure à la date actuelle \n ou bien date debut est uperieure à la date d'arrivée");
                    alert.showAndWait();
                } else {
                    if (ListeImages.getItems().size() != 0) {
                        for (int i=0;i<UpdateImageList.size();i++)
                           anc.getUi().add(new UploadImage(1, 3, UpdateImageList.get(i)));
                         System.out.println("22222"+anc);
                        CS.addWithMedia(anc);
                    } else {
                        System.out.println("22222"+anc);
                        CS.add(anc);
                    }

                    Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionCollocation.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/ajoutercollocation.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajouter Annonce");
                alert.setHeaderText(null);
                alert.setContentText("L'entrée doit etre un nombre");
                alert.showAndWait();

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez Remplir tout les champs");
            alert.showAndWait();
        }
    }

    @FXML
    void MultipleFileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("fichier media image ", "*.*"));
        File fichier = fc.showOpenDialog(null);
        if(UpdateImageList.size()<5){
        dataa.add(fichier);
        List<UploadImage> lsa = new ArrayList();
        int ImageNumber = dataa.size();
        ListeImages.setItems(dataa);
        int i = 0;
        Image image;
        while ((i < ImageNumber) && (i < 4)) {
            switch (i) {
                case 0: {

                    try {

                        if (isEmpty(Img1)) {
                            System.out.println("aaaa" + i);
                            image = new Image(new FileInputStream(fichier.getAbsolutePath()));
                            Img1.setImage(image);
                            UpdateImageList.add(fichier.getAbsolutePath());
                        }
                    } catch (FileNotFoundException ex) {

                    }

                    break;
                }
                case 1: {

                    try {

                        if (isEmpty(Img2)) {
                            System.out.println("aaaa" + i);
                            image = new Image(new FileInputStream(fichier.getAbsolutePath()));
                            Img2.setImage(image);
                            UpdateImageList.add(fichier.getAbsolutePath());

                        }
                    } catch (FileNotFoundException ex) {

                    }
                    break;
                }
                case 2: {

                    try {

                        if (isEmpty(Img3)) {
                            System.out.println("aaaa" + i);
                            image = new Image(new FileInputStream(fichier.getAbsolutePath()));
                            Img3.setImage(image);
                            UpdateImageList.add(fichier.getAbsolutePath());
                        }
                    } catch (FileNotFoundException ex) {

                    }
                    break;
                }
                case 3: {

                    try {

                        if (isEmpty(Img4)) {
                            System.out.println("aaaa" + i);
                            image = new Image(new FileInputStream(fichier.getAbsolutePath()));
                            Img4.setImage(image);
                            UpdateImageList.add(fichier.getAbsolutePath());

                        }
                    } catch (FileNotFoundException ex) {

                    }
                    break;
                }
                default:
                    break;
            }
            i++;
        }
        }else{ Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Upload Image");
                alert.setHeaderText(null);
                alert.setContentText("Le nombre d'images maximale a été dépassé");
                alert.showAndWait();
}
    }

    @FXML
    void Img1Click(MouseEvent event) {
        SelectedImage = 1;

    }

    @FXML
    void Img2Click(MouseEvent event) {
        SelectedImage = 2;
    }

    @FXML
    void Img3Click(MouseEvent event) {
        SelectedImage = 3;
    }

    @FXML
    void Img4Click(MouseEvent event) {
        SelectedImage = 4;
    }

    @FXML
    void DeleteFile(ActionEvent event) {

        ListeImages.getItems().remove(SelectedImage - 1);
        if (SelectedImage == 1) {
            Img1.setImage(null);
            UpdateImageList.remove(0);
            System.out.println("ti emchi"+UpdateImageList.size());
        }
        if (SelectedImage == 2) {
            Img2.setImage(null);
            UpdateImageList.remove(1);
        }
        if (SelectedImage == 3) {
            Img3.setImage(null);
            UpdateImageList.remove(2);
        }
        if (SelectedImage == 4) {
            Img4.setImage(null);
            UpdateImageList.remove(3);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TRAVEL TODOid_annonceur, int id_annonce, String titre_annonce, String Description, float tarif, String adresse, Date datedebut, Date datefin
        //Caused by: java.lang.ClassCastException: Entities.Annonce_collocation cannot be cast to java.util.List
//	at Controllers.GestionCollocationController.initialize     
        //get id user de ALI
        initDrawer();
        btn_Vder.setVisible(false);
        btn_modifierAnnonce.setVisible(false);
        Btn_SupprimerAnnonce.setVisible(false);
        btn_Vder.setVisible(false);
        dataa.clear();
        List<Annonce_collocation> lsa = CS.findByIdAnnonceur(u);
        data = FXCollections.observableArrayList();
        System.out.println("ggiiiirrrrr");

        lsa.stream().forEach((j) -> {
            data.add(j);
        });
        Tab.setItems(data);
        TabTitre.setCellValueFactory(new PropertyValueFactory<>("titre_annonce"));
        TabDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        TabDebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        Tabfin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        TabTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        TabAnnonce.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
        TabAnnonceur.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getUser().getId_user()));
        setCellValueFromTableToText();

//        
    }

    private void setCellValueFromTableToText() {
        System.out.println("messageee");
        Tab.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_modifierAnnonce.setVisible(true);
                btn_modifierAnnonce.setVisible(true);
                Btn_SupprimerAnnonce.setVisible(true);
                btn_Vder.setVisible(true);
                btn_ajout.setVisible(false);
                Annonce_collocation gr = (Annonce_collocation) Tab.getItems().get(Tab.getSelectionModel().getSelectedIndex());
                System.out.println(gr);
                selectedAnn = Tab.getSelectionModel().getSelectedIndex();
                System.out.println(selectedAnn);
                titre.setText(gr.getTitre_annonce());
                description.setText(gr.getDescription());
                debut.setValue(LocalDate.now());
                fin.setValue(LocalDate.now());
                tarif.setText(Float.toString(gr.getTarif()));
                IdAnn.setText(Integer.toString(gr.getId_annonce()));
                adresse.setText(gr.getAdresse());
                List<UploadImage> lsa = gr.getUi();
                System.out.println(lsa);
                dataa.clear();
                UpdateImageList.clear();
                
                ObservableList<String> images;
                images = FXCollections.observableArrayList();
                lsa.stream().forEach((j) -> {
                    dataa.add(j.getMedia_link());
                });
                 lsa.stream().forEach((j) -> {
                    UpdateImageList.add(j.getMedia_link());
                });
                 System.out.println("updateList"+UpdateImageList);
                ListeImages.setItems(dataa);
                int i = 0;
                Image image;
                while (i < 4) {
                    switch (i) {
                        case 0: {

                            try {

                                if (i >= lsa.size()) {
                                    System.out.println("aaaa");
                                    File file = new File("../Ressource/left.png");
                                    image = new Image(file.toURI().toString());
                                    Img1.setImage(image);
                                    
                                } else {
                                    System.out.println("bbbb");
                                    image = new Image(new FileInputStream(lsa.get(i).getMedia_link()));
                                    Img1.setImage(image);
                                    UpdateImageList.add(lsa.get(i).getMedia_link());
                                }
                            } catch (FileNotFoundException ex) {

                            }

                            break;
                        }
                        case 1: {

                            try {

                                if (i >= lsa.size()) {
                                    System.out.println("cccc");
                                    File file = new File("../Ressource/left.png");
                                    image = new Image(file.toURI().toString());
                                    Img2.setImage(image);
                                } else {
                                    System.out.println("dddd");
                                    image = new Image(lsa.get(i).getMedia_link(),true);
                                    Img2.setImage(image);
                                    UpdateImageList.add(lsa.get(i).getMedia_link());
                                }
                            } catch (Exception ex) {

                            }
                            break;
                        }
                        case 2: {

                            try {

                                if (i >= lsa.size()) {
                                    System.out.println("eeeee");
                                    File file = new File("../Ressource/left.png");
                                    image = new Image(file.toURI().toString());
                                    Img3.setImage(image);
                                } else {
                                    System.out.println("ffff");
                                    image = new Image(lsa.get(i).getMedia_link(),true);
                                    Img3.setImage(image);
                                    UpdateImageList.add(lsa.get(i).getMedia_link());
                                }
                            } catch (Exception ex) {

                            }
                            break;
                        }
                        case 3: {

                            try {

                                if (i >= lsa.size()) {
                                    System.out.println("ggggg");
                                    File file = new File("../Ressource/left.png");
                                    image = new Image(file.toURI().toString());
                                    Img4.setImage(image);
                                } else {
                                    System.out.println("hhhhh");
                                    image = new Image(lsa.get(i).getMedia_link(),true);
                                    Img4.setImage(image);
                                    UpdateImageList.add(lsa.get(i).getMedia_link());
                                }
                            } catch (Exception ex) {

                            }
                            break;
                        }
                        default:
                            break;
                    }
                    i++;
                }

                IdAnn.setText(Integer.toString(gr.getId_annonce()));

            }
        });
    }

    public static boolean isEmpty(ImageView imageView) {
        Image image = imageView.getImage();
        return image == null || image.isError();
    }

    @FXML
    void Vider(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionCollocation.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/ajoutercollocation.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ModifierAnnonce(ActionEvent event) throws IOException {

        if ((debut.getValue() != null) && (fin.getValue() != null) && (!adresse.getText().trim().isEmpty()) && (!tarif.getText().trim().isEmpty()) && (!description.getText().trim().isEmpty())) {
            if (ValidateFloat()) {
                System.out.println("ghjavjbmcknmkncmlkc");System.out.println("dattttteteeeeerererreret"+java.sql.Date.valueOf(debut.getValue()));
                Annonce_collocation anc = new Annonce_collocation(u, Integer.parseInt(IdAnn.getText()), titre.getText(), description.getText(), Float.parseFloat(tarif.getText()), adresse.getText(), java.sql.Date.valueOf(debut.getValue()), java.sql.Date.valueOf(fin.getValue()));
                List<Annonce_collocation> lsa = CS.findByIdAnnonceur(u);
                if (lsa.contains(anc)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ajouter Annonce");
                    alert.setHeaderText(null);
                    alert.setContentText("Annonce dupliquée");
                    alert.showAndWait();
                } else if ((debut.getValue().isBefore(LocalDate.now())) || (fin.getValue().isBefore(LocalDate.now())) || (fin.getValue().isBefore(LocalDate.now()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Date");
                    alert.setHeaderText(null);
                    alert.setContentText("Date saisite inferieure à la date actuelle \n ou bien date debut est uperieure à la date d'arrivée");
                    alert.showAndWait();
                } else {
                    if (ListeImages.getItems().size() != 0) {
                         for (int i=0;i<UpdateImageList.size();i++)
                           anc.getUi().add(new UploadImage(1, 3, UpdateImageList.get(i)));
                        CS.addWithMedia(anc);
                    } else {
                        CS.add(anc);
                    }

                    Parent creerGroupe = FXMLLoader.load(getClass().getResource("../GUI/GestionCovoiturage.fxml"));
                    Scene sceneAffichage = new Scene(creerGroupe);
                    sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(sceneAffichage);
                    stage.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajouter Annonce");
                alert.setHeaderText(null);
                alert.setContentText("L'entrée doit etre un nombre");
                alert.showAndWait();

            }

            List<String> lstring = new ArrayList<>();
            CollocationService CS = new CollocationService();
            Annonce_collocation anc = new Annonce_collocation(u, 1, titre.getText(), description.getText(), Float.parseFloat(tarif.getText()), adresse.getText(), java.sql.Date.valueOf(debut.getValue()), java.sql.Date.valueOf(fin.getValue()));

            if (ListeImages.getItems().size() != 0) {
                dataa.stream().forEach(j -> anc.getUi().add(new UploadImage(1, 3, j.toString())));

                CS.edit(anc);
            } else {
                CS.edit(anc);
            }

            Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionCollocation.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../Asset/ajoutercollocation.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez Remplir tout les champs");
            alert.showAndWait();
        }
    }

    @FXML
    void SupprimerAnnonce(ActionEvent event) throws IOException {
        List<String> lstring = new ArrayList<>();
        CollocationService CS = new CollocationService();
        CS.delete(Integer.parseInt(IdAnn.getText()));

        Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionCollocation.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/ajoutercollocation.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
          Parent afficher;
        Scene sceneAffichage;
        Stage stage = new Stage();

        afficher = FXMLLoader.load(getClass().getResource("../GUI/AccueilAnnonces.fxml"));
        sceneAffichage = new Scene(afficher);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/accueilannonces.css").toExternalForm());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionCollocation.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/ajoutercollocation.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
