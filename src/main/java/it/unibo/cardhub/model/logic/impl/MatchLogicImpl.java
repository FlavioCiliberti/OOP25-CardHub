package it.unibo.cardhub.model.logic.impl;

import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Hand;
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
     */
    @Override
    public ComparisonWinner compareCard(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard) throws CardCollectionFullException {
        Objects.requireNonNull(firstPlayerCard);
        Objects.requireNonNull(secondPlayerCard);

        if (firstPlayerCard.value() > secondPlayerCard.value()) {
            // Player1 winner action
                this.executeCardAction(
                    firstPlayerCard, 
                    PlayerEnum.PLAYER_ONE,
                    super.getWinnerCardAction(), 
                    super.getMatchState()
                );
            // Player2 loser action
                this.executeCardAction(
                    secondPlayerCard, 
                    PlayerEnum.PLAYER_TWO,
                    super.getLoserCardAction(), 
                    super.getMatchState()
                );
            return ComparisonWinner.PLAYER_1;
        } else if (firstPlayerCard.value() < secondPlayerCard.value()) {
            // Player1 loser action
                this.executeCardAction(
                    firstPlayerCard, 
                    PlayerEnum.PLAYER_ONE,
                    super.getLoserCardAction(), 
                    super.getMatchState()
                );
            // Player2 winner action
                this.executeCardAction(
                    secondPlayerCard, 
                    PlayerEnum.PLAYER_TWO,
                    super.getWinnerCardAction(), 
                    super.getMatchState()
                );
            return ComparisonWinner.PLAYER_2;
        }
        return ComparisonWinner.TIE;
    }

    private void executeCardAction(final Card<?> card, final PlayerEnum player,
                                    final CardAction action, final MatchState matchState) throws CardCollectionFullException {

        if (action == CardAction.TO_PILE) {
            matchState.removeCardFromField(card);
            matchState.getPlayer(player).putInPile(card);
        } else if (action == CardAction.TO_HAND) {
            final Hand hand = matchState.getPlayer(player).getHand();

            if (hand.size() >= hand.getMaxSize()) {
                throw new CardCollectionFullException("Tried to add a card to a full hand.");
            }

            matchState.removeCardFromField(card);
            hand.addCard(card);
        }
    }
}
