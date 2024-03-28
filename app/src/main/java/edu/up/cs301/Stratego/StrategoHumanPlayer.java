package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;

/**
 * A GUI of a counter-player. The GUI displays the current value of the counter,
 * and allows the human player to press the '+' and '-' buttons in order to
 * send moves to the game.
 * 
 * Just for fun, the GUI is implemented so that if the player presses either button
 * when the counter-value is zero, the screen flashes briefly, with the flash-color
 * being dependent on whether the player is player 0 or player 1.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public class StrategoHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */
	
	// The TextView the displays the current counter value
	private TextView testResultsTextView;
	
	// the most recent game state, as given to us by the CounterLocalGame
	private StrategoState state;
	
	// the android activity that we are running
	private GameMainActivity myActivity;
	
	/**
	 * constructor
	 * @param name
	 * 		the player's name
	 */
	public StrategoHumanPlayer(String name) {
		super(name);
	}

	/**
	 * Returns the GUI's top view object
	 * 
	 * @return
	 * 		the top object in the GUI's view hierarchy
	 */
	public View getTopView() {
		return myActivity.findViewById(R.id.project_e_layout);
	}
	
	/**
	 * sets the counter value in the text view
	 */
	protected void updateDisplay() {
		// set the text in the appropriate widget
		//counterValueTextView.setText("" + state.getCounter());
	}

	/**
	 * this method gets called when the user clicks the '+' or '-' button. It
	 * creates a new CounterMoveAction to return to the parent activity.
	 * 
	 * @param button
	 * 		the button that was clicked
	 */
	public void onClick(View button) {
		// if we are not yet connected to a game, ignore
		if (game == null) return;

		// clears TextView
		testResultsTextView.setText("");

		// creation of firstInstance and the deep copy
		StrategoState firstInstance = new StrategoState();
		firstInstance.setPlayerId(0);
		StrategoState firstCopy = new StrategoState(firstInstance);

		firstInstance.setIsBlueReady(true);
		firstInstance.setIsRedReady(true);
		firstInstance.setGamePhase(1);

		// TODO: playing the game from start to finish is the bulk of the assignment
		firstInstance.movePiece(2,1,5,1);
		testResultsTextView.append("\nAttack red 9 (5,1) with blue 2 (2,1)");

		firstInstance.movePieceAction(5,0,4,0);
		testResultsTextView.append("\nMove red 10 (5,0) to (5,1)");

		firstInstance.movePieceAction(2,9,3,9);
		testResultsTextView.append("\nMove blue 10 (2,9) to (3,9)");

		firstInstance.movePieceAction(5,8,4,8);
		testResultsTextView.append("\nMove red 6 (5,8) to empty (4,8)");

		firstInstance.movePieceAction(3,9,4,9);
		testResultsTextView.append("\nMove blue 10 (3,9) to (4,9)");

		firstInstance.movePieceAction(4,8,4,9);
		testResultsTextView.append("\nMove red 6 (4,8) to blue 10 (4,9)");

		firstInstance.movePieceAction(4,9,5,9);
		testResultsTextView.append("\nMove blue 10 (4,9) to red 5 (5,9)");

		firstInstance.movePieceAction(6,8,2,8);
		testResultsTextView.append("\nMove red 2 (6,8) to blue 11 (2,8)");

		firstInstance.movePieceAction(1,1,2,1);
		testResultsTextView.append("\nMove blue 5 (1,1) to empty (2,1)");

		firstInstance.movePieceAction(6,7,6,8);
		testResultsTextView.append("\nMove red 3 (6,7) to empty (6,8)");

		firstInstance.movePieceAction(2,4,3,4);
		testResultsTextView.append("\nMove blue 1 (2,4) to empty (3,4)");

		firstInstance.movePieceAction(6,8,7,8);
		testResultsTextView.append("\nMove red 3 (6,8) to empty (7,8)");

		firstInstance.movePieceAction(2,1,3,1);
		testResultsTextView.append("\nMove blue 5 (2,1) to empty (3,1)");

		firstInstance.movePieceAction(5,8,4,8);
		testResultsTextView.append("\nMove red 3 (5,8) to empty (4,8)");

		firstInstance.movePieceAction(1,9,4,9);
		testResultsTextView.append("\nMove blue 2 (1,9) to empty (4,9)");

		firstInstance.movePieceAction(4,8,3,8);
		testResultsTextView.append("\nMove red 3 (4,8) to empty (3,8)");

		firstInstance.movePieceAction(2,0,4,0);
		testResultsTextView.append("\nMove blue 2 (2,0) to red 10 (4,0)");

		firstInstance.movePieceAction(3,8,2,8);
		testResultsTextView.append("\nMove red 3 (3,8) to blue 11 (2,8)");

		firstInstance.movePieceAction(5,9,6,9);
		testResultsTextView.append("\nMove blue 10 (5,9) to red 2 (6,9)");

		firstInstance.movePieceAction(5,7,6,7);
		testResultsTextView.append("\nMove red 6 (5,7) to empty (6,7)");

		firstInstance.movePieceAction(6,9,6,8);
		testResultsTextView.append("\nMove blue 10 (6,9) to empty (6,8)");

		firstInstance.movePieceAction(5,1,4,1);
		testResultsTextView.append("\nMove red 9 (5,1) to empty (4,1)");

		firstInstance.movePieceAction(6,8,7,8);
		testResultsTextView.append("\nMove blue 10 (6,8) to red 11 (7,8)");

		firstInstance.movePieceAction(4,1,3,1);
		testResultsTextView.append("\nMove red 9 (4,1) to blue 5 (3,1)");

		firstInstance.movePieceAction(4,9,7,9);
		testResultsTextView.append("\nMove blue 2 (4,9) to red 0 (7,9), CAPTURED FLAG");

		firstInstance.setGamePhase(2);

		testResultsTextView.append("\nBLUE WINS\n");

		// creation of the secondInstance and the deep copy
		StrategoState secondInstance = new StrategoState();
		secondInstance.setPlayerId(0);
		StrategoState secondCopy = new StrategoState(secondInstance);

		// implements toString() for both copies
		String fstCpy = firstCopy.toString();
		String secCpy = secondCopy.toString();

		String gme = firstInstance.toString(); // check the first instance game data

		// if the copies are identical, we append the strings to the TextView
		if(fstCpy.equals(secCpy)) {
			System.out.println("FirstCopy and SecondCopy are the same");

			testResultsTextView.append(fstCpy);
			testResultsTextView.append("\n\n");
			testResultsTextView.append(secCpy);
		}
		else {
			testResultsTextView.append("Copies are not identical... :(");
		}

		testResultsTextView.append("\n\n");
		testResultsTextView.append(gme);

	}// onClick
	
	/**
	 * callback method when we get a message (e.g., from the game)
	 * 
	 * @param info
	 * 		the message
	 */
	@Override
	public void receiveInfo(GameInfo info) {
		// ignore the message if it's not a CounterState message
		if (!(info instanceof StrategoState)) return;
		
		// update our state; then update the display
		this.state = (StrategoState)info;
		updateDisplay();
	}
	
	/**
	 * callback method--our game has been chosen/rechosen to be the GUI,
	 * called from the GUI thread
	 * 
	 * @param activity
	 * 		the activity under which we are running
	 */
	public void setAsGui(GameMainActivity activity) {
		// remember the activity
		this.myActivity = activity;
		
	    // Load the layout resource for our GUI
		myActivity.setContentView(R.layout.project_e);

		// initializes variable to EditText (subclass of TextView) in layout
		this.testResultsTextView = myActivity.findViewById(R.id.editText);

		// sets this class as a handler for the button
		Button runTest = myActivity.findViewById(R.id.runTestButton);
		runTest.setOnClickListener(this);
	}

}// class CounterHumanPlayer

