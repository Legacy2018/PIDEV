/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import Entities.Commentaire;
import Entities.Fos_User;
import Entities.Ticket;
import Entities.TicketRate;
import Services.ServiceRate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import services.ServiceUtilisateur;
import Services.TicketDAO;
import Services.serviceCommentaire;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.List;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author BSS
 */
public class Commentaire1Controller implements Initializable {
    
    @FXML
    private Label affiche;
    
    @FXML
    private JFXButton reserver;
    
    @FXML
    private JFXButton idcomm;
    
    @FXML
    private JFXTextField com;
    
    @FXML
    private AnchorPane ticketAnchorPane;
    
    @FXML
    private Label sataicEquipe1;
    
    @FXML
    private Rating rating2;
    
    @FXML
    private Label LabelVS;
    
    @FXML
    private Separator Separator;
    
    @FXML
    private ImageView IMageView;
    
    @FXML
    private Label LabelUser;
    
    @FXML
    private Label heurAjout;
    
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
    private Label saticdateajout;
    
    @FXML
    private ListView<String> listcomment;
    
    @FXML
    private Label iddate;
    
    @FXML
    private Label LabelStade;
    
    @FXML
    private Label idequipe1;
    
    @FXML
    private Label idequipe2;
    
    @FXML
    private Label idheur;
    
    @FXML
    private Label idprix;
    
    @FXML
    private Label idnbticket;
    
    @FXML
    private Label idcategorie;
    
    @FXML
    private Rating rating;
    
    Double note;
    ServiceRate Rs = new ServiceRate();
    private List<TicketRate> listRate;
    String msg;
    private List<Ticket> tickets;
    private Ticket ticketSelectionne;
    private Fos_User u;
    ListTicketsController listTichetController = new ListTicketsController();
    Login_viewController loginCQONTROLLER = new Login_viewController();
    serviceCommentaire cs = new serviceCommentaire();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ticketSelectionne = listTichetController.getTicketSelectionne();
        
        rating2.setOrientation(Orientation.HORIZONTAL);
        rating2.setUpdateOnHover(false);
        rating.setUpdateOnHover(true);
        rating2.setDisable(true);
        rating2.setPartialRating(true);
        rating2.setRating(Rs.Moyenne(ticketSelectionne.getIdTicket()));

        // u=loginCQONTROLLER.getU();
        idcategorie.setText(ticketSelectionne.getCategories());
        iddate.setText(ticketSelectionne.getIdMatch().getDateMatch());
        idequipe1.setText(ticketSelectionne.getIdMatch().getEquipe1().getPays());
        idequipe2.setText(ticketSelectionne.getIdMatch().getEquipe2().getPays());
        idheur.setText(ticketSelectionne.getIdMatch().getHeureMatch());
        idnbticket.setText(Integer.toString(ticketSelectionne.getNbrTicket()));
        idprix.setText(Float.toString(ticketSelectionne.getPrix()));
        LabelStade.setText(ticketSelectionne.getIdMatch().getStade().getNom_Stade());
        
        Ticket ticket = new Ticket();
        TicketDAO tda = new TicketDAO();
        System.out.println(ticketSelectionne.getIdMatch().getEquipe1().getPays());
        // tickets = tda.afficher_Ticket();
        
        serviceCommentaire cs = new serviceCommentaire();
        TicketDAO ps = new TicketDAO();
        ServiceUtilisateur x = new ServiceUtilisateur();
        cs.getAll(ticketSelectionne.getIdTicket());
        //String s =
        for (int i = 0; i < cs.getAll(ticketSelectionne.getIdTicket()).size(); i++) {
            msg = x.findUtilisateurbyID(cs.getAll(ticketSelectionne.getIdTicket()).get(i).getIdUser().getId()).getUsername() + "   " + cs.getAll(ticketSelectionne.getIdTicket()).get(i).getDescription();
            listcomment.getItems().add(i, msg);
            System.out.println(msg);
            
        }
        
    }
    
    public void refresh() throws IOException {
        listcomment.getItems().clear();
        //CommentaireService cs = new CommentaireService();
        serviceCommentaire cs = new serviceCommentaire();
        TicketDAO ps = new TicketDAO();
        
        cs.getAll(ticketSelectionne.getIdTicket());
        int s = u.getId();
        ServiceUtilisateur x = new ServiceUtilisateur();
        for (int i = 0; i < cs.getAll(ticketSelectionne.getIdTicket()).size(); i++) {
            msg = x.findUtilisateurbyID(cs.getAll(ticketSelectionne.getIdTicket()).get(i).getIdUser().getId()).getUsername() + "   " + cs.getAll(ticketSelectionne.getIdTicket()).get(i).getDescription();
            listcomment.getItems().add(i, msg);
             
        //Parent creerGroupe = FXMLLoader.load(getClass().getResource("/GUI/Commentaire1.fxml"));
        //Scene sceneAffichage = new Scene(creerGroupe);
       // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      //  stage.setScene(sceneAffichage);
        //stage.show();
            
        }
    }
    
    @FXML
    void commenter(ActionEvent event) throws IOException {
        u = loginCQONTROLLER.u;
        
        Commentaire c = new Commentaire(com.getText(), new Ticket(ticketSelectionne.getIdTicket()), new Fos_User(u.getId()));
        cs.add(c);
        System.out.println(ticketSelectionne.getIdTicket());
        System.out.println(u.getId());
        System.out.println(com.getText());
        
        refresh();
        
        
    }
    
    public Commentaire1Controller() {
    }
    
    @FXML
    void ajouterRate(MouseEvent event) throws IOException {
               u = loginCQONTROLLER.u;
       
         int s=u.getId();
        TicketRate R = new TicketRate();
        TicketRate c = new TicketRate(rating.getRating(), new Fos_User(s), new Ticket(ticketSelectionne.getIdTicket()));
        note = rating.getRating();
        Rs.add(c);
        Notifications notificationBuilder = Notifications.create()
                .title("Alerte!!!")
                .text("The Rate Of the Ticket: " + Rs.Moyenne(ticketSelectionne.getIdTicket()))
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
        
        Parent creerGroupe = FXMLLoader.load(getClass().getResource("/GUI/Commentaire1.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
      //sceneAffichage = new Scene(afficher);
     sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/MainFram.css").toExternalForm());
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();
        
    }
    
    @FXML
    void reserverButton(MouseEvent event) {
        //  new SmService().sendSms(new Sms("+21625093776", "You have been signaled please check your account. CovoiturageInjection.com"));
    }
    
}
