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
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.management.Notification;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jpedal.PdfDecoder;
import org.jpedal.*;
import org.jpedal.exception.PdfException;

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
        System.err.println("test " + p.get(1));
        return p;
    }

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD, BaseColor.BLACK);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.BLACK);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 15,
            Font.BOLD, BaseColor.BLACK);
    private static Font Bold = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD, BaseColor.MAGENTA);

    @FXML
    private JFXButton imprim;

    public JFreeChart generatePieChartR() {
        Equipe equipe = (Equipe) lsteq.getSelectionModel().selectedItemProperty().getValue();

        float CartRouge = me.getRouge(equipe);
        float totrouge = me.getTotalRouge();

        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("" + equipe.getPays(), CartRouge / totrouge);
        dataSet.setValue("Adversaires", 1 - (CartRouge / totrouge));

        JFreeChart chart = ChartFactory.createPieChart(
                "Possesion de carton Rouge", dataSet, true, true, false);

        return chart;
    }

    public JFreeChart generatePieChartJ() {
        Equipe equipe = (Equipe) lsteq.getSelectionModel().selectedItemProperty().getValue();

        float CartJaune = me.getJaune(equipe);
        float totjaune = me.getTotalJaune();

        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("" + equipe.getPays(), CartJaune / totjaune);
        dataSet.setValue("Adversaires", 1 - (CartJaune / totjaune));

        JFreeChart chart = ChartFactory.createPieChart(
                "Possesion de carton jaune", dataSet, true, true, false);

        return chart;
    }

    
    @FXML
    void Print(ActionEvent event) throws DocumentException, IOException {
        Document doc = new Document();
        Paragraph paragraph = new Paragraph("Infomaation Equipe", new Font(Font.FontFamily.TIMES_ROMAN, 18,
                Font.BOLD));
        Equipe e = (Equipe) lsteq.getSelectionModel().selectedItemProperty().getValue();
        PdfWriter writer = null;
        if (e != null) {
            try {

                int rand = (int) (Math.random() * (1000 - 1));
                String nomchfich = "Formation" + e.getPays() + String.valueOf(rand) + ".pdf";
                writer = PdfWriter.getInstance(doc, new FileOutputStream(nomchfich));
                doc.open();
                //  doc.add(new Paragraph("Fiche Recapitulative "),Bold);
                System.err.println("pdf equipe  " + e.getPays());
                Paragraph titre = new Paragraph("Fiche Recapitulative ", Bold);
                titre.setAlignment(Paragraph.ALIGN_CENTER);
                doc.add(titre);
              
                Paragraph p = new Paragraph("" + e.getPays(), Bold);
                p.setAlignment(Paragraph.ALIGN_CENTER);
                doc.add(p);
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph("Groupe             : " + e.getGroupe(), catFont));
                doc.add(new Paragraph("Phase              : " + e.getPhase(), catFont));
                doc.add(new Paragraph("Selectionneur      : " + e.getSelecteur(), catFont));
                doc.add(new Paragraph("Nombre de But      : " + String.valueOf(me.afficherNombreButParEquipe(e.getIdequipe())), catFont));
                doc.add(new Paragraph("Nombre de carton   : " + (me.getRouge(e) + me.getRouge(e)), catFont));
                doc.add(new Paragraph("Rouge              : " + me.getRouge(e), catFont));
                doc.add(new Paragraph("Jaune              : " + me.getRouge(e), catFont));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph("Liste des Joueurs              : ", subFont));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));

                //--------------------------------------------------------------
                ObservableList<Joueur> joueurs = FXCollections.observableArrayList(sj.chercherParEquipe(e.getPays()));
                PdfPTable table = new PdfPTable(3);
                // table.setWidths(new int[]{1, 4});

                PdfPCell cell1 = new PdfPCell(new Phrase("joueur"));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBorderWidth(2);
                cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell1);

                cell1 = new PdfPCell(new Phrase("poste"));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBorderWidth(2);
                cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell1);

                cell1 = new PdfPCell(new Phrase("but"));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBorderWidth(2);
                cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell1);

                table.setHeaderRows(1);

                List<String> nom = new ArrayList<>();
                List<String> but = new ArrayList<>();
                List<String> poste = new ArrayList<>();
                for (Joueur joueur : joueurs) {
                    nom.add(joueur.getNomJoueur());
                    poste.add(joueur.getPosition());
                    System.err.println("jjjjjjjj" + joueur);
                    but.add(String.valueOf(joueur.getNbr_but()));
                }
                System.err.println("taille" + joueurs.size());
                for (int i = 0; i < joueurs.size(); i++) {
                    System.out.println("nom");
                    table.addCell(nom.get(i));
                    table.addCell(poste.get(i));
                    table.addCell(but.get(i));

                }

                /*
               
                 */
                PdfContentByte contentByter = writer.getDirectContent();
                PdfContentByte contentBytej = writer.getDirectContent();

                PdfTemplate templatej = contentBytej.createTemplate(200, 200);
                Graphics2D graphics2dj = templatej.createGraphics(200, 200,
                        new DefaultFontMapper());
                Rectangle2D rectangle2dj = new Rectangle2D.Double(0, 0, 200,
                        200);

                PdfTemplate templater = contentByter.createTemplate(200, 200);
                Graphics2D graphics2dr = templater.createGraphics(200, 200,
                        new DefaultFontMapper());
                Rectangle2D rectangle2dr = new Rectangle2D.Double(0, 0, 200,
                        200);

                contentByter.addTemplate(templater, 10, 20);
                contentBytej.addTemplate(templatej, 300, 20);
                JFreeChart chartJ = generatePieChartJ();
                JFreeChart chartR = generatePieChartR();
                chartR.draw(graphics2dr, rectangle2dr);
                chartJ.draw(graphics2dj, rectangle2dj);
                graphics2dr.dispose();
                graphics2dj.dispose();
                doc.add(new Paragraph(""));

                doc.add(table);
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph("Statistiques de possesion de carton             : ", subFont));

                doc.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Le fichier PDF est téléchargé!");
                alert.showAndWait();
                Desktop.getDesktop().open(new File("C:\\Users\\Emel\\Documents\\javaproject\\PIDEV1\\" + nomchfich));
                /*
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("@/Assets/myDialogs.css").toExternalForm());
                dialogPane.getStyleClass().add("myDialog");
                 */

//PdfDecoder pdf = openPdf("Formation.pdf");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GestionEquipeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(GestionEquipeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Veuillez choisir une equipe!");

            alert.showAndWait();
        }
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
    private JFXButton Stat;

    @FXML
    void goToStat(ActionEvent event) {
        Equipe e = (Equipe) lsteq.getSelectionModel().selectedItemProperty().getValue();
        if (e != null) {
            //Stage stage = (Stage) Stat.getScene().getWindow();
            //Parent root;

            try {
                Stage st = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXMLStatistique.fxml"));
                Parent sceneMain = loader.load();
                //    root = FXMLLoader.load(getClass().getResource("/GUI/FXMLStatistique.fxml"));
                //   Scene scene = new Scene(root);
                //  stage.setScene(scene);
                //      loader.load();
                StatistiqueController controller = loader.<StatistiqueController>getController();
                controller.getEq(e);
                Scene scene = new Scene(sceneMain);
                st.setScene(scene);
                // st.setMaximized(true);
                st.setTitle("Statistique");
                st.show();
            } catch (IOException ex) {
                Logger.getLogger(DisplayEquipeJoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Veuillez choisir une equipe!");

            alert.showAndWait();
        }
    }

    @FXML
    void Afficherdetails(MouseEvent event) {
        imprim.setVisible(true);
        Equipe e = (Equipe) lsteq.getSelectionModel().selectedItemProperty().getValue();
        if (e.getEtat() == 0) {
            etat.setVisible(true);
            etat.setText("Eliminée");
        } else if (e.getEtat() == 1) {
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
        nbrbuteq.setText(String.valueOf(me.afficherNombreButParEquipe(e.getIdequipe())));
        select.setText(e.getSelecteur());
        File file = new File(e.getImg().getLink());
        Image image = new Image(file.toURI().toString());

        drapeau.setImage(image);
        System.out.println("equipe " + e);
        ObservableList<Joueur> jEq = FXCollections.observableArrayList(sj.chercherParEquipe(e.getPays()));
        System.out.println("liste joueur " + jEq);
        lstjou.getItems().clear();
        lstjou.getItems().addAll(jEq);

        nom_joueur.setCellValueFactory(new PropertyValueFactory<>("nomJoueur"));
        nbr_but.setCellValueFactory(new PropertyValueFactory<>("nbrBut"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        num.setCellValueFactory(new PropertyValueFactory<>("numMaillot"));
        crtJaune.setCellValueFactory(new PropertyValueFactory<>("nbrCartJaune"));
        crtRouge.setCellValueFactory(new PropertyValueFactory<>("nbrCartRouge"));
    }

    @FXML
    private TableColumn<?, ?> crtRouge;

    @FXML
    private TableColumn<?, ?> crtJaune;
    @FXML
    private TableColumn<?, ?> num;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lsteq.getItems().addAll(getEq());
        eq.setCellValueFactory(new PropertyValueFactory<>("pays"));

    }

}
