package it.unibo.cardhub.model.domain.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.api.Playfield;
import it.unibo.cardhub.model.domain.exceptions.NoSuchCardsException;

/**
 * Playfield implementation.
 */
public class PlayfieldImpl implements Playfield {

    private final Map<Player, List<Card>> playerCards;
    private final int maxHandSize;

    /**
     * Playfield constructor.
     * 
     * @param players of the playfield
     * @param maxHandSize of the game
     */
    public PlayfieldImpl(final List<Player> players, final int maxHandSize) {
        this.playerCards = new LinkedHashMap<>();
        players.forEach(p -> this.playerCards.put(Objects.requireNonNull(p), new ArrayList<>()));
        this.maxHandSize = maxHandSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxCardsPerPlayer() {
        return this.maxHandSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canAddCard(final Player player) {
        final List<Card> cards = Objects.requireNonNull(this.playerCards.get(player), "No such player.");
        return cards.size() < this.maxHandSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCard(final Player player, final Card card) {
        if (!canAddCard(player)) {
            throw new IllegalStateException("The player cannot add more cards.");
        }

        this.playerCards.get(player).add(Objects.requireNonNull(card, "No such card."));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card removeCard(final Card card) {
        for (final List<Card> cards : this.playerCards.values()) {
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
    public List<Card> getCards(final Player player) {
        return List.copyOf(Objects.requireNonNull(this.playerCards.get(player), "No such player."));
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
    public List<Card> getAllCards() {
        return this.playerCards.values().stream().flatMap(List::stream).toList();
    }
}
