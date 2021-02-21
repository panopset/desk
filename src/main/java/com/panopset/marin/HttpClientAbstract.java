package com.panopset.marin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.panopset.compat.Logop;

public abstract class HttpClientAbstract {

  private final URL url;

  public final URL getURL() {
    return url;
  }

  private int responseCode;

  public final int getResponseCode() {
    return responseCode;
  }

  protected abstract void

      setConnectionProperties(final HttpURLConnection connection);

  protected HttpClientAbstract(final URL newURL) {
    url = newURL;
  }

  protected HttpClientAbstract(final String urlString) {
    try {
      url = new URL(urlString);
    } catch (MalformedURLException e) {
      Logop.error(e);
      throw new RuntimeException(e);
    }
  }

  private HttpURLConnection con;

  public final HttpURLConnection getConnection() throws IOException {
    if (con == null) {
      con = (HttpURLConnection) url.openConnection();
      setConnectionProperties(con);
    }
    return con;
  }

  protected final void closeConnection() {
    if (con != null) {
      try {
        responseCode = con.getResponseCode();
      } catch (IOException e) {
        Logop.error(e);
      }
      con.disconnect();
    }
  }
}
