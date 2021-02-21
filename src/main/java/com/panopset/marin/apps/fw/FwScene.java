package com.panopset.marin.apps.fw;

import java.io.IOException;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.flywheel.FlywheelListDriver;
import com.panopset.flywheel.LineFeedRules;
import com.panopset.fxapp.SceneUpdater;
import com.panopset.marin.app.swiftwheel.FlywheelPrompterFX;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FwScene extends SceneUpdater {

  FlywheelListDriver report;
  String input;
  String template;
  String splitz;
  boolean lineBreaks;
  boolean listBreaks;
  String tokens;

  @Override
  protected void doUpdate() {
    if (report != null &&

        fw_input.getText().equals(input) &&

        fw_template.getText().equals(template) &&

        fw_splitz.getText().equals(splitz) &&

        fw_lineBreaks.isSelected() == lineBreaks &&

        fw_listBreaks.isSelected() == listBreaks &&

        fw_tokens.getText().equals(tokens)

    ) {
      return;
    }
    Logop.clear();
    input = fw_input.getText();
    template = fw_template.getText();
    splitz = fw_splitz.getText();
    lineBreaks = fw_lineBreaks.isSelected();
    listBreaks = fw_listBreaks.isSelected();
    tokens = fw_tokens.getText();


    String wrktxt = fw_output.getText();
    fw_output.setText(wrktxt.replace("\n", ""));

    LineFeedRules lineFeedRules =
        new LineFeedRules(fw_lineBreaks.isSelected(), fw_listBreaks.isSelected());


    try {
      report = new FlywheelListDriver.Builder(

          Stringop.stringToList(fw_input.getText()), fw_template.getText())
              .withLineFeedRules(lineFeedRules)
              .withSplitz(fw_splitz.getText())
              .withTokens(fw_tokens.getText())

              .build();
      String result = report.getOutput();
      fw_output.setText(result);
    } catch (IOException e) {
      Logop.error(e.getMessage());
    }
    // getPrompterFX().setFlywheel(report.getFlywheel(), fw_prompters, fw_input, fw_template);
    if (!lineBreaks) {
      Logop.warn("Line breaks not checked, so output will be on one line.");
    }
  }
  
  private void doClear() {
    fw_input.setText("");
    fw_template.setText("");
  }

  FlywheelPrompterFX fwp;

  private FlywheelPrompterFX getPrompterFX() {
    if (fwp == null) {
      fwp = new FlywheelPrompterFX();
    }
    return fwp;
  }

  @FXML
  private void initialize() {
    fw_input.textProperty().addListener((observable, oldValue, newValue) -> triggerAnUpdate());
    fw_template.textProperty().addListener((observable, oldValue, newValue) -> triggerAnUpdate());
    fw_tokens.textProperty().addListener((observable, oldValue, newValue) -> triggerAnUpdate());
    fw_splitz.textProperty().addListener((observable, oldValue, newValue) -> triggerAnUpdate());
    fw_lineBreaks.setOnAction(event -> triggerAnUpdate());
    fw_listBreaks.setOnAction(event -> triggerAnUpdate());
    fw_clear.setOnAction(event -> doClear());
    new FxSampleLoader().loadUp(fw_samples.getChildren(), fw_input, fw_template, fw_lineBreaks, fw_listBreaks, fw_tokens, fw_splitz);
  }

  @FXML
  HBox fw_prompters;

  @FXML
  TextArea fw_input;

  @FXML
  TextArea fw_template;

  @FXML
  TextArea fw_output;

  @FXML
  TextField fw_tokens;

  @FXML
  TextField fw_splitz;

  @FXML
  CheckBox fw_lineBreaks;

  @FXML
  CheckBox fw_listBreaks;
  
  @FXML
  Button fw_clear;
  
  @FXML
  HBox fw_samples;

}
