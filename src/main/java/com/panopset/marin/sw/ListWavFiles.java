package com.panopset.marin.sw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.panopset.compat.DirectoryProcessor;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;

public class ListWavFiles {

  private static final File DIR2PARSE = new File(String.join(Stringop.FILE_SEP,
      Stringop.USER_HOME, "apps", "docs", "notes", "ivrteam", "audio_files", "audio"));

  public static void main(String... args) {
    new ListWavFiles().outputTo(Reports.REPORT_ALL_WAVS);
  }

  private void outputTo(File outputFile) {
    try (FileWriter fw = new FileWriter(outputFile); BufferedWriter bw = new BufferedWriter(fw)) {
      new DirectoryProcessor(DIR2PARSE, new DirectoryProcessor.Listener() {

        @Override
        public boolean processFile(File file) {
          if (Fileop.getExtension(file.getName()).equals("wav")) {
            try {
              bw.write(file.getName());
              bw.write("\n");
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
          }
          return false;
        }
      }).exec();
      bw.flush();
    } catch (IOException ex) {
      Logop.error(ex);
    }
  }
}
