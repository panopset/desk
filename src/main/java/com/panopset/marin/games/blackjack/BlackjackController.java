package com.panopset.marin.games.blackjack;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.panopset.compat.Logop;
import com.panopset.compat.Stringop;
import com.panopset.games.casino.blackjack.BlackjackConfigDefault;
import com.panopset.games.casino.blackjack.BlackjackGameEngine;
import com.panopset.games.casino.blackjack.CardCounterImpl;
import com.panopset.marin.game.card.images.BlackjackImages;
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class BlackjackController implements Initializable {

  @FXML
  BlackjackConfigController blackjackConfigController;

  @FXML
  BlackjackGameController blackjackGameController;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    BlackjackImages.getChipMap();
    BlackjackImages.getCardMap();
    SvgImageLoaderFactory.install();
    BlackjackRulesController bjrc = blackjackConfigController.blackjackRulesController;
    BlackjackCountingController bjcc = blackjackConfigController.blackjackCountingController;
    BlackjackBasicController blackjackBasicController =
        blackjackConfigController.blackjackBasicController;
    BlackjackGameEngine engine = new BlackjackGameEngine(new BlackjackConfigDefault() {

      @Override
      public boolean isDoubleAfterSplitAllowed() {
        return bjrc.dblaftrsplit.isSelected();
      }

      @Override
      public boolean isResplitAcesAllowed() {
        return bjrc.dblaftrsplit.isSelected();
      }

      @Override
      public List<String> getStrategyData() {
        List<String> dft = super.getStrategyData();
        if (blackjackBasicController == null) {
          return dft;
        }
        TextArea ta = blackjackBasicController.ta_basic;
        if (ta == null) {
          return dft;
        }
        String text = ta.getText();
        if (Stringop.isBlank(text)) {
          return dft;
        }
        List<String> rtn = Stringop.stringToList(text);
        if (!rtn.equals(dft)) {
          Logop.warn("Custom (or out of date) strategy data in use.  Go to config to reset.");
        }
        return rtn;
      }

      @Override
      public List<String> getCountingSystemData() {
        List<String> dft =  super.getCountingSystemData();
        if (bjcc == null) {
          return dft;
        }
        TextArea ta = bjcc.ta_counting_systems;
        if (ta == null) {
          return dft;
        }
        String text = ta.getText();
        if (Stringop.isBlank(text)) {
          return dft;
        }
        List<String> rtn = Stringop.stringToList(text);
        if (!rtn.equals(dft)) {
          Logop.warn("Custom (or out of date) counting system data in use.  Go to config to reset.");
        }
        return rtn;
      }

      @Override
      public int getSeats() {
        return Stringop.parseInt(bjrc.cb_seats.getValue(), 1);
      }

      @Override
      public int getDecks() {
        return Stringop.parseInt(bjrc.cb_decks.getValue(), 1);
      }

      @Override
      public boolean isDealerHitSoft17() {
        return bjrc.dealer_hits_soft_17.isSelected();
      }

      @Override
      public boolean isBlackjack6to5() {
        return bjrc.rule_65.isSelected();
      }

      @Override
      public boolean isEvenMoneyOnBlackjackVace() {
        return bjrc.rule_even.isSelected();
      }

      @Override
      public boolean isLateSurrenderAllowed() {
        return bjrc.rule_ls.isSelected();
      }

      @Override
      public boolean isEuropeanStyle() {
        return bjrc.rule_es.isSelected();
      }

      @Override
      public boolean isFastDeal() {
        return bjrc.rule_fd.isSelected();
      }

      @Override
      public boolean isBasicStrategyVariationsOnly() {
        return bjrc.rule_vs.isSelected();
      }

      @Override
      public boolean isShowCount() {
        return bjrc.rule_sc.isSelected();
      }

      @Override
      public void toggleShowCount() {
        bjrc.rule_sc.setSelected(!bjrc.rule_sc.isSelected());
      }

      @Override
      public int getLargeBetInWholeDollars() {
        return Stringop.parseInt(bjcc.large_bet.getText(),
            BlackjackConfigDefault.DEFAULT_LARGE_BET_IWD);
      }

      @Override
      public int getTargetStakeInWholeDollars() {
        return Stringop.parseInt(bjcc.target_stake.getText(),
            BlackjackConfigDefault.DEFAULT_TARGET_STAKE_IWD);
      }

      @Override
      public int getMinimumBetInWholeDollars() {
        return Stringop.parseInt(bjcc.minimum_bet.getText(),
            BlackjackConfigDefault.DEFAULT_MIN_BET_IWD);
      }

      @Override
      public int getBetIncrementInWholeDollars() {
        return Stringop.parseInt(bjcc.minimum_bet.getText(),
            BlackjackConfigDefault.DEFAULT_BET_INCR_IWD);
      }

      @Override
      public int getReloadAmountInWholeDollars() {
        return Stringop.parseInt(bjrc.reload_amount.getText(),
            BlackjackConfigDefault.DEFAULT_INITIAL_STAKE_IWD);
      }

      @Override
      public boolean isCountVeryNegative() {
        try {
          String bstn = bjcc.count_negative.getText();
          if (Stringop.isPopulated(bstn)) {
            int trigger = Stringop.parseInt(bstn);
            if (trigger == 0) {
              return false;
            }
            return CardCounterImpl.getCount() < trigger;
          }
        } catch (final NumberFormatException ex) {
          Logop.error(ex);
        }
        return false;
      }

      @Override
      public boolean isCountVeryPositive() {
        try {
          String bstp = bjcc.count_positive.getText();
          if (Stringop.isPopulated(bstp)) {
            int trigger = Stringop.parseInt(bstp);
            if (trigger == 0) {
              return false;
            }
            return CardCounterImpl.getCount() > trigger;
          }
        } catch (final NumberFormatException ex) {
          Logop.error(ex);
        }
        return false;
      }

      @Override
      public boolean isBetIdeaDoubleAfterBust() {
        return bjrc.bet_idea_dab.isSelected();
      }

      @Override
      public boolean isBetIdeaLetItRideAfterTwoWins() {
        return bjrc.bet_idea_lir.isSelected();
      }

    }) {
      @Override
      public void exec(final String action) {
        super.exec(action);
        blackjackGameController.update();
      }
    };
    blackjackGameController.setEngine(engine);
  }
}
