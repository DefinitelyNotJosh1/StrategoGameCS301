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
	private ArrayList<Piece> redPieces; // pieces red has captured
	private ArrayList<Piece> bluePieces; // pieces blue has captured
	private int playerId; // 0 for player 1, 1 for player 2
	private int gamePhase; // 0 for players placing pieces, 1 for gameplay, 2 for game over
	private int isCaptured; // 0 neither, 1 player 1 flag, 2 player 2 flag
	private int[][] isVisible; // 0 for player 1, 1 for player 2, 2 for both
	private boolean isRedReady;
	private boolean isBlueReady;
	private StrategoMainActivity mainActivity;
	public Piece[][] board;


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
		board = new Piece[8][10]; // [row][col]
		bluePieces = new ArrayList<Piece>();
		redPieces = new ArrayList<Piece>();

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
		this.board = new Piece[8][10]; // [row][col]
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (orig.board[row][col] == null) {
					this.board[row][col] = null;
				}
				else {
					this.board[row][col] = new Piece(orig.board[row][col]); // replace all pieces with new pieces for network play
				}
			}
		}
		this.bluePieces = new ArrayList<Piece>(orig.bluePieces);
		this.redPieces = new ArrayList<Piece>(orig.redPieces);
	}

	//get value of a piece in the array
	public Piece getPiece(int row, int col) {
		// return the int that is in the proper position
		return board[row][col];
	}

	//get team of a piece in the array
	public char getTeam(int row, int col) {
		return board[row][col].getTeam();
	}


	//move the value of a piece in the array (copy piece over and then delete previous)
	public void setPiece(int newRow, int newCol, int origRow, int origCol) {
		// if we're out of bounds or anything, return;
		if (board == null || newRow < 0 || newCol < 0) return;
		if (newRow >= board.length || newCol >= board[newRow].length) return;

		board[newRow][newCol] = new Piece(board[origRow][origCol]);
		board[origRow][origCol] = null; // set old spot to empty
	}

	// adds pieces to the captured pieces ArrayList
	public void capturePiece(int playerId, Piece targetedPiece) {
		if (playerId == 0) {
			bluePieces.add(targetedPiece);
		}
		else if (playerId == 1) {
			redPieces.add(targetedPiece);
		}
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

	public ArrayList<Piece> getRedPieces() {
		return new ArrayList<Piece>(this.redPieces);
	}

	public void setRedPieces(ArrayList<Piece> redPieces) {
		this.redPieces = new ArrayList<Piece>(redPieces);
	}

	public ArrayList<Piece> getBluePieces() {
		return new ArrayList<Piece>(this.bluePieces);
	}

	public void setBluePieces(ArrayList<Piece> bluePieces) {
		this.bluePieces = new ArrayList<Piece>(bluePieces);
	}



	@Override
	public String toString() {
		String bluePieces = "";
		String redPieces = "";
		for (Piece p : this.bluePieces) {
			bluePieces = bluePieces + p.toString() + ", ";
		}
		for (Piece p : this.redPieces) {
			redPieces = redPieces + p.toString() + ", ";
		}

		int aliveBluePieces = 0;
		int aliveRedPieces = 0;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == null) {
					continue;
				} else if (board[row][col].getTeam() == 'B') {
					aliveBluePieces++;
				} else if (board[row][col].getTeam() == 'R') {
					aliveRedPieces++;
				}
			}
		}


		return "StrategoState{" +
				"gamePhase=" + this.gamePhase +
				", CapturedBluePieces=" + bluePieces +
				", CapturedRedPieces=" + redPieces +
				", aliveBluePieces= " + aliveBluePieces +
				", aliveRedPieces= " + aliveRedPieces +
				", isVisible=" + Arrays.toString(this.isVisible) +
				", playerTurn=" + this.playerId +
				", isRedReady=" + this.isRedReady +
				", isBlueReady=" + this.isBlueReady +
				'}';
	}
}
