package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * movePiece action - takes in context that localGame can use to decide if the action is legal
 *
 * @author Josh Krasnogorov
 * @author Indiana Atwood
 * @author James Nguyen
 * @author Ethan Brown
 * @version March 2024
 */

public class MovePiece extends GameAction {
    //instance variables
    private int row;
    private int destRow;
    private int col;
    private int destCol;

    //MovePiece constructor takes in the player, the row and column of the piece, and the target
    //of the piece
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
