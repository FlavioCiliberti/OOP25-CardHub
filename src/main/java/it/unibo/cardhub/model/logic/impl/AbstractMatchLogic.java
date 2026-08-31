package it.unibo.cardhub.model.logic.impl;

import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
<<<<<<< HEAD
=======
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.exceptions.EmptyCardCollectionException;
>>>>>>> development
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.api.MatchLogic;

/**
 * represents an abstract implementation of MatchLogic.
 */
public abstract class AbstractMatchLogic implements MatchLogic {
    private final boolean autoDraw;
    private final CardAction winnerCardAction;
    private final CardAction loserCardAction;

    private PlayerEnum currentPlayer;

    /**
     * Constructor for AbstractMatchLogic.
     * 
     * @param winnerCardAction the card action of the winner card
     * @param loserCardAction the card action of the loser card
     * @param autoDraw whether the player draws on turn start
     */
    protected AbstractMatchLogic(final CardAction winnerCardAction, final CardAction loserCardAction, final boolean autoDraw) {
        this.autoDraw = autoDraw;
        this.winnerCardAction = Objects.requireNonNull(winnerCardAction, "missing cardAction");
        this.loserCardAction = Objects.requireNonNull(loserCardAction, "missing cardAction");

        this.currentPlayer = PlayerEnum.PLAYER_ONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract ComparisonWinner compareCard(Card<?> firstPlayerCard, Card<?> secondPlayerCard, MatchState state);

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
    public CardAction getLoserCardAction() {
        return loserCardAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAutoDrawEnabled() {
        return autoDraw;
    }
}
