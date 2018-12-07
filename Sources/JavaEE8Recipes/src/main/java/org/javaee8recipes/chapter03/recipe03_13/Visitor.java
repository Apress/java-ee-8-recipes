
package org.javaee8recipes.chapter03.recipe03_13;

/**
 * This is a dummy container to represent a visitor object for example 3-18.
 * @author Juneau
 */
public class Visitor {
    private boolean admin = false;

    /**
     * @return the isAdmin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setAdmin(boolean isAdmin) {
        this.admin = admin;
    }
}
