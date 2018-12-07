/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.bookstore.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.javaee8recipes.bookstore.entity.BookAuthor;

/**
 *
 * @author Juneau
 */
@Stateless
public class BookAuthorFacade extends AbstractFacade<BookAuthor> {

    @PersistenceContext(unitName = "org.javaee8recipes_BookStore_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookAuthorFacade() {
        super(BookAuthor.class);
    }
    
}
