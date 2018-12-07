
package org.javaee8recipes.chapter08.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import org.javaee8recipes.chapter08.entity.key.AuthorWorkPKNonEmbedded;

/**
 * Chapter 8 - Example of Non-Embedded Primary Key
 * @author juneau
 */

@IdClass(AuthorWorkPKNonEmbedded.class)
@Entity
@Table(name = "AUTHOR_WORK_LEGACY")
@NamedQueries({
    @NamedQuery(name = "AuthorWork.findAll", query = "SELECT a FROM AuthorWork a")})
public class AuthorWorkNonEmbedded implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Column(name = "BOOK_ID")
    private BigInteger bookId;
    
    @Id
    @Column(name= "AUTHOR_ID")
    private BigInteger authorId;

    public AuthorWorkNonEmbedded() {
    }

    public AuthorWorkNonEmbedded(BigInteger bookId, BigInteger authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    
    public BigInteger getBookId() {
        return bookId;
    }

    public void setBookId(BigInteger bookId) {
        this.bookId = bookId;
    }

    public BigInteger getAuthorId() {
        return authorId;
    }

    public void setAuthorId(BigInteger authorId) {
        this.authorId = authorId;
    }
    
}
