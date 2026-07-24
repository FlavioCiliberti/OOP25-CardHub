package it.unibo.cardhub.controller.api;

/**
 * Controller responsible for handling the main actions available
 * from the home screen of the application.
 */
public interface HomeController {

    /**
     * Creates the instances necessary for the CreateMatch screen.
     * Then navigates to it.
     */
    void newMatch();

    /**
     * Creates the instances necessary for the LoadMatch screen.
     * Then navigates to it.
     */

    void loadMatch();

    /**
     * Terminates the application.
     */
    void exit();
}
