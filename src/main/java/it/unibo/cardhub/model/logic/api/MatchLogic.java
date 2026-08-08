package it.unibo.cardhub.model.logic.api;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.exceptions.EmptyCardCollectionException;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * handles the match logic.
 */
public interface MatchLogic {
    /**
     * compares two Cards.
     * 
     * @param firstPlayerCard the card of player1 to be compared
     * @param secondPlayerCard the card of player2 to be compared
     * @return the card with the higher value between the two
     */
    public Card compareCard(Card firstPlayerCard, Card secondPlayerCard);

    /**
     * Makes a player draw a card.
     * 
     * @param player the player to draw the card
     * @return the drawn card
     * @throws CardCollectionFullException if the player's hand is full
     * @throws EmptyCardCollectionException if the player's deck is empty
     */
    public Card drawCard(Player player) throws CardCollectionFullException, EmptyCardCollectionException;

    /**
     * Makes a player play a card.
     * 
     * @param player the player to play the card
     * @param card the card to be played
     * @return the played card
     * @throws CardCollectionFullException if the player's field is full
     */
    public Card playCard(Player player, Card card) throws CardCollectionFullException;
}
