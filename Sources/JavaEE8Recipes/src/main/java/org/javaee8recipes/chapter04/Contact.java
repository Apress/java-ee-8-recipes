
package org.javaee8recipes.chapter04;

import java.util.List;
import javax.validation.constraints.Pattern;

/**
 * Chapter 4
 * @author juneau
 */
public class Contact {
    private String first;
    private String last;
    
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", message = "Email format is invalid.")
    private String email;
    private String password;
    private String description;
    private String occupation;
    private List <String> newsletterList;
    private boolean receiveNotifications;
    private String[] notificationType;
    private String gender;
    
    public Contact(){
        first = null;
        last = null;
        email = null;
        password = null;
        description = null;
        occupation = null;
        newsletterList = null;
        receiveNotifications = false;
        gender = null;
    }
    
    public Contact(String first, String last){
        this.first = first;
        this.last = last;
        email = null;
        password = null;
        description = null;
        occupation = null;
        newsletterList = null;
    }
    
    

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation the occupation to set
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * @return the newsletterList
     */
    public List <String> getNewsletterList() {
        return newsletterList;
    }

    /**
     * @param newsletterList the newsletterList to set
     */
    public void setNewsletterList(List <String> newsletterList) {
        this.newsletterList = newsletterList;
    }

    /**
     * @return the receiveNotifications
     */
    public boolean isReceiveNotifications() {
        return receiveNotifications;
    }

    /**
     * @param receiveNotifications the receiveNotifications to set
     */
    public void setReceiveNotifications(boolean receiveNotifications) {
        this.receiveNotifications = receiveNotifications;
    }

    /**
     * @return the notificationType
     */
    public String[] getNotificationType() {
        return notificationType;
    }

    /**
     * @param notificationType the notificationType to set
     */
    public void setNotificationType(String[] notificationType) {
        this.notificationType = notificationType;
    }

    /**
        * @return the gender
        */
    public String getGender() {
        return gender;
    }

    /**
        * @param gender the gender to set
        */
    public void setGender(String gender) {
        this.gender = gender;
    }
}


