/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;



/**
 *
 * @author Katouchi
 */
public class Messages {
    private int sender;
    private int recever;
    private String message;
    private int id;
    private boolean afficher;
    private Date datemsg;

    public Date getDatemsg() {
        return datemsg;
    }

    public void setDatemsg(Date datemsg) {
        this.datemsg = datemsg;
    }
    
    public Messages(int sender, int recever, String message, int id, boolean afficher,Date datemsg) {
        this.sender = sender;
        this.recever = recever;
        this.message = message;
        this.id = id;
        this.afficher = afficher;
        this.datemsg=datemsg;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecever() {
        return recever;
    }

    public void setRecever(int recever) {
        this.recever = recever;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAfficher() {
        return afficher;
    }

    public void setAfficher(boolean afficher) {
        this.afficher = afficher;
    }

    @Override
    public String toString() {
        return sender+" "+message+" "+recever;
    }
    
}
