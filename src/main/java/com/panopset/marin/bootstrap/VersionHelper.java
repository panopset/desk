package com.panopset.marin.bootstrap;

import java.io.IOException;
import java.net.URL;
import com.google.gson.Gson;
import com.panopset.compat.AppVersion;
import com.panopset.compat.Logop;
import com.panopset.marin.UrlHelper;

public enum VersionHelper {

  INSTANCE;

  public static String getAvailableVersion() {
    return INSTANCE.getAvailable();
  }

  public static boolean isReadyToUpdate() {
    return INSTANCE.isReadyToUpdateI();
  }

  private String availVers;

  private String getAvailable() {
    if (availVers == null) {
      availVers = "";
        String versJson;
        try {
          versJson = UrlHelper.getTextFromURL(new URL("https://panopset.com/version.json"));
        } catch (IOException e) {
          Logop.error(e);
          availVers = "offline";
          return availVers;
        }
        availVers = new Gson().fromJson(versJson, String.class);
    }
    return availVers;
  }

  private boolean isReadyToUpdateI() {
    String thisVersion = AppVersion.getVersion();
    String availVersion = getAvailable();
    return !availVersion.equals(thisVersion);
  }
}
