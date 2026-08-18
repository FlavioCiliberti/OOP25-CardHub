package it.unibo.cardhub.model.logic.impl;

import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.impl.PlayerImpl;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.Match;

/**
 * a factory for Match.
 */
public final class MatchFactory {
    private static final int DEFAULT_MAX_HAND_SIZE = 5;
    private static final int DEFAULT_STARTING_HAND_SIZE = 5;
    private static final int DEFAULT_PLAYFIELD_SIZE = 3;

    private MatchFactory() {

    }

    /**
     * Creates a Free Match.
     * 
     * @param firstPlayerName player1 name
     * @param secondPlayerName player2 name
     * @param firstPlayerDeck player1 deck
     * @param secondPlayerDeck player2 deck
     * @return a MatchImpl for Free Match
     */
    public static Match createFreeMatch(final String firstPlayerName, final String secondPlayerName,
                                        final Deck firstPlayerDeck, final Deck secondPlayerDeck) {

        final Player player1 = new PlayerImpl(firstPlayerName, DEFAULT_MAX_HAND_SIZE,
                                                DEFAULT_STARTING_HAND_SIZE, firstPlayerDeck);
        final Player player2 = new PlayerImpl(secondPlayerName, DEFAULT_MAX_HAND_SIZE,
                                                DEFAULT_STARTING_HAND_SIZE, secondPlayerDeck);

        return new MatchImpl(player1, player2, 
                                DEFAULT_PLAYFIELD_SIZE, true, 
                                new MatchLogicImpl(CardAction.TO_PILE, CardAction.TO_PILE));
    }

    /**
     * Creates a Custom Match.
     * 
     * @param firstPlayerName player1 name
     * @param secondPlayerName player2 name
     * @param firstPlayerDeck player1 deck
     * @param secondPlayerDeck player2 deck
     * @param maxHandSize maximum hand size
     * @param startingHandSize amount of card in hand at match start
     * @param playerFieldSize maximum number of cards on the field per player
     * @param autoDraw should the turn player draw a card on turn start
     * @param winnerAction the action done to the winner card
     * @param loserAction the action done to the loser card
     * @return a MatchImpl for Custom Match
     */
    public static Match createCustomMatch(final String firstPlayerName, final String secondPlayerName,
                                            final Deck firstPlayerDeck, final Deck secondPlayerDeck,
                                            final int maxHandSize, final int startingHandSize,
                                            final int playerFieldSize, final boolean autoDraw,
                                            final CardAction winnerAction, final CardAction loserAction) {

        final Player player1 = new PlayerImpl(firstPlayerName, maxHandSize,
                                                startingHandSize, firstPlayerDeck);
        final Player player2 = new PlayerImpl(secondPlayerName, maxHandSize,
                                                startingHandSize, secondPlayerDeck);

        return new MatchImpl(player1, player2,
                                playerFieldSize, autoDraw,
                                new MatchLogicImpl(winnerAction, loserAction));

    }

}
