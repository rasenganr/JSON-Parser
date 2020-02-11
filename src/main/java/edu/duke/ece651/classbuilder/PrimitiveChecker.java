package edu.duke.ece651.classbuilder;

public class PrimitiveChecker {
  // Check if the type is primitive or String
  public boolean isPrimitiveOrString(String fieldType) {
    return fieldType.equals("int") || fieldType.equals("short") || fieldType.equals("long") ||
      fieldType.equals("byte") || fieldType.equals("char") || fieldType.equals("float") ||
      fieldType.equals("double") || fieldType.equals("boolean") || fieldType.equals("String");
  }

  // Check if the type is primitive wrapper or String
  public boolean isWrapperOrString(String fieldType) {
    return fieldType.equals("Integer") || fieldType.equals("Short") || fieldType.equals("Long") ||
      fieldType.equals("Byte") || fieldType.equals("Character") || fieldType.equals("Float") ||
      fieldType.equals("Double") || fieldType.equals("Boolean") || fieldType.equals("String");
  }

  // Convert a wrapper to its corresponding primitive type
  public String toPrimitive(String fieldType) {
    String res = "";
    if (fieldType.equals("Integer")) { res = "int"; }
    else if (fieldType.equals("Short")) { res = "short"; }
    else if (fieldType.equals("Long")) { res = "long"; }
    else if (fieldType.equals("Byte")) { res = "byte"; }
    else if (fieldType.equals("Character")) { res = "char"; }
    else if (fieldType.equals("Float")) { res = "float"; }
    else if (fieldType.equals("Double")) { res = "double"; }
    else if (fieldType.equals("Boolean")) { res = "boolean"; }
    else { return fieldType; }
    return res;
  }
}
