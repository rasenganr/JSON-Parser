package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class JSONReaderTest {
  @Test
  public void test_readJSON() {
    JSONReader jr = new JSONReader();
    String str = new JSONObject("{ 'classes' : [\n{'name' : 'Test', 'fields' : [{'name' : 'x', 'type' : 'int'}]}\n]}").toString();
    assertEquals(str, new JSONObject(jr.readJSON("src/main/resources/simple.json")).toString());
  }

}
