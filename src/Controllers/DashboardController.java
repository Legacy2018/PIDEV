/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private HBox boxMenus;
    @FXML
    private AnchorPane paneMatch;
    @FXML
    private AnchorPane paneDeconnection;
    @FXML
    private StackPane fabsContainer;
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     initDrawer();
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
    private void switchToMatch(MouseEvent event) throws IOException {
         
         Stage stage=(Stage) paneDeconnection.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLGestion_Match.fxml"));
        
        Scene scene2 = new Scene(root);
         scene2.getStylesheets().add(getClass().getResource("/Asset/MainFramemel.css").toExternalForm());
        
        stage.setScene(scene2);
        stage.show();
         stage.setTitle("Russie 2018");
         
    }

    @FXML
    private void Logout(MouseEvent event) throws IOException {
         Stage stage=(Stage) paneDeconnection.getScene().getWindow();
        Login_viewController.u=null;
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    
}
