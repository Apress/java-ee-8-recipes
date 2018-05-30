package org.javaee8recipes.jpa.session;

import java.util.concurrent.TimeUnit;
import javax.ejb.*;
import org.javaee8recipes.jpa.object.Cart;

/**
 *
 * @author juneau
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
public class OrderFacade {

    private Cart cart;

    @SuppressWarnings("unused")
    @PrePassivate
    private void prePassivate() {
        System.out.println("In PrePassivate method");
    }

    @SuppressWarnings("unused")
    @PostActivate
    private void postActivate() {
        System.out.println("In PostActivate method");
    }
    
    /**
     * @return the cart
     */
    public Cart getCart() {
        if(cart == null)
            cart = new Cart();
        return cart;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void completePurchase() {
        System.out.println("Not yet implemented..");
    }

    @Remove
    public void destroy() {
        System.out.println("Destroying OrderFacade...");
    }
}
