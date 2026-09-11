package it.unibo.cardhub.controller.api;

import java.util.Map;

import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.GameMode;

/**
 * Provides operations to create a match between two players,
 * either with default rules, full rule sets, or custom configurable rules.
 */
public interface CreateMatchController extends BackNavigableScreen {

    /**
     * @return the minimum allowed hand size
     */
    int getMinHandSize();

    /**
     * @return the maximum allowed hand size
     */
    int getMaxHandSize();

    /**
     * @return the minimum allowed field size
     */
    int getMinFieldSize();

    /**
     * @return the maximum allowed field size
     */
    int getMaxFieldSize();

    /**
     * @return the default hand size proposed to the user
     */
    int getDefaultHandSize();

    /**
     * @return the default field size proposed to the user
     */
    int getDefaultFieldSize();

    /**
     * Retrieves the available decks indexed by their identifier.
     *
     * @return an unmodifiable map associating each deck id with its name
     */
    Map<Integer, String> getDecks();

    /**
     * gets all the match parameters from the view.
     * 
     * @param player1Name the name of the first player
     * @param player1DeckId the deck id chosen by the first player
     * @param player2Name the name of the second player
     * @param player2DeckId the deck id chosen by the second player
     * @param maxHandSize the maximum amount of cards a player can have in their hand
     * @param startingHandSize the amount of cards a player has in their hand on game start
     * @param playerFieldSize the maximum amount of cards a player can put in their playfield
     * @param autoDraw set to {@code true} if a draw must be done automatically at the beggining of the turn
     * @param winnerAction the action to be done to the winner card
     * @param loserAction the action to be done to the loser card
     * @param gameMode the selected gameMode
     */
    void tryCreatingMatch(String player1Name, int player1DeckId, 
                        String player2Name, int player2DeckId,
                        int maxHandSize, int startingHandSize,
                        int playerFieldSize, boolean autoDraw,
                        CardAction winnerAction, CardAction loserAction,
                        GameMode gameMode);

}
