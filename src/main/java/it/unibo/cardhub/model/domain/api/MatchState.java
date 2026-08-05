package it.unibo.cardhub.model.domain.api;

import java.util.List;
import java.util.Optional;

/**
 * Represent a match, the heart of the game.
 */
public interface MatchState {

    /**
     * Starts a match.
     */
    void start();

    /**
     * Sets a new turn for the next player.
     */
    void nextTurn();

    /**
     * A getter for the player of the current turn.
     * 
     * @return the current player
     */
    Player getCurrentPlayer();

    /**
     * Returns all the players of the match.
     * 
     * @return all the players
     */
    List<Player> getPlayers();

    /**
     * A getter for the match's winner.
     * 
     * @return the winner, if present
     */
    Optional<Player> getWinner();

    /**
     * Ends a match and sets the winner.
     * 
     * @param player the winner
     */
    void endMatch(Player player);

    /**
     * Informs about the match's state.
     * 
     * @return true if finished, false otherwise
     */
    boolean isFinished();
}
