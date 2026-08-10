package it.unibo.cardhub.model.domain.api;

import java.util.List;

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
     * Returns the state of the playfield.
     * 
     * @return the playfield
     */
    Playfield getPlayfield();
}
