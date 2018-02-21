/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import IServices.IMatchDAO;
import IServices.IStadeDAO;
import IServices.ITicketDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entites.Fos_User;
import Entities.match;
import entities.Ticket;

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
import javafx.scene.Node;
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
    private TableColumn<?, ?> Equipe1;

    @FXML
    private TableColumn<?, ?> Equipee2;

    @FXML
    private TableColumn<?, ?> satades;

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
        id_equipe1.setText(ticketSelectionne.getIdMatch().getEquipe1());
        id_equipe2.setText(ticketSelectionne.getIdMatch().getEquipe2());
        id_heur.setText(ticketSelectionne.getIdMatch().getHeureMatch());
        id_date.setText(ticketSelectionne.getIdMatch().getDateMatch());
        id_nbticket.setValue(ticketSelectionne.getNbrTicket());
        id_prix.setText(Float.toString(ticketSelectionne.getPrix()));
        id_stade.setText(ticketSelectionne.getIdMatch().getStade());
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
       Equipe1.setCellValueFactory(new PropertyValueFactory<>("equipe1"));
       Equipee2.setCellValueFactory(new PropertyValueFactory<>("equipe2"));
    //categories.setCellValueFactory(new PropertyValueFactory<>("categories"));
     
       satades.setCellValueFactory(new PropertyValueFactory<>("stade"));
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
               id_equipe1.setText(gr.getEquipe1());
               id_equipe2.setText(gr.getEquipe2());
                id_stade.setText(gr.getStade());
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
        int s = Integer.parseInt(txdann.getText());
         int z = ticketSelectionne.getIdUser().getId();
        Ticket x = new Ticket(ticketSelectionne.getIdTicket(), id_nbticket.getValue(), id_categorie.getValue(), Float.parseFloat(id_prix.getText()), new Fos_User(z), new match(s));

        tda.modifierTicket(x);
       // ((Node) (event.getSource())).getScene().getWindow().hide();
Stage stage ;
     
            FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("ListTickets.fxml"));
         Parent  root2 = afficher.load();
            stage =new Stage();
    
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
     //   stage.initOwner(btnInviterMembres.getScene().getWindow());
        stage.showAndWait();
        Scene scene =new Scene(root2);
    stage.setScene(scene);
    stage.show();
    }

    public ModifierTicketController() {
    }

}
