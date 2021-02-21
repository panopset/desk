package com.panopset.marin.broken.structure;

import java.io.IOException;
import com.panopset.compat.Fileop;

public class PanopsetJsonReader {

  private PanopsetJsonReader() {}

  public static String readJson(String path) throws IOException {
    String staticBase = System.getProperty("panopset.static");
    if (staticBase == null) {
      throw new IOException("panopset.static not set.");
    }
    String filePath = String.join(Fileop.FILE_SEP, staticBase, path);
    return Fileop.readTextFile(filePath);
  }
}
