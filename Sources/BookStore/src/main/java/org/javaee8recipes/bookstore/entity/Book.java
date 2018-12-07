
package org.javaee8recipes.bookstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juneau
 */
@Entity
@Table(name = "BOOK")
@NamedNativeQuery(
    name="allBooks",
    query = "select id, title, description " +
                "FROM BOOK " +
                "ORDER BY id",
    resultClass=Book.class)
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")})

@XmlRootElement
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "book_s_generator")
    @SequenceGenerator(name = "book_s_generator", sequenceName = "book_s", allocationSize = 1)
    @Basic(optional = false)
   // @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    //@Size(max = 150)
    @FormParam(value="title")
    @Column(name = "TITLE")
    protected String title;
    //@Size(max = 500)
    @Column(name = "IMAGE")
    private String image;
    @FormParam(value="description")
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToMany(mappedBy="books")
    private Set<BookAuthor> authors;
    @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
    private List<Chapter> chapters = null;

    public Book() {
    }

    public Book(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaeerecipes.chapter09.entity.Book[ id=" + id + " ]";
    }

    /**
     * @return the authors
     */
    @XmlTransient
    public Set<BookAuthor> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(Set<BookAuthor> authors) {
        this.authors = authors;
    }

    /**
     * @return the chapters
     */
    @XmlTransient
    public List<Chapter> getChapters() {
        return chapters;
    }

    /**
     * @param chapters the chapters to set
     */
    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
    
}
