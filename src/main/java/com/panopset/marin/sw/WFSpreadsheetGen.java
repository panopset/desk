package com.panopset.marin.sw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.gp.TextFileProcessor;

public class WFSpreadsheetGen {

  public static void main(String... args) {
    new WFSpreadsheetGen().go();
  }

  private void go() {
    absorbAllWavs();
    absorbUsage();
    absorbJava();
    outputTo(Reports.REPORT_USAGE);
  }

  private void absorbJava() {
    for (String s : TextFileProcessor.loadSmallTextFileToList(Reports.REPORT_JAVA_WAVS)) {
      if (Stringop.isPopulated(s)) {
        String[] flds = s.split("\\|");
        if (flds.length > 1) {
          WaveFile wf = getWavs().get(flds[1]);
          if (wf == null) {
            wf = new WaveFile(s);
            getWavs().put(flds[1], wf);
          }
          wf.setUsed(flds[0]);
          if (flds.length == 3) {
            wf.setText(flds[2]);
          }
        } else {
          throw new RuntimeException(flds.length + " - " + s);
        }
      }      
    }
  }

  private void outputTo(File outputFile) {
    try (FileWriter fw = new FileWriter(outputFile); BufferedWriter bw = new BufferedWriter(fw)) {
      for (Entry<String, WaveFile> e : getWavs().entrySet()) {
        bw.write(e.getValue().toString());
        bw.write(Stringop.getEol());
      }
      bw.flush();
    } catch (IOException ex) {
      Logop.error(ex);
    }
  }

  private void absorbUsage() {
    for (String s : TextFileProcessor.loadSmallTextFileToList(Reports.REPORT_WAV_LIST)) {
      if (Stringop.isPopulated(s)) {
        String[] flds = s.split("\\|");
        if (flds.length > 1) {
          WaveFile wf = getWavs().get(flds[1]);
          if (wf == null) {
            wf = new WaveFile(s);
            getWavs().put(flds[1], wf);
          }
          wf.setUsed(flds[0]);
          if (flds.length == 3) {
            wf.setText(flds[2]);
          }
        } else {
          throw new RuntimeException(flds.length + " - " + s);
        }
      }
    }
  }

  private void absorbAllWavs() {
    for (String s : TextFileProcessor.loadSmallTextFileToList(Reports.REPORT_ALL_WAVS)) {
      if (Stringop.isPopulated(s)) {
        WaveFile wf = getWavs().get(s);
        if (wf == null) {
          wf = new WaveFile(s);
          getWavs().put(s, wf);
        }
      }
    }
  }

  private Map<String, WaveFile> wavs;

  private Map<String, WaveFile> getWavs() {
    if (wavs == null) {
      wavs = new HashMap<>();
    }
    return wavs;
  }
}
