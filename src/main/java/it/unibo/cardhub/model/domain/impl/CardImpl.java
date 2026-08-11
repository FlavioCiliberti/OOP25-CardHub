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
 */
public record CardImpl<T>(String id, T attributes, int value, Optional<String> desc) implements Card<T> {

    public static <T> Card<T> of(final String id, final T attributes, final int value) {
        return new CardImpl<>(id, attributes, value, Optional.empty());
    }
}
