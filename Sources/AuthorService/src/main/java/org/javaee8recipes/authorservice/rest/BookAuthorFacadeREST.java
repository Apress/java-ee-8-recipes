/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.authorservice.rest;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.javaee8recipes.authorservice.entity.BookAuthor;

/**
 *
 * @author Juneau
 */
@Stateless
@Path("bookAuthor")
public class BookAuthorFacadeREST {

    @PersistenceContext(unitName = "AuthorService_1.0PU")
    private EntityManager em;


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public BookAuthor find(@PathParam("id") BigDecimal id) {
        BookAuthor bookAuthor = null;
        try {
            bookAuthor = (BookAuthor) 
                    em.createQuery("select object(o) from BookAuthor o " +
                    "where o.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex){
            System.out.println("Error: "  + ex);
        }
        return bookAuthor;
    }
   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<BookAuthor> findAll() {
        List<BookAuthor> bookAuthors = null;
        try {
            bookAuthors = em.createQuery("select object(o) from BookAuthor o")
                    .getResultList();
        } catch (NoResultException ex){
            System.out.println("Error: "  + ex);
        }
        return bookAuthors;
    }

    protected EntityManager getEntityManager() {
        return em;
    }
    
}
