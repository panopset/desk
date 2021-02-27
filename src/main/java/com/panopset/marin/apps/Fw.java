package com.panopset.marin.apps;

import com.panopset.marin.fx.PanopsetBrandedApp;

public class Fw extends PanopsetBrandedApp {

  public static void main(String... args) {
    new Fw().go();
  }

  @Override
  public String getApplicationDisplayName() {
    return "Flywheel text utilities.";
  }

  @Override
  public String getDescription() {
    return "Text processing utilities (flywheel, hexdump, global replace, list audit.)";
  }

  @Override
  protected String getPaneFXMLName() {
    return "AppFwScene.fxml";
  }

}
