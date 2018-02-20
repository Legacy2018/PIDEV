/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medma
 */
public class SidePanelContentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
      @FXML
    private JFXButton btn_Ticket;

    @FXML
    private JFXButton btn_Utilisateur;

    @FXML
    private JFXButton btn_Logistique;

    @FXML
    private VBox Vbox;

    @FXML
    private JFXButton btn_Match;

    @FXML
    void GetName(ActionEvent event) throws IOException {
  Parent afficher ;
          Scene sceneAffichage;
          Stage stage=new Stage();
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
             case "Gestion Logistique":
                 afficher = FXMLLoader.load(getClass().getResource("../GUI/AjouterCollocation.fxml"));
     sceneAffichage = new Scene(afficher);
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
        break;
//  ali          case "Mes groupes":
//             //   AjouterAnnoncesController.rootP.setStyle("-fx-background-color:#0000FF");
//           afficher = FXMLLoader.load(getClass().getResource("MesGroupes.fxml"));
//    sceneAffichage = new Scene(afficher);
//      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        stage.setScene(sceneAffichage);
//        stage.show();
//                break;
//     senda       case "Mes invitations":
//   afficher = FXMLLoader.load(getClass().getResource("MesInvitations.fxml"));
//    sceneAffichage = new Scene(afficher);
//      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        stage.setScene(sceneAffichage);
//        stage.show();
//        break;
//        }
    }
    }
  




    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
