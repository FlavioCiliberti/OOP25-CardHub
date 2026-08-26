package it.unibo.cardhub.model.domain.api;

import java.util.Optional;

/**
 * Represents a card.
 * 
 * @param <T> the type of the card's attribute
 */
public interface Card<T> {

    /**
     * A getter for the card's ID.
     * 
     * @return the card's ID
     */
    String id();

    /**
     * Returns card's name.
     * 
     * @return card's name
     */
    Optional<String> name();

    /**
     * Returns the card's attribute.
     * 
     * @return the card's attribute
     */
    T attributes();

    /**
     * Returns the card's value.
     * 
     * @return the card's value
     */
    int value();

    /**
     * A getter for the card's description.
     * 
     * @return the cards's description
     */
    Optional<String> desc();

    /**
     * Returns the image file name.
     * 
     * @return image file name
     */
    String image();
}
