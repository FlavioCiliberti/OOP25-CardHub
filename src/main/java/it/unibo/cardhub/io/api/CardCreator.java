package it.unibo.cardhub.io.api;

import java.util.Map;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * Functional interface for creating card instances from loaded data.
 *
 * @param <T> the type of the card content
 */
@FunctionalInterface
public interface CardCreator<T> {

    /**
     * Creates a card instance from the provided card data.
     *
     * @param cardData the data representing the card
     * @return a Card instance
     */
    Card<T> createCard(Map<String, Object> cardData);
}
