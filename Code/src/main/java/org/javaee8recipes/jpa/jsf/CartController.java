package org.javaee8recipes.jpa.jsf;


import java.io.Serializable;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import org.javaee8recipes.jpa.object.Cart;
import org.javaee8recipes.jpa.object.Item;
import org.javaee8recipes.jpa.session.OrderFacade;
//import org.primefaces.event.RowEditEvent;

/**
 *
 * @author juneau
 */
@ManagedBean(name = "cartController")
@SessionScoped
public class CartController implements Serializable {

    private Item currentBook = null;
    
    @EJB
    OrderFacade orderFacade;
    
    @ManagedProperty(value = "#{authorConroller}")
    private AuthorController authorController;

    /**
     * Creates a new instance of CartController
     */
    public CartController() {
    }

    public String addToCart() {
        if (getCart().getBooks() == null) {
            getCart().addBook(getAuthorController().getCurrentBook(), 1);
        } else {
            getCart().addBook(getAuthorController().getCurrentBook(),
                    searchCart(getAuthorController().getCurrentBook().getTitle()) + 1);
        }
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succesfully Updated Cart", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return null;
    }

    /**
     * Determines if a book is already in the shopping cart
     *
     * @param title
     * @return
     */
    public int searchCart(String title) {
        int count = 0;

        for (Item item : getCart().getBooks()) {
            if (item.getBook().getTitle().equals(title)) {
                count++;
            }
        }
        return count;
    }

    public String viewCart() {
        if (getCart() == null) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No books in cart...", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

        return "/chapter09/cart";
    }

    public String continueShopping() {
        return "/chapter09/book";
    }

    public String editItem(String title) {
        for (Item item : getCart().getBooks()) {
            if (item.getBook().getTitle().equals(title)) {
                currentBook = item;
            }
        }
        return "/chapter09/reviewItem";

    }

    public String updateCart(String title) {
        Item foundItem = null;
        if (currentBook.getQuantity() == 0) {
            for (Item item : getCart().getBooks()) {
                if (item.getBook().getTitle().equals(title)) {
                    foundItem = item;
                }
            }
        }
        getCart().getBooks().remove(foundItem);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succesfully Updated Cart", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return "/chapter09/cart";
    }

    /**
     * @return the cart
     */
    public Cart getCart() {
        return orderFacade.getCart();
    }

    
    /**
     * @return the currentBook
     */
    public Item getCurrentBook() {
        return currentBook;
    }

    /**
     * @param currentBook the currentBook to set
     */
    public void setCurrentBook(Item currentBook) {
        this.currentBook = currentBook;
    }

    public void isBookInCart(ComponentSystemEvent event) {
        UIOutput output = (UIOutput) event.getComponent();
        if (getCart() != null) {
            if (searchCart(getAuthorController().getCurrentBook().getTitle()) > 0) {
                output.setValue("This book is currently in your cart.");
            } else {
                output.setValue("This book is not in your cart.");
            }
        } else {
            output.setValue("This book is not in your cart.");
        }
    }
   /** 
    public void updateRowData(RowEditEvent e) {
        System.out.println("Perform editing logic here...");
        currentBook = (Item)e.getObject();
        // Call the updateCart method, passing the title of the current book.
        updateCart(((Item)e.getObject()).getBook().getTitle());
    }
  */
   
    /**
     * @return the authorController
     */
    public AuthorController getAuthorController() {
        return authorController;
    }

    /**
     * @param authorController the authorController to set
     */
    public void setAuthorController(AuthorController authorController) {
        this.authorController = authorController;
    }
}
