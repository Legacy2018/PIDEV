
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.stade;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IStadeDAO {
  
    public List<stade> ConsulterStadeList(); 
    public stade ConsulterstadeId(int id);
     public stade Consulterstade(String nom);
     public String ConsulterstadeNom(String nom);
    
}
