package com.panopset.marin.games.blackjack;

import java.net.URL;
import java.util.ResourceBundle;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.fxapp.Anchor;
import com.panopset.fxapp.ReflectorFX;
import com.panopset.fxapp.TextFormatters;
import com.panopset.games.casino.blackjack.CardCounterImpl;
import com.panopset.games.casino.blackjack.DefaultResources;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BlackjackCountingController implements Initializable, TextFormatters {

  @FXML
  public TextArea ta_counting_systems;

  @FXML
  public ChoiceBox<String> cb_counting_systems;

  @FXML
  public TextField count_positive;

  @FXML
  public TextField count_negative;

  @FXML
  public TextField minimum_bet;

  @FXML
  public TextField large_bet;

  @FXML
  public TextField target_stake;

  @FXML
  public Button btn_counting_reset;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ReflectorFX.bindBundle(this, resources);
    String countingSystems = ta_counting_systems.getText();
    if (!Stringop.isPopulated(countingSystems)) {
      userReset();
    }
    Anchor.setChoiceBoxChoices(cb_counting_systems, CardCounterImpl.getKeyNames());
    cb_counting_systems.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observableValue, Number priorIndex, Number newIndex) {
        new Thread(() -> {
          int selectedIndex = newIndex.intValue();
          CardCounterImpl.updateByIndex(selectedIndex);
          Logop.info(String.format("Counting systems updated to %s.", CardCounterImpl.getSystem().getName()));
        }).start();
      }
    });
    count_positive.setTextFormatter(createNumberFilter());
    count_negative.setTextFormatter(createIntegerFilter());
    minimum_bet.setTextFormatter(createNumberFilter());
    large_bet.setTextFormatter(createNumberFilter());
    target_stake.setTextFormatter(createNumberFilter());
    btn_counting_reset.setOnAction((event -> {
      userReset();
    }));
  }
  
  private void userReset() {
    String dft = DefaultResources.getDefaultCountingSystems();
    ta_counting_systems.setText(dft);
  }
}
