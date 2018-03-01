/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Equipe;
import Services.metierequipe;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Emel
 */
public class StatistiqueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label aa;

    @FXML
    private Button bt;
    metierequipe me = new metierequipe();

    void getEq(Equipe e) {
        Equipe equipe = e;
        float tot = me.getTotal(equipe);
        float CartRouge = me.getRouge(equipe);
        float CartJaune = me.getJaune(equipe);
        float totjaune = me.getTotalJaune();
        float totrouge = me.getTotalRouge();
        float st1 = CartJaune / totjaune;
    
        ObservableList<PieChart.Data> donneJ = FXCollections.observableArrayList(
                new PieChart.Data("" + e.getPays(), CartJaune / totjaune),
                new PieChart.Data("Adversaires", 1 - (CartJaune / totjaune)) //,
        );
        ObservableList<PieChart.Data> donneR = FXCollections.observableArrayList(
                new PieChart.Data("" + e.getPays(), CartRouge / totrouge),
                new PieChart.Data("Adversaires", 1 - (CartRouge / totrouge))
        
        );
        System.out.println(equipe);
        System.out.println("rouge" + CartRouge);
        System.out.println("jaune" + CartJaune);
        statjaune.setData(donneJ);
        statrouge.setData(donneR);
    
        lblro.setText(String.valueOf((int)CartRouge));
        lbljau.setText(String.valueOf((int)CartJaune));
        lbltot.setText(String.valueOf((int)CartJaune + (int)CartRouge));
    }

    @FXML
    private PieChart statrouge;

    @FXML
    private ImageView ymg1;

    @FXML
    private ImageView img2;

    @FXML
    private PieChart statjaune;

    @FXML
    void stati(ActionEvent event) {

    }
    @FXML
    private Label lblro;

    @FXML
    private Label lbljau;

    @FXML
    private Label lbltot;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
