package com.panopset.marin.oldswpkg.games.blackjack;

import com.panopset.util.Tile;

public class BlackjackTable {

    private TableLayout tableLayout;
    private int w,h;

    private TableLayout getTableLayout() {
        if (tableLayout == null) {
            tableLayout = new TableLandscape(w,h);
        }
        return tableLayout;
    }

    public Tile getStatusTile() {
        return getTableLayout().getStatusTile();
    }

    public Tile getChipTray() {
        return getTableLayout().getChipTray();
    }

    public Tile getPlayerTile() {
        return getTableLayout().getPlayerTile();
    }

    public Tile getDealerTile() {
        return getTableLayout().getDealerTile();
    }

    public Tile getMsgTile() {
        return getTableLayout().getMsgTile();
    }

    public Tile getTableTile() {
        return getTableLayout().getTableTile();
    }

    private void setLandscape() {
        tableLayout = new TableLandscape(w,h);
    }

    private void setPortrait() {
        tableLayout = new TablePortrait(w,h);
    }

    public void setBounds(int width, int height) {
        if (width < 1 || height < 1) {
            throw new RuntimeException();
        }
        w = width;
        h = height;
        if (width > height) {
            setLandscape();
        } else {
            setPortrait();
        }
    }

    public void setCardHeight(int cardHeight) {
      getTableLayout().setCardHeight(cardHeight);
    }

}
