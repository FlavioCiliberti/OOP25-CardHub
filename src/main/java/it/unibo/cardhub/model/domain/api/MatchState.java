package it.unibo.cardhub.model.domain.api;

import java.util.List;
import java.util.Optional;

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
}
