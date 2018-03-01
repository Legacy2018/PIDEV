/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ConsulterAnnoncesCollocationsController.isConsulterAnnoncesCollocaionsController;
import static Controllers.Login_viewController.u;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author medma
 */
public class EnvoyerMailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btn_retour;

    @FXML
    private JFXTextArea EmailBody;

    @FXML
    private Button btn_envoyer;

    @FXML
    void b93434(ActionEvent event) {

    }
        @FXML
    private Button btn_itineraire;
        
          @FXML
    void SetMarker(ActionEvent event) {

    }

    @FXML
    void EnvoyerMail(ActionEvent event) {
         try{
            String host ="smtp.gmail.com" ;
            String user = "jouinimohamedmalek@gmail.com"; //email Application
            String pass = "pi123456789"; 
            String to = "medmalek125@gmail.com"; //email get by id user
            String from = "jouinimohamedmalek@gmail.com";
            String subject = "Demande de participation au traget"; 
            String messageText;
     
           messageText = "je suis "+u.getNom() + " "+u.getPnom()+"\n Ce mail concerne votre anonce de covoiturage"
                    +"allant de+"+ConsulterAnnoncesCovoiturageController.AdrDepart+" vers "+ConsulterAnnoncesCovoiturageController.AdrArrivee
                    + "Mon E-mail Vérifié est "+u.getEmail()+"\n "+
                    "le contenu de mmon mail est le suivant: \n"+EmailBody.getText();
            
       
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

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
        if (isConsulterAnnoncesCollocaionsController==0)
        btn_itineraire.setVisible(false);
    }
    

    @FXML
    void retour(ActionEvent event) {

    }

    
  
}
