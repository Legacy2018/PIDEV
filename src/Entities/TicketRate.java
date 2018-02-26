/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author BSS
 */
public class TicketRate {
    private int idRate;
    private Double nbRate;
    private Fos_User idUser;
    private Ticket idTicket;

    public TicketRate() {
    }

    public TicketRate(int idRate, Double nbRate, Fos_User idUser, Ticket idTicket) {
        this.idRate = idRate;
        this.nbRate = nbRate;
        this.idUser = idUser;
        this.idTicket = idTicket;
    }

    public TicketRate(Double nbRate, Fos_User idUser, Ticket idTicket) {
        this.nbRate = nbRate;
        this.idUser = idUser;
        this.idTicket = idTicket;
    }
    
    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public Double getNbRate() {
        return nbRate;
    }

    public void setNbRate(Double nbRate) {
        this.nbRate = nbRate;
    }

    public Fos_User getIdUser() {
        return idUser;
    }

    public void setIdUser(Fos_User idUser) {
        this.idUser = idUser;
    }

    public Ticket getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Ticket idTicket) {
        this.idTicket = idTicket;
    }

    @Override
    public String toString() {
        return "TicketRate{" + "idRate=" + idRate + ", nbRate=" + nbRate + ", idUser=" + idUser + ", idTicket=" + idTicket + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final TicketRate other = (TicketRate) obj;
        if (this.idRate != other.idRate) {
            return false;
        }
        if (!Objects.equals(this.nbRate, other.nbRate)) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        if (!Objects.equals(this.idTicket, other.idTicket)) {
            return false;
        }
        return true;
    }
    
    
}
