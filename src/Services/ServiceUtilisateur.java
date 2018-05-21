/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataSource.DataSource;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import Entities.Fos_User;
import Entities.Utilisateur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableListBase;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public static String get_SHA_512_SecurePassword(String passwordToHash, String   salt){
        String generatedPassword = null;
        try {
             MessageDigest md = MessageDigest.getInstance("SHA-512");
             md.update(salt.getBytes(StandardCharsets.UTF_8));
             byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
             StringBuilder sb = new StringBuilder();
             for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
             }
             generatedPassword = sb.toString();
            } 
           catch (NoSuchAlgorithmException e){
            e.printStackTrace();
           }
        return generatedPassword;
    }
    public void ajouter(Utilisateur u)
    {System.out.println("ajouta user");
        
         try {
            String req = " INSERT INTO utilisateur  "
                    + "VALUES ("+GetAnFos_User(new Fos_User(u.getId(), u.getUsername(), u.getEmail(), u.getPassword(), false, false, "user",getNextSalt().toString()))+", '"+u.getPosition()+"', '"+u.getTelephone()+"',"+u.isFumeur()+",'"+u.getNom()+"','"+u.getPnom()+"',null,'"+u.getImg_profile()+"',"+u.getNum_confirm()+");";
             System.out.println(u.getImg_profile());
            st.executeUpdate(req);
          
         } catch (SQLException ex) {
             System.out.println("hoooooooooooooooooooooouubbbnnni"+ex);
        }
    }
    public Utilisateur findUtilisateur(String login)
    {
        Fos_User fu=findFos_User(login);
        System.out.println("fos_user  "+fu);
        if(fu==null)
        {
            System.out.println("hetha");
          
            return null;
        }
        else
        {
             try
        { ResultSet res= st.executeQuery("Select * from utilisateur where id='"+fu.getId()+"';");
          if(res.next())
          { 
              return new Utilisateur(res.getInt("id"), res.getString("position"), res.getString("telephone"), false, res.getString("nom"),res.getString("prenom") , res.getString("date_de_naissances"),fu,res.getString("Img_profile"),res.getInt("Numdeconfirmation"));
          }
          else
          { 
              return new Utilisateur(fu.getId(), "null", "Mazel", true, "Ma yekhdemesh", "Ma yekhdemesh", "mayekhdemesh", fu,"http://Ressource-Pidev/temoin-de-mariage-toulouse-magasin-robe-de-maric3a9e-toulouse-pas-cher-robe-de-marie-toulouse-pas-cher.jpg" , 0);
          }
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
        }
    }
    public Utilisateur findUtilisateurbyID(int id)
    {
        Fos_User fu=findFos_UserbyID(id);
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
              return new Utilisateur(res.getInt("id"), res.getString("position"), res.getString("telephone"), false, res.getString("nom"),res.getString("prenom") , res.getString("date_de_naissances"),fu,res.getString("Img_profile"),res.getInt("Numdeconfirmation"));
          }
          else
          { System.out.println("step 3");
              return new Utilisateur(fu.getId(), "null", "Mazel", true, "Ma yekhdemesh", "Ma yekhdemesh", "mayekhdemesh", fu,"http://Ressource-Pidev/temoin-de-mariage-toulouse-magasin-robe-de-maric3a9e-toulouse-pas-cher-robe-de-marie-toulouse-pas-cher.jpg" , 0);
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
    
    public List<Utilisateur> getallfiltred(String column,String val) 
    {   List<Utilisateur> us=new ArrayList<>();
    ResultSet res;
         try
        {  if(!column.equals("username"))
            res= st.executeQuery("Select * from Utilisateur where "+column+" like '"+val+"%';");
        else
        {
            System.out.println("Step 2");
            res= st.executeQuery("Select * from fos_User where "+column+" like '"+val+"%';");
        }
            
            while(res.next())
            { System.out.println("Step 3");
                us.add(new ServiceUtilisateur().findUtilisateurbyID(res.getInt("id")));
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
