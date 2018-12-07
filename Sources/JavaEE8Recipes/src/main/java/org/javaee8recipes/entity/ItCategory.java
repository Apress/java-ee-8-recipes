
package org.javaee8recipes.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IT")
public class ItCategory extends BookCategory implements Serializable {
    
    @Column(name="GENRE")
    private String genre;
    
    @Column(name="CATEGORY_DESC")
    private String description;
    
    public ItCategory(){
        
    }
  
    /**
     * @return the color
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param color the color to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
   
}
