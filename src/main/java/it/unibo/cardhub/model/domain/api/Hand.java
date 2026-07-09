package it.unibo.cardhub.model.domain.api;

/**
 * Represents the hand of a player.
 */
public interface Hand extends CardCollection {

    /**
     * Puts a card for a player's hand to the table.
     * 
     * @param card to be played
     */
    void playCard(Card card);
}
