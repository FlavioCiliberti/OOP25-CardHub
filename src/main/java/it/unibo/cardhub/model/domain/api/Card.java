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

    /**
     * Record representing a Yu-Gi-Oh card with its type and race.
     * 
     * @param type the type of the Yu-Gi-Oh card
     * @param race the race of the Yu-Gi-Oh card
     */
    record YuGiOh(String type, String race) { }

    /**
     * Record representing a Pokemon card with its type and rarity.
     * 
     * @param type the type of the Pokemon card
     * @param rarity the rarity of the Pokemon card
     */
    record Pokemon(String type, String rarity) { }

    /**
     * Record representing a Dragon Ball card with its type and rarity.
     * 
     * @param type the type of the Dragon Ball card
     * @param rarity the rarity of the Dragon Ball card
     */
    record DragonBall(String type, String rarity) { }

    /**
     * Enum representing card suits.
     */
    enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
}
