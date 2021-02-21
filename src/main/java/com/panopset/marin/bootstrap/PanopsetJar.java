package com.panopset.marin.bootstrap;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import com.panopset.compat.Logop;
import com.panopset.marin.UrlHelper;
import com.panopset.marin.broken.structure.AppInfoSupplierSomething;

public class PanopsetJar {
  static AppInfoSupplierSomething aiss = new AppInfoSupplierSomething();

  public static String generateUtilitiesPage() throws IOException {
    return generateLynx(aiss.getAiUtilities());
  }

  public static String generateSecurityPage() throws IOException {
    return generateLynx(aiss.getAiSecurity());
  }

  public static String generateGamesPage() throws IOException {
    return generateLynx(aiss.getAiGames());
  }

  public static void includeTitle(StringWriter sw, String title) {
    sw.append("title=\"");
    sw.append(title);
    sw.append("\" ");
  }

  public static String generateLynx(List<ApplicationInformation> value) {
    StringWriter sw = new StringWriter();
    int i = 0;
    for (ApplicationInformation ai : value) {
      String lcname = ai.getLcName();
      sw.append("<tr>\n");
      sw.append("<th ");
      // includeTitle(sw, ai.getDescription());
      sw.append("class=\"");

      if (i == 0) {
        sw.append("greenbar0");
      } else {
        sw.append("greenbar1");
      }

      sw.append("\"");

      sw.append(">\n");

      sw.append("<img src=\"images/");
      sw.append("logo16_");
      sw.append(lcname.toLowerCase());
      sw.append(".png\" alt=\"icon\"/>&nbsp;&nbsp;");

      sw.append(ai.getRawName());
      sw.append("</th>\n");



      sw.append("<td class=\"output2\">");



      sw.append("<input class=\"greenbar\" type=\"text\" value=\"java -cp panopset.jar ");
      Class<?> clazz = null;
      try {
        clazz = Class.forName(lcname);
      } catch (ClassNotFoundException e) {
        // really, nothing to do.
      }
      if (clazz == null) {
        sw.append(ai.getPkg());
        sw.append(".");
        sw.append(ai.getRawName());
      } else {
        sw.append(lcname);
      }
      sw.append("\"/>");
      sw.append("</td></tr>\n");
      sw.append("<tr><td colspan=\"2\">");
      sw.append(ai.getDescription());
      sw.append("</td></tr><tr><td colspan=\"2\">&nbsp;</td></tr>\n");
      i = 1 - i;
    }
    return sw.toString();
  }

  public static String generateChecksums(String dtrp) {
    try {
      return UrlHelper.getTextFromURL(new URL(String.format("%s%s/%s",
          "https://panopset.com/downloads/", dtrp, CreatePlatformChecksumFile.PCSHS_FILE)));
    } catch (IOException e) {
      Logop.handle(e);
    }
    return "";
  }
}
