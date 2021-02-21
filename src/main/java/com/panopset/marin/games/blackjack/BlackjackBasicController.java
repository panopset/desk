package com.panopset.marin.games.blackjack;

import java.net.URL;
import java.util.ResourceBundle;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.fxapp.ReflectorFX;
import com.panopset.games.casino.blackjack.DefaultResources;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BlackjackBasicController implements Initializable {

  @FXML
  public Button btn_reset_basic;

  @FXML
  public TextArea ta_basic;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ReflectorFX.bindBundle(this, resources);
    String basicStrategy = ta_basic.getText();
    if (!Stringop.isPopulated(basicStrategy)) {
      userReset();
    }
    btn_reset_basic.setOnAction((event -> {
      userReset();
      Logop.clear();
    }));
  }

  private void userReset() {
    String dft = DefaultResources.getDefaultBasicStrategy();
    ta_basic.setText(dft);
  }
}
