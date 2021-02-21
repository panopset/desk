package com.panopset.marin.sw;

public class WaveFile {
  
  private final String name;
  private String used;
  private String text;

  public WaveFile(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  
  public void setUsed(String value) {
    used = value;
  }
  
  public String getUsed() {
    if (used == null) {
      used = "";
    }
    return used;
  }
  
  public void setText(String value) {
    text = value;
  }
  
  public String getText() {
    if (text == null) {
      text = "";
    }
    return text;
  }
  
  @Override
  public String toString() {
    return String.join("|", getName(), getUsed(), getText());
  }
}

