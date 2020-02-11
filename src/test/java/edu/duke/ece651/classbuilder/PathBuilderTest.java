package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class PathBuilderTest {
  @Test
  public void test_buildPath() {
    PathBuilder pb = new PathBuilder();
    String str = "{'package' : 'edu.duke.ece651.hwk1.data'}";
    JSONObject json = new JSONObject(str);
    assertEquals("a/b/c/edu/duke/ece651/hwk1/data/", pb.buildPath("a/b/c", json));
  }

}
