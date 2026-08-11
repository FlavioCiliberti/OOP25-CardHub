package it.unibo.cardhub.model.domain.api;

/**
 * Represents a factory for creating decks.
 */
@FunctionalInterface
public interface DeckFactory {

    /**
     * Creates a new deck.
     * 
     * @return a new deck
     */
    Deck create();
}
