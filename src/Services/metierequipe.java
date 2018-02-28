/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataSource.DataSource;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import Entities.Equipe;
import Entities.Imagedrapeau;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Services.serviceEquipe;

/**
 *
 * @author Emel
 */
public class metierequipe {

    DataSource db;
    Connection cnx;
    Statement st;

    public metierequipe() {
        cnx = (Connection) db.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int afficherNombreButParEquipe(int ideq) {
        int somme = 0;
        try {
            ResultSet rest = st.executeQuery("SELECT SUM(joueur.nombre_de_buts) FROM joueur where id_equipe=" + ideq);
            rest.next();
            somme = rest.getInt(1);
            System.out.println("pays");

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return somme;
    }

    public void AtribuerPoint( //match m
            ) {
        /*
                
                if (m.getScore1== m.getScore2)
                {
                //recuperer l'quipe 1 et 2
                equipe1.setPoint(equioppe1.getPoint+1));
                 equipe2.setPoint(equioppe2.getPoint+1));
                
                }
                else if (m.getSCORE1 < M.GETsCORE°
         */

    }

    public int getTotal(Equipe e) {
        int resultat = 0;

        try {

            ResultSet rest = st.executeQuery("SELECT count(`joueur`.`id_joueur`) FROM joueur , equipe WHERE equipe.id_equipe= joueur.id_equipe and joueur.id_equipe=" + e.getIdEquipe());
            rest.next();
            resultat = rest.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }

    public int getRouge(Equipe e) {
        int resultat = 0;

        try {

            ResultSet rest = st.executeQuery(" SELECT SUM(`cartrouge`) FROM joueur , equipe WHERE equipe.id_equipe= joueur.id_equipe and joueur.id_equipe=" + e.getIdEquipe());
            rest.next();
            resultat = rest.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }

    public int getTotalRouge() {
        int resultat = 0;

        try {

            ResultSet rest = st.executeQuery("SELECT SUM(`cartrouge`) FROM joueur , equipe WHERE equipe.id_equipe= joueur.id_equipe");
            rest.next();
            resultat = rest.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }

    public int getJaune(Equipe e) {
        int resultat = 0;

        try {

            ResultSet rest = st.executeQuery(" SELECT SUM(`cartjaune`) FROM joueur , equipe WHERE equipe.id_equipe= joueur.id_equipe and joueur.id_equipe=" + e.getIdEquipe());
            rest.next();
            resultat = rest.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }

    public int getTotalJaune() {
        int resultat = 0;

        try {

            ResultSet rest = st.executeQuery("SELECT SUM(`cartjaune`) FROM joueur , equipe WHERE equipe.id_equipe= joueur.id_equipe");
            rest.next();
            resultat = rest.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }

}
