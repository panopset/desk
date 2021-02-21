package com.panopset.marin;

import org.junit.jupiter.api.Test;
import com.panopset.marin.sw.RegexParser;
import static org.junit.jupiter.api.Assertions.*;

public class RegexParserTest {

  @Test
  public void test() {
    RegexParser rp = new RegexParser();
    rp.addField("fileName", "uri=\\\"", ".bar\"");
    rp.addField("textValue", "CDATA\\[", "\\]\\]");
    rp.setInput(TEST_LINE_00);
    assertEquals("foo", rp.getField("fileName"));
    assertEquals("corresponding text", rp.getField("textValue"));
    rp.setInput(TEST_LINE_01);
    assertEquals("zonk", rp.getField("fileName"));
    assertEquals("some other text", rp.getField("textValue"));
  }

  private final String TEST_LINE_00 = "<text00 some_text_01=\"true\" uri=\"foo.bar\"><![CDATA[corresponding text]]></static>";
  private final String TEST_LINE_01 = "<text00 some_text_01=\"true\" uri=\"zonk.bar\"><![CDATA[some other text]]></static>";
}
