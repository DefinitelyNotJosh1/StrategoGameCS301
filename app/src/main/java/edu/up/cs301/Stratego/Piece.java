package edu.up.cs301.Stratego;

/**
 * Each piece on the baord is given a piece number,
 * a variable that determines if it visible (depending
 * on the current player) and a character that shows
 * which team it is on.
 *
 * @author Josh Krasnogorov
 * @author Indiana Atwood
 * @author James Nguyen
 * @author Ethan Brown
 * @version March 2024
 */
public class Piece {
    private int pieceNumber; // 0-11, 0 is flag, 11 is bomb
    private int isVisible; // could be unnecessary, not using it for now
    private char team; // made it a char so we don't have to worry about deep
                       // copying - 'B' blue, 'R' red

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

    @Override
    public String toString() {
        /* // might not use
        String teamString = "";

        switch (team) {
            case 'B':
                teamString = "Blue";
                break;
            case 'R':
                teamString = "Red";
                break;
        }
        */
        return pieceNumber + "";

    }
}
