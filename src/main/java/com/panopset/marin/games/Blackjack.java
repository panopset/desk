package com.panopset.marin.games;

//import com.panopset.compat.Rezop;
//import com.panopset.deck52.Shoe;
import com.panopset.marin.fx.PanopsetBrandedApp;

public class Blackjack extends PanopsetBrandedApp {

  public static void main(String... args) {
    new Blackjack().go();
  }

  @Override
  protected String getPaneFXMLName() {
    // Shoe.stackTheDeckFromEOLseparatedText(Rezop.loadFromRez(Blackjack.class, "/stack.txt"));
    return "Blackjack.fxml";
  }

  @Override
  public String getApplicationDisplayName() {
    return "Blackjack";
  }

  @Override
  public String getDescription() {
    return "Blackjack trainer.";
  }
}
