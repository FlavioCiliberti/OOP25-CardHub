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
    String getId();

    /**
     * A getter for the card's description.
     * 
     * @return the cards's description
     */
    String getDescription();

    /**
     * A getter for the card's image path.
     * 
     * @return the card's image path
     */
    String getImagePath();

    /**
     * A getter for the card's value.
     * 
     * @return the cards value
     */
    int getValue();
}
