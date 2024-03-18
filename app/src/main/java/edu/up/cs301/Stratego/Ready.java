package edu.up.cs301.Stratego;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * This class is a placeholder for the Ready button,
 * in which it will allow both players to say they
 * are ready to begin the game.
 *
 * @author Josh Krasnogorov
 * @author Indiana Atwood
 * @author James Nguyen
 * @author Ethan Brown
 * @version March 2024
 */
public class Ready extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public Ready(GamePlayer player) {
        super(player);
    }
}
