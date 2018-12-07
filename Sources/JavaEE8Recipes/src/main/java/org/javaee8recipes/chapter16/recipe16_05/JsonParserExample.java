/*
 * Recipe 17-5
 */
package org.javaee8recipes.chapter16.recipe16_05;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 *
 * @author Juneau
 */
public class JsonParserExample {

    public void parseObject() {
        Reader fileReader = new InputStreamReader(getClass().getResourceAsStream("BookObject.json"));
        JsonParser parser = Json.createParser(fileReader);
  //      Iterator<Event> it = parser.iterator();
  //      while (it.hasNext()) {
  //          Event ev = it.next();
  //          System.out.println(ev);
  //      }
    }

    public static void main(String[] args) {
        JsonParserExample ex = new JsonParserExample();
        ex.parseObject();
    }
}
