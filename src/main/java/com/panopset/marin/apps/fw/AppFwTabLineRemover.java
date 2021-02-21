package com.panopset.marin.apps.fw;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.fxapp.PanFileOrDirSelectorFX;
import com.panopset.fxapp.SceneUpdater;
import com.panopset.fxapp.TextFormatters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class AppFwTabLineRemover extends SceneUpdater implements Initializable, TextFormatters {


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    lr_removelines.setOnAction((event) -> {
      triggerAnUpdate();
    });
  }

  @Override
  protected void doUpdate() {
    if (lr_windows.isSelected()) {
      Stringop.setEol(Stringop.EOL);
    } else {
      Stringop.setEol("\n");
    }
    Fileop.deleteLines(lr_file_or_dir_selectController.getFile(), lr_search_criteria.getText());
  }

  @FXML
  TextArea lr_search_criteria;

  @FXML
  PanFileOrDirSelectorFX lr_file_or_dir_selectController;

  @FXML
  Button lr_removelines;

  @FXML
  CheckBox lr_windows;
}
