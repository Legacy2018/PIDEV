/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Equipe;
import java.util.List;

/**
 *
 * @author Emel
 */
public interface IServiceGestionEquipe {

    public boolean ajouterEquipe(Equipe e);

    public boolean modifierEquipe(Equipe e, int id);

    public boolean supprimerEquipe(String pays);

    public List<Equipe> chercherParPays(String pays);
     public Equipe AfficherEquipe(int id);

    public Equipe AfficherEquipe(String pays);
    
    public Equipe AfficherEquipe(Equipe e);
    
    public List<Equipe> EquipeEnCompetition();

    public List<Equipe> EquipeEliminee();

    public List<Equipe> chercherParGroupe(String groupe);

    public List<Equipe> chercherParPhase(String phase);

    public List<String> selectPays();

    public List<Equipe> selectEquipes();
 public String ConsulterstadeNom(String pays);
}
