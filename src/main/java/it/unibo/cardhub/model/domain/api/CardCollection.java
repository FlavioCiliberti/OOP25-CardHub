package it.unibo.cardhub.model.domain.api;

import java.util.List;

/**
 * Represents an abstract card collection.
 */
public interface CardCollection {

    /**
     * Adds a card to another card collection.
     * 
     * @param card to be added
     */
    void addCard(Card<?> card);

    /**
     * Returns a copy of all the cards of the collection.
     * 
     * @return a list of all the cards
     */
    List<Card<?>> getCards();

    /**
     * Gets the current size of the collection.
     * 
     * @return the size
     */
    int size();

    /**
     * Informs either the collection is empty.
     * 
     * @return true if it's empty, false otherwise
     */
    boolean isEmpty();
}
