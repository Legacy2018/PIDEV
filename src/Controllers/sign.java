/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entites.Utilisateur;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import services.ServiceUtilisateur;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        
    }    

    @FXML
    private void add(ActionEvent event) {
       // System.out.println(Fumeur.selectedProperty().get());
        if(new ServiceUtilisateur().UsernameExiste(Username.getText())) {
            Faux.setText("Verifier vos coordonnée");
            return;
        } else if(new ServiceUtilisateur().MailExiste(Email.getText())) {
            Faux.setText("Verifier vos coordonnée");
            return;
        } else if(!password.getText().equals(Cpassword.getText())) {
            Faux.setText("Verifier vos coordonnée");
            return;
        } else
            
        new ServiceUtilisateur().ajouter(new Utilisateur(0, null, Telephone.getText(), Fumeur.selectedProperty().get(), nom.getText(), pnom.getText(), null, 0, Username.getText(), Email.getText(), password.getText(), false, false, "user"));
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
          Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login_view.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) logbtn.getScene().getWindow();
        stage.setScene(scene);
        
        stage.show();
    }

    @FXML
    private void chagefum(ActionEvent event) {
       
    }

    
    
}
