package com.panopset.marin.apps;

import com.panopset.marin.fx.PanopsetBrandedApp;

public class Gr extends PanopsetBrandedApp {

  public static void main(String... args) {
    new Gr().go();
  }

  @Override
  public String getDescription() {
    return "Recursive text replacement.";
  }

  @Override
  public String getApplicationDisplayName() {
    return "Global Replacer (gr)";
  }

  @Override
  protected String getPaneFXMLName() {
    return "AppGrScene.fxml";
  }
}
