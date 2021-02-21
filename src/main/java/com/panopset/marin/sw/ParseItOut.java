package com.panopset.marin.sw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.panopset.compat.DirectoryProcessor;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.gp.TextFileProcessor;

public class ParseItOut {

  public static void main(String[] args) {
    new ParseItOut().outputTo(Reports.REPORT_WAV_LIST);
  }

  private void outputTo(File outputFile) {
    try (FileWriter fw = new FileWriter(outputFile); BufferedWriter bw = new BufferedWriter(fw)) {
      new DirectoryProcessor(Reports.CC_IVR_PROJECT, new DirectoryProcessor.Listener() {

        @Override
        public boolean processFile(File file) {
          String cp = Fileop.getCanonicalPath(file);
          if (cp.indexOf(".git") > -1) {
            return false;
          }
          String pfx = Stringop.USER_HOME + Stringop.FILE_SEP + "apps\\workspaces\\ivrws\\cc-ivr\\";
          String relpath = cp.substring(pfx.length());
          if (Fileop.getExtension(file.getName()).equals("")) {
            writeExtractedData(bw, file, relpath);
            return true;
          }
          return false;
        }
      }).exec();
      bw.flush();
    } catch (IOException ex) {
      Logop.error(ex);
    }
  }

  private void writeExtractedData(BufferedWriter bw, File file, String relPath) {
    for (String s : TextFileProcessor.loadSmallTextFileToList(file)) {
      getParser().setInput(s);
      String wavFileName = getParser().getField(WAV_FILE_KEY);
      String text = getParser().getField(TEXT_KEY);
      if (Stringop.isPopulated(wavFileName)) {
        try {
          bw.write(String.join("|", relPath, String.join(".", wavFileName, "wav"), text));
          bw.write("\n");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  private final String WAV_FILE_KEY = "wavFileKey";
  private final String TEXT_KEY = "textKey";

  private RegexParser rp;

  private RegexParser getParser() {
    if (rp == null) {
      rp = new RegexParser();
      rp.addField(WAV_FILE_KEY, "uri=\\\"", ".wav\"");
      rp.addField(TEXT_KEY, "CDATA\\[", "\\]\\]");
    }
    return rp;
  }
}
