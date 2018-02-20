/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import Entities.Equipe;
import Entities.Joueur;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Services.serviceEquipe;
import Services.serviceJoueur;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Emel
 */
public class GestionJoueurController implements Initializable {

    serviceEquipe se = new serviceEquipe();
    serviceJoueur sj = new serviceJoueur();
    Equipe e = new Equipe();
    Joueur j = new Joueur();
    ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
    ObservableList<String> listepos = FXCollections.observableArrayList("Gardien", "Defenseur", "Attaquant", "Milieu");

    @FXML
    private AnchorPane anchro;

    @FXML
    private ChoiceBox eqchoice;

    @FXML
    private Button btSupp;

    @FXML
    private Button btModif;

    @FXML
    private Button btAjouter;

    @FXML
    private TableView lstjoueur;

    @FXML
    private TableColumn<?, ?> id_joueur;

    @FXML
    private TableColumn<?, ?> nom_joueur;

    @FXML
    private TableColumn<?, ?> pays;

    @FXML
    private TableColumn<?, ?> nbr_but;

    @FXML
    private TableColumn<?, ?> position;

    @FXML
    private JFXTextField txtnom;

    @FXML
    private JFXTextField txtsearch;

    @FXML
    private JFXButton btsearch;

    @FXML
    private JFXTextField txtbut;

    @FXML
    private ChoiceBox pochoice;

    @FXML
    private JFXRadioButton chkNat;

    @FXML
    private JFXTextField txtnat;

    @FXML
    void AjouterJ(ActionEvent event) {
        Equipe e = new Equipe();
        int id;
        //recuperer le nom de l Equipe
        String equipe = eqchoice.getSelectionModel().selectedItemProperty().getValue().toString();
        ///recuperer l objet Equipe
        e = se.AfficherEquipe(equipe);
        if ((txtbut.getText().length() != 0) && (txtnat.getText().length() != 0) && (txtnom.getText().length() != 0)) {

            Joueur j1 = new Joueur(txtnom.getText(),
                    txtnat.getText(),
                    Integer.parseInt(txtbut.getText()),
                    pochoice.getSelectionModel().selectedItemProperty().getValue().toString(),
                    e.getId_equipe(),
                    pays.getText());

            Joueur j;
            System.out.println("mon id" + j1.getId_joueur());
            int res = sj.ajouterJoueur(j1);
            if (res == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajout echoué !");
                alert.setHeaderText("Cette equipe a atteint le nombre maximal des joueurs !");
                alert.setContentText(" Veuillez verifier vos choix !");
                alert.showAndWait();
                //recharger la liste
                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(getJ());
            } else if (res == 2) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajout echoué !");
                alert.setHeaderText("Cette equipe a atteint le nombre maximal de gardien  !");
                alert.setContentText(" Veuillez verifier vos choix !");
                alert.showAndWait();
                //recharger la liste
                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(getJ());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout effectué !");
                alert.setHeaderText("Joueur ajouté avec succès !");
                alert.showAndWait();
                //recharger la liste
                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(getJ());

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Tous les champs sont obligatoirs  !");
            alert.setContentText(" Veuillez verifier vos choix !");
            alert.showAndWait();
        }
    }
    @FXML
    void accueil(MouseEvent event) throws IOException {
         Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/FXMLGestion_Match.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        sceneAffichage.getStylesheets().add(getClass().getResource("../Asset/fxml.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
        stage.show();


    }

    @FXML
    void chargerDonn(MouseEvent event) {
        Joueur j = (Joueur) lstjoueur.getSelectionModel().selectedItemProperty().getValue();
        System.out.println("jouer +" + j);
        txtnom.setText(j.getNom_joueur());
        int nb = j.getNbr_but();
        txtbut.setText(String.valueOf(nb));

        // txtpos.setText(j.getPosition());
        txtnat.setText(j.getNationalite());

        eqchoice.setValue(j.getPays());
        pochoice.setValue(j.getPosition());

    }

    @FXML
    void SupprimerJ(ActionEvent event) {
        if (lstjoueur.getSelectionModel().selectedItemProperty().getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suppression echouée !");
            alert.setHeaderText("");
            alert.setContentText(" Veuillez Selectionner un joueur a supprimer !");
            alert.showAndWait();
            lstjoueur.getItems().clear();
            lstjoueur.getItems().addAll(getJ());

        } else {
            Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
            alerte.setTitle("Confirmation ");
            alerte.setHeaderText("Etes vous certain de vouloir supprimer ce joueur ?");
            alerte.setContentText("OK : Pour supprimer , Annuler : Si non");
            Optional<ButtonType> result = alerte.showAndWait();
            if (result.get() == ButtonType.OK) {

                Joueur j = (Joueur) lstjoueur.getSelectionModel().selectedItemProperty().getValue();
                if (sj.supprimerJoueur(j.getId_joueur()) == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Suppression effectuée !");
                    alert.setHeaderText("Joueur supprimé avec succés !");

                    alert.showAndWait();
                    lstjoueur.getItems().clear();
                    lstjoueur.getItems().addAll(getJ());

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Suppression echouée !");
                    alert.setHeaderText("");
                    alert.setContentText(" Veuillez verifier vos choix!");
                    alert.showAndWait();
                    lstjoueur.getItems().clear();
                    lstjoueur.getItems().addAll(getJ());

                }
            } else {
                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(getJ());
            }
        }
    }

    @FXML
    void modifEJ(ActionEvent event) {
        if (lstjoueur.getSelectionModel().selectedItemProperty().getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modification echouée !");
            alert.setHeaderText("");
            alert.setContentText(" Veuillez Selectionner un joueur a modifier !");
            alert.showAndWait();
            lstjoueur.getItems().clear();
            lstjoueur.getItems().addAll(getJ());

        } else {
            Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
            alerte.setTitle("Confirmation ");
            alerte.setHeaderText("Etes vous certain de vouloir modifier ce joueur ?");
            alerte.setContentText("OK : Pour Modifier , Annuler : Si non");
            Optional<ButtonType> result = alerte.showAndWait();
            if (result.get() == ButtonType.OK) {

                Joueur j = (Joueur) lstjoueur.getSelectionModel().selectedItemProperty().getValue();
                String eq = eqchoice.getSelectionModel().selectedItemProperty().getValue().toString();
                Equipe e = se.AfficherEquipe(eq);
                j.setNom_joueur(txtnom.getText());
                j.setNationalite(txtnat.getText());
                j.setId_equipe(e.getId_equipe());
                j.setNbr_but(Integer.parseInt(txtbut.getText()));
                j.setPosition(pochoice.getSelectionModel().selectedItemProperty().getValue().toString());
                System.out.println("id eq +" + eq);
                if (sj.modifierJoueur(j) == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Modification effectuée !");
                    alert.setHeaderText("Joueur modifié avec succès !");
                    alert.showAndWait();
                    //recharger la liste
                    lstjoueur.getItems().clear();
                    lstjoueur.getItems().addAll(getJ());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Modification echouée !");
                    alert.setHeaderText("");
                    alert.setContentText(" Veuillez verifier vos choix!");
                    alert.showAndWait();
                    lstjoueur.getItems().clear();
                    lstjoueur.getItems().addAll(getJ());

                }
            } else {
                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(getJ());
            }
        }
    }

    @FXML
    void search(ActionEvent event) {

        if ((chkEq.isSelected()) && (chkPos.isSelected()) && (chkNat.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("");
            alert.setContentText(" Veuillez choisir un seul filtre !");
            alert.showAndWait();
        } else if ((chkEq.isSelected() == false) && (chkPos.isSelected() == false) && (chkNat.isSelected() == false) && (txtsearch.getText().length() == 0)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("");
            alert.setContentText(" Veuillez choisir un filtre ou taper un nom de joueur  !");
            alert.showAndWait();
        } else if ((chkEq.isSelected() == false) && (chkPos.isSelected() == false) && (chkNat.isSelected() == false) && (txtsearch.getText().length() != 0)) {

            ObservableList<Joueur> jEq = FXCollections.observableArrayList(sj.chercherParNom(txtsearch.getText()));
            lstjoueur.getItems().clear();
            lstjoueur.getItems().addAll(jEq);
            id_joueur.setCellValueFactory(new PropertyValueFactory<>("id_joueur"));
            nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
            pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
            nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbr_but"));
            position.setCellValueFactory(new PropertyValueFactory<>("position"));
            nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        } else {
            if ((chkEq.isSelected() == true)) {
                ObservableList<Joueur> jEq = FXCollections.observableArrayList(sj.chercherParEquipe(txtsearch.getText()));

                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(jEq);
                id_joueur.setCellValueFactory(new PropertyValueFactory<>("id_joueur"));
                nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
                pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
                nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbr_but"));
                position.setCellValueFactory(new PropertyValueFactory<>("position"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));

            } else if ((chkPos.isSelected() == true)) {
                ObservableList<Joueur> jEq = FXCollections.observableArrayList(sj.chercherParPosition(txtsearch.getText()));

                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(jEq);
                id_joueur.setCellValueFactory(new PropertyValueFactory<>("id_joueur"));
                nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
                pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
                nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbr_but"));
                position.setCellValueFactory(new PropertyValueFactory<>("position"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
            } else if ((chkNat.isSelected() == true)) {
                ObservableList<Joueur> jEq = FXCollections.observableArrayList(sj.chercherParEquipe(txtsearch.getText()));

                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(jEq);
                id_joueur.setCellValueFactory(new PropertyValueFactory<>("id_joueur"));
                nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
                pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
                nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbr_but"));
                position.setCellValueFactory(new PropertyValueFactory<>("position"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
            }
        }

    }

    public ObservableList<Joueur> getJ() {
        ObservableList<Joueur> j = FXCollections.observableArrayList(sj.chercherParEquipe());
        return j;
    }

    @FXML
    private TableColumn<?, ?> nationalite;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eqchoice.setValue(listeequipe.get(0));
        eqchoice.setItems(listeequipe);
        pochoice.setValue(listepos.get(0));
        pochoice.setItems(listepos);
        // lstjoueur.getItems().addAll(getJ());
        lstjoueur.getItems().addAll(getJ());
        id_joueur.setCellValueFactory(new PropertyValueFactory<>("id_joueur"));
        nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbr_but"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));

    }

    @FXML
    private JFXRadioButton chkEq;

    @FXML
    private JFXRadioButton chkPos;
    @FXML
    private ImageView RECHIC;

    @FXML
    private ImageView FILTERIC;

    @FXML
    void verifnum(KeyEvent event) {

        txtbut.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtbut.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    void verifnom(KeyEvent event) {
        /*
        txtbut.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                 ObservableList<joueur> jEq = FXCollections.observableArrayList(sj.chercherParNationalite(txtsearch.getText()));

                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(jEq);
                id_joueur.setCellValueFactory(new PropertyValueFactory<>("id_joueur"));
                nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
                pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
                nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbr_but"));
                position.setCellValueFactory(new PropertyValueFactory<>("position"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
            }
        });*/
    }

}
