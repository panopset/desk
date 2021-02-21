package com.panopset.marin.bootstrap;

public class ApplicationInformation {

  public String getRawName() {
    if (rawName == null) {
      rawName = "";
    }
    return rawName;
  }

  public void setRawName(String upperCaseName) {
    this.rawName = upperCaseName;
  }

  public String getLcName() {
    if (lcName == null) {
      lcName = "";
    }
    return lcName;
  }

  public void setLcName(String lowerCaseName) {
    this.lcName = lowerCaseName;
  }
  
  public String getPkg() {
    if (pkg == null) {
      pkg = "";
    }
    return pkg;
  }
  
  public void setPkg(String packageName) {
    pkg = packageName;
  }

  public String getTitle() {
    if (title == null) {
      title = "";
    }
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    if (description == null) {
      description = "";
    }
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  private String rawName;
  private String lcName;
  private String pkg;
  private String title;
  private String description;

}
