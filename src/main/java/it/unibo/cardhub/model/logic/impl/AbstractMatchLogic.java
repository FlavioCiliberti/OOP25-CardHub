package it.unibo.cardhub.model.logic.impl;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.model.logic.api.PlayerEnum;

/**
 * represents an abstract implementation of MatchLogic.
 */
public abstract class AbstractMatchLogic implements MatchLogic {
    /**
     * The current turn player.
     */
    private PlayerEnum currentPlayer;

    private final CardAction winnerCardAction;
    private final CardAction loserCardAction;

    /**
     * Constructor for AbstractMatchLogic.
     * 
     * @param winnerCardAction the card action of the winner card
     * @param loserCardAction the card action of the loser card
     */
    protected AbstractMatchLogic(final CardAction winnerCardAction, final CardAction loserCardAction) {
        currentPlayer = PlayerEnum.PLAYER_ONE;

        this.winnerCardAction = winnerCardAction;
        this.loserCardAction = loserCardAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract ComparisonWinner compareCard(Card<?> firstPlayerCard, 
                                                    Card<?> secondPlayerCard, 
                                                    MatchState matchState);

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerEnum getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeTurn() {
        if (currentPlayer == PlayerEnum.PLAYER_ONE) {
            currentPlayer = PlayerEnum.PLAYER_TWO;
        } else {
            currentPlayer = PlayerEnum.PLAYER_ONE;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardAction getWinnerCardAction() {
        return winnerCardAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardAction getLooserCardAction() {
        return loserCardAction;
    }
}
