package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;

import java.io.Serializable;

/**
 * A class that represents the state of a game. In our counter game, the only
 * relevant piece of information is the value of the game's counter. The
 * CounterState object is therefore very simple.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public class StrategoLocalGame extends LocalGame implements Serializable {

	// When a counter game is played, any number of players. The first player
	// is trying to get the counter value to TARGET_MAGNITUDE; the second player,
	// if present, is trying to get the counter to -TARGET_MAGNITUDE. The
	// remaining players are neither winners nor losers, but can interfere by
	// modifying the counter.

	// the game's state
	private StrategoState gameState;

	/**
	 * can this player move
	 * 
	 * @return
	 * 		true if the action is done while its the player's turn or if its starting state
	 */
	@Override
	protected boolean canMove(int playerIdx) {
		if (gameState.getGamePhase() == 0) {
			return true;
		}
		if (gameState.getPlayerId() == playerIdx) {
			return true;
		}
		return false;
	}

	/**
	 * This ctor should be called when a new counter game is started
	 */
	public StrategoLocalGame(GameState state) {
		// initialize the game state
		if (! (state instanceof StrategoState)) {
			state = new StrategoState();
		}
		this.gameState = (StrategoState)state;
		super.state = state;
	}

	/**
	 * The only type of GameAction that should be sent is CounterMoveAction
	 */
	@Override
	protected boolean makeMove(GameAction action) {
		int playerId = gameState.getPlayerId(); // which ID the player is

		if (action instanceof Ready) {
			if (gameState.getGamePhase() != 0) { // ready action unavailable during game
				return false;
			}
			switch (playerId) {
				case 0:
					gameState.setIsBlueReady(true);
					break;
				case 1:
					gameState.setIsRedReady(true);
					break;
			}
			if (gameState.getIsBlueReady() && gameState.getIsRedReady()) {
				gameState.setGamePhase(1); // if both players are ready, update gamePhase
			}
			return true;
		}
		else if (action instanceof Quit) {
				gameState.setGamePhase(2);
				return true;
		}
		else if (action instanceof MovePiece) {

			//declaring variables here to make logic easier
			MovePiece mp = (MovePiece)action;
			int row = mp.getRow();
			int col = mp.getCol();
			int destRow = mp.getDestRow();
			int destCol = mp.getDestCol();
			Piece piece = gameState.getPiece(row,col); // now returns Piece instead of int
			int pieceVal = piece.getPieceNumber(); // we can get value of that Piece here (also need to use Piece object later)
			Piece targetedPiece = gameState.getPiece(destRow,destCol);
			int targetedPieceVal = targetedPiece.getPieceNumber();
			char pieceTeam = gameState.getTeam(row,col);
			char destTeam = gameState.getTeam(destRow,destCol);

			if (targetedPiece.isLake()) { // return false if player is trying to move into a lake
				return false;
			}

			//return false if player is trying to move an opponent's piece
			if (pieceTeam == 'B' && playerId == 1) {
				return false;
			}
			if (pieceTeam == 'R' && playerId == 0) {
				return false;
			}

			//return false if out of bounds
			if (destRow >= gameState.board.length || destCol >= gameState.board[row].length) return false;
			if (destRow < 0 || destCol < 0) return false;

			//return false if targeted piece is on the same team
			if (destTeam == pieceTeam) {
				return false;
			}

			//return false if flag or bomb
			if (pieceVal == 0 || pieceVal == 11) {
				return false;
			}

			//return false if the destination spot is illegal for most pieces
			if (pieceVal != 2) {
				if (!((Math.abs(destRow - row) == 1 && destCol == col) ||
						(Math.abs(destCol - col) == 1 && destRow == row))) {
					return false;
				}
			}

			//return false if destination area is illegal for a scout
			if (pieceVal == 2) {
				// Check if the move is horizontal
				if (row == destRow) {
					// Check if moving right
					if (col < destCol) {
						for (int tempCol = col + 1; tempCol < destCol; tempCol++) {
							if (gameState.board[row][col] != null) {
								return false; // Obstacle found in the path
							}
						}
					}
					// Check if moving left
					else {
						for (int tempCol = col - 1; tempCol > destCol; tempCol--) {
							if (gameState.board[row][col] != null) {
								return false; // Obstacle found in the path
							}
						}
					}
				}


				// Check if the move is vertical
				else if (col == destCol) {
					// Check if moving down
					if (row < destRow) {
						for (int tempRow = row + 1; tempRow < destRow; tempRow++) {
							if (gameState.board[row][col] != null) {
								return false; // Obstacle found in the path
							}
						}
					}
					// Check if moving up
					else {
						for (int tempRow = row - 1; tempRow > destRow; tempRow--) {
							if (gameState.board[row][col] != null) {
								return false; // Obstacle found in the path
							}
						}
					}
				}
				//no obstacles found, interaction can proceed
			}


			if (gameState.board[row][col] == null) { // move piece if spot is empty
				gameState.setPiece(destRow, destCol, row, col);
				return true;
			}
			else if (pieceVal > targetedPieceVal) { // if attacker is greater, take previous piece
				gameState.capturePiece(playerId,targetedPiece);
				gameState.setPiece(destRow,destCol,row,col);
				return true;
			}
			else if (targetedPieceVal > pieceVal) { // delete piece that attacked if its opponent is greater
				if (targetedPieceVal == 10 && pieceVal == 1) { // if a spy is attacking a 10
					gameState.capturePiece(playerId,targetedPiece);
					gameState.setPiece(destRow,destCol,row,col);
					return true;
				}
				else if (targetedPieceVal == 11 && pieceVal == 3) { // if a miner is attacking a bomb
					gameState.capturePiece(playerId,targetedPiece);
					gameState.setPiece(destRow,destCol,row,col);
					return true;
				}
				else { // attacking piece loses
					gameState.capturePiece(Math.abs(playerId - 1),piece);
					gameState.setPiece(row, col, row, col);
					return true;
				}
			}
		}
		return false;
	}//makeMove
	
	/**
	 * send the updated state to a given player
	 */
	@Override
	protected void sendUpdatedStateTo(GamePlayer p) {
		// this is a perfect-information game, so we'll make a
		// complete copy of the state to send to the player
		p.sendInfo(new StrategoState(this.gameState));
		
	}//sendUpdatedSate
	
	/**
	 * Check if the game is over. It is over, return a string that tells
	 * who the winner(s), if any, are. If the game is not over, return null;
	 * 
	 * @return
	 * 		a message that tells who has won the game, or null if the
	 * 		game is not over
	 */
	@Override
	protected String checkIfGameOver() {
		for (Piece p : gameState.getRedPieces()) {
			if (p.getPieceNumber() == 0) {
				return "Red has won the game!";
			}
		}
		for (Piece p : gameState.getBluePieces()) {
			if (p.getPieceNumber() == 0) {
				return "Blue has won the game!";
			}
		}
		return null;

	}

}// class CounterLocalGame
