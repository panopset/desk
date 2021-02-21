package com.panopset.marin.ideas;

import java.util.ArrayList;
import java.util.List;
import com.panopset.compat.AppVersion;
import com.panopset.compat.Stringop;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AboutPanopsetBox {
  private static int WIDTH = 256;

  public void createAboutBox() {
//    PopupBox rtn = new PopupBox() {
//
//      @Override
//      public Dimension createSize() {
//        return new Dimension(WIDTH, WIDTH);
//      }
//
//      @Override
//      public Component createComponent() {
//        return new Osgw(getSize()) {
//          @Override
//          public void paintCycle(final Graphics2D g) {
//            getCyclotron().paint(g);
//          }
//        }.getPanel();
//      }
//    };
//    rtn.addLink(new Rectangle(0, WIDTH - (WIDTH / 6), WIDTH, WIDTH), "http://panopset.com");
//    return rtn;
  }


  private Cyclotron cy;

  private Cyclotron getCyclotron() {
    if (cy == null) {
      cy = new Cyclotron(WIDTH, WIDTH);
    }
    return cy;
  }


  static class Cyclotron {
    List<Scene> scenes = new ArrayList<>();
    List<Scene> scenesOnDeck = new ArrayList<>();

    private void startOver() {
      cg = 0;
      scenes.clear();
      scenesOnDeck.clear();
      scenesOnDeck.add(new SquareFadeScene());
      scenesOnDeck.add(new BottomTextScene());
    }

    public Cyclotron(final int width, final int height) {
      w = width;
      h = height;
      thirdMargin = w / 3;
      quartMargin = w / 4;
      fifthMargin = w / 5;
      eighthMargin = w / 8;
      tenthMargin = w / 10;
      twelfthMargin = w / 12;
      bottomInitials = h - fifthMargin;

      startOver();

      xPXS[0] = tenthMargin;
      xPXF[0] = xPXS[0] + fifthMargin;

      xPXS[1] = xPXS[0] + thirdMargin;
      xPXF[1] = xPXF[0] + thirdMargin;

      xPXS[2] = xPXS[1] + thirdMargin;
      xPXF[2] = xPXF[1] + thirdMargin;

    }


    final int w;
    final int h;

    int cg;

    final int thirdMargin;
    final int quartMargin;
    final int eighthMargin;
    final int tenthMargin;
    final int twelfthMargin;
    final int fifthMargin;
    final int bottomInitials;
    int[] xPXS = new int[3];
    int[] xPXF = new int[3];

    public void paint(final GraphicsContext g) {
      if (scenes == null) {
        return;
      }
      List<Scene> rmv = new ArrayList<Scene>();
      for (Scene s : scenes) {
        s.paint(g);
        if (!s.isActive()) {
          rmv.add(s);
        }
      }
      for (Scene s : rmv) {
        scenes.remove(s);
      }
      rmv.clear();
      for (Scene s : scenesOnDeck) {
        scenes.add(s);
      }
      scenesOnDeck.clear();
    }

    abstract class Scene {
      abstract void paint(GraphicsContext g);

      private boolean active = true;

      public void setActive(final boolean value) {
        active = value;
      }

      public boolean isActive() {
        return active;
      }
    }

    class SquareFadeScene extends Scene {

      @Override
      void paint(GraphicsContext g) {
        g.setFill(Color.rgb(0, cg, 0));
        if (cg < 66) {
          g.fillRect(0, 0, w, h);
          cg = cg + 11;
        } else {
          scenesOnDeck.add(new WiresScene());
          scenesOnDeck.add(new FlagScene(w - fifthMargin, bottomInitials + 10, 1));
          setActive(false);
        }
      }
    }

    class RedPScene extends Scene {

      int r = 255;
      int df = ((xPXF[0] - xPXS[0]) / 2);
      int x = xPXS[0] + df;
      int y = eighthMargin + df;
      int w = 2;

      @Override
      void paint(GraphicsContext g) {
        g.setFill(Color.rgb(r, 0, 0));
        g.strokeOval(x, y, w, w);
        w += 2;
        x--;
        y--;
        r -= 16;
        if (w > 20) {
          setActive(false);
        }
      }

    }

    class LetterP_Scene extends Scene {

      int x1 = xPXS[0] - 4;
      int y1 = eighthMargin;
      int x2 = xPXF[0] - 4;
      int y2 = y1;

      @Override
      void paint(GraphicsContext g) {
        if (cg > 65) {
          g.setFill(Color.ORANGE);
          g.strokeLine(x1, y1++, x2, y2++);
        }
        if (y1 == (eighthMargin + fifthMargin)) {
          x2 = xPXS[0] + ((xPXF[0] - xPXS[0]) / 2);
        }
        if (y1 > bottomInitials) {
          setActive(false);
          scenesOnDeck.add(new RedPScene());
        }
      }

    }

    class LetterS_Scene extends Scene {

      int x1 = xPXS[1] - 2;
      int y1 = eighthMargin;
      int x2 = xPXF[1] - 2;
      int y2 = y1;

      @Override
      void paint(GraphicsContext g) {
        if (cg > 65) {
          g.setFill(Color.ORANGE);
          g.strokeLine(x1, y1++, x2, y2++);
        }
        if (y1 == eighthMargin + eighthMargin) {
          x2 = xPXS[1] + ((xPXF[1] - xPXS[1]) / 2);
        }

        if (y1 == eighthMargin + (eighthMargin * 2)) {
          x1 = xPXS[1] - 2;
          x2 = xPXF[1] - 2;
        }

        if (y1 == eighthMargin + (eighthMargin * 3)) {
          x1 = xPXS[1] + ((xPXF[1] - xPXS[1]) / 2);
          x2 = xPXF[1] - 2;
        }

        if (y1 == eighthMargin + (eighthMargin * 4)) {
          x1 = xPXS[1] - 2;
          x2 = xPXF[1] - 2;
        }

        if (y1 > bottomInitials) {
          setActive(false);
        }
      }
    }

    class LetterC_Scene extends Scene {

      int x1 = xPXS[2];
      int y1 = eighthMargin;
      int x2 = xPXF[2];
      int y2 = y1;

      @Override
      void paint(GraphicsContext g) {

        if (cg > 65) {
          g.setFill(Color.ORANGE);
          g.strokeLine(x1, y1++, x2, y2++);
        }
        if (y1 == (eighthMargin + fifthMargin)) {
          x2 = xPXS[2] + ((xPXF[2] - xPXS[2]) / 2);
        }
        if (y1 == (bottomInitials - fifthMargin)) {
          x2 = xPXF[2];
        }
        if (y1 > bottomInitials) {
          setActive(false);
        }
      }
    }


    class BottomTextScene extends Scene {
      int x = 10;
      int y = h - 30;

      @Override
      void paint(GraphicsContext g) {
        g.setFill(Color.WHITE);
        g.strokeText(Stringop.COPYRIGHT + " 2017, made in the USA.", x, y);
        g.strokeText(VERSION, x, y + 18);
      }
    }

    class WiresScene extends Scene {
      int y = eighthMargin / 4;
      int y0 = y + eighthMargin;

      @Override
      void paint(GraphicsContext g) {
        g.setFill(Color.LIGHTGREY);
        for (int i = 0; i < 3; i++) {
          int x = xPXS[i];
          g.strokeLine(x, y0, x + eighthMargin, y);
          g.strokeLine(x + eighthMargin, y, w, y);
          y = y + 2;
          y0 = y0 + 2;
        }
        setActive(false);
        scenesOnDeck.add(new LetterP_Scene());
        scenesOnDeck.add(new LetterS_Scene());
        scenesOnDeck.add(new LetterC_Scene());
      }
    }

    class FlagScene extends Scene {

      final int x, y, m;
      final int bw = 12;
      final int lh = 1;

      public FlagScene(int left, int top, int mult) {
        x = left;
        y = top;
        m = mult;
      }

      @Override
      void paint(GraphicsContext g) {
        g.setFill(Color.BLUE);
        g.fillRect(x, y, lh * 28, lh * 13);
        int y0 = y;
        for (int i = 0; i < 13; i++) {
          int x0 = x;
          int w0 = 30;
          if (i % 2 == 0) {
            g.setFill(Color.RED);
          } else {
            g.setFill(Color.WHITE);
          }
          if (i < 7) {
            x0 = x + bw;
            w0 = 30 - bw;
          }
          g.fillRect(x0, y0, w0, lh);
          y0 = y0 + lh;
        }
        setActive(false);
      }
    }

  }

  private static final String VERSION =
      String.format("%s %s", "Panopset version", AppVersion.getVersion());
}
