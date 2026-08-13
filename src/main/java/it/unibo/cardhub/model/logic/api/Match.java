package it.unibo.cardhub.model.logic.api;

import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.api.Playfield;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Handles the match in its entirety.
 */
public interface Match {

    /**
     * Makes the player draw a card.
     * 
     * @param player the player to draw the card
     * @throws CardCollectionFullException if the player's hand is full
     */
    void drawCard(Player player) throws CardCollectionFullException;

    /**
     * Makes the player play a card.
     * 
     * @param card the card to be played
     * @param player the player playing the card
     * @throws CardCollectionFullException if the player's side of the field is full
     */
    void playCard(Card<?> card, Player player) throws CardCollectionFullException;

    /**
     * Moves a card from the field to the discard pile.
     * 
     * @param card the card to move
     * @param player the player the card belongs to
     */
    void moveCardFromFieldToPile(Card<?> card, Player player);

    /**
     * Shuffles the player's discard pile into the deck.
     * 
     * @param player the player the action is going to be performed on
     */
    void shufflePileIntoDeck(Player player);

    /**
     * compares two Cards and proceeds with the corresponding actions.
     * 
     * @param firstPlayerCard the card of player1 to be compared
     * @param secondPlayerCard the card of player2 to be compared
     * @return the result of the comparison
     */
    ComparisonWinner compareCard(Card<?> firstPlayerCard, Card<?> secondPlayerCard);

    /**
     * Returns the state of the playfield.
     * 
     * @return the playfield
     */
    Playfield getPlayfield();

    /**
     * getter for the turn player.
     * 
     * @return the turn player.
     */
    Player getTurnPlayer();

    /**
     * changes the turn player.
     */
    void changeTurn();

    /**
     * A getter for the match's winner.
     * 
     * @return the winner, if present
     */
    Optional<Player> getWinner();

    /**
     * Ends a match and sets the winner.
     * 
     * @param player the winner
     */
    void endMatch(Player player);

    /**
     * Informs about the match's state.
     * 
     * @return true if finished, false otherwise
     */
    boolean isFinished();
}
