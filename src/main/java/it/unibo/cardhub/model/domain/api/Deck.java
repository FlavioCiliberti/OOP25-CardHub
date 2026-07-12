package it.unibo.cardhub.model.domain.api;

/**
 * Represents a deck.
 */
public interface Deck extends CardCollection {

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
