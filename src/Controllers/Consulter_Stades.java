/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Services.DAOStade;
import IServices.IStadeDAO;
import com.jfoenix.controls.JFXButton;
import Entities.match;
import Entities.stade;
import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;
import org.w3c.dom.NodeList;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class Consulter_Stades implements Initializable {

    /**
     * Initializes the controller class.
-     */
 IStadeDAO s= DAOStade.getInstance();
   @FXML
    private ImageView img;   
   @FXML
    private TableView<stade> t;

    @FXML
    private TableColumn<?, ?> Nom_Stade;

    @FXML
    private TableColumn<?, ?> Pays;

    @FXML
    private TableColumn<?, ?> capacite;

    @FXML
    private JFXButton loca;

    @FXML
    private JFXButton meteo;

    @FXML
    private JFXButton acceuil;
     private ObservableList<stade> data;
     
     public static stade recu;
      @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;

    public static stade getRecu() {
        return recu;
    }

    public static void setRecu(stade recu) {
        Consulter_Stades.recu = recu;
    }

   
     

    @FXML
    void accueil(MouseEvent event) throws IOException {
         Parent creerGroupe = FXMLLoader.load(getClass().getResource("/GUI/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();


    }

    @FXML
    void loca(MouseEvent event) throws IOException {
       Parent creerGroupe = FXMLLoader.load(getClass().getResource("/GUI/FXMLLoca.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();
        
    }

    @FXML
    void meteo(MouseEvent event) throws JAXBException, IOException  {
         Parent creerGroupe = FXMLLoader.load(getClass().getResource("/GUI/Meteo.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();
        
       
        
    }
 private void initDrawer() {
        try {
            AnchorPane SP = FXMLLoader.load(getClass().getResource("/GUI/SidePannel.fxml"));

            
            
            SP.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
            
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         List<stade> lsa=s.ConsulterStadeList();
   data = FXCollections.observableArrayList();
       lsa.stream().forEach((e) -> {
            data.add(e);
        });
       t.setItems(data);
       Nom_Stade.setCellValueFactory(new PropertyValueFactory<>("Nom_Stade"));
       Pays.setCellValueFactory(new PropertyValueFactory<>("Ville"));
       //mchkla lehna
       capacite.setCellValueFactory(new PropertyValueFactory<>("Capacité"));
      initDrawer() ;
    }    
    
}
