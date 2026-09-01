package it.unibo.cardhub.view.api;

import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;

/**
 * JPanel representing the playfield of the match view, including the central playfield and the discard piles for both players.
 */
public interface PlayfieldPanel {
    /**
     * Updates the player's discard pile with the specified card.
     *
     * @param card the card to display in the discard pile
     */
    void updatePlayerOneDiscardPile(Optional<Card<?>> card);

    /**
     * Updates the opponent's discard pile with the specified card.
     *
     * @param card the card to display in the discard pile
     */
    void updatePlayerTwoDiscardPile(Optional<Card<?>> card);

    /**
     * Updates the playfield with the specified list of cards.
     *
     * @param cards the list of cards to display on the playfield
     * @param player the owner of the playfield
     */
    void updatePlayfield(PlayerEnum player, List<Card<?>> cards);

    /**
     * Updates the playfield showing only face-down cards, without
     * exposing any card data to the view.
     *
     * @param cardCount number of cards on the playfield
     * @param player the owner of the playfield
     */
    void updateHiddenPlayfield(PlayerEnum player, int cardCount);

    /**
     * Adds this panel to a parent panel.
     * 
     * @param panel the parent panel
     * @param constraints an object expressing layout constraints for this component
     */
    void addToPanel(JPanel panel, Object constraints);
}
