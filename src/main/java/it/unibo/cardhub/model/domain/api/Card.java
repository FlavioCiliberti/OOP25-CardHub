package it.unibo.cardhub.model.domain.api;

/**
 * Represents a card.
 */
public interface Card {

    /**
     * A getter for the card's ID.
     * 
     * @return the card's ID
     */
    String id();

    /**
     * A getter for the card's description.
     * 
     * @return the cards's description
     */
    String desc();

    /**
     * A getter for the card's image path.
     * 
     * @return the card's image path
     */
    String imagePath();

    /**
     * A getter for the card's value.
     * 
     * @return the cards value
     */
    int value();
}
