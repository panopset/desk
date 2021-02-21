package com.panopset.marin.ideas.backburner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.panopset.util.rpg.TextProcessor;
import com.panopset.util.Nls;
import com.panopset.compat.Stringop;

public class Webvalid {

  private static class Status {
    String url = "";
    String error = "";
    String firstLine = "";

    Status(String urlStr) {
      url = urlStr;
    }

    boolean isValid() {
      return !Stringop.isPopulated(error);
    }

    public void setError(String value) {
      error = value;
    }

    public void setFirstLine(String value) {
      firstLine = value;
    }
  }

  public static void main(final String... args) {
//    new Webvalid().show();
  }

  boolean active = false;

  Status wget(String urlStr) {
    Status status = new Status(urlStr);
    try {
      URL url = new URL(urlStr);
      try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
        status.setFirstLine(br.readLine());
      } catch (IOException e) {
        status.setError(e.getMessage());
      }
    } catch (MalformedURLException e) {
      status.setError(e.getMessage());
    }
    return status;
  }


//  protected JPanel createCenterPanel() {
//    JPanel rtn = new JPanel(new GridLayout(1, 1));
//    JSplitPane sp = new JSplitPane();
//    sp.setLeftComponent(getInputPanel());
//    sp.setRightComponent(getOsgw().getPanel());
//    sp.setDividerLocation(400);
//    rtn.add(sp);
//    getInputTA().setToolTipText(Nls.xlate("Enter list of URLs to ping."));
//    return rtn;
//  }

//  private Osgw osgw;
//
//  private Osgw getOsgw() {
//    if (osgw == null) {
//      osgw = new Osgw(1250, 1250) {
//
//        @Override
//        public void paintCycle(Graphics2D g) {
//          g.setColor(Color.BLACK);
//          g.fillRect(0, 0, 1250, 1250);
//          Font font = FontManagerSwing.getBoldArial();
//          int spacing = (int) (FontManagerFX.getSize() * 1.2);//getInputTA().getFontMetrics(font).getHeight();
//          int y = spacing;
//          g.setFont(font);
//          if (Thread.holdsLock(map)) {
//            return;
//          }
//          synchronized (map) {
//            for (Status st : map) {
//              if (st.isValid()) {
//                g.setColor(Color.green);
//                g.drawString("ok", 15, y);
//              } else {
//                g.setColor(Color.red);
//                g.drawString("xx", 15, y);
//              }
//              if (st.isValid()) {
//                g.setColor(Color.white);
//                g.drawString(st.firstLine, 50, y);
//              } else {
//                g.setColor(Color.red);
//                g.drawString(st.error, 50, y);
//
//              }
//              y = y + spacing - 1;
//            }
//          }
//        }
//
//      };
//    }
//    return osgw;
//  }

  private List<Status> map = Collections.synchronizedList(new ArrayList<Status>());

//  @Override
  protected void doProcess(TextProcessor textProcessor) {
    synchronized (map) {
      map.clear();
      updateStatus(textProcessor);
    }
  }

  protected void updateStatus(TextProcessor textProcessor) {
//    for (String str : textProcessor.getText()) {
//      evaluate(new Status(str));
//    }
  }

  private void evaluate(Status status) {
    if (status.url == null) {
      status.setError(Nls.xlate("Null URL"));
    }
    if (status.url.length() < 1) {
      status.setError(Nls.xlate("Blank URL"));a
    }
    status = wget(status.url);
    map.add(status);
  }

  //@Override
  protected String assembleProcessButtonName() {
    return Nls.xlate("Ping");
  }


  //@Override
  protected String assembleProcessButtonTipText() {
    return Nls.xlate("Ping each of the sites given, and show results.");
  }


  //@Override
  public String getDescription() {
    return "Ping several http sites.";
  }
}
