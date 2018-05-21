/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IMatchDAO;
import IServices.IStadeDAO;
import DataSource.DataSource;
import Entities.Equipe;
import Entities.match;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class MatchDAO implements IMatchDAO {

    Statement ste;
    Connection connection;

    public MatchDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    private static IMatchDAO iMatchDao;

    public static IMatchDAO getInstance() {
        if (iMatchDao == null) {
            iMatchDao = (IMatchDAO) new MatchDAO();
        }
        return iMatchDao;
    }

    @Override
    public void ajouterMatch(match match) {

        String requete = "INSERT INTO `matchss` (`id_match`,"
                + "`nom_equipe1`,"
                + "`nom_equipe2`, "
                + "`idequipe1`, "
                + "`idequipe2`, "
                + "`nom_stade`,"
                + "`date`, "
                + " `heure`, "
                + "`phase`) VALUES (?,null,null,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(requete);
            ps.setDate(5,new java.sql.Date(match.getDateMatch().getTime()));
            ps.setString(6, match.getHeureMatch());
            ps.setInt(4, match.getStade().getId_stade());
            ps.setInt(2, match.getEquipe1().getIdEquipe());
            ps.setInt(3, match.getEquipe2().getIdEquipe());
           
           
            ps.setString(7, match.getPhase());
           ps.setInt(1, match.getIdMatch());
           
            ps.executeUpdate();
            System.err.println("service ajouter");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.print("remplir info");
        }
    }

    @Override
    public void modifierMatch(match match) {
        String requete = "update matchss set score=?,set score2?,   where id_match=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
           
            ps.setInt(1,match.getScore());
            ps.setInt(2,match.getScore2());
           
            ps.setInt(3, match.getIdMatch());

            ps.executeUpdate();

        } catch (SQLException ex) {
            //Logger.getLogger(MatchDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    
     @Override
    public void modifierMatchScore(int score,int score2,match match) {
        String requete = "update matchss set score=?,score2=?   where id_match=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
           
            ps.setInt(1, score);
            ps.setInt(2, score2);
           
            ps.setInt(3, match.getIdMatch());

            ps.executeUpdate();

        } catch (SQLException ex) {
            //Logger.getLogger(MatchDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    @Override
    public void modifierDateMatch(String date, match match) {

        String requete = "update matchss set date=?  where id_match=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, date);
            ps.setInt(2, match.getIdMatch());
            ps.executeUpdate();

        } catch (SQLException ex) {
            //Logger.getLogger(MatchDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }

    }

    @Override
    public void supprimerMatch(int id) {
        String requete = "delete from matchss where id_match=?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<match> afficherMatch() {
        List<match> listeMatch = new ArrayList<match>();
        String requete = "select * from matchss";
        try {
            ste = connection.createStatement();
            ResultSet resultat = ste.executeQuery(requete);
            IStadeDAO iStadeDao = DAOStade.getInstance();
            serviceEquipe iEquipeDao = new serviceEquipe();
            

            while (resultat.next()) {
                match match = new match();
                match.setIdMatch(resultat.getInt(1));
                match.setScore(resultat.getInt(7));
                match.setScore2(resultat.getInt(8));
                Equipe e = new Equipe();
            //    e= resultat.getString(4);
                match.setEquipe1(iEquipeDao.AfficherEquipe(resultat.getInt(4)));
                match.setEquipe2(iEquipeDao.AfficherEquipe(resultat.getInt(5)));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    match.setDateMatch(formatter.parse(resultat.getString(9)));
                } catch (ParseException ex) {
                    System.out.println("noaoa");
                }
                match.setStade(iStadeDao.ConsulterstadeId(resultat.getInt(6)));
                match.setHeureMatch(resultat.getString(10));
                match.setPhase(resultat.getString(11));
                System.out.println(match.getHeureMatch());
                
                listeMatch.add(match);
            }
            return listeMatch;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mchkla" + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<match> afficherMatchParPhase(String phase) {
        List<match> listeMatch = new ArrayList<match>();
        String requete = "select * from matchs where phase='" + phase + "';";
        try {
            ste = connection.createStatement();
            ResultSet resultat = ste.executeQuery(requete);
            IStadeDAO iStadeDao = DAOStade.getInstance();
            serviceEquipe iEquipeDao = new serviceEquipe();

            while (resultat.next()) {
              
              match match = new match();
           
                match.setIdMatch(resultat.getInt(1));
                match.setScore(resultat.getInt(7));
                match.setScore2(resultat.getInt(8));
                Equipe e = new Equipe();
            //    e= resultat.getString(4);
                match.setEquipe1(iEquipeDao.AfficherEquipe(resultat.getInt(4)));
                match.setEquipe2(iEquipeDao.AfficherEquipe(resultat.getInt(5)));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    match.setDateMatch(formatter.parse(resultat.getString(9)));
                } catch (ParseException ex) {
                    System.out.println("noaoa");
                }
                match.setStade(iStadeDao.ConsulterstadeId(resultat.getInt(6)));
                match.setHeureMatch(resultat.getString(10));
                match.setPhase(resultat.getString(11));
                
                listeMatch.add(match);
            }
            return listeMatch;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
    }
    
     @Override
    public List<match> afficherMatchParequipe(String nom_equipe) {
        List<match> listeMatch = new ArrayList<>();
        String requete = "select * from matchs where nom_equipe1='" + nom_equipe + "'or nom_equipe2='"+nom_equipe+"';";
        try {
            ste = connection.createStatement();
            ResultSet resultat = ste.executeQuery(requete);
            IStadeDAO iStadeDao = DAOStade.getInstance();
            serviceEquipe iEquipeDao = new serviceEquipe();

            while (resultat.next()) {
              
              match match = new match();
                match.setIdMatch(resultat.getInt(1));
                match.setScore(resultat.getInt(7));
                match.setScore2(resultat.getInt(8));
                Equipe e = new Equipe();
            //    e= resultat.getString(4);
                match.setEquipe1(iEquipeDao.AfficherEquipe(resultat.getInt(4)));
                match.setEquipe2(iEquipeDao.AfficherEquipe(resultat.getInt(5)));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    match.setDateMatch(formatter.parse(resultat.getString(9)));
                } catch (ParseException ex) {
                    System.out.println("noaoa");
                }
                match.setStade(iStadeDao.ConsulterstadeId(resultat.getInt(6)));
                match.setHeureMatch(resultat.getString(10));
                match.setPhase(resultat.getString(11));
              
                
                listeMatch.add(match);
            }
            return listeMatch;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
    }


    @Override
    public List<match> afficherMatchMaxButs() {
        List<match> listeMatch = new ArrayList<match>();
        String requete = "select *, Max(nbButTot) from matchs";
        try {
            ste = connection.createStatement();
            ResultSet resultat = ste.executeQuery(requete);
            IStadeDAO iStadeDao = DAOStade.getInstance();
            serviceEquipe iEquipeDao = new serviceEquipe();

            while (resultat.next()) {
                match match = new match();
                match.setIdMatch(resultat.getInt(1));
                match.setScore(resultat.getInt(7));
                match.setScore2(resultat.getInt(8));
                Equipe e = new Equipe();
            //    e= resultat.getString(4);
                match.setEquipe1(iEquipeDao.AfficherEquipe(resultat.getInt(4)));
                match.setEquipe2(iEquipeDao.AfficherEquipe(resultat.getInt(5)));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    match.setDateMatch(formatter.parse(resultat.getString(9)));
                } catch (ParseException ex) {
                    System.out.println("noaoa");
                }
                match.setStade(iStadeDao.ConsulterstadeId(resultat.getInt(6)));
                match.setHeureMatch(resultat.getString(10));
                match.setPhase(resultat.getString(11));
              
                listeMatch.add(match);
            }
            return listeMatch;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement " + ex.getMessage());
            return null;
        }
    }

    @Override
    public match chercherMatchParId(int id) {
         
        String requete = "select * from matchs where id_match='" + id + "';";
        try {
            ste = connection.createStatement();
            ResultSet resultat = ste.executeQuery(requete);
            IStadeDAO iStadeDao = DAOStade.getInstance();
            serviceEquipe iEquipeDao = new serviceEquipe();
match match = new match();
            while (resultat.next()) {
         
                match.setIdMatch(resultat.getInt(1));
                match.setScore(resultat.getInt(7));
                match.setScore2(resultat.getInt(8));
                Equipe e = new Equipe();
            //    e= resultat.getString(4);
                match.setEquipe1(iEquipeDao.AfficherEquipe(resultat.getInt(4)));
                match.setEquipe2(iEquipeDao.AfficherEquipe(resultat.getInt(5)));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    match.setDateMatch(formatter.parse(resultat.getString(9)));
                } catch (ParseException ex) {
                    System.out.println("noaoa");
                }
                match.setStade(iStadeDao.ConsulterstadeId(resultat.getInt(6)));
                match.setHeureMatch(resultat.getString(10));
                match.setPhase(resultat.getString(11));
              
            }
            return match;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<match> chercherMatchParEquipe(Equipe equipe) {
        List<match> listeMatch = new ArrayList<>();
        String requete = "select * from matchs where id_equipe1='" + equipe.getIdEquipe() + "';";
        try {
            ste = connection.createStatement();
            ResultSet resultat = ste.executeQuery(requete);
            IStadeDAO iStadeDao = DAOStade.getInstance();
            serviceEquipe iEquipeDao = new serviceEquipe();

            while (resultat.next()) {
                match match = new match();
                match.setIdMatch(resultat.getInt(1));
                match.setScore(resultat.getInt(7));
                match.setScore2(resultat.getInt(8));
                Equipe e = new Equipe();
            //    e= resultat.getString(4);
                match.setEquipe1(iEquipeDao.AfficherEquipe(resultat.getInt(4)));
                match.setEquipe2(iEquipeDao.AfficherEquipe(resultat.getInt(5)));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    match.setDateMatch(formatter.parse(resultat.getString(9)));
                } catch (ParseException ex) {
                    System.out.println("noaoa");
                }
                match.setStade(iStadeDao.ConsulterstadeId(resultat.getInt(6)));
                match.setHeureMatch(resultat.getString(10));
                match.setPhase(resultat.getString(11));
              
                listeMatch.add(match);
            }
            return listeMatch;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mchkla2 " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<match> chercherMatchParDate(String date) {
        List<match> listeMatch = new ArrayList<match>();
        String requete = "select * from matchs where date='" + date + "';";
        try {
            ste = connection.createStatement();
            ResultSet resultat = ste.executeQuery(requete);
            IStadeDAO iStadeDao = DAOStade.getInstance();
            serviceEquipe iEquipeDao = new serviceEquipe();

            while (resultat.next()) {
                match match = new match();
                match.setIdMatch(resultat.getInt(1));
                match.setScore(resultat.getInt(7));
                match.setScore2(resultat.getInt(8));
                Equipe e = new Equipe();
            //    e= resultat.getString(4);
                match.setEquipe1(iEquipeDao.AfficherEquipe(resultat.getInt(4)));
                match.setEquipe2(iEquipeDao.AfficherEquipe(resultat.getInt(5)));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    match.setDateMatch(formatter.parse(resultat.getString(9)));
                } catch (ParseException ex) {
                    System.out.println("noaoa");
                }
                match.setStade(iStadeDao.ConsulterstadeId(resultat.getInt(6)));
                match.setHeureMatch(resultat.getString(10));
                match.setPhase(resultat.getString(11));
                listeMatch.add(match);
            }
            return listeMatch;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Poules " + ex.getMessage());
            return null;
        }
    }

   

}
