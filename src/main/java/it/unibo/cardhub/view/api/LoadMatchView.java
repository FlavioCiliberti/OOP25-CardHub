package it.unibo.cardhub.view.api;

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
     * Tells the navigator to redirect to the home screen.
     */
    void goToHome();

    /**
     * Tells the navigator to redirect to the match screen.
     */
    void goToMatch();
}

