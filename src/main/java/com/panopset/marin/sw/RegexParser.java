package com.panopset.marin.sw;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {
  
  private Map<String, FieldDDS> map = new HashMap<>();
  
  String inp;

  public RegexParser setInput(String value) {
    inp = value;
    return this;
  }

  public void addFieldMatch(String key, String startsWith, String endsWith) {
    map.put(key, new FieldDDS(Pattern.compile(startsWith + ".*" + endsWith)));
  }

  public void addField(String key, String startsWith, String endsWith) {
    map.put(key, new FieldDDS(Pattern.compile("(?<=" + startsWith + ")(.*)(?=" + endsWith + ")")));
  }

  public String getField(String key) {
    String rtn = map.get(key).extract(inp);
    return rtn.replace("}", "").replace("\\{", "");
  }

  private static class FieldDDS {
    private final Pattern p;
    
    FieldDDS(final Pattern pattern) {
      p = pattern;
    }

    public String extract(String input) {
      Matcher m = p.matcher(input);
      if (m.find()) {
        return m.group(1);
      }
      return "";
    }
  }
}
