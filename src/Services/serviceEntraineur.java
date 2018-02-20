/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataSource.DataSource;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import Entities.Entraineur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emel
 */
public class serviceEntraineur {

    DataSource db;
    Connection cnx;
    Statement st;

    public serviceEntraineur() {
        cnx = (Connection) db.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(serviceEntraineur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ajouterEntraineur(Entraineur e) {
        try {
            String req = " INSERT INTO `entraineur` (`id_entraineur`, `nom_entraineur`, `nationalité`, `id_equipe`)"
                    + " VALUES (NULL, '" + e.getNom_entraineur() + "', '" + e.getNationalite() + "', '" + e.getId_equipe() + "');";

            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(serviceEntraineur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierEntraineur(Entraineur e)
    {   try {
            String req = "UPDATE `entraineur` SET "
                    + "`nom_entraineur` = '" + e.getNom_entraineur() + "', `nationalité` = '" + e.getNationalite() + "', `id_equipe` = '" + e.getId_equipe() + "' "
                    + "WHERE `entraineur`.`id_entraineur` = " + e.getId_entraineur() + ";";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(serviceEntraineur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerEntraineur(int id_entraineur) {
        try {
            
            String req = "DELETE FROM `entraineur` "
                    + "WHERE `entraineur`.`id_entraineur` = " + id_entraineur + ";";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(serviceEntraineur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void chercherParNom(String nom) {
        List<Entraineur> entraineurs = new ArrayList<>();

        try {

            ResultSet rest= st.executeQuery("select * from Entraineur where nom_entraineur like '%+" + nom + "+%'");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
            while (rest.next()) {
                Entraineur e = new Entraineur();
                e.setId_entraineur(rest.getInt(1));
                e.setNom_entraineur(rest.getString(2));
                e.setNationalite(rest.getString(3));
                e.setId_equipe(rest.getInt(4));
                entraineurs.add(e);

            }
              for (Entraineur e : entraineurs) {
            System.out.println(e);

        }
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEntraineur.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }

    public void selectEntraineurs() {
        List<Entraineur> entraineurs = new ArrayList<>();

        try {

            ResultSet rest= st.executeQuery("select * from Entraineur");
             if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
            while (rest.next()) {
                Entraineur e = new Entraineur();
                e.setId_entraineur(rest.getInt(1));
                e.setNom_entraineur(rest.getString(2));
                e.setNationalite(rest.getString(3));
                e.setId_equipe(rest.getInt(4));
                entraineurs.add(e);

            }
             for (Entraineur e : entraineurs) {
            System.out.println(e);

        }
             }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEntraineur.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

}
