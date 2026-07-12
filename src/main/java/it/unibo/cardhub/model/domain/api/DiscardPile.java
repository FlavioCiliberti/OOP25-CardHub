package it.unibo.cardhub.model.domain.api;

/**
 * Represents a discard pile, an entity outside the table.
 */
public interface DiscardPile extends CardCollection {

    /**
     * Returns the most recently discarded card without removing it.
     *
     * @return the most recently discarded card
     */
    Card peekCard();

    /**
     * Reshuffles the cards into the deck.
     *
     * @param deck of the match
     */
    void reshuffleIntoDeck(Deck deck);
}
