package com.panopset.marin.bootstrap;

import java.io.File;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.fxapp.PanApplication;

public class ApplicationInformationFactory {

  public ApplicationInformationFactory(File sourceFile) {
    if (sourceFile == null || !sourceFile.exists()) {
      return;
    }
    String simpleName = Fileop.removeExtension(sourceFile.getName());
    setRawName(simpleName);
    setLcName(simpleName.toLowerCase());
    String path = Fileop.getCanonicalPath(sourceFile).replace("\\", "/");
    String className = Fileop.removeExtension(Stringop.pullAfter(path, "/src/main/java/")).replace("/", ".");
    Logop.green(String.join(":", "className", className));
    try {
      PanApplication app = (PanApplication) Class.forName(className).getDeclaredConstructor().newInstance();
      setDescription(app.getDescription());
      setPkg(app.getClass().getPackage().getName());
      setTitle(simpleName);
    } catch (ClassNotFoundException e) {
      Logop.error("Class not found: " + className);
      throw new RuntimeException(e);
    } catch (Exception e) {
      Logop.error(e);
      throw new RuntimeException(e);
    }
  }
  
//  public ApplicationInformation createApplicationInformation() {
//    ApplicationInformation rtn = new ApplicationInformation();
//    rtn.setDescription(getDescription());
//    rtn.setLcName(getLcName());
//    rtn.setPkg(getPkg());
//    rtn.setRawName(getRawName());
//    rtn.setTitle(getTitle());
//    return rtn;
//  }

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
