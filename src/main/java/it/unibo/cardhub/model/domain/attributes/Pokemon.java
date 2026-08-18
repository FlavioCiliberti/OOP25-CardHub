package it.unibo.cardhub.model.domain.attributes;

/**
 * Record representing a Pokemon card with its type and rarity.
 * 
 * @param type the type of the Pokemon card
 * @param rarity the rarity of the Pokemon card
 */
public record Pokemon(String type, String rarity) { }
