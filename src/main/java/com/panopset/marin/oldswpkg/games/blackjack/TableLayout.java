package com.panopset.marin.oldswpkg.games.blackjack;

import com.panopset.util.Tile;

public abstract class TableLayout {

    private final int w;
    private final int h;
    protected int cardHeight;

    public TableLayout(final int width, final int height) {
        w = width;
        h = height;
    }

    private Tile tableTile;

    protected abstract void assembleEverything(final Tile tile);

    private Tile ae(final int w, final int h) {
        Tile rtn = new Tile("rtn");
        rtn.setLocation(0, 0);
        rtn.setDimensions(w, h - 50); // TODO lame workaround here.
        assembleEverything(rtn);
        return rtn;
    }

    public Tile getTableTile() {
        if (tableTile == null) {
            tableTile = ae(w, h);
        }
        return tableTile;
    }

    Tile chipTray;

    public Tile getChipTray() {
        if (chipTray == null) {
            tableTile = ae(w, h);
        }
        return chipTray;
    }

    Tile playerTile;

    public Tile getPlayerTile() {
        if (playerTile == null) {
            tableTile = ae(w, h);
        }
        return playerTile;
    }

    Tile dealerTile;

    public Tile getDealerTile() {
        if (dealerTile == null) {
            tableTile = ae(w, h);
        }
        return dealerTile;
    }

    Tile msgTile;

    public Tile getMsgTile() {
        if (msgTile == null) {
            tableTile = ae(w, h);
        }
        return msgTile;
    }

    Tile statusTile;

    public Tile getStatusTile() {
        if (statusTile == null) {
            tableTile = ae(w, h);
        }
        return statusTile;
    }

    public void setCardHeight(int cardHeight) {
      this.cardHeight = cardHeight;
    }
}
