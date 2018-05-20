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
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import javafx.event.Event;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


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
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        contacts.getSelectionModel().selectedItemProperty().addListener((a,b,c)-> afficherMessage(c.getId()));
        
        if(Recherche_ProfileController.u!=null)
        updateListContacts(Recherche_ProfileController.u.getId());
        ListMessages=new ServiceMessage().getAllMessages(Login_viewController.u.getId());
       
        
        ListMessages.stream().filter(m -> m.getSender()!=Login_viewController.u.getId()).forEach(m -> updateListContacts(m.getSender()));
        if(!ListContacts.isEmpty())
        afficherMessage(ListContacts.get(0).getId());
        this.start();
        
     initDrawer();
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
        ListMessages.stream().filter(m -> m.getSender()==Contact||m.getRecever()==Contact).forEach(
        
                m -> {
                    System.out.println("msg lbara"+m+"\n");
                if(m.getSender()==Contact)
                {
                    allmessages.appendText(new ServiceUtilisateur().findUtilisateurbyID(Contact).getNom()+" : "+m.getMessage()+"\n");
                }
                else
                {
                    allmessages.appendText("Vous : "+m.getMessage()+"\n");
                }
                
                }
        );
        
    }
    
    
    @Override
    public void run() {
        Messages m=null;
       while(true)
        {
            m=new ServiceMessage().getNewMessages(Login_viewController.u.getId());
            if(m!=null)
            {
                
                ListMessages.add(m);
                System.out.println(m);
                System.out.println("fil run "+ListMessages);
                
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
            
            if(sender.getText()!=""){
                Messages m=new Messages(Login_viewController.u.getId(), contacts.selectionModelProperty().get().getSelectedItem().getId(),sender.getText() , 0, true,null);
            new ServiceMessage().sendMessage(m);
            ListMessages.add(m);
            allmessages.appendText("Vous : "+sender.getText()+"\n");
            sender.clear();
            }
        }
    }
}
