package edu.duke.ece651.classbuilder;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConstructorBuilder {
  // Build an initialization for an array field
  public String buildInitialization(JSONObject oneField) {
    TypeBuilder tb = new TypeBuilder();
    // Field name
    String name = oneField.getString("name");
    // The content of this array field
    String content = tb.buildArrayContent(oneField.getJSONObject("type"));
    return "\t" + name + " = new ArrayList<" + tb.toNonPrimitive(content) + ">();\n";
  }
  
  // Build a constructor
  public String buildConstructor(String className, JSONArray fields) {
    String res = "";
    // Check each field for array ones
    for (int i = 0; i < fields.length(); ++i) {
      JSONObject oneField = fields.getJSONObject(i);
      // Array field needs initialization
      if (oneField.optJSONObject("type") != null) {
        res += buildInitialization(oneField);
      }
    }
    // No array field
    if (res == "") {
      return "";
    }
    // Has array field
    // Add a constructor
    else {
      return "public " + className + "() {\n" + res + "}\n\n";
    }
  }
}
