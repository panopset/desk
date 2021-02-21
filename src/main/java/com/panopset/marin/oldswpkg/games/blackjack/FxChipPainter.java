package com.panopset.marin.oldswpkg.games.blackjack;

import com.panopset.fxapp.FontManagerFX;
import com.panopset.marin.game.card.images.BlackjackImages;
import com.panopset.marin.oldswpkg.cards.pngmap.Chip;
import com.panopset.marin.oldswpkg.cards.pngmap.ChipPainter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class FxChipPainter {

  public FxChipPainter() {
    Double cw = getChipImg().getWidth();
    Double ch = getChipImg().getHeight();
    cp = new ChipPainter(cw.intValue(), ch.intValue());
  }

  public int getChipHeight() {
    return cp.getChipHeight();
  }

  public int getChipWidth() {
    return cp.getChipWidth();
  }

  private Image chipImg;

  private Image getChipImg() {
    if (chipImg == null)  {
        chipImg = BlackjackImages.getChipMap();
    }
    return chipImg;
  }


  public int paintChips(GraphicsContext g, int x, int y, long amt) {
    List<Chip> stack = new ArrayList<Chip>();
    long newAmt = amt;
    WHILE: while (newAmt > 99) {
      FOR: for (Chip chip : Chip.values()) {
        if (chip.getValue() == 250 && newAmt > 250) {
          continue FOR;
        }
        if (newAmt >= chip.getValue()) {
          stack.add(chip);
          newAmt = newAmt - chip.getValue();
          continue WHILE;
        }
      }
    }
    int xloc = x + (2 * stack.size());
    int yloc = y;
    for (Chip chip : stack) {
      paintChip(chip, g, xloc, yloc);
      yloc = (int) (yloc - (FontManagerFX.getImgRatio() * 2));
      xloc = (int) (xloc - (FontManagerFX.getImgRatio() * 1));
    }
    return yloc;
  }

  private void paintChip(final Chip chip, final GraphicsContext g, final int x,
    final int y) {
    int[] b = cp.getPaintDimensionsFX(chip, x, y);
    g.drawImage(chipImg, b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7]);
  }

  private final ChipPainter cp;

}
