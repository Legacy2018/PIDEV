/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Messages;
import Entities.Utilisateur;
import Services.ServiceMessage;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import Services.ServiceUtilisateur;


/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class FenetreMessagerieController extends Thread implements Initializable {

    @FXML
    private JFXTextField sender;
    private Utilisateur recever;
    @FXML
    private ListView<Utilisateur> contacts;
    private List <Messages> ListMessages=new ArrayList<>();
    private ObservableList<Utilisateur> ListContacts=FXCollections.observableArrayList();
    private ObservableList<String> nomContacts=FXCollections.observableArrayList();
    @FXML
    private JFXTextArea allmessages;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        contacts.getSelectionModel().selectedItemProperty().addListener((a,b,c)-> afficherMessage(c.getId()));
        if(Recherche_ProfileController.u!=null)
        ListContacts.add(Recherche_ProfileController.u);
        System.out.println("Id user Connecter"+Login_viewController.u);
        ListMessages=new ServiceMessage().getAllMessages(Login_viewController.u.getId());
        System.out.println(Login_viewController.u.getId());
        System.out.println(ListMessages);
        ListMessages.stream().filter(m -> m.getSender()!=Login_viewController.u.getId()).forEach(m -> updateListContacts(m.getSender()));
        if(!ListContacts.isEmpty())
        afficherMessage(ListContacts.get(0).getId());
        this.start();
        
    }    
    public void updateListContacts(int sender){
        if(!ListContacts.stream().anyMatch(c -> c.getId()==sender) )
        {
            
            ListContacts.add(new ServiceUtilisateur().findUtilisateurbyID(sender));
            nomContacts.clear();
            contacts.setItems(ListContacts);
            
        }
    }
    public void afficherMessage(int Contact){
        allmessages.clear();
        if(!ListMessages.isEmpty())
        ListMessages.stream().filter(m -> m.getSender()==Contact||m.getRecever()==Contact).sorted((m1,m2)->m1.getDatemsg().compareTo(m2.getDatemsg())).forEach(
        
                m -> {
                
                if(m.getSender()==Contact)
                {
                    allmessages.appendText(new ServiceUtilisateur().findUtilisateurbyID(Contact).getNom()+" : "+m.toString());
                }
                else
                {
                    allmessages.appendText(Login_viewController.u.getNom()+" : "+m.toString());
                }
                
                }
        );
        
    }
    @Override
    public void run() {
        Messages m=null;
       while(true)
        {
            m=new ServiceMessage().getNewMessages(0, Login_viewController.u.getId());
            if(m!=null)
            {
                ListMessages.add(m);
                updateListContacts(m.getSender());
                afficherMessage(m.getSender());
            }
            else
            {
               System.gc();
            }
        }
        
    }
    
   

    @FXML
    private void send(KeyEvent event) {
        if(event.getCode().getName().equals("Enter"))
        {
            if(sender.getText()!="")
            new ServiceMessage().sendMessage(new Messages(Login_viewController.u.getId(), contacts.selectionModelProperty().get().getSelectedItem().getId(),sender.getText() , 0, true,null));
            ListMessages.add(new Messages(Login_viewController.u.getId(), contacts.selectionModelProperty().get().getSelectedItem().getId(),sender.getText() , 0, true,null));
            allmessages.appendText(Login_viewController.u.getNom()+" : "+sender.getText()+"\n");
            sender.clear();
        }
    }
}
