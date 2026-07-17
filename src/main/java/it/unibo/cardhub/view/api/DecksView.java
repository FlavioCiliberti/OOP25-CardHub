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
     * Handles events for the create deck button.
     * 
     * @param listener action listener
     */
    void addCreateDeckListener(ActionListener listener);

    /**
     * Handles events for the back button.
     * 
     * @param listener action listener
     */
    void addBackListener(ActionListener listener);

    /**
     * Adds a listener to edit buttons.
     * 
     * @param listener action listener
     */
    void addEditListener(ActionListener listener);

    /**
     * Adds a listener to delete buttons.
     * 
     * @param listener action listener
     */
    void addDeleteListener(ActionListener listener);
}
