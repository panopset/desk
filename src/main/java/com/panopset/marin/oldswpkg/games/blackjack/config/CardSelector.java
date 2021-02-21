package com.panopset.marin.oldswpkg.games.blackjack.config;

import java.util.ArrayList;
import java.util.List;
import com.panopset.deck52.Card;
import com.panopset.marin.oldswpkg.games.blackjack.FxCardPainter;

/**
 * Incompletely converted card selector class.
 * TODO: complete conversion to FX.
 * @author Karl Dinwiddie
 *
 */
public class CardSelector {


//    private JPanel p;
    private final List<CardSelector.SelectionListener> ls
    = new ArrayList<CardSelector.SelectionListener>();

    private FxCardPainter cardPainter;

    private FxCardPainter getCardPainter() {
        if (cardPainter == null) {
            cardPainter = new FxCardPainter();
        }
        return cardPainter;
    }

    private Integer fullHeight;

    private Integer fullWidth;

    private Integer getFullWidth() {
        if (fullWidth == null) {
            fullWidth = getCardPainter().getCardWidth() * 13;
        }
        return fullWidth;
    }

    private Integer getFullHeight() {
        if (fullHeight == null) {
            fullHeight = getCardPainter().getCardHeight() * 4;
        }
        return fullHeight;
    }
//    private Image cardsImage;
//
//    private Image getCardsImage() {
//        if (cardsImage == null) {
//            int w = getFullWidth();
//            int h = getFullHeight();
//            cardsImage = BlackjackImages.getCardMap();
//            GraphicsContext g = cardsImage.getg
//            int wd = 0;
//            int hd = 0;
//            for (Suit s : getCardPainter().getOrder()) {
//                for (Face f : Face.values()) {
//                    Card c =  new Card(CardDefinition.find(f, s));
//                    c.show();
//                    getCardPainter().paintCard(g, c, wd, hd);
//                    wd = wd + getCardPainter().getCardWidth();
//                }
//                wd = 0;
//                hd = hd + getCardPainter().getCardHeight();
//            }
//        }
//        return cardsImage;
//    }

//    public JPanel getPanel() {
//        if (p == null) {
//            p = new JPanel(new FlowLayout());
//            final JLabel l = new JLabel(new ImageIcon(getCardsImage()));
//            p.add(l);
//            l.addMouseListener(new MouseAdapter() {
//                public void mousePressed(MouseEvent e) {
//                    int x = e.getX();
//                    int y = e.getY();
//                    int ch = 0;
//                    int cw = 0;
//                    Suit suit = getCardPainter().getOrder()[0];
//                    for (Suit s : getCardPainter().getOrder()) {
//                        if (y > ch) {
//                            suit = s;
//                        } else {
//                            break;
//                        }
//                        ch += getCardPainter().getCardHeight();
//                    }
//                    Face face = Face.ACE;
//                    for (Face f : Face.values()) {
//                        if (x > cw) {
//                            face = f;
//                        } else {
//                            break;
//                        }
//                        cw += getCardPainter().getCardWidth();
//                    }
//                    Card card = new Card(CardDefinition.find(face, suit));
//                    for (SelectionListener sl : ls) {
//                        sl.selected(card);
//                    }
//                }
//            });
//        }
//        return p;
//    }

    public void addSelectionListener(final CardSelector.SelectionListener sl) {
        ls.add(sl);
    }
    public interface SelectionListener {
        void selected(final Card card);
    }
}
