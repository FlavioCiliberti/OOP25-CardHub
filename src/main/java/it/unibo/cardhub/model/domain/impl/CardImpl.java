package it.unibo.cardhub.model.domain.impl;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * Card implementation.
 * 
 * @param id card ID
 * @param name card name
 * @param value card value
 * @param desc card description
 * @param imagePath card image path
 */
public record CardImpl(String id, String name, int value, String desc, String imagePath) implements Card {

}
