package edu.duke.ece651.classbuilder;

import java.io.*;

public class JSONReader {
  // Read JSON file to a string
  public String readJSON(String filename) {
    // Read a JSON file into a string
    StringBuilder res = new StringBuilder();
    try{
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String curr = "";
      // Read each line and add it to res
      while ((curr = br.readLine()) != null) {
        res.append(curr);
      }
      // Close the file reader
      br.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return res.toString();
  }

  // Read a JSON file to an InputStream
  public InputStream readJSONToInputStream(String filename) {
    try {
      InputStream is = new FileInputStream(filename);
      return is;
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  // Convert an InputStream JSON to a string
  public String readJSONAsInputStream(InputStream is) {
    try {
      StringBuilder sb = new StringBuilder();
      String curr = "";
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      // Read each line and add it to res
      while ((curr = br.readLine()) != null) {
        sb.append(curr);
      }
      return sb.toString();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
