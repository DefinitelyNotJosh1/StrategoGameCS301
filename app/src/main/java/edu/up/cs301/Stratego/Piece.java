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
    private boolean isLake;

    public Piece(Piece orig) {
        this.pieceNumber = orig.pieceNumber;
        this.isVisible = orig.isVisible;
        this.team = orig.team;
        this.isLake = orig.isLake;
    }


    //basic piece constructor
    public Piece(int pieceNumber, char team) {
        this.pieceNumber = pieceNumber;
        this.team = team;
        this.isLake = false;
    }

    //piece constructor for Lake
    public Piece(boolean lake) {
        this.isLake = lake;
        this.team = 'L';
    }

    public int getPieceNumber() {return pieceNumber;}
    public int getIsVisible() {return isVisible;}
    public void setIsVisible(int vis) {isVisible = vis;}
    public char getTeam() {return team;}
    public boolean isLake() {
        return isLake;
    }
    public void setLake(boolean lake) {
        isLake = lake;
    }

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
