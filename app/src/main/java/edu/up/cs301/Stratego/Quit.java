package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * Currently a placeholder for the next assignment,
 * in which the Quit button will end the game for
 * both players.
 *
 * @author Josh Krasnogorov
 * @author Indiana Atwood
 * @author James Nguyen
 * @author Ethan Brown
 * @version March 2024
 */
public class Quit extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public Quit(GamePlayer player) {
        super(player);

    }
}
