package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Services.metierequipe;
import Entities.Equipe;
import Entities.Joueur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import Services.serviceEquipe;
import Services.serviceJoueur;

/**
 * FXML Controller class
 *
 * @author Emel
 */
public class DisplayEquipeJoueurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Services.serviceEquipe se = new serviceEquipe();
    Services.serviceJoueur sj = new serviceJoueur();
    Services.metierequipe me = new metierequipe();
    public ObservableList<Equipe> getEq() {
        ObservableList<Equipe> p = FXCollections.observableArrayList(se.selectEquipes());
        return p;
    }
    @FXML
    private AnchorPane anch;

    @FXML
    private ScrollPane scr1;

    @FXML
    private AnchorPane anch1;

    @FXML
    private TableColumn<?, ?> eq;

    @FXML
    private ScrollPane scr2;

    @FXML
    private AnchorPane anch2;

    @FXML
    private TableView lstjou;

    @FXML
    private TableColumn<?, ?> id_joueur;

    @FXML
    private TableColumn<?, ?> nom_joueur;

    @FXML
    private TableColumn<?, ?> nbr_but;

    @FXML
    private TableColumn<?, ?> position;

    @FXML
    private TableColumn<?, ?> nationalite;

    @FXML
    private Label lblent;

    @FXML
    private Label select;

    @FXML
    private Label lblent1;

    @FXML
    private Label nbrbuteq;

    @FXML
    private Label lblent2;

    @FXML
    private Label phase;

    @FXML
    private Label lblent21;

    @FXML
    private Label etat;

    @FXML
    private TableView lsteq;
    @FXML
    private ImageView drapeau;

    @FXML
    void Afficherdetails(MouseEvent event) {
           
        Equipe e = (Equipe) lsteq.getSelectionModel().selectedItemProperty().getValue();
        if (e.getEtat()==0)
                {
                etat.setVisible(true);
                etat.setText("Eliminée");
                }
        else if (e.getEtat()==1)
                {
                etat.setVisible(true);
                
                etat.setTextFill(Color.web("#009900"));
                etat.setText("En competition");
                }
        lblent.setVisible(true);
        lblent1.setVisible(true);
        lblent2.setVisible(true);
        lblent21.setVisible(true);
        nbrbuteq.setVisible(true);
        phase.setVisible(true);
        select.setVisible(true);
        phase.setText(e.getPhase());
        nbrbuteq.setText(String.valueOf(me.afficherNombreButParEquipe(e.getIdEquipe())));
        select.setText(e.getSelecteur());
        System.out.println("equipe " + e);
        ObservableList<Joueur> jEq = FXCollections.observableArrayList(sj.chercherParEquipe(e.getPays()));
        lstjou.getItems().clear();
        lstjou.getItems().addAll(jEq);
        id_joueur.setCellValueFactory(new PropertyValueFactory<>("id_joueur"));
        nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
        nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbr_but"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lsteq.getItems().addAll(getEq());
        eq.setCellValueFactory(new PropertyValueFactory<>("pays"));

    }

}
