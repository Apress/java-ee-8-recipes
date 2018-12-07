package org.javaee8recipes.chapter05;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Chapter 6
 *
 * @author juneau
 */
@SessionScoped
@Named("ch5ContactController")
public class ContactController implements java.io.Serializable {

    @Inject
    private SubscriptionController subscriptionController;
    
    private Contact current;
    private String newsletterDescription;
    private String passwordConfirm;
    private List<String> occupationList;
    private Map<String, Object> newsletterList;
    private List newsletterListSI;
    private String[] notificationTypes;
    private String passwordStrengthMessage;

    /**
     * Creates a new instance of ContactController
     */
    public ContactController() {
        current = null;
        passwordConfirm = null;
        newsletterDescription = "Enter your information below in order to be "
                + "added to the Acme Bookstore newsletter.";
        passwordStrengthMessage = null;
        populateOccupationList();
        populateNewsletterList();
        // Uncomment for SelectItems List object use
        //populateNewsletterListSI();
        populateNotificationTypes();

    }

    private void populateNotificationTypes() {
        notificationTypes = new String[3];
        notificationTypes[0] = "Product Updates";
        notificationTypes[1] = "Best Seller Alerts";
        notificationTypes[2] = "Spam";

    }

    private void populateOccupationList() {
        System.out.println("Populating occupations");
        occupationList = new ArrayList();
        occupationList.add("Author");
        occupationList.add("IT Professional");

    }

    /**
     * Populate newsletter list using a Map
     */
    private void populateNewsletterList() {
        newsletterList = new LinkedHashMap<String, Object>();
        newsletterList.put("Java 7 Recipes Weekly", "Java");
        newsletterList.put("JavaFX Weekly", "FX");
        newsletterList.put("Oracle PL/SQL Weekly", "Oracle");
        newsletterList.put("New Books Weekly", "New Books");
    }

    /**
     * Populate newsletter list using SelectItem objects
     */
    private void populateNewsletterListSI() {
        newsletterListSI = new ArrayList();
        newsletterListSI.add(new SelectItem("Java", "Java 7 Recipes Weekly"));
        newsletterListSI.add(new SelectItem("FX", "JavaFX Weekly"));
        newsletterListSI.add(new SelectItem("Oracle", "Oracle PL/SQL Weekly"));
        newsletterListSI.add(new SelectItem("New Books", "New Books Weekly"));
    }

    /**
     * Obtain the current instance of the Contact object
     *
     * @return Contact
     */
    public Contact getCurrent() {
        if (current == null) {
            current = new Contact();
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
        System.out.println("Here....submitting...");
        getSubscriptionController().getSubscriptionList().add(current);
       
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Successfully Subscribed to Newsletter for " + getCurrent().getEmail(), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return "/chapter06/subscribe";
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
        return "/chapter06/subscribe";
    }

    /**
     * This method will allow a user to navigate to the manageAccount view. This
     * method will be moved into another managed bean that focuses on
     * authentication later on.
     *
     * @return
     */
    public String manage() {
        return "/chapter06/manageAccount";
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
     * @return the passwordConfirm
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * @param passwordConfirm the passwordConfirm to set
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

        if (!passwordText.equals(value.toString())) {
            throw new ValidatorException(new FacesMessage("Passwords do not match"));
        }
    }
    
    public void passwordStrength(AjaxBehaviorEvent event){
        UIInput password = (UIInput) event.getComponent();
        boolean isStrong = false;
        String input = password.getValue().toString();
        
        if(input.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,})")) {
            isStrong = true;
        }
        
        if(isStrong == true){
            setPasswordStrengthMessage("Password is strong");
        } else {
            setPasswordStrengthMessage("Password is weak");
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
    public Map<String, Object> getNewsletterList() {
        return newsletterList;
    }

    /**
     * @param newsletterList the newsletterList to set
     */
    public void setNewsletterList(Map<String, Object> newsletterList) {
        this.newsletterList = newsletterList;
    }

    /**
     * @return the notificationTypes
     */
    public String[] getNotificationTypes() {
        return notificationTypes;
    }

    /**
     * @param notificationTypes the notificationTypes to set
     */
    public void setNotificationTypes(String[] notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    /**
     * @return the newsletterListSI
     */
    public List getNewsletterListSI() {
        return newsletterListSI;
    }

    /**
     * @param newsletterListSI the newsletterListSI to set
     */
    public void setNewsletterListSI(List newsletterListSI) {
        this.newsletterListSI = newsletterListSI;
    }

    /**
     * @return the passwordStrengthMessage
     */
    public String getPasswordStrengthMessage() {
        return passwordStrengthMessage;
    }

    /**
     * @param passwordStrengthMessage the passwordStrengthMessage to set
     */
    public void setPasswordStrengthMessage(String passwordStrengthMessage) {
        this.passwordStrengthMessage = passwordStrengthMessage;
    }

    /**
     * @return the subscriptionController
     */
    public SubscriptionController getSubscriptionController() {
        return subscriptionController;
    }

    /**
     * @param subscriptionController the subscriptionController to set
     */
    public void setSubscriptionController(SubscriptionController subscriptionController) {
        this.subscriptionController = subscriptionController;
    }
}