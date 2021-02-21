package com.panopset.marin.games.blackjack;

import java.util.ResourceBundle;

import com.panopset.fxapp.FontManagerFX;
import com.panopset.games.casino.blackjack.BlackjackGameEngine;

import javafx.scene.control.Button;
import static com.panopset.games.casino.blackjack.CommandDefinitions.*;

public class BlackjackFxGame {
  private final ResourceBundle bundle;
  private final BlackjackGameEngine engine;
  private final BlackjackGameController blackjackGameController;

  public  BlackjackFxGame(ResourceBundle resourceBundle, BlackjackGameEngine blackjackGameEngine,
    BlackjackGameController blackjackGameController) {
    this.bundle = resourceBundle;
    this.engine = blackjackGameEngine;
    this.blackjackGameController = blackjackGameController;
  }

  public void updateControls() {
    blackjackGameController.gameControlFlow.getChildren().clear();
    blackjackGameController.gameControlFlow.getChildren().add(getDealButton());
    blackjackGameController.gameControlFlow.getChildren().add(getStandButton());
    blackjackGameController.gameControlFlow.getChildren().add(getHitButton());
    blackjackGameController.gameControlFlow.getChildren().add(getSplitButton());
    blackjackGameController.gameControlFlow.getChildren().add(getDoubleButton());
    blackjackGameController.gameControlFlow.getChildren().add(getSurrenderButton());
  }

  private Button splitButton;
  private Button getSplitButton() {
    if (splitButton == null) {
      splitButton = new Button(bundle.getString("cmd_split"));
      splitButton.setOnAction((event) ->
        engine.exec(CMD_SPLIT)
      );
      FontManagerFX.register(splitButton);
    }
    return splitButton;
  }


  private Button surrenderButton;
  private Button getSurrenderButton() {
    if (surrenderButton == null) {
      surrenderButton = new Button(bundle.getString("surrender"));
      surrenderButton.setOnAction((event) ->
        engine.exec(CMD_SURRENDER)
      );
      FontManagerFX.register(surrenderButton);
    }
    return surrenderButton;
  }


  private Button doubleButton;
  private Button getDoubleButton() {
    if (doubleButton == null) {
      doubleButton = new Button(bundle.getString("cmd_double"));
      doubleButton.setOnAction((event) ->
        engine.exec(CMD_DOUBLE)
      );
      FontManagerFX.register(doubleButton);
    }
    return doubleButton;
  }


  private Button dealButton;
  private Button getDealButton() {
    if (dealButton == null) {
      dealButton = new Button(bundle.getString("deal"));
      dealButton.setOnAction((event) ->
        engine.exec(CMD_DEAL)
      );
      FontManagerFX.register(dealButton);
    }
    return dealButton;
  }


  private Button hitButton;
  private Button getHitButton() {
    if (hitButton == null) {
      hitButton = new Button(bundle.getString("cmd_hit"));
      hitButton.setOnAction((event) ->
        engine.exec(CMD_HIT)
      );
      FontManagerFX.register(hitButton);
    }
    return hitButton;
  }


  private Button standButton;
  private Button getStandButton() {
    if (standButton == null) {
      standButton = new Button(bundle.getString("cmd_stand"));
      standButton.setOnAction((event) ->
        engine.exec(CMD_STAND)
      );
      FontManagerFX.register(standButton);
    }
    return standButton;
  }
}
