package com.panopset.marin.oldswpkg.cards.pngmap;

import com.panopset.deck52.Card;

public class CardLocation {

    private final Card c;

    public Card getCard() {
        return c;
    }

    public int x;
    public int y;
    public int w;
    public int h;

    public CardLocation(
            final Card card,
            final int xloc,
            final int yloc,
            final int width,
            final int height) {
        c = card;
        x = xloc;
        y = yloc;
        w = width;
        h = height;
    }
}
