package it.unibo.cardhub.model.domain.api;

import java.util.List;
import java.util.Optional;

/**
 * Represent a match, the heart of the game.
 */
public interface MatchState {

    /**
     * Returns all the players of the match.
     * 
     * @return all the players
     */
    List<Player> getPlayers();

    /**
     * Getter for a specific player by enum.
     * 
     * @param player the requested player by its enum
     * @return the actual player
     */
    Player getPlayer(PlayerEnum player);

    /**
     * returns the PlayerEnum of the specified player.
     * 
     * @param player the requested player
     * @return the player
     */
    PlayerEnum getEnum(Player player);

    /**
     * Makes the player draw a card.
     * 
     * @param player the player to draw the card
     */
    void drawCard(PlayerEnum player);

    /**
     * Makes the player play a card.
     * 
     * @param card the card to be played
     * @param playerEnum the player playing the card
     */
    void playCard(Card<?> card, PlayerEnum playerEnum);

    /**
     * Moves a card from the field to the discard pile.
     * 
     * @param card the card to move
     * @param player the player the card belongs to
     */
    void moveCardFromFieldToPile(Card<?> card, PlayerEnum player);

    /**
     * Returns the state of the playfield.
     * 
     * @return the playfield
     */
    Playfield getPlayfield();

    /**
     * Returns playField size (per player).
     * 
     * @return playField size (per player) 
     */
    int getPlayFieldSize();

    /**
     * Moves all cards from discard pile to deck.
     * 
     * @param player the owner of the deck and pile
     */
    void shufflePileIntoDeck(PlayerEnum player);

    /**
     * Checks if the player's deck is empty.
     * 
     * @param owner the player who owns the deck
     * @return {@code true} if the deck is empty
     */
    boolean isEmptyDeck(PlayerEnum owner);

    /**
     * Checks if the player's discard pile is empty.
     * 
     * @param owner the player who owns the discard pile
     * @return {@code true} if the discard pile is empty
     */
    boolean isEmptyDiscardPile(PlayerEnum owner);

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

    /**
     * A getter for the match's winner.
     * 
     * @return the winner, if present
     */
    Optional<Player> getWinner();
}
