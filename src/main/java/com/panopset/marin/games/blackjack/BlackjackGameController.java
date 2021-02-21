package com.panopset.marin.games.blackjack;

import java.net.URL;
import java.util.ResourceBundle;

import com.panopset.fxapp.FontManagerFX;
import com.panopset.fxapp.ReflectorFX;
import com.panopset.games.casino.blackjack.BlackjackGameEngine;
import com.panopset.games.casino.blackjack.CycleSnapshot;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;

public class BlackjackGameController implements Initializable {

  public void initialize(URL location, ResourceBundle resources) {
    ReflectorFX.bindBundle(this, resources);
    startPaintCycle();
  }

  private void handleKey(KeyEvent keyEvent) {
    if (engine == null) {
      return;
    }
    engine.exec(keyEvent.getText().toLowerCase());
  }

  private int fontSize = 0;
  private boolean gameStarted = false;
  private CycleSnapshot paintedSnapshot;
  private boolean dirty = true;
  private AnimationTimer timer;

  private AnimationTimer getTimer() {
    if (timer == null) {
      timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
          if (now - lastUpdate > 50000000L) {
            issuePaintRequestIfNecessary();
            lastUpdate = now;
          }
        }
      };

    }
    return timer;
  }

  long lastUpdate = 0;

  private void startPaintCycle() {
    getTimer().start();
  }

  private void issuePaintRequestIfNecessary() {
    dirty = paintedSnapshot == null || paintedSnapshot != engine.getCurrentSnapshot()
        || fontSize != FontManagerFX.getSize();
    if (dirty) {
      paintedSnapshot = paintFelt();
      dirty = false;
    }
  }

  public void update() {
    dirty = true;
  }

  private CycleSnapshot paintFelt() {
    if (felt == null || engine == null) {
      return null;
    }
    if (!engine.isActive()) {
      return null;
    }
    if (fontSize != FontManagerFX.getSize()) {
      fontSize = FontManagerFX.getSize();
    }
    int layoutHeight = (int) felt.getParent().getLayoutBounds().getHeight();
    int layoutWidth = (int) felt.getParent().getLayoutBounds().getWidth();

    if (layoutHeight < 10 || layoutWidth < 10) {
      return null;
    }

    if (!gameStarted) {
      felt.getScene().setOnKeyPressed(this::handleKey);
      gameStarted = true;
    }

    felt.setWidth(layoutWidth);
    felt.setHeight(layoutHeight);

    GraphicsContext gc = felt.getGraphicsContext2D();
    CycleSnapshot rtn = engine.getCurrentSnapshot();
    getFeltPainter().draw(rtn, gc, layoutWidth, layoutHeight);
    return rtn;
  }

  private FeltPainter fp;

  private FeltPainter getFeltPainter() {
    if (fp == null) {
      fp = new FeltPainter();
    }
    return fp;
  }

  @FXML
  public Canvas felt;

  @FXML
  public FlowPane gameControlFlow;

  private BlackjackGameEngine engine;

  public void setEngine(BlackjackGameEngine engine) {
    this.engine = engine;
    engine.getZombie().addStopAction(getTimer()::stop);
  }
}
