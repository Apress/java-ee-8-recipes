package org.javaee8recipes.chapter08.recipe08_08;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Chapter 8
 * Entity class for the BOOK_AUTHOR database table of the Acme Bookstore application
 * @author juneau
 */
//@Entity
@Table(name = "BOOK_AUTHOR")
@NamedQueries({
    @NamedQuery(name = "BookAuthor.findAll", query = "SELECT b FROM BookAuthor b"),
    @NamedQuery(name = "BookAuthor.findById", query = "SELECT b FROM BookAuthor b WHERE b.id = :id"),
    @NamedQuery(name = "BookAuthor.findByLast", query = "SELECT b FROM BookAuthor b WHERE b.last = :last"),
    @NamedQuery(name = "BookAuthor.findByFirst", query = "SELECT b FROM BookAuthor b WHERE b.first = :first")})
public class BookAuthorMany implements Serializable {
   
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="book_author_s_generator",sequenceName="book_author_s", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
    generator="book_author_s_generator")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 30)
    @Column(name = "LASTNAME")
    private String last;
    @Size(max = 30)
    @Column(name = "FIRSTNAME")
    private String first;
    @Lob
    @Column(name = "BIO")
    private String bio;
    @ManyToMany(mappedBy="bookAuthors")
    private Set<Book> books;

    public BookAuthorMany() {
    }

    public BookAuthorMany(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookAuthorMany)) {
            return false;
        }
        BookAuthorMany other = (BookAuthorMany) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaeerecipes.chapter08.recipe08_08.BookAuthor[ id=" + id + " ]";
    }

    /**
     * @return the books
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

   
   
    
}
