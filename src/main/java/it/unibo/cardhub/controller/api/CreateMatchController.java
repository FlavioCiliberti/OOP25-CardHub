package it.unibo.cardhub.controller.api;

import java.util.Map;

import it.unibo.cardhub.model.domain.exceptions.EmptyFieldException;
import it.unibo.cardhub.model.logic.GameMode;
import it.unibo.cardhub.model.logic.LoserCardAction;
import it.unibo.cardhub.model.logic.WinnerCardAction;

/**
 * Provides operations to create a match between two players,
 * either with default rules, full rule sets, or custom configurable rules.
 */
public interface CreateMatchController extends BackNavigableScreen {

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
     * @throws EmptyFieldException if player1Name or player2Name are empty
     */
    void tryCreatingMatch(String player1Name, int player1DeckId, 
                        String player2Name, int player2DeckId,
                        int maxHandSize, int startingHandSize,
                        int playerFieldSize, boolean autoDraw,
                        WinnerCardAction winnerAction, LoserCardAction loserAction,
                        GameMode gameMode) throws EmptyFieldException;

    /**
     * Creates a new game using default (free) rules.
     *
     * @param player1Name the name of the first player
     * @param player1DeckId the deck id chosen by the first player
     * @param player2Name the name of the second player
     * @param player2DeckId the deck id chosen by the second player
     */
    void createFreeGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId);

    /**
     * Creates a new game using the full official rule set.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     */
    void createFullGame(String player1Name, String player2Name);

    /**
     * Creates a new game using a custom, user-configurable rule set.
     *
     * <p>TO DO:
     * add winner and looser card actions parameters.
     * </p>
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
     */
    void createCustomGame(String player1Name, int player1DeckId, 
                        String player2Name, int player2DeckId,
                        int maxHandSize, int startingHandSize,
                        int playerFieldSize, boolean autoDraw,
                        WinnerCardAction winnerAction, LoserCardAction loserAction);

}
