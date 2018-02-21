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
public class Fos_User {
    
    protected int id;
    protected String username;
    protected String email;
    protected String password;
    protected boolean enabled;
    protected boolean confirmation_token;
    protected String role;

    public Fos_User() {
    }

    public Fos_User(int id, String username, String email, String password, boolean enabled, boolean confirmation_token, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.confirmation_token = confirmation_token;
        this.role = role;
    }

     public Fos_User(int id) {
        this.id = id;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(boolean confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Fos_user (id :"+id+"|username : "+username+"|email : "+email+"|password : "+password+"|enabled : "+enabled+"|confirmation : "+confirmation_token+"|role : "+role+")\n";
    }
    
}
