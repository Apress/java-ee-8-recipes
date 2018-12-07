/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter16.recipe16_06;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import org.javaee8recipes.entity.Employee;

/**
 *
 * @author Juneau
 */
@Named("employeeJsonController")
@RequestScoped
public class EmployeeController {
    
    private String lastSearchText;
    
    private String searchResult;
    
    List<Employee> employees;
    
    
    public String fetchJson(){
        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/JavaEERecipes/rest/org.javaee8recipes.entity.employee");


            employees = (target.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .get(
                    new GenericType<List<Employee>>() {
            }));
        System.out.println("Items: " + employees);
        Jsonb jsonb = JsonbBuilder.create();
        String result = null;

        result = jsonb.toJson(employees);
        System.out.println("JSON String: " + result);
        return result;
    }
    
    public List<Employee> fetchJavaFromJson(){
        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/JavaEERecipes/rest/org.javaee8recipes.entity.employee");


            String employeesJson = (target.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                    .get(
                    new GenericType<String>() {
            }));
        System.out.println("Items: " + employeesJson);
        Jsonb jsonb = JsonbBuilder.create();
        List<Employee> employees = new ArrayList();
        employees = jsonb.fromJson(employeesJson, ArrayList.class);
        // Specific Generic type conversion:
        // employees = jsonb.fromJson(employeesJson, new ArrayList<Employee>(){}.getClass().getGenericSuperclass());
        return employees;
    }
    
    public void findEmployeeByLast() {
        setSearchResult(null);
        String text = "/" + this.lastSearchText;
        JsonObject json = Json.createObjectBuilder().build();
        JsonValue object = json.getJsonObject(fetchJson());
        if (lastSearchText != null && object != null) {
            JsonPointer pointer = Json.createPointer(text);
            System.out.println("text: " + text + pointer);
            System.out.println("json: " + object);
            JsonValue result = pointer.getValue(object.asJsonArray());

            // Replace a value
            JsonArray array = (JsonArray) pointer.replace(object.asJsonArray(), Json.createValue("JsonMaster"));
            setSearchResult(array.toString());
        }

    }

    /**
     * @return the lastSearchText
     */
    public String getLastSearchText() {
        return lastSearchText;
    }

    /**
     * @param lastSearchText the lastSearchText to set
     */
    public void setLastSearchText(String lastSearchText) {
        this.lastSearchText = lastSearchText;
    }

    /**
     * @return the searchResult
     */
    public String getSearchResult() {
        return searchResult;
    }

    /**
     * @param searchResult the searchResult to set
     */
    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult;
    }
}
