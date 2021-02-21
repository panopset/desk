package com.panopset.marin.fx;

import java.util.HashMap;
import java.util.Map;
import com.panopset.compat.Logop;
import javafx.scene.image.Image;

public enum ImageMapCache {

  INSTANCE;

  private ImageMapCache() {}

  public static Image get(String key) {
    return INSTANCE.map.get(key);
  }

  public static Image get(String key, String path) {
    return INSTANCE.find(key, path);
  }

  Image find(String key, String path) {
    Image rtn = map.get(key);
    if (rtn == null) {
      try {
        rtn = new Image(path);
      } catch (IllegalArgumentException iae) {
        Logop.error(path, iae);
        return null;
      }
      map.put(key, rtn);
    }
    return rtn;
  }

  private Map<String, Image> map = new HashMap<>();
}
