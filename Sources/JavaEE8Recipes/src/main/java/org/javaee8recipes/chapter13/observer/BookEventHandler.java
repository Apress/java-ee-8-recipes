/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter13.observer;

import java.math.BigDecimal;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.javaee8recipes.chapter09.jsf.BookController;
import org.javaee8recipes.chapter13.event.BookEvent;
import org.javaee8recipes.chapter13.qualifier.OnlineSale;
import org.javaee8recipes.chapter13.qualifier.StoreSale;

/**
 *
 * @author Juneau
 */
public class BookEventHandler {
    
    @Inject
    private BookController bookController;
    
    public BookEventHandler(){
        
    }
    
    public void notifyPublisherOnline (@Observes @OnlineSale BookEvent event) {
        for (String s : event.getNotifyList()) {
            System.out.println("Sending Notification to Pubisher: " + s + " purchase of book online: "
                    + bookController.findById(event.getBook()).getTitle() + " from store: " + event.getStoreName()
                    + " purchase price: $" + event.getPrice()
                    + " on: " + event.getDate());
        }
    }
    
    public void notifyPublisherInStore (@Observes @StoreSale BookEvent event) {
        for (String s : event.getNotifyList()) {
            System.out.println("Sending Notification to Pubisher: " + s + " purchase of book in store: "
                    + bookController.findById(event.getBook()).getTitle() + " from store: " + event.getStoreName()
                    + " purchase price: $" + event.getPrice()
                    + " on: " + event.getDate());
        }
    }
}
