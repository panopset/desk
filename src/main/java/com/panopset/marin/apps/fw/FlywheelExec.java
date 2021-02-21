package com.panopset.marin.apps.fw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;

public class FlywheelExec {

  private boolean skipBlanks;

  private boolean isSkipBlanks() {
    return skipBlanks;
  }

  public FlywheelExec setSkipBlanks(boolean value) {
    skipBlanks = value;
    return this;
  }

  public FlywheelExec(File inputFile) {
    inpF = inputFile;
  }

  public FlywheelExec(File inputFile, String tmpltData) {
    this(inputFile);
    tmpltText = tmpltData;
  }

  public FlywheelExec setOutputFile(File outputFile) {
    outF = outputFile;
    return this;
  }

  public void exec() {
    try (BufferedWriter bw = getWriter()) {
      execAll(bw);
      bw.flush();
    } catch (IOException e) {
      Logop.error(e);
    }
  }

  public void execAll(BufferedWriter bw) throws IOException {
    process(tmpltText);
    if (inpF.canRead()) {
      try (
          Stream<String> lines = Files.lines(Paths.get(inpF.getPath()), Charset.defaultCharset())) {
        lines.forEachOrdered(line -> {
          try {
            process(line);
          } catch (IOException e) {
            Logop.error(e);
          }
        });
      } catch (IOException e) {
        Logop.error(e);
      }
    }
  }

  private void process(String line) throws IOException {
    if (isEligible(line)) {
      getWriter().write(line);
      getWriter().write(Stringop.EOL);
    }
  }

  private BufferedWriter bw;

  private BufferedWriter getWriter() {
    if (bw == null) {
      if (outF == null) {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
      } else {
        File pf = outF.getParentFile();
        if (pf != null) {
          pf.mkdirs();
        }
        try {
          bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outF)));
        } catch (FileNotFoundException e) {
          Logop.error(e);
          Logop.warn(String.format("Can not write to %s, switching to stdout",
              Fileop.getCanonicalPath(outF)));
          bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }
      }
    }
    return bw;
  }

  private boolean isEligible(String s) {
    if (s == null) {
      return false;
    }
    if (isSkipBlanks() && Stringop.isEmpty(s.trim())) {
      return false;
    }
    return true;
  }

  private String tmpltText;

  private File inpF;

  private File outF;

  public FlywheelExec skipBlanks() {
    setSkipBlanks(true);
    return this;
  }
}
