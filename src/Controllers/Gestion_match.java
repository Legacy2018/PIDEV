/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.serviceEquipe;
import Services.DAOStade;
import Services.MatchDAO;

import IServices.IMatchDAO;
import IServices.IStadeDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import Entities.Equipe;
import Entities.match;
import Entities.stade;


import java.io.IOException;
import javafx.scene.control.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author admin
 */
public class Gestion_match extends Application implements Initializable {

    @FXML
    private JFXComboBox<String> id_equipe;
    @FXML
    private JFXTextField heure;
    @FXML
    private JFXTextField phi;
   

   @FXML
    private JFXComboBox<String> phase;

    @FXML
    private TableView<match> t;
     @FXML
    private JFXButton mo;

    @FXML
    private TableColumn<match, String> Equipe1;

    @FXML
    private TableColumn<match, String> Equipe2;

    @FXML
    private TableColumn<?, ?> ph;

    @FXML
    private TableColumn<?, ?> dt;
    
    @FXML
    private TableColumn<?, ?> sc;
    @FXML
    private TableColumn<?, ?> sc1;
    
    @FXML
    private TableColumn<match, String> Stade;
    @FXML
    private TableColumn<?, ?> h;
    @FXML
    private JFXComboBox<String> id_equipe2;
    @FXML
    private JFXComboBox<String> Stades;
      @FXML
    private JFXTimePicker ta;
   @FXML
    private JFXButton c_phase1;

    @FXML
    private JFXButton c_date;
    
    private ObservableList<match> data;
    
    @FXML
    private JFXButton btn_ajouter;
    @FXML
    private JFXButton Supprimer;
@FXML
    private JFXComboBox<String> gp;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXDatePicker date1;
    
    @FXML
    private JFXComboBox<String> score1;
    @FXML
    private JFXComboBox<String> score2;

  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
   private  Date dateUtil;
    serviceEquipe e = new serviceEquipe(); 
    IStadeDAO s= DAOStade.getInstance();
    IMatchDAO M = MatchDAO.getInstance();
    List<Equipe>eq=new ArrayList<>();
    List<stade> s1 = new ArrayList<>();
    List eq1;
    List eq2;
    List s2;
    List s3;
   match m ;
    
    /**
     * Initializes the controller class.
     */
    
    
    public Gestion_match()  {
    
       eq1 = new ArrayList<>();       
       eq2 = new ArrayList<>();
      
       s2 = new ArrayList<>();
       s1=s.ConsulterStadeList();
      eq = e.selectEquipes();
      eq1=eq.stream().map(e->e.getPays()).collect(Collectors.toList());
      eq2=eq.stream().map(e->e.getIdEquipe()).collect(Collectors.toList());
     
      s2=s1.stream().map(e->e.getNom_Stade()).collect(Collectors.toList());
      s3=s1.stream().map(e->e.getId_stade()).collect(Collectors.toList());
    
    }   
     @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/FXMLGestion_Match.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(new Image("/Ressource/fa.png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
      @FXML
    void Gere_Joueur(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestionJoueur.fxml"));
        Scene s = new Scene(root); 
        s.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage s2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
        s2.setScene(s);
        s2.show();

    }

    @FXML
    void Gerer_equipe(MouseEvent event) throws IOException {

          Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestionEquipe.fxml"));
        Scene s = new Scene(root); 
        s.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage s2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
        s2.setScene(s);
        s2.show();
    }

    
    @FXML
    void ajouter_m(MouseEvent event) throws IOException {
        
        Equipe eqa= new Equipe(); 
        Equipe eqb = new Equipe();
        eqa=e.AfficherEquipe(id_equipe.getValue());
        eqb=e.AfficherEquipe(id_equipe2.getValue());
        stade saa = new stade();
        saa =s.Consulterstade(Stades.getValue());
    m = new match(date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),ta.getValue().format(DateTimeFormatter.ofPattern("hh:mm")),eqa,saa,eqb,phase.getValue());
     
    List<match> sda= M.chercherMatchParDate(date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
   List sda1=sda.stream().map(e->e.getStade()).collect(Collectors.toList());
   if(sda.stream().anyMatch(e->e.getStade().getNom_Stade().contains(Stades.getValue()))){
        Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Stade !!!");
         alert.setHeaderText(null);
        alert.setContentText("il y a un match dans ce stade déja !!");
        alert.showAndWait();
   }
   else if ((sda.stream().anyMatch(e->e.getEquipe1().getPays().contains(id_equipe.getValue())))||(sda.stream().anyMatch(e->e.getEquipe2().getPays().contains(id_equipe.getValue()))))
           {
       Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Equipe !!!");
         alert.setHeaderText(null);
        alert.setContentText("l'equipe"+id_equipe.getValue()+"dèja a un match");
        alert.showAndWait();
   }
   else if ((sda.stream().anyMatch(e->e.getEquipe1().getPays().contains(id_equipe2.getValue())))||
           (sda.stream().anyMatch(e->e.getEquipe2().getPays().contains(id_equipe2.getValue()))))
   {
        Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Equipe !!!");
         alert.setHeaderText(null);
        alert.setContentText("l'equipe"+id_equipe2.getValue()+ "dèja a un match");
        alert.showAndWait();
   }
       else
       if(date.getValue().isBefore(LocalDate.now()))
    {
        Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Date !!!");
         alert.setHeaderText(null);
        alert.setContentText("Date Non acceptable");
        alert.showAndWait();
    
      }
     else if ((id_equipe.getValue().equals(id_equipe2.getValue()))){
          Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText(null);
        alert.setContentText("ce n'est pas possible");
        alert.showAndWait();
         
     }
     else if (id_equipe.getValue().isEmpty()||id_equipe2.getValue().isEmpty()||Stades.getValue().isEmpty()||phase.getValue().isEmpty()){
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("remplir info");
         alert.setHeaderText(null);
        alert.setContentText("remplir tous les informations");
        alert.showAndWait();
     }
             
             
            else {  M.ajouterMatch(m);
          Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Match ajouté");
         alert.setHeaderText(null);
        alert.setContentText("vous avez ajouté le match");
        alert.showAndWait();}
   
       Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();

  
    }
     @FXML
    void Supprimer(MouseEvent event) throws IOException {
    int s = t.getSelectionModel().getSelectedItem().getIdMatch();
       
       
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Supprimer match");
         alert.setHeaderText(null);
        alert.setContentText("vous voulez supprimer le match");
         Optional<ButtonType> option = alert.showAndWait();
        
         if (option.get() == ButtonType.OK){
              M.supprimerMatch(s);
         }else if(option.get()==ButtonType.CANCEL){
             t.refresh();
         }
         

        
        t.refresh();
       
       
       Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();

    }
    
    
    
     public void c_phase(MouseEvent event) throws IOException{ 
         
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLConsulter_Match.fxml"));
        Scene s = new Scene(root); 
        s.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage s2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
        s2.setScene(s);
        s2.show();
            
            
               
         }
      
 
     @FXML
    void modifier(MouseEvent event) throws IOException {
        
            match  m6 =  t.getSelectionModel().getSelectedItem();
            LocalDate d = date1.getValue();
            
        
        String s = date1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
       M.modifierDateMatch(s, m6);
       Notifications.create()
           .title("Changement du date")
              .text("le match est reporté pour "+s)
             .showWarning();
      
       t.refresh();
       
       
       Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
       sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();


    }
    
    @FXML
    void modifier_C(MouseEvent event) throws IOException {
        
        

        match  m6 =  t.getSelectionModel().getSelectedItem();
        String e1= t.getSelectionModel().getSelectedItem().getEquipe1().getPays();
        String e2= t.getSelectionModel().getSelectedItem().getEquipe2().getPays();
        String c =score1.getValue();
        String c2=score2.getValue();
        M.modifierMatchScore(c,c2, m6);
        if(Integer.parseInt(c)>Integer.parseInt(c2)){
         Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e1+" a gagné")
             .showInformation();}
        else if (Integer.parseInt(c)<Integer.parseInt(c2)){
             Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e2+" a gagné")
             .showInformation();
        }
        else {
             Notifications.create()
           .title("Changement du score")
              .text("Null")
             .showInformation();
        }
        t.refresh();
       
       
       Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
       sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();

        
    }
     @FXML
    void modifier_Ca(MouseEvent event) throws IOException {
      
       
       
       Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/FXMLC_Stade.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
       sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();

        
        
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       this.id_equipe.getItems().addAll(eq1);
        this.id_equipe2.getItems().addAll(eq1);
        this.Stades.getItems().addAll(s2);
        this.phase.getItems().addAll("Premier Tour","1/8","1/4","1/2","Final","3éme place");
      this.score1.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
      this.score2.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
       date.setValue(LocalDate.now());
     
       List<match> lsa=M.afficherMatch();
        data = FXCollections.observableArrayList();
       lsa.stream().forEach((e) -> {
            data.add(e);
        });
       t.setItems(data);
       Equipe1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEquipe1().getPays()));
       Equipe2.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEquipe2().getPays()));
       
       Stade.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getStade().getNom_Stade()));
       ph.setCellValueFactory(new PropertyValueFactory<>("phase"));
       h.setCellValueFactory(new PropertyValueFactory<>("heureMatch"));
       dt.setCellValueFactory(new PropertyValueFactory<>("dateMatch"));
       sc.setCellValueFactory(new PropertyValueFactory<>("score"));
       sc1.setCellValueFactory(new PropertyValueFactory<>("score2"));
       
       
       
     
        //TRAVEL TODO
    }    
     
   
   

   

    /**
     * @param args the command line arguments
     */
   
    
    
}
