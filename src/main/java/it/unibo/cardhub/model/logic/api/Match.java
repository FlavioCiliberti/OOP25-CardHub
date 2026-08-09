package it.unibo.cardhub.model.logic.api;

import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Handles the match in its entirety.
 */
public interface Match {
    /**
     * Makes the player draw a card.
     * 
     * @param player the player to draw the card
     * @throws CardCollectionFullException if the player's hand is full
     */
    public void drawCard(PlayerEnum player) throws CardCollectionFullException;

    /**
     * getter for the turn player.
     * 
     * @return the turn player.
     */
    public PlayerEnum getTurnPlayer();

    /**
     * changes the turn player.
     */
    public void changeTurn();
}
