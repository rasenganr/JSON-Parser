package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class TypeBuilderTest {
  @Test
  public void test_toNonPrimitive() {
    TypeBuilder tb = new TypeBuilder();
    assertEquals(tb.toNonPrimitive("int"), "Integer");
    assertEquals(tb.toNonPrimitive("long"), "Long");
    assertEquals(tb.toNonPrimitive("short"), "Short");
    assertEquals(tb.toNonPrimitive("byte"), "Byte");
    assertEquals(tb.toNonPrimitive("float"), "Float");
    assertEquals(tb.toNonPrimitive("double"), "Double");
    assertEquals(tb.toNonPrimitive("char"), "Character");
    assertEquals(tb.toNonPrimitive("boolean"), "Boolean");
    assertEquals(tb.toNonPrimitive("String"), "String");
    assertEquals(tb.toNonPrimitive("Grade"), "Grade");
    assertEquals(tb.toNonPrimitive("Integer"), "Integer");
  }

  @Test
  public void test_buildType() {
    TypeBuilder tb = new TypeBuilder();
    JSONObject field1 = new JSONObject("{'name' : 'instructor', 'type' : 'Faculty'}");
    JSONObject field2 = new JSONObject("{'name' : 'grades', 'type' : {'e': 'Grade'}}");
    JSONObject field3 = new JSONObject("{'name' : 'numbers', 'type' : {'e': 'int'}}");
    JSONObject field4 = new JSONObject("{'name' : 'board', 'type' : {'e': {'e' : 'int'}}}");
    assertEquals(tb.buildType(field1), "Faculty");
    assertEquals(tb.buildType(field2), "ArrayList<Grade>");
    assertEquals(tb.buildType(field3), "ArrayList<Integer>");
    assertEquals(tb.buildType(field4), "ArrayList<ArrayList<Integer>>");
    assertEquals(tb.buildArrayContent(field4.getJSONObject("type")), "ArrayList<Integer>");
  }

}
