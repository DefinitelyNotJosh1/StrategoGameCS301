package edu.up.cs301.Stratego;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.core.content.res.ResourcesCompat;
import java.util.Random;

public class Board extends SurfaceView {
    private int testInt;


    // Basic copy constructor for strategoState compiling
    public Board(Board orig) {
        super(orig.getContext());
        this.testInt = orig.testInt;
    }

    //instance Variables
    private final int WIDTH_CELLS = 10;
    private final int HEIGHT_CELLS = 8;
    private float dpHeight;
    private float dpWidth;
    private int[][] boardInfo;

    //Bitmaps, created once to conserve resources
    Bitmap redFlag;
    Bitmap redBomb;
    Bitmap redPiece1;
    Bitmap redPiece2;
    Bitmap redPiece3;
    Bitmap redPiece4;
    Bitmap redPiece5;
    Bitmap redPiece6;
    Bitmap redPiece7;
    Bitmap redPiece8;
    Bitmap redPiece9;
    Bitmap redPiece10;


    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);

        //let it draw
        this.setWillNotDraw(false);

        //init bitmaps
        //initBitmaps();

        //Set background
        //Drawable board = ResourcesCompat.getDrawable(context.getResources(), R.drawable.board, null);
        //this.setBackground(board);

        Random gen = new Random();


        //fill boardInfo randomly, this WILL BE CHANGED. Just proof of concept
        boardInfo = new int[WIDTH_CELLS][HEIGHT_CELLS];

        for (int col = 0; col < WIDTH_CELLS; col++) {
            for (int row = 0; row < HEIGHT_CELLS; row++) {
                if (row == 3 ||  row == 4) {
                    boardInfo[col][row] = 99; // 99 value for rows 3 and 4, avoids having them drawn
                    // just trying to ignore them makes them flags, which have a value of 0
                }
                else {
                    boardInfo[col][row] = gen.nextInt(12);
                }

            }
        }
    }


    //onDraw method, just initializes board dimensions for now
    @Override
    protected void onDraw(Canvas canvas) {
        dpHeight = canvas.getHeight();
        dpWidth = canvas.getWidth();

        initBoard(canvas);
        this.invalidate();
    }

    //onDraw helper, draws 2d array of pieces
    public void initBoard(Canvas canvas) {
        for (int col = 0; col < WIDTH_CELLS; col++) {
            for (int row = 0; row < HEIGHT_CELLS; row++) {

                drawBoardInfo(boardInfo[col][row], canvas, col, row);
            }
        }
    }

    //draws the pieces depending on the info given - made so any int can work, not stuck
    //only using a 2d int array
    public void drawBoardInfo(int pieceVal, Canvas canvas, int col, int row) {
        switch (pieceVal) {
            case 0:
                drawBoardHelper(canvas,redFlag,col,row);
                break;
            case 1:
                drawBoardHelper(canvas,redPiece1,col,row);
                break;
            case 2:
                drawBoardHelper(canvas,redPiece2,col,row);
                break;
            case 3:
                drawBoardHelper(canvas,redPiece3,col,row);
                break;
            case 4:
                drawBoardHelper(canvas,redPiece4,col,row);
                break;
            case 5:
                drawBoardHelper(canvas,redPiece5,col,row);
                break;
            case 6:
                drawBoardHelper(canvas,redPiece6,col,row);
                break;
            case 7:
                drawBoardHelper(canvas,redPiece7,col,row);
                break;
            case 8:
                drawBoardHelper(canvas,redPiece8,col,row);
                break;
            case 9:
                drawBoardHelper(canvas,redPiece9,col,row);
                break;
            case 10:
                drawBoardHelper(canvas,redPiece10,col,row);
                break;
            case 11:
                drawBoardHelper(canvas,redBomb,col,row);
                break;
            default:
        }

    }

    //draw helper
    public void drawBoardHelper(Canvas canvas, Bitmap bitmap, int col, int row) {
        canvas.drawBitmap(bitmap,(dpWidth/WIDTH_CELLS) * col + 25, (dpHeight/HEIGHT_CELLS) * row + 5, null);
    }

    /** commented out for now


    //initialize all Bitmaps
    public void initBitmaps() {
        redFlag = initBitmapHelper(R.drawable.flag_red, redFlag);
        redBomb = initBitmapHelper(R.drawable.bomb_red, redBomb);
        redPiece1 = initBitmapHelper(R.drawable.piece_1_red, redPiece1);
        redPiece2 = initBitmapHelper(R.drawable.piece_2_red,redPiece2);
        redPiece3 = initBitmapHelper(R.drawable.piece_3_red, redPiece3);
        redPiece4 = initBitmapHelper(R.drawable.piece_4_red, redPiece4);
        redPiece5 = initBitmapHelper(R.drawable.piece_5_red, redPiece5);
        redPiece6 = initBitmapHelper(R.drawable.piece_6_red, redPiece6);
        redPiece7 = initBitmapHelper(R.drawable.piece_7_red, redPiece7);
        redPiece8 = initBitmapHelper(R.drawable.piece_8_red, redPiece8);
        redPiece9 = initBitmapHelper(R.drawable.piece_9_red, redPiece9);
        redPiece10 = initBitmapHelper(R.drawable.piece_10_red, redPiece10);
    }
    */

    public Bitmap initBitmapHelper(int ID, Bitmap bitmap) {
        bitmap = BitmapFactory.decodeResource(getResources(),ID);
        return Bitmap.createScaledBitmap(bitmap,75,110,false);
    }

}
