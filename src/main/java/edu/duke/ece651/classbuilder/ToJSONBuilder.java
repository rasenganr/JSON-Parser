package edu.duke.ece651.classbuilder;

import org.json.JSONArray;
import org.json.JSONObject;

public class ToJSONBuilder {
  // Generate source code for toJSONHelper
  // This helper function might call toJSONHelper of objects of other classes
  public String buildToJSONHelper(JSONArray fields) {
    TypeBuilder tb = new TypeBuilder();
    PrimitiveChecker pc = new PrimitiveChecker();
    String res = "";
    res += "public String toJSONHelper(HashMap<Object, Integer> mp) throws JSONException {\n";
    res += "\tString className = this.getClass().getSimpleName();\n";
    // This object has been recorded, so use "ref"
    res += "\tif (mp.containsKey(this)) {\n";
    res += "\t\treturn \"{\\\"ref\\\" : \" + mp.get(this) + \"}\";\n";
    res += "\t}\n";
    // This object needs to be recorded
    res += "\telse {\n";
    // Set the unique id as the map size
    res += "\t\tint id = mp.size() + 1;\n";
    res += "\t\tString id_str = \"\\\"id\\\" : \" + Integer.toString(id) + \", \";\n";
    // Set the type as the class name
    res += "\t\tString type_str = \"\\\"type\\\" : \" + className + \", \";\n";
    // Maintain this map for DFS
    res += "\t\tmp.put(this, id);\n";
    // Generate source code for each field
    res += "\t\tString inner = \"\";\n";
    for (int i = 0; i < fields.length(); ++i) {
      JSONObject oneField = fields.getJSONObject(i);
      String fieldName = oneField.getString("name");
      String fieldType = tb.buildType(oneField);
      // String field
      // Store it as a string
      if (fieldType.equals("String")) {
        res += "\t\tinner += \"{\\\"" + fieldName + "\\\" : \\\"\" + " + fieldName + " + \"\\\"}, \";\n";
      }
      // Field of primitive type
      // Store it as its own type
      else if (pc.isPrimitiveOrString(fieldType)) {
        res += "\t\tinner += \"{\\\"" + fieldName + "\\\" : \" + " + fieldName + " + \"}, \";\n";
      }
      // Array field
      else if (fieldType.length() > 11 && fieldType.substring(0, 10).equals("ArrayList<")) {
        String content = fieldType.substring(10, fieldType.length() - 1);
        res += "\t\tinner += \"{\\\"" + fieldName + "\\\" : [\";\n";
        // The content is primitive type or string
        // Simply record its value
        if (pc.isWrapperOrString(content)) {
          res += "\t\tfor (int i = 0; i < " + fieldName + ".size(); ++i) {\n";
          res += "\t\t\tinner += " + fieldName + ".get(i) + \", \";\n";
          res += "\t\t}\n";
        }
        // The content is objects of another class
        // Call each object's toJSONHelper to get its content
        else{
          res += "\t\tfor (int i = 0; i < " + fieldName + ".size(); ++i) {\n";
          res += "\t\t\tinner += " + fieldName + ".get(i).toJSONHelper(mp) + \", \";\n";
          res += "\t\t}\n";
        }
        res += "\t\tinner += \"]}, \";\n";
      }
      // Field of another class
      // Call this object's toJSONHelper to get its content
      else {
        res += "\t\tinner += \"{\\\"" + fieldName + "\\\" : \" + " + fieldName + ".toJSONHelper(mp) + \"}, \";\n";
      }
    }
    // Gather the source code for each part
    res += "\t\tString values_str = \"\\\"values\\\" : [\" + inner + \"]\";\n";
    res += "\t\tString res = \"{\" + id_str + type_str + values_str + \"}\";\n";
    res += "\t\treturn res;\n";
    res += "\t}\n";
    res += "}\n\n";
    return res;
  }

  // Generate source code for toJSON
  // This method creates a HashMap and calls its own helper function
  public String buildToJSON() {
    String res = "";
    res += "public JSONObject toJSON() throws JSONException {\n";
    // HashMap: Generated object - Unique id
    res += "\tHashMap<Object, Integer> mp = new HashMap<Object, Integer>();\n";
    res += "\tString str = toJSONHelper(mp);\n";
    // Generated JSONObject
    res += "\tJSONObject res = new JSONObject(str);\n";
    res += "\treturn res;\n";
    res += "}\n";
    return res;
  }
}
