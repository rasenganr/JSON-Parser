package edu.duke.ece651.classbuilder;

import org.json.JSONObject;

public class ImportBuilder {
  // Generate source code for package info and import info
  public String buildImport(JSONObject json) {
        String res = "";
        // Need to generate package info
        if (json.optString("package") != "") {
            res += "package " + json.getString("package") + ";\n\n";
        }
        // Generate import info
        res += "import org.json.*;\n";
        res += "import java.util.*;\n\n";
        return res;
    }
}
