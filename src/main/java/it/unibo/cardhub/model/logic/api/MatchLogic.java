package it.unibo.cardhub.model.logic.api;

import java.util.List;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * handles the match logic.
 */
public interface MatchLogic {
    /**
     * compares two Cards.
     * 
     * @param firstPlayerCard the card of player1 to be compared
     * @param secondPlayerCard the card of player2 to be compared
     * @return a list of cards with the winner card as the first item and the loser card as the second card
     */
    public List<Card> compareCard(Card firstPlayerCard, Card secondPlayerCard);

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
