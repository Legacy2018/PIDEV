/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Iservices.IService;
import Entities.Annonce_collocation;
import Entities.Annonce_covoiturage;
import Entities.UploadImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author medma
 */
public class CollocationService implements IService<Annonce_collocation,Integer> {
private Connection connection;
    private PreparedStatement ps;
private PreparedStatement ps1;
    public CollocationService() {
        connection=DataSource.DataSource.getInstance().getConnection();
    }
    
    
    @Override
    public void add(Annonce_collocation t) {
        String req = "insert into annonce_collocation (id_annonceur, titre_annonce, Description, tarif, adresse) values (?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, t.getId_annonceur());
            ps.setString(2, t.getTitre_annonce());
            ps.setString(3, t.getDescription());
            ps.setDouble(4, (double)t.getTarif());
            ps.setString(5, t.getAdresse());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addWithMedia(Annonce_collocation t) {
        int rid = 0;
        String req = "insert into Annonce_Collocation (id_annonceur, titre_annonce, Description, tarif, adresse, datedebut, datefin) values (?,?,?,?,?,?,?)";
        String req1="insert into UploadImage (id_annonce, media_link) values (?,?)";
        try {
            ps = connection.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, t.getId_annonceur());
            ps.setString(2, t.getTitre_annonce());
            ps.setString(3, t.getDescription());
            ps.setDouble(4, (double)t.getTarif());
            ps.setString(5, t.getAdresse());
            ps.setDate(6, t.getDatedebut());
            ps.setDate(7, t.getDatedebut());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
    if(rs.next()) {
       rid=rs.getInt(1); 
      //what you get is only a RowId ref, try make use of it anyway U could think of
     
    }

        } catch (Exception e) {
            e.printStackTrace();
        }
         try {
             for(int i=0;i<t.getUi().size();i++){
            ps = connection.prepareStatement(req1);
            ps.setInt(1,rid);
            ps.setString(2, t.getUi().get(i).getMedia_link());
            ps.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  public void deleteMedia(Integer r) {
        
        String req1 = "delete from UploadImage where id_annonce =?";
        try {
         
            
         
             ps = connection.prepareStatement(req1);
             ps.setInt(1, r);
             ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Integer r) {
        String req = "delete from Annonce_Collocation where id_annonce =?";
        String req1 = "delete from UploadImage where id_annonce =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
           
            
            ps.executeUpdate();
             ps = connection.prepareStatement(req1);
             ps.setInt(1, r);
             ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Annonce_collocation> findByIdAnnonceur(Integer r) {
        String req = "select * from annonce_collocation where id_annonceur = ?";
        String req1 = "select * from uploadimage where id_annonce = ?";
        List<UploadImage> Images= new ArrayList<>();
        
        List<Annonce_collocation> Annonces= new ArrayList<>();

        try {
             ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                
               Annonce_collocation Ann_coll = new Annonce_collocation( rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getString(6),rs.getDate(7),rs.getDate(8));
              int resultat=rs.getInt(2);
                ps1 = connection.prepareStatement(req1);
           
                ps1.setInt(1, resultat);
               // System.out.println( "++++++++++++++++++++++++++++++++"+Ann_coll);
             ResultSet rs1 = ps1.executeQuery();
             
            while (rs1.next()) {
              UploadImage  UI = new UploadImage( rs1.getInt(1),rs1.getInt(2),rs1.getString(3));
             // System.out.println( "----------------"+Ann_coll);
                //Images.add(UI);
                if(UI!=null)
                Ann_coll.getUi().add(UI);
            }
            //Ann_coll.setUi(Images);
            Annonces.add(Ann_coll);
                //System.out.println(Ann_coll);
                //Images.clear();
                //System.out.println(Annonces); 

              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       return Annonces;
    }
    
    @Override
    public Annonce_collocation findById(Integer r) {
        String req = "select * from Annonce_Collocation where id_annonce = ?";
        String req1 = "select * from UploadImage where id_annonce = ?";
        List<UploadImage> Images= new ArrayList<>();
        Annonce_collocation Ann_coll = null;
        UploadImage UI=null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ann_coll = new Annonce_collocation( rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getString(6),rs.getDate(7),rs.getDate(8));
                
            }
            
            
            ps = connection.prepareStatement(req1);
            ps.setInt(1, r);
            
             ResultSet rs1 = ps.executeQuery();
            if (rs1.next()) {
                UI = new UploadImage( rs1.getInt(1),rs1.getInt(2),rs1.getString(3));
                Images.add(UI);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       // Ann_coll.setUi(Images);
        return Ann_coll;
    }
     
    @Override
    public List<Annonce_collocation> getAll() {
       String req = "select * from annonce_collocation";
        String req1 = "select * from uploadimage where id_annonce = ?";
        List<UploadImage> Images= new ArrayList<>();
        
        List<Annonce_collocation> Annonces= new ArrayList<>();

        try {
            Statement stmt= connection.createStatement();
            ResultSet rs = stmt.executeQuery(req);
           
            while (rs.next()) {
                
               Annonce_collocation Ann_coll = new Annonce_collocation( rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getString(6),rs.getDate(7),rs.getDate(8));
              int resultat=rs.getInt(2);
                ps1 = connection.prepareStatement(req1);
           
                ps1.setInt(1, resultat);
               // System.out.println( "++++++++++++++++++++++++++++++++"+Ann_coll);
             ResultSet rs1 = ps1.executeQuery();
             
            while (rs1.next()) {
              UploadImage  UI = new UploadImage( rs1.getInt(1),rs1.getInt(2),rs1.getString(3));
             // System.out.println( "----------------"+Ann_coll);
                //Images.add(UI);
                if(UI!=null)
                Ann_coll.getUi().add(UI);
            }
            //Ann_coll.setUi(Images);
            Annonces.add(Ann_coll);
                //System.out.println(Ann_coll);
                //Images.clear();
                //System.out.println(Annonces); 

              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return  Annonces;
    }

    @Override
    public void edit(Annonce_collocation t) {
        String req = "update Annonce_Collocation set id_annonceur =?, titre_annonce =?, Description =?, tarif =?, adresse =?, datedebut=?, datefin =? where id_annonce = ?";
        String req2="insert into UploadImage (id_annonce, media_link) values (?,?)";
        int rid = 0;

        try {
            ps = connection.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, t.getId_annonceur());
            ps.setString(2, t.getTitre_annonce());
            ps.setString(3, t.getDescription());
            ps.setDouble(4, (double)t.getTarif());
            ps.setString(5, t.getAdresse());
            ps.setDate(6, t.getDatedebut());
            ps.setDate(7, t.getDatedebut());
             ps.setInt(8, t.getId_annonce());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
    if(rs.next()) {
       rid=rs.getInt(1); 
      //what you get is only a RowId ref, try make use of it anyway U could think of
     
    }

        } catch (Exception e) {
            e.printStackTrace();
        }
         try {deleteMedia(t.getId_annonce());
             for(int i=0;i<t.getUi().size();i++){
            ps = connection.prepareStatement(req2);
            ps.setInt(1,t.getId_annonce());
            ps.setString(2, t.getUi().get(i).getMedia_link());
            ps.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }    }

    

  
 
    }
    

