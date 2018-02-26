/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author admin
 */
public class Equipe {
    public int idEquipe ;
    public String pays;
   int etat; //0 pour eliminée 1 pour encore dans la competition
    String phase; //1/8 ou 1/4 ou 1/2 ou 3éme place ou finale
    String groupe; //A ou B ou C ou D ou E ou F ou G ou H 
    String selecteur;
    int point ;
    
   public String getSelecteur() {
        return selecteur;
    }

    public void setSelecteur(String selecteur) {
        this.selecteur = selecteur;
    }

    public Equipe(String pays, String groupe, String selecteur) {
        this.pays = pays;
        this.etat = 1;
        this.phase = "";
        this.groupe = "groupe";
        this.selecteur = selecteur;

    }

    public Equipe(int id ,int point) {
        
        this.idEquipe= id ; 
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Equipe(String pays, int etat, String phase, String groupe,  String selecteur) {
        this.pays = pays;
        this.etat = etat;
        this.phase = phase;
        this.groupe = groupe;
        this.selecteur = selecteur;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int id_equipe) {
        this.idEquipe = id_equipe;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    @Override
    public String toString() {
        return "equipe{" + "id_equipe=" + idEquipe + ", pays=" + pays + ", etat=" + etat + ", phase=" + phase + ", groupe=" + groupe + ", selecteur=" + selecteur + '}';
    }

   
    public Equipe() {
    }

}
