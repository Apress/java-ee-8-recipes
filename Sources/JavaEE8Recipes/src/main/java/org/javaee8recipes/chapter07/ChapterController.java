
package org.javaee8recipes.chapter07;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.javaee8recipes.chapter07.dao.ChapterDAO;

/**
 * Chapter Controller
 * @author Juneau
 */
@SessionScoped
@ManagedBean(name = "ch7ChapterController")
public class ChapterController implements java.io.Serializable {
 
    private ChapterDAO chapterDAO;
    
    public ChapterController(){
        chapterDAO = new ChapterDAO();
    }
    
    
}
