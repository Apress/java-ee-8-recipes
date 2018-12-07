/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter16.recipe16_08;

import javax.json.bind.annotation.JsonbTransient;

/**
 *
 * @author Juneau
 */
public class Dog {
    
    private String name;
    
    private int age;
    
    private String gender;
    
    @JsonbTransient
    private String color;
}
