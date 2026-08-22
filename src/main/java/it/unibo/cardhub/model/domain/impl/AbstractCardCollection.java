package it.unibo.cardhub.model.domain.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.CardCollection;

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
        this.cards = new LinkedList<>(Objects.requireNonNull(cards));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCard(final Card<?> card) {
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
}
