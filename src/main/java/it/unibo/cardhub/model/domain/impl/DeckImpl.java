package it.unibo.cardhub.model.domain.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
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
        super(Objects.requireNonNull(cards));
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
     * {@inheritDoc}
     */
    @Override
    public void addCard(final Card<?> card) {
        this.getMutableCards().add(card);
    }
}
