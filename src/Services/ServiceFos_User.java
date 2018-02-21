/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DataSource.DataSource;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import entites.Fos_User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Katouchi
 */
public class ServiceFos_User {
    protected DataSource db;
    protected Connection cnx;
    protected Statement st;

    public ServiceFos_User() {
      cnx = (Connection) DataSource.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    public int GetAnFos_User( Fos_User fu) 
    {
         try {
            String req = " INSERT INTO fos_user (id, username, email, password, enabled , confirmation_token,roles) "
                    + "VALUES (NULL, '" +fu.getUsername()+ "', '"+fu.getEmail()+"', '"+fu.getPassword()+"' ,0,'0','user');";

            st.executeUpdate(req);
           ResultSet res= st.executeQuery("Select id from fos_User where email='"+fu.getEmail()+"';");
           if(res.next());
           fu.setId(res.getInt("id"));
         } catch (SQLException ex) {
             System.out.println(ex);
        }
         
         return fu.getId();
    }
    
    public Fos_User findFos_User(String login) 
    {
        try
        { ResultSet res= st.executeQuery("Select * from fos_User where email='"+login+"' or username='"+login+"';");
          if(res.next())
          {
              return new Fos_User(res.getInt("id"), res.getString("username"), res.getString("email"), res.getString("password"), res.getBoolean("enabled"), res.getBoolean("enabled"), res.getString("roles"));
              
          }
          else
          {   
              return null;
          }
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public Fos_User findFos_UserbyID(int id) 
    {
        try
        { ResultSet res= st.executeQuery("Select * from fos_User where id="+id+";");
          if(res.next())
          {
              return new Fos_User(res.getInt("id"), res.getString("username"), res.getString("email"), res.getString("password"), res.getBoolean("enabled"), res.getBoolean("enabled"), res.getString("roles"));
              
          }
          else
          {   
              return null;
          }
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
     public void suprimerfos(int id)
    {
          try
        {   
            st.executeUpdate("DELETE FROM fos_user WHERE id = "+id);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
}
