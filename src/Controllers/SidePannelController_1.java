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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class SidePannelController_1 implements Initializable {

    @FXML
    private VBox pan;
    @FXML
    private Label name;
    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton Home;
    @FXML
    private JFXButton Profil;
    @FXML
    private ImageView imgprofile;
    @FXML
    private JFXButton reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setText(Login_viewController.u.getNom());
        if(( Login_viewController.u.getImg_profile()!=null)&&(Login_viewController.u.getImg_profile()!="null"))
        imgprofile.setImage(new Image(Login_viewController.u.getImg_profile(), true));
    }    

    
    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage=(Stage) pan.getScene().getWindow();
        Login_viewController.u=null;
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        Stage stage=(Stage) pan.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void goProfil(ActionEvent event) throws IOException {
        Stage stage=(Stage) pan.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/DProfiles.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void goreclamation(ActionEvent event) throws IOException {
         Stage stage=(Stage) pan.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/DReclamations.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        
    }
    
}
