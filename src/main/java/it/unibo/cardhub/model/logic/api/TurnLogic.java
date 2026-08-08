package it.unibo.cardhub.model.logic.api;

import it.unibo.cardhub.model.domain.api.Player;

/**
 * handles the turn logic.
 */
public interface TurnLogic {
    /**
     * getter for the turn player.
     * 
     * @return the turn player.
     */
    public Player getCurrentPlayer();

    /**
     * changes the turn player.
     */
    public void changeTurn();
}
