package it.unibo.cardhub.model.logic.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;

class MatchLogicImpl implements MatchLogic {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final CardAction winnerCardAction;
    private final CardAction loserCardAction;

    MatchLogicImpl(final Player player1, final Player player2,
                    final CardAction winnerCardAction, final CardAction loserCardAction) {

        this.player1 = player1;
        this.player2 = player2;
        this.winnerCardAction = winnerCardAction;
        this.loserCardAction = loserCardAction;

        currentPlayer = player1;
    }

    @Override
    public ComparisonWinner compareCard(final Card<?> firstPlayerCard, 
        final Card<?> secondPlayerCard, 
            final MatchState matchState) {

        if (firstPlayerCard.value() > secondPlayerCard.value()) {
            //player1 winner action
            this.executeCardAction(firstPlayerCard, player1, winnerCardAction, matchState);
            //player2 loser action
            this.executeCardAction(secondPlayerCard, player2, loserCardAction, matchState);
            return ComparisonWinner.PLAYER_1;
        } else if (firstPlayerCard.value() < secondPlayerCard.value()) {
            //player2 winner action
            this.executeCardAction(firstPlayerCard, player2, winnerCardAction, matchState);
            //player1 loser action
            this.executeCardAction(secondPlayerCard, player1, loserCardAction, matchState);
            return ComparisonWinner.PLAYER_2;
        }
        return ComparisonWinner.TIE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
                        justification = "temporary for development purposes"
    )
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
                        justification = "temporary for development purposes"
    )
    public void changeTurn() {
        if (currentPlayer.equals(player1)) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private void executeCardAction(final Card<?> card, final Player player,
                                    final CardAction action, final MatchState matchState) {

        if (action == CardAction.TO_PILE) {
            matchState.getPlayfield().removeCard(card);
            player.putInPile(card);
        } else if (action == CardAction.TO_HAND) {
            matchState.getPlayfield().removeCard(card);
            player.getHand().addCard(card);
        }
    }

    @Override
    public Player getPlayerOne() {
        return player1;
    }

    @Override
    public Player getPlayerTwo() {
        return player2;
    }

}
