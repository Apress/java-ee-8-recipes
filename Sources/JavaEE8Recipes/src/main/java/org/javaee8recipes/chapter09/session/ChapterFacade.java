/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter09.session;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.javaee8recipes.entity.Book;
import org.javaee8recipes.entity.Chapter;

/**
 *
 * @author juneau
 */
// Uncomment to publish this EJB as a web service (Recipe 15-5)
@WebService
@Stateless
public class ChapterFacade extends AbstractFacade<Chapter> {
    @PersistenceContext(unitName = "JavaEERecipesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChapterFacade() {
        super(Chapter.class);
    }
    
    @WebMethod
    public List<Book> findBookByChapterTitle(Chapter chapter){
        return em.createQuery("select b from Book b INNER JOIN b.chapters c " +
                "where c.title = :title")
                .setParameter("title", chapter.getTitle())
                .getResultList();
    }
    
    public List<Book> findAllBooksByChapterNumber(BigDecimal chapterNumber){
        return em.createQuery("select b from Book b LEFT OUTER JOIN b.chapters c " +
                "where c.chapterNumber = :num")
                .setParameter("num", chapterNumber)
                .getResultList();
    }
    
}
