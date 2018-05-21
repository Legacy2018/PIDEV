/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataSource.DataSource;
import Entities.Equipe;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import Entities.Joueur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emel
 */
public class serviceJoueur implements IServices.IServiceGestionJoueur {

    DataSource db;
    Connection cnx;
    Statement st;

    public serviceJoueur() {
        cnx = (Connection) db.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int VerifJoueur (int idj , int maillot, int idequipe , String nom) throws SQLException
    {
                ResultSet rest1 = st.executeQuery("SELECT COUNT("+idj+") FROM `joueur` WHERE idEquipe="+idequipe+" and NumeroMaillot="+maillot+"  or nom like '"+nom+"';");
        rest1.next();
    return rest1.getInt(1);
    }
    @Override
    public int ajouterJoueur(Joueur j) {

        //ok prend 1 si Joueur ajouté `|| ok =0 nbre >23 ||  ok =2 gardien >3
        int ok = 1;
        try {

            ResultSet rest1 = st.executeQuery("SELECT count(idEquipe) from joueur where joueur.idEquipe=" + j.getId_equipe().getIdequipe());
            rest1.next();
            //verifier que l'equipe n'est pas saturée ( 23j/equipe)
            if ((rest1.getInt(1) < 23)) {

                //verifier sa position (le nombre des gardiens ne depasse pas 3)
                if (VerifJoueur(j.getIdJoueur(), j.getNumMaillot(), j.getId_equipe().getIdequipe(), j.getNomJoueur())==0)
                {
                if (j.getPosition().equals("Gardien")) {
                    ResultSet rest2 = st.executeQuery("SELECT count(id_joueur) from joueur where joueur.idEquipe =" + j.getId_equipe().getIdequipe() + " and joueur.position='Gardien'");
                    rest2.next();
                    if ((rest2.getInt(1) < 3)) {

                        String req = " INSERT INTO `joueur` (`id_joueur`, `nom`, `nombre_de_buts`, `position`, `idEquipe`, `cartrouge`, `cartjaune`, `NumeroMaillot`)"
                                + " VALUES (NULL, '" + j.getNom_joueur() + "', " + j.getNbr_but() + ", '" + j.getPosition() + "', " + j.getId_equipe().getIdequipe() + ",0 , 0, " + j.getNumMaillot() + ")";

                        st.executeUpdate(req);
                    } else {

                        ok = 2;
                    }
                    //   test = true;
                } else //SI IL EST PAS GARDIEN L AJOUT SERA FAIT sans verification pour les autres positions
                {

                    String req = " INSERT INTO `joueur` (`id_joueur`, `nom`, `nombre_de_buts`, `position`, `idEquipe`, `cartrouge`, `cartjaune`, `NumeroMaillot`)"
                            + " VALUES (NULL, '" + j.getNom_joueur() + "', " + j.getNbr_but() + ", '" + j.getPosition() + "', " + j.getId_equipe().getIdequipe() + ",0 , 0, " + j.getNumMaillot() + ")";

                    st.executeUpdate(req);

                    ok = 1;
                }
            }
                else ok=3;
            } else {

                ok = 0;

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    @Override
    public boolean modifierJoueur(Joueur j) {

        boolean resultat = false;
        try {

            String req = "UPDATE `joueur` SET `nom` = '" + j.getNom_joueur() + "',"
                    + "`nombre_de_buts` = " + j.getNbr_but() + " , "
                    + "`position` = '" + j.getPosition() + "' , "
                    + "`idEquipe` = " + j.getId_equipe().getIdequipe()
                    + ",  `cartrouge` = '" + j.getNbrCartRouge() + "',"
                    + " `cartjaune` = '" + j.getNbrCartJaune() + "',"
                    + " `NumeroMaillot` = '" + j.getNumMaillot() + "' "
                    + " WHERE `joueur`.`id_joueur` = " + j.getId_joueur() + ";";
            int count = st.executeUpdate(req);
            if (count > 0) {
                resultat = true;
            } else {
                resultat = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultat;
    }

    @Override
    public boolean supprimerJoueur(int id_joueur) {

        boolean test = true;
        try {
            String req = "DELETE FROM `joueur`"
                    + " WHERE `joueur`.`id_joueur` = " + id_joueur + ";";
            int count = st.executeUpdate(req);
            if (count > 0) {
                test = true;
            } else {
                test = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("rslt supp " + test);
        return test;

    }

    @Override
    public List<Joueur> selectJoueurs() {
        List<Joueur> joueurs = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery ("SELECT `id_joueur`, `nom`, `nombre_de_buts`, `position`,  joueur.idEquipe , `cartrouge`, `cartjaune`, `NumeroMaillot` FROM `joueur` , equipe WHERE joueur.idEquipe = equipe.id_equipe");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                serviceEquipe se = new serviceEquipe();
                rest.beforeFirst();
                while (rest.next()) {
                    Joueur j = new Joueur();
                    j.setId_joueur(rest.getInt(1));
                    j.setNom_joueur(rest.getString(2));
                    j.setNbr_but(rest.getInt(3));
                    j.setPosition(rest.getString(4));
                    Equipe e = new Equipe();
                    j.setId_equipe(se.AfficherEquipe(rest.getInt(5)));
                    j.setNbrCartRouge(rest.getInt(6));
                    j.setNbrCartJaune(rest.getInt(7));
                    j.setNumMaillot(rest.getInt(8));
                    
                    joueurs.add(j);

                }
                for (Joueur j : joueurs) {
                    System.out.println(j);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return joueurs;
    }

    @Override
    public List<Joueur> chercherParNom(String nom) {
        List<Joueur> joueurs = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT id_joueur ,nom ,equipe.pays, nombre_de_buts , position, `cartrouge`,`cartjaune`,`NumeroMaillot` "
                    + "FROM `joueur` , equipe"
                    + " where equipe.id_equipe=joueur.idEquipe and nom like '%" + nom + "%' order by nombre_de_buts desc;");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                serviceEquipe se = new serviceEquipe();
                while (rest.next()) {
                    Joueur j = new Joueur();
                    j.setId_joueur(rest.getInt(1));
                    j.setNom_joueur(rest.getString(2));
                    //   j.setPays(rest.getString(3));
                    Equipe e = new Equipe();
                    j.setId_equipe(se.AfficherEquipe(rest.getString(3)));

                    //    j.setId_equipe(rest.getObject());
                
                    j.setNbr_but(rest.getInt(4));
                    j.setPosition(rest.getString(5));
                    j.setNbrCartRouge(rest.getInt(6));
                    j.setNbrCartJaune(rest.getInt(7));
                    j.setNumMaillot(rest.getInt(8));
                    joueurs.add(j);

                }
                for (Joueur j : joueurs) {
                    System.out.println(j);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return joueurs;
    }

 

    @Override
    public List<Joueur> chercherParPosition(String pos) {
        List<Joueur> joueurs = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT id_joueur ,nom ,equipe.pays, nombre_de_buts , position, `cartrouge`,`cartjaune`,`NumeroMaillot`"
                    + "FROM `joueur` , equipe"
                    + " where equipe.id_equipe=joueur.idEquipe and position= '" + pos + "' order by nombre_de_buts desc;");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                serviceEquipe se = new serviceEquipe();
                while (rest.next()) {
                    Joueur j = new Joueur();
                    j.setId_joueur(rest.getInt(1));
                    j.setNom_joueur(rest.getString(2));
                    //    j.setPays(rest.getString(3));
                    Equipe e = new Equipe();
                    j.setId_equipe(se.AfficherEquipe(rest.getString(3)));

                
                    j.setNbr_but(rest.getInt(4));
                    j.setPosition(rest.getString(5));
                    j.setNbrCartRouge(rest.getInt(6));
                    j.setNbrCartJaune(rest.getInt(7));
                    j.setNumMaillot(rest.getInt(8));
                    joueurs.add(j);

                }
                for (Joueur j : joueurs) {
                    System.out.println(j);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return joueurs;
    }

    @Override
    public List<Joueur> chercherParEquipe(String pays) {
        List<Joueur> joueurs = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT id_joueur ,nom ,equipe.pays , nombre_de_buts , position, `cartrouge`,`cartjaune`,`NumeroMaillot`  "
                    + "FROM `joueur` , equipe"
                    + " where equipe.id_equipe=joueur.idEquipe and equipe.pays like '%" + pays + "%' order by nombre_de_buts desc;");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                serviceEquipe se = new serviceEquipe();
                while (rest.next()) {
                    Joueur j = new Joueur();
                    j.setId_joueur(rest.getInt(1));
                    j.setNom_joueur(rest.getString(2));
                    Equipe e = new Equipe();
                    j.setId_equipe(se.AfficherEquipe(rest.getString(3)));
                    j.setNbr_but(rest.getInt(4));
                    j.setPosition(rest.getString(5));
                    j.setNbrCartRouge(rest.getInt(6));
                    j.setNbrCartJaune(rest.getInt(7));
                    j.setNumMaillot(rest.getInt(8));

                    joueurs.add(j);

                }
                for (Joueur j : joueurs) {
                    System.out.println(j);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return joueurs;
    }

    @Override
    public List<Joueur> chercherParEquipe() {
        List<Joueur> joueurs = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT id_joueur ,nom ,equipe.pays , nombre_de_buts , position, `cartrouge`,`cartjaune`,`NumeroMaillot` "
                    + "FROM `joueur`, equipe "
                    + "WHERE joueur.idEquipe=equipe.id_equipe");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                serviceEquipe se = new serviceEquipe();
                rest.beforeFirst();
                while (rest.next()) {
                    Joueur j = new Joueur();
                    j.setId_joueur(rest.getInt(1));
                    j.setNom_joueur(rest.getString(2));
                    Equipe e = new Equipe();
                    j.setId_equipe(se.AfficherEquipe(rest.getString(3)));
                    j.setNbr_but(rest.getInt(4));
                    j.setPosition(rest.getString(5));
                    j.setNbrCartRouge(rest.getInt(6));
                    j.setNbrCartJaune(rest.getInt(7));
                    j.setNumMaillot(rest.getInt(8));
                    joueurs.add(j);

                }
                for (Joueur j : joueurs) {
                    System.out.println(j);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return joueurs;
    }

    @Override
    public Joueur chercherParEquipe(int id) {
        //    List<joueur> joueurs = new ArrayList<>();
        Joueur j = new Joueur();

        try {

            ResultSet rest = st.executeQuery("SELECT id_joueur ,nom ,equipe.pays , nombre_de_buts , position, `cartrouge`,`cartjaune`,`NumeroMaillot`"
                    + "FROM `joueur`, equipe "
                    + " WHERE joueur.idEquipe=equipe.id_equipe "
                    + "and id_joueur=" + id);
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                serviceEquipe se = new serviceEquipe();
                rest.beforeFirst();
                while (rest.next()) {
                    j.setId_joueur(rest.getInt(1));
                    j.setNom_joueur(rest.getString(2));
//                    j.setPays(rest.getString(3));
                    Equipe e = new Equipe();
                    j.setId_equipe(se.AfficherEquipe(rest.getString(3)));
                    j.setNbr_but(rest.getInt(4));
                    j.setPosition(rest.getString(5));
                    j.setNbrCartRouge(rest.getInt(6));
                    j.setNbrCartJaune(rest.getInt(7));
                    j.setNumMaillot(rest.getInt(8));
                    // joueurs.add(j);
                }

                System.out.println(j);

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }

    /*  public void TriButASC() {
        List<Joueur> joueurs = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("select * from joueur ");
            while (rest.next()) {
                Joueur j = new Joueur();
                j.setId_joueur(rest.getInt(1));
                j.setNom_joueur(rest.getString(2));
                j.setNationalite(rest.getString(3));
                j.setNbr_but(rest.getInt(4));
                j.setPosition(rest.getString(5));
                j.setId_equipe(rest.getInt(6));

                joueurs.add(j);

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(joueurs);
        for (Joueur j : joueurs) {
            System.out.println(j);

        }

    }

    public void TriButDESC() {
        List<Joueur> joueurs = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT * FROM `joueur` "
                    + "ORDER BY nombre_de_buts DESC");
            while (rest.next()) {
                Joueur j = new Joueur();
                j.setId_joueur(rest.getInt(1));
                j.setNom_joueur(rest.getString(2));
                j.setNationalite(rest.getString(3));
                j.setNbr_but(rest.getInt(4));
                j.setPosition(rest.getString(5));
                j.setId_equipe(rest.getInt(6));

                joueurs.add(j);

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Joueur j : joueurs) {
            System.out.println(j);

        }

    }
     */
}
