
package org.javaee8recipes.chapter15.rest.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import org.javaee8recipes.chapter15.rest.interfaces.Alerter;

/**
 *
 * @author Juneau
 */
@Provider
@Alerter
public class AlertFilter implements ContainerRequestFilter,
        ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {
        alert(requestContext);
    }

    @Override
    public void filter(ContainerRequestContext crc, ContainerResponseContext crc1) throws IOException {
        alert(crc);
    }

    public void alert(ContainerRequestContext context) {
        
        try(InputStream in = context.getEntityStream();) {
            if (in != null) {
                InputStreamReader inreader = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(inreader);
                String text = "";

                while ((text = reader.readLine()) != null) {
                    System.out.println(text);

                }

            }
        } catch (IOException ex) {
            // Error handling
        }
    }
}
