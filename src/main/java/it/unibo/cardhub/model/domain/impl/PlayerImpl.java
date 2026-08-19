package it.unibo.cardhub.model.domain.impl;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.DiscardPile;
import it.unibo.cardhub.model.domain.api.Hand;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Player implementation.
 */
public final class PlayerImpl implements Player {

    private final String name;
    private final Hand hand;
    private final Deck deck;
    private final DiscardPile discardPile;

    /**
     * Player constructor.
     * 
     * @param name player name
     * @param maxHandSize the maximum hand size
     * @param startingHandSize the amount of card in hand at match start
     * @param deck player deck
     */
    public PlayerImpl(final String name, final int maxHandSize, final int startingHandSize, final Deck deck) {
        if (startingHandSize > maxHandSize) {
            throw new IllegalArgumentException("Starting hand size cannot be greater than maximum hand size.");
        }
        if (startingHandSize <= 0 || maxHandSize <= 0) {
            throw new IllegalArgumentException("Starting hand size and maximum hand size must be positive.");
        }

        this.name = Objects.requireNonNull(name);
        this.hand = new HandImpl(new ArrayList<>(), maxHandSize);
        this.deck = Objects.requireNonNull(deck);
        this.discardPile = new DiscardPileImpl(new ArrayList<>());

        //draw initial cards
        for (int i = 0; i < startingHandSize; i++) {
            try {
                this.drawCard();
            } catch (final CardCollectionFullException e) {
                //startingHandSize <= maxHandSize so the exception should never trigger
                throw new IllegalStateException("Hand already had cards in it on instantiation", e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putInPile(final Card<?> card) {
        this.discardPile.addCard(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Card<?>> peekDiscardPile() {
        return discardPile.peekCard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shufflePileIntoDeck() {
        this.discardPile.reshuffleIntoDeck(deck);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card<?> drawCard() throws CardCollectionFullException {
        if (this.hand.isFull()) {
            throw new CardCollectionFullException("The hand exceeded the max amount of cards.");
        }

        if (this.deck.isEmpty()) {
            throw new IllegalStateException("Tried to draw with an empty deck");
        }

        final Card<?> card = this.deck.drawCard();
        this.hand.addCard(card);
        return card;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card<?> playCard(final Card<?> card) {
        return this.hand.playCard(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
                        justification = "Hand is intentionally exposed to let callers mutate its state"
    )
    public Hand getHand() {
        return this.hand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEmptyDeck() {
        return this.deck.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEmptyDiscardPile() {
        return this.discardPile.isEmpty();
    }

    @Override
    public int getDeckCount() {
        return this.deck.size();
    }
}
