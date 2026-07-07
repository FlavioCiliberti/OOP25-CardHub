package it.unibo.cardhub.model.domain.api;

/**
 * Represents a discard pile, an entity outside the table.
 */
public interface DiscardPile extends CardCollection {

    /**
     * Reshuffles the cards into the deck.
     *
     * @param deck of the match
     */
    void reshuffleIntoDeck(Deck deck);
}
