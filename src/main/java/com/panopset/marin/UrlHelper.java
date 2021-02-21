package com.panopset.marin;

import java.io.IOException;
import java.net.URL;

public class UrlHelper {

  public static String getTextFromURL(final URL url) throws IOException {
    HttpGETclient client = new HttpGETclient(url);
    return client.getResponse();
  }
}
