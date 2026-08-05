package it.unibo.cardhub.view.api;

import java.util.List;
import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Player;

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
    void updateHand(Player player, List<Card> cards);

    /**
     * Refreshes a player's section of the playfield.
     *
     * @param player the player whose playfield section changed
     * @param cards the cards on that section of the playfield
     */
    void updatePlayfield(Player player, List<Card> cards);

    /**
     * Refreshes a player's discard pile.
     *
     * @param player the player whose discard pile changed
     * @param topCard the top card of the pile, if any
     */
    void updateDiscardPile(Player player, Optional<Card> topCard);

    /**
     * Refreshes a player's remaining deck size.
     *
     * @param player the player whose deck changed
     * @param remainingCards the number of cards left in the deck
     */
    void updateDeckCount(Player player, int remainingCards);

    /**
     * Signals whose turn it currently is.
     *
     * @param player the current player
     */
    void showCurrentPlayer(Player player);

    /**
     * Signals that the match has ended.
     *
     * @param winner the match's winner
     */
    void showMatchEnded(Player winner); //important: match stats parameters must be added
}
