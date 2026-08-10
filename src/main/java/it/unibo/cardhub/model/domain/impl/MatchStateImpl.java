package it.unibo.cardhub.model.domain.impl;

import java.util.List;
import java.util.Objects;

import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.api.Playfield;

/**
 * Match state implementation.
 */
public class MatchStateImpl implements MatchState {

    private final List<Player> players;
    private final Playfield field;

    /**
     * Match state constructor.
     * 
     * @param players of the match
     * @throws IllegalArgumentException if the match has no players
     */
    public MatchStateImpl(final List<Player> players, int maxFieldSize) {
        Objects.requireNonNull(players);

        if (players.isEmpty()) {
            throw new IllegalArgumentException("A match needs at least one player.");
        }

        this.players = List.copyOf(players);
        this.field = new PlayfieldImpl(players, maxFieldSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return List.copyOf(this.players);
    }
}
