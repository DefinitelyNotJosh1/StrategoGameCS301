package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import android.util.Log;

/**
 * A class that represents the state of a game. In our counter game, the only
 * relevant piece of information is the value of the game's counter. The
 * CounterState object is therefore very simple.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public class StrategoLocalGame extends LocalGame {

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
			//TODO: WILL CHANGE LATER
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

		if (action instanceof Ready) {
			switch (gameState.getPlayerId()) { // which ID the player is
				case 0:
					gameState.setIsBlueReady(true);
					break;
				case 1:
					gameState.setIsRedReady(true);
					break;
			}
			return true;
		}
		else if (action instanceof Quit) {
				gameState.setGamePhase(2);
				return true;
		}
		else if (action instanceof MovePiece) {
			MovePiece mp = (MovePiece)action;
			int row = mp.getRow();
			int col = mp.getCol();
			int destRow = mp.getDestRow();
			int destCol = mp.getDestCol();
			int piece = gameState.getPiece(row,col);
			int targetedPiece = gameState.getPiece(destRow,destCol);

			if (targetedPiece == -1) { // move piece if spot is empty
				gameState.setPiece(destRow, destCol, row, col, piece);
			}
			else if (piece > targetedPiece) { // if attacker is greater, take previous piece
				gameState
				gameState.setPiece(destRow,destCol,row,col, piece);
			}
			else if (targetedPiece > piece) { // delete piece that attacked if its opponent is greater
				gameState.setPiece(row, col, row, col, -1);

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
		//empty, our game doesn't use counters
		return null;

	}

}// class CounterLocalGame
