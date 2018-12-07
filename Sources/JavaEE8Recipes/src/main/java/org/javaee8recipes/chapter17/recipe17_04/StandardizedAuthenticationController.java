/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter17.recipe17_04;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juneau
 */
@Named
@RequestScoped
public class StandardizedAuthenticationController {

    @Inject
    private IdentityStoreHandler identityStoreHandler;
    
    @NotNull
    private String username;
    @NotNull
    private String password;
    
    public void login() {
        System.out.println("in the login");
        FacesContext context = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        
        CredentialValidationResult cres = identityStoreHandler.validate(credential);
        System.out.println("login status: " + cres.getStatus());
        System.out.println("user:" + username);
        System.out.println("password: " + password);
        if (cres.getStatus().equals(CredentialValidationResult.Status.VALID)) {
            // Authentication mechanism has send a redirect, should not
            // send anything to response from JSF now.
            context.responseComplete();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Authentication Successful", null));
        } else if (cres.getStatus().equals(CredentialValidationResult.Status.INVALID)) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication Failure", null));
        }
        
    }
    
    private static HttpServletResponse getResponse(FacesContext context) {
        return (HttpServletResponse) context
            .getExternalContext()
            .getResponse();
    }
    
    private static HttpServletRequest getRequest(FacesContext context) {
        return (HttpServletRequest) context
            .getExternalContext()
            .getRequest();
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
}
