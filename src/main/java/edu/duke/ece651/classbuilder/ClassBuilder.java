package edu.duke.ece651.classbuilder;

import java.io.InputStream;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class ClassBuilder {
  private JSONObject json;
  // Hash Table: Class name (string) - Fields (JSONArray)
  private HashMap<String, JSONArray> nameFieldsMp;
  
  // Constructor with String
  public ClassBuilder(String str) {
    constructorHelper(str);
  }

  // Constructor with InputStream
  public ClassBuilder(InputStream is) {
    JSONReader jr = new JSONReader();
    String str = jr.readJSONAsInputStream(is);
    constructorHelper(str);
  }

  //Helper function for constructors
  private void constructorHelper(String str) {
    // Create a JSONObject
    json = new JSONObject(str);
    // Initialize the HashMap
    nameFieldsMp = new HashMap<String, JSONArray>();
    JSONArray classes = json.getJSONArray("classes");
    // Traverse each class saved in JSON file
    for (int i = 0; i < classes.length(); ++i) {
      JSONObject oneClass = classes.getJSONObject(i);
      String className = oneClass.getString("name");
      // A class without any field
      if (oneClass.optJSONArray("fields") == null) {
        nameFieldsMp.put(className, null);
      }
      // Store the fields of this class
      else {
        nameFieldsMp.put(className, oneClass.getJSONArray("fields"));
      }
    }
  }

  // Obtain all class names
  public Collection<String> getClassNames() {
    Collection<String> res = new ArrayList<String>();
    for (String key : nameFieldsMp.keySet()) {
      res.add(key);
    }
    return res;
  }

  // Generate source code for one class
  public String getSourceCode(String className) {
    String classCode = "";
    ImportBuilder ib = new ImportBuilder();
    FieldBuilder fb = new FieldBuilder();
    MethodBuilder mb = new MethodBuilder();
    ConstructorBuilder cb = new ConstructorBuilder();
    ToJSONBuilder jb = new ToJSONBuilder();
    // Generate package and import info
    classCode += ib.buildImport(json);
    // Generate class name
    classCode += "public class " + className + " {\n";
    // This class has fields
    if (nameFieldsMp.get(className) != null) {
      JSONArray fields = nameFieldsMp.get(className);
      // Generate fields
      classCode += fb.buildAllFields(fields) + "\n";
      // Generate constructor
      classCode += cb.buildConstructor(className, fields) + "\n";
      // Generate methods
      classCode += mb.buildMethods(fields) + "\n";
      // Generate toJSONHelper
      classCode += jb.buildToJSONHelper(nameFieldsMp.get(className));
      // Generate toJSON
      classCode += jb.buildToJSON();
    }
    // Closing brace
    classCode += "}\n";
    return classCode;
  }

  // Create one file per class for all classes
  public void createAllClasses(String basePath) {
    PathBuilder pb = new PathBuilder();
    ImportBuilder ib = new ImportBuilder();
    DeserializerBuilder db = new DeserializerBuilder();
    JSONWriter jw = new JSONWriter();
    String path = pb.buildPath(basePath, json);
    // Create one java file per class
    for (String key : nameFieldsMp.keySet()) {
      jw.writeJSON(path, key, getSourceCode(key));
    }
    // Create a deserializer class
    String deserializerCode = ib.buildImport(json) + db.buildDeserializer(json.getJSONArray("classes"));
    jw.writeJSON(path, "Deserializer", deserializerCode);
  }

  //Main function (only for generating source code into the tester)
  public static void main(String [] args) {
    JSONReader jr = new JSONReader();
    InputStream is = jr.readJSONToInputStream(args[0]);
    ClassBuilder clb = new ClassBuilder(is);
    clb.createAllClasses(args[1]);
  }
}
