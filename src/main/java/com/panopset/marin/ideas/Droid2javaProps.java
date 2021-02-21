package com.panopset.marin.ideas;

import java.io.File;
import java.io.IOException;
import com.panopset.compat.DirectoryProcessor;
import com.panopset.compat.Logop;
import com.panopset.util.rpg.FileTransformer;

public class Droid2javaProps {

  public static void main(String... args) throws IOException {
    new Droid2javaProps().go("C:/a/dune/droid/blkjcklib/src/main/res");
  }

  private void go(String dir) throws IOException {
    new DirectoryProcessor(new File(dir), (File file) -> {


      String parentDirName = file.getParentFile().getName();

      String tgdFileName = parentDirName.replace("values", "Blackjack") + ".properties";
      tgdFileName = tgdFileName.replaceAll("-", "_");

      Logop.dspmsg(tgdFileName + " ~ " +
        file.getName());


      File tgtFile = new File("c:/b/" + tgdFileName);

      if (parentDirName.indexOf("values") > -1) {
        go00(file, tgtFile);
      }

      return true;
    }
    ).exec();
  }

  private void go00(File file, File tgtFile) {
    if (!"strings.xml".equals(file.getName())) {
      return;
    }
    new FileTransformer(file, tgtFile) {

      @Override
      protected String filter(String s) {
        int keyBegin = s.indexOf("string name=");
        if (keyBegin == -1) {
          return null;
        }
        keyBegin = keyBegin + 13;
        String remain = s.substring(keyBegin);
        return remain.replace("\">", "=").replace("</string>", "");
      }
    }.exec();
  }
}
