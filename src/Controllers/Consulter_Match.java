/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.serviceEquipe;
import Services.MatchDAO;
import IServices.IMatchDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import Entities.Equipe;
import Entities.match;
import Entities.stade;
import java.io.IOException;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class Consulter_Match implements Initializable {
    IMatchDAO M = MatchDAO.getInstance();
     @FXML
    private TableView<match> t;
     ObservableList<match> data ; 
     @FXML
    private TableColumn<?, ?> Equipe1_2;

    @FXML
    private TableColumn<?, ?> Equipe2_2;

    @FXML
    private TableColumn<?, ?> st_2;

    @FXML
    private TableColumn<?, ?> ph_2;

    @FXML
    private TableColumn<?, ?> dt_2;

    @FXML
    private TableColumn<?, ?> h_2;
 @FXML
    private TableColumn<?, ?> sc_2;
 @FXML
    private TableColumn<?, ?> sc_21;
 @FXML
    private JFXTextField phs;
  @FXML
    private JFXDatePicker dta;

    @FXML
    private JFXButton pha;
     @FXML
    private JFXButton d;
@FXML 
private JFXButton ac;
@FXML
    private JFXComboBox<String> sa;
 List<Equipe>eq=new ArrayList<>();
 serviceEquipe e = new serviceEquipe();
  IMatchDAO Mat = MatchDAO.getInstance();
    List eq1;

@FXML
void ac(MouseEvent event) throws IOException{
    Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();
    
}

   @FXML
    void par(MouseEvent event) {
         List<match> lsa=M.afficherMatchParequipe(sa.getValue());
         data = FXCollections.observableArrayList();
       lsa.stream().forEach((e) -> {
            data.add(e);
        });
       t.setItems(data);
        Equipe1_2.setCellValueFactory(new PropertyValueFactory<>("equipe1"));
       Equipe2_2.setCellValueFactory(new PropertyValueFactory<>("equipe2"));
       //mchkla lehna saat
       st_2.setCellValueFactory(new PropertyValueFactory<>("stade"));
       ph_2.setCellValueFactory(new PropertyValueFactory<>("phase"));
       h_2.setCellValueFactory(new PropertyValueFactory<>("heureMatch"));
       dt_2.setCellValueFactory(new PropertyValueFactory<>("dateMatch"));
       sc_2.setCellValueFactory(new PropertyValueFactory<>("score"));
       sc_21.setCellValueFactory(new PropertyValueFactory<>("score2"));
         

    }

    
    

    /**
     * Initializes the controller class.
     * 
     */
    @FXML
    void pha(ActionEvent event) {
        List<match> lsa=M.afficherMatchParPhase(phs.getText());
        data = FXCollections.observableArrayList();
       lsa.stream().forEach((e) -> {
            data.add(e);
        });
       t.setItems(data);
      Equipe1_2.setCellValueFactory(new PropertyValueFactory<>("equipe1"));
       Equipe2_2.setCellValueFactory(new PropertyValueFactory<>("equipe2"));
       //mchkla lehna saat
       st_2.setCellValueFactory(new PropertyValueFactory<>("stade"));
       ph_2.setCellValueFactory(new PropertyValueFactory<>("phase"));
       h_2.setCellValueFactory(new PropertyValueFactory<>("heureMatch"));
       dt_2.setCellValueFactory(new PropertyValueFactory<>("dateMatch"));
       sc_2.setCellValueFactory(new PropertyValueFactory<>("score"));
       sc_21.setCellValueFactory(new PropertyValueFactory<>("score2"));

    }
    @FXML
    void d(ActionEvent event){
          List<match> lsa=M.chercherMatchParDate(dta.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        data = FXCollections.observableArrayList();
       lsa.stream().forEach((e) -> {
            data.add(e);
        });
       t.setItems(data);
        Equipe1_2.setCellValueFactory(new PropertyValueFactory<>("equipe1"));
       Equipe2_2.setCellValueFactory(new PropertyValueFactory<>("equipe2"));
       //mchkla lehna saat
       st_2.setCellValueFactory(new PropertyValueFactory<>("stade"));
       ph_2.setCellValueFactory(new PropertyValueFactory<>("phase"));
       h_2.setCellValueFactory(new PropertyValueFactory<>("heureMatch"));
       dt_2.setCellValueFactory(new PropertyValueFactory<>("dateMatch"));
       sc_2.setCellValueFactory(new PropertyValueFactory<>("score"));
       sc_21.setCellValueFactory(new PropertyValueFactory<>("score2"));
     

    }
      
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eq1=e.selectEquipes().stream().map(e->e.getPays()).collect(Collectors.toList());
        System.out.print(eq1);
        this.sa.getItems().addAll(eq1);
        List<match> lsa=M.afficherMatch();
        data = FXCollections.observableArrayList();
       lsa.stream().forEach((e) -> {
            data.add(e);
        });
       t.setItems(data);
       Equipe1_2.setCellValueFactory(new PropertyValueFactory<>("equipe1"));
       Equipe2_2.setCellValueFactory(new PropertyValueFactory<>("equipe2"));
       //mchkla lehna saat
       st_2.setCellValueFactory(new PropertyValueFactory<>("stade"));
       ph_2.setCellValueFactory(new PropertyValueFactory<>("phase"));
       h_2.setCellValueFactory(new PropertyValueFactory<>("heureMatch"));
       dt_2.setCellValueFactory(new PropertyValueFactory<>("dateMatch"));
       sc_2.setCellValueFactory(new PropertyValueFactory<>("score"));
       sc_21.setCellValueFactory(new PropertyValueFactory<>("score2"));
        // TODO
    }    
    
}
