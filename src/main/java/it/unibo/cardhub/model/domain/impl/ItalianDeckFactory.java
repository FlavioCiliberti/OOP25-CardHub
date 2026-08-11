package it.unibo.cardhub.model.domain.impl;

import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.DeckFactory;

/**
 * Factory for creating Italian decks.
 */
public class ItalianDeckFactory implements DeckFactory {

    private static final int MAX = 10;

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck create() {
        final Deck deck = new DeckImpl();
        for (final Suit suit : Suit.values()) {
            for (int value = 1; value <= MAX; value++) {
                deck.addCard(CardImpl.of(suit.name() + "_" + value, suit, value));
            }
        }
        return deck;
    }

    /**
     * Represents the suits of an Italian deck.
     */
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
}
