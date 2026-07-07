package it.unibo.cardhub.model.domain.api;

/**
 * Represents a player in a match.
 */
public interface Player {

    /**
     * Draws a card from the player's deck.
     * 
     * @return the drawn card
     */
    Card drawCard();

    /**
     * Puts a card onto the table from the player's hand.
     * 
     * @param card to be played
     */
    void playCard(Card card);

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
     * A getter for the player's deck.
     * 
     * @return the player's deck
     */
    Deck getDeck();
}
