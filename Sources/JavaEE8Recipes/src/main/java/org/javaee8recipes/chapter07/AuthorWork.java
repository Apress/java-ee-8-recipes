
package org.javaee8recipes.chapter07;

/**
 *
 * @author juneau
 */
public class AuthorWork implements java.io.Serializable {
    
    private int id;
    private int authorId;
    private int bookId;

    public AuthorWork(){
        this.id = -1;
        this.authorId = -1;
        this.bookId = -1;
    }
    
    public AuthorWork(int id, int authorId, int bookId){
        this.id = id;
        this.authorId = authorId;
        this.bookId = bookId;
    }

    /**
     * @return the authorId
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the bookId
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    
    
}
