package it.unibo.cardhub.view.api;

import java.awt.event.ActionListener;

import it.unibo.cardhub.model.logic.GameMode;

/**
 * Represents the view of the load macth section.
 */
public interface LoadMatchView {

    /**
     * Adds a match entry to the view.
     *
     * @param date match date
     * @param mode game mdoe
     */
    void addMatch(String date, GameMode mode);

    /**
     * Removes all match entries from the view.
     */
    void clearMatches();

    /**
     * Handles events for the back button.
     * 
     * @param listener action listener
     */
    void addBackListener(ActionListener listener);

    /**
     * Adds a listener to load buttons.
     * 
     * @param listener action listener
     */
    void addLoadListener(ActionListener listener);

    /**
     * Adds a listener to delete buttons.
     * 
     * @param listener action listener
     */
    void addDeleteListener(ActionListener listener);
}

