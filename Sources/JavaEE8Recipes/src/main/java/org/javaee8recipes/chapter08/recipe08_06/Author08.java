package org.javaee8recipes.chapter08.recipe08_06;

import org.javaee8recipes.chapter08.entity.BookAuthor08;
import org.javaee8recipes.chapter08.recipe08_06.AuthorDetail08;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Chapter 8 Demonstration for a one-to-one entity relationship
 *
 * @author juneau
 */
@Entity
@Table(name = "AUTHOR")
public class Author08 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "author_s_generator", sequenceName = "author_s", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "author_s_generator")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 30)
    @Column(name = "LAST")
    private String last;
    @Size(max = 30)
    @Column(name = "FIRST")
    private String first;
    @Lob
    @Column(name = "BIO")
    private String bio;
    @OneToOne
    private AuthorDetail08 authorDetail;

    public Author08() {
    }

    public Author08(BigDecimal id) {
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
        if (!(object instanceof BookAuthor08)) {
            return false;
        }
        Author08 other = (Author08) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaeerecipes.chapter08.entity.recipe08_06.Author08[ id=" + id + " ]";
    }

    /**
     * @return the authorId
     */
    public org.javaee8recipes.chapter08.recipe08_06.AuthorDetail08 getAuthorDetail() {
        return authorDetail;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorDetail(org.javaee8recipes.chapter08.recipe08_06.AuthorDetail08 authorDetail) {
        this.authorDetail = authorDetail;
    }
}
