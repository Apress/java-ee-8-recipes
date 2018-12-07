package org.javaee8recipes.chapter08.recipe08_04;

import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
import org.javaee8recipes.chapter08.entity.BookAuthor08;

/**
 * Recipe 8-4: Persistence Unit Teat
 *
 * @author juneau
 */
public class SequenceTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaEERecipesLOCAL");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            BookAuthor08 author = new BookAuthor08();
            author.setFirst("JOE");
            author.setLast("TESTER");
            author.setBio("An author test account.");
            boolean successful = false;
            try {
                em.persist(author);
                successful = true;
            } finally {
                if (successful){
                    entr.commit();
                } else {
                    entr.rollback();
                }
            } 
           
            Query query = em.createNamedQuery("BookAuthor08.findAll");
            List authorList = query.getResultList();
            Iterator authorIterator = authorList.iterator();
            while (authorIterator.hasNext()) {
                author = (BookAuthor08) authorIterator.next();
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
