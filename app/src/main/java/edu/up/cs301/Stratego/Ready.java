package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class Ready extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public Ready(GamePlayer player) {
        super(player);
    }
}
