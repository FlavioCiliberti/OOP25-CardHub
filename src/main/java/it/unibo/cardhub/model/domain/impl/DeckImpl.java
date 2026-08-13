package it.unibo.cardhub.model.domain.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.Card.Suit;
import it.unibo.cardhub.model.domain.exceptions.EmptyCardCollectionException;

/**
 * Deck implementation.
 */
public class DeckImpl extends AbstractCardCollection implements Deck {

    /**
     * Deck constructor.
     * 
     * @param cards deck cards
     */
    public DeckImpl(final List<? extends Card<?>> cards) {
        super(new ArrayList<>(cards));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card<?> drawCard() {
        if (this.isEmpty()) {
            throw new EmptyCardCollectionException();
        }

        return this.getMutableCards().remove(this.size() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shuffle() {
        Collections.shuffle(this.getMutableCards());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Card<?>> peekCard() {
        if (getMutableCards().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(getMutableCards().get(this.size() - 1));
    }

    /**
     * Creates a deck of italian traditional cards.
     * 
     * @return deck with italian cards
     */
    public static Deck createItalian() {
        final Deck deck = new DeckImpl(new ArrayList<>());

        for (final Suit suit : Suit.values()) {
            for (int value = 1; value <= 10; value++) {
                deck.addCard(new CardImpl<>(
                    suit.name() + "_" + value, 
                        suit, 
                            value, 
                                Optional.empty(), 
                                    suit.name() + "_" + value + ".png"));
            }
        }

        return deck;
    }
}
