package com.panopset.marin.oldswpkg.games.blackjack;

public class LayoutChips {

    public final int chipXnextBet;
    public final int chipXstack;
    public final int chipY;

    public LayoutChips(int left, int bottom, int chipWidth, int chipHeight) {
        chipXnextBet = left;
        chipXstack = left + chipWidth;
        chipY = bottom - ((int) (chipHeight * 1.5));
    }

}
