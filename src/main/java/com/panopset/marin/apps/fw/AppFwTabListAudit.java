package com.panopset.marin.apps.fw;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.flywheel.ListAudit;
import com.panopset.fxapp.PanDirSelectorFX;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class AppFwTabListAudit implements Initializable {

  @FXML
  PanDirSelectorFX ladirselectController;

  @FXML
  Button run_btn;

  @FXML
  TextArea audit_ta;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    run_btn.setOnAction(event -> process());
  }

  private void process() {
    File dir = ladirselectController.getDirectory();
    ListAudit listAudit = new ListAudit();
    if (dir == null) {
      Logop.error("Dir is null");
      return;
    }
    if (!dir.exists()) {
      Logop.error("Does not exist.", dir);
      return;
    }
    if (!dir.isDirectory()) {
      Logop.error("Not a directory.", dir);
      return;
    }
    for (File f : dir.listFiles()) {
      if (f.isFile()) {
        listAudit.add(f.getName(), Fileop.readLines(f));
      }
    }
    audit_ta.setText(listAudit.getReportText());
  }
}
