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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class ProfileController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane fabsContainer;
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField Username;
    @FXML
    private TextField Email;
    @FXML
    private TextField Telephone;
    @FXML
    private ImageView imgprofil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       initall();
        initDrawer();
        
    }   
    
    private void initall()
    {
        System.out.println(Login_viewController.u);
        nom.setText(Login_viewController.u.getNom());
        prenom.setText(Login_viewController.u.getPnom());
        Username.setText(Login_viewController.u.getUsername());
        Email.setText(Login_viewController.u.getEmail());
        Telephone.setText(Login_viewController.u.getEmail());
        if(( Login_viewController.u.getImg_profile()!=null)&&(Login_viewController.u.getImg_profile()!="null"))
        imgprofil.setImage(new Image(getClass().getResource(Login_viewController.u.getImg_profile()).toString(), true));
        
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
    private void cgn(InputMethodEvent event) {
        
        
    }

    @FXML
    private void ngc(KeyEvent event) throws IOException {
         if(event.getCode().getName().equals("Enter"))
        {new ServiceUtilisateur().update(((TextField)event.getSource()).getId(), ((TextField)event.getSource()).getText(), Login_viewController.u.getId());
         ((TextField)event.getSource()).setEditable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification");
        alert.setHeaderText("Votre "+((TextField)event.getSource()).getId()+" a été modifier");
        alert.setContentText("Modification Faite");

        alert.showAndWait();
        Login_viewController.u=new ServiceUtilisateur().findUtilisateurbyID(Login_viewController.u.getId());
        Stage stage=(Stage) rootPane.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Profile.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
         
         
        
    }
         
    }

    @FXML
    private void cng(KeyEvent event) {
        
          
    }

    @FXML
    private void changethis(MouseEvent event) {
        ((TextField)event.getSource()).setEditable(true);
       System.out.println(((TextField)event.getSource()).getId()+"  "+((TextField)event.getSource()).isDisabled());
      
        
    }
    
}
