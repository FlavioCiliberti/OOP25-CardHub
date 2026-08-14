package it.unibo.cardhub.view.api;

import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * JPanel representing the playfield of the match view, including the central playfield and the discard piles for both players.
 */
public interface PlayfieldPanel {
    /**
     * Updates the player's discard pile with the specified card.
     *
     * @param card the card to display in the discard pile
     */
    void updatePlayerOneDiscardPile(Optional<Card> card);

    /**
     * Updates the opponent's discard pile with the specified card.
     *
     * @param card the card to display in the discard pile
     */
    void updatePlayerTwoDiscardPile(Optional<Card> card);

    /**
     * Updates the playfield with the specified list of cards.
     *
     * @param cards the list of cards to display on the playfield
     * @param columns the number of columns in the playfield
     */
    void updatePlayfield(List<Card> cards, int columns);

    /**
     * Adds this panel to a parent panel.
     * 
     * @param panel the parent panel
     * @param constraints an object expressing layout constraints for this component
     */
    void addToPanel(JPanel panel, Object constraints);
}
