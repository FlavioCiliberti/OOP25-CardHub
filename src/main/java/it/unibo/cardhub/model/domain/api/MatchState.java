package it.unibo.cardhub.model.domain.api;

import java.util.List;

import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.api.PlayerEnum;

/**
 * Represent a match, the heart of the game.
 */
public interface MatchState {

    /**
     * Returns all the players of the match.
     * 
     * @return all the players
     */
    List<Player> getPlayers();

    /**
     * Getter for a specific player by enum.
     * 
     * @param player the requested player by its enum
     * @return the actual player
     */
    Player getPlayer(PlayerEnum player);

    /**
     * returns the PlayerEnum of the specified player.
     * 
     * @param player the requested player
     * @return the player
     */
    PlayerEnum getEnum(Player player);

    /**
     * Returns the state of the playfield.
     * 
     * @return the playfield
     */
    Playfield getPlayfield();

    /**
     * A getter for the player's points.
     * 
     * @param player the enum of the player
     * @return the points of the corresponding player
     */
    int getPlayerPoints(PlayerEnum player);

    /**
     * A getter for the player with the most points.
     * 
     * @return the enum of the player with the most points
     */
    ComparisonWinner getWinningPlayer();
}
