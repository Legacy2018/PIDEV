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
public class Imagedrapeau {
    int idImage;
    String link ;
    Equipe idEquipe;

    public Imagedrapeau(String link, Equipe idEquipe) {
        this.link = link;
        this.idEquipe=idEquipe;
    }

    public Equipe getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Equipe idEquipe) {
        this.idEquipe = idEquipe;
    }

  

    
    public Imagedrapeau() {
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

  
      @Override
    public String toString() {
        return "Imagedrapeau{" + "idImage=" + idImage + ", link=" + link + '}';
    }
    
}
