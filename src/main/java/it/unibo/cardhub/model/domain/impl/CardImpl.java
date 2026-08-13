package it.unibo.cardhub.model.domain.impl;

import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * Card implementation.
 * 
 * @param id card ID
 * @param attributes card attribute
 * @param value card value
 * @param desc card description
 * @param <T> card attribute type
 */
public record CardImpl<T>(String id, T attributes, int value, Optional<String> desc, String image) implements Card<T> {

    /**
     * Creates a new card without a description.
     * 
     * @param id         the card's ID
     * @param attributes the card's attribute
     * @param value      the card's value
     * @return a new card with the given ID, attribute and value, and without a description
     */
    public static <T> Card<T> of(final String id, final T attributes, final int value, final String image) {
        return new CardImpl<>(id, attributes, value, Optional.empty(), image);
    }
}
