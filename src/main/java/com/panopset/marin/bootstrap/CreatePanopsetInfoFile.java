package com.panopset.marin.bootstrap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.util.Jxon;

public class CreatePanopsetInfoFile {

  public static void main(String... args) {
    if (args == null) {
      printUsage();
      throw new NullPointerException();
    }
    if (args.length != 2) {
      printUsage();
      return;
    }
    new CreatePanopsetInfoFile().go(args[0], args[1]);
  }

  private void go(String src, String trg) {
    File srcFile = new File(src);
    File targetFile = new File(trg);
    Fileop.checkParent(targetFile);
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
      new Jxon<ChecksumInformation>(ChecksumInformation.class)
          .list2json(ChecksumInformationFactory.createList(srcFile), bw);
    } catch (IOException ex) {
      throw new RuntimeException(Fileop.getCanonicalPath(targetFile), ex);
    }
  }

  private static void printUsage() {
    Logop.dspmsg(generateUseText());
  }

  private static String generateUseText() {
    StringWriter sw = new StringWriter();
    sw.append("Usage:\n");
    sw.append("CreatePanopsetInfoFile <path-to-file> <target-json>");
    return sw.toString();
  }
}
