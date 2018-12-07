//
package org.javaee8recipes.chapter09.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.javaee8recipes.entity.Book;
import org.javaee8recipes.entity.BookAuthor;

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
        try {
            em.persist(book);
            String message = "Success";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
        } catch (Exception e){
            String message = e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
        }
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
     * Return a Book object based upon a given ID.  
     * @param id
     * @return 
     */
    public Book findById(BigDecimal id){
        Book book = null;
        try {
            book = (Book) em.createQuery("select object(o) from Book o " +
                              "where o.id = :id")
                              .setParameter("id", id)
                              .getSingleResult();
        } catch (NoResultException e){
            System.out.println(e);
        }
        return book;
    }
    
    /**
     * Recipe 10-11:  Forcing a query to be executed
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
                " ORDER BY id", org.javaee8recipes.entity.Book.class);
        return query.getResultList();
        
    }
    
    public List<Book> obtainNamedNativeList(){
        Query query = em.createNamedQuery("allBooks", org.javaee8recipes.entity.Book.class);
        return query.getResultList();
        
    }
    
    public List<Book> findBooksByAuthor(BookAuthor authorId){
        return em.createQuery("select o from Book o " +
                "where :bookAuthor MEMBER OF o.authors")
                .setParameter("bookAuthor", authorId)
                .getResultList();
    }
    
    public List<Book> findBooksByAuthorCriteria(BookAuthor authorId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
	Root<Book> book = cq.from(Book.class);
        //Book_ is a static metamodel class that is generated at compile time
//	cq.where(book.get(Book_.bookAuthor).in(authorId));
	TypedQuery<Book> tq = em.createQuery(cq);
	return tq.getResultList();
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
