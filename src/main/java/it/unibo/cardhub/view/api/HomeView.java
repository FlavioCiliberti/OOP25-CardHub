package it.unibo.cardhub.view.api;

/**
 * View for the home screen.
 */
public interface HomeView {

    /**
     * Navigates to the new match screen.
     */
    void goToNewMatch();

    /**
     * Navigates to the load match screen.
     */
    void goToLoadMatch();

    /**
     * Navigates to the manage decks screen.
     */
    void goToManageDecks();

    /**
     * Exits the application.
     */
    void exit();
}
