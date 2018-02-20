/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Emel
 */
public class Entraineur {
    int id_entraineur;
    String nom_entraineur;
    String nationalite;
    int id_equipe;

    public Entraineur(String nom_entraineur, String nationalite, int id_equipe) {
        this.nom_entraineur = nom_entraineur;
        this.nationalite = nationalite;
        this.id_equipe = id_equipe;
    }

    public Entraineur(int id_entraineur, String nom_entraineur, String nationalite, int id_equipe) {
        this.id_entraineur = id_entraineur;
        this.nom_entraineur = nom_entraineur;
        this.nationalite = nationalite;
        this.id_equipe = id_equipe;
    }

    public int getId_entraineur() {
        return id_entraineur;
    }

    public void setId_entraineur(int id_entraineur) {
        this.id_entraineur = id_entraineur;
    }

    public String getNom_entraineur() {
        return nom_entraineur;
    }

    public void setNom_entraineur(String nom_entraineur) {
        this.nom_entraineur = nom_entraineur;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    @Override
    public String toString() {
        return "entraineur{" + "id_entraineur=" + id_entraineur + ", nom_entraineur=" + nom_entraineur + ", nationalite=" + nationalite + ", id_equipe=" + id_equipe + '}';
    }

    public Entraineur() {
    }
    
    
}
