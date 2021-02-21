package com.panopset.marin.secure.checksums;

import com.panopset.compat.Stringop;

public enum ChecksumType {
  BYTES, MD5, SHA1, SHA256, SHA384, SHA512;

  public static ChecksumType find(String name) {
    if (Stringop.isEmpty(name)) {
      return null;
    }
    for (ChecksumType cst : values()) {
      if (cst.name().equalsIgnoreCase(name)) {
        return cst;
      }
    }
    return null;
  }
}
