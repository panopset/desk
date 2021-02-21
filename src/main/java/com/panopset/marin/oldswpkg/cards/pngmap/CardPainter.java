package com.panopset.marin.oldswpkg.cards.pngmap;

import java.util.HashMap;
import java.util.Map;
import com.panopset.compat.AniGrid;
import com.panopset.deck52.Card;
import com.panopset.deck52.Suit;
import com.panopset.fxapp.FontManagerFX;

/**
 * Paint cards based on a card map image. This class only deals with calculating
 * source and destination rectangles, so that it can be used independently of
 * the platform specific painting methods.
 *
 * @author Karl Dinwiddie
 *
 */
public class CardPainter {

    private final AniGrid aniGrid;

    /**
     * Default order is club, spade, heart, diamond.
     */
    public static final Suit[] DFT_ORDER = new Suit[] {Suit.CLUB, Suit.SPADE,
            Suit.HEART, Suit.DIAMOND };


    public CardPainter(final int mapW, final int mapH) {
        this(mapW, mapH, DFT_ORDER);
    }

    public CardPainter(final int mapW, final int mapH, Suit[] suits) {
        aniGrid = new AniGrid(mapW, mapH, 13, 4, FontManagerFX.getSvgRatio());
        order = suits;
    }

    private final Suit[] order;

    public final Suit[] getOrder() {
        return order;
    }

    public int getCardHeight() {
        return aniGrid.getDspHeight();
    }

    public int getCardWidth() {
        return aniGrid.getDspWidth();
    }

    private final Map<Suit, Integer> ofs = new HashMap<Suit, Integer>();

    public int getOffset(final Suit suit) {
        Integer rtn = ofs.get(suit);
        if (rtn == null) {
            Integer offset = 0;
            for (Suit s : order) {
                if (s.equals(suit)) {
                    break;
                }
                offset++;
            }
            ofs.put(suit, offset);
            rtn = offset;
        }
        return rtn;
    }

    public int[] getPaintDimensionsFX(Card card, int x, int y) {
        return aniGrid.getPaintDimensions(card.getFace().getOffset(), getOffset(card.getSuit()), x, y);
    }
}
