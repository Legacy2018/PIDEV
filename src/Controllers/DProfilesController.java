/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class DProfilesController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton sup;
    @FXML
    private TableView<Utilisateur> tabview;
    @FXML
    private TableColumn<Utilisateur, String> id;
    @FXML
    private TableColumn<Utilisateur, String> nom;
    @FXML
    private TableColumn<Utilisateur, String> prenom;
    @FXML
    private JFXTextField username;
    @FXML
    private TableColumn<Utilisateur, String> email;
    @FXML
    private TableColumn<Utilisateur, String> telephone;
    @FXML
    private TableColumn<Utilisateur, String> activated;
    private ObservableList<Utilisateur> Us =FXCollections.observableArrayList();
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    @FXML
    private TableColumn<Utilisateur, String> Username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("pnom"));
        Username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        activated.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        new ServiceUtilisateur().getall().stream().filter( t -> t.getRole().equals("user")).forEach(t ->Us.add(t));
        tabview.setItems(Us);
        initDrawer();
    }  
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
    private void suprec(ActionEvent event) {
          new ServiceUtilisateur().suprimer(tabview.getSelectionModel().getSelectedItems().get(0).getId());
        
        Us.clear();
        new ServiceUtilisateur().getall().stream().filter( t -> t.getRole().equals("user")).forEach(t ->Us.add(t));
         tabview.setItems(Us);
    }

    @FXML
    private void filtre(KeyEvent event) {
         Us.clear();
        new ServiceUtilisateur().getallfiltred(((TextField)event.getSource()).getId(), ((TextField)event.getSource()).getText()).stream().filter( t -> t.getRole().equals("user")).forEach(t ->Us.add(t));
         tabview.setItems(Us);
    }
    
}
