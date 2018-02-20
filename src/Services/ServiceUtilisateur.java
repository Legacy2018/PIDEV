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
import entites.Utilisateur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableListBase;

/**
 *
 * @author Katouchi
 */
public class ServiceUtilisateur extends ServiceFos_User {


    public ServiceUtilisateur() {
         cnx = (Connection) DataSource.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
           
        }
    }
    public void ajouter(Utilisateur u)
    {
        
         try {
            String req = " INSERT INTO utilisateur  "
                    + "VALUES ("+GetAnFos_User(new Fos_User(u.getId(), u.getUsername(), u.getEmail(), u.getPassword(), false, false, "user"))+", '"+u.getPosition()+"', '"+u.getTelephone()+"',"+u.isFumeur()+",'"+u.getNom()+"','"+u.getPnom()+"',null);";
             System.out.println(req);
            st.executeUpdate(req);
          
         } catch (SQLException ex) {
             System.out.println(ex);
        }
    }
    public Utilisateur findUtilisateur(String login)
    {
        Fos_User fu=findFos_User(login);
        if(fu==null)
        {
          
            return null;
        }
        else
        {
             try
        { ResultSet res= st.executeQuery("Select * from utilisateur where id='"+fu.getId()+"';");
          if(res.next())
          {
              return new Utilisateur(res.getInt("id"), res.getString("position"), res.getString("telephone"), false, res.getString("nom"),res.getString("prenom") , res.getString("date_de_naissances"),fu);
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
    }
    public boolean MailExiste(String mail)
    {
         try
        { ResultSet res= st.executeQuery("Select * from Fos_user where email='"+mail+"';");
             return res.next();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
        
        
       
    }
    public boolean UsernameExiste(String username)
    {
         try
        { ResultSet res= st.executeQuery("Select * from Fos_user where username like '"+username+"';");
             return res.next();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
        
        
       
    }
     public boolean TelephoneExiste(String numero)
    {
         try
        { ResultSet res= st.executeQuery("Select * from Utilisateur where telephone='"+numero+"';");
             return res.next();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
        
        
       
    }
     
    public List<Utilisateur> getall() 
    {   List<Utilisateur> us=new ArrayList<>();
         try
        {  
            ResultSet res= st.executeQuery("Select username from fos_user;");
            while(res.next())
            {
                us.add(new ServiceUtilisateur().findUtilisateur(res.getString("username")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return us;
       
    }
    public void suprimer(int id)
    {
          try
        {   
            
            st.executeUpdate("DELETE FROM utilisateur WHERE id = "+id);
            suprimerfos(id);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void update(String column,String val,int id)
    {
       
         try
        {   
            
            st.executeUpdate(" UPDATE utilisateur SET "+column+" = '"+val+"' WHERE id ="+id);
            
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
}
