package it.unibo.cardhub.view.api;

import java.awt.event.ActionListener;

/**
 * Represents the view of the deck manager section.
 */
public interface DeckManagerView {

    /**
     * Adds a card entry to the view.
     *
     * @param cardName card name
     * @param value value of the card
     */
    void addCard(String cardName, int value);

    /**
     * Removes all card entries from the view.
     */
    void clearCards();

    /**
     * Handles events for the create cards button.
     * 
     * @param listener action listener
     */
    void addCreateCardListener(ActionListener listener);

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
