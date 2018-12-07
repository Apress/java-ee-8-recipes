
package org.javaee8recipes.chapter18.recipe18_07;

import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;

/**
 *
 * @author Juneau
 */
public class AcmeWriter extends AbstractItemWriter {

    @Override
    public void writeItems(List<Object> list) throws Exception {
        for(Object item:list){
            WidgetOutputItem out = (WidgetOutputItem) item;
            System.out.println("Write to file:" + out.getLineText());
        }
    }
    
}
