package it.unibo.cardhub.model.logic.api;

/**
 * An interface for point-tracking in the match logic.
 */
public interface PointTracker {
    /**
     * Getter for a player's points.
     * 
     * @param player the Enum of the player
     * @return the points of the specified player
     */
    int getPoints(PlayerEnum player);

    /**
     * Getter for the winning player.
     * 
     * @return the Enum of the player with the most points
     */
    ComparisonWinner getWinningPlayer();
}
