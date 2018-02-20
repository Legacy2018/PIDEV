/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ConsulterAnnoncesCovoiturageController.Annonce;
import static Controllers.ConsulterAnnoncesCovoiturageController.IdAnnonce;
import static Controllers.ConsulterAnnoncesCovoiturageController.IdAnnonceur;
import Entities.Annonce_collocation;
import Services.CollocationService;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medma
 */
public class ConsulterAnnoncesCollocationsController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    private JFXButton btn_mail;

    @FXML
    private TextField idAnnonce;

    @FXML
    private TextField IdAnnonceur;

    @FXML
    private Label DateDepart;

    @FXML
    private ImageView Image1;

    @FXML
    private Label DateArivee;

  
      @FXML
    private VBox annoncesVBox;
      ScrollPane scroller = new ScrollPane(annoncesVBox);
      public static int isConsulterAnnoncesCollocaionsController=0;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
// todo afficher la liste des annonces envoyer email et consulter l'emplacement de la propriete 
//grace a un marquer
//pareil pour l'annonce de covoiturage annonce plus la mise en place d'une intreface affichant le map en plus d'un textarea pour 
// le body de l'email vers l'email de l'annonceur qui sera un retour sur la fonction de Ali gestion des utilisateurs
   scroller.setFitToWidth(true);
       try {
           getallAnnoncesList();
       } catch (FileNotFoundException ex) {
         
       }
       annoncesAnchorPane.setVisible(false);
    }
    
    
    @FXML
    void ShowMap(ActionEvent event) {

    }

    @FXML
    void SendMail(ActionEvent event) {
        isConsulterAnnoncesCollocaionsController=1;
        Controllers.ConsulterAnnoncesCovoiturageController.IdAnnonce=Annonce.getId_annonce();
         Controllers.ConsulterAnnoncesCovoiturageController.IdAnnonceur=Annonce.getId_annonceur();
   try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/EnvoyerMail.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                        Scene scene = new Scene(root1);
        scene.getStylesheets().add(getClass().getResource("../Asset/envoyerMail.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(scene);  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
    }
 public void getallAnnoncesList() throws FileNotFoundException{
        CollocationService annService = new CollocationService();
       List<Annonce_collocation> annonces = annService.findByIdAnnonceur(1);
        
        if(!annonces.isEmpty()){
            
            for (Annonce_collocation annonce: annonces) {
                
       
                AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(annoncesAnchorPane.getStyle());
        newAnnoncesAnchorPane.setEffect(annoncesAnchorPane.getEffect());

       
        //fromtolabel         
        Label fromToLabel2 = new Label();
        fromToLabel2.setFont(titre.getFont());
        fromToLabel2.setTextFill(titre.getTextFill());
        fromToLabel2.setLayoutX(titre.getLayoutX());
        fromToLabel2.setLayoutY(titre.getLayoutY());
        fromToLabel2.setText("Titre de l'annonce :"+annonce.getTitre_annonce());
       //datedepartlabel
       Label description =  new Label();
        description.setFont(Description.getFont());
        fromToLabel2.setTextFill(titre.getTextFill());
        description.setLayoutX(Description.getLayoutX());
        description.setLayoutY(Description.getLayoutY());
        description.setText("Description de l'annonce :"+annonce.getDescription());
       
        //distanceLabel
        Label departLabel2 = new Label();
        departLabel2.setFont(DateDepart.getFont());
        departLabel2.setTextFill(DateDepart.getTextFill());
        departLabel2.setLayoutX(DateDepart.getLayoutX());
        departLabel2.setLayoutY(DateDepart.getLayoutY());
        departLabel2.setText("Date debut :"+annonce.getDatedebut());
        //prixLabel
        Label arriveeLabel2 = new Label();
        arriveeLabel2.setFont(DateArivee.getFont());
        arriveeLabel2.setTextFill(DateArivee.getTextFill());
        arriveeLabel2.setLayoutX(DateArivee.getLayoutX());
        arriveeLabel2.setLayoutY(DateArivee.getLayoutY());
        arriveeLabel2.setText("Date fin :"+annonce.getDatefin());
        //tarrif
       
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
        
        JFXButton btnMail = new JFXButton();
        btnMail.setFont(btn_mail.getFont());
        btnMail.setTextFill(btn_mail.getTextFill());
        btnMail.setLayoutX(btn_mail.getLayoutX());
        btnMail.setLayoutY(btn_mail.getLayoutY());
        btnMail.setButtonType(JFXButton.ButtonType.RAISED);
        btnMail.setRipplerFill(btn_mail.getRipplerFill());
        btnMail.setText(btn_mail.getText());
       
       
         TextField idannonceur = new TextField();
        idannonceur.setFont(IdAnnonceur.getFont());
        idannonceur.setLayoutX(IdAnnonceur.getLayoutX());
        idannonceur.setLayoutY(IdAnnonceur.getLayoutY());
        idannonceur.setText(""+annonce.getId_annonceur());
        
          TextField idannonce = new TextField();
        idannonce.setFont(idAnnonce.getFont());
        idannonce.setLayoutX(idAnnonce.getLayoutX());
        idannonce.setLayoutY(idAnnonce.getLayoutY());
        idannonce.setText(""+annonce.getId_annonce());
        
        List<String> listeimages=new ArrayList();
        annonce.getUi().stream().forEach(j->listeimages.add(j.getMedia_link()));
        ImageView userImageView1 = new ImageView();
          ImageView userImageView4 = new ImageView();
           ImageView userImageView2 = new ImageView();
            ImageView userImageView3 = new ImageView();
        int i=0;
        while((i<listeimages.size())&&(i<4)){
        if(i==0){
        
        userImageView1.setImage(Image1.getImage());
        userImageView1.setLayoutX(Image1.getLayoutX());
        userImageView1.setLayoutY(Image1.getLayoutY());
        userImageView1.setStyle(Image1.getStyle());
        userImageView1.setFitWidth(Image1.getFitWidth());
        userImageView1.setFitHeight(Image1.getFitHeight());
            System.out.println("azeijoaziizehfilznf "+listeimages.get(i));
        Image image2 = new Image(new FileInputStream(listeimages.get(i)));
        userImageView1.setImage(image2);
        }
        else if(i==1){
       
        userImageView2.setImage(Image2.getImage());
        userImageView2.setLayoutX(Image2.getLayoutX());
        userImageView2.setLayoutY(Image2.getLayoutY());
        userImageView2.setStyle(Image2.getStyle());
        userImageView2.setFitWidth(Image2.getFitWidth());
        userImageView2.setFitHeight(Image2.getFitHeight());
        Image image2 = new Image(new FileInputStream(listeimages.get(i)));
        userImageView2.setImage(image2);
       }
        else if(i==2){
        
        userImageView3.setImage(Image3.getImage());
        userImageView3.setLayoutX(Image3.getLayoutX());
        userImageView3.setLayoutY(Image3.getLayoutY());
        userImageView3.setStyle(Image3.getStyle());
        userImageView3.setFitWidth(Image3.getFitWidth());
        userImageView3.setFitHeight(Image3.getFitHeight());
        Image image2 = new Image(new FileInputStream(listeimages.get(i)));
        userImageView3.setImage(image2);
        }
        else if(i==3){
      
        userImageView4.setImage(Image4.getImage());
        userImageView4.setLayoutX(Image4.getLayoutX());
        userImageView4.setLayoutY(Image4.getLayoutY());
        userImageView4.setStyle(Image4.getStyle());
        userImageView4.setFitWidth(Image4.getFitWidth());
        userImageView4.setFitHeight(Image4.getFitHeight());
        Image image2 = new Image(new FileInputStream(listeimages.get(i)));
        userImageView4.setImage(image2);
        }
        i++;}
    
      
        
      
        
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
        
        
        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,description,departLabel2,arriveeLabel2,separator1,userImageView1,userImageView2,userImageView3,userImageView4,btnMail,posbtn,idannonce,idannonceur);
        annoncesVBox.getChildren().add(newAnnoncesAnchorPane);
               newAnnoncesAnchorPane.setOnMouseClicked(e -> {
            		isConsulterAnnoncesCollocaionsController=1;
        Controllers.ConsulterAnnoncesCovoiturageController.IdAnnonce=annonce.getId_annonce();
         Controllers.ConsulterAnnoncesCovoiturageController.IdAnnonceur=annonce.getId_annonceur();
   try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/EnvoyerMail.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                        Scene scene = new Scene(root1);
        scene.getStylesheets().add(getClass().getResource("../Asset/envoyerMail.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(scene);  
                stage.show();
        } catch(Exception a) {
           a.printStackTrace();
          }

           

                 
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
    }    
    

