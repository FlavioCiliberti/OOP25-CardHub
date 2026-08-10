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
    public ComparisonWinner compareCard(Card firstPlayerCard, Card secondPlayerCard, MatchState matchState);

    /**
     * getter for the turn player.
     * 
     * @return the turn player.
     */
    public PlayerEnum getCurrentPlayer();

    /**
     * changes the turn player.
     */
    public void changeTurn();
}
