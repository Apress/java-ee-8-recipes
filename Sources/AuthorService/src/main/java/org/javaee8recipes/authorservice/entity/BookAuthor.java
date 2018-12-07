
package org.javaee8recipes.authorservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juneau
 */
@Entity
@Table(name = "BOOK_AUTHOR")
@NamedQueries({
    @NamedQuery(name = "BookAuthor.findAll", query = "SELECT b FROM BookAuthor b")})
@SqlResultSetMapping(name="authorBooks",
        entities= {
            @EntityResult(entityClass=org.javaee8recipes.authorservice.entity.Book.class, fields={
                @FieldResult(name="id", column="BOOK_ID"),
                @FieldResult(name="title", column="TITLE")
            }),
            @EntityResult(entityClass=org.javaee8recipes.authorservice.entity.BookAuthor.class, fields={
                @FieldResult(name="id", column="AUTHOR_ID"),
                @FieldResult(name="first", column="FIRST"),
                @FieldResult(name="last", column="LAST")
            })
        })
@XmlRootElement
public class BookAuthor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
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
    @ManyToMany
    @JoinTable(name="AUTHOR_WORK",
            joinColumns=
            @JoinColumn(name="AUTHOR_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="BOOK_ID", referencedColumnName="ID"))
    private Set<Book> books;

    public BookAuthor() {
    }

    public BookAuthor(BigDecimal id) {
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
        // TODO: Warning - this method won't work in the case the bookAuthorId fields are not set
        if (!(object instanceof BookAuthor)) {
            return false;
        }
        BookAuthor other = (BookAuthor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaee8recipes.entity.BookAuthor[ id=" + id + " ]";
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
