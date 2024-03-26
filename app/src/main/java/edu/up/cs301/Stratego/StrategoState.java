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

/**
 * We updated this to get the state of the Stratego game. The state consist
 * where and how many pieces are on the board.
 *
 * @author James, Indiana, Joshua, Ethan
 * @version March 18, 2024
 */
public class StrategoState extends GameState {
	
	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;
	private ArrayList<Piece> redPieces; // pieces red has captured
	private ArrayList<Piece> bluePieces; // pieces blue has captured
	private int playerId; // 0 for player 1, 1 for player 2
	private int gamePhase; // 0 for players placing pieces, 1 for gameplay, 2 for game over
	private int isCaptured; // 0 neither, 1 player 1 flag, 2 player 2 flag
	private boolean isRedReady;
	private boolean isBlueReady;
	public Piece[][] board;

	public StrategoState() {
		gamePhase = 0;
		isCaptured = 0;
		playerId = 1;
		isRedReady = false;
		isBlueReady = false;
		board = new Piece[8][10]; // [row][col]
		//order pieces are put on board - kept it simple for my sake when I was counting
		int[] blues = {10, 9, 8, 8, 7, 7, 6, 6, 6, 5, 5, 5, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 1, 11, 11, 11, 11, 0};
		int[] reds = {10, 9, 8, 8, 7, 7, 6, 6, 6, 5, 5, 5, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 1, 11, 11, 11, 11, 0};

		int blueIndex = 0;
		int redIndex = 0;
		int rows = 8;
		int cols = 10;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (row < 3) { // set up blue pieces
					int value = blues[blueIndex];
					blueIndex++;
					board[row][col] = new Piece(value, 'B');
				} else if (row > 4) { // set up red pieces
					int value = reds[redIndex];
					redIndex++;
					board[row][col] = new Piece(value, 'R');
				}
					// Setting up lakes
					if ((row == 3 || row == 4) && (col == 2 || col == 3 || col == 6 || col == 7)) {
						board[row][col] = new Piece(true);
					}
				}
			}

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
				", playerTurn=" + this.playerId +
				", isRedReady=" + this.isRedReady +
				", isBlueReady=" + this.isBlueReady +
				'}';
	}
}
