package it.unibo.cardhub.model.logic.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.impl.MatchStateImpl;
import it.unibo.cardhub.model.logic.api.Match;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.model.logic.api.PlayerEnum;

class MatchImpl implements Match{
    private final MatchState matchState;
    private final MatchLogic matchLogic;

    private final boolean autoDraw;

    private MatchStatus status;
    private Optional<Player> winner;

    public MatchImpl(Player player1, Player player2,
                        int maxHandSize, int startingHandSize,
                        int playerFieldSize, boolean autoDraw,
                        MatchLogic matchLogic) {
        matchState = new MatchStateImpl(new ArrayList<>(Arrays.asList(player1, player2)), playerFieldSize);

        this.matchLogic = matchLogic;
        this.autoDraw = autoDraw;
        this.winner = Optional.empty();
    }

    @Override
    public void drawCard(PlayerEnum player) throws CardCollectionFullException {
        try {
            matchState.getPlayers().get(player.getIndex()).drawCard();
        } catch (CardCollectionFullException e) {
            throw new CardCollectionFullException("Hand is full!");
        }
    }

    @Override
    public PlayerEnum getTurnPlayer() {
        return matchLogic.getCurrentPlayer();
    }

    @Override
    public void changeTurn() {
        this.matchLogic.changeTurn();
        if (autoDraw) {
            try {
                this.drawCard(this.getTurnPlayer());
            } catch (CardCollectionFullException e) {
            }
        }
    }

    @Override
    public void start() {
        if (this.status != MatchStatus.CREATED) {
            throw new IllegalStateException("The match has already started.");
        }

        this.status = MatchStatus.RUNNING;
    }

    @Override
    public Optional<Player> getWinner() {
        return this.winner;
    }

    @Override
    public void endMatch(Player player) {
        if (this.status != MatchStatus.RUNNING) {
            throw new IllegalStateException("A winner can only be set while the match is running.");
        }

        if (!matchState.getPlayers().contains(player)) {
            throw new IllegalArgumentException("The winner must be a player of this match.");
        }

        this.winner = Optional.of(player);
        this.status = MatchStatus.FINISHED;
    }

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
