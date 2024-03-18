package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class MovePiece extends GameAction {
    private int row;
    private int destRow;
    private int col;
    private int destCol;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MovePiece(GamePlayer player, int row, int col, int destRow, int destCol) {
        //invoke superclass ctor
        super(player);

        //set the rows and columns
        this.row = row;
        this.col = col;
        this.destRow = destRow;
        this.destCol = destCol;
    }

    //getters for everything
    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }
    public int getDestRow() { return destRow; }
    public int getDestCol() { return destCol; }
}
