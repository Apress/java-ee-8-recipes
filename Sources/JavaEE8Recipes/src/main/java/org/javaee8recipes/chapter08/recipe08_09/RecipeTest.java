package org.javaee8recipes.chapter08.recipe08_09;

import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
import org.javaee8recipes.chapter08.entity.BookAuthor08;

/**
 * Recipe 8-9:  Named Queries
 *
 * @author juneau
 */
public class RecipeTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaEERecipesLOCAL");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            Query query = em.createNamedQuery("BookAuthor.findAll");
            List authorList = query.getResultList();
            Iterator authorIterator = authorList.iterator();
            while (authorIterator.hasNext()) {
                BookAuthor08 author = (BookAuthor08) authorIterator.next();
                System.out.print("Name:" + author.getFirst() + " " + author.getLast());
                System.out.println();
            }
        } catch (Exception ex){
            System.err.println(ex);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }
}
