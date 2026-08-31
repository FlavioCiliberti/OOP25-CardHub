package it.unibo.cardhub.model.domain.impl;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.api.Playfield;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.exceptions.NoSuchCardsException;

/**
 * Playfield implementation.
 */
public class PlayfieldImpl implements Playfield {

    private final Map<PlayerEnum, List<Card<?>>> playerCards;
    private final int maxFieldSize;

    /**
     * Playfield constructor.
     * 
     * @param maxFieldSize of the game
     */
    public PlayfieldImpl(final int maxFieldSize) {
        if (maxFieldSize <= 0) {
            throw new IllegalArgumentException("Maximum field size must be positive.");
        }

        this.playerCards = new EnumMap<>(PlayerEnum.class);
        for (final PlayerEnum player : PlayerEnum.values()) {
            this.playerCards.put(player, new ArrayList<>());
        }
        this.maxFieldSize = maxFieldSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxCardsPerPlayer() {
        return this.maxFieldSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canAddCard(final PlayerEnum player) {
        final List<Card<?>> cards = this.playerCards.get(player);
        return cards.size() < this.maxFieldSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCard(final PlayerEnum player, final Card<?> card) {
        if (!canAddCard(player)) {
            throw new CardCollectionFullException("The player cannot add more cards.");
        }

        this.playerCards.get(player).add(Objects.requireNonNull(card, "No such card."));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card<?> removeCard(final Card<?> card) {
        for (final List<Card<?>> cards : this.playerCards.values()) {
            if (cards.remove(card)) {
                return card;
            }
        }

        throw new NoSuchCardsException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card<?>> getCards(final PlayerEnum player) {
        return List.copyOf(this.playerCards.get(player));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAll() {
        this.playerCards.values().forEach(List::clear);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card<?>> getAllCards() {
        return this.playerCards.values().stream().flatMap(List::stream).toList();
    }
}
