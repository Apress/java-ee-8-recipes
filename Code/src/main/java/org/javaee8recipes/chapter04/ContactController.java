package org.javaee8recipes.chapter04;

import java.util.*;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Chapter 4
 *
 * @author juneau
 */
@RequestScoped
@Named(value = "contactController")
public class ContactController implements java.io.Serializable {

    @Inject
    SubscriptionController subscriptionController;
    private Contact current;
    private String newsletterDescription;

    private List<String> occupationList;
    private Map<String, Object> allNewsletters;
    private List allNewslettersSI;
    private Map<String, Object> notificationTypes;

    /**
     * Creates a new instance of ContactController
     */
    public ContactController() {

        newsletterDescription = "Enter your information below in order to be "
                + "added to the Acme Bookstore newsletter.";
        populateOccupationList();
        populateNewsletterList();
        // Uncomment for SelectItems List object use
        //populateNewsletterListSI();
        populateNotificationTypes();

    }

    private void populateNotificationTypes() {
        notificationTypes = new HashMap<>();
        notificationTypes.put("Product Updates", "1");
        notificationTypes.put("Best Seller Alerts","2");
        notificationTypes.put("Spam", "3");

    }

    private void populateOccupationList() {
        occupationList = new ArrayList();
        occupationList.add("Author");
        occupationList.add("IT Professional");

    }

    /**
     * Populate newsletter list using a Map
     */
    private void populateNewsletterList() {
        allNewsletters = new LinkedHashMap<String, Object>();
        allNewsletters.put("Java 9 Recipes Weekly", "Java");
        allNewsletters.put("JavaFX Weekly", "FX");
        allNewsletters.put("Oracle PL/SQL Weekly", "Oracle");
        allNewsletters.put("New Books Weekly", "New Books");
    }

    /**
     * Populate newsletter list using SelectItem objects
     */
    private void populateNewsletterListSI() {
        setAllNewslettersSI(new ArrayList());
        getAllNewslettersSI().add(new SelectItem("Java", "Java 9 Recipes Weekly"));
        getAllNewslettersSI().add(new SelectItem("FX", "JavaFX Weekly"));
        getAllNewslettersSI().add(new SelectItem("Oracle", "Oracle PL/SQL Weekly"));
        allNewslettersSI.add(new SelectItem("New Books", "New Books Weekly"));
    }

    /**
     * Obtain the current instance of the Contact object
     *
     * @return Contact
     */
    public Contact getCurrent() {
        if (current == null) {
            current = new Contact();
            current.setReceiveNotifications(true);
        }
        return current;
    }

    /**
     * Adds a subscriber to the newsletter
     *
     * @return String
     */
    public String subscribe() {
        // Using a list implementation for now,
        // but will add to a database table in Chapter 7

        // Add the current contact to the subscription list
        subscriptionController.getSubscriptionList().add(current);


        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Successfully Subscribed to Newsletter for " + getCurrent().getEmail(), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return "SUBSCRIBE";
    }

    public void newsletterBoxListener(ValueChangeEvent event) {
        try {
        Boolean isChecked = (Boolean) event.getNewValue();
        if (isChecked) {
            System.out.println("Checked");
        } else {
            System.out.println("Not Checked");
        }
        } catch(Exception e){
            System.err.println(e);
        }
    }

    /**
     * Navigational method
     *
     * @return String
     */
    public String add() {
        return "ADD_SUBSCRIBER";
    }

    /**
     * This method will allow a user to navigate to the manageAccount view. This
     * method will be moved into another managed bean that focuses on
     * authentication later on.
     *
     * @return
     */
    public String manage() {
        return "/chapter04/manageAccount";
    }

    /**
     * @return the newsletterDescription
     */
    public String getNewsletterDescription() {
        return newsletterDescription;
    }

    /**
     * @param newsletterDescription the newsletterDescription to set
     */
    public void setNewsletterDescription(String newsletterDescription) {
        this.newsletterDescription = newsletterDescription;
    }



    /**
     * Custom validator to ensure that password field contents match
     *
     * @param context
     * @param component
     * @param value
     */
    public void validatePassword(FacesContext context,
            UIComponent component,
            Object value) {
        Map map = context.getExternalContext().getRequestParameterMap();
        String passwordText = (String) map.get(("contactForm:password"));
        String confirmPassword = value.toString();

        if (!passwordText.equals(confirmPassword)) {
            throw new ValidatorException(new FacesMessage("Passwords do not match"));
        }
    }

    /**
     * @return the occupationList
     */
    public List<String> getOccupationList() {
        return occupationList;
    }

    /**
     * @param occupationList the occupationList to set
     */
    public void setOccupationList(List<String> occupationList) {
        this.occupationList = occupationList;
    }

    /**
     * @return the newsletterList
     */
    public Map<String, Object> getAllNewsletters() {
        return allNewsletters;
    }

    /**
     * @param newsletterList the newsletterList to set
     */
    public void setAllNewsletters(Map<String, Object> allNewsletters) {
        this.allNewsletters = allNewsletters;
    }

    /**
     * @return the notificationTypes
     */
    public Map<String, Object> getNotificationTypes() {
        return notificationTypes;
    }

    /**
     * @param notificationTypes the notificationTypes to set
     */
    public void setNotificationTypes(Map<String, Object> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    /**
     * @return the allNewslettersSI
     */
    public List getAllNewslettersSI() {
        return allNewslettersSI;
    }

    /**
     * @param allNewslettersSI the allNewslettersSI to set
     */
    public void setAllNewslettersSI(List allNewslettersSI) {
        this.allNewslettersSI = allNewslettersSI;
    }


}