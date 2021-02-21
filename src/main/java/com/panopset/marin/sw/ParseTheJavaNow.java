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

public class ParseTheJavaNow {
  public static void main(String[] args) {
    new ParseTheJavaNow().outputTo(Reports.REPORT_JAVA_WAVS);
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
          String relpath = cp.substring("C:\\Users\\karldi\\apps\\workspaces\\ivrws\\cc-ivr\\".length());
          if (Fileop.getExtension(file.getName()).equals("java")) {
            writeExtractedData(bw, file, relpath);
            flush(bw);
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

  private AudioInfo ai;
  
  private void writeExtractedData(BufferedWriter bw, File file, String relPath) {
    for (String s : TextFileProcessor.loadSmallTextFileToList(file)) {
      if (s.indexOf("new Audio()") > -1) {
        flush(bw);
        ai = new AudioInfo(relPath);
      } else if (s.indexOf("setWavURI") > -1) {
        if (ai == null) {
          ai = new AudioInfo(relPath);
        }
        ai.wavFile = parseWavURI(s);
      } else if (s.indexOf("setTextToSpeech") > -1) {
        if (ai == null) {
          ai = new AudioInfo(relPath);
        }
        ai.text = parseTextToSpeech(s);
      }
    }
  }

  private void flush(BufferedWriter bw) {
    if (ai == null) {
      return;
    }
    if (!Stringop.isPopulated(ai.wavFile)) {
      return;
    }
    try {
      bw.write(ai.toString());
      bw.write(Stringop.getEol());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ai = null;
  }

  private String parseWavURI(String s) {
    return getWavURIparser().setInput(s).getField(WAV_FILE_KEY);
  }
  
  private final String WAV_FILE_KEY = "wavFileKey";
  private final String TEXT_KEY = "textKey";
  
  private RegexParser wavURIparser;
  
  private RegexParser getWavURIparser() {
    if (wavURIparser == null) {
      wavURIparser = new RegexParser();
      wavURIparser.addField(WAV_FILE_KEY, "setWavURI\\(\\\"", "\\\"");
    }
    return wavURIparser;
  }

  private String parseTextToSpeech(String s) {
    return getTtsParser().setInput(s).getField(TEXT_KEY);
  }
  
  private RegexParser ttsParser;
  
  private RegexParser getTtsParser() {
    if (ttsParser == null) {
      ttsParser = new RegexParser();
      ttsParser.addField(TEXT_KEY, "setTextToSpeech\\(\\\"", "\\\"");
    }
    return ttsParser;
  }

  static class AudioInfo {
    public AudioInfo(String relPath) {
      this.relPath = relPath;
    }
    @Override
    public String toString() {
      return String.join("|", relPath, wavFile, text);
    }
    final String relPath;
    String text = "";
    String wavFile = "";
  }
}
