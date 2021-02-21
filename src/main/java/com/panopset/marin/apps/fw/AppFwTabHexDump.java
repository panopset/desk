package com.panopset.marin.apps.fw;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.panopset.compat.Logop;
import com.panopset.compat.Nls;
import com.panopset.compat.Stringop;
import com.panopset.fxapp.PanFileSelectorFX;
import com.panopset.fxapp.SceneUpdater;
import com.panopset.fxapp.TextFormatters;
import com.panopset.util.FileHexDumper;
import com.panopset.util.Hexop;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppFwTabHexDump extends SceneUpdater implements Initializable, TextFormatters {

  private void loadBytes(String filePath, int start, int max, int width, boolean isSpaces,
      boolean isCharsetIncluded) throws IOException {
    File file = new File(filePath);
    if (!file.exists()) {
      Logop.warn(Nls.xlate("File does not exist."));
      return;
    }
    FileHexDumper fhd = new FileHexDumper(file);
    String[] result = fhd.dump(start, max, width, isSpaces, isCharsetIncluded);

    if (Stringop.isPopulated(hd_text.getText())) {
      Platform.runLater(() -> {
        hd_text.setText("");
      });
      Platform.runLater(() -> {
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            hd_dump.setText(result[1]);
          }
        }).start();
      });
    } else {
      Platform.runLater(() -> {
        hd_dump.setText(result[1]);
      });
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    hd_text.textProperty().addListener((observable, oldValue, newValue) -> {
      triggerAnUpdate();
    });
    hd_space.setOnAction((event) -> {
      triggerAnUpdate();
    });
    hd_chars.setOnAction((event) -> {
      triggerAnUpdate();
    });
    hd_load.setOnAction((event) -> {
      try {
        loadBytes(hd_fileselectController.inpfile.getText(), getStart(), getMax(), getWidth(),
            hd_space.isSelected(), hd_chars.isSelected());
      } catch (IOException e) {
        Logop.handle(e);
      }
    });
    hd_max.setTextFormatter(createIntegerFilter());
    hd_max.textProperty().addListener((observable, oldValue, newValue) -> {
      triggerAnUpdate();
    });
    hd_width.setTextFormatter(createIntegerFilter());
    hd_width.textProperty().addListener((observable, oldValue, newValue) -> {
      triggerAnUpdate();
    });
    hd_start.setTextFormatter(createIntegerFilter());
    hd_start.textProperty().addListener((observable, oldValue, newValue) -> {
      triggerAnUpdate();
    });
  }

  @FXML
  public PanFileSelectorFX hd_fileselectController;

  @FXML
  public CheckBox hd_space;

  @FXML
  public CheckBox hd_chars;

  @FXML
  public TextField hd_start;

  @FXML
  public TextField hd_max;

  @FXML
  public TextField hd_width;

  @FXML
  public SplitPane hd_sp;

  @FXML
  public TextArea hd_text;

  @FXML
  public TextArea hd_dump;

  @FXML
  public Button hd_load;

  @Override
  protected void doUpdate() {
    hd_dump.setText(Hexop.dumpToString(hd_text.getText(), getStart(), getMax(),
        getWidth(), hd_space.isSelected(), hd_chars.isSelected()));
  }

  private int getStart() {
    return Stringop.parseInt(hd_start.getText());
  }

  private int getMax() {
    return Stringop.parseInt(hd_max.getText());
  }

  private int getWidth() {
    return Stringop.parseInt(hd_width.getText());
  }
}
