package it.unibo.cardhub.model.logic.api;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;

/**
 * handles the match logic.
 */
public interface MatchLogic {
    /**
     * compares two Cards and proceeds with the corresponding actions.
     * 
     * @param firstPlayerCard the card of player1 to be compared
     * @param secondPlayerCard the card of player2 to be compared
     * @return the result of the comparison
     */
    ComparisonWinner compareCard(Card<?> firstPlayerCard, Card<?> secondPlayerCard);

    /**
     * getter for the turn player.
     * 
     * @return the turn player.
     */
    PlayerEnum getCurrentPlayer();

    /**
     * changes the turn player.
     */
    void changeTurn();

    /**
     * getter for the winner card action.
     * 
     * @return the action that must be done with the winner card
     */
    CardAction getWinnerCardAction();

    /**
     * getter for the looser card action.
     * 
     * @return the action that must be done with the winner card
     */
    CardAction getLoserCardAction();
}
