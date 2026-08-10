package it.unibo.cardhub.model.logic.impl;

import java.util.List;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.logic.api.LoserCardAction;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.model.logic.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.WinnerCardAction;

class MatchLogicImpl implements MatchLogic{
    private final Player player1;
    private final Player player2;
    private PlayerEnum currentPlayer;
    private final WinnerCardAction winnerCardAction;
    private final LoserCardAction loserCardAction;

    public MatchLogicImpl(Player player1, Player player2,
                            WinnerCardAction winnerCardAction, LoserCardAction loserCardAction) {
        this.player1 = player1;
        this.player2 = player2;
        this.winnerCardAction = winnerCardAction;
        this.loserCardAction = loserCardAction;

        currentPlayer = PlayerEnum.PLAYER_ONE;
    }

    @Override
    public List<Card> compareCard(Card firstPlayerCard, Card secondPlayerCard) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareCard'");
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
    
}
