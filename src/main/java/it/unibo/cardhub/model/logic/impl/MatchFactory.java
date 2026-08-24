package it.unibo.cardhub.model.logic.impl;

import java.util.Objects;

import it.unibo.cardhub.io.api.DeckFactory;
import it.unibo.cardhub.io.impl.DeckFactoryImpl;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.impl.PlayerImpl;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.Match;
import it.unibo.cardhub.model.logic.api.MatchLogic;

/**
 * a factory for Match.
 */
public final class MatchFactory {
    private static final int ECARD_MAX_HAND_SIZE = 7;
    private static final int ECARD_STARTING_HAND_SIZE = 7;
    private static final int ECARD_PLAYFIELD_SIZE = 1;

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

        return new MatchBuilder(firstPlayerName, secondPlayerName,
                                firstPlayerDeck, secondPlayerDeck,
                                new MatchLogicImpl(CardAction.TO_PILE, CardAction.TO_PILE)).build();
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
        return new MatchBuilder(firstPlayerName, secondPlayerName,
                                firstPlayerDeck, secondPlayerDeck,
                                new MatchLogicImpl(winnerAction, loserAction))
                                .maxHandSize(maxHandSize).startingHandSize(startingHandSize)
                                .playfieldSize(playerFieldSize).autoDraw(autoDraw).build();
    }

    /**
     * Creates an E-Card match.
     * 
     * @param firstPlayerName player1 name
     * @param secondPlayerName player2 name
     * @return a MatchImpl for E-Card match
     */
    public static Match createECardMatch(final String firstPlayerName, final String secondPlayerName) {
        final DeckFactory deckFactory = new DeckFactoryImpl();

        return new MatchBuilder(firstPlayerName, secondPlayerName,
                                deckFactory.createECardDeck(), deckFactory.createECardDeck(), new ECardLogic())
                                .maxHandSize(ECARD_MAX_HAND_SIZE).startingHandSize(ECARD_STARTING_HAND_SIZE)
                                .playfieldSize(ECARD_PLAYFIELD_SIZE).autoDraw(false).build();
    }

    /**
     * A builder for Match.
     */
    private static final class MatchBuilder {
    private static final int DEFAULT_MAX_HAND_SIZE = 5;
    private static final int DEFAULT_STARTING_HAND_SIZE = 5;
    private static final int DEFAULT_PLAYFIELD_SIZE = 3;

    private final String firstPlayerName;
    private final String secondPlayerName;

    private final Deck firstPlayerDeck;
    private final Deck secondPlayerDeck;

    private final MatchLogic logic;

    private int maxHandSize = DEFAULT_MAX_HAND_SIZE;
    private int startingHandSize = DEFAULT_STARTING_HAND_SIZE;
    private int playfieldSize = DEFAULT_PLAYFIELD_SIZE;
    private boolean autoDraw = true;

    private MatchBuilder(final String firstPlayerName, final String secondPlayerName,
                        final Deck firstPlayerDeck, final Deck secondPlayerDeck,
                        final MatchLogic logic) {
        this.firstPlayerName = Objects.requireNonNull(firstPlayerName);
        this.secondPlayerName = Objects.requireNonNull(secondPlayerName);

        this.firstPlayerDeck = Objects.requireNonNull(firstPlayerDeck);
        this.secondPlayerDeck = Objects.requireNonNull(secondPlayerDeck);

        this.logic = Objects.requireNonNull(logic);
    }

    private MatchBuilder maxHandSize(final int maxHandSizeValue) {
        this.maxHandSize = maxHandSizeValue;
        return this;
    }

    private MatchBuilder startingHandSize(final int startingHandSizeValue) {
        this.startingHandSize = startingHandSizeValue;
        return this;
    }

    private MatchBuilder playfieldSize(final int playfieldSizeValue) {
        this.playfieldSize = playfieldSizeValue;
        return this;
    }

    private MatchBuilder autoDraw(final boolean autoDrawValue) {
        this.autoDraw = autoDrawValue;
        return this;
    }

    private Match build() {
        final Player player1 = new PlayerImpl(firstPlayerName, maxHandSize,
                                                startingHandSize, firstPlayerDeck);
        final Player player2 = new PlayerImpl(secondPlayerName, maxHandSize,
                                                startingHandSize, secondPlayerDeck);

        return new MatchImpl(player1, player2, playfieldSize, autoDraw, logic);
    }
}

}
