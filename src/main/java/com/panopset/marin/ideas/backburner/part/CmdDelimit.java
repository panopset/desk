package com.panopset.marin.ideas.backburner.part;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import com.panopset.compat.Logop;
import com.panopset.compat.Nls;
import com.panopset.compat.Stringop;

public class CmdDelimit extends Cmd {

  List<String> list = new ArrayList<>();
  List<String> dups = new ArrayList<>();
  private String token;

  public CmdDelimit(String input, String splitText, Writer output, Options options) {
    this(input, splitText, output);
    setOptions(options);
  }

  public CmdDelimit(String input, String splitText) {
    setInput(input);
    setToken(splitText);
  }

  public CmdDelimit(String input, String splitText, Writer output) {
    setInput(input);
    setToken(splitText);
    setOutput(output);
  }

  public CmdDelimit() {
  }

  public CmdDelimit(String input, String splitText, Options options) {
    setInput(input);
    setToken(splitText);
  }

  public String getToken() {
    if (token == null) {
      token = "";
    }
    return token;
  }

  public void setToken(String value) {
    token = value;
  }

  @Override
  public void exec() {

    String inp;
    try {
      inp = getInput().readLine();
    } catch (IOException e) {
      Logop.error(e);
      Logop.error(Nls.xlate("Failed on initial read, see log."));
      return;
    }

    if (!Stringop.isPopulated(inp)) {
      Logop.warn(Nls.xlate("Nothing to do, no input."));
      return;
    }
    

    if (!Stringop.isPopulated(getToken())) {
      Logop.warn(Nls.xlate("Nothing to do, no delimiter selected."));
      return;
    }
    
    
    int count = 1;
    while (inp != null) {
      FOR00: for (String s : inp.split(getToken())) {
        if (getOptions().removeDups) {
          if (dups.contains(s)) {
            continue FOR00;
          }
          dups.add(s);
        }
        list.add(s);
      }
      try {
        inp = getInput().readLine();
        count++;
      } catch (IOException e) {
        Logop.error(e);
        Logop.error(String.format(Nls.xlate("Failed on read of line %d, see log."), count));
        return;
      }
    }
    if (list.isEmpty()) {
      Logop.warn(Nls.xlate("Nothing to do, nothing outside of the delimiter found."));
      return;
    }
    if (getOptions().sort) {
      list.sort(String::compareToIgnoreCase);
    }
    count = 1;
    for (String s : list) {
      if (!s.isEmpty()) {
        try {
          if (count > 1) {
            getOutput().write(Stringop.getEol());
          }
          getOutput().write(s);
          count++;
        } catch (IOException e) {
          Logop.error(e);
          Logop.error(String.format(Nls.xlate("Failed on write of line %d, see log."), count));
        }
      }
    }
    try {
      getOutput().flush();
      getOutput().close();
    } catch (IOException e) {
      Logop.error(e);
      return;
    }
    Logop.green("Delimit completed.");
  }

  public void setOptions(Options value) {
    opts = value;
  }

  private Options opts;

  private Options getOptions() {
    if (opts == null) {
      opts = new Options();
    }
    return opts;
  }

  public static class Options {
    public boolean sort;
    public boolean removeDups;
  }

  public String getResultAsString() {
    StringWriter sw = new StringWriter();
    setOutput(sw);
    exec();
    return sw.toString();
  }
}
