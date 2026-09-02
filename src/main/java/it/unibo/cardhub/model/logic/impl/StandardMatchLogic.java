package it.unibo.cardhub.model.logic.impl;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;

/**
 * MatchLogic for a Free or Custom match.
 */
public class StandardMatchLogic extends AbstractMatchLogic {

    /**
     * Constructor for MatchLogicImpl.
     * 
     * @param winnerCardAction winner card action
     * @param loserCardAction loser card action
     * @param autoDraw whether the player draws on turn start
     */
    public StandardMatchLogic(final CardAction winnerCardAction, final CardAction loserCardAction, final boolean autoDraw) {
        super(winnerCardAction, loserCardAction, autoDraw);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComparisonWinner compareCard(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard,
                                        final MatchState state) {

        if (firstPlayerCard.value() > secondPlayerCard.value()) {
            // Player1 winner action
            this.executeCardAction(
                firstPlayerCard, 
                PlayerEnum.PLAYER_ONE,
                super.getWinnerCardAction(), 
                state
            );
            // Player2 loser action
            this.executeCardAction(
                secondPlayerCard, 
                PlayerEnum.PLAYER_TWO,
                super.getLoserCardAction(), 
                state
            );
            return ComparisonWinner.PLAYER_1;
        } else if (firstPlayerCard.value() < secondPlayerCard.value()) {
            // Player1 loser action
            this.executeCardAction(
                firstPlayerCard, 
                PlayerEnum.PLAYER_ONE,
                super.getLoserCardAction(), 
                state
            );
            // Player2 winner action
            this.executeCardAction(
                secondPlayerCard, 
                PlayerEnum.PLAYER_TWO,
                super.getWinnerCardAction(), 
                state
            );
            return ComparisonWinner.PLAYER_2;
        }
        return ComparisonWinner.TIE;
    }

    private void executeCardAction(final Card<?> card, final PlayerEnum player,
                                    final CardAction action, final MatchState matchState) {

        if (action == CardAction.TO_PILE) {
            matchState.moveCardFromFieldToPile(card, player);
        } else if (action == CardAction.TO_DECK) {
            matchState.moveCardFromFieldToDeck(card, player);
        }
    }

}
