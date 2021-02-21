package com.panopset.marin.game.card.images;

import java.net.URL;
import com.panopset.compat.Logop;
import javafx.scene.image.Image;

public enum BlackjackImages {

  INSTANCE;

  public static Image getFxArrowImg() {
    return INSTANCE.getArrowImg();
  }

  private Image arrowImg;

  private Image getArrowImg() {
    if (arrowImg == null) {
      arrowImg = new Image(getClass().getResource(ARROW_IMAGE).toExternalForm());
    }
    return arrowImg;
  }

  private Image cardMap;

  public static Image getCardMap() {
    return INSTANCE.getFxCardMap();
  }

  private Image getFxCardMap() {
    if (cardMap == null) {
      cardMap = new Image(getClass().getResource(DFT_CARD_IMAGE).toExternalForm());
    }
    return cardMap;
  }

  private Image chipMap;

  public static Image getChipMap() {
    return INSTANCE.getFxChipMap();
  }

  private Image getFxChipMap() {
    if (chipMap == null) {
      Logop.dspmsg(getClass().getCanonicalName());
      URL url = getClass().getResource(DFT_CHIP_IMAGE);
      String imgextf = url.toExternalForm();
      chipMap = new Image(imgextf);
    }
    return chipMap;
  }

  /**
   * Default card image is the one from jfitz.com.
   */
  private final String DFT_CARD_IMAGE = "/com/panopset/marin/game/card/images/jfitzcards.png";


  /**
   * Panopset owned image, created by Karl Dinwiddie 2012-05-19.
   */
  private final String DFT_CHIP_IMAGE = "/com/panopset/marin/game/card/images/chips.png";

  /**
   * Panopset owned image.
   */
  private final String ARROW_IMAGE = "/com/panopset/marin/game/card/images/arrow.png";

}
