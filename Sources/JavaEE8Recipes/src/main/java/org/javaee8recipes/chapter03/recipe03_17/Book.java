
package org.javaee8recipes.chapter03.recipe03_17;

/**
 * Recipe 4-5
 * @author juneau
 */
public class Book implements java.io.Serializable{
    private String title;
    private String image;
    
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
}
