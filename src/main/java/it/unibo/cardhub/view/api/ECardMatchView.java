package it.unibo.cardhub.view.api;

/**
 * Extension of {@MatchView} with a pop-up notification for visualizing the result.
 */
public interface ECardMatchView extends ScoredMatchView {

    /**
     * Shows a pop-up with the comparison result.
     * 
     * @param message the message
     */
    void showComparisonResult(String message);
}
