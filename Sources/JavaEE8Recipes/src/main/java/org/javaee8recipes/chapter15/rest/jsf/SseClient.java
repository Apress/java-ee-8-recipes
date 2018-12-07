/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter15.rest.jsf;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

/**
 *
 * @author Juneau
 */
@Named
@RequestScoped
public class SseClient {

    private Client client;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
    }

    public void listen() {
        WebTarget target = client.target("http://localhost:8080/JavaEERecipes/rest/ssebroadcaster/register");
        try (SseEventSource source = SseEventSource.target(target).build()) {
            source.register(System.out::println);
            source.open();
            Thread.sleep(250); // Consume events for 250 ms
            source.close();
        } catch (InterruptedException e) {
            
        }
    }
}
