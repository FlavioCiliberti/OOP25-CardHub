package it.unibo.cardhub.model.domain.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;

/**
 * Match state implementation.
 */
public class MatchStateImpl implements MatchState {

    private final List<Player> players;
    private Player currentPlayer;
    private Optional<Player> winner;
    private MatchStatus status;

    /**
     * Match state constructor.
     * 
     * @param players of the match
     * @throws IllegalArgumentException if the match has no players
     */
    public MatchStateImpl(final List<Player> players) {
        Objects.requireNonNull(players);

        if (players.isEmpty()) {
            throw new IllegalArgumentException("A match needs at least one player.");
        }

        this.players = List.copyOf(players);
        this.winner = Optional.empty();
        this.status = MatchStatus.CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        if (this.status != MatchStatus.CREATED) {
            throw new IllegalStateException("The match has already started.");
        }

        this.currentPlayer = this.players.get(0);
        this.status = MatchStatus.RUNNING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextTurn() {
        if (this.status != MatchStatus.RUNNING) {
            throw new IllegalStateException("Cannot change turn if the match is not running.");
        }

        final int currentIndex = this.players.indexOf(this.currentPlayer);
        final int nextIndex = (currentIndex + 1) % this.players.size();

        this.currentPlayer = this.players.get(nextIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return Objects.requireNonNull(this.currentPlayer, "The match has not started yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return List.copyOf(this.players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Player> getWinner() {
        return this.winner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endMatch(final Player player) {
        if (this.status != MatchStatus.RUNNING) {
            throw new IllegalStateException("A winner can only be set while the match is running.");
        }

        if (!this.players.contains(player)) {
            throw new IllegalArgumentException("The winner must be a player of this match.");
        }

        this.winner = Optional.of(player);
        this.status = MatchStatus.FINISHED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinished() {
        return this.status == MatchStatus.FINISHED;
    }

    /**
     * Represents the status of the match.
     */
    public enum MatchStatus {
        CREATED, RUNNING, FINISHED
    }
}
