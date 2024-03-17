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
	private ArrayList<Integer> redPieces; // pieces red has captured
	private ArrayList<Integer> bluePieces; // pieces blue has captured
	private int playerId; // 0 for player 1, 1 for player 2
	private int gamePhase; // 0 for players placing pieces, 1 for gameplay, 2 for game over
	private int isCaptured; // 0 neither, 1 player 1 flag, 2 player 2 flag
	private int[][] isVisible; // 0 for player 1, 1 for player 2, 2 for both
	private boolean isRedReady;
	private boolean isBlueReady;
	private StrategoMainActivity mainActivity;
	public int[][] board;


	// Josh - could use a 2d Array to signify whether each piece is visible or not - another option
	// is to make the current 2d int array to show position of game pieces a 3d array, with the third slot
	// containing a 0, 1, or 2, signifying if the pieces are visible to red, blue, or both - should
	// make for less hassle

	public StrategoState() {
		gamePhase = 0;
		isCaptured = 0;
		playerId = 1;
		isRedReady = false;
		isBlueReady = false;
		board = new int[8][10]; // [row][col]
		bluePieces = new ArrayList<Integer>();
		redPieces = new ArrayList<Integer>();

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
		this.playerId = orig.playerId;
		this.isRedReady = orig.isRedReady;
		this.isBlueReady = orig.isBlueReady;
		this.board = new int[8][10]; // [row][col]
		this.bluePieces = new ArrayList<Integer>(orig.bluePieces);
		this.redPieces = new ArrayList<Integer>(orig.redPieces);
	}

	//get value of a piece in the array
	public int getPiece(int row, int col) {
		// if we're out of bounds or anything, return -2;
		if (board == null || row < 0 || col < 0) return -2;
		if (row >= board.length || col >= board[row].length) return -2;

		// return the int that is in the proper position
		return board[row][col];
	}

	//move the value of a piece in the array (copy piece over and then delete previous)
	public void setPiece(int newRow, int newCol, int origRow, int origCol, int piece) {
		// if we're out of bounds or anything, return;
		if (board == null || newRow < 0 || newCol < 0) return;
		if (newRow >= board.length || newCol >= board[newRow].length) return;

		// return the character that is in the proper position
		board[newRow][newCol] = piece;
		board[origRow][origCol] = -1; // empty
	}





	public int getGamePhase() {return gamePhase;}

	public void setGamePhase(int phase) {this.gamePhase = phase;}

	public int getIsCaptured() {return isCaptured;}

	public void setIsCaptured(int capt) {this.isCaptured = capt;}
	public int[][] getIsVisible() {return isVisible;}
	public void setIsVisible(int[][] vis) {this.isVisible = vis;}

	public boolean getIsRedReady() {return isRedReady;}

	public void setIsRedReady(boolean isReady) {this.isRedReady = isReady;}

	public boolean getIsBlueReady() {return isBlueReady;}

	public void setIsBlueReady(boolean isReady) {this.isBlueReady = isReady;}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public ArrayList<Integer> getRedPieces() {
		return new ArrayList<Integer>(this.redPieces);
	}

	public void setRedPieces(ArrayList<Integer> redPieces) {
		this.redPieces = new ArrayList<Integer>(redPieces);
	}

	public ArrayList<Integer> getBluePieces() {
		return new ArrayList<Integer>(this.bluePieces);
	}

	public void setBluePieces(ArrayList<Integer> bluePieces) {
		this.bluePieces = new ArrayList<Integer>(bluePieces);
	}

	public void capturePiece(int playerId, int targetedPiece) {
		if (playerId == 0) {
			bluePieces.add(targetedPiece);
		}
		else if (playerId == 1) {
			redPieces.add(targetedPiece);
		}
	}

	@Override
	public String toString() {
		return "StrategoState{" +
				"gamePhase=" + this.gamePhase +
				", isCaptured=" + this.isCaptured +
				", isVisible=" + Arrays.toString(this.isVisible) +
				", playerTurn=" + this.playerId +
				", isRedReady=" + this.isRedReady +
				", isBlueReady=" + this.isBlueReady +
				", board=" + this.board +
				'}';
	}
}
