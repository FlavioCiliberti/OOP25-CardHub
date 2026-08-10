package it.unibo.cardhub.model.domain.api;

import java.util.List;

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
     * Returns the state of the playfield.
     * 
     * @return the playfield
     */
    Playfield getPlayfield();
}
