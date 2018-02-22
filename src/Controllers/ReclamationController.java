/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import entites.Reclamation;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class ReclamationController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton send;
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    @FXML
    private TableView<Reclamation> rectab;
    @FXML
    private TableColumn<Reclamation, String> rid;
    @FXML
    private TableColumn<Reclamation, String> rsujet;
    @FXML
    private TableColumn<Reclamation, String> rmessage;
    @FXML
    private TableColumn<Reclamation, String> rlu;
    @FXML
    private JFXTextField Sujet;
    private ObservableList<Reclamation> rs =FXCollections.observableArrayList();
    @FXML
    private JFXButton sup;
    @FXML
    private JFXTextArea Message;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initDrawer();
        rid.setCellValueFactory(new PropertyValueFactory<>("id"));
        rsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        rmessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        rlu.setCellValueFactory(new PropertyValueFactory<>("lu"));
        new ServiceReclamation().getuserRec(Login_viewController.u.getId()).stream().forEach(t -> rs.add(t) );
        rectab.setItems(rs);
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
    private void send(ActionEvent event) {
        new ServiceReclamation().posterReclamation(new Reclamation(0 ,Login_viewController.u.getId_user(), Sujet.getText(), false, Message.getText(), null));
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reclamation");
        alert.setHeaderText("Reclamation Envoyer");
        alert.setContentText("Votre Reclamation au sujet "+Sujet.getText()+" a été envoyer");

        alert.showAndWait();
        rs.clear();
        new ServiceReclamation().getuserRec(Login_viewController.u.getId()).stream().forEach(t -> rs.add(t) );
        rectab.setItems(rs);
        
    }

    @FXML
    private void suprec(ActionEvent event) {
        new ServiceReclamation().supprimer(rectab.getSelectionModel().getSelectedItems().get(0).getId());
        rs.clear();
         new ServiceReclamation().getuserRec(Login_viewController.u.getId()).stream().forEach(t -> rs.add(t) );
        rectab.setItems(rs);
    }
    
}
