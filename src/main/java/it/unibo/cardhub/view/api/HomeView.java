package it.unibo.cardhub.view.api;

/**
 * Represents th view for the home screen.
 */
public interface HomeView {

    /**
     * Navigates to the new match screen.
     */
    void onNewMatch();

    /**
     * Navigates to the load match screen.
     */
    void onLoadMatch();

    /**
     * Navigates to the manage decks screen.
     */
    void onManageDecks();

    /**
     * Exits the application.
     */
    void onExit();
}
