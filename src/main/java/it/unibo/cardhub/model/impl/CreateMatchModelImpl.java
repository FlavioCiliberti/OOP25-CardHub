package it.unibo.cardhub.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.cardhub.model.api.CreateMatchModel;
import it.unibo.cardhub.model.domain.DeckEnum;

/**
 * Default implementation of {@link CreateMatchModel}, exposing fixed domain constraints.
 */
public final class CreateMatchModelImpl implements CreateMatchModel {
    private static final int MIN_HAND_SIZE = 1;
    private static final int MAX_HAND_SIZE = 7;
    private static final int MIN_FIELD_SIZE = 1;
    private static final int MAX_FIELD_SIZE = 6;
    private static final int HAND_DEFAULT_VALUE = 4;
    private static final int FIELD_DEFAULT_VALUE = 3;

    @Override
    public int getMinHandSize() {
        return MIN_HAND_SIZE;
    }

    @Override
    public int getMaxHandSize() {
        return MAX_HAND_SIZE;
    }

    @Override
    public int getMinFieldSize() {
        return MIN_FIELD_SIZE;
    }

    @Override
    public int getMaxFieldSize() {
        return MAX_FIELD_SIZE;
    }

    @Override
    public int getDefaultHandSize() {
        return HAND_DEFAULT_VALUE;
    }

    @Override
    public int getDefaultFieldSize() {
        return FIELD_DEFAULT_VALUE;
    }

    @Override
    public Map<Integer, String> getDecks() {
        final Map<Integer, String> decks = new HashMap<>();

        for (final DeckEnum deck : DeckEnum.values()) {
            decks.put(deck.getId(), deck.getDisplayName());
        }

        return decks;
    }
}
