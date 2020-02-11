package edu.duke.ece651.classbuilder;

import org.json.JSONObject;

public class PathBuilder {
  // Build the path according to "package"
  public String buildPath(String basePath, JSONObject json) {
    String res = basePath;
    // Check the format of basePath
    if (basePath.charAt(basePath.length() - 1) != '/') {
      res += "/";
    }
    // No "package" specified
    if (json.optString("package") == "") {
      return basePath;
    }
    // Interpret "package"
    else {
      String [] path = json.getString("package").split("\\.");
      for (int i = 0; i < path.length; ++i) {
        res += path[i] + "/";
      }
      return res;
    }
  }
}
