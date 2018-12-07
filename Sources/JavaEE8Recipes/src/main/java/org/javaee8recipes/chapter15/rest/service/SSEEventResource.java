/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter15.rest.service;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author Juneau
 */
@Path("sse")
public class SSEEventResource {

    @Resource(name = "DefaultManagedExecutorService")
    ManagedExecutorService executor;

    public SSEEventResource() {

    }

    @GET
    @Path("send")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void send(@Context SseEventSink eventSink,
            @Context Sse sse) {
        executor.execute(() -> {
            try (SseEventSink sink = eventSink) {
                eventSink.send(sse.newEvent("Welcome to the List!"));
                eventSink.send(sse.newEvent("Message One!"));
                eventSink.send(sse.newEvent("SERVER-NOTIFICATION", "Message Two!"));
                eventSink.send(sse.newEventBuilder()
                        .comment("Nice Test")
                        .name("SERVER-TEST")
                        .data("Some data...could be an object")
                        .build());
                eventSink.close();
            }
        });
    }

}
