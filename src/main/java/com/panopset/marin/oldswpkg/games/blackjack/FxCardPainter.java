package com.panopset.marin.oldswpkg.games.blackjack;

import com.panopset.deck52.Card;
import com.panopset.deck52.Suit;
import com.panopset.fxapp.FontManagerFX;
import com.panopset.marin.game.card.images.BlackjackImages;
import com.panopset.marin.oldswpkg.cards.pngmap.CardLocation;
import com.panopset.marin.oldswpkg.cards.pngmap.CardPainter;
import com.sun.javafx.tk.Toolkit;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class FxCardPainter {
  private static final String BASE_PATH = "/com/panopset/marin/game/card/svg/poker-qr-plain/";
  
  private final CardPainter cp;
  private final CardPathKeys sck;
  private final int cw;
  private final int ch;
  public Suit[] getOrder() {
    return cp.getOrder();
  }

  public FxCardPainter() {
    Double mw = getMapImg().getWidth();
    Double mh = getMapImg().getHeight();
    cp = new CardPainter(mw.intValue(), mh.intValue());
    sck = new CardPathKeys(BASE_PATH, "svg");
    ch = (int) (98 * FontManagerFX.getSvgRatio());
    cw = (int) (73 * FontManagerFX.getSvgRatio());
  }

  public void paintCard(GraphicsContext g, CardLocation cl, Color hlt) {
    // paintCardBitmap(g, cl.getCard(), cl.x, cl.y, hlt);
    paintCardSvg(g, cl.getCard(), cl.x, cl.y, hlt);
  }

  public void paintCard(GraphicsContext g, CardLocation cl) {
    paintCard(g, cl.getCard(), cl.x, cl.y);
  }

  public void paintCard(GraphicsContext g, Card card, int x, int y) {
    // paintCardBitmap(g, card, x, y, Color.BLACK);
    paintCardSvg(g, card, x, y, Color.BLACK);
  }

  public void paintCardSvg(GraphicsContext g, Card card, int x, int y, Color hlt) {
    Toolkit.getToolkit().checkFxUserThread();
    if (card.isShowing()) {
      g.drawImage(sck.getImage(card), x, y, cw, ch);
    } else {
      if (card.isBlue()) {
        g.drawImage(sck.getBackBlue(), x, y, cw, ch);
      } else {
        g.drawImage(sck.getBackRed(), x, y, cw, ch);
      }
    }
  }

  public void paintCardBitmap(GraphicsContext g, Card card, int x, int y, Color hlt) {
    Toolkit.getToolkit().checkFxUserThread();
    int[] b = cp.getPaintDimensionsFX(card, x, y);
    int arc = 10;
    if (card.isShowing()) {
      g.drawImage(mapImg, b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7]);
    } else {
      if (card.isBlue()) {
        g.setFill(Color.BLUE);
      } else {
        g.setFill(Color.RED);
      }
      g.fillRoundRect(b[4], b[5], b[6], b[7], arc, arc);
      for (int i = 0; i < getBorder(); i++) {
        g.strokeRect(b[4] + i, b[5] + i, b[6] - (i * 2), b[7] - (i * 2));
      }
    }
    g.setFill(hlt);
    g.strokeRect(b[4], b[5], b[6], b[7]);
  }

  private Integer border;

  public int getBorder() {
    if (border == null) {
      if (getCardWidth() > 30) {
        border = getCardWidth() / 30;
      } else {
        border = 1;
      }
    }
    return border;
  }

  public int getCardHeight() {
    return cp.getCardHeight();
  }

  public int getCardWidth() {
    return cp.getCardWidth();
  }

  private Image mapImg;

  private Image getMapImg() {
    if (mapImg == null) {
      mapImg = BlackjackImages.getCardMap();
    }
    return mapImg;
  }
}
