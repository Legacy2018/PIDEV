/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class match {
    
    private int idMatch;
    private int nbButTot;
    private int score;
    private int score2;
    private String dateMatch;
    private String heureMatch;
    private stade stade;
    private Equipe equipe1;
    private Equipe equipe2;
    private String phase;
    

    public match(String dateMatch, String heureMatch, Equipe equipe1,stade stade,Equipe  equipe2, String phase) {
        this.dateMatch = dateMatch;
        this.heureMatch = heureMatch;
        this.stade = stade;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.phase = phase;
    
    }
     public match(int idMatch) {
        this.idMatch = idMatch;
    }
    

    public match(int score,int score2) {
        this.score = score;
        this.score2=score2;
    }

    public match() {
    }

    public match(stade  d) {
     this.stade=d;  
    }

   
    
    
    
    public int getIdMatch() {
        return idMatch;
    }

    public int getNbButTot() {
        return nbButTot;
    }

    public int getScore() {
        return score;
    }

    public String getDateMatch() {
        return dateMatch;
    }
    
    public stade getStade() {
        return stade;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public void setNbButTot(int nbButTot) {
        this.nbButTot = nbButTot;
    }

    public void setScore(int Score) {
        this.score = Score;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public void setDateMatch(String dateMatch) {
        this.dateMatch = dateMatch;
    }

    public void setStade(stade stade) {
        this.stade = stade;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
        
    }

    
    public void setHeureMatch(String heureMatch) {
        this.heureMatch = heureMatch;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getHeureMatch() {
        return heureMatch;
    }

    public String getPhase() {
        return phase;
    }

    @Override
    public String toString() {
        return "Match{" + "idMatch=" + idMatch + ", nbButTot=" + nbButTot + ", score=" + score + ", dateMatch=" + dateMatch + ", heureMatch=" + heureMatch + ", stade=" + stade + ", equipe1=" + equipe1 + ", equipe2=" + equipe2 + ", phase=" + phase +'}';
    }

    

    
    
    
}
