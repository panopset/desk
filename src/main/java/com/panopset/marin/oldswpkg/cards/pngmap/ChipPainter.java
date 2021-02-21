package com.panopset.marin.oldswpkg.cards.pngmap;

import com.panopset.compat.AniGrid;
import com.panopset.fxapp.FontManagerFX;

public class ChipPainter {

    private final AniGrid aniGrid;

    public ChipPainter(int mapW, int mapH) {
       aniGrid = new AniGrid(mapW, mapH, 4, 2, FontManagerFX.getImgRatio());
    }

    public int getChipWidth() {
        return aniGrid.getDspWidth();
    }

    public int getChipHeight() {
        return aniGrid.getDspHeight();
    }

    public int[] getPaintDimensionsFX(Chip chip, int x, int y) {
        return aniGrid.getPaintDimensions(chip.getOffsetW(), chip.getOffsetH(), x, y);
    }

}
