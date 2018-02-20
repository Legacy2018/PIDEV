/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;


import Entities.Equipe;
import Entities.match;
import java.util.List;

/**
 *
 * @author MarwaBj
 */
public interface IMatchDAO {
    
    public List<match> afficherMatchParequipe(String nom_equipe);
    void  ajouterMatch(match match); //on ajoute les equipes et le stade
    
    void modifierMatch(match match); //on modifie les buts et le score
     public void modifierMatchScore(String score,String score2,match match);
    void modifierDateMatch(String date, match match);
    
    void supprimerMatch(int id);
    
    List<match> afficherMatch();
    
    List<match> afficherMatchMaxButs();
    
    match chercherMatchParId(int id);
    
    public List<match> afficherMatchParPhase(String phase);
    
    public List<match> chercherMatchParEquipe(Equipe equipe);
    
    
    List<match> chercherMatchParDate(String date);
    
   
}
