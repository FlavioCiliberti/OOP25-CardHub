package it.unibo.cardhub.model.logic.impl;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.model.logic.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;

class MatchLogicImpl implements MatchLogic{
    private PlayerEnum currentPlayer;
    private final CardAction winnerCardAction;
    private final CardAction loserCardAction;

    public MatchLogicImpl(CardAction winnerCardAction, CardAction loserCardAction) {
        this.winnerCardAction = winnerCardAction;
        this.loserCardAction = loserCardAction;

        currentPlayer = PlayerEnum.PLAYER_ONE;
    }

    @Override
    public ComparisonWinner compareCard(Card firstPlayerCard, Card secondPlayerCard, MatchState matchState) {
        if (firstPlayerCard.value() > secondPlayerCard.value()) {
            //player1 winner action
            this.executeCardAction(firstPlayerCard, PlayerEnum.PLAYER_ONE, winnerCardAction, matchState);
            //player2 loser action
            this.executeCardAction(secondPlayerCard, PlayerEnum.PLAYER_TWO, loserCardAction, matchState);
            return ComparisonWinner.PLAYER_1;
        } else if (firstPlayerCard.value() < secondPlayerCard.value()) {
            //player2 winner action
            this.executeCardAction(firstPlayerCard, PlayerEnum.PLAYER_TWO, winnerCardAction, matchState);
            //player1 loser action
            this.executeCardAction(secondPlayerCard, PlayerEnum.PLAYER_ONE, loserCardAction, matchState);
            return ComparisonWinner.PLAYER_2;
        }
        return ComparisonWinner.TIE;
    }

    @Override
    public PlayerEnum getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void changeTurn() {
        if (currentPlayer == PlayerEnum.PLAYER_ONE) {
            currentPlayer = PlayerEnum.PLAYER_TWO;
        } else {
            currentPlayer = PlayerEnum.PLAYER_ONE;
        }
    }

    private void executeCardAction(Card Card, PlayerEnum player, CardAction action, MatchState matchState) {
        if (action == CardAction.TO_PILE) {
            matchState.getPlayfield().removeCard(Card);
            matchState.getPlayer(player).putInPile(Card);
        } else if (action == CardAction.TO_HAND) {
            matchState.getPlayfield().removeCard(Card);
            matchState.getPlayer(player).getHand().addCard(Card);
        }
    }
    
}
