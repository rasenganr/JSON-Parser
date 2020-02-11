package edu.duke.ece651.classbuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class JSONWriter {
  // Write a string to a file
  public void writeJSON(String path, String className, String toWrite) {
    try {
      File newFile = new File(path, className + ".java");
      // This path does not exist, create necessary directories
      if (!newFile.exists()) {
        newFile.getParentFile().mkdirs();
      }
      // Create a new file
      newFile.createNewFile();
      // Write the content to the new file
      BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
      bw.write(toWrite);
      // Store the written content from buffer to memory
      bw.flush();
      bw.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
