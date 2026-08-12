package it.unibo.cardhub.io.api;

import java.util.List;

import it.unibo.cardhub.model.domain.api.Card;

public interface CardLoader<T> {

    List<Card<T>> load();
}
