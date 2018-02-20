/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Iservices.IService;
import Entities.Annonce_collocation;
import Entities.Annonce_covoiturage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author medma
 */
public class CovoiturageService implements IService<Annonce_covoiturage,Integer>{

    private Connection connection;
    private PreparedStatement ps;
    public CovoiturageService() {
        connection=DataSource.DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Annonce_covoiturage t) {
String req = "INSERT INTO `annonce_covoiturage` (`id_annonceur`, `date_depart`, `Date_arrivee`, `adresse_depart`, `adresse_arrivee`, `tari`) "
        + "VALUES (?,?,?,?,?,?)";
System.out.println(req);        
try {System.out.println(req);
            ps = connection.prepareStatement(req);
            ps.setInt(1, t.getId_annonceur());
            ps.setDate(2, t.getDate_depart());
            ps.setDate(3, t.getDate_arrivee());
            ps.setString(4, t.getAdresse_depart());
            ps.setString(5, t.getAdresse_arrivee());
            ps.setDouble(6, (double)t.getTarif());
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
                Ann_cov = new Annonce_covoiturage( rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getFloat(7));
         
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ann_cov;
    }
    
    public List<Annonce_covoiturage> findByIdAnnonceur(Integer r) {
          String req = "select * from Annonce_Covoiturage where id_annonceur = ?";
          List<Annonce_covoiturage> lsa=new ArrayList<>();
        Annonce_covoiturage Ann_cov = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
                Ann_cov = new Annonce_covoiturage( rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getFloat(7));
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
                Annonce_covoiturage Ann_cov = new Annonce_covoiturage( rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getFloat(7));
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
           String req = "update Annonce_Covoiturage set `id_annonceur`=?, `date_depart`=?, `Date_arrivee`=?, `adresse_depart`=?, `adresse_arrivee`=?, `tari` =? where id_annonce = ?";
        Annonce_covoiturage Ann_cov = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(7, t.getId_annonce());
            ps.setInt(1, t.getId_annonceur());
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
    

