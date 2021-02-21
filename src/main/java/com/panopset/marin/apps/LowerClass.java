package com.panopset.marin.apps;

import com.panopset.marin.fx.PanopsetBrandedApp;

public class LowerClass extends PanopsetBrandedApp {

  public static void main(String... args) {
    new LowerClass().go();
  }
  
  @Override
  public String getApplicationDisplayName() {
    return "Lower Class";
  }

  @Override
  public String getDescription() {
    return "Generate a report on minimum JDKs for the class files found in a jar, or a repository directory.";
  }

  @Override
  protected String getPaneFXMLName() {
    return "AppLcScene.fxml";
  }

}
