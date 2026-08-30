package it.unibo.cardhub.view.api;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;

/**
 * Executes the necessary actions in PlayerPanel,
 * according to events happening in PlayfieldPanel.
 */
public interface PlayerPanelNotifier {
    /**
     * Updates the card description when the mouse hovers over a card.
     * 
     * @param card the card
     * @param player the player the card belongs to
     */
    void mouseHovered(Card<?> card, PlayerEnum player);

    /**
     * Removes the card description when the mouse stops hovering a card.
     * 
     * @param player the player the card belonged to
     */
    void mouseExited(PlayerEnum player);
}
