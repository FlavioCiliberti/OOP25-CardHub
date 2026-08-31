package it.unibo.cardhub.model.logic.impl;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
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
     * @throws CardCollectionFullException 
     */
    @Override
    public ComparisonWinner compareCard(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard) throws CardCollectionFullException {

        if (firstPlayerCard.value() > secondPlayerCard.value()) {
            // Player1 winner action
            try {
                this.executeCardAction(
                    firstPlayerCard, 
                    PlayerEnum.PLAYER_ONE,
                    super.getWinnerCardAction(), 
                    super.getMatchState()
                );
            } catch (CardCollectionFullException e) {
                throw new CardCollectionFullException("Tried to add a card to a full hand.");
            }
            // Player2 loser action
            try {
                this.executeCardAction(
                    secondPlayerCard, 
                    PlayerEnum.PLAYER_TWO,
                    super.getLoserCardAction(), 
                    super.getMatchState()
                );
            } catch (CardCollectionFullException e) {
                throw new CardCollectionFullException("Tried to add a card to a full hand.");
            }
            return ComparisonWinner.PLAYER_1;
        } else if (firstPlayerCard.value() < secondPlayerCard.value()) {
            // Player1 loser action
            try {
                this.executeCardAction(
                    firstPlayerCard, 
                    PlayerEnum.PLAYER_ONE,
                    super.getLoserCardAction(), 
                    super.getMatchState()
                );
            } catch (CardCollectionFullException e) {
                throw new CardCollectionFullException("Tried to add a card to a full hand.");
            }
            // Player2 winner action
            try {
                this.executeCardAction(
                    secondPlayerCard, 
                    PlayerEnum.PLAYER_TWO,
                    super.getWinnerCardAction(), 
                    super.getMatchState()
                );
            } catch (CardCollectionFullException e) {
                throw new CardCollectionFullException("Tried to add a card to a full hand.");
            }
            return ComparisonWinner.PLAYER_2;
        }
        return ComparisonWinner.TIE;
    }

    private void executeCardAction(final Card<?> card, final PlayerEnum player,
                                    final CardAction action, final MatchState matchState) throws CardCollectionFullException {

        if (action == CardAction.TO_PILE) {
            matchState.getPlayfield().removeCard(card);
            matchState.getPlayer(player).putInPile(card);
        } else if (action == CardAction.TO_HAND) {
            matchState.getPlayfield().removeCard(card);
            try {
                matchState.getPlayer(player).getHand().addCard(card);
            } catch (CardCollectionFullException e) {
                throw new CardCollectionFullException("Tried to add a card to a full hand.");
            }
        }
    }

}
