package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FullTest {
  @Test
  public void test_full() {
    JSONReader jr = new JSONReader();
    String str = jr.readJSON("src/main/resources/prims.json");
    ClassBuilder clb = new ClassBuilder(str);
    String res = clb.getSourceCode("Prims");
    System.out.println(res);
    clb.createAllClasses("src/main/resources");
  }

}
