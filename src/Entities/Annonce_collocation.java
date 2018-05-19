/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author medma
 */
public class Annonce_collocation {
private Utilisateur user;
private int id_annonce;
private String titre_annonce;
private String description;
private  float tarif;
private String adresse;
private Date datedebut;
private Date datefin;
private List<UploadImage> ui=new ArrayList<>();

    public Annonce_collocation() {
    }

    @Override
    public String toString() {
        return "Annonce_collocation{" + "user=" + user + ", id_annonce=" + id_annonce + ", titre_annonce=" + titre_annonce + ", description=" + description + ", tarif=" + tarif + ", adresse=" + adresse + ", datedebut=" + datedebut + ", datefin=" + datefin + ", ui=" + ui + '}';
    }

    public Annonce_collocation(int id_annonce, String titre_annonce, String description, float tarif, String adresse, Date datedebut, Date datefin) {
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.tarif = tarif;
        this.adresse = adresse;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Annonce_collocation(Utilisateur user, int id_annonce, String titre_annonce, String description, float tarif, String adresse, Date datedebut, Date datefin) {
        this.user = user;
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.tarif = tarif;
        this.adresse = adresse;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getTitre_annonce() {
        return titre_annonce;
    }

    public void setTitre_annonce(String titre_annonce) {
        this.titre_annonce = titre_annonce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public List<UploadImage> getUi() {
        return ui;
    }

    public void setUi(List<UploadImage> ui) {
        this.ui = ui;
    }

   
}
