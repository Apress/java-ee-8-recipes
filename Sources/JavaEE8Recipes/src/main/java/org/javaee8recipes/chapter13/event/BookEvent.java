/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter13.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Juneau
 */
public class BookEvent {
    private BigDecimal book;
    private String storeName;
    private BigDecimal price;
    private int numBooks;
    private LocalDate date;
    private List<String> notifyList;

    /**
     * @return the bookTitle
     */
    public BigDecimal getBook() {
        return book;
    }

    /**
     * @param book
     */
    public void setBook(BigDecimal book) {
        this.book = book;
    }

    /**
     * @return the storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the notifyList
     */
    public List<String> getNotifyList() {
        return notifyList;
    }

    /**
     * @param notifyList the notifyList to set
     */
    public void setNotifyList(List<String> notifyList) {
        this.notifyList = notifyList;
    }

    /**
     * @return the numBooks
     */
    public int getNumBooks() {
        return numBooks;
    }

    /**
     * @param numBooks the numBooks to set
     */
    public void setNumBooks(int numBooks) {
        this.numBooks = numBooks;
    }
            
}
