package it.unibo.cardhub.controller.api;

import javax.swing.JLabel;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Player;

/**
 * Controller responsible for handling the main actions available
 * in the match screen, updating it's model and notifying it's view.
 * Each action targets a fixed source-to-destination movement
 * (e.g. playing a card always moves
 * it from hand to playfield).
 *
 * <p>Every action depends on turn order: it throws {@link
 * IllegalStateException} if it is not {@code owner}'s turn. No further
 * rule validation is performed, movements are purely mechanical.
 * </p>
 */
public interface MatchController extends ScreenController {

    /**
     * Starts the match and notifies the view of the initial state.
     */
    void startMatch();

    /**
     * Moves a card from a player's hand to that player's section of the
     * playfield.
     *
     * @param owner the player playing the card
     * @param card the card to play, from the player's hand
     * @throws IllegalStateException if {@code owner} is not the current
     *         player, or {@code card} is not in their hand
     */
    void playCard(Player owner, Card<?> card);

    /**
     * Moves a card from a player's section of the playfield to that
     * player's discard pile.
     *
     * @param owner the player discarding the card
     * @param card the card to discard, from the player's playfield section
     * @throws IllegalStateException if {@code owner} is not the current
     *         player, or {@code card} is not on their section of the
     *         playfield
     */
    void discardCard(Player owner, Card<?> card);

    /**
     * Draws the top card of a player's deck into that player's hand.
     *
     * @param owner the player drawing a card
     * @throws IllegalStateException if {@code owner} is not the current
     *         player
     */
    void drawFromDeck(Player owner);

    /**
     * Reshuffles a player's discard pile back into their deck.
     *
     * @param owner the player who owns the discard pile and deck
     * @throws IllegalStateException if {@code owner} is not the current
     *         player
     */
    void reshuffleIntoDeck(Player owner);

    /**
     * Checks if the player's deck is empty.
     * 
     * @param owner the player who owns the deck
     * @return {@code true} if the deck is empty
     */
    boolean isEmptyDeck(Player owner);

    /**
     * Checks if the player's discard pile is empty.
     * 
     * @param owner the player who owns the discard pile
     * @return {@code true} if the discard pile is empty
     */
    boolean isEmptyDiscardPile(Player owner);

    /**
     * Gets the card count of a player's deck.
     * 
     * @param owner the player who owns the deck
     * @return deck's card count
     */
    int getDeckCount(Player owner);

    /**
     * Ends the current player's turn and moves on to the next one.
     */
    void endTurn();

    /**
     * Ends the match with the current player conceding.
     */
    void concede();

    /**
     * Sets the card to be highlighted, eventually de-highlighting the previous one.
     * 
     * @param cardLabel the card label to be highlited
     * @param selectedCard the selected card
     * @param player the player that tried to select the card
     */
    void changeSelectedCard(JLabel cardLabel, Card<?> selectedCard, Player player);
    //if player == turnPlayer store selectedCard, then call MatchView.changeSelectedCard(cardLabel)

    /**
     * getter for the turn player.
     * 
     * @return the turn player.
     */
    Player getTurnPlayer();

    /**
     * getter for the player 1.
     * 
     * @return player 1.
     */
    Player getPlayerOne();

    /**
     * getter for the player 2.
     * 
     * @return player 2.
     */
    Player getPlayerTwo();

    /**
     * Returns playField size (per player).
     * 
     * @return playField size (per player) 
     */
    int getPlayFieldSize();
}

