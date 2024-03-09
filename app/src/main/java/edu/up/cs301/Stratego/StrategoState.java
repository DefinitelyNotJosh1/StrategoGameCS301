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
	
	// the value of the counter
	private int counter;

	/* TODO: add Stratego instance variables */
	private boolean isRedCaptured;
	private boolean isBlueCaptured;
	private boolean isRedVisible;
	private boolean isBlueVisible;
	private int playerTurn;

	/* Indiana though this might be useful for keeping
	* track of each teams' pieces... unsure */
	private List<Piece> redPieces = new ArrayList<Piece>();
	private List<Piece> bluePieces = new ArrayList<Piece>();
	private boolean isRedReady;
	private boolean isBlueReady;

	
	/**
	 * constructor, initializing the counter value from the parameter
	 * 
	 * @param counterVal
	 * 		the value to which the counter's value should be initialized
	 */
	public StrategoState(int counterVal) {
		counter = counterVal;
	}
	
	/**
	 * copy constructor; makes a copy of the original object
	 * 
	 * @param orig
	 * 		the object from which the copy should be made
	 */
	public StrategoState(StrategoState orig) {
		// set the counter to that of the original
		this.counter = orig.counter;

		this.isRedCaptured = orig.isRedCaptured;
		this.isBlueCaptured = orig.isBlueCaptured;
		this.isRedVisible = orig.isRedVisible;
		this.isBlueVisible = orig.isBlueVisible;
		this.playerTurn = orig.playerTurn;
		this.redPieces = orig.redPieces;
		this.bluePieces = orig.bluePieces;
		this.isRedReady = orig.isRedReady;
		this.isBlueReady = orig.isBlueReady;
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
