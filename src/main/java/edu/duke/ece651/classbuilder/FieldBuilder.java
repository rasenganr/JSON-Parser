package edu.duke.ece651.classbuilder;

import org.json.*;

public class FieldBuilder {  
  // Build a string representing a fields name
  public String buildFieldName(JSONObject oneField) {
    return oneField.getString("name");
  }
  
  // Build a field that is not an array
  // Every field is private
  public String buildOneField(JSONObject oneField) {
    TypeBuilder tb = new TypeBuilder();
    return "private " + tb.buildType(oneField) + " " + buildFieldName(oneField) + ";";
  }

  // Build all the fields
  public String buildAllFields(JSONArray fields) {
    String res = "";
    // Build each field
    for (int i = 0; i < fields.length(); ++i) {
      JSONObject oneField = fields.getJSONObject(i);
      res += buildOneField(oneField) + "\n";
    }
    return res;
  }
}
