package it.unibo.cardhub.model.domain.impl;

import java.util.Optional;

import lombok.Builder;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * Card implementation.
 * 
 * @param id card ID
 * @param name card name
 * @param attributes card attribute
 * @param value card value
 * @param desc card description
 * @param image image file name
 * @param <T> card attribute type
 */
@Builder
public record CardImpl<T>(
    String id, 
    Optional<String> name, 
    T attributes, 
    int value, 
    Optional<String> desc, 
    String image
) implements Card<T> {
}
