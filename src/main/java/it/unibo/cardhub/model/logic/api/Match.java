package it.unibo.cardhub.model.logic.api;

import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Handles the match in its entirety.
 */
public interface Match {
    /**
     * Starts a match.
     */
    void start();

    /**
     * Makes the player draw a card.
     * 
     * @param player the player to draw the card
     * @throws CardCollectionFullException if the player's hand is full
     */
    void drawCard(PlayerEnum player) throws CardCollectionFullException;

    /**
     * getter for the turn player.
     * 
     * @return the turn player.
     */
    PlayerEnum getTurnPlayer();

    /**
     * changes the turn player.
     */
    void changeTurn();

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
