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
public class stade {
    public int id_stade;
    public String nom_Stade ;
    public String ville;
    public int capacite;
    
    

   

    public int getId_stade() {
        return id_stade;
    }

    public String getVille() {
        return ville;
    }

    public void setId_stade(int id_stade) {
        this.id_stade = id_stade;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNom_Stade() {
        return nom_Stade;
    }

    public int getCapacité() {
        return capacite;
    }

    public void setNom_Stade(String nom_Stade) {
        this.nom_Stade = nom_Stade;
    }

    public void setCapacité(int capacité) {
        this.capacite = capacité;
    }

    public stade(int id_stade, String nom_Stade, String ville, int capacité) {
        this.id_stade = id_stade;
        this.nom_Stade = nom_Stade;
        this.ville = ville;
        this.capacite = capacité;
    }

    @Override
    public String toString() {
        return "stade{" + "id_stade=" + id_stade + ", nom_Stade=" + nom_Stade + ", ville=" + ville + ", capacit\u00e9=" + capacite + '}';
    }

    public stade() {
    }

   
    
}
