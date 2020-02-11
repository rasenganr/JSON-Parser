package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

public class ConstructorBuilderTest {
  @Test
  public void test_buildConstructor() {
    ConstructorBuilder cb = new ConstructorBuilder();
    String str = "[ {'name' : 'instructor', 'type' : 'String'}, {'name' : 'numStudents', 'type' : 'int' }, {'name' : 'grades', 'type' : {'e': 'int'}}]";
    JSONArray fields = new JSONArray(str);
    String res = "";
    res += "public Test() {\n";
    res += "\tgrades = new ArrayList<Integer>();\n";
    res += "}\n\n";
    assertEquals(res, cb.buildConstructor("Test", fields));
  }

}
