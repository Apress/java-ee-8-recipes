/*
 * Web service client that consumes JAX-WS and JAX-RS services
 */
package org.javaee8recipes.chapter15.recipe15_01.endpoint.appclient;

import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.javaee8recipes.chapter15.recipe15_01.endpoint.BookstoreService;
import org.javaee8recipes.chapter15.recipe15_01.endpointinterface.BookstoreEndpoint;
import org.javaee8recipes.entity.Contact;

/**
 *
 * @author juneau
 */
public class BookstoreClient {


    public static void main(String[] args) {
        List<Contact> contacts = obtainList();
    }

    public static List<Contact> obtainList() {
        try {
            URL url = new URL("http://localhost:8080/JavaEERecipes/BookstoreServiceService?wsdl");

            QName qname = new QName("http://endpoint.recipe15_01.chapter15.javaee8recipes.org/", "BookstoreServiceService");
            Service service = Service.create(url, qname);
            BookstoreEndpoint bookstore = service.getPort(BookstoreEndpoint.class);
            System.out.println(bookstore.obtainCompleteContactList());
        } catch (Exception e) {
            System.out.println("Exception: "+ e);
        }
        // System.out.println(bookstoreService.obtainCompleteContactList());
        return null;
        //    return port.
    }
}
