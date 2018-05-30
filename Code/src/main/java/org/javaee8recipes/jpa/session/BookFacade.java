//
package org.javaee8recipes.jpa.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.javaee8recipes.jpa.entity.Book;
import org.javaee8recipes.jpa.entity.BookAuthor;

/**
 * Stateless Session Bean for the Book entity
 * @author juneau
 */

@Stateless
public class BookFacade extends AbstractFacade<Book> {
    @PersistenceContext(unitName = "JavaEERecipesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
    /**
     * Create a book object
     * @param book 
     */
    public void create(Book book){
        em.persist(book);
    }
    
    /**
     * Update a book object
     * @param book 
     */
    public void edit(Book book){
        em.merge(book);
    }
    
    /**
     * Remove a book object
     * @param book 
     */
    public void remove(Book book){
        em.remove(book);
    }
    
    /**
     * Return a Book object based upon a given title.  This assumes that there
     * are no duplicate titles in the database.
     * @param title
     * @return 
     */
    public Book findByTitle(String title){
        return (Book) em.createQuery("select object(o) from Book o " +
                              "where o.title = :title")
                              .setParameter("title", title.toUpperCase())
                              .getSingleResult();
    }
    
    /**
     * Recipe 10-9:  Forcing a query to be executed
     * @return 
     */

    public List<Book> findAllBooks(){ 
        Query qry = em.createQuery("select o from Book o");
        qry.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        return qry.getResultList();
    }
    
    
    public List<Map> obtainCustomList(){

        List<Object[]> results = em.createNativeQuery(
                "select id, title, description " +
                "FROM BOOK " +
                " ORDER BY id").getResultList();

        List data = new ArrayList<HashMap>();

        if (!results.isEmpty()) {
            for (Object[] result : results) {
                HashMap resultMap = new HashMap();
                resultMap.put("id", result[0]);
                resultMap.put("title", result[1]);
                resultMap.put("description", result[2]);

                data.add(resultMap);
            }
        }
        return data;
    }
    
    public List<Book> obtainNativeList(){
        Query query = em.createNativeQuery("select id, title, description " +
                "FROM BOOK " +
                " ORDER BY id", org.javaee8recipes.jpa.entity.Book.class);
        return query.getResultList();
        
    }
    
    public List<Book> obtainNamedNativeList(){
        Query query = em.createNamedQuery("allBooks", org.javaee8recipes.jpa.entity.Book.class);
        return query.getResultList();
        
    }
    
    public List<Book> findBooksByAuthor(BookAuthor authorId){
        return em.createQuery("select o from Book o " +
                "where :bookAuthor MEMBER OF o.authors")
                .setParameter("bookAuthor", authorId)
                .getResultList();
    }
    
   
    
    public List<Book> findBookByTitle(String title){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Metamodel m = em.getMetamodel();
        EntityType<Book> Book_ = m.entity(Book.class);
        Root<Book> book = cq.from(Book.class);
      //  cq.where(cb.equal(book.get(Book_.title), title));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    
    
}
