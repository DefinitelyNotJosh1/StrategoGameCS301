package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class MovePiece extends GameAction {
    private int row;
    private int col;



    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MovePiece(GamePlayer player, int row, int col) {
        //invoke superclass ctor
        super(player);

        //set the row and column

    }
}
