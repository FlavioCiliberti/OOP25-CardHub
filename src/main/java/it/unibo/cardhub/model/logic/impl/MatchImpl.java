package it.unibo.cardhub.model.logic.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.api.Playfield;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.impl.MatchStateImpl;
import it.unibo.cardhub.model.logic.api.Match;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;

/**
 * an implementation of Match.
 */
class MatchImpl implements Match {
    private final MatchState matchState;
    private final MatchLogic matchLogic;

    private final boolean autoDraw;

    private MatchStatus status;
    private Optional<Player> winner;

    /**
     * Match constructor.
     * 
     * @param player1 the first player
     * @param player2 the second player
     * @param playerFieldSize maximum number of cards on the field per player
     * @param autoDraw should the turn player draw a card on turn start
     * @param matchLogic the match logic
     */
    MatchImpl(final Player player1, final Player player2,
                        final int playerFieldSize, final boolean autoDraw,
                        final MatchLogic matchLogic) {
        matchState = new MatchStateImpl(new ArrayList<>(Arrays.asList(player1, player2)), playerFieldSize);

        this.matchLogic = matchLogic;
        this.autoDraw = autoDraw;
        this.winner = Optional.empty();

        this.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCard(final Player player) throws CardCollectionFullException {
        player.drawCard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playCard(final Card<?> card, final Player player) throws CardCollectionFullException {
        if (matchState.getPlayfield().canAddCard(player)) {
            player.playCard(card);
            matchState.getPlayfield().addCard(player, card);
        } else {
            throw new CardCollectionFullException("Player's field is full!");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCardFromFieldToPile(final Card<?> card, final Player player) {
        matchState.getPlayfield().removeCard(card);
        player.putInPile(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComparisonWinner compareCard(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard) {
        return matchLogic.compareCard(firstPlayerCard, secondPlayerCard, matchState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getTurnPlayer() {
        return matchLogic.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public void changeTurn() {
        this.matchLogic.changeTurn();
        if (autoDraw) {
            try {
                this.drawCard(this.getTurnPlayer());
            } catch (final CardCollectionFullException e) {
                // Expected: the player doesn't draw if their hand is already full
            }
        }
    }

    private void start() {
        if (this.status != MatchStatus.CREATED) {
            throw new IllegalStateException("The match has already started.");
        }

        this.status = MatchStatus.RUNNING;
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

        if (!matchState.getPlayers().contains(player)) {
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
     * {@inheritDoc}
     */
    @Override
    public Playfield getPlayfield() {
        return matchState.getPlayfield();
    }

    /**
     * Represents the status of the match.
     */
    enum MatchStatus {
        CREATED, RUNNING, FINISHED
    }
}
