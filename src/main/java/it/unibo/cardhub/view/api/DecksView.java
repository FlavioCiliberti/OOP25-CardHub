package it.unibo.cardhub.view.api;

/**
 * Represents the view of the decks section.
 */
public interface DecksView {

    /**
     * Adds a deck entry to the view.
     *
     * @param name deck name
     * @param numberOfCards number of cards contained in the deck
     */
    void addDeck(String name, int numberOfCards);

    /**
     * Removes all deck entries from the view.
     */
    void clearDecks();

    /**
     * Tells the navigator to redirect to the deck manager screen.
     */
    void goToDeckManager();

    /**
     * Tells the navigator to redirect to the home screen.
     */
    void goToHome();
}