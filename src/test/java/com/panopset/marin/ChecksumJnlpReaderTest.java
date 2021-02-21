package com.panopset.marin;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.panopset.marin.bootstrap.ChecksumInformation;
import com.panopset.marin.bootstrap.JnlpReader;

public class ChecksumJnlpReaderTest {

  private static JnlpReader cjr;

  @BeforeAll
  public static void init() {
    cjr = new JnlpReader(TEST_DATA);
  }

  @Test
  public void test() throws Exception {
    List<ChecksumInformation> list = cjr.getChecksumInformation();
    assertTrue(list.size() == 2);
    assertEquals("md5", list.get(0).getAlg());
  }

  private static final String TEST_DATA =
      "[{\"alg\":\"md5\",\"val\":\"ce301f0897a8ac\"},{\"alg\":\"sha1\",\"val\":\"dc921546c\"}]";

}
