package it.unibo.cardhub.controller.impl;

import java.util.Map;
import java.util.Objects;

import javax.swing.JComponent;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.cardhub.controller.ScreenId;
import it.unibo.cardhub.controller.api.CreateMatchController;
import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.controller.api.MatchControllerFactory;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.io.api.DeckFactory;
import it.unibo.cardhub.io.impl.DeckFactoryImpl;
import it.unibo.cardhub.model.api.CreateMatchModel;
import it.unibo.cardhub.model.domain.DeckEnum;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.exceptions.EmptyFieldException;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.GameMode;
import it.unibo.cardhub.view.api.CreateMatchView;
import it.unibo.cardhub.view.impl.CreateMatchViewImpl;

/**
 * implementation of {@link CreateMatchController}.
 */
public class CreateMatchControllerImpl implements CreateMatchController {

    private final CreateMatchModel model;
    private final Navigator navigator;
    private final CreateMatchView view;
    private final DeckFactory deckFactory;

    /**
     * Constructor for the controller.
     * 
     * @param model the create match screen model
     * @param navigator the screen navigator
     */
    public CreateMatchControllerImpl(final CreateMatchModel model, final Navigator navigator) {
        this.model = Objects.requireNonNull(model, "no model supplied");
        this.navigator = Objects.requireNonNull(navigator, "no navigator supplied");
        this.view = new CreateMatchViewImpl(this);
        this.deckFactory = new DeckFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goBack() {
        navigator.goHome();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI", justification =
            "The view JComponent must be returned by reference so it "
                    + "can be embedded in the real application window; "
                    + "cannot return a defensive copy for this purpuse.")
    public JComponent getView() {
        return (JComponent) view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMinHandSize() {
        return model.getMinHandSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxHandSize() {
        return model.getMaxHandSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMinFieldSize() {
        return model.getMinFieldSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxFieldSize() {
        return model.getMaxFieldSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDefaultHandSize() {
        return model.getDefaultHandSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDefaultFieldSize() {
        return model.getDefaultFieldSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, String> getDecks() {
        return model.getDecks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tryCreatingMatch(final String player1Name, final int player1DeckId, 
                                final String player2Name, final int player2DeckId,
                                final int maxHandSize, final int startingHandSize, 
                                final int playerFieldSize, final boolean autoDraw, 
                                final CardAction winnerAction, final CardAction loserAction, 
                                final GameMode gameMode) {
        try {
            checkMatchParams(player1Name, player2Name, 
                            winnerAction, loserAction, gameMode);
        } catch (final EmptyFieldException e) {
            view.showInvalidForm(e.getMessage());
            return;
        }

        switch (gameMode) {
            case FREE_PLAY:
                createFreeGameController(player1Name, player1DeckId, player2Name, player2DeckId);
                break;
            case CUSTOM:
                createCustomGameController(player1Name, player1DeckId, player2Name, player2DeckId, 
                                maxHandSize, startingHandSize, playerFieldSize, autoDraw, 
                                winnerAction, loserAction);
                break;
            case E_CARD:
                createFullGameController(player1Name, player2Name);
                break;
        }

    }

    /**
     * Creates the controller of a new game using default (free) rules.
     *
     * @param player1Name the name of the first player
     * @param player1DeckId the deck id chosen by the first player
     * @param player2Name the name of the second player
     * @param player2DeckId the deck id chosen by the second player
     */
    private void createFreeGameController(final String player1Name, final int player1DeckId, 
                                final String player2Name, final int player2DeckId) {
        final MatchController controller = MatchControllerFactory.createFreeMatchController(player1Name, player2Name,
                                                                    getDeck(player1DeckId), getDeck(player2DeckId), navigator);
        showMatch(controller);
    }

    /**
     * Creates the controller of a new game using a custom, user-configurable rule set.
     *
     * <p>TO DO:
     * add winner and looser card actions parameters.
     * </p>
     *
     * @param player1Name the name of the first player
     * @param player1DeckId the deck id chosen by the first player
     * @param player2Name the name of the second player
     * @param player2DeckId the deck id chosen by the second player
     * @param maxHandSize the maximum amount of cards a player can have in their hand
     * @param startingHandSize the amount of cards a player has in their hand on game start
     * @param playerFieldSize the maximum amount of cards a player can put in their playfield
     * @param autoDraw set to {@code true} if a draw must be done automatically at the beggining of the turn
     * @param winnerAction the action to be done to the winner card
     * @param loserAction the action to be done to the loser card
     */
    private void createCustomGameController(final String player1Name, final int player1DeckId, 
                                final String player2Name, final int player2DeckId,
                                final int maxHandSize, final int startingHandSize, 
                                final int playerFieldSize, final boolean autoDraw, 
                                final CardAction winnerAction, final CardAction loserAction) {
        final MatchController controller = MatchControllerFactory.createCustomMatchController(player1Name,
                                                                    player2Name, getDeck(player1DeckId), getDeck(player2DeckId),
                                                                    maxHandSize, startingHandSize, playerFieldSize,
                                                                    autoDraw, winnerAction, loserAction, navigator);
        showMatch(controller);
    }

    /**
     * Creates the controller of a new E-Card game.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     */
    private void createFullGameController(final String player1Name, final String player2Name) {
        final MatchController controller = MatchControllerFactory.createECardMatchController(player1Name, player2Name, navigator);
        showMatch(controller);
    }

    private void checkMatchParams(final String player1Name, 
                        final String player2Name,
                        final CardAction winnerAction, final CardAction loserAction, 
                        final GameMode gameMode) 
                        throws EmptyFieldException {
        if (player1Name == null
            || player2Name == null
            || gameMode == null
            || winnerAction == null
            || loserAction == null) {
            throw new EmptyFieldException("A required field is null");
        }

        if (player1Name.isBlank()) {
            throw new EmptyFieldException("Player one name is required");
        }

        if (player2Name.isBlank()) {
            throw new EmptyFieldException("Player two name is required");
        }
    }

    /**
     * Navigates to the match corresponding to the controller.
     * 
     * @param matchController the controller of the game
     */
    private void showMatch(final MatchController matchController) {
        navigator.show(ScreenId.MATCH, matchController.getView());
    }

    private Deck getDeck(final int deckId) {
        final DeckEnum deck = DeckEnum.fromId(deckId);

        return switch (deck) {
            case POKEMON -> this.deckFactory.createPokemonDeck();
            case DRAGONBALL -> this.deckFactory.createDragonBallDeck();
            case YUGIOH -> this.deckFactory.createYuGiOhDeck();
        };
    }
}
