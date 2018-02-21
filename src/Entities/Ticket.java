package entities;

import Entities.Fos_User;
import java.sql.Date;
import Entities.match;
import java.util.Objects;


public class Ticket {

    private int idTicket;
    private int nbrTicket;
    private String categories;
    private Float prix;
    private Fos_User idUser;
    private match idMatch;
    private Date heurAjout;

    public Date getHeurAjout() {
        return heurAjout;
    }

    public void setHeurAjout(Date heurAjout) {
        this.heurAjout = heurAjout;
    }

    public Ticket(int idTicket) {
        this.idTicket = idTicket;
    }
    

    public Ticket(int idTicket, int nbrTicket, String categories, Float prix, Fos_User idUser, match idMatch, Date heurAjout) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
        this.idUser = idUser;
        this.idMatch = idMatch;
        this.heurAjout = heurAjout;
    }

    public Ticket(int idTicket, int nbrTicket, String categories, Float prix, Fos_User idUser, match idMatch) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
        this.idUser = idUser;
        this.idMatch = idMatch;
    }

    public Ticket() {
    }

    public Ticket(int nbrTicket, String categories, Float prix, Fos_User idUser, match idMatch) {
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
        this.idUser = idUser;
        this.idMatch = idMatch;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getNbrTicket() {
        return nbrTicket;
    }

    public void setNbrTicket(int nbrTicket) {
        this.nbrTicket = nbrTicket;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Fos_User getIdUser() {
        return idUser;
    }

    public void setIdUser(Fos_User idUser) {
        this.idUser = idUser;
    }

  
    public match getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(match idMatch) {
        this.idMatch = idMatch;
    }

    @Override
    public String toString() {
        return "Ticket{" + "idTicket=" + idTicket + ", nbrTicket=" + nbrTicket + ", categories=" + categories + ", prix=" + prix + ", idUser=" + idUser + ", idMatch=" + idMatch + ", heurAjout=" + heurAjout + '}';
    }

   
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idTicket;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (this.idTicket != other.idTicket) {
            return false;
        }
        return true;
    }

    

   

}
