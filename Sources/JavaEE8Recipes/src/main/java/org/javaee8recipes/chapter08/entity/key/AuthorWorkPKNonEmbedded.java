package org.javaee8recipes.chapter08.entity.key;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Non-Embeddable Primary Key class for AuthorWork
 *
 * @author juneau
 */
public class AuthorWorkPKNonEmbedded implements Serializable {

    private BigInteger bookId;
    private BigInteger authorId;

    public AuthorWorkPKNonEmbedded() {
    }

    /**
     * @return the bookId
     */
    public BigInteger getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(BigInteger bookId) {
        this.bookId = bookId;
    }

    /**
     * @return the authorId
     */
    public BigInteger getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(BigInteger authorId) {
        this.authorId = authorId;
    }

    public int hashCode() {
        return bookId.hashCode() + authorId.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthorWorkPKEmbedded)) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        AuthorWorkPKEmbedded pk = (AuthorWorkPKEmbedded) obj;
        return (((bookId == ((AuthorWorkPKEmbedded) obj).getBookId()))
                && ((authorId == ((AuthorWorkPKEmbedded) obj).getAuthorId())));
    }
}