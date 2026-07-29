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
     * Returns the name of the card.
     * 
     * @return name of the card
     */
    String name();

    /**
     * A getter for the card's value.
     * 
     * @return the cards value
     */
    int value();

    /**
     * A getter for the card's description.
     * 
     * @return the cards's description
     */
    String desc();

    /**
     * Returns image path.
     * 
     * @return image path
     */
    String imagePath();
}
