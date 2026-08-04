package it.unibo.cardhub.controller.api;

import java.util.Map;

import it.unibo.cardhub.controller.exceptions.InvalidFormFieldsException;

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
     * Creates a new game using default (free) rules.
     *
     * @param player1Name the name of the first player
     * @param player1DeckId the deck id chosen by the first player
     * @param player2Name the name of the second player
     * @param player2DeckId the deck id chosen by the second player
     * 
     * @throws InvalidFormFieldsException if one or more parameters do not match a 
     *      valid field state 
     */
    void createFreeGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId) 
        throws InvalidFormFieldsException;

    /**
     * Creates a new game using the full official rule set.
     *
     * @param player1Name the name of the first player
     * @param player1DeckId the deck id chosen by the first player
     * @param player2Name the name of the second player
     * @param player2DeckId the deck id chosen by the second player
     * @throws InvalidFormFieldsException if one or more parameters do not match a 
     *      valid field state 
     */
    void createFullGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId)
        throws InvalidFormFieldsException;

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
     * @param playerFieldSize the maximum amount of cards a player can put in their playfield
     * @param autoDraw set to {@code true} if a draw must be done automatically at the beggining of the turn
     * 
     * @throws InvalidFormFieldsException if one or more parameters do not match a 
     *      valid field state 
     */
    void createCustomGame(String player1Name, int player1DeckId, 
                        String player2Name, int player2DeckId,
                        int maxHandSize, int playerFieldSize,
                        boolean autoDraw)
                        throws InvalidFormFieldsException; //IMPORTANT: winner and looser card actions parameters must be added

}
