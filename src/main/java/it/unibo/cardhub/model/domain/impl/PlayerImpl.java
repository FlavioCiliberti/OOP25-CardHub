package it.unibo.cardhub.model.domain.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.Hand;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Player implementation.
 */
public class PlayerImpl implements Player {

    private final String name;
    private final Hand hand;
    private final Deck deck;

    /**
     * Player constructor.
     * 
     * @param name player name
     * @param hand player hand
     * @param deck player deck
     */
    @SuppressFBWarnings(value = "EI2", justification = "The player's deck is intentionally shared with the match lifecycle.")
    public PlayerImpl(final String name, final Hand hand, final Deck deck) {
        this.name = name;
        this.hand = hand;
        this.deck = deck;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card drawCard() throws CardCollectionFullException{
        if (this.hand.isFull()) {
            throw new CardCollectionFullException("The hand exceeded the max amount of cards.");
        }

        final Card card = this.deck.drawCard();
        this.hand.addCard(card);
        return card;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card playCard(final Card card) {
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
    public Hand getHand() {
        return this.hand;
    }
}
