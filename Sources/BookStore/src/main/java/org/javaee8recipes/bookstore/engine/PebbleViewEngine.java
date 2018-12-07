/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.bookstore.engine;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mvc.engine.ViewEngine;
import javax.mvc.engine.ViewEngineContext;
import javax.mvc.engine.ViewEngineException;
import javax.servlet.ServletContext;

/**
 *
 * @author Juneau
 */
@ApplicationScoped
public class PebbleViewEngine implements ViewEngine {

    @Inject
    private ServletContext servletContext;

    @Override
    public boolean supports(String view) {
        return view.endsWith(".html");
    }

    @Override
    public void processView(ViewEngineContext context) throws ViewEngineException {

        try {

            String viewName = "/WEB-INF/views/" + context.getView();
            URL template = servletContext.getResource(viewName);

            PebbleEngine engine = new PebbleEngine.Builder()
                    .loader(new ServletLoader(servletContext)).build();
           
            
            PebbleTemplate compiledTemplate = engine.getTemplate(viewName);

            Writer writer = new StringWriter();

            compiledTemplate.evaluate(writer, context.getModels());

            context.getResponse().getWriter().write(writer.toString());

        } catch (IOException|PebbleException e) {
            throw new IllegalStateException(e);
        }

    }
    
   
}
