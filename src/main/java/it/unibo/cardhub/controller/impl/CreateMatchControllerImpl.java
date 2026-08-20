package it.unibo.cardhub.controller.impl;

import java.util.Map;
import java.util.Objects;

import javax.swing.JComponent;

import it.unibo.cardhub.controller.api.CreateMatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.api.CreateMatchModel;
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

    @Override
    public void goBack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'goBack'");
    }

    @Override
    public JComponent getView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }

    @Override
    public int getMinHandSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMinHandSize'");
    }

    @Override
    public int getMaxHandSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxHandSize'");
    }

    @Override
    public int getMinFieldSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMinFieldSize'");
    }

    @Override
    public int getMaxFieldSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxFieldSize'");
    }

    @Override
    public int getDefaultHandSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDefaultHandSize'");
    }

    @Override
    public int getDefaultFieldSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDefaultFieldSize'");
    }

    @Override
    public Map<Integer, String> getDecks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDecks'");
    }

    @Override
    public void tryCreatingMatch(String player1Name, int player1DeckId, String player2Name, int player2DeckId,
            int maxHandSize, int startingHandSize, int playerFieldSize, boolean autoDraw, CardAction winnerAction,
            CardAction loserAction, GameMode gameMode) {
        // TODO Auto-generated method stub
    }

    @Override
    public void createFreeGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFreeGame'");
    }

    @Override
    public void createFullGame(String player1Name, String player2Name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFullGame'");
    }

    @Override
    public void createCustomGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId,
            int maxHandSize, int startingHandSize, int playerFieldSize, boolean autoDraw, CardAction winnerAction,
            CardAction loserAction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCustomGame'");
    }
    
}
