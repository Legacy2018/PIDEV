/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Collections;

/**
 *
 * @author Emel
 */
public class Joueur implements Comparable<Joueur>{

    int idJoueur;
    String nomJoueur;
  //  String nationalite;
    int nbrBut;
    String position;
    int nbrCartRouge;
    int nbrCartJaune;
    int NumMaillot;
    Equipe idEquipe;
     
   

    public Joueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public int getNbrBut() {
        return nbrBut;
    }

    public void setNbrBut(int nbrBut) {
        this.nbrBut = nbrBut;
    }

    public int getNbrCartRouge() {
        return nbrCartRouge;
    }

    public void setNbrCartRouge(int nbrCartRouge) {
        this.nbrCartRouge = nbrCartRouge;
    }

    public int getNbrCartJaune() {
        return nbrCartJaune;
    }

    public void setNbrCartJaune(int nbrCartJaune) {
        this.nbrCartJaune = nbrCartJaune;
    }

    public int getNumMaillot() {
        return NumMaillot;
    }

    public void setNumMaillot(int NumMaillot) {
        this.NumMaillot = NumMaillot;
    }

    public Equipe getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Equipe idEquipe) {
        this.idEquipe = idEquipe;
    }

    
  /*  public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    
    */
 
    

    public int getId_joueur() {
        return idJoueur;
    }

    public void setId_joueur(int id_joueur) {
        this.idJoueur = id_joueur;
    }

    public String getNom_joueur() {
        return nomJoueur;
    }

    public void setNom_joueur(String nom_joueur) {
        this.nomJoueur = nom_joueur;
    }

  
   
    public int getNbr_but() {
        return nbrBut;
    }

    public void setNbr_but(int nbr_but) {
        this.nbrBut = nbr_but;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Equipe getId_equipe() {
        return idEquipe;
    }

    public void setId_equipe(Equipe id_equipe) {
        this.idEquipe = id_equipe;
    }

    @Override
    public String toString() {
        return "Joueur{" + "idJoueur=" + idJoueur + ", nomJoueur=" + nomJoueur + " , nbrBut=" + nbrBut + ", position=" + position + ", nbrCartRouge=" + nbrCartRouge + ", nbrCartJaune=" + nbrCartJaune + ", NumMaillot=" + NumMaillot + ", idEquipe=" + idEquipe + '}';
    }

    public Joueur( String nomJoueur, int nbrBut, String position, int nbrCartRouge, int nbrCartJaune, int NumMaillot, Equipe idEquipe) {
     
        this.nomJoueur = nomJoueur;
        this.nbrBut = nbrBut;
        this.position = position;
        this.nbrCartRouge = nbrCartRouge;
        this.nbrCartJaune = nbrCartJaune;
        this.NumMaillot = NumMaillot;
        this.idEquipe = idEquipe;
    }

   

   

   
    public Joueur() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    
 
  
    @Override  
    public boolean equals(Object obj) {
    if(obj instanceof Joueur && obj != null){
        
        Joueur j = (Joueur) obj;
        if(j.getId_joueur() == this.idJoueur)
            return true;
    }    
    return false;
    }
    
    
    @Override
    public int compareTo(Joueur o) {
        return this.nbrBut - o.nbrBut;
    }
    
    
}
