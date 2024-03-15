package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class Quit extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public Quit(GamePlayer player) {
        super(player);
    }
}
