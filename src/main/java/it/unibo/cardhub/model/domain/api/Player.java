package it.unibo.cardhub.model.domain.api;

import java.util.Optional;

import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Represents a player in a match.
 */
public interface Player {

    /**
     * Draws a card from the player's deck.
     * 
     * @return the drawn card
     * @throws CardCollectionFullException if the player's hand is full
     */
    Card<?> drawCard() throws CardCollectionFullException;

    /**
     * Puts a card onto the table from the player's hand.
     * 
     * @param card to be played
     * @return the card
     */
    Card<?> playCard(Card<?> card);

    /**
     * Puts a card in the player's discard pile.
     * 
     * @param card the card to be put in the discard pile
     */
    void putInPile(Card<?> card);

    /**
     * Puts a card in the player's deck.
     * 
     * @param card the card to be put in the deck
     */
    void putInDeck(Card<?> card);

    /**
     * Shuffles the deck.
     */
    void shuffleDeck();

    /**
     * Gets the card on top of the discard pile without taking it out.
     * 
     * @return an optional of the card on the top of the pile
     */
    Optional<Card<?>> peekDiscardPile();

    /**
     * Shuffles the player's discard pile into the deck.
     */
    void shufflePileIntoDeck();

    /**
     * Returns the player's name.
     * 
     * @return the player's name
     */
    String getName();

    /**
     * Returns the player's hand.
     * 
     * @return the player's hand
     */
    Hand getHand();

    /**
     * Checks if the player's deck is empty.
     * 
     * @return {@code true} if the deck is empty
     */
    boolean hasEmptyDeck();

    /**
     * Checks if the player's discard pile is empty.
     * 
     * @return {@code true} if the discard pile is empty
     */
    boolean hasEmptyDiscardPile();

    /**
     * Returns the current size of the deck.
     * 
     * @return deck size
     */
    int getDeckCount();
}
