package edu.up.cs301.Stratego;

public class Piece {
    private int pieceNumber; // 0-11, 0 is flag, 11 is bomb
    private int isVisible; // could be unnecessary, not using it for now
    private char team; // made it a char so we don't have to worry about deep copying - 'B' blue, 'R' red

    public Piece(Piece orig) {
        this.pieceNumber = orig.pieceNumber;
        this.isVisible = orig.isVisible;
        this.team = orig.team;
    }


    //basic piece constructor
    public Piece(int pieceNumber, char team) {
        this.pieceNumber = pieceNumber;
        this.team = team;
    }

    public int getPieceNumber() {return pieceNumber;}
    public int getIsVisible() {return isVisible;}
    public void setIsVisible(int vis) {isVisible = vis;}
    public char getTeam() {return team;}
}
