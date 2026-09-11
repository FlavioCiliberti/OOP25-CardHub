package it.unibo.cardhub.view.api;

import java.util.List;

import javax.swing.JPanel;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * Represents the player area of the match view, containing the hand, the deck and the card descriptions.
 */
public interface PlayerPanel {
    /**
     * Repaints the hand.
     * 
     * @param cards the list of cards in the hand
     */
    void updateShowingHandPanel(List<Card<?>> cards);

    /**
     * Updates hand showing only face-down cards, without
     * exposing any card data to the view.
     * 
     * @param cardCount number of cards on hand
     */
    void updateHiddenHandPanel(int cardCount);

    /**
     * Updates the deckSizeLabel and sets the deck to invisible if it's empty.
     */
    void updateDeck();

    /**
     * Adds this panel to a parent panel.
     * 
     * @param panel the parent panel
     * @param constraints an object expressing layout constraints for this component
     */
    void addToPanel(JPanel panel, Object constraints);
}
