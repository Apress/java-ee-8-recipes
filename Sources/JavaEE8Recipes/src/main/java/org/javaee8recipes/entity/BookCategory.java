/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Juneau
 */
@Entity
@Table(name="BOOK_CATEGORY")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CATEGORY_TYPE", discriminatorType=DiscriminatorType.STRING,length=30)
public abstract class BookCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "book_category_s_generator")
    @SequenceGenerator(name = "book_category_s_generator", sequenceName = "book_category_s", allocationSize = 1)
    private Long id;
    @Column(name="NAME")
    private String name;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STORE_ID", referencedColumnName="ID")
    private BookStore bookStore;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookCategory)) {
            return false;
        }
        BookCategory other = (BookCategory) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaee8recipes.entity.BookCateory[ id=" + getId() + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the bookStore
     */
    public BookStore getBookStore() {
        return bookStore;
    }

    /**
     * @param bookStore the bookStore to set
     */
    public void setBookStore(BookStore bookStore) {
        this.bookStore = bookStore;
    }
    
}
