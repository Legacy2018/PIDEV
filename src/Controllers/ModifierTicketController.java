/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import static Controllers.ListTicketsController.ticketSelectionne;
import IServices.IMatchDAO;
import IServices.IStadeDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import Entities.Fos_User;
import Entities.match;
import Entities.Ticket;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Services.DAOStade;
import Services.MatchDAO;
import Services.serviceEquipe;
import Services.TicketDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import org.controlsfx.control.Notifications;

public class ModifierTicketController implements Initializable {

    public Fos_User user;
    public match mat;
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
    private TableColumn<match, String> Equipe1;

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
    private ObservableList<match> data;

    MatchDAO m = new MatchDAO();

    serviceEquipe e = new serviceEquipe();
    IStadeDAO s = DAOStade.getInstance();
    IMatchDAO m1 = MatchDAO.getInstance();
    List<match> eq = new ArrayList<>();
    List eq1;
    List eq2;
    List s2;
    private Ticket ticketSelectionne;
    TicketDAO tda = new TicketDAO();
    ListTicketsController listTichetController = new ListTicketsController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ticketSelectionne = listTichetController.getTicketSelectionne();
        System.out.println(ticketSelectionne);
        id_categorie.setItems(lstcat);
        id_nbticket.setItems(lstnb);
      id_equipe1.setText(ticketSelectionne.getIdMatch().getEquipe1().getPays());
            id_equipe2.setText(ticketSelectionne.getIdMatch().getEquipe2().getPays());
              id_stade.setText(ticketSelectionne.getIdMatch().getStade().getNom_Stade());
        id_heur.setText(ticketSelectionne.getIdMatch().getHeureMatch());
        id_date.setText(ticketSelectionne.getIdMatch().getDateMatch());
        id_nbticket.setValue(ticketSelectionne.getNbrTicket());
        id_prix.setText(Float.toString(ticketSelectionne.getPrix()));
       
        id_categorie.setValue(ticketSelectionne.getCategories());

        
  //txdann.setText(Integer.toString(ticketSelectionne.getIdMatch().getIdMatch()));
      
      List<match> lsa=m.afficherMatch();
        data = FXCollections.observableArrayList();
       lsa.stream().forEach((e) -> {
            data.add(e);
            System.out.println(e);
        });
       
       tableViws.setItems(data);
      //  txdann.setCellValueFactory(new PropertyValueFactory<>("id_ticket"));
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
            }
        });
    }

    @FXML
    void ModifierTicket(ActionEvent event) throws IOException {
      //  int s = Integer.parseInt(txdann.getText());
         int z = ticketSelectionne.getIdUser().getId();
        Ticket x = new Ticket(ticketSelectionne.getIdTicket(), id_nbticket.getValue(), id_categorie.getValue(), Float.parseFloat(id_prix.getText()), new Fos_User(z), new match(ticketSelectionne.getIdMatch().getIdMatch()));

        tda.modifierTicket(x);
         Parent creerGroupe = FXMLLoader.load(getClass().getResource("/GUI/ListTickets.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
       sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/Style.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();
       /* Stage stage ;
     
            FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("ListTickets.fxml"));
         Parent  root = afficher.load();
            stage =new Stage();*/
         Notifications.create().title("Signaler").text("Ticket Modifié ").showConfirm();
              
    }

    public ModifierTicketController() {
    }

}
