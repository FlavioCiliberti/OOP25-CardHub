package it.unibo.cardhub.model.domain.api;

import java.util.Optional;

/**
 * Represents a deck.
 */
public interface Deck extends CardCollection {

    /**
     * Adds a card to the deck.
     * 
     * @param card to be added
     */
    void addCard(Card<?> card);

    /**
     * Draws a card from the deck.
     * 
     * @return the drawn card
     */
    Card<?> drawCard();

    /**
     * Peeks a card without removing it.
     * 
     * @return the peeked card
     */
    Optional<Card<?>> peekCard();

    /**
     * Shuffles the deck's cards.
     */
    void shuffle();
}
