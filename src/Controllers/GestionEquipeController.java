/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
//import com.itextpdf.text;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import Entities.Equipe;
import Entities.Imagedrapeau;
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
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Emel
 */
public class GestionEquipeController implements Initializable {

    Services.serviceEquipe se = new serviceEquipe();
    Equipe e = new Equipe();
    ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
    ObservableList<String> listegroupe = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H");
    ObservableList<String> listeEtat = FXCollections.observableArrayList("IN", "OUT");
    ObservableList<String> listephase = FXCollections.observableArrayList("groupe", "1/8", "1/4", "1/2", "finale");
    ObservableList<Equipe> listeEquipeInit = FXCollections.observableArrayList(se.selectEquipes());
    ObservableList<String> listegroupe1 = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H");
    ObservableList<String> listeEtat1 = FXCollections.observableArrayList("IN", "OUT");
    ObservableList<String> listephase1 = FXCollections.observableArrayList("groupe", "1/8", "1/4", "1/2", "finale");

    public ObservableList<Equipe> getEq() {
        ObservableList<Equipe> p = FXCollections.observableArrayList(se.selectEquipes());
        return p;
    }

    public static Imagedrapeau img;
    public static boolean verifimg = false;
    @FXML
    private JFXButton btfiltre;

    @FXML
    private AnchorPane anchro;
    @FXML
    private TableColumn<?, ?> selecteur;

    @FXML
    private Label label;

    // @FXML
    // private ChoiceBox eqchoice;
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
        // eqchoice.setValue(listeequipe.get(1));
        //      eqchoice.setItems(listeequipe);
        cbgroupe.setValue(listegroupe.get(0));
        cbgroupe.setItems(listegroupe);
        cbetat.setValue(listeEtat.get(0));
        cbetat.setItems(listeEtat);
        cbpahse.setValue(listephase.get(0));
        cbpahse.setItems(listephase);
        listegroupe1.add(0, "");
        cbgroupe1.setValue(listegroupe1.get(0));
        cbgroupe1.setItems(listegroupe1);
        listeEtat1.add(0, "");
        cbetat1.setValue(listeEtat1.get(0));
        cbetat1.setItems(listeEtat1);
        listephase1.add(0, "");
        cbpahse1.setValue(listephase1.get(0));
        cbpahse1.setItems(listephase1);
        lstequipe.getItems().addAll(getEq());
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
        phase.setCellValueFactory(new PropertyValueFactory<>("Phase"));
        etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        selecteur.setCellValueFactory(new PropertyValueFactory<>("Selecteur"));

    }

    @FXML
    void chargerEquipe(MouseEvent event) {
        Equipe eq = se.AfficherEquipe(lstequipe.getSelectionModel().selectedItemProperty().getValue());
        txtpays.setText(eq.getPays());
        txtselect.setText(eq.getSelecteur());
        txtPoint.setText(String.valueOf(eq.getPoint()));
        btModif.setDisable(false);
        btSupp.setDisable(false);
        btAjouter.setDisable(true);
        cbgroupe.setValue(eq.getGroupe());
        if (eq.getEtat() == 0) {
            cbetat.setValue("OUT");
        } else {
            cbetat.setValue("IN");
        }
        cbpahse.setValue(eq.getPhase());
          File file = new File(e.getImg().getLink());
        Image image = new Image(e.getImg().getLink(),true);
         imgdr.setImage(image);
       // File file = new File(eq.getImg().getLink().replace("file:/C", "C"));
      //  Image image = new Image(file.toURI().toString());
      //  imgdr.setImage(image);
        btAjouter.setDisable(true);
    }

    @FXML
    private ImageView imgdr;
    @FXML
    private Label lblr;
    @FXML
    private JFXButton btImpIm;
    @FXML
    private BorderPane Paneimage;

    @FXML
    private Imagedrapeau importerImage() {
        FileChooser fc = new FileChooser();
        String link = null;
        //   FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(
                //extFilterJPG, 
                extFilterPNG);

        File file = fc.showOpenDialog(null);
        Image image = new Image(file.toURI().toString());

        imgdr.setImage(image);
        link = file.toURI().toString();

        img = new Imagedrapeau(link, null);
        verifimg = true;
        return img;
    }

    @FXML
    private JFXTextField txtPoint;

    @FXML
    void SupprimerEq(ActionEvent event) {
        boolean choix = true;
        if ("".equals(lstequipe.getSelectionModel().selectedItemProperty().getValue().toString())) {
            choix = false;

        }
        System.out.println("choix ------" + choix);
        Alert alerte = new Alert(AlertType.CONFIRMATION);
        alerte.setTitle("Confirmation ");
        alerte.setHeaderText("Etes vous certain de vouloir supprimer cette equipe ?");
        alerte.setContentText("OK : Pour supprimer , Annuler : Si non");
        Optional<ButtonType> result = alerte.showAndWait();
        if (result.get() == ButtonType.OK) {
            Equipe eq = se.AfficherEquipe(lstequipe.getSelectionModel().selectedItemProperty().getValue());
            boolean supprimer = (se.supprimerEquipe(eq));
            if (supprimer == true) {
                System.out.println("choix --" + eq.getPays());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Suppression effectuée !");
                alert.setHeaderText("Equipe supprimée avec succès !");
                //   alert.setContentText("GOOOD");
                btSupp.setDisable(true);
                btModif.setDisable(true);
                btAjouter.setDisable(false);
                alert.showAndWait();
                //recharger la liste
                ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
                lstequipe.getItems().clear();
                lstequipe.getItems().addAll(getEq());
                //  eqchoice.setItems(listeequipe);
                //   eqchoice.setValue(listeequipe.get(0));
                viderForm();
            } else if (supprimer == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Suppression echouée !");
                alert.setHeaderText("");
                //   alert.setContentText(" Veuillez verifier vos choix!");
                alert.showAndWait();
            }
        } else {
            ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
//            eqchoice.setItems(listeequipe);
//            eqchoice.setValue(listeequipe.get(0));

        }

    }

    @FXML
    void AjouterEq(ActionEvent event) {
        String pays = txtpays.getText();
        String groupe = cbgroupe.getSelectionModel().selectedItemProperty().getValue().toString();
        String selec = txtselect.getText();
        Services.serviceEquipe se = new Services.serviceEquipe();
        Equipe e = new Equipe();
        if (!txtpays.getText().isEmpty() && !txtPoint.getText().isEmpty() && !txtselect.getText().isEmpty()) {
            obpays.setVisible(false);
            obphase.setVisible(false);
            obsel.setVisible(false);
            obsetat.setVisible(false);
            obgroupe.setVisible(false);
            obpoint.setVisible(false);
            if (verifimg == true) {
                e.setPays(pays);
                e.setGroupe(groupe);
                e.setSelecteur(selec);
                e.setEtat(0);
                e.setPhase("groupe");
                e.setPoint(0);
                e.setImg(img);
                System.out.println("image ajout -------------------" + img);
                boolean ajout = se.ajouterEquipe(e);
                //  boolean test = verifform();

                if (ajout == false) {
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
                    System.err.println("image" + img);
                    System.err.println("equipe ac image" + e);

                    viderForm();

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur !");
                alert.setHeaderText("Tous les champs sont obligatoirs !");
                alert.setContentText(" Veuillez importet un drapeau !");
                alert.showAndWait();

            }
        } else {
            verifform();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Tous les champs sont obligatoirs !");
            alert.setContentText(" Veuillez verifier vos choix !");
            alert.showAndWait();
        }

    }

    @FXML
    void modifEq(ActionEvent event) {

        int etat = 5;
        if (!txtpays.getText().isEmpty() && !txtPoint.getText().isEmpty() && !txtselect.getText().isEmpty()) {
            obpays.setVisible(false);
            obphase.setVisible(false);
            obsel.setVisible(false);
            obsetat.setVisible(false);
            obgroupe.setVisible(false);
            obpoint.setVisible(false);
            Image i = imgdr.getImage();
            boolean test = (i == null || i.isError());
            if (verifimg == true || test == false) {
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
                    Equipe eq = se.AfficherEquipe(lstequipe.getSelectionModel().selectedItemProperty().getValue().getPays().toString());
                    int id = eq.getIdequipe();
                    Imagedrapeau imagequipe;
                    if (verifimg == false) {
                        imagequipe = eq.getImg();
                    } else {
                        imagequipe = img;
                    }
                    Equipe e = new Equipe(txtpays.getText(),
                            etat,
                            cbpahse.getSelectionModel().selectedItemProperty().getValue().toString(),
                            cbgroupe.getSelectionModel().selectedItemProperty().getValue().toString(),
                            Integer.parseInt(txtPoint.getText()),
                            txtselect.getText(),
                            imagequipe
                    );

//            System.out.println("image modif" + img.getLink());
                    System.out.println("id " + id);
                    System.out.println("euiope " + e);

                    // Equipe eq = new Equipe(eqchoice.getSelectionModel().selectedItemProperty().getValue().toString(), "");
                    boolean modif = se.modifierEquipe(e, id);
                    if (modif == true) {

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Modification effectuée !");
                        alert.setHeaderText("Equipe modifiée avec succès !");
                        alert.showAndWait();
                        viderForm();
                        btSupp.setDisable(true);
                        btModif.setDisable(true);
                        btAjouter.setDisable(false);
                        //recharger la liste
                        //   ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
                        lstequipe.getItems().clear();
                        lstequipe.getItems().addAll(getEq());
                    } else if (modif == false) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Modification echouée !");
                        alert.setHeaderText("Veuillez verifier les données sasies !");
                        //   alert.setContentText(" faux");
                        alert.showAndWait();
                    }
                } else {
                    ObservableList<String> listeequipe = FXCollections.observableArrayList(se.selectPays());
                    lstequipe.getItems().addAll(getEq());

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur !");
                alert.setHeaderText("Tous les champs sont obligatoirs !");
                alert.setContentText(" Veuillez importer un drapeau !");
                alert.showAndWait();

            }
        } else {
            verifform();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Tous les champs sont obligatoirs !");
            alert.setContentText(" Veuillez verifier vos choix !");
            alert.showAndWait();
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
            lstequipe.getItems().clear();
            lstequipe.getItems().addAll(getRes());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errer !");
            alert.setHeaderText("Veuillez Saisir un nom d'equipe !");
            alert.showAndWait();
        }
    }

    public boolean verif() {
        return (!"".equals(txtpays.getText()));
    }

    @FXML
    void rechraff(MouseDragEvent event) {

        chkPos.setVisible(true);
        btAjouter.setDisable(true);
    }

    @FXML
    void filtrerliste(ActionEvent event) {

        String etatF = cbetat1.getSelectionModel().selectedItemProperty().getValue().toString();
        String phaseF = cbpahse1.getSelectionModel().selectedItemProperty().getValue().toString();
        String groupeF = cbgroupe1.getSelectionModel().selectedItemProperty().getValue().toString();
        List<Equipe> list = se.selectEquipes();
        List<Equipe> listf = new ArrayList<>();
        System.out.println("etat  ==" + etatF);
        System.out.println("phase  ==" + phaseF);
        System.out.println("groupe  ==" + groupeF);
        System.out.println("liste initiale " + list);
        boolean chnged = false;

        if (!etatF.equals("")) {
            chnged = true;
            list.forEach((eq) -> {
                int et = eq.getEtat();
                if (et == 0) {
                    String etatn = "OUT";
                    if ((etatn.equals(etatF))) {
                        listf.add(eq);
                    }
                } else if (et == 1) {
                    String etatn = "IN";

                    if ((etatn.equals(etatF))) {
                        listf.add(eq);

                    }
                }
            });
            list.clear();
            listf.forEach((e) -> {
                list.add(e);
            });

            listf.clear();
        }
        if (!phaseF.equals("")) {
            chnged = true;
            list.stream().filter((e) -> (e.getPhase().equals(phaseF))).map((e) -> {
                return e;
            }).forEachOrdered((e) -> {
                listf.add(e);
            });
            list.clear();
            listf.forEach((e) -> {
                list.add(e);
            });

            listf.clear();
        }
        if (!groupeF.equals("")) {
            chnged = true;

            list.stream().filter((e) -> (e.getGroupe().equals(groupeF))).map((e) -> {

                return e;
            }).forEachOrdered((e) -> {
                listf.add(e);
            });
            list.clear();
            for (Equipe e : listf) {
                list.add(e);
            }

            listf.clear();
        }

        if (chnged == true) {

            System.out.println("change true list.size() element" + list);
            System.out.println("list.size()" + list.size());
            chnged = true;
            ObservableList<Equipe> lstf = FXCollections.observableArrayList(list);
            lstequipe.getItems().clear();
            lstequipe.getItems().addAll(lstf);
            System.out.println("listffff.size() element" + lstf);
            System.out.println("listf.size()" + lstf.size());
            pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
            groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
            phase.setCellValueFactory(new PropertyValueFactory<>("Phase"));
            etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
            selecteur.setCellValueFactory(new PropertyValueFactory<>("Selecteur"));
        }
        if (chnged == false || list.size() == 0) {
            lstequipe.getItems().clear();
            lstequipe.getItems().addAll(getEq());
            System.out.println("changd 0");
            //   System.out.println("list.size()" +list.size() );
            pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
            groupe.setCellValueFactory(new PropertyValueFactory<>("Groupe"));
            phase.setCellValueFactory(new PropertyValueFactory<>("Phase"));
            etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
            selecteur.setCellValueFactory(new PropertyValueFactory<>("Selecteur"));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aucun resultat !");
            alert.setHeaderText("Veuillez reajuster les filtres !");
            alert.showAndWait();
        }

    }

    @FXML
    void verifnum(KeyEvent event) {

        txtPoint.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPoint.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        obpoint.setVisible(false);
    }

    @FXML
    void verifselect(KeyEvent event) {
        txtselect.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtselect.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        obsel.setVisible(false);
    }

    @FXML
    void veriftxt(KeyEvent event) {
        txtsearch.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtsearch.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

    }

    @FXML
    void verifpays(KeyEvent event) {
        txtpays.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\sa-zA-Z*")) {
                    txtpays.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });
        obpays.setVisible(false);

    }
    @FXML
    private Label obpays;

    @FXML
    private Label obsel;

    @FXML
    private Label obphase;

    @FXML
    private Label obsetat;

    @FXML
    private Label obgroupe;

    @FXML
    private Label obpoint;

    public void verifform() {
        boolean ok = true;
        if (txtpays.getText().equals("")) {
            obpays.setVisible(true);
            ok = false;

        }
        if (txtselect.getText().equals("")) {
            obsel.setVisible(true);
            ok = false;

        }
        if (txtPoint.getText().equals("")) {
            ok = false;

            obpoint.setVisible(true);
        }

    }

    public void viderForm() {
        txtPoint.setText("");
        txtselect.setText("");
        txtpays.setText("");
        cbgroupe.setValue(listegroupe.get(0));
        cbetat.setValue(listeEtat.get(0));
        cbpahse.setValue(listephase.get(0));
        imgdr.setImage(null);
//     Image image2 = new Image(":/Ressource/flag.png");
        // imgdr.setImage(null);
        obpays.setVisible(false);
        obphase.setVisible(false);
        obsel.setVisible(false);
        obsetat.setVisible(false);
        obgroupe.setVisible(false);
        obpoint.setVisible(false);

    }
}
