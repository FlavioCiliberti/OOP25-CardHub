package it.unibo.cardhub.model.logic.api;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;

/**
 * handles the match logic.
 */
public interface MatchLogic {
    /**
     * compares two Cards and proceeds with the corresponding actions.
     * 
     * @param firstPlayerCard the card of player1 to be compared
     * @param secondPlayerCard the card of player2 to be compared
     * @param matchState the matchState to be updated with the actions
     * @return the result of the comparison
     */
    ComparisonWinner compareCard(Card<?> firstPlayerCard, Card<?> secondPlayerCard, MatchState matchState);

    /**
     * getter for the turn player.
     * 
     * @return the turn player.
     */
    PlayerEnum getCurrentPlayer();

    /**
     * getter for the player 1.
     * 
     * @return player 1.
     */
    Player getPlayerOne();

    /**
     * getter for the player 2.
     * 
     * @return player 2.
     */
    Player getPlayerTwo();

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
    CardAction getLooserCardAction();
}
