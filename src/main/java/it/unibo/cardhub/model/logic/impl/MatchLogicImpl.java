package it.unibo.cardhub.model.logic.impl;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;

/**
 * MatchLogic for a Free or Custom match.
 */
public class MatchLogicImpl extends AbstractMatchLogic {

    /**
     * Constructor for MatchLogicImpl.
     * 
     * @param winnerCardAction winner card action
     * @param loserCardAction loser card action
     * @param autoDraw whether the player draws on turn start
     * @param matchState the match state
     */
    public MatchLogicImpl(final CardAction winnerCardAction, final CardAction loserCardAction,
                    final boolean autoDraw, final MatchState matchState) {
        super(winnerCardAction, loserCardAction, autoDraw, matchState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComparisonWinner compareCard(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard) {

        if (firstPlayerCard.value() > secondPlayerCard.value()) {
            //player1 winner action
            this.executeCardAction(firstPlayerCard, PlayerEnum.PLAYER_ONE,
                                    super.getWinnerCardAction(), super.getMatchState());
            //player2 loser action
            this.executeCardAction(secondPlayerCard, PlayerEnum.PLAYER_TWO,
                                    super.getLoserCardAction(), super.getMatchState());
            return ComparisonWinner.PLAYER_1;
        } else if (firstPlayerCard.value() < secondPlayerCard.value()) {
            //player2 winner action
            this.executeCardAction(firstPlayerCard, PlayerEnum.PLAYER_TWO,
                                    super.getWinnerCardAction(), super.getMatchState());
            //player1 loser action
            this.executeCardAction(secondPlayerCard, PlayerEnum.PLAYER_ONE,
                                    super.getLoserCardAction(), super.getMatchState());
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
