package com.panopset.marin.apps.scrambler;

import java.net.URL;
import java.util.ResourceBundle;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.fxapp.TextFormatters;
import com.panopset.util.TextScrambler;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * AppScramblerScene.fxml controller
 */
public class ScramblerScene implements Initializable, TextFormatters {

  @FXML
  CheckBox scrambler_show;

  @FXML
  PasswordField fxid_pwd;

  @FXML
  TextField fxid_pwdshow;

  @FXML
  TextArea fxid_message;

  @FXML
  Button fxid_unscramble;

  @FXML
  Button fxid_scramble;

  @FXML
  Label fxid_passphrase;

  @FXML
  Label fxid_koi_label;

  @FXML
  TextField fxid_koi;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    fxid_pwd.textProperty().bindBidirectional(fxid_pwdshow.textProperty());
    fxid_pwd.visibleProperty().bind(scrambler_show.selectedProperty().not());
    fxid_pwdshow.visibleProperty().bind(scrambler_show.selectedProperty());
    fxid_koi.setTextFormatter(createIntegerFilter());
    if ("0".equals(Stringop.notNull(fxid_koi.getText()))) {
      Platform.runLater(() -> fxid_koi
          .setText(String.format("%d", TextScrambler.DEFAULT_KEY_OBTENTION_ITERATIONS)));
    }
  }

  @FXML
  private void handleShowAction(Event event) {
    // Nothing to do here, thanks to https://stackoverflow.com/questions/50922253.
  }

  @FXML
  private void handleUnscrambleAction(Event event) {
    String value = fxid_message.getText();
    try {
      fxid_message.setText(createScrambler().decrypt(getPassword(), value));
    } catch (Exception e) {
      Logop.error(e);
    }
  }

  @FXML
  private void handleScrambleAction(Event event) {
    String value = fxid_message.getText();
    try {
      fxid_message.setText(createScrambler().encrypt(getPassword(), value));
    } catch (Exception e) {
      Logop.error(e);
    }
  }

  private TextScrambler createScrambler() {
    return new TextScrambler().withKeyObtentionIters(getKeyObIters());
  }

  private String getPassword() {
    return scrambler_show.isSelected() ? fxid_pwdshow.getText() : fxid_pwd.getText();
  }

  private int getKeyObIters() {
    String strVal = fxid_koi.getText();
    if (Stringop.isPopulated(strVal)) {
      int intVal = Stringop.parseInt(strVal);
      if (intVal > 0) {
        return intVal;
      }
    }
    return TextScrambler.DEFAULT_KEY_OBTENTION_ITERATIONS;
  }
}
