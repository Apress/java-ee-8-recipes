
package org.javaee8recipes.chapter18.recipe18_07;

import javax.batch.api.chunk.ItemProcessor;

/**
 *
 * @author Juneau
 */
public class AcmeProcessor implements ItemProcessor {
    
    public AcmeProcessor(){}

    /**
     * Write out all lines that contain the text "Two"
     * @param item
     * @return
     * @throws Exception 
     */
    @Override
    public WidgetOutputItem processItem(Object item) throws Exception {
        WidgetReportItem widgetReportItem = (WidgetReportItem) item;
        if(widgetReportItem.getLineText().contains("Two")){
            return new WidgetOutputItem(widgetReportItem.getLineText());
        } else {
            return null;
        }
    }
    
}
