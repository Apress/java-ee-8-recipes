package org.javaee8recipes.chapter18.recipe18_07;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.batch.api.chunk.AbstractItemReader;

/**
 * Example of a file reading task
 *
 * @author Juneau
 */
public class AcmeReader extends AbstractItemReader {

    public AcmeReader() {
    }

    /**
     * Read lines of report and store each into a WidgetReportItem object.  Once
     * all lines have been read then return null to trigger the end of file.
     * @return
     * @throws Exception 
     */
    @Override
    public WidgetReportItem readItem() throws Exception {
        Path file = Paths.get("widgetFile.txt");
        List<String> fileLines;
        Charset charset = Charset.forName("US-ASCII");
        fileLines = Files.readAllLines(file, charset);
        for(String line:fileLines){
            return new WidgetReportItem(line);
        }
        return null;
        
    }
}
