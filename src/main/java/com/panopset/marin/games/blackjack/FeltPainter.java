package com.panopset.marin.games.blackjack;

import java.io.StringWriter;
import com.panopset.compat.Stringop;
import com.panopset.fxapp.FontManagerFX;
import com.panopset.games.casino.blackjack.BlackjackCard;
import com.panopset.games.casino.blackjack.CycleSnapshot;
import com.panopset.games.casino.blackjack.HandPlayer;
import com.panopset.games.casino.blackjack.Player;
import com.panopset.marin.game.card.images.BlackjackImages;
import com.panopset.marin.oldswpkg.games.blackjack.BlackjackCmdBinder;
import com.panopset.marin.oldswpkg.games.blackjack.BlackjackTable;
import com.panopset.marin.oldswpkg.games.blackjack.FxCardPainter;
import com.panopset.marin.oldswpkg.games.blackjack.FxChipPainter;
import com.panopset.marin.oldswpkg.games.blackjack.LayoutChips;
import com.panopset.marin.oldswpkg.games.blackjack.LayoutDealer;
import com.panopset.marin.oldswpkg.games.blackjack.LayoutPlayer;
import com.panopset.util.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FeltPainter {

  private void updateTable(int w, int h) {
    if (weDontHaveEnoughRoomToWorkWith(w, h)) {
      return;
    }
    getTable().setBounds(w, h);
    chpptr = new FxChipPainter();
    cptr = new FxCardPainter();
    getTable().setCardHeight(cptr.getCardHeight());
  }

  private static boolean weDontHaveEnoughRoomToWorkWith(int w, int h) {
    return w < 200 || h < 200;
  }

  private FxChipPainter chpptr;
  private FxCardPainter cptr;

  private int verticalSeparator;

  public synchronized void draw(CycleSnapshot cs, GraphicsContext g, int width, int height) {
    updateTable(width, height);
    if (cs == null || width == 0.0 || height == 0.0) {
      return;
    }
    verticalSeparator = (int) (FontManagerFX.getSize() * 1.2);
    if (Stringop.isPopulated(cs.getMistakeMessage())) {
      g.setFill(Color.DARKRED);
    } else {
      g.setFill(Color.DARKGREEN);
    }
    g.fillRect(0, 0, width, height);
    paintChips(g, getTable().getChipTray(), cs);
    if (getTable().getStatusTile() != null) {
      paintStatus(cs, g, getTable().getStatusTile());
    }
    paintDealer(g, getTable().getDealerTile(), cs);
    paintPlayer(g, getTable().getPlayerTile(), cs);
    paintMsgLandscape(cs, g, getTable().getMsgTile());
  }

  private void paintMsgLandscape(CycleSnapshot cs, GraphicsContext g, Tile t) {
    initGforMsg(cs, g, t);
    int y = t.getBottom() - (verticalSeparator / 2);
    paintMistakeMessage(cs, g, t, y, verticalSeparator);
  }

  private void initGforMsg(CycleSnapshot cs, GraphicsContext g, Tile t) {
    if (dbg) {
      g.setFill(Color.GRAY);
      g.fillRect(t.getLeft(), t.getTop(), t.getWidth(), t.getBottom());
    }
    g.setFill(Color.BLACK);
    g.setFont(getFont0());
    if (Stringop.isPopulated(cs.getMistakeMessage())) {
      g.setFill(Color.YELLOW);
    } else {
      g.setFill(Color.DARKBLUE);
    }
    g.setFont(getFont0());
  }

  private void paintMistakeMessage(CycleSnapshot cs, GraphicsContext g, Tile t, int y, int vs) {
    if (Stringop.isPopulated(cs.getMistakeMessage())) {
    }
    if (Stringop.isPopulated(cs.getMistakeMessage())) {
      g.setFill(Color.YELLOW);
      g.setFont(Font.font(java.awt.Font.MONOSPACED, FontManagerFX.getSize()));
      g.fillText(String.format("%s - dealer up card.", cs.getMistakeHeader()), 0, y);
      g.fillText(String.format("%s - basic strategy line for your hand.", cs.getMistakeMessage()),
          0, y + vs);
      g.setFont(getFont0());
    } else if (Stringop.isPopulated(cs.getDealerMessage())) {
      g.setFill(Color.WHITE);
      g.fillText(cs.getDealerMessage(), 0, y);
    }
    int newY = y - vs;
    paintKeyMeanings(g, t.getLeft(), newY);
  }

  private void paintKeyMeanings(GraphicsContext g, int x, int y) {
    g.setFont(getFont0());
    g.setFill(Color.LIGHTYELLOW);
    g.fillText(commander.toString(), x, y);
  }

  public final BlackjackCmdBinder commander = new BlackjackCmdBinder();

  private void paintDealer(GraphicsContext g, Tile t, CycleSnapshot cs) {
    if (dbg) {
      g.setFill(Color.RED);
      g.fillRect(t.getLeft(), t.getTop(), t.getWidth(), t.getBottom());
    }
    LayoutDealer o = new LayoutDealer(t, new int[] {cptr.getCardWidth(), cptr.getCardHeight()}, cs);
    g.setFill(Color.BLACK);
    for (BlackjackCard bc : cs.getDealer().getCards()) {
      cptr.paintCard(g, bc.getCard(), o.getNextDealerX(), 0);
    }
  }

  private void paintPlayer(GraphicsContext g, Tile t, CycleSnapshot cs) {
    if (dbg) {
      g.setFill(Color.YELLOW);
      g.fillRect(t.getLeft(), t.getTop(), t.getWidth(), t.getBottom());
    }
    g.setFill(Color.BLACK);
    LayoutPlayer o = new LayoutPlayer(t, new int[] {cptr.getCardWidth(), cptr.getCardHeight()}, cs,
        new int[] {chpptr.getChipWidth(), chpptr.getChipHeight()});
    int cardX = o.getPlayerCardXstart();
    boolean arrowHasBeenDrawn = false;
    for (Player p : cs.getPlayers()) {
      HandPlayer activeHand = p.getActiveHand();
      for (HandPlayer h : p.getHands()) {
        int handFirstCardX = cardX;
        for (BlackjackCard bc : h.getCards()) {
          cptr.paintCard(g, bc.getCard(), cardX, o.getPlayerY() - o.cardH);
          cardX = cardX + o.cardSpacer;
        }
        cardX = cardX + o.cardW;
        StringWriter sm = new StringWriter();
        sm.append(h.getMessage());
        Color fg00 = Color.BLACK;
        Color fg01 = Color.WHITE;

        int yloc = o.getPlayerChipY();
        if (h.getWager().getInitialBet() > 0) {
          chpptr.paintChips(g, handFirstCardX, yloc, h.getWager().getInitialBet());
          if (h.isDoubleDowned()) {
            chpptr.paintChips(g, handFirstCardX + chpptr.getChipWidth() + chpptr.getChipHeight(),
                yloc, h.getWager().getDoubledBet());
          }
        }
        yloc = o.getPlayerChipY() - chpptr.getChipWidth();
        if (h.getWager().getInitialPayoff() > 0) {
          chpptr.paintChips(g, handFirstCardX, yloc, h.getWager().getInitialPayoff());
        }
        if (h.isDoubleDowned()) {
          if (h.getWager().getDoubledPayoff() > 0) {
            chpptr.paintChips(g, handFirstCardX + chpptr.getChipWidth() + chpptr.getChipHeight() , yloc, h.getWager().getDoubledPayoff());
          }
        }
        if (activeHand != null && h == activeHand) {
          fg00 = Color.BLACK;
          fg01 = Color.YELLOW;
          if (!arrowHasBeenDrawn) {
            g.drawImage(BlackjackImages.getFxArrowImg(), handFirstCardX + o.chipW,
                o.getPlayerChipY());
            arrowHasBeenDrawn = true;
          }
        }

        g.setFont(getFont1());
        g.setFill(fg00);
        g.fillText(sm.toString(), handFirstCardX, o.getPlayerMsgY());
        g.setFill(fg01);
        g.fillText(sm.toString(), handFirstCardX + 1, o.getPlayerMsgY() + 1);
      }
    }
  }

  private void paintStatus(CycleSnapshot cs, GraphicsContext g, Tile t) {
    if (dbg) {
      g.setFill(Color.BLUE);
      g.fillRect(t.getLeft(), t.getTop(), t.getWidth(), t.getHeight());
    }
    g.setFill(Color.WHITE);
    g.setFont(getFont0());
    int y = t.getTop() + verticalSeparator;
    for (String s : cs.getGameStatusVertical()) {
      g.fillText("  " + s.trim(), t.getLeft(), y);
      y = y + verticalSeparator;
    }
    g.fillText(String.format("  Action: %s", cs.getAction().toUpperCase()), t.getLeft(), y);
  }

  private BlackjackTable bjt;

  private BlackjackTable getTable() {
    if (bjt == null) {
      bjt = new BlackjackTable();
    }
    return bjt;
  }

  boolean dbg = false;

  private void paintChips(GraphicsContext g, Tile t, CycleSnapshot cs) {
    if (chpptr == null) {
      return;
    }
    if (dbg) {
      g.setFill(Color.CYAN);
      g.fillRect(t.getLeft(), t.getTop(), t.getWidth(), t.getHeight());
    }
    g.setFill(Color.DARKCYAN);
    g.fillRoundRect(t.getLeft(), t.getTop(), t.getWidth(), t.getHeight(), 20, 20);
    g.setFill(Color.BLACK);
    LayoutChips lc =
        new LayoutChips(t.getLeft(), t.getBottom(), chpptr.getChipWidth(), chpptr.getChipHeight());
    chpptr.paintChips(g, lc.chipXnextBet, lc.chipY, cs.getNextBet());
    chpptr.paintChips(g, lc.chipXstack, lc.chipY, cs.getBankroll().getChips() - cs.getNextBet());
    g.setFill(Color.WHITE);
    g.setFont(getFont0());
    int y = t.getTop() + verticalSeparator;
    for (String s : cs.getStatusChipsVertical()) {
      g.fillText("  " + s.trim(), t.getLeft(), y);
      y = y + verticalSeparator;
    }
    if (getTable().getStatusTile() == null) {
      for (String s : cs.getGameStatusVertical()) {
        g.fillText("  " + s.trim(), t.getLeft(), y);
        y = y + verticalSeparator;
      }
    }
  }

  private Font getFont0() {
    return FontManagerFX.getPlainSerif();
  }

  private Font getFont1() {
    return FontManagerFX.getBoldSerif();
  }

  private Font getFontMono() {
    return FontManagerFX.getMonospace();
  }

}
