/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.EmailAttachmentSender;
import static Entities.EmailAttachmentSender.sendEmailWithAttachments;
import com.jfoenix.controls.JFXButton;
import Entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.max;
import static java.lang.Math.max;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.concurrent.ThreadLocalRandom;
import Services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class sign implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField pnom;
    @FXML
    private TextField Username;
    @FXML
    private TextField Email;
    @FXML
    private ToggleButton Fumeur;
    @FXML
    private TextField Telephone;
    @FXML
    private PasswordField Cpassword;
    @FXML
    private PasswordField password;
    @FXML
    private Label UsernameExiste;
    @FXML
    private Label MailExiste;
    @FXML
    private Label NoMatch;
    @FXML
    private Button add;
    @FXML
    private Label Faux;
    @FXML
    private Button logbtn;
    private Label fum;
    @FXML
    private JFXButton loadImg;
    @FXML
    private ImageView profile;
    @FXML
    private Label imgpath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     // profile.setImage(new Image(getClass().getResource("../Ressource/Tunisia$20-$20Chott$20el$20Jerid.jpg").toString(), true));
        
    }    

    @FXML
    private void add(ActionEvent event) {
       // System.out.println(Fumeur.selectedProperty().get());
       System.out.println("hetha houwa"+imgpath.getText());
        if(new ServiceUtilisateur().UsernameExiste(Username.getText())) {
            Faux.setText("Verifier vos coordonnée");
            return;
        } else if(new ServiceUtilisateur().MailExiste(Email.getText())) {
            Faux.setText("Verifier vos coordonnée");
            return;
        } else if(!password.getText().equals(Cpassword.getText())) {
            Faux.setText("Verifier vos coordonnée");
            return;
        }
        int num_confirmation=ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
         try {
            sendEmailWithAttachments(Email.getText(),
                "Email de Confirmation", EmailAttachmentSender.part1+num_confirmation+EmailAttachmentSender.part2, null);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
        new ServiceUtilisateur().ajouter(new Utilisateur(0, null, Telephone.getText(), Fumeur.selectedProperty().get(), nom.getText(), pnom.getText(), null, 0, Username.getText(), Email.getText(), password.getText(), false, false, "user",imgpath.getText(),num_confirmation));
        Faux.setText("Inscription Confirmer");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inscription");
        alert.setHeaderText("Inscription Envoyé");
        alert.setContentText("Votre Inscription a été envoyé");

        alert.showAndWait();
    }

    @FXML
    private void Vuser(KeyEvent event) {
        if(new ServiceUtilisateur().UsernameExiste(Username.getText())) UsernameExiste.setText("Existe Déja");
        else UsernameExiste.setText("");
        
    }

    @FXML
    private void Vmail(KeyEvent event) {
            if(new ServiceUtilisateur().MailExiste(Email.getText())) MailExiste.setText("Existe Déja");
            else MailExiste.setText("");
    }

    @FXML
    private void Vpass(KeyEvent event) {
        if(!password.getText().equals(Cpassword.getText())) NoMatch.setText("No matching password");
        else NoMatch.setText("");
    }

    @FXML
    private void log(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        Stage stage=(Stage) Faux.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
      
           
       
    }

    @FXML
    private void chagefum(ActionEvent event) {
       
    }

    @FXML
    private void filechooser(ActionEvent event) throws URISyntaxException, IOException {
        
         FileChooser fileChooser = new FileChooser();
            File file;
              //Set extension filter
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));  
         
//Show open file dialog
              file = fileChooser.showOpenDialog(null);
            
            
           Path from = Paths.get(file.toURI());
        Path to = Paths.get("C:\\Users\\Katouchi\\Documents\\GitHub\\PIDEV1\\src\\Ressource\\"+file.getName());
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(from, to, options);
        
        
        imgpath.setText(""+file.getName());
        profile.setImage(new Image(getClass().getResource("../Ressource/"+file.getName()).toString(), true));

    }

    
    
}
