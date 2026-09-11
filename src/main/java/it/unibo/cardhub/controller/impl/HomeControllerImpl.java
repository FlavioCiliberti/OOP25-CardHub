package it.unibo.cardhub.controller.impl;

import java.util.Objects;

import it.unibo.cardhub.controller.ScreenId;
import it.unibo.cardhub.controller.api.CreateMatchController;
import it.unibo.cardhub.controller.api.HomeController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.api.CreateMatchModel;
import it.unibo.cardhub.model.impl.CreateMatchModelImpl;
import it.unibo.cardhub.view.api.HomeView;
import it.unibo.cardhub.view.components.ScreenView;
import it.unibo.cardhub.view.impl.HomeViewImpl;

/**
 * implementation of {@link HomeController}.
 */

public class HomeControllerImpl implements HomeController {

    private final Navigator navigator;
    private final Runnable exitOperation;
    private final HomeView view;

    /**
     * Constructor for the controller.
     * 
     * @param exitOperation the {@link Runnable} operation that closes the app
     * @param navigator the screen navigator
     */
    public HomeControllerImpl(final Navigator navigator, final Runnable exitOperation) {
        this.exitOperation = Objects.requireNonNull(exitOperation, "no exit operation supplied");
        this.navigator = Objects.requireNonNull(navigator, "no navigator supplied");
        this.view = new HomeViewImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showScreen() {
        navigator.show(ScreenId.HOME, (ScreenView) view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newMatch() {
        final CreateMatchModel createMatchModel = new CreateMatchModelImpl();
        final CreateMatchController createMatchController = new CreateMatchControllerImpl(createMatchModel, navigator);
        createMatchController.showScreen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageDecks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'manageDecks'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        exitOperation.run();
    }

}
