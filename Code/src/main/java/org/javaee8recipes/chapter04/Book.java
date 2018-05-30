
package org.javaee8recipes.chapter04;

import java.util.List;

/**
 * Chapter 4
 * @author juneau
 */
public class Book implements java.io.Serializable{
    private String title;
    private String image;
    private List<Chapter> chapters;
    
    
    public Book(){
        this.title = null;
        this.image = null;
    }
    
    public Book(String title, String image){
        this.title = title;
        this.image = image;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the chapters
     */
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
