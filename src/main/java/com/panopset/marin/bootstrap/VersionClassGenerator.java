package com.panopset.marin.bootstrap;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.flywheel.FlywheelBuilder;

public class VersionClassGenerator {

  public static void main(String... args) throws IOException {
    File vf = new File("src/main/java/com/panopset/compat/AppVersion.java");
    if (!vf.getParentFile().exists()) {
      Logop.error("Parent directory not found", vf);
      return;
    }
    String result = new FlywheelBuilder()
        .inputResourcePath(VersionClassGenerator.class,
            "/com/panopset/marin/bootstrap/version.tmplt")
        .map("version_timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()))
        .construct().exec();
    if (Stringop.isPopulated(result)) {
      Fileop.write(result, vf);
    }
  }
}
