package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;

public class ClassBuilderTest {
  @Test
  public void test_ClassBuilderConstructor_with_string() {
    String str = "{ 'classes' : [{'name' : 'Test'}]}";
    ClassBuilder clb = new ClassBuilder(str);
    Collection<String> classNames = clb.getClassNames();
    assertEquals(classNames.contains("Test"), true);
  }

}
