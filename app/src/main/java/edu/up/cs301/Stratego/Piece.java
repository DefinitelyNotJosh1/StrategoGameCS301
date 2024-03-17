package edu.up.cs301.Stratego;

public class Piece {
    private int pieceNumber;
    private int isVisible;
    private boolean isCaptured;
    // each piece will have else/if tree or switch/case tree to signify interactions

    public Piece(Piece orig) {
        this.pieceNumber = orig.pieceNumber;
        this.isVisible = orig.isVisible;
        this.isCaptured = orig.isCaptured;
    }

    public Piece() {

    }

    public int getPieceNumber() {return pieceNumber;}
    public void setPieceNumber(int num) {pieceNumber = num;}
    public int getIsVisible() {return isVisible;}
    public void setIsVisible(int vis) {isVisible = vis;}
    public boolean getIsCaptured() {return isCaptured;}
    public void setIsCaptured(boolean captured) {isCaptured = captured;}
}
