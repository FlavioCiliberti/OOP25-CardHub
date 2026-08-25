package it.unibo.cardhub.model.logic.impl;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;

class MatchLogicImpl extends AbstractMatchLogic {

    MatchLogicImpl(final CardAction winnerCardAction, final CardAction loserCardAction) {
        super(winnerCardAction, loserCardAction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComparisonWinner compareCard(final Card<?> firstPlayerCard, 
                                        final Card<?> secondPlayerCard, 
                                        final MatchState matchState) {

        if (firstPlayerCard.value() > secondPlayerCard.value()) {
            //player1 winner action
            this.executeCardAction(firstPlayerCard, PlayerEnum.PLAYER_ONE, super.getWinnerCardAction(), matchState);
            //player2 loser action
            this.executeCardAction(secondPlayerCard, PlayerEnum.PLAYER_TWO, super.getLooserCardAction(), matchState);
            return ComparisonWinner.PLAYER_1;
        } else if (firstPlayerCard.value() < secondPlayerCard.value()) {
            //player2 winner action
            this.executeCardAction(firstPlayerCard, PlayerEnum.PLAYER_TWO, super.getWinnerCardAction(), matchState);
            //player1 loser action
            this.executeCardAction(secondPlayerCard, PlayerEnum.PLAYER_ONE, super.getLooserCardAction(), matchState);
            return ComparisonWinner.PLAYER_2;
        }
        return ComparisonWinner.TIE;
    }

    private void executeCardAction(final Card<?> card, final PlayerEnum player,
                                    final CardAction action, final MatchState matchState) {

        if (action == CardAction.TO_PILE) {
            matchState.getPlayfield().removeCard(card);
            matchState.getPlayer(player).putInPile(card);
        } else if (action == CardAction.TO_HAND) {
            matchState.getPlayfield().removeCard(card);
            matchState.getPlayer(player).getHand().addCard(card);
        }
    }

}
