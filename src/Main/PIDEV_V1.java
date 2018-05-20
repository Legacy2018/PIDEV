/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Services.ServiceUtilisateur;

/**
 *
 * @author Katouchi
 */
public class PIDEV_V1 extends Application {
    
    @Override
    public void start(Stage stage) {
        try
        {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Asset/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image("/Ressource/fa.png"));
        stage.show();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //System.out.println(new ServiceUtilisateur().getall());
    }
    
}
