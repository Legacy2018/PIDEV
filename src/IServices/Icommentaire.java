/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Commentaire;
import java.util.List;
import static javafx.scene.input.KeyCode.R;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author BSS
 */
public interface Icommentaire {
       public List<Commentaire> getAll(int i);
        public Commentaire findById(Integer idCommentaire);
          public void delete(Integer idCommentaire);
           public void Update(Commentaire t);
           public void add(Commentaire t);
}
