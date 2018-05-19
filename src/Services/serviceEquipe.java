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
import Entities.Equipe;
import Entities.Imagedrapeau;
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
public class serviceEquipe implements IServices.IServiceGestionEquipe {

    DataSource db;
    Connection cnx;
    Statement st;
    Statement st2;

    public serviceEquipe() {
        cnx = (Connection) db.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
              st2 = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean ajouterEquipe(Equipe e) {
        boolean resultat = true;
        try {
            //verifier que le groupe n'est pas saturé
            ResultSet rest1 = st.executeQuery("SELECT count(Groupe) from equipe "
                    + "where Groupe='" + e.getGroupe() + "';");
            rest1.next();
            if ((rest1.getInt(1) < 4)) {

                //verifier l'unicité de l'Equipe
                ResultSet rest2 = st.executeQuery("SELECT count(pays) from equipe "
                        + "where pays='" + e.getPays() + "' ;");
                rest2.next();
                if ((rest2.getInt(1) == 0)) {

                    String req = " INSERT INTO `equipe` (`id_equipe`, `pays`, `etat`, `Phase`, `Groupe`,`Selecteur`) "
                            + "VALUES (NULL, '" + e.getPays() + "', '1', NULL, '" + e.getGroupe() + "','" + e.getSelecteur() + "');";

                    st.executeUpdate(req);
                    //recuprer l id de l'equipe
                    Equipe eqim = AfficherEquipe(e.getPays());
                    System.err.println("id equipe   "+eqim);
                    //inserer l image du drapeau
                    String reqim = "INSERT INTO `imagedrapeau` (`id_drapeau`, `lien`, `id_equipe`) VALUES (NULL, '" + e.getImg().getLink() + "', '" + eqim.getIdEquipe() + "');";
                    st.executeUpdate(reqim);
                    System.out.print("imageeeeeeeeeeeeeeee    12:" + e.getImg().getLink());
                    System.out.print("ajout effectué ");
                    resultat = true;
                } else {
                    System.out.print(" pays existant");
                    resultat = false;
                }
            } else {
                System.out.print("groupe saturé ");
                resultat = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultat;
    }

    @Override
    public boolean modifierEquipe(Equipe e, int id) {
        boolean resultat = false;
        try {

            String req = "UPDATE `equipe` "
                    + "SET `pays` = '" + e.getPays() + "',"
                    + "`etat` = " + e.getEtat() + ", "
                    + "`Phase` = '" + e.getPhase() + "', `Groupe` = '" + e.getGroupe() + "',"
                    + "`Selecteur` = '" + e.getSelecteur() + "', "
                    + "point=" + e.getPoint()
                    + " WHERE `equipe`.`id_equipe` =" + id + ";";
            int count = st.executeUpdate(req);
            if (count > 0) {
               // resultat = true;
                System.out.println("here          :"+e.getImg().getLink());
                String   req2 = "UPDATE `imagedrapeau` "
                        + "SET `lien` = '"+e.getImg().getLink()+"'"
                        + " WHERE `id_equipe` = "+id+";";
           int count2 = st2.executeUpdate(req2);
            
              if (count2 > 0) {
               resultat = true;
              }else  resultat = false;
            } else {
                resultat = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultat;
    }

    @Override
    public boolean supprimerEquipe(Equipe pays) {
        boolean test = true;
        boolean resultat;
        try {
            String req = "DELETE FROM `equipe`"
                    + " WHERE `equipe`.`pays` = '" + pays.getPays() + "'";
            int count = st.executeUpdate(req);
            if (count > 0) {
                
               // resultat = true;
              //  System.out.println("here          :"+paysgetImg().getLink());
                String   req2 = "DELETE FROM `imagedrapeau` WHERE id_equipe="+pays.getIdEquipe();
           int count2 = st2.executeUpdate(req2);
            
              if (count2 > 0) {
               resultat = true;
              }else  resultat = false;
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
    public List<Equipe> chercherParPays(String pays) {
        List<Equipe> equipes = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT equipe.`id_equipe`, `pays`, `etat`, `Phase`, `Groupe`, `Selecteur`, `point`, "
                    + "imagedrapeau.lien"
                    + " from equipe , imagedrapeau"
                    + " WHERE equipe.id_equipe=imagedrapeau.id_equipe and "
                    + "pays like '%" + pays + "%'");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                while (rest.next()) {
                    Equipe e = new Equipe();
                    e.setIdequipe(rest.getInt(1));
                    e.setPays(rest.getString(2));
                    e.setEtat(rest.getInt(3));
                    e.setPhase(rest.getString(4));
                    e.setGroupe(rest.getString(5));
                    e.setSelecteur(rest.getString(6));
                    e.setPoint(rest.getInt(7));
                    System.out.println("image 0");

                    /*   System.out.println("image 1");
                        im = new Imagedrapeau();
                        im.setLink(rest.getString(8));
                            System.out.println("iurl :"+im.getLink());
                        im.setIdEquipe(e);
                         System.out.println("quipe :"+im.getIdEquipe());
                        // e.getImg().setLink(rest.getString(8));
                        //e.getImg().setIdEquipe(e);
                        
                         System.out.println("image eq :"+im);*/
                    e.setImg(new Imagedrapeau(rest.getString(8), null));

                    equipes.add(e);

                }
                for (Equipe e : equipes) {
                    System.out.println(e);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipes;
    }

    @Override
    public Equipe AfficherEquipe(String pays) {

        Equipe e = new Equipe();
        try {

            ResultSet rest = st.executeQuery("SELECT equipe.`id_equipe`, `pays`, `etat`, `Phase`, `Groupe`, `Selecteur`, `point`, "
                    + "imagedrapeau.lien"
                    + " from equipe , imagedrapeau"
                    + " WHERE "
                    + "pays like '" + pays + "'");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                while (rest.next()) {

                    e.setIdequipe(rest.getInt(1));
                    e.setPays(rest.getString(2));
                    e.setEtat(rest.getInt(3));
                    e.setPhase(rest.getString(4));
                    e.setGroupe(rest.getString(5));
                    e.setSelecteur(rest.getString(6));
                    e.setPoint(rest.getInt(7));
                    e.setImg(new Imagedrapeau(rest.getString(8), null));

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return e;
    }
 
    public int Consulterpoint(String pays) {
     Equipe s = new Equipe();

        String requete = "select * from equipe where pays='" + pays + "';";
        try {
            
                    
            ResultSet resultat = st.executeQuery(requete);

            while (resultat.next()) {
               
               
              s.setPoint(resultat.getInt(7));
               

               

            }
         return s.getPoint();
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur " + ex.getMessage());
          
        }
        return s.getPoint();
    }
    public String ConsulterstadeNom(String pays) {
     Equipe s = new Equipe();

        String requete = "select * from equipe where pays='" + pays + "';";
        try {
            
                    
            ResultSet resultat = st.executeQuery(requete);

            while (resultat.next()) {
               
               
                s.setPays(resultat.getString(2));
               

               

            }
         return s.getPays();
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur " + ex.getMessage());
            return null;
        }
    }
    @Override
    public Equipe AfficherEquipe(Equipe eq) {
        Equipe e = new Equipe();
        try {

            ResultSet rest = st.executeQuery("SELECT equipe.`id_equipe`, `pays`, `etat`, `Phase`, `Groupe`, `Selecteur`, `point`, "
                    + "imagedrapeau.lien"
                    + " from equipe , imagedrapeau"
                    + " where equipe.id_equipe=imagedrapeau.id_equipe and "
                    + "pays like '" + eq.getPays() + "'");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                while (rest.next()) {

                    e.setIdequipe(rest.getInt(1));
                    e.setPays(rest.getString(2));
                    e.setEtat(rest.getInt(3));
                    e.setPhase(rest.getString(4));
                    e.setGroupe(rest.getString(5));
                    e.setSelecteur(rest.getString(6));
                    e.setPoint(rest.getInt(7));
                    e.setImg(new Imagedrapeau(rest.getString(8), null));

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return e;
    }

    @Override
    public List<Equipe> EquipeEnCompetition() {
        List<Equipe> equipes = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT equipe.`id_equipe`, `pays`, `etat`, `Phase`, `Groupe`, `Selecteur`, `point`, "
                    + "imagedrapeau.lien"
                    + " from equipe , imagedrapeau"
                    + " WHERE equipe.id_equipe=imagedrapeau.id_equipe and "
                    + "etat=1");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                while (rest.next()) {
                    Equipe e = new Equipe();
                    e.setIdequipe(rest.getInt(1));
                    e.setPays(rest.getString(2));
                    e.setEtat(rest.getInt(3));
                    e.setPhase(rest.getString(4));
                    e.setGroupe(rest.getString(5));
                    e.setSelecteur(rest.getString(6));
                    e.setPoint(rest.getInt(7));
                    e.setImg(new Imagedrapeau(rest.getString(8), null));

                    equipes.add(e);

                }
                for (Equipe e : equipes) {
                    System.out.println(e);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipes;
    }

    @Override
    public List<Equipe> EquipeEliminee() {
        List<Equipe> equipes = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT equipe.`id_equipe`, `pays`, `etat`, `Phase`, `Groupe`, `Selecteur`, `point`, "
                    + "imagedrapeau.lien"
                    + " from equipe , imagedrapeau"
                    + " WHERE equipe.id_equipe=imagedrapeau.id_equipe and "
                    + "etat=0");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                while (rest.next()) {
                    Equipe e = new Equipe();
                    e.setIdequipe(rest.getInt(1));
                    e.setPays(rest.getString(2));
                    e.setEtat(rest.getInt(3));
                    e.setPhase(rest.getString(4));
                    e.setGroupe(rest.getString(5));
                    e.setSelecteur(rest.getString(6));
                    e.setPoint(rest.getInt(7));
                    e.setImg(new Imagedrapeau(rest.getString(8), null));

                    equipes.add(e);

                }
                for (Equipe e : equipes) {
                    System.out.println(e);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipes;
    }

    @Override
    public List<Equipe> chercherParGroupe(String groupe) {
        
        List<Equipe> equipes = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT `id_equipe`, `pays`, `etat`, `Phase`, `Groupe`, `Selecteur`, `point` "
                 
                    + " from equipe "
                   
                    + "where groupe='"+groupe+"'");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                while (rest.next()) {
                    Equipe e = new Equipe();
                    e.setIdequipe(rest.getInt(1));
                    e.setPays(rest.getString(2));
                    e.setEtat(rest.getInt(3));
                    e.setPhase(rest.getString(4));
                    e.setGroupe(rest.getString(5));
                    e.setSelecteur(rest.getString(6));
                    e.setPoint(rest.getInt(7));
               

                    equipes.add(e);

                }
                for (Equipe e : equipes) {
                    System.out.println(e);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipes;
    }

    @Override
    public List<Equipe> chercherParPhase(String phase) {
        List<Equipe> equipes = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT equipe.`id_equipe`, `pays`, `etat`, `Phase`, `Groupe`, `Selecteur`, `point`, "
                    + "imagedrapeau.lien"
                    + " from equipe , imagedrapeau"
                    + " WHERE equipe.id_equipe=imagedrapeau.id_equipe and "
                    + "phase='"+phase+"'");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                while (rest.next()) {
                    Equipe e = new Equipe();
                    e.setIdequipe(rest.getInt(1));
                    e.setPays(rest.getString(2));
                    e.setEtat(rest.getInt(3));
                    e.setPhase(rest.getString(4));
                    e.setGroupe(rest.getString(5));
                    e.setSelecteur(rest.getString(6));
                    e.setPoint(rest.getInt(7));
                    e.setImg(new Imagedrapeau(rest.getString(8), null));

                    equipes.add(e);

                }
                for (Equipe e : equipes) {
                    System.out.println(e);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipes;
    }

    @Override
    public List<Equipe> selectEquipes() {
        List<Equipe> equipes = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("SELECT equipe.`id_equipe`, `pays`, `etat`, `Phase`, `Groupe`, `Selecteur`, `point`,  imagedrapeau.lien "
                    + "from equipe ,imagedrapeau "
                    + "where equipe.id_equipe = imagedrapeau.id_equipe");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
              
                while (rest.next()) {
                    Equipe e = new Equipe();
                    e.setIdequipe(rest.getInt(1));
                    e.setPays(rest.getString(2));
                    e.setEtat(rest.getInt(3));
                    e.setPhase(rest.getString(4));
                    e.setGroupe(rest.getString(5));
                    e.setSelecteur(rest.getString(6));
                    e.setPoint(rest.getInt(7));
                  

                    equipes.add(e);

                }
                for (Equipe e : equipes) {
                    System.out.println(e);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipes;
    }


    public List<String> selectPays() {
        List<String> pays = new ArrayList<>();

        try {

            ResultSet rest = st.executeQuery("select pays from equipe");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                while (rest.next()) {

                    pays.add(rest.getString(1));

                }
                for (String e : pays) {
                    System.out.println("pays " + e);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pays;
    }


    @Override
    public Equipe AfficherEquipe(int id) { 
        Equipe e = new Equipe();
        try {

            ResultSet rest = st.executeQuery("SELECT equipe.`id_equipe`, `pays`, `etat`, `Phase`, `Groupe`, `Selecteur`, `point`, "
                    + "imagedrapeau.lien"
                    + " from equipe , imagedrapeau"
                    + " WHERE equipe.id_equipe=imagedrapeau.id_equipe and "
                    + " equipe.`id_equipe`=" + id + "");
            if (!rest.next()) {
                System.err.println("Resultat introuvable");
            } else {
                rest.beforeFirst();
                while (rest.next()) {

                    e.setIdequipe(rest.getInt(1));
                    e.setPays(rest.getString(2));
                    e.setEtat(rest.getInt(3));
                    e.setPhase(rest.getString(4));
                    e.setGroupe(rest.getString(5));
                    e.setSelecteur(rest.getString(6));
                    e.setPoint(rest.getInt(7));
                    e.setImg(new Imagedrapeau(rest.getString(8), e));

                }

            }
        }
         catch (SQLException ex) {
            Logger.getLogger(serviceEquipe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return e;
    }
}
