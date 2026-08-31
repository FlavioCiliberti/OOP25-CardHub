package it.unibo.cardhub.model.logic.api;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Handles the match logic.
 */
public interface MatchLogic {
    /**
     * Compares two Cards and proceeds with the corresponding actions.
     * 
     * @param firstPlayerCard the card of player1 to be compared
     * @param secondPlayerCard the card of player2 to be compared
     * @return the result of the comparison
     * @throws CardCollectionFullException if a player's hand is full
     */
    ComparisonWinner compareCard(Card<?> firstPlayerCard, Card<?> secondPlayerCard) throws CardCollectionFullException;

    /**
     * Returns the player of the current turn.
     * 
     * @return the player of the current turn.
     */
    PlayerEnum getCurrentPlayer();

    /**
     * Passes to the next turn and to the next player.
     * @throws CardCollectionFullException if the player's hand is full
     */
    void changeTurn() throws CardCollectionFullException;

    /**
     * Returns the winner of card action.
     * 
     * @return the action that must be done with the winner card
     */
    CardAction getWinnerCardAction();

    /**
     * Returns the loser card action.
     * 
     * @return the action that must be done with the loser's card
     */
    CardAction getLoserCardAction();
}
