package it.unibo.cardhub.model.domain.api;

import java.util.List;
import java.util.Optional;

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
     * Peeks a card without removing it.
     * 
     * @return the peeked card
     */
    Optional<Card> peekCard();

    /**
     * Shuffles the deck's cards.
     */
    void shuffle();

    /**
     * Adds a collection of cards to deck (should be refactored).
     * 
     * @param cards list of cards
     */
    void addCards(List<Card> cards);
}
