/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DataSource.DataSource;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import Entities.Reclamation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Katouchi
 */
public class ServiceReclamation {
     protected DataSource db;
    protected Connection cnx;
    protected Statement st;
    public ServiceReclamation() {
      cnx = (Connection) DataSource.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            
        }
        
    }
    public void supprimer(int id)
    {
         try
        {   
            
            st.executeUpdate("DELETE FROM reclamation WHERE id = "+id);
            
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void posterReclamation(Reclamation r)
    {
          try {
            String req = " INSERT INTO Reclamation (id, id_user, sujet, message) "
                    + "VALUES (NULL, '" +r.getId_user()+ "', '"+r.getSujet()+"', '"+r.getMessage()+"');";

            st.executeUpdate(req);
         
         } catch (SQLException ex) {
             System.out.println(ex);
        }
         
    }
    public List<Reclamation> getAllRec()
    {  List<Reclamation> recs=new ArrayList<>();
        try {
            ResultSet res= st.executeQuery("Select * from reclamation;");
            while(res.next())
                recs.add(new Reclamation(res.getInt("id"), res.getInt("id_user"), res.getString("sujet"), res.getBoolean("lu"), res.getString("message"), null));
        } catch (SQLException e) {
        }
        return recs;
    }
    public void marquerlu(int id)
    {
          try
        {   
            
            st.executeUpdate(" UPDATE reclamation SET lu = 1 WHERE id ="+id);
            
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public List<Reclamation> getuserRec(int id)
    {
        List<Reclamation> rec=new ArrayList<>();
        try {
            ResultSet res= st.executeQuery("Select * from reclamation where id_user="+id+";");
            while(res.next())
                rec.add(new Reclamation(res.getInt("id"), res.getInt("id_user"), res.getString("sujet"), res.getBoolean("lu"), res.getString("message"), null));
        } catch (SQLException e) {
        }
        return rec;
    }
}
