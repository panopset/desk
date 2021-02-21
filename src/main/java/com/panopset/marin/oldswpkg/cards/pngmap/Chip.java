package com.panopset.marin.oldswpkg.cards.pngmap;

public enum Chip {
    HUNNERK(3,0,1000000),
    TENK(3,1,1000000),
    THOU(2,1,100000),
    HUNDERT(2,0,10000),
    TWENTYFIVE(1,0,2500),
    FIVE(1,1,500),
    TWOFITY(0,1,250),
    ONE(0,0,100),
    ;
    private final int wo;
    private final int ho;
    private final int val;
    public int getValue() {
        return val;
    }
    public int getOffsetW() {
        return wo;
    }
    public int getOffsetH() {
        return ho;
    }
    Chip(final int ow, final int oh, final int value) {
        wo = ow;
        ho = oh;
        val = value;
    }
}
