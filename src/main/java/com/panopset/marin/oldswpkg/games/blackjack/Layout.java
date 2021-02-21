package com.panopset.marin.oldswpkg.games.blackjack;

import com.panopset.games.casino.blackjack.CycleSnapshot;
import com.panopset.util.Tile;

public abstract class Layout {

    public final Tile t;
    public final int h;
    public final int w;
    public final int centerW;
    public final int centerH;
    public final int cardW;
    public final int cardH;
    public final int cardSpacer;
    public final CycleSnapshot cs;

    public Layout(Tile tile, int[] cardDim, CycleSnapshot cs) {
        t = tile;
        w = t.getWidth();
        h = t.getHeight();
        centerW = w / 2;
        centerH = h / 2;
        cardW = cardDim[0];
        cardH = cardDim[1];
        cardSpacer = cardW / 5;
        this.cs = cs;
    }
}
