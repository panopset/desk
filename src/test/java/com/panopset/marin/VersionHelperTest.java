package com.panopset.marin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.panopset.marin.bootstrap.VersionHelper;

class VersionHelperTest {

  @Test
  void testVersionHelper() {
    Assertions.assertEquals(16, VersionHelper.getAvailableVersion().length());
  }

}
