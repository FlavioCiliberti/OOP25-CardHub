package it.unibo.cardhub.model.logic.impl;

import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.logic.api.Match;

/**
 * a factory for match.
 */
public class MatchFactory {

    private MatchFactory() {

    }

    public static Match createFreeMatch(String firstPlayerName, String secondPlayerName,
                                        Deck firstPlayerDeck, Deck secondPlayerDeck) {
        return null;
    }

    public static Match createCustomMatch(String firstPlayerName, String secondPlayerName,
                                            Deck firstPlayerDeck, Deck secondPlayerDeck) {
        return null;
    }

    public static Match createFullGame(String firstPlayerName, String secondPlayerName) {
        return null;
    }
}
