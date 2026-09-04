package it.unibo.cardhub.model.domain.api;

import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Represents the hand of a player.
 */
public interface Hand extends CardCollection {

    /**
     * Adds a card to the hand.
     * 
     * @param card to be added
     * @throws CardCollectionFullException if the hand is full
     */
    void addCard(Card<?> card) throws CardCollectionFullException;

    /**
     * Returns the maximum amount of cards per hand.
     * 
     * @return the maximum amount
     */
    int getMaxSize();

    /**
     * Puts a card for a player's hand to the table.
     * 
     * @param card to be played
     * @return the card
     */
    Card<?> playCard(Card<?> card);

    /**
     * Informs either the hand is full.
     * 
     * @return true if it's full, false otherwise
     */
    boolean isFull();
}
