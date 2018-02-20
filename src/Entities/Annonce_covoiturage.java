/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author medma
 */
public class Annonce_covoiturage {
    private int id_annonceur;
    private int id_annonce;
    private Date date_depart;
    private Date Date_arrivee;
    private String adresse_depart;
    private String adresse_arrivee;
    private float tarif;

    public Annonce_covoiturage() {
    }

    @Override
    public String toString() {
        return "Annonce_covoiturage{" + "id_annonceur=" + id_annonceur + ", id_annonce=" + id_annonce + ", date_depart=" + date_depart + ", Date_arrivee=" + Date_arrivee + ", adresse_depart=" + adresse_depart + ", adresse_arrivee=" + adresse_arrivee + ", tarif=" + tarif + '}';
    }

    public Annonce_covoiturage(int id_annonce, Date date_depart, Date Date_arrivee, String adresse_depart, String adresse_arrivee, float tarif) {
        this.id_annonce = id_annonce;
        this.date_depart = date_depart;
        this.Date_arrivee = Date_arrivee;
        this.adresse_depart = adresse_depart;
        this.adresse_arrivee = adresse_arrivee;
        this.tarif = tarif;
    }

    public Annonce_covoiturage(int id_annonceur, int id_annonce, Date date_depart, Date Date_arrivee, String adresse_depart, String adresse_arrivee, float tarif) {
        this.id_annonceur = id_annonceur;
        this.id_annonce = id_annonce;
        this.date_depart = date_depart;
        this.Date_arrivee = Date_arrivee;
        this.adresse_depart = adresse_depart;
        this.adresse_arrivee = adresse_arrivee;
        this.tarif = tarif;
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

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public Date getDate_arrivee() {
        return Date_arrivee;
    }

    public void setDate_arrivee(Date Date_arrivee) {
        this.Date_arrivee = Date_arrivee;
    }

    public String getAdresse_depart() {
        return adresse_depart;
    }

    public void setAdresse_depart(String adresse_depart) {
        this.adresse_depart = adresse_depart;
    }

    public String getAdresse_arrivee() {
        return adresse_arrivee;
    }

    public void setAdresse_arrivee(String adresse_arrivee) {
        this.adresse_arrivee = adresse_arrivee;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

}