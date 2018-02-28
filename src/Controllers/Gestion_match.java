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
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    private TableColumn<match, ?> sc;
    @FXML
    private TableColumn<match, ?> sc1;
    
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
     @FXML
    private Label ha;
    @FXML
    private BorderPane border;
    @FXML
    private TableColumn<?, ?> vs;
    @FXML
    private JFXButton mo1;
    @FXML
    private JFXButton mo11;
    @FXML
    private JFXButton mo2;
    @FXML
    private JFXButton mo3;
     @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    
    
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
    void Gere_Joueur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestionJoueur.fxml"));
        Scene s = new Scene(root); 
        s.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage s2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
        s2.setScene(s);
        s2.show();

    }
    @FXML
    void Classement(ActionEvent event) throws IOException {
        
       
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Classement.fxml"));
        Scene s = new Scene(root); 
        s.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
         Stage s2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
       
        s2.setScene(s);
      
       
          s2.show();
    }

    @FXML
    void Gerer_equipe(ActionEvent event) throws IOException {

          Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestionEquipe.fxml"));
        Scene s = new Scene(root); 
        s.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage s2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
        s2.setScene(s);
        s2.show();
    }

     private void initDrawer() {
        try {
            AnchorPane SP = FXMLLoader.load(getClass().getResource("/GUI/SidePannel.fxml"));

            
            
            SP.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
            
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
    void ajouter_m(MouseEvent event) throws IOException {
        
        Equipe eqa= new Equipe(); 
        Equipe eqb = new Equipe();
        eqa=e.AfficherEquipe(id_equipe.getValue());
        eqb=e.AfficherEquipe(id_equipe2.getValue());
        stade saa = new stade();
        saa =s.Consulterstade(Stades.getValue());
         
   
     
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
        alert.setContentText("l'equipe "+id_equipe.getValue()+" dèja a un match");
        alert.showAndWait();
   }
   else if ((sda.stream().anyMatch(e->e.getEquipe1().getPays().contains(id_equipe2.getValue())))||
           (sda.stream().anyMatch(e->e.getEquipe2().getPays().contains(id_equipe2.getValue()))))
   {
        Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Equipe !!!");
         alert.setHeaderText(null);
        alert.setContentText("l'equipe "+id_equipe2.getValue()+" dèja a un match");
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
     else if (id_equipe.getSelectionModel().isEmpty() || id_equipe2.getValue().isEmpty()){
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("remplir info");
         alert.setHeaderText(null);
        alert.setContentText("remplir tous les informations");
        alert.showAndWait();
     }
             
             
            else {
          m = new match(date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),ta.getValue().format(DateTimeFormatter.ofPattern("hh:mm")),eqa,saa,eqb,phase.getValue());
         M.ajouterMatch(m);
          Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Match ajouté");
         alert.setHeaderText(null);
        alert.setContentText("vous avez ajouté le match !");
        alert.showAndWait();}
   
       Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();

  
    }
     @FXML
    void Supprimer(ActionEvent event) throws IOException {
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
    
    
    @FXML
    void c_phase(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLConsulter_Match.fxml"));
        Scene s = new Scene(root); 
        s.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage s2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
        s2.setScene(s);
        s2.show();
    }

    
    
 
     @FXML
    void modifier(ActionEvent event) throws IOException {
        
            match  m6 =  t.getSelectionModel().getSelectedItem();
            LocalDate d = date1.getValue();
            
        if(date1.getValue().isAfter(LocalDate.now())){
        String s = date1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
       M.modifierDateMatch(s, m6);
       Notifications.create()
           .title("Changement du date")
              .text("le match est reporté pour "+s)
             .showWarning();}
        else {
            Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Date!!");
         alert.setHeaderText(null);
        alert.setContentText("Date non acceptable !");
        alert.showAndWait();
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
    void modifier_C(ActionEvent event) throws IOException {
        
        

        match  m6 =  t.getSelectionModel().getSelectedItem();
        String e1= t.getSelectionModel().getSelectedItem().getEquipe1().getPays();
        String e2= t.getSelectionModel().getSelectedItem().getEquipe2().getPays();
        int c =Integer.parseInt(score1.getValue());
        int c2=Integer.parseInt(score2.getValue());
        M.modifierMatchScore(c,c2, m6);
        if (t.getSelectionModel().getSelectedItem().getPhase().equals("Premier Tour"))
        {if(c>c2){
           int z1= e.AfficherEquipe(e1).getIdEquipe();
           int score=e.Consulterpoint(e1);
          Equipe ea=new Equipe(z1,3+score);
         e.modifierEquipescore(ea, ea.idEquipe);
         Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e1+" a gagné")
             .showInformation();}
        else if (c<c2){
             int z1= e.AfficherEquipe(e2).getIdEquipe();
           int score=e.Consulterpoint(e2);
          Equipe ea=new Equipe(z1,3+score);
         e.modifierEquipescore(ea, ea.idEquipe);
             Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e2+" a gagné")
             .showInformation();
        }
        else {
             int z1= e.AfficherEquipe(e1).getIdEquipe();
           int score=e.Consulterpoint(e1);
          Equipe ea=new Equipe(z1,1+score);
         e.modifierEquipescore(ea, ea.idEquipe);
          int z2= e.AfficherEquipe(e2).getIdEquipe();
           int score2=e.Consulterpoint(e2);
          Equipe ea2=new Equipe(z2,1+score2);
         e.modifierEquipescore(ea2, ea2.idEquipe);
             Notifications.create()
           .title("Changement du score")
              .text("Null")
             .showInformation();
        }}
        if(t.getSelectionModel().getSelectedItem().getPhase().equals("1/8")){
            {
                if(c>c2){
           int z1= e.AfficherEquipe(e1).getIdEquipe();
        Equipe eqa = e.AfficherEquipe(z1);
        eqa.setPhase("1/4");
        e.modifierEquipe(eqa, z1);
        
         Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e1+" a gagné")
             .showInformation();}
        else if (c<c2){
             int z1= e.AfficherEquipe(e2).getIdEquipe();
          
          Equipe ea=e.AfficherEquipe(z1);
          ea.setPhase("1/4");
         e.modifierEquipescore(ea, z1);
             Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e2+" a gagné")
             .showInformation();
        }}
        }
        if(t.getSelectionModel().getSelectedItem().getPhase().equals("1/4")){
           {
                if(c>c2){
           int z1= e.AfficherEquipe(e1).getIdEquipe();
        Equipe eqa = e.AfficherEquipe(z1);
        eqa.setPhase("1/2");
        e.modifierEquipe(eqa, z1);
        
         Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e1+" a gagné")
             .showInformation();}
        else if (c<c2){
             int z1= e.AfficherEquipe(e2).getIdEquipe();
          
          Equipe ea=e.AfficherEquipe(z1);
          ea.setPhase("1/2");
         e.modifierEquipescore(ea, z1);
             Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e2+" a gagné")
             .showInformation();
        }} 
        }
         if(t.getSelectionModel().getSelectedItem().getPhase().equals("1/2")){
           {
                if(c>c2){
           int z1= e.AfficherEquipe(e1).getIdEquipe();
           int z2=e.AfficherEquipe(e2).getIdEquipe();
           Equipe eq2 = e.AfficherEquipe(z2);
        Equipe eqa = e.AfficherEquipe(z1);
        eqa.setPhase("final");
        eq2.setPhase("3éme place");
        e.modifierEquipe(eqa, z2);
        e.modifierEquipe(eqa, z1);
        
         Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e1+" a gagné")
             .showInformation();}
        else if (c<c2){
             int z1= e.AfficherEquipe(e2).getIdEquipe();
          
          Equipe ea=e.AfficherEquipe(z1);
          ea.setPhase("final");
         e.modifierEquipescore(ea, z1);
             Notifications.create()
           .title("Changement du score")
              .text("l'equipe "+e2+" a gagné")
             .showInformation();
        }} 
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
    void p(ActionEvent event) {

         
    if(this.phase.getValue().equals("Premier Tour"))
    {
        this.gp.setDisable(false);
        
    }
    else  if(this.phase.getValue().equals("1/4"))
    {
        this.gp.setDisable(true);
        this.ha.setVisible(false);
        List z = new ArrayList<>();
       z= e.chercherParPhase("1/4").stream().map(e->e.getPays()).collect(Collectors.toList());
        this.id_equipe.getItems().clear();
         this.id_equipe.getItems().addAll(z);
         this.id_equipe2.getItems().clear();
         this.id_equipe2.getItems().addAll(z);
    }
   else  if(this.phase.getValue().equals("1/8"))
    {
        this.gp.setDisable(true);
        this.ha.setVisible(false);
        List z = new ArrayList<>();
       z= e.chercherParPhase("1/8").stream().map(e->e.getPays()).collect(Collectors.toList());
        this.id_equipe.getItems().clear();
         this.id_equipe.getItems().addAll(z);
          this.id_equipe2.getItems().clear();
         this.id_equipe2.getItems().addAll(z);
    }
   else  if(this.phase.getValue().equals("1/2"))
    {
        this.gp.setDisable(true);
        this.ha.setVisible(false);
        List z = new ArrayList<>();
       z= e.chercherParPhase("1/2").stream().map(e->e.getPays()).collect(Collectors.toList());
        this.id_equipe.getItems().clear();
         this.id_equipe.getItems().addAll(z);
          this.id_equipe2.getItems().clear();
         this.id_equipe2.getItems().addAll(z);
    }
    
    else  if(this.phase.getValue().equals("final"))
    {
        this.gp.setDisable(true);
        this.ha.setVisible(false);
        List z = new ArrayList<>();
       z= e.chercherParPhase("final").stream().map(e->e.getPays()).collect(Collectors.toList());
        this.id_equipe.getItems().clear();
         this.id_equipe.getItems().addAll(z);
          this.id_equipe2.getItems().clear();
         this.id_equipe2.getItems().addAll(z);
    }
    
    }
     @FXML
    void modifier_Ca(ActionEvent event) throws IOException {
       
       Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/FXMLC_Stade.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
       sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();

        
        
    }
    @FXML
    void gp(ActionEvent event) {
        
        
        e.AfficherEquipepargp(this.gp.getValue());
        List z = new ArrayList<>();
        z=e.AfficherEquipepargp(this.gp.getValue()).stream().map(e->e.getPays()).collect(Collectors.toList());
        this.id_equipe.getItems().clear();
        this.id_equipe.getItems().addAll(z);
         this.id_equipe2.getItems().clear();
        this.id_equipe2.getItems().addAll(z);
        
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
      
        this.gp.setDisable(true);
        
        this.Stades.getItems().addAll(s2);
        this.phase.getItems().addAll("Premier Tour","1/8","1/4","1/2","Final","3éme place");
      this.score1.getItems().addAll("0","1","2","3","4","5","6","7","8","9","10");
      this.score2.getItems().addAll("0","1","2","3","4","5","6","7","8","9","10");
      this.gp.getItems().addAll("A","B","C","D","E","F","G","H");
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
       
         initDrawer();
       
     
        //TRAVEL TODO
    }    
     
   
   

   

    /**
     * @param args the command line arguments
     */
   
    
    
}
