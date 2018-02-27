/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Services.DAOStade;
import Services.MatchDAO;
import Services.TicketDAO;

import IServices.IMatchDAO;
import IServices.IStadeDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import Entities.Fos_User;
import Entities.Ticket;
import Entities.Utilisateur;
import Entities.match;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author khmai
 */
public class AjouterTicketController extends Application implements Initializable {

    public Utilisateur user ;
    public match mat ;
    ObservableList<String> lstcat = FXCollections.observableArrayList("pelouse", "virage");
    ObservableList<Integer> lstnb = FXCollections.observableArrayList(1, 2, 3, 4);
    @FXML

    private JFXTextField id_prix;

    @FXML
    private JFXComboBox<String> id_categorie;

    @FXML
    private JFXButton AjouterAchatTicket;

     @FXML
    private JFXTextField id_equipe1;

    @FXML
    private JFXTextField id_equipe2;

    @FXML
    private JFXTextField id_stade;

    @FXML
    private JFXComboBox<Integer> id_nbticket;

    @FXML
    private JFXTextField id_heur;

    @FXML
    private JFXTextField id_date;

    @FXML
    private TableView<match> tableViws;

    @FXML
    private TableColumn<?, ?> id_matchht;

    @FXML
    private TableColumn<match,String> Equipe1;

    @FXML
    private TableColumn<match, String> Equipee2;

    @FXML
    private TableColumn<match, String> satades;

    @FXML
    private TableColumn<?, ?> datematch;

    @FXML
    private TableColumn<?, ?> heur;
    @FXML
    public JFXTextField txdann;
       @FXML
    private ImageView image1;

     private ObservableList<match> data;
 ListTicketsController listTichetController = new ListTicketsController();
    Login_viewController loginCQONTROLLER = new Login_viewController();
    TicketDAO tdao = new TicketDAO();
    MatchDAO m = new MatchDAO();

   // IEquipeDAO e = ServiceEquipe.;
    IStadeDAO s = DAOStade.getInstance();
    IMatchDAO m1 = MatchDAO.getInstance();
    List<match> eq = new ArrayList<>();
    List eq1;
    List eq2;
    List s2;
    Fos_User x;
    
 static public int test;
 @Override
    public void start(Stage stage) throws Exception {
      /*  Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjouterTicket.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(new Image("/Ressource/fa.png"));*/
    }

    public static void main(String[] args) {
        launch(args);
    }
   /* @FXML
    void verifnum(KeyEvent event) {
        id_prix.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    id_prix.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }*/

    @FXML
    void AjouterAchatTicket(ActionEvent event) throws IOException {
        try{
            Float.parseFloat(id_prix.getText());
               x = loginCQONTROLLER.u;
           test=Integer.parseInt(txdann.getText());
            int s=x.getId();
           
                          
   Ticket ticket = new Ticket(id_nbticket.getValue(), id_categorie.getValue(), Float.parseFloat(id_prix.getText()) ,new Fos_User(s),new match (test));
        TicketDAO tda = new TicketDAO();
        tda.ajouter_Ticket(ticket);
        System.out.println("to text field action " + event.toString());
           Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("Succés!");
        fail.setContentText("Votre Ticket est ajouté  ");
        fail.showAndWait();
          
      
        Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("../GUI/ListTickets.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/MainFram.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     stage.setScene(sceneAffichage);
        stage.show();
         Notifications.create().title("Succes").text("Ticket ajouté ").showConfirm();
        }catch(NumberFormatException ex){
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("error");
            a.setHeaderText("prix invalid !");
            a.setContentText("");
            a.showAndWait();
        }
          
          
               
        
     
         /*  Alert fail= new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("Attention!");
        fail.setContentText("vérifiez vos champs s'il vous plait ");
        fail.showAndWait();}
       
       */
            
          
    }
          
       
          

    public AjouterTicketController() {


    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        id_categorie.setValue(lstcat.get(0));
        id_categorie.setItems(lstcat);
        id_nbticket.setValue(lstnb.get(0));
        id_nbticket.setItems(lstnb);
        AjouterAchatTicket.setDisable(true);
       image1.setImage(new Image("Ressource/2018_FIFA_World_Cup.png"));
 // image1=new ImageView("Ressource/Dmd.jpg");
  image1.setFitHeight(100);
  image1.setFitWidth(900);
    
  List<match> lsa=m.afficherMatch();
        data = FXCollections.observableArrayList();
       lsa.stream().forEach((e) -> {
            data.add(e);
            System.out.println(e);
        });
       
       tableViws.setItems(data);
      //  txdann.setCellValueFactory(new PropertyValueFactory<>("id_ticket"));
     //  Equipe1.setCellValueFactory(new PropertyValueFactory<>("equipe1"));
     //  Equipee2.setCellValueFactory(new PropertyValueFactory<>("");
    //categories.setCellValueFactory(new PropertyValueFactory<>("categories"));
       Equipe1.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEquipe1().getPays()));
       Equipee2.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEquipe2().getPays()));
       satades.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getStade().getNom_Stade()));
id_matchht.setCellValueFactory(new PropertyValueFactory<>("idMatch"));
       datematch.setCellValueFactory(new PropertyValueFactory<>("dateMatch"));
              heur.setCellValueFactory(new PropertyValueFactory<>("heureMatch"));
       
        
       setCellValueFromTableToText();
        
        
        
    }
      private void setCellValueFromTableToText() {
         
        System.out.println("messageee");
        tableViws.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

              match   gr = (match) tableViws.getItems().get(tableViws.getSelectionModel().getSelectedIndex());
                System.out.println(gr);
                int s = tableViws.getSelectionModel().getSelectedIndex();
                System.out.println(s);
             id_matchht.setText(Integer.toString(gr.getIdMatch()));
               id_equipe1.setText(gr.getEquipe1().getPays());
               id_equipe2.setText(gr.getEquipe2().getPays());
                id_stade.setText(gr.getStade().getNom_Stade());
               // id_nbticket.setText(Integer.toString(gr.getNbTicket()));
               // id_prix.setText(Float.toString(gr.getPrix()));
                id_date.setText(gr.getDateMatch());
                id_heur.setText(gr.getHeureMatch());
                  txdann.setText(Integer.toString(gr.getIdMatch()));
                AjouterAchatTicket.setDisable(false);
                              }
        });
    }

    

}
