package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class MovePiece extends GameAction {
    private GamePlayer player;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MovePiece(GamePlayer player) {
        super(player);
        this.player = player;
    }
}
