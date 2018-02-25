/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class SidePannelController implements Initializable {

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
    @FXML
    private JFXTextField search;
    public static String srch;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setText(Login_viewController.u.getNom());
        if(( Login_viewController.u.getImg_profile()!=null)&&(Login_viewController.u.getImg_profile()!="null"))
        imgprofile.setImage(new Image(getClass().getResource(Login_viewController.u.getImg_profile()).toString(), true));
        else
        {
         imgprofile.setImage(new Image(getClass().getResource("../Ressource/a.jpg").toString(), true));
        }
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
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/toolbar.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void goProfil(ActionEvent event) throws IOException {
        Stage stage=(Stage) pan.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Profile.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void goreclamation(ActionEvent event) throws IOException {
         Stage stage=(Stage) pan.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        
    }

   
    
    

    @FXML
    private void chercher(KeyEvent event) throws IOException {
        System.out.println(event.getCode().getName());
        srch=search.getText();
        if(event.getCode().getName().equals("Enter"))
        {Stage stage = (Stage) search.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Recherche_Profile.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();}
    }
    
}
