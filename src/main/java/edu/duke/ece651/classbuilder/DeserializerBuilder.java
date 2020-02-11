package edu.duke.ece651.classbuilder;

import org.json.JSONArray;
import org.json.JSONObject;

public class DeserializerBuilder {
  // Capitalize the first character of a string
  private String toCapital(String str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }
  
  // Build the sentence of "get" from JSONObject of a field
  private String buildGetField(String fieldType, String fieldName) {
    // Get and int
    if (fieldType.equals("int")) {
      return "oneField.getInt(\"" + fieldName + "\")";
    }
    // Get an int and cast it to byte
    else if (fieldType.equals("byte")) {
      return "(byte)(oneField.getInt(\"" + fieldName + "\"))";
    }
    // Get an int and cast it to short
    else if (fieldType.equals("short")) {
      return "(short)(oneField.getInt(\"" + fieldName + "\"))";
    }
    // Get a string and take its first character
    else if (fieldType.equals("char")) {
      return "oneField.getString(\"" + fieldName + "\").charAt(0)";
    }
    // Get a variable of proper type
    else {
      return "oneField.get" + toCapital(fieldType) + "(\"" + fieldName + "\")";
    }

  }
  
  // Build the sentence of "set" (for array fields, "add")
  private String buildSetField(String fieldType, String fieldName) {
    String res = "";
    PrimitiveChecker pc = new PrimitiveChecker();
    // Field of primitive type or string
    // Deal with indirect "get" of JSONObject
    if (pc.isPrimitiveOrString(fieldType)) {
      res += "\t\tres.set" + toCapital(fieldName) + "(" + buildGetField(fieldType, fieldName) + ");\n";
    }
    // Array field
    else if (fieldType.length() > 11 && fieldType.substring(0, 10).equals("ArrayList<")) {
      // Check each element in this array
      res += "\t\tfor (int i = 0; i < oneField.getJSONArray(\"" + fieldName + "\").length(); ++i) {\n";
      String content = fieldType.substring(10, fieldType.length() - 1);
      // The content is primitive type or string
      if (pc.isWrapperOrString(content)) {
        res += "\t\t\tres.add" + toCapital(fieldName) + "((" + content + ")(oneField.getJSONArray(\"" + fieldName + "\").get(i)));\n";
      }
      // The content is objects of another class
      else {
        res += "\t\t\tres.add" + toCapital(fieldName) + "(read" + content + "Helper(mp, oneField.getJSONArray(\"" + fieldName + "\").getJSONObject(i)));\n";
      }
      res += "\t\t}\n";
    }
    // Field of another class
    else {
      res += "\t\tres.set" + toCapital(fieldName) + "(read" + fieldType + "Helper(mp, oneField.getJSONObject(\"" + fieldName + "\")));\n";
    }
    return res;
  }
  
  // Build a "read" function for a class
  // This function creates a HashMap and calls its own helper function
  public String buildRead(String className) {
    String res = "";
    res += "public static " + className + " read" + className + "(JSONObject js) throws JSONException {\n";
    // HashMap: Unique id - Generated object
    res += "\tHashMap<Integer, Object> mp = new HashMap<Integer, Object>();\n";
    res += "\treturn read" + className + "Helper(mp, js);\n";
    res += "}\n\n";
    return res;
  }
  
  // Build a helper function for "read" of a class
  // This helper function might call helper functions of objects of other classes
  public String buildReadHelper(String className, JSONArray fields) {
    TypeBuilder tb = new TypeBuilder();
    String res = "";
    res += "public static " + className + " read" + className
        + "Helper(HashMap<Integer, Object> mp, JSONObject js) throws JSONException {\n";
    // Repeated object, use "ref"
    res += "\tif (!js.optString(\"ref\").equals(\"\")) {\n";
    res += "\t\treturn (" + className + ")(mp.get(js.getInt(\"ref\")));\n";
    res += "\t}\n";
    // New object, create one
    res += "\telse {\n";
    res += "\t\t" + className + " res = new " + className + "();\n";
    res += "\t\tmp.put(js.getInt(\"id\"), res);\n";
    res += "\t\tJSONArray fields = js.getJSONArray(\"values\");\n";
    res += "\t\tJSONObject oneField = new JSONObject();\n";
    // Generate source code for every field
    for (int i = 0; i < fields.length(); ++i) {
      JSONObject oneField = fields.getJSONObject(i);
      String fieldName = oneField.getString("name");
      String fieldType = tb.buildType(oneField);
      res += "\t\toneField = fields.getJSONObject(" + i + ");\n";
      res += buildSetField(fieldType, fieldName);
    }
    res += "\t\treturn res;\n";
    res += "\t}\n";
    res += "}\n\n";
    
    return res;
  }
  
  // Build a deserializer
  // This class contains 2 methods per generated class (readX + readXHelper)
  public String buildDeserializer(JSONArray classes) {
    String res = "";
    res += "public class Deserializer {\n";
    for (int i = 0; i < classes.length(); ++i) {
      JSONObject oneClass = classes.getJSONObject(i);
      String className = oneClass.getString("name");
      JSONArray fields = new JSONArray();
      // This class has some fields
      if (oneClass.optJSONArray("fields") != null) {
        fields = oneClass.getJSONArray("fields");
      }
      // If this class has no field, pass an empty JSONArray
      // Generate source code for readHelper method
      res += buildReadHelper(className, fields);
      // Generate source code for read method
      res += buildRead(className);
    }
    res += "}\n";
    return res;
  }
}
