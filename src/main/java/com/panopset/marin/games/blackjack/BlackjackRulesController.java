package com.panopset.marin.games.blackjack;

import java.net.URL;
import java.util.ResourceBundle;

import com.panopset.fxapp.Anchor;
import com.panopset.fxapp.ReflectorFX;
import com.panopset.fxapp.TextFormatters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BlackjackRulesController implements Initializable, TextFormatters {

  @FXML
  public Label decks;

  @FXML
  ChoiceBox<String> cb_decks;

  @FXML
  public Label seats;

  @FXML
  ChoiceBox<String> cb_seats;

  @FXML
  public CheckBox rule_es;

  @FXML
  public CheckBox rspltaces;

  @FXML
  public CheckBox rule_vs;

  @FXML
  public CheckBox dealer_hits_soft_17;

  @FXML
  public CheckBox rule_sc;

  @FXML
  public CheckBox rule_65;

  @FXML
  public CheckBox rule_even;

  @FXML
  public CheckBox dblaftrsplit;

  @FXML
  public CheckBox rule_ls;

  @FXML
  public CheckBox rule_fd;

  @FXML
  public CheckBox bet_idea_dab;
  
  @FXML
  public CheckBox bet_idea_lir;
  
  @FXML
  public TextField reload_amount;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ReflectorFX.bindBundle(this, resources);
    Anchor.setChoiceBoxChoices(cb_decks, "1","2","6","8");
    Anchor.setChoiceBoxChoices(cb_seats, "1", "2", "3", "4", "5", "6", "7");
    reload_amount.setTextFormatter(createNumberFilter());
  }
}
