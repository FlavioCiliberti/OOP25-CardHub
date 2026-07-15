package it.unibo.cardhub;

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
        final var ui = new CHFrame();
        ui.setVisible(true);
    }
}
