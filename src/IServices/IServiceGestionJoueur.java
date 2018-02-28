/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Joueur;
import java.util.List;

/**
 *
 * @author Emel
 */
public interface IServiceGestionJoueur {

    public int ajouterJoueur(Joueur j);

    public boolean modifierJoueur(Joueur j);

    public boolean supprimerJoueur(int id);

    public List selectJoueurs();

    public List<Joueur> chercherParNom(String nom);

  
    public List<Joueur> chercherParPosition(String pos);

    public List<Joueur> chercherParEquipe(String pays);

    public List<Joueur> chercherParEquipe();

    public Joueur chercherParEquipe(int id);
}
