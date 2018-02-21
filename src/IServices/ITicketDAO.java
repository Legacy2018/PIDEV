package IServices;

import Entities.match;
import entities.Ticket;
import java.util.List;

public interface ITicketDAO {

    public void ajouter_Ticket(Ticket t);

    public List<Ticket> afficher_Ticket();

    public void supprimerTicket(int id);

    public void modifierTicket(Ticket ticket);

    public Ticket chercherTicketParId(int id);

    public int nbTicketAcheteParMatch(match Matche);

    public List<Ticket> chercherTicketParCategorie(String categorie);

}
