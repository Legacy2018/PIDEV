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
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class Recherche_ProfileController extends Application implements Initializable {

    private VBox annoncesVBox;
    @FXML
    private JFXHamburger Sp;
    @FXML
    private JFXDrawer SidePannel;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label titre;
    @FXML
    private Separator separator;
    @FXML
    private VBox Profilevbox;
    @FXML
    private AnchorPane Profile;
    ScrollPane scroller = new ScrollPane(Profilevbox);
    @FXML
    private ImageView Profileimg;
    @FXML
    private JFXTextField search;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        scroller.setFitToWidth(true);
       
    initDrawer();
    AfficherUtilisateur(new ServiceUtilisateur().getall());
    
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
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Recherche_Profile.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void AfficherUtilisateur(List <Utilisateur> Users)
    {
       Users.stream().forEach( u ->{
            AnchorPane newProfile = new AnchorPane();
        newProfile.setStyle(Profile.getStyle());
        newProfile.setEffect(Profile.getEffect());
        
        
        Label nom = new Label();
        nom.setFont(titre.getFont());
        nom.setTextFill(titre.getTextFill());
        nom.setLayoutX(titre.getLayoutX());
        nom.setLayoutY(titre.getLayoutY());
        nom.setText("Votre Nom :"+u.getNom());
        
        
        ImageView imgprof;
        if(( u.getImg_profile()!=null)&&(u.getImg_profile()!="null"))
        {
        imgprof=new ImageView(new Image(getClass().getResource(u.getImg_profile()).toString(), true));
        
        }
        else
        {
         imgprof=new ImageView(new Image(getClass().getResource("../Ressource/a.jpg").toString(), true));
        }
        imgprof.setLayoutX(Profileimg.getLayoutX());
        imgprof.setLayoutY(Profileimg.getLayoutY());
        imgprof.setStyle(Profileimg.getStyle());
        imgprof.setFitWidth(Profileimg.getFitWidth());
        imgprof.setFitHeight(Profileimg.getFitHeight());
        
        
        Separator sp=new Separator();
        sp.setLayoutX(separator.getLayoutX());
        sp.setLayoutY(separator.getLayoutY());
        sp.setStyle(separator.getStyle());
        sp.setMinWidth(756);
        sp.setMinHeight(7);
        newProfile.getChildren().addAll(imgprof,nom,sp);
        
        Profilevbox.getChildren().add(newProfile);
       }
       
       
       
       
       );
    }

    @FXML
    private void Reloadlist(KeyEvent event) {
        Profilevbox.getChildren().clear();
        AfficherUtilisateur(new ServiceUtilisateur().getallfiltred("nom", search.getText()));
        AfficherUtilisateur(new ServiceUtilisateur().getallfiltred("prenom", search.getText()));
        
    }
}
