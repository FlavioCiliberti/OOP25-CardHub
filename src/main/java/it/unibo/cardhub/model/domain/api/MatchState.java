package it.unibo.cardhub.model.domain.api;

import java.util.List;
import java.util.Optional;

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
     * Ends a match and sets the winner.
     * 
     * @param player the winner
     */
    void endMatch(Player player);

    /**
     * Informs about the match's state.
     * 
     * @return true if finished, false otherwise
     */
    boolean isFinished();

    /**
     * A getter for the match's winner.
     * 
     * @return the winner, if present
     */
    Optional<Player> getWinner();
}
