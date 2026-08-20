package it.unibo.cardhub.controller.impl;

import java.util.Map;
import java.util.Objects;

import javax.swing.JComponent;

import it.unibo.cardhub.controller.api.CreateMatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.api.CreateMatchModel;
import it.unibo.cardhub.model.domain.exceptions.EmptyFieldException;
import it.unibo.cardhub.model.logic.GameMode;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.view.api.CreateMatchView;
import it.unibo.cardhub.view.impl.CreateMatchViewImpl;

/**
 * implementation of {@link CreateMatchController}.
 */
public class CreateMatchControllerImpl implements CreateMatchController {

    private final CreateMatchModel model;
    private final Navigator navigator;
    private final CreateMatchView view;

    public CreateMatchControllerImpl(final CreateMatchModel model, final Navigator navigator) {
        this.model = Objects.requireNonNull(model, "no model loaded");
        this.navigator = Objects.requireNonNull(navigator, "no navigator loaded");
        this.view = new CreateMatchViewImpl(this);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDecks'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tryCreatingMatch(String player1Name, int player1DeckId, String player2Name, int player2DeckId,
            int maxHandSize, int startingHandSize, int playerFieldSize, boolean autoDraw, CardAction winnerAction,
            CardAction loserAction, GameMode gameMode) {
        try {
            checkMatchParams(player1Name, player1DeckId, player2Name, player2DeckId, 
                            maxHandSize, startingHandSize, playerFieldSize, autoDraw, 
                            winnerAction, loserAction, gameMode);
        } catch (final EmptyFieldException e) {
            view.showInvalidForm(e.getMessage());
        }
    }

    /**
     * Creates a new game using default (free) rules.
     *
     * @param player1Name the name of the first player
     * @param player1DeckId the deck id chosen by the first player
     * @param player2Name the name of the second player
     * @param player2DeckId the deck id chosen by the second player
     */
    private void createFreeGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFreeGame'");
    }

    /**
     * Creates a new game using a custom, user-configurable rule set.
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
    private void createCustomGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId,
            int maxHandSize, int startingHandSize, int playerFieldSize, boolean autoDraw, CardAction winnerAction,
            CardAction loserAction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCustomGame'");
    }

    /**
     * Creates a new game using the full official rule set.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     */
    private void createFullGame(String player1Name, String player2Name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFullGame'");
    }

    void checkMatchParams(final String player1Name, final int player1DeckId, 
                        final String player2Name, final int player2DeckId,
                        final int maxHandSize, final int startingHandSize, final int playerFieldSize, final boolean autoDraw, final CardAction winnerAction,
                        final CardAction loserAction, final GameMode gameMode) 
                        throws EmptyFieldException {
        try {
            Objects.requireNonNull(player1Name);
            Objects.requireNonNull(player1DeckId);
            Objects.requireNonNull(player2Name);
            Objects.requireNonNull(player2DeckId);
            Objects.requireNonNull(maxHandSize);
            Objects.requireNonNull(startingHandSize);
            Objects.requireNonNull(playerFieldSize);
            Objects.requireNonNull(autoDraw);
            Objects.requireNonNull(winnerAction);
            Objects.requireNonNull(loserAction);
            Objects.requireNonNull(gameMode);
        } catch(final NullPointerException e) {
            throw new EmptyFieldException(e.getMessage());
        }

        if (player1Name.isBlank()) {
            throw new EmptyFieldException("Player one name is required");
        }

        if (player2Name.isBlank()) {
            throw new EmptyFieldException("Player two name is required");
        }
    }
    
}
