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
     * Puts a card for a player's hand to the table.
     * 
     * @param card to be played
     * @return the card
     */
    Card<?> playCard(Card<?> card);
}
