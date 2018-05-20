/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ConsulterAnnoncesCollocationsController.isConsulterAnnoncesCollocaionsController;
import Entities.Annonce_covoiturage;
import Entities.Utilisateur;
import Services.CovoiturageService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Services.ServiceUtilisateur;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
 public int signalee=-1;
 @FXML
    private TableColumn<?, ?> Datedepart;
 @FXML
    private TableColumn<?, ?> Tarrifs;
 @FXML
    private TableColumn<Annonce_covoiturage, String> idannonceur;
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
   @FXML
    private DatePicker Fdate;

    @FXML
    private JFXTextField Fdepart;

    @FXML
    private JFXTextField FArrivee;

    @FXML
    private JFXTextField FTarif;

    @FXML
    private Button btn_ViderFiltres;

    @FXML
    private Button btn_recherche;
        @FXML
    private Button btn_signaler;
         @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;

  public static Utilisateur globalDestMail=null;
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
    void SignalerAnnonce(ActionEvent event) {
         btn_signaler.setVisible(false);
        CS.Signaler(Annonce.getId_annonce());
        CS.Trigger();
         TrayNotification tray = new TrayNotification();
                            NotificationType type = NotificationType.INFORMATION;
                            tray.showAndDismiss(Duration.seconds(5));
              tray.setTitle("Annonce signalé"  );
              tray.setMessage("Annonce Signalée avec succés");
           tray.setNotificationType(type.INFORMATION);
    }
    public Boolean ValidateFloat() {
        try {
            Float.parseFloat(FTarif.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    @FXML
    void RechercheAvancée(ActionEvent event) {
       List<Annonce_covoiturage> lsa = CS.getAll();
       List<Annonce_covoiturage> filtredListd =new ArrayList();
       int stateChanged=-1;
       if (!FArrivee.getText().equals(""))
       {System.out.println("aaaaa");
                  filtredListd=lsa.stream().filter(j->j.getAdresse_arrivee().equals(FArrivee.getText())).collect(Collectors.toList()); 
       stateChanged=1;
       }
       else if (!Fdepart.getText().equals(""))
       {System.out.println("bbbbb");
                  filtredListd=lsa.stream().filter(j->j.getAdresse_depart().equals(Fdepart.getText())).collect(Collectors.toList()); 
       stateChanged=1;
       }
       
       else if (Fdate.getValue()!=null)
       {System.out.println("cccc");
       filtredListd=lsa.stream().filter(j->j.getDate_arrivee().before(java.sql.Date.valueOf(Fdate.getValue()))).collect(Collectors.toList());
       stateChanged=1;
       }
       else if ((!FTarif.getText().isEmpty()))
           if((ValidateFloat())){filtredListd=lsa.stream().filter(j->j.getTarif()<=Float.parseFloat(FTarif.getText())).collect(Collectors.toList());
       stateChanged=1;}else {Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajouter Annonce");
                alert.setHeaderText(null);
                alert.setContentText("L'entrée doit etre un nombre");
                alert.showAndWait();}
                
       //System.out.println(filtredListd);
       if (stateChanged==1){
      dataa= FXCollections.observableArrayList(); 
      dataa.removeAll(dataa);
         filtredListd.forEach((e) -> dataa.add(e));
         
         for ( int i = 0; i<tableannonces.getItems().size(); i++) {
    tableannonces.getItems().clear();
}// System.out.println(dataa);
         
        tableannonces.setItems(dataa);
        Datedepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        Datearrivee.setCellValueFactory(new PropertyValueFactory<>("Date_arrivee"));
        Adressedepart.setCellValueFactory(new PropertyValueFactory<>("adresse_depart"));
        Adressearrivee.setCellValueFactory(new PropertyValueFactory<>("adresse_arrivee"));
        Tarrifs.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        idannonce.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
        idannonceur.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getUser().getId_user())));
       }
       else{
             lsa.stream().forEach((j) -> {
            data.add(j);
        });System.out.println(data);
           tableannonces.setItems(data);
        Datedepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        Datearrivee.setCellValueFactory(new PropertyValueFactory<>("Date_arrivee"));
        Adressedepart.setCellValueFactory(new PropertyValueFactory<>("adresse_depart"));
        Adressearrivee.setCellValueFactory(new PropertyValueFactory<>("adresse_arrivee"));
        Tarrifs.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        idannonce.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
                idannonceur.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getUser().getId_user())));
       } 
    }

    int selectedAnnonce;
    public static Annonce_covoiturage Annonce;

     @FXML
    void ConsulterItinerarire(ActionEvent event) {
        System.out.println("Adresse depart ="+Annonce.getAdresse_depart());
        System.out.println("Adresse arrivee ="+Annonce.getAdresse_arrivee());
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/AfficherItineraireCovoiturage.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                        Scene scene = new Scene(root1);
        scene.getStylesheets().add(getClass().getResource("/Asset/afficheritinerairecovoiturage.css").toExternalForm());
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/EnvoyerMail.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                        Scene scene = new Scene(root1);
        scene.getStylesheets().add(getClass().getResource("/Asset/envoyerMail.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(scene);  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }

    }
     @FXML
    void Retour(ActionEvent event) throws IOException {
         Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("/GUI/AccueilAnnonces.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/accueilannonces.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
         
    }
 private static ObservableList<Annonce_covoiturage> data;
  private ObservableList<Annonce_covoiturage> dataa;
 CovoiturageService CS = new CovoiturageService();
ServiceUtilisateur SU=new ServiceUtilisateur();
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        //TRAVEL TODO
        isConsulterAnnoncesCollocaionsController=0;
        List<Annonce_covoiturage> lsa = CS.getAll();
      // initDrawer();
        data = FXCollections.observableArrayList();
        System.out.println("ggiiiirrrr"+lsa);
        btn_signaler.setVisible(false);
        btn_envoyer_mail.setVisible(false);
        btn_conlt_itin.setVisible(false);
        lsa.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println("dataaaa"+data);
        tableannonces.setItems(data);
        Datedepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        Datearrivee.setCellValueFactory(new PropertyValueFactory<>("Date_arrivee"));
        Adressedepart.setCellValueFactory(new PropertyValueFactory<>("adresse_depart"));
        Adressearrivee.setCellValueFactory(new PropertyValueFactory<>("adresse_arrivee"));
        Tarrifs.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        idannonce.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
        idannonceur.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getUser().getId_user())));
        //String e1= t.getSelectionModel().getSelectedItem().getEquipe1().getPays();
        
        //
        
        
      setCellValueFromTableToText();
    }
     @FXML
     private void setCellValueFromTableToText() {
        System.out.println("messageee");
        tableannonces.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                        btn_signaler.setVisible(true);
        btn_envoyer_mail.setVisible(true);
        btn_conlt_itin.setVisible(true);
     
           
                Annonce = (Annonce_covoiturage) tableannonces.getItems().get(tableannonces.getSelectionModel().getSelectedIndex());
               int selectedAnn = tableannonces.getSelectionModel().getSelectedIndex();
                System.out.println("coucou"+idannonceur.getCellData(selectedAnn));
                Utilisateur SelectedUser=new Utilisateur();
                SelectedUser.setId(Integer.parseInt(idannonceur.getCellData(selectedAnn)));
                Annonce.setUser(SelectedUser);
                System.out.println(("faouziiiiiiiii")+Annonce);
                IdAnnonce= Integer.parseInt(idannonceur.getCellData(selectedAnn));
                IdAnnonceur=Annonce.getUser().getId_user();
                globalDestMail=SU.findUtilisateurbyID(IdAnnonceur);
                AdrDepart=Annonce.getAdresse_depart();
                AdrArrivee=Annonce.getAdresse_arrivee();
                            }
             
        });
    }
       @FXML
    void ViderFiltres(ActionEvent event) throws IOException {
        Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("/GUI/ConsulterAnnoncesCovoiturage.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/consultertoutesannonces.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
         
       
    }
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ConsulterAnnoncesCovoiturage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/consultertoutesannonces.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    }    
    

