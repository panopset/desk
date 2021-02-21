package com.panopset.marin.bootstrap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import com.panopset.compat.DirectoryProcessor;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.util.Jxon;
import com.panopset.util.WebLocal;

public class CreateJnlpList {

  public static void main(String... args) throws IOException {
    if (args == null) {
      printUsage();
      throw new NullPointerException();
    }
    if (args.length != 2) {
      printUsage();
      return;
    }
    new CreateJnlpList().go(args[0], args[1], "https://panopset.com",
        WebLocal.getWebLocalDirectory());
  }

  private void go(String src, String trg, String cbu, String targDir) throws IOException {
    List<ApplicationInformation> list = new ArrayList<>();
    File targetFile = new File(trg);
    new DirectoryProcessor(new File(src), file -> {
      ApplicationInformation ai = new ApplicationInformation();
      ApplicationInformationFactory aif = new ApplicationInformationFactory(file);
      ai.setDescription(aif.getDescription());
      ai.setLcName(aif.getLcName());
      ai.setPkg(aif.getPkg());
      ai.setRawName(aif.getRawName());
      list.add(ai);
      return true;
    }, false).exec();
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
      new Jxon<>(ApplicationInformation.class).list2json(list, bw);
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
    sw.append("CreateJnlpList <path-to-src-package> <target-jnlp> <codebase-url>");
    return sw.toString();
  }
}
