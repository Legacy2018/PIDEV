/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Services.ServiceUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class ShowProfileController implements Initializable {

    @FXML
    private Label Nompnom;
    @FXML
    private Label email;
    @FXML
    private ImageView imgprofile;
    @FXML
    private Label telephone;
    @FXML
    private Label username;
    @FXML
    private JFXButton descussion;
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Recherche_ProfileController.u=new ServiceUtilisateur().findUtilisateurbyID(Recherche_ProfileController.u.getId_user());
       Nompnom.setText(Recherche_ProfileController.u.getNom()+" "+Recherche_ProfileController.u.getPnom());
       email.setText(Recherche_ProfileController.u.getEmail());
       telephone.setText(Recherche_ProfileController.u.getTelephone());
       username.setText(Recherche_ProfileController.u.getUsername());
        if(( Recherche_ProfileController.u.getImg_profile()!=null)&&(Recherche_ProfileController.u.getImg_profile()!="null"))
        imgprofile.setImage(new Image(Login_viewController.u.getImg_profile(), true));
        
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
    private void oppenMessages(ActionEvent event) throws IOException {
        Stage stage=(Stage) Nompnom.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FenetreMessagerie.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    
}
