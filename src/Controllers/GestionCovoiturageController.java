/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.Login_viewController.u;
import Entities.Annonce_covoiturage;
import Services.CovoiturageService;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
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
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author medma
 */
public class GestionCovoiturageController extends Application implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    private GeocodingService geocodingService;
    List<String> l = new ArrayList();
    String[] s;   
     @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    @FXML
    private Button btn_ViderFiltres;
//    @FXML
//    private Button btn_ClearFilters;
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
    private Button btn_supprimer;

    @FXML
    private JFXTextField txidann;

    @FXML
    private Button btn_retour;


    public int selectedAnn;
    private ObservableList<Annonce_covoiturage> data;
    List<Annonce_covoiturage> LAC;
    Annonce_covoiturage AC;
    Annonce_covoiturage AnnCo;
    CovoiturageService CS = new CovoiturageService();
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
//    @FXML
//    void ClearFilters(ActionEvent event) {
//  try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/ConsulterAnnoncesCovoiturage.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Scene scene = new Scene(root1);
//            scene.getStylesheets().add(getClass().getResource("../Asset/consultertoutesannonces.css").toExternalForm());
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            System.out.println("baaa33333333");
//        }
//    }
    @FXML
    private void fromOnKeyTypedEvent(KeyEvent event) {
        try {
            geocodingService = new GeocodingService();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        geocodingService.geocode(Adressedepart_txt.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
            l.clear();
            //int i;                 
            for (int i = 0; i < results.length; i++) {
                s = new String[results.length];
                s[i] = results[i].getFormattedAddress();
                System.out.println(results[i].getJSObject());
                l.add(results[i].getFormattedAddress());

            }

            for (GeocodingResult result : results) {

                TextFields.bindAutoCompletion(Adressedepart_txt, s);

            }

            TextFields.bindAutoCompletion(Adressedepart_txt, t -> {

                return l;

            });

        });
    }

    public Boolean ValidateFloat() {
        try {
            Float.parseFloat(Tarif_txt.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    @FXML
    public void ViderFiltres(ActionEvent event) throws IOException {
       Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("../GUI/GestionCovoiturage.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
    }

    @FXML
    void ModifierAnnonce(ActionEvent event) throws IOException {
        List<Annonce_covoiturage> lsa = CS.findByIdAnnonceur(u);
        int s = Integer.parseInt(txidann.getText());
                
        System.out.println("date====="+Datearrivee_txt.getValue());
        if ((Datearrivee_txt.getValue() != null) && (!Tarrifs.getText().trim().isEmpty()) && (!Adressedepart_txt.getText().trim().isEmpty())&&(!Adressearrivee_txt.getText().trim().isEmpty()) ) {
            if(ValidateFloat()){AnnCo = new Annonce_covoiturage(u, s, java.sql.Date.valueOf(Datedapart_txt1.getValue()), java.sql.Date.valueOf(LocalDate.now()), Adressedepart_txt.getText(), Adressearrivee_txt.getText(), Float.parseFloat(Tarif_txt.getText()));
            if (lsa.contains(AnnCo)) {System.out.println("faouziii"+Datearrivee_txt.getValue()+Tarrifs.getText()+Adressedepart_txt.getText()+Adressearrivee_txt.getText());
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Annonce dupliquée");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez modifier l'annonce");
                alert.showAndWait();
            } else if (Datedapart_txt1.getValue().isBefore(LocalDate.now())) {
                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Date");
                    alert.setHeaderText(null);
                    alert.setContentText("Date saisite inferieure à la date actuelle");
                    alert.showAndWait();
                }
               
             else {
                CS.edit(AnnCo);
                Parent creerGroupe = FXMLLoader.load(getClass().getResource("../GUI/GestionCovoiturage.fxml"));
                Scene sceneAffichage = new Scene(creerGroupe);
                sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(sceneAffichage);
                stage.show();
            }
            } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modifier Annonce");
                alert.setHeaderText(null);
                alert.setContentText("L'entrée doit etre un nombre");
                alert.showAndWait();;
        }
            }else{System.out.println("faouziii"+Datearrivee_txt.getValue()+Tarrifs.getText()+Adressedepart_txt.getText()+Adressearrivee_txt.getText());
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Modification");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez Remplir tout les champs");
            alert.showAndWait();}
    }

    void Vider() {
        Datedapart_txt1.setValue(LocalDate.now());
        Datearrivee_txt.setValue(LocalDate.now());
        Adressedepart_txt.setText("");
        Adressearrivee_txt.setText("");
        Tarif_txt.setText(Float.toString(0));

    }

    

    @FXML
    private void AjouterAnnonceCovoiturage(MouseEvent event) throws IOException {

        if ((Datedapart_txt1.getValue() != null) && (Tarrifs.getText() != null && !Adressedepart_txt.getText().trim().isEmpty()) && (!Adressearrivee_txt.getText().trim().isEmpty())) {
            System.out.println("mouch fergha");
            if (ValidateFloat()){
            AC = new Annonce_covoiturage(u, 1, java.sql.Date.valueOf(Datedapart_txt1.getValue()), java.sql.Date.valueOf(Datearrivee_txt.getValue()), Adressedepart_txt.getText(), Adressearrivee_txt.getText(), Integer.parseInt(Tarif_txt.getText()));
            List<Annonce_covoiturage> lsa = CS.findByIdAnnonceur(u);
            if (lsa.contains(AC)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ajouter Annonce");
                alert.setHeaderText(null);
                alert.setContentText("Annonce dupliquée");
                alert.showAndWait();
            } else if (Datedapart_txt1.getValue().isBefore(LocalDate.now())) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Date");
                alert.setHeaderText(null);
                alert.setContentText("Date saisite inferieure à la date actuelle");
                alert.showAndWait();
            } 
            else {
                System.out.println("fergha");
                CS.add(AC);
                Parent creerGroupe = FXMLLoader.load(getClass().getResource("../GUI/GestionCovoiturage.fxml"));
                Scene sceneAffichage = new Scene(creerGroupe);
                sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(sceneAffichage);
                stage.show();
            }
            }else {
                     Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ajouter Annonce");
                alert.setHeaderText(null);
                alert.setContentText("L'entrée doit etre un nombre");
                alert.showAndWait();}

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ajout");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez Remplir tout les champs");
            alert.showAndWait();
        }
    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
        Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("../GUI/AccueilAnnonces.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/accueilannonces.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

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
       initDrawer();
        btn_modifier.setVisible(false);
        btn_supprimer.setVisible(false);
        // Travel TODO
        Datearrivee_txt.setValue(LocalDate.now());
        mapView.addMapInializedListener(this);
        to.bindBidirectional(Adressearrivee_txt.textProperty());
        from.bindBidirectional(Adressedepart_txt.textProperty());
        List<Annonce_covoiturage> lsa = CS.findByIdAnnonceur(u);
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
                btn_Ajouter.setVisible(false);
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
                
                btn_modifier.setVisible(true);
                btn_supprimer.setVisible(true);
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
