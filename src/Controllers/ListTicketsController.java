/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.TicketDAO;
import com.jfoenix.controls.JFXButton;
import Entities.Fos_User;
import com.jfoenix.controls.JFXTextField;
import Entities.Ticket;
import Services.MatchDAO;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author BSS
 */
public class ListTicketsController implements Initializable {

    @FXML
    private JFXTextField recherche;
    @FXML
    private Label heurAjout;
    @FXML
    private VBox VBOXTicket;

    @FXML
    private AnchorPane ticketAnchorPane;

    @FXML
    private Label Labelcategorie;

    @FXML
    private Label labelEquipe1;

    @FXML
    private Label LabelDate;

    @FXML
    private Label labelEquipe2;

    @FXML
    private Label LabelHeure;

    @FXML
    private Label LabelStade;

    @FXML
    private Label LabelNbTicket;

    @FXML
    private Label Labelprix;

    @FXML
    private JFXButton BtnModif;

    @FXML
    private JFXButton BtnSupp;

    @FXML
    private JFXButton btnCommenter;

    @FXML
    private Label LabelVS;

    @FXML
    private Separator Separator;

    @FXML
    private ImageView IMageView;

    @FXML
    private Label LabelUser;

    @FXML
    private Label sataticEquipe2;

    @FXML
    private Label saticstade;

    @FXML
    private Label staticdate;

    @FXML
    private Label saticheur;

    @FXML
    private Label staticcategorie;
    
    @FXML
    private ImageView imagemodif;

    @FXML
    private Label sataticNb;

    @FXML
    private Label staticprix;
    @FXML
    private Label sataicEquipe1;
    @FXML
    private Label saticdateajout;

    @FXML
    private JFXButton AjouterUnTicketBT;
       @FXML
    private ImageView image3;
           @FXML
    private ImageView image5;
    public String s;
    private Fos_User u;

    public static Ticket ticketSelectionne;

    public static Ticket getTicketSelectionne() {
        return ticketSelectionne;
    }

    public static void setTicketSelectionne(Ticket ticketSelectionne) {
        ListTicketsController.ticketSelectionne = ticketSelectionne;
    }
    Ticket ticket = new Ticket();
    TicketDAO tda = new TicketDAO();
    private List<Ticket> tickets;

    Login_viewController loginCQONTROLLER = new Login_viewController();
    @FXML
    private AnchorPane root;
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    @FXML
    private ImageView retour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = loginCQONTROLLER.u;
        tickets = tda.afficher_Ticket();
        // System.out.println("loggg");
        //System.out.println(u);
        getAll(tickets);
        initDrawer();
   BtnSupp.setVisible(false);
   BtnModif.setVisible(false);
   btnCommenter.setVisible(false);
           
        
        
    //       image2.setImage(new Image("Ressource/7271.2905907_full-lnd.jpg"));
 
  //image2.setFitHeight(2000);
  //image2.setFitWidth(250);
        
        /*  try {
           

              
           VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(ListTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
     
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });*/
    }
     private void initDrawer() {
        try {
            AnchorPane SP = FXMLLoader.load(getClass().getResource("/GUI/SidePannel.fxml"));

            
            
            SP.getStylesheets().add(getClass().getResource("/Asset/Style.css").toExternalForm());
            
            SidePannel.setSidePane(SP);

        } catch (IOException ex) {
           
            //tem.out.println(ex.getMessage());
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
    void AjouterUnTicket(ActionEvent event) throws IOException {
        AjouterTicketController controller = new AjouterTicketController();

     
        Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("/GUI/AjouterTicket.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/MainFram.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();

        /* stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(AjouterUnTicketBT.getScene().getWindow());
        stage.showAndWait();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();*/
    }

    /*@FXML
    void rechercherticket(KeyEvent event){      
            String recherche = idchercher.getText();
 if (   "".equals(recherche)) {
            //groupes = groupeService.getGroupbyUser(user);
             VBOXTicket.getChildren().clear();
              VBOXTicket.getChildren().add(ticketAnchorPane);
            getAll();
        } else {
            List<Ticket> lstTicket = new ArrayList<>();
           // lstTicket = TicketDAO.rechercherTicket(idchercher.getText()); 

            Set set = new HashSet();
            set.addAll(lstTicket);
            ArrayList distinctList = new ArrayList(set);
            System.out.println(distinctList);
            VBOXTicket.getChildren().clear();
             VBOXTicket.getChildren().add(ticketAnchorPane);
            
            getAll();
    }
    }*/
    public ListTicketsController() {
    }

    @FXML
    void rechercher(KeyEvent event)  {
        String Recherche = recherche.getText();
        TicketDAO tda = new TicketDAO();
        if ("".equals(Recherche)) {
           
            VBOXTicket.getChildren().clear();
            VBOXTicket.getChildren().add(ticketAnchorPane);
           
            getAll(tickets);
        } else {
            List<Ticket> lstTicket = new ArrayList<>();
            lstTicket = tda.rechercherTicket(recherche.getText());

            List<Ticket> set = new ArrayList<>();
            set.addAll(lstTicket);
            ArrayList distinctList = new ArrayList(set);
           
            VBOXTicket.getChildren().clear();
            VBOXTicket.getChildren().add(ticketAnchorPane);
 getAll(distinctList);
           
           //  System.out.println(distinctList);
        }

    }

    public void getAll(List<Ticket> tickets) {

        //  System.out.println(tickets);
        if (!tickets.isEmpty()) {
            Image image;
            for (Ticket ticket : tickets) {

                AnchorPane newTicketAnchorPane = new AnchorPane();
                newTicketAnchorPane.setStyle(ticketAnchorPane.getStyle());
                newTicketAnchorPane.setEffect(ticketAnchorPane.getEffect());

                //  ModifierButton.setRipplerFill(BtnModif.getRipplerFill());
                //    System.out.println();
                //txann.setText(Integer.toString(idTicket));
                //labeleNbTicket       
                Label labeleNbTicket = new Label();
                labeleNbTicket.setFont(LabelNbTicket.getFont());
                labeleNbTicket.setTextFill(LabelNbTicket.getTextFill());
                labeleNbTicket.setLayoutX(LabelNbTicket.getLayoutX());
                labeleNbTicket.setLayoutY(LabelNbTicket.getLayoutY());
                labeleNbTicket.setText(Integer.toString(ticket.getNbrTicket()));

                //labeleNbTicket       
                Label labeleprix = new Label();
                labeleprix.setFont(Labelprix.getFont());
                labeleprix.setTextFill(Labelprix.getTextFill());
                labeleprix.setLayoutX(Labelprix.getLayoutX());
                labeleprix.setLayoutY(Labelprix.getLayoutY());
                labeleprix.setText(Float.toString(ticket.getPrix()));
                //labeleCategorie       
                Label labeleCategorie = new Label();
                labeleCategorie.setFont(Labelcategorie.getFont());
                labeleCategorie.setTextFill(Labelcategorie.getTextFill());
                labeleCategorie.setLayoutX(Labelcategorie.getLayoutX());
                labeleCategorie.setLayoutY(Labelcategorie.getLayoutY());
                labeleCategorie.setText(ticket.getCategories());
                //labelEquipe1       
                Label labelequip1 = new Label();
                labelequip1.setFont(labelEquipe1.getFont());
                labelequip1.setTextFill(labelEquipe1.getTextFill());
                labelequip1.setLayoutX(labelEquipe1.getLayoutX());
                labelequip1.setLayoutY(labelEquipe1.getLayoutY());
              //  labelequip1.setText(ticket.getIdMatch().getEquipe1().getPays());
                MatchDAO tdo=new MatchDAO();
                labelequip1.setText(tdo.chercherMatchParId(ticket.getIdMatch().getIdMatch()).getEquipe1().getPays());
                //labelEquipe2
                Label labelequip2 = new Label();
                labelequip2.setFont(labelEquipe2.getFont());
                labelequip2.setTextFill(labelEquipe2.getTextFill());
                labelequip2.setLayoutX(labelEquipe2.getLayoutX());
                labelequip2.setLayoutY(labelEquipe2.getLayoutY());
                //labelequip2.setText(ticket.getIdMatch().getEquipe2().getPays());
                 labelequip2.setText(tdo.chercherMatchParId(ticket.getIdMatch().getIdMatch()).getEquipe2().getPays());

                //labelStade
                Label labelStade = new Label();
                labelStade.setFont(LabelStade.getFont());
                labelStade.setTextFill(LabelStade.getTextFill());
                labelStade.setLayoutX(LabelStade.getLayoutX());
                labelStade.setLayoutY(LabelStade.getLayoutY());
                labelStade.setText(tdo.chercherMatchParId(ticket.getIdMatch().getIdMatch()).getStade().getNom_Stade());

                //labelDate
                Label labelDate = new Label();
                labelDate.setFont(LabelDate.getFont());
                labelDate.setTextFill(LabelDate.getTextFill());
                labelDate.setLayoutX(LabelDate.getLayoutX());
                labelDate.setLayoutY(LabelDate.getLayoutY());
                labelDate.setText(ticket.getIdMatch().getDateMatch());

                //labelHeure
                Label labelHeure = new Label();
                labelHeure.setFont(LabelHeure.getFont());
                labelHeure.setTextFill(LabelHeure.getTextFill());
                labelHeure.setLayoutX(LabelHeure.getLayoutX());
                labelHeure.setLayoutY(LabelHeure.getLayoutY());
                labelHeure.setText(ticket.getIdMatch().getHeureMatch());
                //labelUser
                Label labelUser = new Label();
                labelUser.setFont(LabelUser.getFont());
                labelUser.setTextFill(LabelUser.getTextFill());
                labelUser.setLayoutX(LabelUser.getLayoutX());
                labelUser.setLayoutY(LabelUser.getLayoutY());
                labelUser.setText(ticket.getIdUser().getUsername());

                //labelvs
                Label labelvs = new Label();
                labelvs.setFont(LabelVS.getFont());
                labelvs.setTextFill(LabelVS.getTextFill());
                labelvs.setLayoutX(LabelVS.getLayoutX());
                labelvs.setLayoutY(LabelVS.getLayoutY());
                labelvs.setText(LabelVS.getText());
                //laheurajout
                Label labelheurajout = new Label();
                labelheurajout.setFont(heurAjout.getFont());
                labelheurajout.setTextFill(heurAjout.getTextFill());
                labelheurajout.setLayoutX(heurAjout.getLayoutX());
                labelheurajout.setLayoutY(heurAjout.getLayoutY());
             labelheurajout.setText(toString().valueOf(ticket.getHeurAjout()));
                //SEP
                Separator separator1 = new Separator();
                separator1.setLayoutX(Separator.getLayoutX());
                separator1.setLayoutY(Separator.getLayoutY());
                separator1.setPrefSize(Separator.getPrefWidth(), Separator.getPrefHeight());
                separator1.setOrientation(Orientation.VERTICAL);
                //IMAGEVIEW
                ImageView ImageViewUser2 = new ImageView();

                ImageViewUser2.setImage(IMageView.getImage());
                ImageViewUser2.setLayoutX(IMageView.getLayoutX());
                ImageViewUser2.setLayoutY(IMageView.getLayoutY());
                ImageViewUser2.setStyle(IMageView.getStyle());
                ImageViewUser2.setFitWidth(IMageView.getFitWidth());
                ImageViewUser2.setFitHeight(IMageView.getFitHeight());
                //    ImageView ImageViewUser2 = new ImageView("Ressource/bck_inscri.png");
                ServiceUtilisateur uti=new ServiceUtilisateur();
                IMageView = new ImageView(uti.findUtilisateurbyID(ticket.getIdUser().getId()).getImg_profile());
                IMageView.setFitHeight(80);
                IMageView.setFitWidth(70);
               
                //image3=new ImageView("Ressource/téléchargement.png");
               //image3.setFitHeight(30);
              // image3.setFitWidth(30);
                //SupprimerButton
                JFXButton SupprimerButton = new JFXButton();
                SupprimerButton.setFont(BtnSupp.getFont());
                SupprimerButton.setTextFill(BtnSupp.getTextFill());
                SupprimerButton.setLayoutX(BtnSupp.getLayoutX());
                SupprimerButton.setLayoutY(BtnSupp.getLayoutY());
                SupprimerButton.setButtonType(JFXButton.ButtonType.RAISED);
                SupprimerButton.setRipplerFill(BtnSupp.getRipplerFill());
                SupprimerButton.setText(BtnSupp.getText());
                 //logo
                ImageView ImageViewlogosupp = new ImageView();
                ImageViewlogosupp.setImage(image3.getImage());
                ImageViewlogosupp.setLayoutX(image3.getLayoutX());
                ImageViewlogosupp.setLayoutY(image3.getLayoutY());
                ImageViewlogosupp.setStyle(image3.getStyle());
                ImageViewlogosupp.setFitWidth(image3.getFitWidth());
                ImageViewlogosupp.setFitHeight(image3.getFitHeight());
                 //logo
                ImageView ImageViewlogodetails = new ImageView();
                ImageViewlogodetails.setImage(image5.getImage());
                ImageViewlogodetails.setLayoutX(image5.getLayoutX());
                ImageViewlogodetails.setLayoutY(image5.getLayoutY());
                ImageViewlogodetails.setStyle(image5.getStyle());
                ImageViewlogodetails.setFitWidth(image5.getFitWidth());
                ImageViewlogodetails.setFitHeight(image5.getFitHeight());
                  //logo
                ImageView ImageViewlogosmodif = new ImageView();
                ImageViewlogosmodif.setImage(imagemodif.getImage());
                ImageViewlogosmodif.setLayoutX(imagemodif.getLayoutX());
                ImageViewlogosmodif.setLayoutY(imagemodif.getLayoutY());
                ImageViewlogosmodif.setStyle(imagemodif.getStyle());
                ImageViewlogosmodif.setFitWidth(imagemodif.getFitWidth());
                ImageViewlogosmodif.setFitHeight(imagemodif.getFitHeight());
                //ModifierButton
                JFXButton ModifierButton = new JFXButton();
                ModifierButton.setFont(BtnModif.getFont());
                ModifierButton.setTextFill(BtnModif.getTextFill());
                ModifierButton.setLayoutX(BtnModif.getLayoutX());
                ModifierButton.setLayoutY(BtnModif.getLayoutY());
                ModifierButton.setButtonType(JFXButton.ButtonType.RAISED);
                ModifierButton.setRipplerFill(BtnModif.getRipplerFill());
                ModifierButton.setText(BtnModif.getText());
              //  System.out.println("getall");

                //  System.out.println(ticket.getIdUser());
                //   System.out.println(u);

                                if (ticket.getIdUser().getId() != u.getId()) {
                   // System.out.println("t");
                    ImageViewlogosmodif.setVisible(false);
                    ImageViewlogosupp.setVisible(false);
                } else {
                    //System.out.println("f");
                    ImageViewlogosmodif.setVisible(true);
                    ImageViewlogosupp.setVisible(true);
                }
                Rating rate = new Rating();

                //SupprimerButton
                JFXButton CommenterButton = new JFXButton();
                CommenterButton.setFont(btnCommenter.getFont());
                CommenterButton.setTextFill(btnCommenter.getTextFill());
                CommenterButton.setLayoutX(btnCommenter.getLayoutX());
                CommenterButton.setLayoutY(btnCommenter.getLayoutY());
                CommenterButton.setButtonType(JFXButton.ButtonType.RAISED);
                CommenterButton.setRipplerFill(btnCommenter.getRipplerFill());
                CommenterButton.setText(btnCommenter.getText());
                //heurAjout
                Label labelHeurAjout = new Label();
                labelHeurAjout.setFont(heurAjout.getFont());
                labelHeurAjout.setTextFill(heurAjout.getTextFill());
                labelHeurAjout.setLayoutX(heurAjout.getLayoutX());
                labelHeurAjout.setLayoutY(heurAjout.getLayoutY());
//                labelHeurAjout.setText(ticket.getHeurAjout().toString());
                //labelstaticEquipe1
                Label labelstaticEquipe1 = new Label();
                labelstaticEquipe1.setFont(sataicEquipe1.getFont());
                labelstaticEquipe1.setTextFill(sataicEquipe1.getTextFill());
                labelstaticEquipe1.setLayoutX(sataicEquipe1.getLayoutX());
                labelstaticEquipe1.setLayoutY(sataicEquipe1.getLayoutY());
                labelstaticEquipe1.setText(sataicEquipe1.getText());

                //labelstaticEquipe
                Label labelstaticEquipe2 = new Label();
                labelstaticEquipe2.setFont(sataticEquipe2.getFont());
                labelstaticEquipe2.setTextFill(sataticEquipe2.getTextFill());
                labelstaticEquipe2.setLayoutX(sataticEquipe2.getLayoutX());
                labelstaticEquipe2.setLayoutY(sataticEquipe2.getLayoutY());
                labelstaticEquipe2.setText(sataticEquipe2.getText());
                //labelstaticEquipe
                Label labelstaticstade = new Label();
                labelstaticstade.setFont(saticstade.getFont());
                labelstaticstade.setTextFill(saticstade.getTextFill());
                labelstaticstade.setLayoutX(saticstade.getLayoutX());
                labelstaticstade.setLayoutY(saticstade.getLayoutY());
                labelstaticstade.setText(saticstade.getText());
                //labelstaticdate
                Label labelstaticdate = new Label();
                labelstaticdate.setFont(staticdate.getFont());
                labelstaticdate.setTextFill(staticdate.getTextFill());
                labelstaticdate.setLayoutX(staticdate.getLayoutX());
                labelstaticdate.setLayoutY(staticdate.getLayoutY());
                labelstaticdate.setText(staticdate.getText());
                //labelstatichheur
                Label labelstatichheur = new Label();
                labelstatichheur.setFont(saticheur.getFont());
                labelstatichheur.setTextFill(saticheur.getTextFill());
                labelstatichheur.setLayoutX(saticheur.getLayoutX());
                labelstatichheur.setLayoutY(saticheur.getLayoutY());
                labelstatichheur.setText(saticheur.getText());
                //labelstaticCategorie
                Label labelstaticCategorie = new Label();
                labelstaticCategorie.setFont(staticcategorie.getFont());
                labelstaticCategorie.setTextFill(staticcategorie.getTextFill());
                labelstaticCategorie.setLayoutX(staticcategorie.getLayoutX());
                labelstaticCategorie.setLayoutY(staticcategorie.getLayoutY());
                labelstaticCategorie.setText(staticcategorie.getText());
                //labelstaticNBR
                Label labelstaticNBR = new Label();
                labelstaticNBR.setFont(sataticNb.getFont());
                labelstaticNBR.setTextFill(sataticNb.getTextFill());
                labelstaticNBR.setLayoutX(sataticNb.getLayoutX());
                labelstaticNBR.setLayoutY(sataticNb.getLayoutY());
                labelstaticNBR.setText(sataticNb.getText());
                //labelstaticCategorie
                Label labelstaticPrix = new Label();
                labelstaticPrix.setFont(staticprix.getFont());
                labelstaticPrix.setTextFill(staticprix.getTextFill());
                labelstaticPrix.setLayoutX(staticprix.getLayoutX());
                labelstaticPrix.setLayoutY(staticprix.getLayoutY());
                labelstaticPrix.setText(staticprix.getText());
                //labelstaticCategorie
                Label labelstaticDateAjout = new Label();
                labelstaticDateAjout.setFont(saticdateajout.getFont());
                labelstaticDateAjout.setTextFill(saticdateajout.getTextFill());
                labelstaticDateAjout.setLayoutX(saticdateajout.getLayoutX());
                labelstaticDateAjout.setLayoutY(saticdateajout.getLayoutY());
                labelstaticDateAjout.setText(saticdateajout.getText());

                //Envoyerunsmsbtn
                /*    JFXButton envoyerunsmsBtn = new JFXButton();
                envoyerunsmsBtn.setFont(btnEnvoyerunSMS.getFont());
                envoyerunsmsBtn.setTextFill(btnEnvoyerunSMS.getTextFill());
                envoyerunsmsBtn.setLayoutX(btnEnvoyerunSMS.getLayoutX());
                envoyerunsmsBtn.setLayoutY(btnEnvoyerunSMS.getLayoutY());
                envoyerunsmsBtn.setButtonType(JFXButton.ButtonType.RAISED);
                envoyerunsmsBtn.setRipplerFill(btnEnvoyerunSMS.getRipplerFill());
                envoyerunsmsBtn.setText(btnEnvoyerunSMS.getText());
                 */
                VBOXTicket.getChildren().add(newTicketAnchorPane);

                newTicketAnchorPane.getChildren().addAll(labelequip1,labelheurajout,ImageViewlogodetails ,ImageViewlogosmodif,ImageViewlogosupp,labeleNbTicket, labelstaticPrix, labeleprix, labeleCategorie, labelstaticDateAjout, labelstaticNBR, labelstaticCategorie, labelstatichheur, labelstaticdate, labelstaticstade, labelequip2, labelHeure, labelDate, labelStade, ImageViewUser2, labelUser, labelvs, separator1, SupprimerButton, CommenterButton, ModifierButton, labelHeurAjout, labelstaticEquipe1, labelstaticEquipe2);

                ModifierButton.setOnMouseClicked(
                        e -> {
                          //  Parent afficher;
                           // Scene sceneAffichage;
                          //  Stage stage = new Stage();

                            // System.out.println(ticket);
                            ticketSelectionne = ticket;
                           try {
  Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("/GUI/ModifierTicket.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/MainFram.css").toExternalForm());
         stage = (Stage) ModifierButton.getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ListTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                SupprimerButton.setOnMouseClicked(
                        e -> {
                            
                             ticketSelectionne = ticket;
                            // System.out.println(ticketSelectionne.getIdTicket());
                            TicketDAO aa = new TicketDAO();
                            int x = ticketSelectionne.getIdTicket();    
                                    Alert fail= new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("Alerte");
        fail.setContentText("Votre Ticket est supprimé ");
        fail.showAndWait();
                           
                            aa.supprimerTicket(x);
                          //  System.out.println("ticket supprieme");
                         
                            Parent afficher;
                            Scene sceneAffichage;
                            Stage stage = new Stage();
                       
          Notifications notificationBuilder = Notifications.create()
                .title("Alerte!!!")
                .text("Ticket supprimé ")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                    }
                    
                    });
           Notifications.create().title("Succes").text("Ticket supprimé ").showConfirm();
                  
           try {
                                afficher = FXMLLoader.load(getClass().getResource("/GUI/ListTickets.fxml"));

                               sceneAffichage = new Scene(afficher);
                                  stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                              // sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/MainFram.css").toExternalForm());
      

                                stage.setScene(sceneAffichage);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ListTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                
                        /*    Stage stage = new Stage();
                   try {
                                afficher = FXMLLoader.load(getClass().getResource("/GUI/ListTickets.fxml"));
                 
                      sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
                             //   sceneAffichage = new Scene(afficher);
                               stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                               stage.setScene(sceneAffichage);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ListTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                        });


                /* CommenterButton.setOnMouseClicked(
                        e -> {
                           Parent afficher;
                            Scene sceneAffichage;
                            Stage stage = new Stage();
                            ticketSelectionne = ticket;
                            TicketDAO aa = new TicketDAO();
                            int y= ticketSelectionne.getIdTicket();
                            System.out.println(y);
                            aa.supprimerTicket(y);
                            System.out.println("commentaire");
                            /*

                            try {
                                afficher = FXMLLoader.load(getClass().getResource("/views/Commentaire.fxml"));

                                sceneAffichage = new Scene(afficher);
                                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                                stage.setScene(sceneAffichage);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ListTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                //  });
                CommenterButton.setOnMouseClicked(
                        e -> {
                            Parent afficher;
                            Scene sceneAffichage;
                            Stage stage = new Stage();

                          //  System.out.println(ticket);
                            ticketSelectionne = ticket;

                             try {
                                afficher = FXMLLoader.load(getClass().getResource("/GUI/Commentaire1.fxml"));

                                sceneAffichage = new Scene(afficher);
                                //  stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                               sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/MainFram.css").toExternalForm());
      

                                stage.setScene(sceneAffichage);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ListTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                /* CommenterBut*/
                //  newSujetAnchorPane.setOnMouseClicked(e -> {

                // ScreensFramework.annonceId=sujet.getId()
                //  screen.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
                //          screen.setScreen(ScreensFramework.screen3ID);
                // });
            }
        }

    }

    @FXML
    void btnCommenter(ActionEvent event) throws IOException {

        Commentaire1Controller controller = new Commentaire1Controller();

    }

    @FXML
    void BtnModif(ActionEvent event) throws IOException {
        ModifierTicketController controller = new ModifierTicketController();
    }

    @FXML
    void BtnSupp(ActionEvent event) throws IOException {
       
    }
  void deconnection(ActionEvent event) throws IOException {
      
        /*
           Parent afficher ;
        Scene sceneAffichage;
          Stage stage=new Stage();
        
        afficher = FXMLLoader.load(getClass().getResource("/GUI/toolbar.fxml"));
     sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("/Asset/Style.css").toExternalForm());
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
*/
    }
}
