/*
 * JAX-WS web service endpoint class
 * 
 */
package org.javaee8recipes.chapter15.recipe15_01.endpoint;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceClient;
import org.javaee8recipes.chapter15.recipe15_01.endpointinterface.BookstoreEndpoint;
import org.javaee8recipes.entity.Contact;
import org.javaee8recipes.chapter09.session.ContactFacade;


@WebService(endpointInterface="org.javaee8recipes.chapter15.recipe15_01.endpointinterface.BookstoreEndpoint")
//@WebService(serviceName="BookstoreService")
//@WebServiceClient(name="BookstoreService",
//                  targetNamespace="http://localhost:8080",
//                  wsdlLocation="http://localhost:8080/JavaEERecipes/BookstoreServiceService?WSDL")
public class BookstoreService implements BookstoreEndpoint {
    
    
    @EJB
    ContactFacade contactFacade;
    
    public void BookstoreService(){
        
    }
    
    @Override
    //@WebMethod
    public String obtainCompleteContactList(){
        StringBuilder sb = new StringBuilder();
        sb.append("Here is the new JAX-WS Web Service\n");
        List<Contact> contacts = contactFacade.findAll();
        for(Contact contact: contacts){
            sb.append(contact.getEmail() + "\n");
        }
        return sb.toString();
    }
}
