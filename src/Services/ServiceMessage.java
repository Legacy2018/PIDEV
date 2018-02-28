/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataSource.DataSource;
import Entities.Messages;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Katouchi
 */
public class ServiceMessage {
     protected DataSource db;
    protected Connection cnx;
    protected Statement st;

    public ServiceMessage() {
      cnx = (Connection) DataSource.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    public Messages getNewMessages(int sender,int recever){
        Messages m=null;
         try {
            ResultSet res= st.executeQuery("Select * from Messages where id_recever="+recever+" and afficher=1 ;");
            if(res.next()){
                try {
                    m= new Messages(res.getInt("id_sender"), res.getInt("id_recever"), res.getString("msg"), res.getInt("id"), true,res.getDate("datemsg"));
                    System.out.println(m.getDatemsg());
                } catch (NullPointerException e) {
                    System.out.println(e);
                }
             
            st.executeUpdate(" UPDATE Messages SET afficher = 0 WHERE id ="+res.getInt("id"));
            }
            
        } catch (SQLException e) {
        }
         
         return m;
    }
    public List<Messages> getAllMessages(int recever){
         List<Messages> m =new ArrayList<>();
         try {
            ResultSet res= st.executeQuery("Select * from Messages where id_recever="+recever+" or id_sender="+recever+";");
            while(res.next()){
           
            m.add(new Messages(res.getInt("id_sender"), res.getInt("id_recever"), res.getString("msg"), res.getInt("id"), true,res.getDate("datemsg"))); 
            
            }
            
        } catch (SQLException e) {
        }
         
         return m;
    }
    public void sendMessage(Messages m){
        try {
            String req = " INSERT INTO Messages (id, id_sender, id_recever, msg , afficher,datemsg) "
                    + "VALUES (NULL, " +m.getSender()+ ", "+m.getRecever()+", '"+m.getMessage()+"' , 1,CURRENT_TIMESTAMP);";

            st.executeUpdate(req);
         
         } catch (SQLException ex) {
             System.out.println(ex);
        }
    }
}
    
