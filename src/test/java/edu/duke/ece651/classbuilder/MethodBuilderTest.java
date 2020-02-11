package edu.duke.ece651.classbuilder;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

public class MethodBuilderTest {
  @Test
  public void test_buildMethods() {
    String str = "[ {'name' : 'x', 'type' : 'int'}, {'name' : 'grades', 'type' : {'e': 'Grade'}}]";
    JSONArray jarr = new JSONArray(str);
    MethodBuilder mb = new MethodBuilder();
  }

}
