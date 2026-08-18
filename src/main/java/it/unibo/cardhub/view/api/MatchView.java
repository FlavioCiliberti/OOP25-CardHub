package it.unibo.cardhub.view.api;

import java.util.List;
import java.util.Optional;

import javax.swing.JLabel;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.logic.api.PlayerEnum;

/**
 * Represents the view of a match, updated by the {@link MatchController} 
 * whenever the model changes.
 * 
 * <p>It only deals with domain objects (cards, players): mapping
 * a card to its image is entirely up to the concrete view implementation.
 * </p>
 */
public interface MatchView {

    /**
     * Refreshes a player's hand.
     *
     * @param player the player whose hand changed
     * @param cards the player's hand, in order
     */
    void updateHand(PlayerEnum player, List<Card<?>> cards);

    /**
     * Updates the playfield with the specified list of cards.
     *
     * @param cards the list of cards to display on the playfield
     * @param player the owner of the playfield
     */
    void updatePlayfield(PlayerEnum player, List<Card<?>> cards);

    /**
     * Refreshes a player's discard pile.
     *
     * @param player the player whose discard pile changed
     * @param topCard the top card of the pile, if any
     */
    void updateDiscardPile(PlayerEnum player, Optional<Card<?>> topCard);

    /**
     * Refreshes a player's remaining deck size and makes the deck invisible if empty.
     *
     * @param player the player whose deck changed
     * @param remainingCards the number of cards left in the deck
     */
    void updateDeck(PlayerEnum player, int remainingCards);

    /**
     * Signals whose turn it currently is.
     *
     * @param player the current player
     */
    void showCurrentPlayer(PlayerEnum player);

    /**
     * Signals that the match has ended.
     *
     * @param winner the match's winner
     */
    void showMatchEnded(PlayerEnum winner); //important: match stats parameters must be added

    /**
     * Sets the card to be highlighted, eventually de-highlighting the previous one.
     * 
     * @param cardLabel the card label to be highlited
     */
    void changeSelectedCard(JLabel cardLabel);
}
