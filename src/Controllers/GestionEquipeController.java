/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
//import com.itextpdf.text;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import Entities.Equipe;
import Entities.Joueur;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Translate;
import Services.serviceEquipe;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Emel
 */
public class GestionEquipeController implements Initializable {

    Services.serviceEquipe se = new serviceEquipe();
    Equipe e = new Equipe();
    ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
    ObservableList<String> listegroupe = FXCollections.observableArrayList("","A", "B", "C", "D", "E", "F", "G", "H");
    ObservableList<String> listeEtat = FXCollections.observableArrayList("","IN", "OUT");
    ObservableList<String> listephase = FXCollections.observableArrayList("","groupe", "1/8", "1/4", "1/2", "finale");
    ObservableList<Equipe> listeEquipeInit = FXCollections.observableArrayList(se.selectEquipes());

    public ObservableList<Equipe> getEq() {
        ObservableList<Equipe> p = FXCollections.observableArrayList(se.selectEquipes());
        return p;
    }

    @FXML
    private JFXButton btfiltre;

    @FXML
    private AnchorPane anchro;
    @FXML
    private TableColumn<?, ?> selecteur;

    @FXML
    private Label label;

    @FXML
    private ChoiceBox eqchoice;

    @FXML
    private Button btSupp;

    @FXML
    private Button lstBt;

    @FXML
    private ChoiceBox cbgroupe;

    @FXML
    private ChoiceBox cbetat;

    @FXML
    private ChoiceBox cbpahse;

    @FXML
    private Button btModif;

    @FXML
    private Button btAjouter;

    @FXML
    private Button btChercher;

    @FXML
    private JFXTextField txtsearch;

    @FXML
    private JFXButton btsearch;

    @FXML
    private JFXTextField txtpays;

    @FXML
    private TableView<Equipe> lstequipe;

    @FXML
    private TableColumn<?, ?> pays;

    @FXML
    private TableColumn<?, ?> groupe;

    @FXML
    private TableColumn<?, ?> phase;

    @FXML
    private TableColumn<?, ?> etat;

    @FXML
    private ImageView RECHIC;

    @FXML
    private ImageView FILTERIC;

    @FXML
    private JFXRadioButton chkPos;

    @FXML
    private JFXRadioButton chkEq;

    @FXML
    private ChoiceBox cbgroupe1;

    @FXML
    private ChoiceBox cbetat1;

    @FXML
    private ChoiceBox cbpahse1;

    @FXML
    private JFXRadioButton chkNat;
    @FXML
    private JFXTextField txtselect;

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        eqchoice.setValue(listeequipe.get(0));
        eqchoice.setItems(listeequipe);
        cbgroupe.setValue(listegroupe.get(0));
        cbgroupe.setItems(listegroupe);
        cbetat.setValue(listeEtat.get(0));
        cbetat.setItems(listeEtat);
        cbpahse.setValue(listephase.get(0));
        cbpahse.setItems(listephase);
        // cbgroupe1.setValue(listegroupe.get(0));
        cbgroupe1.setItems(listegroupe);
        //  cbetat1.setValue(listeEtat.get(0));
        cbetat1.setItems(listeEtat);
        // cbpahse1.setValue(listephase.get(0));
        cbpahse1.setItems(listephase);
        lstequipe.getItems().addAll(getEq());
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
        phase.setCellValueFactory(new PropertyValueFactory<>("Phase"));
        etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        selecteur.setCellValueFactory(new PropertyValueFactory<>("Selecteur"));

    }

    @FXML
    void chargerEquipe(MouseEvent event) {
        Equipe e = new Equipe(eqchoice.getSelectionModel().selectedItemProperty().getValue().toString(), "", "");
        Equipe eq = se.AfficherEquipe(e);
        txtpays.setText(eq.getPays().toString());
        txtselect.setText(eq.getPays().toString());

        cbgroupe.setValue(eq.getGroupe());
        if (eq.getEtat() == 0) {
            cbetat.setValue("OUT");
        } else {
            cbetat.setValue("IN");
        }
        cbpahse.setValue(eq.getPhase());
        //      System.out.println("select      :"+e.getSelecteur());
        //    txtselect.setText(eq.getSelecteur().toString());
    }

    @FXML
    void SupprimerEq(ActionEvent event) {
        boolean choix = true;
        if ("".equals(eqchoice.getSelectionModel().selectedItemProperty().getValue().toString())) {
            choix = false;

        }
        System.out.println("choix ------" + choix);
        Alert alerte = new Alert(AlertType.CONFIRMATION);
        alerte.setTitle("Confirmation ");
        alerte.setHeaderText("Etes vous certain de vouloir supprimer cette equipe ?");
        alerte.setContentText("OK : Pour supprimer , Annuler : Si non");
        Optional<ButtonType> result = alerte.showAndWait();
        if (result.get() == ButtonType.OK) {
            Equipe eq = new Equipe(eqchoice.getSelectionModel().selectedItemProperty().getValue().toString(), "", "");

            if ((se.supprimerEquipe(eq.getPays())) == true) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Suppression effectuée !");
                alert.setHeaderText("Equipe supprimée avec succès !");
                //   alert.setContentText("GOOOD");
                alert.showAndWait();
                //recharger la liste
                ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
                lstequipe.getItems().clear();
                lstequipe.getItems().addAll(getEq());
                eqchoice.setItems(listeequipe);
                eqchoice.setValue(listeequipe.get(0));
            } else if ((se.supprimerEquipe(eq.getPays())) == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Suppression echouée !");
                alert.setHeaderText("");
                //   alert.setContentText(" Veuillez verifier vos choix!");
                alert.showAndWait();
            }
        } else {
            ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
            eqchoice.setItems(listeequipe);
            eqchoice.setValue(listeequipe.get(0));

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
    void AjouterEq(ActionEvent event) {
        String pays = txtpays.getText();
        String groupe = cbgroupe.getSelectionModel().selectedItemProperty().getValue().toString();
        String selec = txtselect.getText();
        Services.serviceEquipe se = new Services.serviceEquipe();
        Equipe e = new Equipe();
        e.setPays(pays);
        e.setGroupe(groupe);
        e.setSelecteur(selec);
        Document doc = new Document();
        try {
            //choisir le nom 
            //alert nekhou menha essm w nhot fell fos
            PdfWriter.getInstance(doc, new FileOutputStream("rapport.pdf"));
            doc.open();
            doc.add(new Paragraph(txtpays.getText() + "\n" + txtselect.getText() + e));
            doc.close();
            // lstequipe.getItems().addAll(getEq());*/
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionEquipeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GestionEquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // se.ajouterEquipe(e);
        if ((txtpays.getText().length() != 0) && (txtselect.getText().length() != 0)) {
            if (se.ajouterEquipe(e) == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajout echoué !");
                alert.setHeaderText("Pays deja existant et/ou groupe Saturé !");
                alert.setContentText(" Veuillez verifier vos choix !");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajout effectué !");
                alert.setHeaderText("Equipe ajoutée avec succès!");
                //  alert.setContentText("GOOOD");
                alert.showAndWait();
                ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
                lstequipe.getItems().clear();
                lstequipe.getItems().addAll(getEq());
                eqchoice.setItems(listeequipe);
                eqchoice.setValue(listeequipe.get(0));

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Tous les champs sont obligatoirs !");
            alert.setContentText(" Veuillez verifier vos choix !");
            alert.showAndWait();
        }
    }

    @FXML
    void modifEq(ActionEvent event) {
        /*     Equipe eq = se.AfficherEquipe(eqchoice.getSelectionModel().selectedItemProperty().getValue().toString());
        int id=eq.getId_equipe();
        int etat = 2;
        if ("OUT".equals(cbgroupe.getSelectionModel().selectedItemProperty().getValue().toString()) ) {
            etat = 0;
        } else if ("IN".equals(cbgroupe.getSelectionModel().selectedItemProperty().getValue().toString())) {
            etat = 1;
        }
        Equipe e = new Equipe(txtpays.getText(),
                etat,
                cbpahse.getSelectionModel().selectedItemProperty().getValue().toString(),
                cbgroupe.getSelectionModel().selectedItemProperty().getValue().toString());
        System.out.println("Equipe a modifier ");
        se.modifierEquipe(e ,id);
        
             ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
                eqchoice.setItems(listeequipe);*/
        int etat = 5;
        Alert alerte = new Alert(AlertType.CONFIRMATION);
        alerte.setTitle("Confirmation ");
        alerte.setHeaderText("Etes vous certain de vouloir modifier cette equipe ?");
        alerte.setContentText("OK : Pour modifier , Annuler : Si non");
        Optional<ButtonType> result = alerte.showAndWait();
        if (result.get() == ButtonType.OK) {
            if ("OUT".equals(cbetat.getSelectionModel().selectedItemProperty().getValue().toString())) {
                etat = 0;
                System.out.println("out");
            } else if ("IN".equals(cbetat.getSelectionModel().selectedItemProperty().getValue().toString())) {
                etat = 1;
                System.out.println("IN");
            }
            Equipe eq = se.AfficherEquipe(eqchoice.getSelectionModel().selectedItemProperty().getValue().toString());
            int id = eq.getIdEquipe();
            Equipe e = new Equipe(txtpays.getText(),
                    etat,
                    cbpahse.getSelectionModel().selectedItemProperty().getValue().toString(),
                    cbgroupe.getSelectionModel().selectedItemProperty().getValue().toString(),
                    txtselect.getText().toString());

            // Equipe eq = new Equipe(eqchoice.getSelectionModel().selectedItemProperty().getValue().toString(), "");
            if (se.modifierEquipe(e, id) == true) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Modification effectuée !");
                alert.setHeaderText("Equipe modifiée avec succès !");
                // alert.setContentText("GOOOD");
                alert.showAndWait();
                //recharger la liste
                ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
                lstequipe.getItems().clear();
                lstequipe.getItems().addAll(getEq());
                eqchoice.setItems(listeequipe);
                eqchoice.setValue(listeequipe.get(0));
            } else if (se.modifierEquipe(e, id) == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modification echouée !");
                alert.setHeaderText("Veuillez verifier les données sasies !");
                //   alert.setContentText(" faux");
                alert.showAndWait();
            }
        } else {
            ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
            eqchoice.setItems(listeequipe);
            eqchoice.setValue(listeequipe.get(0));

        }

    }

    @FXML
    void AfficherEquipe(ActionEvent event) {
        System.out.println("click");
    }

    public ObservableList<Equipe> getRes() {
        ObservableList<Equipe> p = FXCollections.observableArrayList(se.chercherParPays(txtsearch.getText()));
        return p;
    }

    @FXML
    void search(ActionEvent event) {
        // System.out.println(txtsearch.getText().toString());
        //   lstequipe.getItems().addAll();
        if (txtsearch.getText().length() != 0) {
            pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
            groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
            phase.setCellValueFactory(new PropertyValueFactory<>("Phase"));
            etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
            selecteur.setCellValueFactory(new PropertyValueFactory<>("selecteur"));

            // System.out.println("aaaaaaaaaaaaaa");
            //    System.out.println(getRes());
            lstequipe.getItems().clear();
            lstequipe.getItems().addAll(getRes());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errer !");
            alert.setHeaderText("Veuillez Saisir un nom d'equipe !");
            //  alert.setContentText(" faux");
            alert.showAndWait();
        }
    }

    public boolean verif() {
        return (!"".equals(txtpays.getText()));
    }

    @FXML
    void rechraff(MouseDragEvent event) {

        chkPos.setVisible(true);
        btAjouter.setVisible(false);
    }

    @FXML
    void filtrerliste(ActionEvent event) {

        String etat = cbetat1.getSelectionModel().selectedItemProperty().getValue().toString();
        String phase = cbpahse1.getSelectionModel().selectedItemProperty().getValue().toString();
        String groupe = cbgroupe1.getSelectionModel().selectedItemProperty().getValue().toString();
        ObservableList<Equipe> list = FXCollections.observableArrayList(se.selectEquipes());
        List<Equipe> newList = new ArrayList<>();
        for (Equipe e : list) {
            int et = e.getEtat();
            if (et == 0) {
                          
                String etatn = "OUT";
                //filtre sur 3 parties 
                if ((etatn.equals(etat)) && (e.getGroupe().equals(groupe))&&(e.getPhase().equals(phase))) {
                    newList.add(e);
                }
            }
            else if (et == 1) {
                String etatn = "IN";
                if ((etatn.equals(etat)) && (e.getGroupe().equals(groupe))&&(e.getPhase().equals(phase))) {
                    newList.add(e);
                }
            }

        }
        lstequipe.getItems().clear();
        lstequipe.getItems().addAll(newList);

    }
      @FXML
    void chercherparEtat(MouseEvent event) {

        String e = cbetat1.getSelectionModel().selectedItemProperty().getValue().toString();
        if (e.equals("IN")) {
            ObservableList<Equipe> eq = FXCollections.observableArrayList(se.EquipeEnCompetition());

            lstequipe.getItems().clear();
            lstequipe.getItems().addAll(eq);
            pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
            groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
            phase.setCellValueFactory(new PropertyValueFactory<>("Phase"));
            etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
            selecteur.setCellValueFactory(new PropertyValueFactory<>("selecteur"));

        } else {
            ObservableList<Equipe> eq = FXCollections.observableArrayList(se.EquipeEliminee());

            lstequipe.getItems().clear();
            lstequipe.getItems().addAll(eq);
            pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
            groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
            phase.setCellValueFactory(new PropertyValueFactory<>("Phase"));
            etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
            selecteur.setCellValueFactory(new PropertyValueFactory<>("selecteur"));

        }
    }

    @FXML
    void chercherparPhase(MouseEvent event) {
        ObservableList<Equipe> eq = FXCollections.observableArrayList(se.chercherParPhase(cbpahse1.getSelectionModel().selectedItemProperty().getValue().toString()));

        lstequipe.getItems().clear();
        lstequipe.getItems().addAll(eq);
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
        phase.setCellValueFactory(new PropertyValueFactory<>("Phase"));
        etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        selecteur.setCellValueFactory(new PropertyValueFactory<>("selecteur"));

    }

    @FXML
    void chercherpargroupe(MouseEvent event) {

        ObservableList<Equipe> eq = FXCollections.observableArrayList(se.chercherParGroupe(cbgroupe1.getSelectionModel().selectedItemProperty().getValue().toString()));

        lstequipe.getItems().clear();
        lstequipe.getItems().addAll(eq);
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
        phase.setCellValueFactory(new PropertyValueFactory<>("Phase"));
        etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        selecteur.setCellValueFactory(new PropertyValueFactory<>("selecteur"));

    }

}
