package com.panopset.marin.bootstrap;

import java.util.List;
import com.panopset.util.Jxon;

public class JnlpReader {

  private final String json;

  public JnlpReader(String jsonStr) {
    json = jsonStr;
  }

  private List<ChecksumInformation> ci;

  public List<ChecksumInformation> getChecksumInformation() {
    if (ci == null) {
      ci = new Jxon<ChecksumInformation>(ChecksumInformation.class).json2list(json);
    }
    return ci;
  }

}
