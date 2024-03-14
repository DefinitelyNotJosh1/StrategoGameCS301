package edu.up.cs301.Stratego;

import java.util.ArrayList;
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
	private int gamePhase; // an int signifying if we're in the place pieces phase or playing the game
	private boolean isRedCaptured;
	private boolean isBlueCaptured;
	private boolean isRedVisible;
	private boolean isBlueVisible;
	private int playerTurn;

	/* Indiana though this might be useful for keeping
	* track of each teams' pieces... unsure */
	//Josh - changed List<Piece> to ArrayList<Piece> to get rid of syntax errors in getters/setters
	private ArrayList<Piece> redPieces = new ArrayList<Piece>();
	private ArrayList<Piece> bluePieces = new ArrayList<Piece>();
	private boolean isRedReady;
	private boolean isBlueReady;

	// Josh - figured we should store a "Board" object that has all the piece data - Board Class
	// could also include arrayLists of available pieces, as well as which pieces are captured
	private Board board = new Board();

	// Josh - could use a 2d Array to signify whether each piece is visible or not - another option
	// is to make the current 2d int array to show position of game pieces a 3d array, with the third slot
	// containing a 0, 1, or 2, signifying if the pieces are visible to red, blue, or both - should
	// make for less hassle



	
	/**
	 * Josh - crude constructor idea
	 */
	public StrategoState(Board initBoard, boolean initIsRedReady, boolean initIsBlueReady,
						 int initPlayerTurn) {
		initBoard = this.board;
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


		this.isRedCaptured = orig.isRedCaptured;
		this.isBlueCaptured = orig.isBlueCaptured;
		this.isRedVisible = orig.isRedVisible;
		this.isBlueVisible = orig.isBlueVisible;
		this.playerTurn = orig.playerTurn;
		this.redPieces = orig.redPieces;
		this.bluePieces = orig.bluePieces;
		this.isRedReady = orig.isRedReady;
		this.isBlueReady = orig.isBlueReady;
		this.board = new Board(this.board);

	}

	public boolean getIsRedCaptured() {return isRedCaptured;}

	public void setIsRedCaptured(boolean isCapt) {this.isRedCaptured = isCapt;}

	public boolean getIsBlueCaptured() {return isBlueCaptured;}

	public void setIsBlueCaptured(boolean isCapt) {this.isBlueCaptured = isCapt;}

	public boolean getIsRedVisible() {return isRedVisible;}

	public void setIsRedVisible(boolean isVis) {this.isRedVisible = isVis;}

	public boolean getIsBlueVisible() {return isBlueVisible;}

	public void setIsBlueVisible(boolean isVis) {this.isBlueVisible = isVis;}

	public ArrayList<Piece> getRedPieces() {return redPieces;}

	public void setRedPieces(ArrayList<Piece> red) {this.redPieces = red;}

	public ArrayList<Piece> getBluePieces() {return bluePieces;}

	public void setBluePieces(ArrayList<Piece> blue) {this.bluePieces = blue;}

	public boolean getIsRedReady() {return isRedReady;}

	public void setIsRedReady(boolean isReady) {this.isRedReady = isReady;}

	public boolean getIsBlueReady() {return isBlueReady;}

	public void setIsBlueReady(boolean isReady) {this.isBlueReady = isReady;}
}
