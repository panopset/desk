package com.panopset.marin.oldswpkg.games.blackjack;

import com.panopset.games.casino.blackjack.CycleSnapshot;
import com.panopset.games.casino.blackjack.HandPlayer;
import com.panopset.games.casino.blackjack.Player;
import com.panopset.util.Tile;

public class LayoutPlayer extends Layout {
    public final int chipW;
    public final int chipH;

    public LayoutPlayer(Tile tile, int[] cardDim, CycleSnapshot cs, int[] chipDim) {
        super(tile, cardDim, cs);
        chipW = chipDim[0];
        chipH = chipDim[1];
    }

    private Integer playerChipY;

    public Integer getPlayerChipY() {
        if (playerChipY == null) {
            playerChipY = t.getTop() + cardH;
        }
        return playerChipY;
    }

    private Integer playerMsgY;

    public Integer getPlayerMsgY() {
        if (playerMsgY == null) {
            playerMsgY = getPlayerChipY() + (chipH * 2);
        }
        return playerMsgY;
    }

    private Integer playerCardXstart;

    public Integer getPlayerCardXstart() {
        if (playerCardXstart == null) {
            int cardsWidth = 0;
            for (Player p : cs.getPlayers()) {
                for (HandPlayer h : p.getHands()) {
                    if (!h.cards.isEmpty()) {
                        cardsWidth = cardsWidth + cardW;
                        cardsWidth = cardsWidth + (cardSpacer * h.cards.size());
                    }
                }
            }
            playerCardXstart = centerW - (cardsWidth / 2);
        }
        return playerCardXstart;
    }

    private Integer playerY;

    public Integer getPlayerY() {
        if (playerY == null) {
            playerY = t.getTop() + cardH;
        }
        return playerY;
    }
}
