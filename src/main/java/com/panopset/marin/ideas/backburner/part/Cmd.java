package com.panopset.marin.ideas.backburner.part;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import com.panopset.compat.Logop;

public abstract class Cmd {
  
  public abstract void exec();

  public void setInput(String value) {
    setBufferedReader(
        new BufferedReader(new StringReader(value)));
  }
  
  public void setInput(Reader value) {
    setBufferedReader(new BufferedReader(value));
  }
  
  public void setOutput(Writer value) {
    if (value instanceof BufferedWriter) {
      setBufferedWriter((BufferedWriter) value);
    }
    setBufferedWriter(new BufferedWriter(value));
  }
  
  public void setOutput(BufferedWriter value) {
    setBufferedWriter(value);
  }
  
  private BufferedReader inp;
  private BufferedWriter out;
  
  private void setBufferedReader(BufferedReader value) {
    inp = value;
  }

  protected BufferedReader getInput() {
    if (inp == null) {
      setInput("");
    }
    return inp;
  }
  
  private void setBufferedWriter(BufferedWriter value) {
    if (out != null) {
      Logop.warn("Poor design alert, overwriting existing output.");
    }
    out = value;
  }

  protected BufferedWriter getOutput() {
    if (out == null) {
      Logop.error("No output ever set, dumping to dev/null.");
      // Java 11:
      //out = new BufferedWriter(OutputStreamWriter.nullWriter());
      out = new BufferedWriter(new NullOutputStreamWriter());
    }
    return out;
  }

  private static class NullOutputStreamWriter extends OutputStreamWriter {
    public NullOutputStreamWriter() {
      super(new OutputStream() {
        @Override
        public void write(int b) throws IOException {

        }
      });
    }
  }
}
