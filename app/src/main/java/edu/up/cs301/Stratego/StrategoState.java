package edu.up.cs301.Stratego;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the counter.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class StrategoState extends GameState {
	
	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;


	/* TODO: add Stratego instance variables */
	private int gamePhase; // 0 for players placing pieces, 1 for gameplay
	private int isCaptured; // 0 neither, 1 player 1 flag, 2 player 2 flag
	private int[][] isVisible; // 0 for player 1, 1 for player 2, 2 for both
	private int playerTurn; // 0 for player 1, 1 for player 2

	/* Indiana though this might be useful for keeping
	* track of each teams' pieces... unsure */
	//Josh - changed List<Piece> to ArrayList<Piece> to get rid of syntax errors in getters/setters
	private boolean isRedReady;
	private boolean isBlueReady;
	private Board board; // has piece objects that themselves say which player they're visible to
	private CapturedPieces capturedPieces;

	// Josh - could use a 2d Array to signify whether each piece is visible or not - another option
	// is to make the current 2d int array to show position of game pieces a 3d array, with the third slot
	// containing a 0, 1, or 2, signifying if the pieces are visible to red, blue, or both - should
	// make for less hassle

	public StrategoState() {
		gamePhase = 0;
		isCaptured = 0;
		playerTurn = 1;
		isRedReady = false;
		isBlueReady = false;
		board = new Board();
		capturedPieces = new CapturedPieces();

	}



	/**
	 * Josh - crude constructor idea
	 */
	public StrategoState(Board initBoard, boolean initIsRedReady, boolean initIsBlueReady,
						 int initPlayerTurn) {

		this.board = this.initBoard;
		initIsRedReady = this.isRedReady;
		initIsBlueReady = this.isBlueReady;
		initPlayerTurn = this.playerTurn;
	}
	
	/**
	 * copy constructor; makes a copy of the original object
	 * 
	 * @param orig
	 * 		the object from which the copy should be made
	 */
	public StrategoState(StrategoState orig) {
		this.gamePhase = orig.gamePhase;
		this.isCaptured = orig.isCaptured;
		this.isVisible = orig.isVisible;
		this.playerTurn = orig.playerTurn;
		this.redPieces = orig.redPieces;
		this.bluePieces = orig.bluePieces;
		this.isRedReady = orig.isRedReady;
		this.isBlueReady = orig.isBlueReady;
		this.board = new Board(orig.board);
	}

	public int getGamePhase() {return gamePhase;}

	public int setGamePhase(int phase) {this.gamePhase = phase;}

	public int getIsCaptured() {return isCaptured;}

	public int setIsCaptured(int capt) {this.isCaptured = capt;}
	public int[][] getIsVisible() {return isVisible;}
	public void setIsVisible(int[][] vis) {this.isVisible = vis;}

	public boolean getIsRedReady() {return isRedReady;}

	public void setIsRedReady(boolean isReady) {this.isRedReady = isReady;}

	public boolean getIsBlueReady() {return isBlueReady;}

	public void setIsBlueReady(boolean isReady) {this.isBlueReady = isReady;}
	public int getPlayerTurn() {return playerTurn;}
	public void setPlayerTurn(int turn) {this.playerTurn = turn;}
	public Board getBoard() {return board;}
	public void setBoard(Board board) {this.board = new Board(board);}

	@Override
	public String toString() {
		return "StrategoState{" +
				"gamePhase=" + this.gamePhase +
				", isCaptured=" + this.isCaptured +
				", isVisible=" + Arrays.toString(this.isVisible) +
				", playerTurn=" + this.playerTurn +
				", isRedReady=" + this.isRedReady +
				", isBlueReady=" + this.isBlueReady +
				", board=" + this.board +
				'}';
	}
}
