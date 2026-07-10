package it.unibo.cardhub.model.domain.api;

/**
 * Represents the hand of a player.
 */
public interface Hand extends CardCollection {

    /**
     * Returns the maximum amount of cards per hand.
     * 
     * @return the maximum amount
     */
    int getMaxSize();

    /**
     * Informs whether a player's hand is full.
     * 
     * @return true if full, false otherwise
     */
    boolean isFull();

    /**
     * Puts a card for a player's hand to the table.
     * 
     * @param card to be played
     */
    void playCard(Card card);
}
