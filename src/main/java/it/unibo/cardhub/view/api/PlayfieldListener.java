package it.unibo.cardhub.view.api;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;

/**
 * Listener for events in the Playfield.
 */
public interface PlayfieldListener {
    /**
     * Executes an action when the mouse hovers over a card.
     * 
     * @param card the card
     * @param player the player the card belongs to
     */
    void mouseHovered(Card<?> card, PlayerEnum player);

    /**
     * Executes an action when the mouse stops hovering a card.
     * 
     * @param player the player the card belonged to
     */
    void mouseExited(PlayerEnum player);
}
