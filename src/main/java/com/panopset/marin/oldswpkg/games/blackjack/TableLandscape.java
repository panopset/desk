package com.panopset.marin.oldswpkg.games.blackjack;

import com.panopset.util.Tile;


public class TableLandscape extends TableLayout {

  public TableLandscape(final int width, final int height) {
    super(width, height);
  }

  @Override
  protected void assembleEverything(Tile tile) {
    int cardDrop = cardHeight + (cardHeight / 8);
    Tile[] tiles = tile.splitVertical(80, "top3", "msg");
    Tile top3 = tiles[0];
    msgTile = tiles[1]; // GRAY
    Tile[] top3tiles = top3.splitHorizontal(70, "left2", "chips");
    Tile left2 = top3tiles[0];
    chipTray = top3tiles[1];// CYAN square frame
    Tile[] left2tiles =
        left2.splitVerticalExactHeight(cardDrop, "top2", "player");
    Tile top2 = left2tiles[0];
    Tile[] top2tiles = top2.splitHorizontal(50, "status", "dealer");
    statusTile = top2tiles[0];
    dealerTile = top2tiles[1];// RED
    playerTile = left2tiles[1];// YELLOW
  }
}
