package it.unibo.cardhub.model.domain.api;

import java.util.Optional;

/**
 * Represents a discard pile, an entity outside the table.
 */
public interface DiscardPile extends CardCollection {

    /**
     * Takes a chosen card from the pile.
     * 
     * @param card the chosen card
     * @return the chosen card
     */
    Card<?> takeCard(Card<?> card);

    /**
     * Returns the most recently discarded card without removing it.
     *
     * @return the most recently discarded card
     */
    Optional<Card<?>> peekCard();

    /**
     * Reshuffles the cards into the deck.
     *
     * @param deck of the match
     */
    void reshuffleIntoDeck(Deck deck);
}
