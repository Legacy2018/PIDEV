/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.TicketDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import Entities.Fos_User;
import entities.Ticket;
import Entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import Services.serviceCommentaire;

/**
 * FXML Controller class
 *
 * @author BSS
 */
public class ListTicketsController implements Initializable {

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
    private Label sataticNb;

    @FXML
    private Label staticprix;
    @FXML
    private Label sataicEquipe1;

    @FXML
    private Label saticdateajout;
       @FXML
    private JFXButton btnEnvoyerunSMS;
    @FXML
    private JFXButton AjouterUnTicketBT;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
      @FXML
    void btnEnvoyerunSMS(ActionEvent event) {

    }

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = loginCQONTROLLER.u;
        System.out.println("loggg");
        System.out.println(u);
        getAll();

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

    @FXML
    void AjouterUnTicket(ActionEvent event) throws IOException {
        AjouterTicketController controller = new AjouterTicketController();

        FXMLLoader fxmlLoader = new FXMLLoader(ListTicketsController.class.getResource("/GUI/AjouterTicket.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.show();

        /* stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(AjouterUnTicketBT.getScene().getWindow());
        stage.showAndWait();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();*/
    }

    public ListTicketsController() {
    }

    public void getAll() {
        tickets = tda.afficher_Ticket();
        //  System.out.println(tickets);
        if (!tickets.isEmpty()) {

            for (Ticket ticket : tickets) {

                AnchorPane newTicketAnchorPane = new AnchorPane();
                newTicketAnchorPane.setStyle(ticketAnchorPane.getStyle());
                newTicketAnchorPane.setEffect(ticketAnchorPane.getEffect());

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
              labelequip1.setText(ticket.getIdMatch().getEquipe1().getPays());
                //labelEquipe2
                Label labelequip2 = new Label();
                labelequip2.setFont(labelEquipe2.getFont());
                labelequip2.setTextFill(labelEquipe2.getTextFill());
                labelequip2.setLayoutX(labelEquipe2.getLayoutX());
                labelequip2.setLayoutY(labelEquipe2.getLayoutY());
               labelequip2.setText(ticket.getIdMatch().getEquipe2().getPays());

                //labelStade
                Label labelStade = new Label();
                labelStade.setFont(LabelStade.getFont());
                labelStade.setTextFill(LabelStade.getTextFill());
                labelStade.setLayoutX(LabelStade.getLayoutX());
                labelStade.setLayoutY(LabelStade.getLayoutY());
              labelStade.setText(ticket.getIdMatch().getStade().getNom_Stade());

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
                //SupprimerButton
                JFXButton SupprimerButton = new JFXButton();
                SupprimerButton.setFont(BtnSupp.getFont());
                SupprimerButton.setTextFill(BtnSupp.getTextFill());
                SupprimerButton.setLayoutX(BtnSupp.getLayoutX());
                SupprimerButton.setLayoutY(BtnSupp.getLayoutY());
                SupprimerButton.setButtonType(JFXButton.ButtonType.RAISED);
                SupprimerButton.setRipplerFill(BtnSupp.getRipplerFill());
                SupprimerButton.setText(BtnSupp.getText());
                //ModifierButton
                JFXButton ModifierButton = new JFXButton();
                ModifierButton.setFont(BtnModif.getFont());
                ModifierButton.setTextFill(BtnModif.getTextFill());
                ModifierButton.setLayoutX(BtnModif.getLayoutX());
                ModifierButton.setLayoutY(BtnModif.getLayoutY());
                ModifierButton.setButtonType(JFXButton.ButtonType.RAISED);
                ModifierButton.setRipplerFill(BtnModif.getRipplerFill());
                ModifierButton.setText(BtnModif.getText());
                System.out.println("getall");
                //  System.out.println(ticket.getIdUser());
                //   System.out.println(u);

/*                if (ticket.getIdUser().getId() != u.getId()) {
                    System.out.println("t");
                    ModifierButton.setDisable(true);
                    SupprimerButton.setDisable(true);
                } else {
                    System.out.println("f");
                    ModifierButton.setDisable(false);
                    SupprimerButton.setDisable(false);
                }*/
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
                labelHeurAjout.setText(ticket.getHeurAjout().toString());
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
                JFXButton envoyerunsmsBtn = new JFXButton();
                envoyerunsmsBtn.setFont(btnEnvoyerunSMS.getFont());
                envoyerunsmsBtn.setTextFill(btnEnvoyerunSMS.getTextFill());
                envoyerunsmsBtn.setLayoutX(btnEnvoyerunSMS.getLayoutX());
                envoyerunsmsBtn.setLayoutY(btnEnvoyerunSMS.getLayoutY());
                envoyerunsmsBtn.setButtonType(JFXButton.ButtonType.RAISED);
                envoyerunsmsBtn.setRipplerFill(btnEnvoyerunSMS.getRipplerFill());
                envoyerunsmsBtn.setText(btnEnvoyerunSMS.getText());
                VBOXTicket.getChildren().add(newTicketAnchorPane);
                
             
                
                newTicketAnchorPane.getChildren().addAll(labelequip1, envoyerunsmsBtn,labeleNbTicket, labelstaticPrix, labeleprix, labeleCategorie, labelstaticDateAjout, labelstaticNBR, labelstaticCategorie, labelstatichheur, labelstaticdate, labelstaticstade, labelequip2, labelHeure, labelDate, labelStade, ImageViewUser2, labelUser, labelvs, separator1, SupprimerButton, CommenterButton, ModifierButton, labelHeurAjout, labelstaticEquipe1, labelstaticEquipe2);
              
                ModifierButton.setOnMouseClicked(
                        e -> {
                            Parent afficher;
                            Scene sceneAffichage;
                            Stage stage = new Stage();

                            System.out.println(ticket);
                            ticketSelectionne = ticket;
                            System.out.println("ticketSelectionne");
                            System.out.println(ticketSelectionne);

                            try {
                                afficher = FXMLLoader.load(getClass().getResource("/GUI/ModifierTicket.fxml"));

                                sceneAffichage = new Scene(afficher);
                                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                                stage.setScene(sceneAffichage);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ListTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                SupprimerButton.setOnMouseClicked(
                        e -> {
                            Parent afficher;
                            Scene sceneAffichage;
                            Stage stage = new Stage();
                            ticketSelectionne = ticket;
                            TicketDAO aa = new TicketDAO();
                            int x = ticketSelectionne.getIdTicket();
                            System.out.println(x);
                            aa.supprimerTicket(x);
                            System.out.println("ticket supprieme");

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
                            ticketSelectionne = ticket;
                            TicketDAO aa = new TicketDAO();
                            int x = ticketSelectionne.getIdTicket();
                            System.out.println(x);
                            aa.supprimerTicket(x);
                            System.out.println("ticket supprieme");
                            serviceCommentaire    cs=new serviceCommentaire();
        cs.getAll(1);
        ArrayList ls=new ArrayList<String>();
        //AcceuilController.listcomment.getItems().add(cs.getAll(1));
               for(int i=0;i<cs.getAll(1).size();i++){   
                // System.out.println(cs.getAll(1).get(1).getBody());
                               //CommentaireController.str=CommentaireController.str+"\n"+cs.getAll(1).get(i).getBody();
                               ls.add( cs.getAll(1).get(i).getDescription());


                   
               }
                             try {
                                afficher = FXMLLoader.load(getClass().getResource("/GUI/Commentaire1.fxml"));

                                sceneAffichage = new Scene(afficher);
                                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                                stage.setScene(sceneAffichage);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ListTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            

                        });
              
                //  newSujetAnchorPane.setOnMouseClicked(e -> {

                // ScreensFramework.annonceId=sujet.getId()
                //  screen.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
                //          screen.setScreen(ScreensFramework.screen3ID);
                // });
            }
        }

    }
    @FXML
    void btnCommenter(ActionEvent event)throws IOException  {
    
            Commentaire1Controller controller = new Commentaire1Controller();
               
        
    }

    @FXML
    void BtnModif(ActionEvent event) throws IOException {
        ModifierTicketController controller = new ModifierTicketController();
    }

    @FXML
    void BtnSupp(ActionEvent event) throws IOException {
        Stage stage;

        FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("/GUI/ListTickets.fxml"));
        Parent root2 = afficher.load();
        stage = new Stage();

        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        //   stage.initOwner(btnInviterMembres.getScene().getWindow());
        stage.showAndWait();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();
    }

}
