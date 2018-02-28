/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class Login_viewController implements Initializable {

    @FXML
    private Button submit;
    @FXML
    private Label Fauxmail;
    @FXML
    private PasswordField password;
    @FXML
    private TextField ID;
    @FXML
    private Button signinbtn;
    @FXML
    private Label accees;
    public static Utilisateur u;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Log(ActionEvent event){
        
        u=new ServiceUtilisateur().findUtilisateur(ID.getText());
        System.out.println(u);
        if(u!=null)
        {System.out.println(u.getPassword()+"   "+password.getText());
         
            if(!u.getPassword().equals(password.getText()))
            {
                System.out.println("Faux");
                accees.setText("Login où mot de passe erroné");
            }
            else
            { 
                
                
                    try
                    {
                        Stage stage = (Stage) submit.getScene().getWindow();
                        Parent root;
                        if(u.isEnabled())
                {
                        if(u.getRole().equals("user"))
                            root = FXMLLoader.load(getClass().getResource("/GUI/toolbar.fxml"));
                        else
                            root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard.fxml"));
                 }
                else
                {
                    root = FXMLLoader.load(getClass().getResource("/GUI/Confirmationscreen.fxml"));
                }
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
                        stage.setScene(scene);
                        stage.show();
                    }
                    catch(IOException e)
                    {
                        System.out.println(e);
                    }
                
                
                
            }
                
        }
        else accees.setText("Login où mot de passe erroné");
    }

   
    
    @FXML
    private void Sign(ActionEvent event) throws IOException {
        Stage stage = (Stage) submit.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Sign_view.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    


    @FXML
    private void Veriflog(KeyEvent event) {
                if((!new ServiceUtilisateur().MailExiste(ID.getText()))&&(!new ServiceUtilisateur().UsernameExiste(ID.getText())))
            accees.setText("Login où mot de passe erroné");
        else
            accees.setText("");
        
    }

    @FXML
    private void Veriflogs(KeyEvent event) {
    }

    @FXML
    private void Verif(KeyEvent event) {
    }

    
}
