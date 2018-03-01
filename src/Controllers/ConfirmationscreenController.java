/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Katouchi
 */
public class ConfirmationscreenController implements Initializable {

    @FXML
    private JFXButton submit;
    @FXML
    private JFXTextField Num;
    @FXML
    private Label VNum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Log(ActionEvent event) throws IOException {
        if(Integer.parseInt(Num.getText())==Login_viewController.u.getNum_confirm())
        {
            new ServiceUtilisateur().enableaccount(Login_viewController.u.getId());
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/toolbar.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/MainFram.css").toExternalForm());
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.setScene(scene);
        
        stage.show();
        }
        else
        {
            VNum.setText("Reverifier la valeur");
        }
        
    }
    
}
