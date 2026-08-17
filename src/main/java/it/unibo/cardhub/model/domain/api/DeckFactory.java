package it.unibo.cardhub.model.domain.api;

/**
 * Represents a factory for creating decks.
 */
public interface DeckFactory {

    /**
     * Creates a new deck.
     * 
     * @return a new deck
     */
    Deck createItalianDeck();

    /**
     * Creates a new Yu-Gi-Oh deck.
     * 
     * @return a new Yu-Gi-Oh deck
     */
    Deck createYuGiOhDeck();

    /**
     * Creates a new Pokemon deck.
     * 
     * @return a new Pokemon deck
     */
    Deck createPokemonDeck();

    /**
     * Creates a new Dragon Ball deck.
     * 
     * @return a new Dragon Ball deck
     */
    Deck createDragonBallDeck();

    /**
     * Represents the suits of an Italian deck.
     */
    enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
}
