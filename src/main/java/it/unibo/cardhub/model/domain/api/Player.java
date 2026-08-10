package it.unibo.cardhub.model.domain.api;

import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Represents a player in a match.
 */
public interface Player {

    /**
     * Draws a card from the player's deck.
     * 
     * @return the drawn card
     * @throws CardCollectionFullException if the hand is full
     */
    Card drawCard() throws CardCollectionFullException;

    /**
     * Puts a card onto the table from the player's hand.
     * 
     * @param card to be played
     * @return the card
     */
    Card playCard(Card card);

    /**
     * Puts a card in the player's discard pile.
     * 
     * @param card the card to be put in the discard pile
     */
    void putInPile(Card card);

    /**
     * A getter for the player's name.
     * 
     * @return the player's name
     */
    String getName();

    /**
     * A getter for the player's hand.
     * 
     * @return the player's hand
     */
    Hand getHand();
}
