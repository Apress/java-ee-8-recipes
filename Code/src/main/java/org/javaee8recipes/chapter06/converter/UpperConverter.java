package org.javaee8recipes.chapter06.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author juneau
 */
@FacesConverter("org.javaeerecipes.converter.UpperConverter")
public class UpperConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        // Return String value in upper case
        return value.toString().toUpperCase();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {

        // Return String value
        return value.toString().toUpperCase();

    }
}
