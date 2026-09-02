package it.unibo.cardhub.model.domain.api;

import java.util.Optional;

/**
 * Represents a player in a match.
 */
public interface Player {

    /**
     * Draws a card from the player's deck.
     * 
     * @return the drawn card
     */
    Card<?> drawCard();

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
     * Gets the current size of the deck.
     * 
     * @return deck size
     */
    int getDeckCount();
}
