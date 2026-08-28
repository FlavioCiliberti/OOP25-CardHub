package it.unibo.cardhub.controller.api;

import it.unibo.cardhub.controller.impl.MatchControllerImpl;
import it.unibo.cardhub.io.api.DeckFactory;
import it.unibo.cardhub.io.impl.DeckFactoryImpl;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.impl.MatchStateBuilderImpl;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.model.logic.impl.ECardLogic;
import it.unibo.cardhub.model.logic.impl.MatchLogicImpl;

/**
 * A factory for MatchController.
 */
public final class MatchControllerFactory {
    private static final int ECARD_MAX_HAND_SIZE = 7;
    private static final int ECARD_STARTING_HAND_SIZE = 7;
    private static final int ECARD_PLAYFIELD_SIZE = 1;

    private MatchControllerFactory() {

    }

    /**
     * Creates a MatchController for a free match.
     * 
     * @param firstPlayerName player1 name
     * @param secondPlayerName player2 name
     * @param firstPlayerDeck player1 deck
     * @param secondPlayerDeck player2 deck
     * @param navigator the navigator
     * @return a MatchControllerImpl for Free Match
     */
    public static MatchController createFreeMatchController(final String firstPlayerName, final String secondPlayerName,
                                                            final Deck firstPlayerDeck, final Deck secondPlayerDeck,
                                                            final Navigator navigator) {
        final MatchState state = new MatchStateBuilderImpl(firstPlayerName, secondPlayerName,
                                                        firstPlayerDeck, secondPlayerDeck).build();
        final MatchLogic logic = new MatchLogicImpl(CardAction.TO_PILE, CardAction.TO_PILE, true, state);
        return new MatchControllerImpl(state, logic, navigator);
    }

    /**
     * Creates a Controller for Custom Match.
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
     * @param navigator the navigator
     * @return a MatchControllerImpl for Custom Match
     */
    public static MatchController createCustomMatchController(final String firstPlayerName, final String secondPlayerName,
                                            final Deck firstPlayerDeck, final Deck secondPlayerDeck,
                                            final int maxHandSize, final int startingHandSize,
                                            final int playerFieldSize, final boolean autoDraw,
                                            final CardAction winnerAction, final CardAction loserAction,
                                            final Navigator navigator) {
        final MatchState state = new MatchStateBuilderImpl(firstPlayerName, secondPlayerName, firstPlayerDeck, secondPlayerDeck)
                            .maxHandSize(maxHandSize).startingHandSize(startingHandSize).playfieldSize(playerFieldSize).build();
        final MatchLogic logic = new MatchLogicImpl(winnerAction, loserAction, autoDraw, state);
        return new MatchControllerImpl(state, logic, navigator);
    }

    /**
     * Creates a Controller for an E-Card match.
     * 
     * @param firstPlayerName player1 name
     * @param secondPlayerName player2 name
     * @param navigator the navigator
     * @return a MatchControllerImpl for E-Card match
     */
    public static MatchController createECardMatchController(final String firstPlayerName, final String secondPlayerName,
                                                    final Navigator navigator) {
        final DeckFactory deckFactory = new DeckFactoryImpl();

        final MatchState state = new MatchStateBuilderImpl(firstPlayerName, secondPlayerName,
                            deckFactory.createECardDeck(), deckFactory.createECardDeck()).maxHandSize(ECARD_MAX_HAND_SIZE)
                            .startingHandSize(ECARD_STARTING_HAND_SIZE).playfieldSize(ECARD_PLAYFIELD_SIZE).build();
        return new MatchControllerImpl(state, new ECardLogic(state), navigator);
    }
}
