package it.unibo.cardhub.io.api;

import it.unibo.cardhub.model.domain.api.Deck;

/**
 * Factory interface for creating instances of Deck for different card types.
 */
public interface DeckFactory {

    /**
     * Creates a deck of italian traditional cards.
     * 
     * @return italian cards deck
     */
    Deck createItalianDeck();

    /**
     * Creates a Pokemon deck.
     * 
     * @return Pokemon deck
     */
    Deck createPokemonDeck();

    /**
     * Creates a Dragon Ball deck.
     * 
     * @return Dragon Ball deck
     */
    Deck createDragonBallDeck();

    /**
     * Creates a Yu-Gi-Oh! deck.
     * 
     * @return Yu-Gi-Oh! deck
     */
    Deck createYuGiOhDeck();
}
