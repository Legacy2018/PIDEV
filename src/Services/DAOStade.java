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
import Entities.stade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOStade implements IStadeDAO {

    
    
    Statement ste;
    Connection connection;
    public DAOStade(){
    connection= DataSource.getInstance().getConnection();
    }
    
 
    
    private static IStadeDAO iStadeDao;

    public static IStadeDAO getInstance() {
        if (iStadeDao == null) {
            iStadeDao= (IStadeDAO) new DAOStade();
        }
        return  iStadeDao;
    }
    
   

    @Override
    public List<stade> ConsulterStadeList() {
         List<stade> listeStades = new ArrayList<>();

        String requete = "select * from stade";
        try {
            Statement statement = connection.createStatement();
                    
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                stade s1 = new stade();
                s1.setId_stade(resultat.getInt(1));
                s1.setNom_Stade(resultat.getString(2));
                s1.setVille(resultat.getString(3));
                s1.setCapacité(resultat.getInt(4));

               listeStades.add(s1);

            }
            return listeStades;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur " + ex.getMessage());
            return null;
        }
    }

    @Override
    public stade ConsulterstadeId(int id) {
     stade s = new stade();

        String requete = "select * from stade where id='" + id + "';";
        try {
            Statement statement = connection.createStatement();
                    
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
               
                s.setId_stade(resultat.getInt(1));
                s.setNom_Stade(resultat.getString(2));
                s.setVille(resultat.getString(3));
                s.setCapacité(resultat.getInt(4));

               

            }
         return s;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur " + ex.getMessage());
            return null;
        }
    }
     @Override
    public stade Consulterstade(String nom) {
     stade s = new stade();

        String requete = "select * from stade where nom='" + nom + "';";
        try {
            Statement statement = connection.createStatement();
                    
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
               
                s.setId_stade(resultat.getInt(1));
                s.setNom_Stade(resultat.getString(2));
                s.setVille(resultat.getString(3));
                s.setCapacité(resultat.getInt(4));

               

            }
         return s;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur " + ex.getMessage());
            return null;
        }
    }
    
     @Override
    public String ConsulterstadeNom(String nom) {
     stade s = new stade();

        String requete = "select * from stade where nom='" + nom + "';";
        try {
            Statement statement = connection.createStatement();
                    
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
               
               
                s.setNom_Stade(resultat.getString(2));
               

               

            }
         return s.getNom_Stade();
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur " + ex.getMessage());
            return null;
        }
    }
       
    }

   
    
    

