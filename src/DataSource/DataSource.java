/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.sql.DataSource;

/**
 *
 * @author Emel
 */
public class DataSource {
     private Connection con ;
     private static DataSource data;

    private DataSource() {
        try {

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev_v6", "root","");



            


            System.out.println("connexion Ok ! !");
        } catch (SQLException x) {
            
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE , null , x);
            // handle the error
            
        }
    }

    public Connection getConnection() {
        return con;
    }
    
    public static DataSource getInstance ()
    {
    if (data == null)
            data = new DataSource();
    return data ;
        }
     
}