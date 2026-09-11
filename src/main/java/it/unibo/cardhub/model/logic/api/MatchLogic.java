package it.unibo.cardhub.model.logic.api;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;

/**
 * Handles the match logic.
 */
public interface MatchLogic {
    /**
     * Compares two Cards and proceeds with the corresponding actions.
     * 
     * @param firstPlayerCard the card of player1 to be compared
     * @param secondPlayerCard the card of player2 to be compared
     * @param state the match state
     * @return the result of the comparison
     */
    ComparisonWinner compareCard(Card<?> firstPlayerCard, Card<?> secondPlayerCard, MatchState state);

    /**
     * Returns the player of the current turn.
     * 
     * @return the player of the current turn.
     */
    PlayerEnum getCurrentPlayer();

    /**
     * Passes to the next turn and to the next player.
     */
    void changeTurn();

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

    /**
     * Return whether the turn player should automatically draw a card on turn start.
     * 
     * @return whether the player should autodraw
     */
    boolean shouldAutoDraw();
}
