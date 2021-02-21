package com.panopset.marin;

/**
 * Interface to a persistent map of String Objects.
 * @author Karl Dinwiddie
 *
 */
public interface PersistentMap {

  /**
   * @param key Key.
   * @param value Value
   */
  void put(String key, String value);

  /**
   * @param key
   * @return Value, empty String if not found.
   */
  String get(String key);

}
