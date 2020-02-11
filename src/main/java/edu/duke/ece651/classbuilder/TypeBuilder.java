package edu.duke.ece651.classbuilder;

import org.json.JSONObject;

public class TypeBuilder {
  // Convert primitive into non-primitive type
  public String toNonPrimitive(String toConvert) {
    String res = "";
    switch(toConvert) {
    case "int":
      res = "Integer";
      break;
    case "long":
      res = "Long";
      break;
    case "short":
      res = "Short";
      break;
    case "byte":
      res = "Byte";
      break;
    case "float":
      res = "Float";
      break;
    case "double":
      res = "Double";
      break;
    case "char":
      res = "Character";
      break;
    case "boolean":
      res = "Boolean";
      break;
    // Another type, keep it unchanged
    default:
      return toConvert;
    }
    return res;
  }

  // Build an array type
  public String buildArrayType(JSONObject arrType) {
    // Base case: no nested array any more
    // e.g. {"e": "int"}
    if (arrType.optJSONObject("e") == null) {
      String temp = arrType.getString("e");
      return "ArrayList<" + toNonPrimitive(temp) + ">";
    }
    // Nested array exists
    // e.g. {"e": {"e": {"e": "int"}}}
    else {
      String inner = buildArrayType(arrType.getJSONObject("e"));
      return "ArrayList<" + inner + ">";
    }
  }

  // Build a string representing the content of an array
  public String buildArrayContent(JSONObject arrType) {
    // Non-array content
    if (arrType.optJSONObject("e") == null) {
      return arrType.getString("e");
    }
    // Array content
    else {
      return buildArrayType(arrType.getJSONObject("e"));
    }
  }
  
  // Build a string representing a type with primitive type
  public String buildType(JSONObject oneField) {
    // Not an array field
    if (oneField.optJSONObject("type") == null) {
      return oneField.getString("type");
    }
    // Array field
    else {
      return buildArrayType(oneField.getJSONObject("type"));
    }
  }

}
