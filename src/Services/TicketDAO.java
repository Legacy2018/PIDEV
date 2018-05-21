package Services;

import IServices.ITicketDAO;
import Entities.Fos_User;
import Entities.Ticket;
import Entities.match;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Services.ServiceUtilisateur;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDAO  {

    Connection connection;
    private PreparedStatement ps;
    Statement ste;

    public TicketDAO() {
        connection = DataSource.DataSource.getInstance().getConnection();
    }

    private static ITicketDAO iTicketDao;

    public static ITicketDAO getInstance() {
        if (iTicketDao == null) {
            //iTicketDao = new TicketDAO();
        }
        return iTicketDao;
    }

    //@Override
    public void ajouter_Ticket(Ticket ticket) {

        String requete = "INSERT INTO `ticket`  VALUES (?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(requete);
             ps.setInt(1, ticket.getIdUser().getId());
            ps.setString(2, ticket.getCategories());
            ps.setInt(3, ticket.getNbrTicket());
            ps.setFloat(4, ticket.getPrix());
            ps.setFloat(5,1);
            ps.setInt(6, ticket.getIdMatch().getIdMatch());
         

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //@Override
    public List<Ticket> afficher_Ticket() {

        List<Ticket> listTicket = new ArrayList<>();
        String requete = "select * from ticket";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {

                Ticket t = new Ticket();
                t.setIdTicket(resultat.getInt(1));
               t.setIdUser(new Services.ServiceFos_User().findFos_UserbyID(resultat.getInt(2)));
                t.setCategories(resultat.getString(3));
                t.setPrix(resultat.getFloat(5));
                 t.setNbrTicket(resultat.getInt(4));
                 t.setHeurAjout(resultat.getDate(6));
          
     // ps = connection.prepareStatement(requete2);
//
           // ps.setInt(1,resultat.getInt(5));
             //    ResultSet resultSet2 = ps.executeQuery();
               t.setIdMatch(new MatchDAO().chercherMatchParId(resultat.getInt(8)));

               // t.setIdMatch(new Match (resultSet2.getInt(1),resultSet2.getString(5),resultSet2.getString(7),resultSet2.getString(6),resultSet2.getString(3),resultSet2.getString(4)));
             
                //System.out.println(t);
                listTicket.add(t);
            }
            return listTicket;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }

    }

   // @Override
    public void supprimerTicket(int id) {
        String requete = "DELETE FROM `ticket` WHERE `id_ticket`=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

   // @Override
    public void modifierTicket( Ticket ticket) {
        String requete = "UPDATE `ticket` SET `categories` =?, `NbTicket` =?, `prix` =?, `idMatch` =?, `id_annonceur` =? WHERE id_ticket=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(6, ticket.getIdTicket());
            ps.setString(1, ticket.getCategories());
            ps.setInt(2, ticket.getNbrTicket());
            ps.setFloat(3, ticket.getPrix());
            ps.setInt(4, ticket.getIdMatch().getIdMatch());
            ps.setInt(5, ticket.getIdUser().getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    //@Override
    public Ticket chercherTicketParId(int id) {
        String req = "select * from ticket where id_ticket =?";
        Ticket ticket = null;
        try {
            PreparedStatement ps;
            ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                ticket = new Ticket(resultSet.getInt(1), resultSet.getInt(3), resultSet.getString(2), resultSet.getFloat(4), new Fos_User(resultSet.getInt(6)), new match((resultSet.getInt(5))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }
    
  //  @Override
    public List<Ticket> chercherTicketMatch(match Match) {
        List<Ticket> listTicket = new ArrayList<>();
        String requete = "select * from ticket where ticket.idMatch='" + Match.getIdMatch() + "';";
        try {
             ps = connection.prepareStatement(requete);
       //  String recherche =null;
           ps.setInt(1, Match.getIdMatch());
          // s.setInt(2, i);
            ResultSet resultSet = ps.executeQuery();   
            
            while (resultSet.next()) {
               Ticket ticket = new Ticket(resultSet.getInt(1), resultSet.getInt(3), resultSet.getString(2), resultSet.getFloat(4), new Fos_User(resultSet.getInt(6)), new match((resultSet.getInt(5))));
 
                listTicket.add(ticket);
               // System.out.println(listTicket);
            }
            return listTicket;
        } catch (SQLException ex) {
            //Logger.getLogger(ProjetDao.class.getName()).log(Level.SEVERE, null, ex);
           // System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }

    /*  @Override
    public int nbTicketAcheteParMatch(match Matche) {
    int nb=-1;
        String requete = "select count(*) from ticket where ticket.id_match= '" + Matche.getId_match() + "';";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                nb=resultat.getInt(1);
            }
            return nb;
        } catch (SQLException ex) {
            //Logger.getLogger(ProjetDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return -1;
        }
    }*/
 /*@Override
    public List<Ticket> chercherTicketParCategorie(String categorie) {

        List<Ticket> listTicket = new ArrayList<>();
        String requete = "select * from ticket where categories'" + categorie + "';";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            IMatchDAO mdao = MatchDAO.getInstance();
            IUserDAO udao = UserDAO.getInstance();
            while (resultat.next()) {
                Ticket t = new Ticket();
                t.setIdTicket(resultat.getInt(1));
                t.setNbTicket(resultat.getInt(2));
                t.setNbTicket(resultat.getInt(3));
                //t.setUser(udao.chercherUserParId(resultat.getInt(4)));
                //  t.setMatch(mdao.chercherMatchParId(resultat.getInt(5)));
                listTicket.add(t);
            }
            return listTicket;
        } catch (SQLException ex) {
            //Logger.getLogger(ProjetDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }*/
   /* public int nbTicketAcheteParMatch(Match matche) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

 //   @Override
      public List<Ticket>  rechercherTicket  (String rechercher) {
     List<Ticket> listeTicket= new ArrayList<>();
       List<Ticket> listeTicket2= new ArrayList<>();
      // Ticket ticket=new Ticket();
   listeTicket=afficher_Ticket();
     for (Ticket gr: listeTicket){
     //System.out.println(listeTicket);
      String req ="select * from ticket t inner join matchss m where t.categories like '%"+rechercher+"%' or m.nom_equipe1 like '%"+rechercher+"%' or m.Nom_equipe2 like '%"+rechercher+"%' and t.id_ticket=? and m.id_match=?";  
    //  String req ="select * from ticket t inner join  matchs m where t.prix like '%"+rechercher+"%' or t.categories like '%"+rechercher+"%'  and t.id_ticket=? and m.id_match=?";  
  
//or m.equipe1 like '%"+rechercher+"%' or m.equipe2 like '%"+rechercher+"%' or m.heure like '%"+rechercher+"%'
        try {
            ps = connection.prepareStatement(req);
       //  String recherche =null;
           ps.setInt(1, gr.getIdTicket());
            ps.setInt(2, gr.getIdMatch().getIdMatch());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
 Ticket ticket = new Ticket(resultSet.getInt(1), resultSet.getInt(3), resultSet.getString(2), resultSet.getFloat(4), new Fos_User(resultSet.getInt(6)), new match((resultSet.getInt(5))));
                listeTicket2.add(ticket);
                
                   //System.out.println(listeTicket2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
          //System.out.println(listeTicket2);
     } 
      return listeTicket2;
        
     //  System.out.println(listeTicket);
     
      }

   // @Override
    public int nbTicketAcheteParMatch(match Matche) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  //  @Override
    public List<Ticket> chercherTicketParCategorie(String categorie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
