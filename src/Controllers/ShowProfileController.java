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
import services.ServiceUtilisateur;

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
    private AnchorPane rootPane;

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
        imgprofile.setImage(new Image(getClass().getResource(Recherche_ProfileController.u.getImg_profile()).toString(), true));
        
    }    
    
}
