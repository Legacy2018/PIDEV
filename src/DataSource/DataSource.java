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

                  //         Class.forName("com.mysql.jdbc.Driver");


<<<<<<< HEAD

=======
<<<<<<< HEAD
          
=======
<<<<<<< HEAD
>>>>>>> 2d988acd322c59772e3eb310985fbe884c26ac41
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev_v4", "root","");

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev_v2", "root","");
        


>>>>>>> 33ac5287755f2f6ebd8a6d44be5bad186ca1b59f
            
//         Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev_v4", "root","");

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev_v2", "root","");

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