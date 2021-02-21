package com.panopset.marin.fx;

import com.panopset.compat.Logop;
import com.panopset.fxapp.JavaFXappLauncher;
import com.panopset.fxapp.PanApplication;
import com.panopset.marin.bootstrap.VersionHelper;
import com.panopset.util.HiddenFolder;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;

public abstract class PanopsetBrandedApp extends JavaFXappLauncher implements PanApplication {

  public PanopsetBrandedApp() {
    HiddenFolder.setHiddenFolderName("panopset");
  }
  
  @Override
  public String getCompanyName() {
    return "Panopset";
  }

  @Override
  public void initializeFxDoc(Task<Void> task, FXMLLoader loader) {
    if (VersionHelper.isReadyToUpdate()) {
      Logop.green(String.format("  A newer version, %s is now available on panopset.com.", VersionHelper.getAvailableVersion()));
    }
  }
}
