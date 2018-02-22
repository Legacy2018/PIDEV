/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class DReclamationsController implements Initializable {

    @FXML
    private AnchorPane rootPane;
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
    private ObservableList<Reclamation> rs =FXCollections.observableArrayList();
    @FXML
    private JFXButton sup;
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    @FXML
    private CheckBox Vlu;

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
        new ServiceReclamation().getAllRec().stream().forEach(t -> rs.add(t) );
        rectab.setItems(rs);
    }    
     private void initDrawer() {
        try {
            AnchorPane SP = FXMLLoader.load(getClass().getResource("/GUI/SidePannel_1.fxml"));

            
            
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
        new ServiceReclamation().marquerlu(rectab.getSelectionModel().getSelectedItems().get(0).getId());
        rs.clear();
          if(Vlu.isSelected())
         new ServiceReclamation().getAllRec().stream().filter(t -> !t.isLu()).forEach(t -> rs.add(t) );
        else
         new ServiceReclamation().getAllRec().stream().forEach(t -> rs.add(t) );
        rectab.setItems(rs);
    }

    @FXML
    private void change(ActionEvent event) {
        rs.clear();
        if(Vlu.isSelected())
         new ServiceReclamation().getAllRec().stream().filter(t -> !t.isLu()).forEach(t -> rs.add(t) );
        else
         new ServiceReclamation().getAllRec().stream().forEach(t -> rs.add(t) );
        rectab.setItems(rs);
    }
    
}
