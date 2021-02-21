package com.panopset.marin.security;

import com.panopset.marin.fx.PanopsetBrandedApp;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;

public class Scrambler extends PanopsetBrandedApp {

  public static void main(String... args) {
    new Scrambler().go();
  }
  
  @Override
  public void initializeFxDoc(Task<Void> task, FXMLLoader loader) {
    super.initializeFxDoc(task, loader);
    setDefaultValue("fxid_koi", "10000");
  }
  
  @Override
  public String getApplicationDisplayName() {
    return "Scrambler";
  }

  @Override
  public String getDescription() {
    return "Text scrambler.";
  }

  @Override
  protected String getPaneFXMLName() {
    return "AppScramblerScene.fxml";
  }

}
