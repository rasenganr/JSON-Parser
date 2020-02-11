package edu.duke.ece651.classbuilder;

import org.json.JSONArray;
import org.json.JSONObject;

public class MethodBuilder {
  // Generate "getX" for non-array fields
  // e.g. public int getNumStudents()
  public String buildGetX_nonarray(String name, String type) {
    String res = "";
    String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
    res += "public " + type + " get" + capName + "() {\n";
    res += "\treturn " + name + ";\n";
    res += "}\n\n";
    return res;
  }

  // Generate "getX" for array fields
  // e.g. public int getNames(int index)
  public String buildGetX_array(String name, String content) {
    String res = "";
    String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
    res += "public " + content + " get" + capName + "(int index) {\n";
    res += "\treturn " + name + ".get(index);\n";
    res += "}\n\n";
    return res;
  }

  // Generate "setX" for non-array fields
  // e.g. pubic void setNumStudents(int value)
  public String buildSetX_nonarray(String name, String type) {
    String res = "";
    String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
    res += "public void set" + capName + "(" + type + " value) {\n";
    res += "\t" + name + " = value;\n";
    res += "}\n\n";
    return res;
  }

  // Generate "setX" for array fields
  // e.g. public void setNames(int index, String value)
  public String buildSetX_array(String name, String content) {
    String res = "";
    String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
    res += "public void set" + capName + "(int index, " + content + " value) {\n";
    res += "\t" + name + ".set(index, value);\n";
    res += "}\n\n";
    return res;
  }

  // Generate "addX" for array fields
  // e.g. public void addNames(String toAdd)
  public String buildAddX(String name, String content) {
    String res = "";
    String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
    res += "public void add" + capName + "(" + content + " toAdd) {\n";
    res += "\t" + name + ".add(toAdd);\n";
    res += "}\n\n";
    return res;
  }

  // Generate "numX" for array fields
  // e.g. public int numNames()
  public String buildNumX(String name) {
    String res = "";
    String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
    res += "public int num" + capName + "() {\n";
    res += "\treturn " + name + ".size();\n";
    res += "}\n\n";
    return res;
  }
  
  // Generate source code of methods for all fields
  public String buildMethods(JSONArray fields) {
    String res = "";
    FieldBuilder fb = new FieldBuilder();
    TypeBuilder tb = new TypeBuilder();
    // Generate source code for every field
    for (int i = 0; i < fields.length(); ++i) {
      JSONObject oneField = fields.getJSONObject(i);
      String fieldName = fb.buildFieldName(oneField);
      String fieldType = tb.buildType(oneField);
      // Non-array field
      // Only has get and set methods
      if (oneField.optJSONObject("type") == null) {
        res += buildGetX_nonarray(fieldName, fieldType);
        res += buildSetX_nonarray(fieldName, fieldType);
      }
      // Array Field
      // Has get, set, add and num methods
      else {
        String content = tb.buildArrayContent(oneField.getJSONObject("type"));
        res += buildGetX_array(fieldName, content);
        res += buildSetX_array(fieldName, content);
        res += buildAddX(fieldName, content);
        res += buildNumX(fieldName);
      }
    }
    return res;
  }
}
