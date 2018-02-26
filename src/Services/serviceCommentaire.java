/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controllers.Login_viewController;
import static Controllers.Login_viewController.u;
import DataSource.DataSource;
import IServices.Icommentaire;
import com.mysql.jdbc.Statement;
import Entities.Commentaire;
import Entities.Fos_User;
import Entities.Ticket;
import Entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BSS   DataSource db;
    Connection cnx;
    Statement st;
 */
public class serviceCommentaire implements Icommentaire{
    private Connection connection;
    private PreparedStatement ps;

    public serviceCommentaire() {
    
    
    connection=DataSource.getInstance().getConnection();
    }
    


    @Override
    public void add(Commentaire t) {
        String req = "INSERT INTO `commentaire` ( `idticket`,`description`, `idUser`) VALUES (?,?,?)";
        try {
            ps = connection.prepareStatement(req);
           // ps.setInt(1, );
            ps.setString(2, t.getDescription());
            ps.setInt(1, t.getIdticket().getIdTicket());
            ps.setInt(3, Login_viewController.u.getId());
           
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Commentaire t) {
String req = "update `commentaire` set `description`=?  where idCommentaire=?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(2, t.getIdCommentaire());
            ps.setString(1, t.getDescription());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    @Override
    public void delete(Integer idCommentaire) {
String req = "delete from commentaire where idCommentaire =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idCommentaire);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
    

    @Override
    public Commentaire findById(Integer idCommentaire) {
String req = "select * from commentaire where idCommentaire= ?";
        Commentaire commentaire = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idCommentaire);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                commentaire= new Commentaire( resultSet.getString(3),new Ticket(resultSet.getInt(2)),new Fos_User(resultSet.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentaire;    
    }

    @Override
    public List<Commentaire> getAll(int i) {
String req = "select * from commentaire where idticket=? ";
        List<Commentaire> listCommentaire = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
           ps.setInt(1, i);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Commentaire commentaire= new Commentaire( resultSet.getInt(1),resultSet.getString(3),new Ticket(resultSet.getInt(2)),new Fos_User(resultSet.getInt(4)));
                listCommentaire.add(commentaire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCommentaire;   
    }


}
