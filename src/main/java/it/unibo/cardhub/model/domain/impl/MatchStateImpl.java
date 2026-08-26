package it.unibo.cardhub.model.domain.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.api.Playfield;

/**
 * Match state implementation.
 */
public class MatchStateImpl implements MatchState {
    private final List<Player> players;
    private final Playfield field;

    private MatchStatus status;
    private Optional<Player> winner;

    /**
     * Match state constructor.
     * 
     * @param players of the match
     * @param maxFieldSize maximum number of cards on the field per player
     * @throws IllegalArgumentException if the match has no players
     */
    public MatchStateImpl(final List<Player> players, final int maxFieldSize) {
        Objects.requireNonNull(players);

        if (players.size() != 2) {
            throw new IllegalArgumentException("A match needs exactly two players.");
        }
        if (maxFieldSize <= 0) {
            throw new IllegalArgumentException("Maximum field size must be positive.");
        }

        this.players = List.copyOf(players);
        this.field = new PlayfieldImpl(players, maxFieldSize);

        this.winner = Optional.empty();

        status = MatchStatus.CREATED;
        this.start();
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
    public Player getPlayer(final PlayerEnum player) {
        return this.getPlayers().get(player.getIndex());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCard(final PlayerEnum player) {
        this.getPlayer(player).drawCard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playCard(final Card<?> card, final PlayerEnum playerEnum) {
        this.getPlayer(playerEnum).playCard(card);
        field.addCard(playerEnum, card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCardFromFieldToPile(final Card<?> card, final PlayerEnum playerEnum) {
        field.removeCard(card);
        this.getPlayer(playerEnum).putInPile(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerEnum getEnum(final Player player) {
        if (players.get(0).equals(player)) {
            return PlayerEnum.PLAYER_ONE;
        }
        if (players.get(1).equals(player)) {
            return PlayerEnum.PLAYER_TWO;
        }
        throw new IllegalArgumentException("No such player");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
                        justification = "Playfield is intentionally exposed to let callers mutate its state"
    )
    public Playfield getPlayfield() {
        return this.field;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayFieldSize() {
        return field.getMaxCardsPerPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shufflePileIntoDeck(final PlayerEnum player) {
        this.getPlayer(player).shufflePileIntoDeck();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmptyDeck(final PlayerEnum owner) {
        return this.getPlayer(owner).hasEmptyDeck();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmptyDiscardPile(final PlayerEnum owner) {
        return this.getPlayer(owner).hasEmptyDiscardPile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endMatch(final Player player) {
        if (this.status != MatchStatus.RUNNING) {
            throw new IllegalStateException("A winner can only be set while the match is running.");
        }

        if (!this.getPlayers().contains(player)) {
            throw new IllegalArgumentException("The winner must be a player of this match.");
        }

        this.winner = Optional.of(player);
        this.status = MatchStatus.FINISHED;
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
    public boolean isFinished() {
        return this.status == MatchStatus.FINISHED;
    }

    private void start() {
        if (this.status != MatchStatus.CREATED) {
            throw new IllegalStateException("The match has already started.");
        }

        this.status = MatchStatus.RUNNING;
    }

    /**
     * Represents the status of the match.
     */
    enum MatchStatus {
        CREATED, RUNNING, FINISHED
    }
}
