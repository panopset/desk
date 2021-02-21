package com.panopset.marin.app.swiftwheel;

import java.util.Map.Entry;
import com.panopset.flywheel.Flywheel;
import com.panopset.fxapp.FontManagerFX;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class FlywheelPrompterFX {

  public void setFlywheel(Flywheel flywheel, HBox prompters, final TextArea list, final TextArea template) {
    if (flywheel == null || prompters == null) {
      return;
    }

    Platform.runLater(() -> {
      prompters.getChildren().clear();
      prompters.getChildren().add(createClearButton(list, template));
      for (Entry<String, String> e : flywheel.getAllValues().entrySet()) {
        if (e.getKey().indexOf("com.panopset") == 0) {
          continue;
        }
        Button btn = new Button(e.getKey());
        btn.setOnAction(event -> {
          String text = "${" + btn.getText() + "}";
          int pos = template.getCaretPosition();
          if (pos > -1) {
            template.insertText(pos, text);
          } else {
            template.appendText(text);
          }
        });
        FontManagerFX.register(btn);
        prompters.getChildren().add(btn);
        prompters.getChildren().add(createCapitalizeButton(template, e.getKey()));
      }
    });
  }

  private Button createClearButton(final TextArea list, final TextArea template) {
    Button rtn = new Button("Clear");
    rtn.setTooltip(new Tooltip("Clear list and template"));
    FontManagerFX.register(rtn);
    rtn.setOnAction(event -> {
      list.setText("");
      template.setText("");
    });
    return rtn;
  }
  
  private Button createCapitalizeButton(final TextArea template, String variable) {
    Button rtn = new Button("C" + variable);
    rtn.setTooltip(new Tooltip("Capitalize " + variable + "."));
    rtn.setOnAction(event -> {
      String text = "${@e com.panopset.compat.Stringop.capitalize(" + variable + ")}";
      int pos = template.getCaretPosition();
      if (pos > -1) {
        template.insertText(pos, text);
      } else {
        template.appendText(text);
      }
    });
    FontManagerFX.register(rtn);
    return rtn;
  }
}
