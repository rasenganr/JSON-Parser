package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class ImportBuilderTest {
  @Test
  public void test_buildImport() {
    ImportBuilder ib = new ImportBuilder();
    String str = "{'package' : 'edu.duke.ece651.hwk1.data'}";
    JSONObject json = new JSONObject(str);
    String res = "";
    res += "package edu.duke.ece651.hwk1.data;\n\n";
    res += "import org.json.*;\n";
    res += "import java.util.*;\n\n";
    assertEquals(res, ib.buildImport(json));
  }

}
