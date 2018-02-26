/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Fos_User;
import Entities.Ticket;
import Entities.TicketRate;
import IServices.ITicketRate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BSS
 */
public class ServiceRate implements ITicketRate {

    private Connection connection;
    private PreparedStatement ps;

    public ServiceRate() {
        connection = DataSource.DataSource.getInstance().getConnection();

    }

    @Override
    public void Update(TicketRate t) {
        String req = "update `rate` set `nbrRate`=?  where idRate=?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(2, t.getIdRate());
            ps.setDouble(1, t.getNbRate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void add(TicketRate t) {
        String req = "insert into rate (nbrRate,idUser,idTicket) values (?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setDouble(1, t.getNbRate());
            ps.setInt(2, t.getIdUser().getId());
            ps.setInt(3, t.getIdTicket().getIdTicket());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Integer idRate) {
        String req = "delete from rate where idRate =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idRate);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public TicketRate findById(Integer idTicket) {
        String req = "select * from rate where idTicket = ?";
        TicketRate rate = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idTicket);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                rate = new TicketRate(resultSet.getInt(1), resultSet.getDouble(2), new Fos_User(resultSet.getInt(4)), new Ticket(resultSet.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rate;
    }
    
    public List<TicketRate> getAll() {
        String req = "select * from rate";
        List<TicketRate> listRate = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                TicketRate rate = new TicketRate(resultSet.getInt(1), resultSet.getDouble(2), new Fos_User(resultSet.getInt(4)), new Ticket(resultSet.getInt(3)));
                listRate.add(rate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRate;
    }
    
    public Double Moyenne(Integer idTicket) {
        String req = "SELECT ROUND(AVG(nbrRate),2) FROM rate where idTicket=?";
        TicketRate rate = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idTicket);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                // rate= new Rate( resultSet.getInt(1),resultSet.getDouble(2), new User(resultSet.getInt(4)),new Annonce(resultSet.getInt(3)));
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
        }
        return 0.0;
    }
    
    

}
