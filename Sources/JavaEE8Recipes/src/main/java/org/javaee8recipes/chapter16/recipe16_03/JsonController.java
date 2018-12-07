/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter16.recipe16_03;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import org.javaee8recipes.entity.BookAuthor;
import org.javaee8recipes.chapter09.session.BookAuthorFacade;

@Named(value = "jsonController")
@RequestScoped
public class JsonController {

    @EJB
    BookAuthorFacade bookAuthorFacade;
    private String authorJson;
    
    @PostConstruct
    public void init(){
        buildAuthors();
    }

    public void buildAuthors() {
        List<BookAuthor> authors = bookAuthorFacade.findAll();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        StringBuilder json = new StringBuilder();
        try (StringWriter sw = new StringWriter();) {
            for (BookAuthor author : authors) {
                System.out.println("author" + author.getLast());
                builder.add("author", Json.createObjectBuilder()
                        .add("authorId", author.getId())
                        .add("first", author.getFirst())
                        .add("last", author.getLast())
                        .add("bio", author.getBio()));

            }
            JsonObject result = builder.build();

            try (JsonWriter writer = Json.createWriter(sw)) {
                writer.writeObject(result);
            }
            json.append(sw.toString());
            setAuthorJson(json.toString());
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public JsonObject buildAuthorsJson() {
        List<BookAuthor> authors = bookAuthorFacade.findAll();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject result = null;
        StringBuilder json = new StringBuilder();
        try (StringWriter sw = new StringWriter();) {
            for (BookAuthor author : authors) {
                System.out.println("author" + author.getLast());
                builder.add("author", Json.createObjectBuilder()
                        .add("authorId", author.getId())
                        .add("first", author.getFirst())
                        .add("last", author.getLast())
                        .add("bio", author.getBio()));

            }
            result = builder.build();

            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    public void writeJson() {
        try {
            JsonObject jsonObject = buildAuthorsJson();

            javax.json.JsonWriter jsonWriter = Json.createWriter(new FileWriter("Authors.json"));
            
            jsonWriter.writeObject(jsonObject);
            jsonWriter.close();


            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "JSON Built",
                "JSON Built"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public String buildAndReturnAuthors() {
        List<BookAuthor> authors = bookAuthorFacade.findAll();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        StringBuilder json = new StringBuilder();
        JsonObjectBuilder object = null;
        for (BookAuthor author : authors) {
            object = builder.add("authorId", author.getId())
                    .add("first", author.getFirst())
                    .add("last", author.getLast())
                    .add("bio", author.getBio());

        }
        builder.add("author", object);
        JsonObject result = builder.build();
        StringWriter sw = new StringWriter();
        try (JsonWriter writer = Json.createWriter(sw)) {
            writer.writeObject(result);
        }
        json.append(sw.toString());
        return json.toString();
    }

    public String readObject() {
        InputStream in = new ByteArrayInputStream(buildAndReturnAuthors().getBytes());
         // or
        //Reader fileReader = new InputStreamReader(getClass().getResourceAsStream("AuthorObject.json"));
        //JsonReader reader = Json.createReader(fileReader);
        JsonReader reader = Json.createReader(in);
        JsonObject obj = reader.readObject();
        return obj.toString();
              
    }
    
    /**
     * @return the authorJson
     */
    public String getAuthorJson() {
        return authorJson;
    }

    /**
     * @param authorJson the authorJson to set
     */
    public void setAuthorJson(String authorJson) {
        this.authorJson = authorJson;
    }
}
