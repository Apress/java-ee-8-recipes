
package org.javaee8recipes.chapter15.recipe15_01.endpointinterface;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * Bookstore Web Service Endpoint Interface
 * @author juneau
 */
@WebService
@SOAPBinding(style=Style.RPC)
public interface BookstoreEndpoint {
    @WebMethod String obtainCompleteContactList();
}
