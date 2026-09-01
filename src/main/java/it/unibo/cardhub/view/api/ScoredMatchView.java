package it.unibo.cardhub.view.api;

/**
 * Extension of {@MatchView} with a player's score.
 */
public interface ScoredMatchView extends MatchView {

    /**
     * Updates the current score.
     * 
     * @param p1Score current score of player one
     * @param p2Score current score of player two
     */
    void updateScore(int p1Score, int p2Score);
}
