package com.panopset.marin.bootstrap;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.util.WebLocal;

public class CreatePlatformChecksumFile {

  public static void main(String... args) throws IOException {
    new CreatePlatformChecksumFile().publish(args[0]);
  }

  private void publish(String dtrp) throws IOException {
    StringWriter sw = new StringWriter();
    String downloadRelPath =
        String.join("/", WebLocal.getWebLocalDirectory(), "downloads", dtrp);
    File panopsetJar = new File(String.join("/", downloadRelPath, "panopset.jar"));
    List<ChecksumInformation> list = ChecksumInformationFactory.createList(panopsetJar);
    for (ChecksumInformation info : list) {
      sw.append("   <tr><th class=\"ts\">");
      sw.append(info.getAlg());
      sw.append("</th><td class=\"output2\">");
      sw.append(info.getVal());
      sw.append("</td></tr>\n");
    }
    File platformCheckSumHtmlSnippit = new File(String.join("/", downloadRelPath, PCSHS_FILE));
    Fileop.write(sw.toString(), platformCheckSumHtmlSnippit);
    
    File jar_info = new File(String.join("/", downloadRelPath, JAR_INFO_JSON));
    ObjectMapper mapper = new ObjectMapper();
    try {
      Fileop.write(mapper.writeValueAsString(list), jar_info);
    } catch (JsonProcessingException ex) {
      Logop.handle(ex);
    }
  }

  public static final String PCSHS_FILE = "pcshs.txt";
  public static final String JAR_INFO_JSON = "jar-info.json";
}
