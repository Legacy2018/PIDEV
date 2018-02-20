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
private int id_annonceur;
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

    public Annonce_collocation(int id_annonceur, int id_annonce, String titre_annonce, String Description, float tarif, String adresse, Date datedebut, Date datefin) {
        this.id_annonceur = id_annonceur;
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description = Description;
        this.tarif = tarif;
        this.adresse = adresse;
        this.datedebut = datedebut;
        this.datefin = datefin;
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

   

    public int getId_annonceur() {
        return id_annonceur;
    }

    public void setId_annonceur(int id_annonceur) {
        this.id_annonceur = id_annonceur;
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

    public void setDescription(String Description) {
        this.description = Description;
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

    public List<UploadImage> getUi() {
        return ui;
    }

    public void setUi(List<UploadImage> ui) {
        this.ui = ui;
    }

    @Override
    public String toString() {
        return "Annonce_collocation{" + "id_annonceur=" + id_annonceur + ", id_annonce=" + id_annonce + ", titre_annonce=" + titre_annonce + ", Description=" + description + ", tarif=" + tarif + ", adresse=" + adresse + ", ui=" + ui+ '}';
    }
    
    
}
