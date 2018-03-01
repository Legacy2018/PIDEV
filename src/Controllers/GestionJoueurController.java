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
import Services.metierequipe;
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
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

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
    ObservableList<String> listeequipe1 = FXCollections.observableArrayList(se.selectPays());
    ObservableList<String> listepos1 = FXCollections.observableArrayList("", "Gardien", "Defenseur", "Attaquant", "Milieu");
    metierequipe me = new metierequipe();
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
    private JFXTextField txtcrtjaune;

    @FXML
    private JFXTextField txtcrtrouge;

    @FXML
    private JFXTextField txtmaillot;
    @FXML
    private TableColumn<?, ?> num;
    @FXML
    private TableColumn<?, ?> nom_joueur;

    @FXML
    private TableColumn<Joueur, String> pays;

    @FXML
    private TableColumn<?, ?> nbr_but;

    @FXML
    private TableColumn<?, ?> position;

    @FXML
    private TableColumn<?, ?> crtRouge;

    @FXML
    private TableColumn<?, ?> crtJaune;

    @FXML
    private JFXTextField txtnom;

    @FXML
    private JFXTextField txtsearch;

    @FXML
    private JFXButton btsearch;

    @FXML
    private ChoiceBox chPos;

    @FXML
    private ChoiceBox cheq;
    @FXML
    private JFXButton filter;
    @FXML
    private JFXDrawer SidePannel;
    @FXML
    private JFXHamburger Sp;

    @FXML
    void search(ActionEvent event) {
        ObservableList<Joueur> jEq = FXCollections.observableArrayList(sj.chercherParNom(txtsearch.getText()));
        lstjoueur.getItems().clear();
        lstjoueur.getItems().addAll(jEq);
        nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
        pays.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getId_equipe().getPays()));
        nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbr_but"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        num.setCellValueFactory(new PropertyValueFactory<>("numMaillot"));
        crtJaune.setCellValueFactory(new PropertyValueFactory<>("nbrCartJaune"));
        crtRouge.setCellValueFactory(new PropertyValueFactory<>("nbrCartRouge"));
    }

    @FXML
    void filtrerjoueur(ActionEvent event) {

        ObservableList<Joueur> lstj = FXCollections.observableArrayList(sj.selectJoueurs());
        List<Joueur> lst = new ArrayList<>();

        String pos = chPos.getSelectionModel().selectedItemProperty().getValue().toString();
        System.out.println("pos" + pos);

        String eq = cheq.getSelectionModel().selectedItemProperty().getValue().toString();
        System.out.println("eq" + eq);

        //si les deux sont selctionnés
        if ((pos.length() != 0) && eq.length() != 0) {
            System.out.println("if 1");
            lstj.stream().filter((j) -> (j.getPosition().equals(pos) && j.getIdEquipe().getPays().equals(eq))).forEachOrdered((j) -> {
                lst.add(j);
            });
            System.out.println("eq---pos" + eq + "---" + pos);
        } //une des deux
        else if ((pos.length() != 0) || eq.length() != 0) {
            System.out.println("if 2");
            if ((pos.length() != 0)) {
                System.out.println("if 3");
                lstj.stream().filter((j) -> (j.getPosition().equals(pos))).forEachOrdered((j) -> {
                    lst.add(j);
                    System.out.println("pos" + pos);
                });
            }
            if (eq.length() != 0) {
                System.out.println("if 4");
                lstj.stream().filter((j) -> (j.getIdEquipe().getPays().equals(eq))).forEachOrdered((j) -> {
                    lst.add(j);
                    System.out.println("eq" + eq);
                });
            }
            System.out.println("end 2");
        }
        // lstj=(ObservableList<Joueur>) lst;
        ObservableList<Joueur> lstn = FXCollections.observableArrayList(lst);
        lstjoueur.getItems().clear();
        lstjoueur.getItems().addAll(lstn);

    }

    @FXML
    private JFXTextField txtbut;

    @FXML
    private ChoiceBox pochoice;

    @FXML
    private ImageView RECHIC;

    @FXML
    private ImageView FILTERIC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eqchoice.setValue(listeequipe.get(0));
        eqchoice.setItems(listeequipe);
        pochoice.setValue(listepos.get(0));
        pochoice.setItems(listepos);
        chPos.setItems(listepos1);
        chPos.setValue(listepos1.get(0));
        listeequipe1.add(0, "");
        cheq.setItems(listeequipe1);
        cheq.setValue(listeequipe1.get(0));
        // lstjoueur.getItems().addAll(getJ());
        lstjoueur.getItems().addAll(getJ());
        nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
        pays.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getId_equipe().getPays()));
        nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbr_but"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        num.setCellValueFactory(new PropertyValueFactory<>("numMaillot"));
        crtJaune.setCellValueFactory(new PropertyValueFactory<>("nbrCartJaune"));
        crtRouge.setCellValueFactory(new PropertyValueFactory<>("nbrCartRouge"));
        btModif.setDisable(true);
        btSupp.setDisable(true);
        btAjouter.setDisable(false
        );

         initDrawer();
    }    
    private void initDrawer() {
        try {
            AnchorPane SP = FXMLLoader.load(getClass().getResource("/GUI/SidePannel.fxml"));

            
            
            SP.getStylesheets().add(getClass().getResource("/Asset/Style.css").toExternalForm());
            
            SidePannel.setSidePane(SP);

        } catch (IOException ex) {
           
            System.out.println(ex.getMessage());
        }
        
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(Sp);
        task.setRate(-1);
        Sp.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            if (SidePannel.isHidden()) {
                SidePannel.open();
            } else {
                SidePannel.close();
            }
        });
    }

    void viderchampscrtrou(KeyEvent event) {
        lblr.setVisible(false);
    }

    void viderchampcrtyjau(KeyEvent event) {
        lblnj.setVisible(false);
    }
    @FXML
    private Label lblr;

    @FXML
    private Label lblnj;

    @FXML
    void AjouterJ(ActionEvent event) {
        Equipe e = new Equipe();
        int id;
        //recuperer le nom de l Equipe
        String equipe = eqchoice.getSelectionModel().selectedItemProperty().getValue().toString();
        ///recuperer l objet Equipe
        e = se.AfficherEquipe(equipe);
        if (verifform() == true) {

            Joueur j1 = new Joueur(txtnom.getText(),
                    Integer.parseInt(txtbut.getText()),
                    pochoice.getSelectionModel().selectedItemProperty().getValue().toString(),
                    Integer.parseInt(txtcrtrouge.getText()),
                    Integer.parseInt(txtcrtjaune.getText()),
                    Integer.parseInt(txtmaillot.getText()),
                    e
            );

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
            } else if (res == 3) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout echoué !");
                alert.setHeaderText("Ce Joueur appartient déja a une equipe !");
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
                viderForm();
                btSupp.setDisable(true);
                btModif.setDisable(true);
                btAjouter.setDisable(false
                );
            }
        } else {
            verifobl();
            /*  lbln.setVisible(true);
            lblma.setVisible(true);
            lblnj.setVisible(true);
            lblr.setVisible(true);*/
        }
    }

    @FXML
    void viderchampmaillot(KeyEvent event) {
        lblma.setVisible(false);
    }

    @FXML
    void viderchampsnom(KeyEvent event) {
        lbln.setVisible(false);
    }

    @FXML
    private Label lbln;

    @FXML
    private Label lblma;
    @FXML
    private Label lblb;

    @FXML
    void chargerDonn(MouseEvent event) {
        Joueur j = (Joueur) lstjoueur.getSelectionModel().selectedItemProperty().getValue();

        txtnom.setText(j.getNom_joueur());
        int nb = j.getNbr_but();
        txtbut.setText(String.valueOf(nb));
        txtcrtjaune.setText(String.valueOf(j.getNbrCartJaune()));
        txtcrtrouge.setText(String.valueOf(j.getNbrCartRouge()));
        txtmaillot.setText(String.valueOf(j.getNumMaillot()));

        eqchoice.setValue(j.getId_equipe().getPays());
        pochoice.setValue(j.getPosition());
        System.out.println("joueuuurr id +" + j.getId_equipe().getPays());
        btSupp.setDisable(false);
        btModif.setDisable(false);
        btAjouter.setDisable(true);
        statjaune.setVisible(false);
        statrouge.setVisible(false);
        //statjoueur(j);

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
            viderForm();
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
                    viderForm();
                    btSupp.setDisable(true);
                    btModif.setDisable(true);
                    btAjouter.setDisable(false);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Suppression echouée !");
                    alert.setHeaderText("");
                    alert.setContentText(" Veuillez verifier vos choix!");
                    alert.showAndWait();
                    lstjoueur.getItems().clear();
                    lstjoueur.getItems().addAll(getJ());
                    viderForm();

                }
            } else {
                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(getJ());
                viderForm();
            }
        }
    }

    @FXML
    void modifEJ(ActionEvent event) {
        boolean verif = verifform();
        if (verifform() == true) {
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
                //    j.setNationalite(txtnat.getText());
                j.setId_equipe(e);
                j.setNbr_but(Integer.parseInt(txtbut.getText()));
                j.setPosition(pochoice.getSelectionModel().selectedItemProperty().getValue().toString());
                j.setNumMaillot(Integer.parseInt(txtmaillot.getText()));
                j.setNbrCartJaune(Integer.parseInt(txtcrtjaune.getText()));
                j.setNbrCartRouge(Integer.parseInt(txtcrtrouge.getText()));
                boolean modif = sj.modifierJoueur(j);
                //  System.out.println("id eq +" + eq);
                if (modif == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Modification effectuée !");
                    alert.setHeaderText("Joueur modifié avec succès !");
                    alert.showAndWait();
                    //recharger la liste
                    lstjoueur.getItems().clear();
                    lstjoueur.getItems().addAll(getJ());
                    //vider le formulaire
                    viderForm();
                    btSupp.setDisable(true);
                    btModif.setDisable(true);
                    btAjouter.setDisable(false);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Modification echouée !");
                    alert.setHeaderText("");
                    alert.setContentText(" Veuillez verifier vos choix!");
                    alert.showAndWait();
                    lstjoueur.getItems().clear();
                    lstjoueur.getItems().addAll(getJ());
                    // viderForm();
                    //  btSupp.setVisible(false);
                    //  btModif.setVisible(false);
                    //  btAjouter.setVisible(true);
                }
            } else {
                lstjoueur.getItems().clear();
                lstjoueur.getItems().addAll(getJ());
                viderForm();
                btAjouter.setDisable(true);
            }
        } else {

            verifobl();
        }

    }

    public ObservableList<Joueur> getJ() {
        ObservableList<Joueur> j = FXCollections.observableArrayList(sj.selectJoueurs());

        return j;
    }

    @FXML
    void verifnum2(KeyEvent event) {

        txtmaillot.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtmaillot.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        lblma.setVisible(false);
    }

    @FXML
    void verifnum3(KeyEvent event) {

        txtcrtrouge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtcrtrouge.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        lblr.setVisible(false);
    }

    @FXML
    void verifnum4(KeyEvent event) {

        txtcrtjaune.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtcrtjaune.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        lblnj.setVisible(false);
    }

    @FXML
    void verifnum1(KeyEvent event) {

        txtbut.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtbut.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        lblb.setVisible(false);
    }

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

        lblb.setVisible(false);
    }

    @FXML
    void verifnom(KeyEvent event) {
        txtnom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\sa-zA-Z*")) {
                    txtnom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });
        lbln.setVisible(false);

    }


    @FXML
    void veriftxt(KeyEvent event) {
        txtsearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\sa-zA-Z*")) {
                    txtsearch.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });

    }

    public boolean verifform() {
        boolean ok = true;
        if ((txtnom.getText().isEmpty())
                || (txtbut.getText().isEmpty())
                || (txtcrtjaune.getText().equals(""))
                || (txtcrtrouge.getText().equals(""))
                || (txtmaillot.getText().equals(""))) {
            ok = false;
        }
        return ok;

    }

    public void verifobl() {
        boolean ok = true;
        if (txtnom.getText().isEmpty()) {
            lbln.setVisible(true);
            ok = false;

        }
        if (txtbut.getText().isEmpty()) {
            lblb.setVisible(true);
            ok = false;

        }
        if (txtcrtjaune.getText().equals("")) {

            lblnj.setVisible(true);
        }
        if (txtcrtrouge.getText().equals("")) {

            lblr.setVisible(true);
        }
        if (txtmaillot.getText().equals("")) {

            lblma.setVisible(true);
        }
    }

    public void viderForm() {
        txtnom.setText("");
        txtcrtjaune.setText("");
        txtbut.setText("");
        txtcrtrouge.setText("");
        txtmaillot.setText("");

    }

    @FXML
    private PieChart statrouge;

    @FXML
    private PieChart statjaune;

    public void statjoueur(Joueur j) {
        // Joueur j = (Joueur) lstjoueur.getSelectionModel().selectedItemProperty().getValue();
        Equipe e = j.getIdEquipe();
        Equipe equipe = e;
        //   float tot = me.getTotal(equipe);
        float CartRouge = me.getRouge(equipe);
        float CartJaune = me.getJaune(equipe);
        //  float totjaune = me.getTotalJaune();
        // float totrouge = me.getTotalRouge();
        //float st1 = CartJaune / totjaune;
        if (j.getNbrCartJaune() != 0) {
            ObservableList<PieChart.Data> donneJ = FXCollections.observableArrayList(
                    new PieChart.Data("Carton jaune" + j.getNomJoueur(), j.getNbrCartJaune() / CartJaune),
                    new PieChart.Data("carton rouge"+ j.getNomJoueur(), j.getNbrCartRouge() / CartRouge),
                    new PieChart.Data("Autre jaune", 1 - (j.getNbrCartJaune() / CartJaune)),
                    new PieChart.Data("Autre rouge", 1 - (j.getNbrCartRouge() / CartRouge)) 
            );
            statjaune.setVisible(true);

            statjaune.setData(donneJ);
        }
        if (j.getNbrCartRouge() != 0) {
            ObservableList<PieChart.Data> donneR = FXCollections.observableArrayList(
                    new PieChart.Data("" + j.getNomJoueur(), j.getNbrCartRouge() / CartRouge),
                    new PieChart.Data("Autre", 1 - (j.getNbrCartRouge() / CartRouge))
            );
           statrouge.setData(donneR);
          statrouge.setVisible(true);
        }

    }
}
