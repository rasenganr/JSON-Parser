package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

public class CreateAllClassesTest {
  @Test
  public void test_createAllClasses() {
    JSONReader jr = new JSONReader();
    InputStream is = jr.readJSONToInputStream("src/main/resources/array.json");
    ClassBuilder clb = new ClassBuilder(is);
    clb.createAllClasses("src/main/resources/generate/");
  }
}
