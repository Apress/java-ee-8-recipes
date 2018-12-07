/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Juneau
 */
@Converter
public class EmployeeStatusConverter implements AttributeConverter<Boolean, String> {
    
    
    @Override
    public String convertToDatabaseColumn(Boolean entityValue) {
        if(entityValue){
            return "ACTIVE";
        } else {
            return "INACTIVE";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String databaseValue) {
        return databaseValue.equals("ACTIVE");
    }
}

