package com.panopset.marin.bootstrap;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.flywheel.FlywheelBuilder;
import com.panopset.flywheel.ReflectionInvoker;
import com.panopset.util.WebLocal;

public class CreatePanopsetSite {

  public static void main(String... args) throws IOException {
    if (args == null || Stringop.isBlank(args[0])) {
      throw new NullPointerException();
    }
    ReflectionInvoker.defineTemplateAllowedReflection("generateChecksums", "com.panopset.marin.bootstrap.PanopsetJar.generateChecksums");
    ReflectionInvoker.defineTemplateAllowedReflection("generateUtilitiesPage", "com.panopset.marin.bootstrap.PanopsetJar.generateUtilitiesPage");
    ReflectionInvoker.defineTemplateAllowedReflection("generateSecurityPage", "com.panopset.marin.bootstrap.PanopsetJar.generateSecurityPage");
    ReflectionInvoker.defineTemplateAllowedReflection("generateGamesPage", "com.panopset.marin.bootstrap.PanopsetJar.generateGamesPage");
    ReflectionInvoker.defineTemplateAllowedReflection("getChart", "com.panopset.lowerclass.JavaVersionChart.getChart");
    ReflectionInvoker.defineTemplateAllowedReflection("getVersion", "com.panopset.util.AppVersion.getVersion()");

    
    
    //com.panopset.marin.bootstrap.PanopsetJar.generateUtilitiesPage
    File tmplt = new File("set/templates/home.txt");
    File toDir = new File(WebLocal.getWebLocalDirectory());
    Logop.dspmsg("******* args[0], tmplt:");
    Logop.dspmsg(Fileop.getCanonicalPath(tmplt));
    Logop.dspmsg("******* args[1], toDir:");
    Logop.dspmsg(Fileop.getCanonicalPath(toDir));
    System.setProperty("panopset.static", WebLocal.getWebLocalDirectory());
//    SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    new FlywheelBuilder().file(tmplt).targetDirectory(toDir).map("dtrp", args[0])
        .map("lastmodified", format.format(new Date())).construct().exec();
  }

}
