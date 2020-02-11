package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class FieldBuilderTest {
  @Test
  public void test_buildOneField_without_array() {
    FieldBuilder fb = new FieldBuilder();
    String str = "{'name' : 'x', 'type' : 'int'}";
    JSONObject obj = new JSONObject(str);
    assertEquals("private int x;", fb.buildOneField(obj));
  }

  @Test
  public void test_buildAllFields_without_array() {
    FieldBuilder fb = new FieldBuilder();
    String str = "[{'name' : 'x', 'type' : 'int'}, {'name' : 'y', 'type' : 'String' }]";
    JSONArray jarr = new JSONArray(str);
    assertEquals("private int x;\nprivate String y;\n", fb.buildAllFields(jarr));
  }
  
  @Test
  public void test_buildOneField_with_array() {
    FieldBuilder fb = new FieldBuilder();
    String str = "{'name' : 'grades', 'type' : {'e': {'e': 'Grade'}}}";
    JSONObject obj = new JSONObject(str);
    assertEquals("private ArrayList<ArrayList<Grade>> grades;", fb.buildOneField(obj));
  }

  @Test
  public void test_buildAllFields_with_array() {
    FieldBuilder fb = new FieldBuilder();
    String str = "[{'name' : 'x', 'type' : 'int'}, {'name' : 'grades', 'type' : {'e': {'e': 'Grade'}}}]";
    JSONArray jarr = new JSONArray(str);
    assertEquals("private int x;\nprivate ArrayList<ArrayList<Grade>> grades;\n", fb.buildAllFields(jarr));
  }
}
