package com.panopset.marin.apps.gr;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.fxapp.PanDirSelectorFX;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * AppGrScene.fxml controller.
 *
 */
public class GrScene implements Initializable {
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (!gr_rbDoNothing.isSelected() && !gr_rbw2u.isSelected() && !gr_rbu2w.isSelected()) {
      gr_rbDoNothing.setSelected(true);
    }
  }

  @FXML
  private void handleReplaceAllAction(Event event)  {
    if (gr_rbDoNothing.isSelected()) {
      Stringop.setEol(Stringop.LINE_SEPARATOR);
    } else if (gr_rbu2w.isSelected()) {
      Stringop.setEol(Stringop.DOS_RTN);
    } else if (gr_rbw2u.isSelected()) {
      Stringop.setEol(Stringop.LINE_FEED);
    }
    GlobalReplaceProcessor te = new GlobalReplaceProcessor();
    try {
      te.process(PanDirSelectorFX.INSTANCE.getDirectory(), fxid_fromtext.getText(),

          fxid_totext.getText(), fxid_exts.getText(), fxid_regex.getText(), gr_recursive.isSelected());
    } catch (IOException e) {
      Logop.error(e);
    }
  }

  @FXML
  ToggleGroup gr_crlf_tg;

  @FXML
  RadioButton gr_rbDoNothing;
  
  @FXML
  RadioButton gr_rbw2u;
  
  @FXML
  RadioButton gr_rbu2w;

  @FXML
  CheckBox gr_recursive;
  
  @FXML
  TextField fxid_fromtext;

  @FXML
  TextField fxid_totext;

  @FXML
  VBox mainbox;

  @FXML
  HBox grdirselect;

  @FXML
  TextField fxid_regex;

  @FXML
  TextField fxid_exts;

}
