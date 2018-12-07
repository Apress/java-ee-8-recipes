package org.javaee8recipes.chapter15.rest;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.javaee8recipes.chapter15.recipe15_07.MessageWrapper;
import org.javaee8recipes.chapter15.recipe15_07.MessageWrapperList;

// Set the PATH to http://host:port/application/rest/simplerest/
@Path("/simplerest")
public class SimpleRest {
    
    private String message = "Hello from a simple REST Service";
    private String htmlMessage = "<p><b>" + message + "</b></p>";
    
    @GET
    // Produces plain text message
    @Produces("text/plain")
    public String getPlainMessage() {
        return message;
    }
    
    @GET
    // Produces plain text message
    @Produces("text/html")
    public String getHTMLMessage() {
        return htmlMessage;
    }
    
    @GET
    // Produces an XML message
    @Produces("application/xml")
    public MessageWrapper getXMLMessage() {
        return new MessageWrapper(message);
    }
    
    @GET
    // Produces an XML message
    @Path("all")
    @Produces("application/xml")
    public MessageWrapperList getXMLMessageList() {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("String 1");
        messageList.add("String 2");
        return new MessageWrapperList(messageList);
    }
    
    @PUT
    @Path("add")
    @Consumes("text/plain")
    public String add(@QueryParam("text") String text){
        this.message = text;
        return message;
    }
}