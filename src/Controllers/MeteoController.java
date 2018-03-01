/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class MeteoController implements Initializable {

    
     @FXML
    private  JFXComboBox<String> boxLoca;

    @FXML
    private JFXButton btnUmidita;

    @FXML
    private JFXButton btnCalcola;

    @FXML
    private JFXTextArea txtResult;
      @FXML
    private ImageView image;
       @FXML
    private ImageView berda;
       @FXML
    private ImageView nos;

    @FXML
    private ImageView sunny;

     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boxLoca.getItems().addAll("Moscow","Saint-Pétersbourg","kazan","Samara","Iekaterinbourg","Rostov","Volgograd","sotchi","Saransk","Kaliningrad");
       
        // TODO
    } 
      
    @FXML
    void ac(MouseEvent event) throws IOException {
         Parent creerGroupe = FXMLLoader.load(getClass().getResource("/GUI/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();


    }
     
      @FXML
    void meteo(ActionEvent event) throws JAXBException, IOException {

         if (boxLoca.getValue().equals("Moscow")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("2122265", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
         }
         if (boxLoca.getValue().equals("Saint-Pétersbourg")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("24553447", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
    }
         if (boxLoca.getValue().equals("kazan")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("2121267", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
    }
           if (boxLoca.getValue().equals("Samara")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("2077746", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
    }
            if (boxLoca.getValue().equals("Iekaterinbourg")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("2112237", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
    }
              if (boxLoca.getValue().equals("Rostov")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("2123177", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
    }
            
    


              if (boxLoca.getValue().equals("Volgograd")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("2124298", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
    
            

}
              
              if (boxLoca.getValue().equals("sotchi")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("2086230", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
    
            

}
                
              if (boxLoca.getValue().equals("Kaliningrad")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("7224124", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
    
            

}
                if (boxLoca.getValue().equals("Saransk")){
            YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("12597502", DegreeUnit.CELSIUS);
        if(channel.getItem().getCondition().getTemp()<0)
        {
           image.setImage(berda.getImage());
        }
        else if(channel.getItem().getCondition().getTemp()>0 && channel.getItem().getCondition().getTemp()<15){
             image.setImage(nos.getImage());
        }
        else{
             image.setImage(sunny.getImage());
        }
        txtResult.setText("La ville "+channel.getLocation().getCity()+
                "\n visibilté est "+channel.getAtmosphere().getVisibility()
                 +"\n température est "+channel.getItem().getCondition().getTemp()+"°C"
                 +"\n vent "+channel.getWind().getSpeed()+"km/h"
                 +"\n"+channel.getLastBuildDate()
                
        
        
        );  
       
    
            

}
              
}}