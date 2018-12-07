
package org.javaee8recipes.chapter13.recipe13_03;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author juneau
 */
@Named(value = "ebookController")
@SessionScoped
@Ebook
public class EbookController implements Serializable, Book {

    /**
     * Creates a new instance of EbookController
     */
    public EbookController() {
    }
}
