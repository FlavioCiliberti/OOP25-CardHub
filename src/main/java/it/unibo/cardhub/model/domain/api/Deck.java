package it.unibo.cardhub.model.domain.api;

/**
 * Represents a deck.
 */
public interface Deck extends CardCollection {

    /**
     * Returns the deck's ID.
     * 
     * @return the deck's ID
     */
    String getId();

    /**
     * Draws a card from the deck.
     * 
     * @return the drawn card
     */
    Card drawCard();

    /**
     * Shuffles the deck's cards.
     */
    void shuffle();
}
