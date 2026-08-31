package it.unibo.cardhub.model.domain.impl;

import java.util.Map;
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

    private final Map<PlayerEnum, Player> players;
    private final Playfield field;

    private MatchStatus status;
    private Optional<Player> winner;

    /**
     * Match state constructor.
     * 
     * @param playerOne first player
     * @param playerTwo second player
     * @param maxFieldSize maximum number of cards on the field per player
     * @throws IllegalArgumentException if the maximum field size is invalid
     */
    public MatchStateImpl(final Player playerOne, final Player playerTwo, final int maxFieldSize) {
        Objects.requireNonNull(playerOne);
        Objects.requireNonNull(playerTwo);

        if (maxFieldSize <= 0) {
            throw new IllegalArgumentException("Maximum field size must be positive.");
        }

        this.players = Map.of(PlayerEnum.PLAYER_ONE, playerOne, PlayerEnum.PLAYER_TWO, playerTwo);
        this.field = new PlayfieldImpl(maxFieldSize);

        this.winner = Optional.empty();

        status = MatchStatus.CREATED;
        this.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(final PlayerEnum player) {
        return players.get(Objects.requireNonNull(player));
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
    public void playCard(final Card<?> card, final PlayerEnum player) {
        this.field.addCard(player, card);
        this.getPlayer(player).playCard(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCardFromFieldToPile(final Card<?> card, final PlayerEnum player) {
        this.field.removeCard(card);
        this.getPlayer(player).putInPile(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCardFromFieldToDeck(final Card<?> card, final PlayerEnum player) {
        this.field.removeCard(card);
        this.getPlayer(player).putInDeck(card);
        this.getPlayer(player).shuffleDeck();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
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
        return this.field.getMaxCardsPerPlayer();
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
    public void endMatch(final PlayerEnum player) {
        if (this.status != MatchStatus.RUNNING) {
            throw new IllegalStateException("A winner can only be set while the match is running.");
        }

        this.winner = Optional.of(this.getPlayer(player));
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
    private enum MatchStatus {
        CREATED, RUNNING, FINISHED
    }
}
