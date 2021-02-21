package com.panopset.marin.physics.model;

import javafx.scene.paint.Color;

public enum QMColor {

  RED(Color.RED), BLUE(Color.BLUE), GREEN(Color.GREEN),

  ANTIRED(Color.CYAN), ANTIGREEN(Color.MAGENTA), ANTIBLUE(Color.YELLOW);


  QMColor(Color value) {
    color = value;
  }
  
  private final Color color;
  
  public Color getColor() {
    return color;
  }
}
