package it.unibo.cardhub;

import it.unibo.cardhub.controller.ScreenId;
import it.unibo.cardhub.controller.api.HomeController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.controller.impl.CardLayoutNavigator;
import it.unibo.cardhub.controller.impl.HomeControllerImpl;
import it.unibo.cardhub.view.components.CHFrame;

/**
 * Main class of the CardHub application.
 */
public final class App {

    private App() {

    }

    /**
     * Application entry point.
     * 
     * @param args command line arguments
     */
    public static void main(final String[] args) {

        final Navigator navigator = new CardLayoutNavigator();
        final CHFrame frame = new CHFrame();
        frame.add(navigator.getRootView());

        final HomeController home = new HomeControllerImpl(
                                    navigator,
                                    frame::dispose);
        navigator.show(ScreenId.HOME, home.getView());

        frame.setVisible(true);
    }
}
