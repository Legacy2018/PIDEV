/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import Entities.Commentaire;
import entites.Fos_User;
import entities.Ticket;
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

/**
 * FXML Controller class
 *
 * @author BSS
 */
public class Commentaire1Controller implements Initializable {

 

    @FXML
    private Label affiche;

    @FXML
    private JFXTextArea com;
        @FXML
    private JFXButton idcomm;

    @FXML
    private ListView<String> listcomment;
String msg;
        private Ticket ticketSelectionne;
          private Fos_User u;
   Controllers.ListTicketsController listTichetController = new Controllers.ListTicketsController();
    Login_viewController loginCQONTROLLER = new Login_viewController();
      serviceCommentaire cs=new serviceCommentaire();
  
   @FXML
       void commenter(ActionEvent event) {
            u=loginCQONTROLLER.u;
           
 Commentaire c=new Commentaire(com.getText(),new Ticket(ticketSelectionne.getIdTicket()), new Fos_User(u.getId()));
        cs.add(c);
        System.out.println(ticketSelectionne.getIdTicket());
        System.out.println(u.getId());
        System.out.println(com.getText());
       refresh();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // u=loginCQONTROLLER.getU();
              ticketSelectionne = listTichetController.getTicketSelectionne();
        serviceCommentaire cs = new serviceCommentaire();
        TicketDAO ps = new TicketDAO();
       ServiceUtilisateur x=new ServiceUtilisateur();
        cs.getAll(ticketSelectionne.getIdTicket());
           //String s =
        for (int i = 0; i < cs.getAll(ticketSelectionne.getIdTicket()).size(); i++) {
            msg = x.findUtilisateurbyID(cs.getAll(ticketSelectionne.getIdTicket()).get(i).getIdUser().getId()).getUsername()+ "   " + cs.getAll(ticketSelectionne.getIdTicket()).get(i).getDescription();
            listcomment.getItems().add(i, msg);
            System.out.println(msg);
          
    }
       
    }
        public void refresh() {
        listcomment.getItems().clear();
        //CommentaireService cs = new CommentaireService();
      serviceCommentaire cs = new serviceCommentaire();
        TicketDAO ps = new TicketDAO();
        
        cs.getAll(ticketSelectionne.getIdTicket());
        int s=u.getId();
  ServiceUtilisateur x=new ServiceUtilisateur();
        for (int i = 0; i < cs.getAll(ticketSelectionne.getIdTicket()).size(); i++) {
            msg =x.findUtilisateurbyID(cs.getAll(ticketSelectionne.getIdTicket()).get(i).getIdUser().getId()).getUsername()+ "   " + cs.getAll(ticketSelectionne.getIdTicket()).get(i).getDescription();
            listcomment.getItems().add(i, msg);

        }
    }  
    
}
