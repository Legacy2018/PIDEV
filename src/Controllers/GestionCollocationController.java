/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Annonce_collocation;
import Entities.UploadImage;
import Services.CollocationService;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.lynden.gmapsfx.GoogleMapView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private JFXTextField IdAnn;
    
     @FXML
    private JFXTextField titre;

    @FXML
    private JFXTextArea description;
    
        @FXML
    private JFXListView <String> ListeImages;

    @FXML
    private DatePicker fin;

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
    private TableColumn<?, ?> TabAnnonceur;
        @FXML
    private Button Btn_SupprimerAnnonce;
        
    @FXML
    private Button btn_modifierAnnonce;

    @FXML
    private Button btn_ajout;
    
    @FXML
    private JFXDrawer drawer;

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
    private JFXHamburger hamburger;

    @FXML
    private Button btn_ajouter_photo;
    
    public int selectedAnn;
    Stage stage1;
    List<Annonce_collocation> LAC;
    Annonce_collocation AC;
    Annonce_collocation AnnCo;
    CollocationService CS = new CollocationService();
    private ObservableList dataa = 
        FXCollections.observableArrayList();
     private ObservableList data = 
        FXCollections.observableArrayList();
    @FXML
    void ajouter(ActionEvent event) throws IOException {
//AC = new Annonce_covoiturage(1, 1, java.sql.Date.valueOf(Datedapart_txt1.getValue()), java.sql.Date.valueOf(Datearrivee_txt.getValue()), Adressedepart_txt.getText()
//, Adressearrivee_txt.getText(), Integer.parseInt(Tarif_txt.getText()));   
//getid user from Ali's code
   List<String> lstring=new ArrayList<>();
   CollocationService CS=new CollocationService();
        Annonce_collocation anc=new Annonce_collocation(1, 1, titre.getText(), description.getText(), Float.parseFloat(tarif.getText()) , adresse.getText(), java.sql.Date.valueOf(debut.getValue()), java.sql.Date.valueOf(fin.getValue()));
        if (ListeImages.getItems().size()!=0)
            
        { dataa.stream().forEach(j->anc.getUi().add(new UploadImage(1, 3, j.toString())));
            
            CS.addWithMedia(anc);
        }
        else 
           CS.add(anc);
        
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionCollocation.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/ajoutercollocation.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void MultipleFileChooser(ActionEvent event) {
        FileChooser fc=new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("fichier media image ", "*.*"));
        List <File> fichier = fc.showOpenMultipleDialog(null);
        for (File f : fichier)
        { 
            
            dataa.add(f);
           
        }
       List<UploadImage>lsa=new ArrayList();
               
             
               ListeImages.setItems(dataa);
               
    }

    @FXML
    void DeleteFile(ActionEvent event) {
          ListeImages.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
  int index=ListeImages.getSelectionModel().getSelectedIndex();
    ListeImages.getItems().remove(index);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TRAVEL TODOid_annonceur, int id_annonce, String titre_annonce, String Description, float tarif, String adresse, Date datedebut, Date datefin
  //Caused by: java.lang.ClassCastException: Entities.Annonce_collocation cannot be cast to java.util.List
//	at Controllers.GestionCollocationController.initialize     
        //get id user de ALI
        dataa.clear();
        List<Annonce_collocation> lsa =  CS.findByIdAnnonceur(1);
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
        TabAnnonceur.setCellValueFactory(new PropertyValueFactory<>("id_annonceur"));
       setCellValueFromTableToText();
        
       
       
       try {
            VBox box = FXMLLoader.load(getClass().getResource("../GUI/SidePanelContent.fxml"));
            //scene.getStylesheets().add(getClass().getResource("../Asset/envoyerMail.css").toExternalForm());
          drawer.getStylesheets().add(getClass().getResource("../Asset/sidepanelcontent.css").toExternalForm());
          drawer.setDefaultDrawerSize(240);
            drawer.setSidePane(box);
        } catch (IOException ex) {
           ex.getMessage();        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
       
       
    }    

     private void setCellValueFromTableToText() {
        System.out.println("messageee");
        Tab.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

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
              List<UploadImage>lsa=gr.getUi();
                System.out.println(lsa);
              dataa.clear();
              ObservableList<String> images;
              images = FXCollections.observableArrayList();
              lsa.stream().forEach((j) -> { dataa.add(j.getMedia_link());});
               ListeImages.setItems(dataa);
               
               IdAnn.setText(Integer.toString(gr.getId_annonce()));
             
            }
        });
    }
     
      @FXML
    void ModifierAnnonce(ActionEvent event) throws IOException {
         List<String> lstring=new ArrayList<>();
   CollocationService CS=new CollocationService();
        Annonce_collocation anc=new Annonce_collocation(1, Integer.parseInt(IdAnn.getText()), titre.getText(), description.getText(), Float.parseFloat(tarif.getText()) , adresse.getText(), java.sql.Date.valueOf(debut.getValue()), java.sql.Date.valueOf(fin.getValue()));
        if (ListeImages.getItems().size()!=0)
            
        { dataa.stream().forEach(j->anc.getUi().add(new UploadImage(1, 3, j.toString())));
            
            CS.edit(anc);
        }
        else 
           CS.edit(anc);
        
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionCollocation.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/ajoutercollocation.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    void SupprimerAnnonce(ActionEvent event) throws IOException {
 List<String> lstring=new ArrayList<>();
   CollocationService CS=new CollocationService();
        CS.delete(Integer.parseInt(IdAnn.getText()));
        
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionCollocation.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/ajoutercollocation.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
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
