/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ConsulterAnnoncesCollocationsController.signs;
import Services.CollocationService;
import Services.CovoiturageService;
import static Services.CovoiturageService.DeletedAnnonce;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author medma
 */
public class AccueilAnnoncesController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private HBox boxMenus;

    @FXML
    private AnchorPane paneTickets;

    @FXML
    private StackPane fabsContainer;

    @FXML
    private AnchorPane paneBuses;

    @FXML
    private AnchorPane paneDrivers;

    @FXML
    private AnchorPane paneUsers;
    
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    

    @FXML
    void AnnoncesCovoiturage(MouseEvent event) throws IOException {

            Parent afficher ;
            Scene sceneAffichage;
            Stage stage=new Stage();
        
            afficher = FXMLLoader.load(getClass().getResource("/GUI/ConsulterAnnoncesCovoiturage.fxml"));
            sceneAffichage = new Scene(afficher);
            sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/consultertoutesannonces.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
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
    void GestionCovoiturage(MouseEvent event) throws IOException {   
         Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("/GUI/GestionCovoiturage.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/fxml.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
    }

    @FXML
    void AnnoncesCollocation(MouseEvent event) throws IOException {
          Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("/GUI/ConsulterAnnoncesCollocations.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/consulterannoncescollocaions.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
    }

    @FXML
    void GestionCollocation(MouseEvent event) throws IOException {
         Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("/GUI/GestionCollocation.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/ajoutercollocation.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        signs=-1;
        initDrawer();
        CollocationService ColSer= new CollocationService();
        ColSer.SuppressionAutomatique();
        ColSer.Trigger();
        CovoiturageService CovSer=new CovoiturageService();
        CovSer.Trigger();
        System.out.println("supppriméééeee"+DeletedAnnonce);
        if(DeletedAnnonce!=null){
         TrayNotification tray = new TrayNotification();
                            NotificationType type = NotificationType.INFORMATION;
                            tray.showAndDismiss(Duration.seconds(5));
              tray.setTitle("Annonce signalé"  );
              tray.setMessage("Votre Annonce '"+CovoiturageService.DeletedAnnonce.getAdresse_depart()
              +"' et comme andresse d'arrivée '"+CovoiturageService.DeletedAnnonce.getAdresse_arrivee()+"a été signalée et supprimée");
           tray.setNotificationType(type.INFORMATION);
        }
    }    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/AccueilAnnonces.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/accueilannonces.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
