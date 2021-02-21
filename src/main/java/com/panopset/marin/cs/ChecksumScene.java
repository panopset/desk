package com.panopset.marin.cs;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.panopset.compat.Logop;
import com.panopset.fxapp.PanFileOrDirSelectorFX;
import com.panopset.fxapp.PanMenuFX;
import com.panopset.marin.secure.checksums.ChecksumReport;
import com.panopset.marin.secure.checksums.ChecksumType;
import com.panopset.util.rpg.ProcessListener;
import com.panopset.util.rpg.TextProcessor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class ChecksumScene implements Initializable {

  private boolean isAllOn = true;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    for (ChecksumType cst : ChecksumType.values()) {
      CheckBox cb = new CheckBox(cst.name());
      cb.setId("cs_algbtn_" + cst.name());
      if ("BYTES".equals(cst.name())) {
        cb.setTooltip(new Tooltip(

            "This is just the byte count for the file, not any kind of checksum."));
      }
      cs_checkboxes.getChildren().add(cb);
      CHECKBOXES.add(cb);
    }
    cs_all.setOnAction((event) -> {
      for (CheckBox cb : CHECKBOXES) {
        cb.setSelected(isAllOn);
      }
      isAllOn = !isAllOn;
    });
    cs_checksum.setOnAction((event -> {
      Platform.runLater(() -> {
        doProcess();
      });
    }));
  }

  private void doProcess() {
    List<ChecksumType> types = getSelectedTypes();
    if (types.isEmpty()) {
      Logop.warn("Nothing selected.");
      cs_out.setText("Nothing selected.");
      return;
    }
    Logop.clear();
    cs_out.setText("");
    createReport(types);
  }

  private void createReport(List<ChecksumType> types) {
    TextProcessor tp = new TextProcessor();
    tp.addProcessListener(new ProcessListener() {
      @Override
      public void setText(String s) {
        cs_out.setText(s);
      }

      @Override
      public void append(String s) {
        cs_out.appendText(s);
      }

      @Override
      public void reset() {
        cs_out.setText("");
      }
    });
    new ChecksumReport().generateReport(getFile(), types, tp);
  }

  private File getFile() {
    File rtn = cs_fileselectController.getFile();
    return rtn;
  }

  private List<ChecksumType> getSelectedTypes() {
    List<ChecksumType> rtn = new ArrayList<>();
    for (CheckBox cb : CHECKBOXES) {
      if (cb.isSelected()) {
        ChecksumType cst = ChecksumType.find(cb.getText());
        if (cst != null) {
          rtn.add(cst);
        }
      }
    }
    return rtn;
  }

  @FXML
  PanMenuFX panMenuBarController;

  @FXML
  Button cs_checksum;

  @FXML
  HBox cs_checkboxes;

  @FXML
  Button cs_all;

  @FXML
  HBox cs_fileselect;

  @FXML
  TextArea cs_out;

  @FXML
  PanFileOrDirSelectorFX cs_fileselectController;

  private static final List<CheckBox> CHECKBOXES = new ArrayList<>();

}
