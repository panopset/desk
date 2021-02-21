package com.panopset.marin.apps.fw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ResourceBundle;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class AppFwTabStract implements Initializable {

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    stract_extract.setOnAction((event) -> {
      int i = stract_src.getSelection().getStart();
      int j = stract_src.getSelection().getEnd();
      if (j > i) {
        try {
          process(i, j);
        } catch (IOException e) {
          Logop.error(e);
        }
      }
    });
  }

  private void process(int startIndex, int endIndex) throws IOException {
    StringReader sr = new StringReader(stract_src.getText());
    try (BufferedReader br = new BufferedReader(sr)) {
      String s = br.readLine();
      while (s != null) {
        int lnth = s.length();
        if (lnth > startIndex) {
          String out = "";
          if (endIndex > lnth) {
            out = s.substring(startIndex);
          } else {
            out = s.substring(startIndex, endIndex);
          }
          if (stract_xml.isSelected()) {
            out = out.replace(">", ">\n");
          }
          stract_out.appendText(out);
          if (!stract_flatten.isSelected()) {
            stract_out.appendText(Stringop.getEol());
          }
        }
        s = br.readLine();
      }
    }
  }

  @FXML
  CheckBox stract_flatten;

  @FXML
  CheckBox stract_xml;

  @FXML
  Button stract_extract;

  @FXML
  TextArea stract_src;

  @FXML
  TextArea stract_out;
}
