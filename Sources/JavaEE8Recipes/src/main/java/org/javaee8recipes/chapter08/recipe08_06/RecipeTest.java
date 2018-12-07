package org.javaee8recipes.chapter08.recipe08_06;

import java.util.Iterator;
import java.util.List;
import javax.persistence.*;

/**
 * Recipe 8-6: Persistence Unit Teat
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
            Author08 author = new Author08();
            author.setFirst("JOE");
            author.setLast("TESTER");
            author.setBio("An author test account.");
            AuthorDetail08 detail = new AuthorDetail08();
            detail.setAddress1("Address 1");
            detail.setAddress2("Address 2");
            detail.setCity("NoMansLand");
            detail.setState("ZZ");
            detail.setZip("12345");
            detail.setNotes("This is a test detail");
            author.setAuthorDetail(detail);
            em.persist(author);
            em.getTransaction().commit();
        } catch (Exception ex){
            System.err.println(ex);
        }
    }
}
