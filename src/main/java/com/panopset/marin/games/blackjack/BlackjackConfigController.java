package com.panopset.marin.games.blackjack;

import java.net.URL;
import java.util.ResourceBundle;

import com.panopset.fxapp.ReflectorFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

public class BlackjackConfigController implements Initializable {

  @FXML
  public Tab rules;

  @FXML
  BlackjackRulesController blackjackRulesController;

  @FXML
  BlackjackCountingController blackjackCountingController;
  
  @FXML
  BlackjackBasicController blackjackBasicController;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ReflectorFX.bindBundle(this, resources);
  }
}
