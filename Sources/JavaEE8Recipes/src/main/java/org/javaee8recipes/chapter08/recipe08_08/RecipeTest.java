package org.javaee8recipes.chapter08.recipe08_08;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 * Recipe 8-8: Persistence Unit Teat
 *
 * @author juneau
 */
public class RecipeTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaEERecipesLOCAL");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Book book1 = new Book();
            book1.setTitle("New Book 1");
            Book book2 = new Book();
            book2.setTitle("New Book 2");

            BookAuthorMany author1 = new BookAuthorMany();
            author1.setFirst("JOE");
            author1.setLast("AUTHOR 1");

            BookAuthorMany author2 = new BookAuthorMany();
            author2.setFirst("MARYJJOE");
            author2.setLast("AUTHOR 2");

            Set authors = new HashSet();
            authors.add(author1);
            authors.add(author2);

            Set books = new HashSet();
            books.add(book1);
            books.add(book2);

            book1.setBookAuthor(authors);
            author1.setBooks(books);

            em.persist(author1);
            em.persist(book1);
            em.getTransaction().commit();
        } catch (Exception ex){
            System.err.println(ex);
        } finally{
            if (em != null){
                em.close();
            }
        }
    }
}
