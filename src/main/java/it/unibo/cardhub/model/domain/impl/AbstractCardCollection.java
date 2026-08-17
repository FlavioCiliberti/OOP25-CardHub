package it.unibo.cardhub.model.domain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.CardCollection;

/**
 * Represents an abstract card collection.
 */
public abstract class AbstractCardCollection implements CardCollection {

    private final List<Card<?>> cards = new ArrayList<>();

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
     * Returns a mutable list of all the cards of the collection.
     * 
     * @return a list of all the cards
     */
    protected List<Card<?>> getMutableCards() {
        return this.cards;
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
}
