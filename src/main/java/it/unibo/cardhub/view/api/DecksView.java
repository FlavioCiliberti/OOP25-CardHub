package it.unibo.cardhub.view.api;

import java.awt.event.ActionListener;

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
     * Adds an action listener to the create button.
     *
     * @param listener listener for create action
     */
    void addCreateListener(ActionListener listener);

    /**
     * Adds an action listener to the back button.
     *
     * @param listener listener for back action
     */
    void addBackListener(ActionListener listener);
}