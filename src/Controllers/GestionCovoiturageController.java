/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Annonce_covoiturage;
import Services.CovoiturageService;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import java.io.IOException;
import static java.lang.ProcessBuilder.Redirect.to;
import java.time.LocalDate;
import static java.time.LocalDate.from;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medma
 */
public class GestionCovoiturageController extends Application implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback  {
     protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
@FXML
    private GoogleMapView mapView;
    @FXML
    private JFXTextField Tarif_txt;

    @FXML
    private JFXTextField Adressearrivee_txt;

    @FXML
    private TableColumn<?, ?> Datedepart;

    @FXML
    private TableColumn<?, ?> Tarrifs;

    @FXML
    private TableView<Annonce_covoiturage> tableannonces;

    @FXML
    private JFXTextField Adressedepart_txt;

    @FXML
    private TableColumn<?, ?> Datearrivee;

    @FXML
    private Button btn_Ajouter;

    @FXML
    private TableColumn<?, ?> Adressearrivee;

    @FXML
    private DatePicker Datearrivee_txt;

    @FXML
    private DatePicker Datedapart_txt1;
    
    @FXML
    private JFXListView<String> ListeImages;
    
    @FXML
    private TableColumn<Annonce_covoiturage, String> idannonceur;

    @FXML
    private TableColumn<?, ?> Adressedepart;

    @FXML
    private TableColumn<Annonce_covoiturage, String> idannonce;
    
      @FXML
    private Button btn_modifier;
    
    @FXML
    private JFXTextField txidann;
  
    public int selectedAnn;
    private ObservableList<Annonce_covoiturage> data;
    List<Annonce_covoiturage> LAC;
    Annonce_covoiturage AC;
      Annonce_covoiturage AnnCo;
    CovoiturageService CS = new CovoiturageService();

    /**
     * Initializes the controller class.
     */
    @FXML
    void SupprimerAnnonces(ActionEvent event) throws IOException {
        int s = Integer.parseInt(txidann.getText());
        System.out.println(txidann.getText());
        CS.delete(s);

        Parent creerGroupe = FXMLLoader.load(getClass().getResource("../GUI/GestionCovoiturage.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();

    }
   
 @FXML
    void ModifierAnnonce(ActionEvent event) throws IOException {
        System.out.println("1111111");
          int s = Integer.parseInt(txidann.getText());
          System.out.println("1111"+s);
          System.out.println( "Annonce_covoiturage(1,+"+s+"+,"+java.sql.Date.valueOf(Datedapart_txt1.getValue())+","+ java.sql.Date.valueOf(Datearrivee_txt.getValue()));
   
     AnnCo = new Annonce_covoiturage(1, s, java.sql.Date.valueOf(Datedapart_txt1.getValue()), java.sql.Date.valueOf(Datearrivee_txt.getValue()), Adressedepart_txt.getText(), Adressearrivee_txt.getText(), Float.parseFloat(Tarif_txt.getText()));
      System.out.println(AnnCo);
        CS.edit(AnnCo);
         Parent creerGroupe = FXMLLoader.load(getClass().getResource("../GUI/GestionCovoiturage.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();

    }
    @FXML
    private void AjouterAnnonceCovoiturage(MouseEvent event) throws IOException {
        AC = new Annonce_covoiturage(1, 1, java.sql.Date.valueOf(Datedapart_txt1.getValue()), java.sql.Date.valueOf(Datearrivee_txt.getValue()), Adressedepart_txt.getText(), Adressearrivee_txt.getText(), Integer.parseInt(Tarif_txt.getText()));
        CS.add(AC);

        Parent creerGroupe = FXMLLoader.load(getClass().getResource("../GUI/GestionCovoiturage.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();

    }
    
    @FXML
    void toTextFieldAction(ActionEvent event) {
 DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Travel TODO
            mapView.addMapInializedListener(this);
        to.bindBidirectional(Adressearrivee_txt.textProperty());
        from.bindBidirectional(Adressedepart_txt.textProperty());
        List<Annonce_covoiturage> lsa = CS.findByIdAnnonceur(1);
        data = FXCollections.observableArrayList();
        System.out.println("ggiiiirrrrr");

        lsa.stream().forEach((j) -> {
            data.add(j);
        });
        tableannonces.setItems(data);
        Datedepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        Datearrivee.setCellValueFactory(new PropertyValueFactory<>("Date_arrivee"));
        Adressedepart.setCellValueFactory(new PropertyValueFactory<>("adresse_depart"));
        Adressearrivee.setCellValueFactory(new PropertyValueFactory<>("adresse_arrivee"));
        Tarrifs.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        idannonce.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
        idannonceur.setCellValueFactory(new PropertyValueFactory<>("id_annonceur"));
        setCellValueFromTableToText();
    }

    private void setCellValueFromTableToText() {
        System.out.println("messageee");
        tableannonces.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Annonce_covoiturage gr = (Annonce_covoiturage) tableannonces.getItems().get(tableannonces.getSelectionModel().getSelectedIndex());
                System.out.println(gr);
                selectedAnn = tableannonces.getSelectionModel().getSelectedIndex();
                System.out.println(selectedAnn);
                Datedapart_txt1.setValue(LocalDate.now());
                Datearrivee_txt.setValue(LocalDate.now());
                Adressedepart_txt.setText(gr.getAdresse_depart());
                Adressearrivee_txt.setText(gr.getAdresse_arrivee());
                Tarif_txt.setText(Float.toString(gr.getTarif()));
                txidann.setText(Integer.toString(gr.getId_annonce()));
                 
            }
        });
    }
    
    
    

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionCovoiturage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void mapInitialized() {
        
         MapOptions options = new MapOptions();

        options.center(new LatLong(47.606189, -122.335842))
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
