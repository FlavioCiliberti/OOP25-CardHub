package it.unibo.cardhub.controller.api;

/**
 * Controller responsible for handling the actions available
 * from the "Manage decks" screen of the application.
 * 
 * <p>
 * Exposes the operations available to the user for browsing, creating,
 * editing, and deleting decks.
 * </p>
 */
public interface ManageDecks {

    /**
     * Navigates back to the previous view.
     */
    void goBack();

    /**
     * Creates the instances necessary for the ManageDeck screen
     * for creating a new deck.
     * Then navigates to it.
     */
    void createNewDeck();

    /**
     * Creates the instances necessary for the ManageDeck screen
     * for editing the deck with the given identifier.
     *
     * @param id the identifier of the deck to edit
     * @throws IllegalArgumentException if no deck with the given identifier exists
     */
    void editDeck(int id);

    /**
     * Deletes the deck with the given identifier.
     *
     * @param id the identifier of the deck to delete
     * @throws IllegalArgumentException if no deck with the given identifier exists
     */
    void deleteDeck(int id);

    /**
     * Changes the current page of the deck list to the given page number.
     *
     * @param page the target page number
     * @throws IllegalArgumentException if {@code page} is not a valid page number
     */
    void changeDeckPage(int page);

    /**
     * Changes the current page of the deck list to the next one.
     */
    void previousDeckPage();

    /**
     * Changes the current page of the deck list to the previous one.
     */
    void nextDeckPage();
}
