package org.javaee8recipes.chapter09.session;

import java.util.concurrent.TimeUnit;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import org.javaee8recipes.chapter09.object.Cart;

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
    
    public Cart getCart() {
        if(cart == null)
            cart = new Cart();
        return cart;
    }

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
