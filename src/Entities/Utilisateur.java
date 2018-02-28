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
public class Utilisateur extends Fos_User{
    private int id_user;
    private String position;
    private String telephone;
    private boolean fumeur;
    private String nom;
    private String pnom;
    private String date_de_naissance;
    private String Img_profile;
    private int num_confirm;
    public Utilisateur() {
    }

    public Utilisateur(int id_user, String position, String telephone, boolean fumeur, String nom, String pnom, String date_de_naissance,Fos_User fu,String Img_profile,int num_confirm) {
        super(fu.id, fu.username, fu.email, fu.password, fu.enabled, fu.confirmation_token, fu.role);
        this.id_user = id_user;
        this.position = position;
        this.telephone = telephone;
        this.fumeur = fumeur;
        this.nom = nom;
        this.pnom = pnom;
        this.date_de_naissance = date_de_naissance;
        this.Img_profile=Img_profile;
        this.num_confirm=num_confirm;
    }

    public Utilisateur(int id_user, String position, String telephone, boolean fumeur, String nom, String pnom, String date_de_naissance, int id, String username, String email, String password, boolean enabled, boolean confirmation_token, String role,String Img_profile,int num_confirm) {
        super(id, username, email, password, enabled, confirmation_token, role);
        this.id_user = id_user;
        this.position = position;
        this.telephone = telephone;
        this.fumeur = fumeur;
        this.nom = nom;
        this.pnom = pnom;
        this.date_de_naissance = date_de_naissance;
        this.Img_profile=Img_profile;
        this.num_confirm=num_confirm;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getImg_profile() {
        return Img_profile;
    }

    public int getNum_confirm() {
        return num_confirm;
    }

    public void setNum_confirm(int num_confirm) {
        this.num_confirm = num_confirm;
    }
    
    public void setImg_profile(String Img_profile) {
        this.Img_profile = Img_profile;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPassword() {
        return password;
    }

    public String getPnom() {
        return pnom;
    }

    public String getPosition() {
        return position;
    }

    public String getRole() {
        return role;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setConfirmation_token(boolean confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFumeur(boolean fumeur) {
        this.fumeur = fumeur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPnom(String pnom) {
        this.pnom = pnom;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFumeur() {
        return fumeur;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
    
   
    
   
}
