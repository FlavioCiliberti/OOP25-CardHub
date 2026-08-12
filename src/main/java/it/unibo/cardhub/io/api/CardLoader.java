package it.unibo.cardhub.io.api;

import java.util.List;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * Interface that defines the contract for loading cards from a data source.
 *
 * @param <T> the type of the card content
 */
@FunctionalInterface
public interface CardLoader<T> {

    List<Card<T>> load();
}
