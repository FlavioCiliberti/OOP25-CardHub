package it.unibo.cardhub.model.domain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.CardCollection;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Represents an abstract card collection.
 */
public abstract class AbstractCardCollection implements CardCollection {

    private final List<Card<?>> cards;

    /**
     * Card collection constructor.
     * 
     * @param cards card collection cards
     */
    protected AbstractCardCollection(final List<? extends Card<?>> cards) {
        this.cards = new ArrayList<>(Objects.requireNonNull(cards));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addCard(final Card<?> card) throws CardCollectionFullException {
        if (this.isFull()) {
            throw new CardCollectionFullException();
        }

        this.cards.add(Objects.requireNonNull(card, "No such card."));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card<?>> getCards() {
        return List.copyOf(this.cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.cards.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    /**
     * Returns deck cards that can be modified. It's needed for incapsulation purposes.
     * 
     * @return a mutable list of cards
     */
    protected List<Card<?>> getMutableCards() {
        return this.cards;
    }

    /**
     * Checks if the card collection is full.
     * 
     * @return {@code true} if the card collection is full
     */
    protected abstract boolean isFull();
}
