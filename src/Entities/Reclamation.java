/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Katouchi
 */
public class Reclamation {
    private int id;
    private int id_user;
    private String Sujet;
    private boolean Lu;
    private String Message;
    private String Date;

    public Reclamation() {
    }

    public Reclamation(int id, int id_user, String Sujet, boolean Lu, String Message, String Date) {
        this.id = id;
        this.id_user = id_user;
        this.Sujet = Sujet;
        this.Lu = Lu;
        this.Message = Message;
        this.Date = Date;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getSujet() {
        return Sujet;
    }

    public void setSujet(String Sujet) {
        this.Sujet = Sujet;
    }

    public boolean isLu() {
        return Lu;
    }

    public void setLu(boolean Lu) {
        this.Lu = Lu;
    }

   
  

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    
}
