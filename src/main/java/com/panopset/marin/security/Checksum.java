package com.panopset.marin.security;

import com.panopset.marin.fx.PanopsetBrandedApp;

public class Checksum extends PanopsetBrandedApp {

  public static void main(String... args) {
    new Checksum().go();
  }

  @Override
  protected String getPaneFXMLName() {
    return "AppChecksumScene.fxml";
  }

  @Override
  public String getApplicationDisplayName() {
    return "Checksum";
  }

  @Override
  public String getDescription() {
    return "Validate files with various checksums you might typically encounter.";
  }
}
