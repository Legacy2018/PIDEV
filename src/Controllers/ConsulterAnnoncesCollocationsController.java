/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.Login_viewController.u;
import Entities.Annonce_collocation;
import Entities.Annonce_covoiturage;
import Services.CollocationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author medma
 */
public class ConsulterAnnoncesCollocationsController extends Application implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btn_mail;

    @FXML
    private Button btnEnvoyerMail;

    @FXML
    private JFXButton btn_position;

    @FXML
    private AnchorPane annoncesAnchorPane;

    @FXML
    private Label Description;

    @FXML
    private Label titre;

    @FXML
    private ImageView Image4;

    @FXML
    private ImageView Image3;

    @FXML
    private ImageView Image2;

    @FXML
    private Separator separator;

    @FXML
    private Label Tarif;

    @FXML
    private JFXButton btnMail;

    @FXML
    private TextField idAnnonce;

    @FXML
    private TextField IdAnnonceur;

    @FXML
    private Label DateDepart;
      @FXML
    private Button btn_retour;
    @FXML
    private ImageView Image1;

    @FXML
    private Label DateArivee;
    @FXML
    private TextArea MailBody;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private Button btn_Signaler;
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    private static int IdAnnonceCollocation = 0;
    private static String date = null;
    private static String AdresseToMail = null;
    public static int signs = -1;

    @FXML
    private VBox annoncesVBox;
    ScrollPane scroller = new ScrollPane(annoncesVBox);
    public static int isConsulterAnnoncesCollocaionsController = 0;
    @FXML
    private JFXTextField Ftarif;

    @FXML
    private Label FAdresse;

    @FXML
    private JFXTextField Fadresse;

    @FXML
    private DatePicker Fdate;

    @FXML
    private Button Btn_Filtrer;

    @FXML
    private Label Adresse;

    @FXML
    private Button btn_Filter_Annuler;
    
 @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDrawer();
        mapView.addMapInializedListener(this);
// todo afficher la liste des annonces envoyer email et consulter l'emplacement de la propriete 
//grace a un marquer
//pareil pour l'annonce de covoiturage annonce plus la mise en place d'une intreface affichant le map en plus d'un textarea pour 
// le body de l'email vers l'email de l'annonceur qui sera un retour sur la fonction de Ali gestion des utilisateurs
        scroller.setFitToWidth(true);
        System.out.println("hajdkgsefsf4164281f");
        try {
            getallAnnoncesList();
        } catch (FileNotFoundException ex) {

        }
        annoncesAnchorPane.setVisible(false);
        btnEnvoyerMail.setVisible(false);
        btn_Signaler.setVisible(false);

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
    void ShowMap(ActionEvent event) {

    }
    
    @FXML
    void Retour(ActionEvent event) throws IOException {
Parent afficher;
        Scene sceneAffichage;
        Stage stage = new Stage();

        afficher = FXMLLoader.load(getClass().getResource("../GUI/AccueilAnnonces.fxml"));
        sceneAffichage = new Scene(afficher);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/accueilannonces.css").toExternalForm());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
    }

    @FXML
    void AnnulerFiltres(ActionEvent event) throws IOException {
        Parent afficher;
        Scene sceneAffichage;
        Stage stage = new Stage();

        afficher = FXMLLoader.load(getClass().getResource("../GUI/ConsulterAnnoncesCollocations.fxml"));
        sceneAffichage = new Scene(afficher);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/consulterannoncescollocaions.css").toExternalForm());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
    }

    @FXML
    void Filtrer(ActionEvent event) throws FileNotFoundException {
        annoncesVBox.getChildren().clear();
        getFilterdList();
    }

    @FXML
    void SignalerAnnonce(ActionEvent event) {
        CollocationService annService = new CollocationService();
        annService.Signaler(IdAnnonceCollocation);
        annService.Trigger();
        btn_Signaler.setVisible(false);
        TrayNotification tray = new TrayNotification();
        NotificationType type = NotificationType.INFORMATION;
        tray.showAndDismiss(Duration.seconds(5));
        System.out.println("here");
        tray.setTitle("Annonce signalé");
        System.out.println("here1");
        tray.setMessage("Annonce Signalée avec succés");
        System.out.println("here2");
        tray.setNotificationType(type.INFORMATION);
        System.out.println("here3");
        signs = 1;

    }

    @FXML
    void EnvoyerMail(ActionEvent event) {
        try {
            String host = "smtp.gmail.com";
            String user = "jouinimohamedmalek@gmail.com"; //email Application
            String pass = "pi123456789";
            String to = "medmalek125@gmail.com"; //email get by id user
            String from = "jouinimohamedmalek@gmail.com";
            String subject = "Demande de participation au traget";
            String messageText;

            messageText = "je suis " + u.getNom() + " " + u.getPnom() + "\n Ce mail concerne votre anonce de collocation"
                    + "de votre proprieté qui se situe sur+" + AdresseToMail
                    + "Mon E-mail Vérifié est " + u.getEmail() + "\n "
                    + "le contenu de mmon mail est le suivant: \n" + MailBody.getText();

            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Boolean ValidateFloat() {
        try {
            Float.parseFloat(Ftarif.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void getFilterdList() throws FileNotFoundException {
        int stateChanged = 0;

        CollocationService annService = new CollocationService();
        List<Annonce_collocation> annonces = annService.getAll();
        List<Annonce_collocation> FiltredList = new ArrayList();
        if (!Fadresse.getText().equals("")) {
            System.out.println("aaaaa");
            FiltredList = annonces.stream().filter(j -> j.getAdresse().equals(Fadresse.getText())).collect(Collectors.toList());
            stateChanged = 1;
        } else if (Fdate.getValue() != null) {
            System.out.println("cccc");
            FiltredList = annonces.stream().filter(j -> j.getDatedebut().before(java.sql.Date.valueOf(Fdate.getValue()))).collect(Collectors.toList());
            stateChanged = 1;
        } else if (!Ftarif.getText().isEmpty()) {
            if ((ValidateFloat())) {
                FiltredList = annonces.stream().filter(j -> j.getTarif() <= Float.parseFloat(Ftarif.getText())).collect(Collectors.toList());
                stateChanged = 1;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajouter Annonce");
                alert.setHeaderText(null);
                alert.setContentText("L'entrée doit etre un nombre");
                alert.showAndWait();
            }
        }
        //System.out.println(filtredListd);
        if (stateChanged == 1) {

            if (!annonces.isEmpty()) {

                for (Annonce_collocation annonce : FiltredList) {

                    AnchorPane newAnnoncesAnchorPane = new AnchorPane();
                    newAnnoncesAnchorPane.setStyle(annoncesAnchorPane.getStyle());
                    newAnnoncesAnchorPane.setEffect(annoncesAnchorPane.getEffect());

                    Label adresse = new Label();
                    adresse.setFont(Adresse.getFont());
                    adresse.setTextFill(Adresse.getTextFill());
                    adresse.setLayoutX(Adresse.getLayoutX());
                    adresse.setLayoutY(Adresse.getLayoutY());
                    adresse.setText(annonce.getAdresse());
                    adresse.setVisible(false);
                    //fromtolabel         
                    Label fromToLabel2 = new Label();
                    fromToLabel2.setFont(titre.getFont());
                    fromToLabel2.setTextFill(titre.getTextFill());
                    fromToLabel2.setLayoutX(titre.getLayoutX());
                    fromToLabel2.setLayoutY(titre.getLayoutY());
                    fromToLabel2.setText("Titre de l'annonce :" + annonce.getTitre_annonce());
                    //datedepartlabel
                    Label description = new Label();
                    description.setFont(Description.getFont());
                    description.setTextFill(Description.getTextFill());
                    description.setLayoutX(Description.getLayoutX());
                    description.setLayoutY(Description.getLayoutY());
                    description.setText("Description de l'annonce :" + annonce.getDescription());

                    //distanceLabel
                    Label departLabel2 = new Label();
                    departLabel2.setFont(DateDepart.getFont());
                    departLabel2.setTextFill(DateDepart.getTextFill());
                    departLabel2.setLayoutX(DateDepart.getLayoutX());
                    departLabel2.setLayoutY(DateDepart.getLayoutY());
                    departLabel2.setText("Date debut :" + annonce.getDatedebut());
                    //prixLabel
                    Label arriveeLabel2 = new Label();
                    arriveeLabel2.setFont(DateArivee.getFont());
                    arriveeLabel2.setTextFill(DateArivee.getTextFill());
                    arriveeLabel2.setLayoutX(DateArivee.getLayoutX());
                    arriveeLabel2.setLayoutY(DateArivee.getLayoutY());
                    arriveeLabel2.setText("Date fin :" + annonce.getDatefin());

                    //tarrif
                    Label tarif = new Label();
                    tarif.setFont(Tarif.getFont());
                    arriveeLabel2.setTextFill(Tarif.getTextFill());
                    arriveeLabel2.setLayoutX(Tarif.getLayoutX());
                    arriveeLabel2.setLayoutY(Tarif.getLayoutY());
                    arriveeLabel2.setText("Tarif Par Nuité :" + annonce.getTarif());

                    Separator separator1 = new Separator();
                    separator1.setLayoutX(separator.getLayoutX());
                    separator1.setLayoutY(separator.getLayoutY());
                    separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
                    separator1.setOrientation(Orientation.HORIZONTAL);

                    JFXButton posbtn = new JFXButton();
                    posbtn.setFont(btn_position.getFont());
                    posbtn.setTextFill(btn_position.getTextFill());
                    posbtn.setLayoutX(btn_position.getLayoutX());
                    posbtn.setLayoutY(btn_position.getLayoutY());
                    posbtn.setButtonType(JFXButton.ButtonType.RAISED);
                    posbtn.setRipplerFill(btn_position.getRipplerFill());
                    posbtn.setText(btn_position.getText());

                    JFXButton mailbtn = new JFXButton();
                    mailbtn.setFont(btn_mail.getFont());
                    mailbtn.setTextFill(btn_mail.getTextFill());
                    mailbtn.setLayoutX(btn_mail.getLayoutX());
                    mailbtn.setLayoutY(btn_mail.getLayoutY());
                    mailbtn.setButtonType(JFXButton.ButtonType.RAISED);
                    mailbtn.setRipplerFill(btn_mail.getRipplerFill());
                    mailbtn.setText(btn_mail.getText());

                    TextField idannonceur = new TextField();
                    idannonceur.setFont(IdAnnonceur.getFont());
                    idannonceur.setLayoutX(IdAnnonceur.getLayoutX());
                    idannonceur.setLayoutY(IdAnnonceur.getLayoutY());
                    idannonceur.setText("" + u.getId_user());
                    idannonceur.setVisible(false);

                    TextField idannonce = new TextField();
                    idannonce.setFont(idAnnonce.getFont());
                    idannonce.setLayoutX(idAnnonce.getLayoutX());
                    idannonce.setLayoutY(idAnnonce.getLayoutY());
                    idannonce.setText("" + annonce.getId_annonce());
                    idannonce.setVisible(false);
                    List<String> listeimages = new ArrayList();
                    annonce.getUi().stream().forEach(j -> listeimages.add(j.getMedia_link()));
                    ImageView userImageView1 = new ImageView();
                    ImageView userImageView4 = new ImageView();
                    ImageView userImageView2 = new ImageView();
                    ImageView userImageView3 = new ImageView();
                    int i = 0;
                    while ((i < listeimages.size()) && (i < 4)) {
                        switch (i) {
                            case 0: {
                                userImageView1.setImage(Image1.getImage());
                                userImageView1.setLayoutX(Image1.getLayoutX());
                                userImageView1.setLayoutY(Image1.getLayoutY());
                                userImageView1.setStyle(Image1.getStyle());
                                userImageView1.setFitWidth(Image1.getFitWidth());
                                userImageView1.setFitHeight(Image1.getFitHeight());
                                System.out.println("azeijoaziizehfilznf " + listeimages.get(i));
                                Image image2 = new Image(new FileInputStream(listeimages.get(i)));
                                userImageView1.setImage(image2);
                                break;
                            }
                            case 1: {
                                userImageView2.setImage(Image2.getImage());
                                userImageView2.setLayoutX(Image2.getLayoutX());
                                userImageView2.setLayoutY(Image2.getLayoutY());
                                userImageView2.setStyle(Image2.getStyle());
                                userImageView2.setFitWidth(Image2.getFitWidth());
                                userImageView2.setFitHeight(Image2.getFitHeight());
                                Image image2 = new Image(new FileInputStream(listeimages.get(i)));
                                userImageView2.setImage(image2);
                                break;
                            }
                            case 2: {
                                userImageView3.setImage(Image3.getImage());
                                userImageView3.setLayoutX(Image3.getLayoutX());
                                userImageView3.setLayoutY(Image3.getLayoutY());
                                userImageView3.setStyle(Image3.getStyle());
                                userImageView3.setFitWidth(Image3.getFitWidth());
                                userImageView3.setFitHeight(Image3.getFitHeight());
                                Image image2 = new Image(new FileInputStream(listeimages.get(i)));
                                userImageView3.setImage(image2);
                                break;
                            }
                            case 3: {
                                userImageView4.setImage(Image4.getImage());
                                userImageView4.setLayoutX(Image4.getLayoutX());
                                userImageView4.setLayoutY(Image4.getLayoutY());
                                userImageView4.setStyle(Image4.getStyle());
                                userImageView4.setFitWidth(Image4.getFitWidth());
                                userImageView4.setFitHeight(Image4.getFitHeight());
                                Image image2 = new Image(new FileInputStream(listeimages.get(i)));
                                userImageView4.setImage(image2);
                                break;
                            }
                            default:
                                break;
                        }
                        i++;
                    }

//        ImageView userImageView2 = new ImageView();
//        
//        userImageView2.setImage(userImageView.getImage());
//        userImageView2.setLayoutX(userImageView.getLayoutX());
//        userImageView2.setLayoutY(userImageView.getLayoutY());
//        userImageView2.setStyle(userImageView.getStyle());
//        userImageView2.setFitWidth(userImageView.getFitWidth());
//        userImageView2.setFitHeight(userImageView.getFitHeight());
//        
                    //reserverButton
                    /* JFXButton reserverButton2 = new JFXButton();
        reserverButton2.setFont(reserverButton.getFont());
        reserverButton2.setTextFill(reserverButton.getTextFill());
        reserverButton2.setLayoutX(reserverButton.getLayoutX());
        reserverButton2.setLayoutY(reserverButton.getLayoutY());
        reserverButton2.setButtonType(JFXButton.ButtonType.RAISED);
        reserverButton2.setRipplerFill(reserverButton.getRipplerFill());
        reserverButton2.setText(reserverButton.getText());*/
                    newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2, description, adresse, departLabel2, arriveeLabel2, tarif, separator1, userImageView1, userImageView2, userImageView3, userImageView4, posbtn, idannonce, idannonceur);
                    annoncesVBox.getChildren().addAll(newAnnoncesAnchorPane);

                    posbtn.setOnMouseClicked(e -> {

                        IdAnnonceCollocation = annonce.getId_annonce();

                        DirectionsRequest request = new DirectionsRequest(annonce.getAdresse(), annonce.getAdresse(), TravelModes.DRIVING);
                        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
                        btnEnvoyerMail.setVisible(true);
                        if (signs != -1) {
                            btn_Signaler.setVisible(true);
                        }

                    });

                }
            }

        } else {
            getallAnnoncesList();
        }
    }

    public void getallAnnoncesList() throws FileNotFoundException {
        CollocationService annService = new CollocationService();
        List<Annonce_collocation> annonces = annService.getAll();

        if (!annonces.isEmpty()) {

            for (Annonce_collocation annonce : annonces) {

                AnchorPane newAnnoncesAnchorPane = new AnchorPane();
                newAnnoncesAnchorPane.setStyle(annoncesAnchorPane.getStyle());
                newAnnoncesAnchorPane.setEffect(annoncesAnchorPane.getEffect());

                Label adresse = new Label();
                adresse.setFont(Adresse.getFont());
                adresse.setTextFill(Adresse.getTextFill());
                adresse.setLayoutX(Adresse.getLayoutX());
                adresse.setLayoutY(Adresse.getLayoutY());
                adresse.setText(annonce.getAdresse());
                adresse.setVisible(false);
                //fromtolabel         
                Label fromToLabel2 = new Label();
                fromToLabel2.setFont(titre.getFont());
                fromToLabel2.setTextFill(titre.getTextFill());
                fromToLabel2.setLayoutX(titre.getLayoutX());
                fromToLabel2.setLayoutY(titre.getLayoutY());
                fromToLabel2.setText("Titre de l'annonce :" + annonce.getTitre_annonce());
                //datedepartlabel
                Label description = new Label();
                description.setFont(Description.getFont());
                description.setTextFill(Description.getTextFill());
                description.setLayoutX(Description.getLayoutX());
                description.setLayoutY(Description.getLayoutY());
                description.setText("Description de l'annonce :" + annonce.getDescription());

                //distanceLabel
                Label departLabel2 = new Label();
                departLabel2.setFont(DateDepart.getFont());
                departLabel2.setTextFill(DateDepart.getTextFill());
                departLabel2.setLayoutX(DateDepart.getLayoutX());
                departLabel2.setLayoutY(DateDepart.getLayoutY());
                departLabel2.setText("Date debut :" + annonce.getDatedebut());
                //prixLabel
                Label arriveeLabel2 = new Label();
                arriveeLabel2.setFont(DateArivee.getFont());
                arriveeLabel2.setTextFill(DateArivee.getTextFill());
                arriveeLabel2.setLayoutX(DateArivee.getLayoutX());
                arriveeLabel2.setLayoutY(DateArivee.getLayoutY());
                arriveeLabel2.setText("Date fin :" + annonce.getDatefin());

                //tarrif
                Label tarif = new Label();
                tarif.setFont(Tarif.getFont());
                arriveeLabel2.setTextFill(Tarif.getTextFill());
                arriveeLabel2.setLayoutX(Tarif.getLayoutX());
                arriveeLabel2.setLayoutY(Tarif.getLayoutY());
                arriveeLabel2.setText("Tarif Par Nuité :" + annonce.getTarif());

                Separator separator1 = new Separator();
                separator1.setLayoutX(separator.getLayoutX());
                separator1.setLayoutY(separator.getLayoutY());
                separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
                separator1.setOrientation(Orientation.HORIZONTAL);

                JFXButton posbtn = new JFXButton();
                posbtn.setFont(btn_position.getFont());
                posbtn.setTextFill(btn_position.getTextFill());
                posbtn.setLayoutX(btn_position.getLayoutX());
                posbtn.setLayoutY(btn_position.getLayoutY());
                posbtn.setButtonType(JFXButton.ButtonType.RAISED);
                posbtn.setRipplerFill(btn_position.getRipplerFill());
                posbtn.setText(btn_position.getText());

                JFXButton mailbtn = new JFXButton();
                mailbtn.setFont(btn_mail.getFont());
                mailbtn.setTextFill(btn_mail.getTextFill());
                mailbtn.setLayoutX(btn_mail.getLayoutX());
                mailbtn.setLayoutY(btn_mail.getLayoutY());
                mailbtn.setButtonType(JFXButton.ButtonType.RAISED);
                mailbtn.setRipplerFill(btn_mail.getRipplerFill());
                mailbtn.setText(btn_mail.getText());

                TextField idannonceur = new TextField();
                idannonceur.setFont(IdAnnonceur.getFont());
                idannonceur.setLayoutX(IdAnnonceur.getLayoutX());
                idannonceur.setLayoutY(IdAnnonceur.getLayoutY());
                System.out.println("hajdkgsjdhfbksjdn f");
                System.out.println("iddddd" + annonce.getUser().getId_user());
                idannonceur.setText("" + annonce.getUser().getId_user());
                idannonceur.setVisible(false);

                TextField idannonce = new TextField();
                idannonce.setFont(idAnnonce.getFont());
                idannonce.setLayoutX(idAnnonce.getLayoutX());
                idannonce.setLayoutY(idAnnonce.getLayoutY());
                idannonce.setText("" + annonce.getId_annonce());
                idannonce.setVisible(false);
                List<String> listeimages = new ArrayList();
                annonce.getUi().stream().forEach(j -> listeimages.add(j.getMedia_link()));
                ImageView userImageView1 = new ImageView();
                ImageView userImageView4 = new ImageView();
                ImageView userImageView2 = new ImageView();
                ImageView userImageView3 = new ImageView();
                int i = 0;
                while ((i < listeimages.size()) && (i < 4)) {
                    switch (i) {
                        case 0: {
                            userImageView1.setImage(Image1.getImage());
                            userImageView1.setLayoutX(Image1.getLayoutX());
                            userImageView1.setLayoutY(Image1.getLayoutY());
                            userImageView1.setStyle(Image1.getStyle());
                            userImageView1.setFitWidth(Image1.getFitWidth());
                            userImageView1.setFitHeight(Image1.getFitHeight());
                            System.out.println("azeijoaziizehfilznf " + listeimages.get(i));
                            Image image2 = new Image(new FileInputStream(listeimages.get(i)));
                            userImageView1.setImage(image2);
                            break;
                        }
                        case 1: {
                            userImageView2.setImage(Image2.getImage());
                            userImageView2.setLayoutX(Image2.getLayoutX());
                            userImageView2.setLayoutY(Image2.getLayoutY());
                            userImageView2.setStyle(Image2.getStyle());
                            userImageView2.setFitWidth(Image2.getFitWidth());
                            userImageView2.setFitHeight(Image2.getFitHeight());
                            Image image2 = new Image(new FileInputStream(listeimages.get(i)));
                            userImageView2.setImage(image2);
                            break;
                        }
                        case 2: {
                            userImageView3.setImage(Image3.getImage());
                            userImageView3.setLayoutX(Image3.getLayoutX());
                            userImageView3.setLayoutY(Image3.getLayoutY());
                            userImageView3.setStyle(Image3.getStyle());
                            userImageView3.setFitWidth(Image3.getFitWidth());
                            userImageView3.setFitHeight(Image3.getFitHeight());
                            Image image2 = new Image(new FileInputStream(listeimages.get(i)));
                            userImageView3.setImage(image2);
                            break;
                        }
                        case 3: {
                            userImageView4.setImage(Image4.getImage());
                            userImageView4.setLayoutX(Image4.getLayoutX());
                            userImageView4.setLayoutY(Image4.getLayoutY());
                            userImageView4.setStyle(Image4.getStyle());
                            userImageView4.setFitWidth(Image4.getFitWidth());
                            userImageView4.setFitHeight(Image4.getFitHeight());
                            Image image2 = new Image(new FileInputStream(listeimages.get(i)));
                            userImageView4.setImage(image2);
                            break;
                        }
                        default:
                            break;
                    }
                    i++;
                }

//        ImageView userImageView2 = new ImageView();
//        
//        userImageView2.setImage(userImageView.getImage());
//        userImageView2.setLayoutX(userImageView.getLayoutX());
//        userImageView2.setLayoutY(userImageView.getLayoutY());
//        userImageView2.setStyle(userImageView.getStyle());
//        userImageView2.setFitWidth(userImageView.getFitWidth());
//        userImageView2.setFitHeight(userImageView.getFitHeight());
//        
                //reserverButton
                /* JFXButton reserverButton2 = new JFXButton();
        reserverButton2.setFont(reserverButton.getFont());
        reserverButton2.setTextFill(reserverButton.getTextFill());
        reserverButton2.setLayoutX(reserverButton.getLayoutX());
        reserverButton2.setLayoutY(reserverButton.getLayoutY());
        reserverButton2.setButtonType(JFXButton.ButtonType.RAISED);
        reserverButton2.setRipplerFill(reserverButton.getRipplerFill());
        reserverButton2.setText(reserverButton.getText());*/
                newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2, description, adresse, departLabel2, arriveeLabel2, tarif, separator1, userImageView1, userImageView2, userImageView3, userImageView4, posbtn, idannonce, idannonceur);
                annoncesVBox.getChildren().addAll(newAnnoncesAnchorPane);

                posbtn.setOnMouseClicked(e -> {

                    IdAnnonceCollocation = annonce.getId_annonce();
                    date = annonce.getDatedebut().toString();
                    AdresseToMail = annonce.getAdresse();
                    DirectionsRequest request = new DirectionsRequest(annonce.getAdresse(), annonce.getAdresse(), TravelModes.DRIVING);
                    directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
                    btnEnvoyerMail.setVisible(true);
                    btn_Signaler.setVisible(true);

                });

            }
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ConsulterAnnoncesCollocations.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../Asset/consulterannoncescollocaions.css").toExternalForm());
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
        directionsPane = mapView.getDirec();
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {

    }
}
