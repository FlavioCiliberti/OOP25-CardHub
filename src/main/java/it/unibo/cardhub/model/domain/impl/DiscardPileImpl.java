package it.unibo.cardhub.model.domain.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.DiscardPile;
import it.unibo.cardhub.model.domain.exceptions.NoSuchCardsException;

/**
 * Discard pile implementation.
 */
public class DiscardPileImpl extends AbstractCardCollection implements DiscardPile {

    /**
     * Discard pile constructor.
     * 
     * @param cards discard pile cards
     */
    public DiscardPileImpl(final List<? extends Card<?>> cards) {
        super(List.copyOf(cards));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card<?> takeCard(final Card<?> card) {
        if (!this.getMutableCards().remove(card)) {
            throw new NoSuchCardsException();
        }

        return card;
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
    public void reshuffleIntoDeck(final Deck deck) {
        this.getMutableCards().forEach(deck::addCard);
        this.getMutableCards().clear();
        deck.shuffle();
    }
}
