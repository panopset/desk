package com.panopset.marin.apps.fw;

import com.panopset.flywheel.samples.FlywheelSample;
import com.panopset.flywheel.samples.FlywheelSamples;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FxSampleLoader {

  public void loadUp(ObservableList<Node> children, TextArea fw_input, TextArea fw_template,
      CheckBox fw_lineBreaks, CheckBox fw_listBreaks, TextField fw_tokens, TextField fw_splitz) {
    for (FlywheelSample fs : FlywheelSamples.all()) {
      Button btn = new Button(fs.getDesc());
      btn.setOnAction(event -> {
        fw_input.setText(fs.getListText());
        fw_template.setText(fs.getTemplateText());
        fw_lineBreaks.setSelected(fs.getLineBreaks());
        fw_listBreaks.setSelected(fs.getListBreaks());
        fw_tokens.setText(fs.getTokens());
        fw_splitz.setText(fs.getSplitz());
      });
      children.add(btn);
    }
  }
}
