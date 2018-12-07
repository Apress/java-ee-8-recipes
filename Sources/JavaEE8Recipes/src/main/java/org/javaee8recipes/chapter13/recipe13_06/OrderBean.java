

package org.javaee8recipes.chapter13.recipe13_06;

import java.util.List;
import javax.enterprise.inject.Produces;
import org.javaee8recipes.entity.Book;


/**
 * Recipe 13-6 - @Veto Annotation
 * @author Juneau
 */

//@Veto
public class OrderBean implements java.io.Serializable {
    
   // @Produces @Disposer
    List<Book> books;
    
    public OrderBean(){
        
    }
    
    // Some Class Implementation
}
