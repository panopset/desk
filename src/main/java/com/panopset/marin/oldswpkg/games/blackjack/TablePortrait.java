package com.panopset.marin.oldswpkg.games.blackjack;

import com.panopset.util.Tile;


public class TablePortrait extends TableLayout {

    public TablePortrait(final int width, final int height) {
        super(width, height);
    }

    @Override
    protected void assembleEverything(Tile tile) {
        Tile[] tiles = tile.splitVertical(50, "top", "bot");
        Tile top2 = tiles[0];
        Tile[] top2tiles = top2.splitHorizontal(50, "dealer", "chips");
        dealerTile = top2tiles[0];//RED
        chipTray = top2tiles[1];//CYAN square frame
        Tile bot2 = tiles[1];
        Tile[] bot2tiles = bot2.splitVertical(60, "player", "msg");
        playerTile = bot2tiles[0];//YELLOW
        msgTile = bot2tiles[1];//GRAY
    }
}
