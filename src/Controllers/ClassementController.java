/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Equipe;
import IServices.IMatchDAO;
import IServices.IStadeDAO;
import Services.DAOStade;
import Services.MatchDAO;
import Services.serviceEquipe;
import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
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
public class ClassementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    serviceEquipe e = new serviceEquipe(); 
     @FXML
    private TableView<Equipe> t;

    @FXML
    private TableColumn<Equipe, ?> equipe;

    @FXML
    private TableColumn<Equipe, ?> point;
  
   
    List<Equipe>eq=new ArrayList<>();
    @FXML
    private JFXComboBox<String> Gp;

    @FXML
    void Classement(ActionEvent event) {

        eq=e.chercherParGroupe(this.Gp.getValue());
        ObservableList<Equipe> data = FXCollections.observableArrayList();
       eq.stream().forEach((e) -> {
            data.add(e);
        });
       t.setItems(data);
        
       equipe.setCellValueFactory(new PropertyValueFactory<>("pays"));
    
       point.setCellValueFactory(new PropertyValueFactory<>("point"));
    }
      @FXML
    void accueil(MouseEvent event) throws IOException {
         Parent creerGroupe = FXMLLoader.load(getClass().getResource("/GUI/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();


    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Gp.getItems().addAll("A","B","C","D","E","F","G");
        System.out.print(eq);
        // TODO
    }    
    
}
