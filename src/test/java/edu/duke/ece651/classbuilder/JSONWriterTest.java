package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JSONWriterTest {
  @Test
  public void test_writeJSON() {
    JSONWriter jw = new JSONWriter();
    jw.writeJSON("src/main/resources/aaa/bbb/", "Test", "Just a test");
  }

}
