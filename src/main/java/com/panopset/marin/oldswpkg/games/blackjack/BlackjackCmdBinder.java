package com.panopset.marin.oldswpkg.games.blackjack;

import com.panopset.marin.compat.util.CommandBinder;
import static com.panopset.games.casino.blackjack.CommandDefinitions.*;

public class BlackjackCmdBinder extends CommandBinder {
  public BlackjackCmdBinder() {
    super();
    registerCommand(new CommandBinder.Command("Deal", CMD_DEAL, 0));
    registerCommand(new CommandBinder.Command("Hit", CMD_HIT, 0));
    registerCommand(new CommandBinder.Command("Surrender", CMD_SURRENDER, 0));
    registerCommand(new CommandBinder.Command("Stand", CMD_STAND, 0));
    registerCommand(new CommandBinder.Command("Split", CMD_SPLIT, 0));
    registerCommand(new CommandBinder.Command("Double", CMD_DOUBLE, 0));
    registerCommand(new CommandBinder.Command("Increase", CMD_INCREASE, 0));
    registerCommand(new CommandBinder.Command("Decrease", CMD_DECREASE, 0));
    registerCommand(new CommandBinder.Command("Reset", CMD_RESET, 0));
    registerCommand(new CommandBinder.Command("Shuffle", CMD_SHUFFLE, 0));
    registerCommand(new CommandBinder.Command("Auto", CMD_AUTO, 0));
    registerCommand(new CommandBinder.Command("Count", CMD_COUNT, 0));
  }
}
