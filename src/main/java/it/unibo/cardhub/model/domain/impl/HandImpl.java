package it.unibo.cardhub.model.domain.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Hand;
import it.unibo.cardhub.model.domain.exceptions.NoSuchCardsException;

/**
 * Hand implementation.
 */
public class HandImpl extends AbstractCardCollection implements Hand {

    private final int maxSize;

    /**
     * Hand constructor.
     * 
     * @param cards hand cards
     * @param maxSize max amount of cards
     * @throws IllegalArgumentException if max hand size is null or negative
     */
    public HandImpl(final List<? extends Card<?>> cards, final int maxSize) {
        super(new ArrayList<>(cards));
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Maximum hand size must be positive.");
        }

        this.maxSize = maxSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxSize() {
        return this.maxSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        return this.size() >= this.maxSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card<?> playCard(final Card<?> card) {
        if (!this.getMutableCards().remove(card)) {
            throw new NoSuchCardsException();
        }

        return card;
    }
}
