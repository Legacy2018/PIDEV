/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ConsulterAnnoncesCollocationsController.isConsulterAnnoncesCollocaionsController;
import Entities.Annonce_covoiturage;
import Services.CovoiturageService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medma
 */

public class ConsulterAnnoncesCovoiturageController extends Application implements Initializable {
 public static int IdAnnonceur=0;
 public static int IdAnnonce=0;
 public static String AdrDepart="";
 public static String AdrArrivee="";
 @FXML
    private TableColumn<?, ?> Datedepart;
 @FXML
    private TableColumn<?, ?> Tarrifs;
 @FXML
    private TableColumn<?, ?> idannonceur;
 @FXML
    private TableView<Annonce_covoiturage> tableannonces;

 @FXML
    private TableColumn<?, ?> Datearrivee;
 @FXML
    private TableColumn<?, ?> Adressearrivee;
 @FXML
    private TableColumn<?, ?> idannonce;
 @FXML
    private TableColumn<?, ?> Adressedepart;
   @FXML
    private Button btn_retour;
     @FXML
    private Button btn_conlt_itin;
   @FXML
    private Button btn_envoyer_mail;
    int selectedAnnonce;
    public static Annonce_covoiturage Annonce;

     @FXML
    void ConsulterItinerarire(ActionEvent event) {
        System.out.println("Adresse depart ="+Annonce.getAdresse_depart());
        System.out.println("Adresse arrivee ="+Annonce.getAdresse_arrivee());
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/AfficherItineraireCovoiturage.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                        Scene scene = new Scene(root1);
        scene.getStylesheets().add(getClass().getResource("../Asset/afficheritinerairecovoiturage.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(scene);  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }


    }
     @FXML
    void EnvoyerMail(ActionEvent event) {
         
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/EnvoyerMail.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                        Scene scene = new Scene(root1);
        scene.getStylesheets().add(getClass().getResource("../Asset/envoyerMail.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(scene);  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }

    }
     @FXML
    void Retour(ActionEvent event) {

    }
 private ObservableList<Annonce_covoiturage> data;
 CovoiturageService CS = new CovoiturageService();

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TRAVEL TODO
        isConsulterAnnoncesCollocaionsController=0;
        List<Annonce_covoiturage> lsa = CS.getAll();
        data = FXCollections.observableArrayList();
        System.out.println("ggiiiirrrrr");

        lsa.stream().forEach((j) -> {
            data.add(j);
        });
        tableannonces.setItems(data);
        Datedepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        Datearrivee.setCellValueFactory(new PropertyValueFactory<>("Date_arrivee"));
        Adressedepart.setCellValueFactory(new PropertyValueFactory<>("adresse_depart"));
        Adressearrivee.setCellValueFactory(new PropertyValueFactory<>("adresse_arrivee"));
        Tarrifs.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        idannonce.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
        idannonceur.setCellValueFactory(new PropertyValueFactory<>("id_annonceur"));
      setCellValueFromTableToText();
    }
     @FXML
     private void setCellValueFromTableToText() {
        System.out.println("messageee");
        tableannonces.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Annonce = (Annonce_covoiturage) tableannonces.getItems().get(tableannonces.getSelectionModel().getSelectedIndex());
                System.out.println(Annonce);
                IdAnnonce=Annonce.getId_annonce();
                IdAnnonceur=Annonce.getId_annonceur();
                AdrDepart=Annonce.getAdresse_depart();
                AdrArrivee=Annonce.getAdresse_arrivee();
                            }
        });
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ConsulterAnnoncesCovoiturage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/consultertoutesannonces.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    }    
    

