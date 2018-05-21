/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static Controllers.Login_viewController.u;
import Iservices.IService;
import Entities.Annonce_covoiturage;
import Entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author medma
 */
public class CovoiturageService implements IService<Annonce_covoiturage,Integer>{

    private Connection connection;
    private PreparedStatement ps;
    public static Annonce_covoiturage DeletedAnnonce = null;
    public CovoiturageService() {
        connection=DataSource.DataSource.getInstance().getConnection();
    }
     public void Signaler(int Annonce){
         String req = "update Annonce_Covoiturage set Signale = Signale + 1 where id_annonce = ?";
      
        int rid = 0;

        try {
            ps = connection.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, Annonce);
            
            ps.executeUpdate();
   
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
     public void SuppressionAutomatique(){
         
         
    String req = "delete from Annonce_Covoiturage where date_depart <'"+java.sql.Date.valueOf(LocalDate.now().format(DateTimeFormatter.ISO_DATE))+"'";
    System.out.println("req"+req);  
    try {
            ps = connection.prepareStatement(req);
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
     
    public void Trigger(){ 
        String req = "select * from Annonce_Covoiturage where id_annonce = ? and Signale > 5";
        
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, u.getId_user());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DeletedAnnonce = new Annonce_covoiturage( rs.getInt(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getFloat(7));
                System.out.println("service"+DeletedAnnonce); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
     String req1 = "delete from Annonce_Covoiturage where Signale > 5 ";
        try {
         
             ps = connection.prepareStatement(req1); 
             ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Annonce_covoiturage t) {
String req = "INSERT INTO `annonce_covoiturage` (`id_annonceur`, `date_depart`, `Date_arrivee`, `adresse_depart`, `adresse_arrivee`, `tari`, `nbrplaces`) "
        + "VALUES (?,?,?,?,?,?,?)";
System.out.println(req);        
try {System.out.println(req);
            ps = connection.prepareStatement(req);
            ps.setInt(1,  t.getUser().getId_user());
            ps.setDate(2, t.getDate_depart());
            ps.setDate(3, t.getDate_arrivee());
            ps.setString(4, t.getAdresse_depart());
            ps.setString(5, t.getAdresse_arrivee());
            ps.setDouble(6, (double)t.getTarif());
            ps.setDouble(7, t.getNbplaces());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Integer r) {
            String req = "delete from Annonce_Covoiturage where id_annonce =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Annonce_covoiturage findById(Integer r) {
          String req = "select * from Annonce_Covoiturage where id_annonce = ?";
        Annonce_covoiturage Ann_cov = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ann_cov = new Annonce_covoiturage( rs.getInt(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getFloat(7));
                Ann_cov.setNbplaces(Integer.parseInt(rs.getString(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ann_cov;
    }
    
    public List<Annonce_covoiturage> findByIdAnnonceur(Utilisateur u) {
          String req = "select * from Annonce_Covoiturage where id_annonceur = ?";
          List<Annonce_covoiturage> lsa=new ArrayList<>();
        Annonce_covoiturage Ann_cov = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, u.getId_user());
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
                Ann_cov = new Annonce_covoiturage( rs.getInt(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getFloat(7));
                  Ann_cov.setNbplaces(Integer.parseInt(rs.getString(9)));
                lsa.add(Ann_cov);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsa;
    }
    

    @Override
    public List<Annonce_covoiturage> getAll() {
            String req = "select * from Annonce_Covoiturage";
        List<Annonce_covoiturage> Annonces= new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur utilisateur=new Utilisateur();
                utilisateur.setId_user(rs.getInt(1));
                Annonce_covoiturage Ann_cov = new Annonce_covoiturage( utilisateur,rs.getInt(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getFloat(7));
                  Ann_cov.setNbplaces(Integer.parseInt(rs.getString(9)));
                Annonces.add(Ann_cov);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Annonces;
    }


    @Override
    public void edit(Annonce_covoiturage t) {
         System.out.println("hellloooooo111111");
           String req = "update Annonce_Covoiturage set `id_annonceur`=?, `date_depart`=?, `Date_arrivee`=?, `adresse_depart`=?, `adresse_arrivee`=?, `tari` =?, `nbrplaces`=? where id_annonce = ?";
        Annonce_covoiturage Ann_cov = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(8, t.getId_annonce());
            ps.setInt(7, t.getNbplaces());
            ps.setInt(1,  t.getUser().getId_user());
            ps.setDate(2, t.getDate_depart());
            ps.setDate(3, t.getDate_arrivee());
            ps.setString(4, t.getAdresse_depart());
            ps.setString(5, t.getAdresse_arrivee());
            ps.setDouble(6, (double)t.getTarif());
            ps.executeUpdate();
            System.out.println("hellloooooo");
             ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
    

