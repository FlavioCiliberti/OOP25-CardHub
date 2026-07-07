package it.unibo.cardhub.model.domain.api;

import java.util.List;

/**
 * Represents a playfield in a match.
 */
public interface Playfield {

    /**
     * Adds a card from the player's hand to the table.
     * 
     * @param player that plays the card
     * @param card to be put on the table
     */
    void addCard(Player player, Card card);

    /**
     * Removes a card from the table.
     * 
     * @param card to be removed
     */
    void removeCard(Card card);

    /**
     * A getter for the player's cards.
     * 
     * @param player having cards of interest
     * @return a list of cards
     */
    List<Card> getCards(Player player);

    /**
     * Removes all cards from the table.
     */
    void removeAll();

    /**
     * A getter for all the cards present on the table.
     * 
     * @return a list of all the cards
     */
    List<Card> getAllCards();
}
