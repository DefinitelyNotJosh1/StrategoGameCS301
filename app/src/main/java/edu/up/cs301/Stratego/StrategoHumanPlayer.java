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
		MovePiece move1 = new MovePiece(this,2,1,5,1);
		game.sendAction(move1);
		testResultsTextView.append("\nAttack red 9 (5,1) with blue 2 (2,1)");

		//MovePiece move2 = new MovePiece(this,2,1,5,1);


		// creation of the secondInstance and the deep copy
		StrategoState secondInstance = new StrategoState();
		secondInstance.setPlayerId(0);
		StrategoState secondCopy = new StrategoState(secondInstance);

		// implements toString() for both copies
		String fstCpy = firstCopy.toString();
		String secCpy = secondCopy.toString();

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

