package com.panopset.marin.broken.structure;

import java.io.IOException;
import java.util.List;
import com.panopset.marin.bootstrap.ApplicationInformation;
import com.panopset.util.Jxon;

public class AppInfoSupplierSomething {
  private List<ApplicationInformation> ai_utilities;
  private List<ApplicationInformation> ai_security;
  private List<ApplicationInformation> ai_games;

  public List<ApplicationInformation> getAiSecurity() throws IOException {
    if (ai_security == null) {
      String jsonStr = PanopsetJsonReader.readJson("gen/json/pan_security.json");
      ai_security = new Jxon<ApplicationInformation>(ApplicationInformation.class).json2list(jsonStr);
    }
    return ai_security;
  }

  public List<ApplicationInformation> getAiGames() throws IOException {
    if (ai_games == null) {
      String jsonStr = PanopsetJsonReader.readJson("gen/json/pan_games.json");
      ai_games = new Jxon<ApplicationInformation>(ApplicationInformation.class).json2list(jsonStr);
    }
    return ai_games;
  }

  public List<ApplicationInformation> getAiUtilities() throws IOException {
    if (ai_utilities == null) {
      String jsonStr = PanopsetJsonReader.readJson("gen/json/pan_apps.json");
      ai_utilities = new Jxon<ApplicationInformation>(ApplicationInformation.class).json2list(jsonStr);
    }
    return ai_utilities;
  }
}
