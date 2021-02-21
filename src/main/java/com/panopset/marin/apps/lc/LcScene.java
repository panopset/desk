package com.panopset.marin.apps.lc;

import java.io.File;
import java.io.IOException;
import com.panopset.compat.Logop;
import com.panopset.fxapp.PanFileOrDirSelectorFX;
import com.panopset.lowerclass.VersionParser;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class LcScene {

  File currentFile;

  @FXML
  private void handleDetailsCBAction(Event event) {
    if (lc_details.isSelected() && lc_file_or_dir_selectController.getFile().isDirectory()) {
      Logop.warn("Details not available on a directory report.");
      return;
    }
    refresh();
  }

  @FXML
  private synchronized void handleReportAction(Event event) {
    refresh();
  }

  private void refresh() {
    lc_out.setText("");
    if (currentFile != null) {
      if (!lc_file_or_dir_selectController.getFile().equals(currentFile)) {
        vp = null;
      }
    }
    currentFile = lc_file_or_dir_selectController.getFile();
    if (lc_report.isDisabled()) {
      Logop.warn("Please wait for running process to complete.");
      return;
    }
    lc_report.setDisable(true);
    Platform.runLater(() -> {
      VersionParser vp0 = getVersionParser();
      try {
        if (lc_details.isSelected()) {
          if (currentFile.isDirectory()) {
            Logop.warn("Details not available on a directory report.");
            lc_out.appendText(vp0.getSummaryReport());
          } else {
            lc_out.appendText(vp0.getDetailedReport());
          }
        } else {
          lc_out.appendText(vp0.getSummaryReport());
        }
      } catch (IOException ex) {
        Logop.handle(ex);
      }
      lc_report.setDisable(false);
    });
  }

  private VersionParser vp;

  private VersionParser getVersionParser() {
    if (vp == null) {
      if (lc_file_or_dir_selectController.getFile().exists()
          && lc_file_or_dir_selectController.canRead()) {
        vp = new VersionParser(lc_file_or_dir_selectController.getFile());
      } else {
        vp = new VersionParser();
      }
    }
    return vp;
  }

  @FXML
  TextArea lc_out;

  @FXML
  Button lc_report;

  @FXML
  CheckBox lc_details;

  @FXML
  HBox lc_file_or_dir_select;

  @FXML
  PanFileOrDirSelectorFX lc_file_or_dir_selectController;
}
