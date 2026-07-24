package it.unibo.cardhub.controller.api;

/**
 * Controller responsible for handling the actions available
 * from the "Deck manager" screen of the application.
 * 
 * <p>
 * Exposes the operations available to the user for browsing, creating,
 * editing, and deleting cards.
 * </p>
 */
public interface DeckManager {

    /**
     * Navigates back to the previous view.
     */
    void goBack();

    /**
     * Creates the instances necessary for the CardManager screen
     * for creating a new card.
     * Then navigates to it.
     */
    void createNewCard();

    /**
     * Creates the instances necessary for the CardManager screen
     * for editing the card with the given identifier.
     *
     * @param id the identifier of the card to edit
     * @throws IllegalArgumentException if no card with the given identifier exists
     */
    void editCard(int id);

    /**
     * Deletes the card with the given identifier.
     *
     * @param id the identifier of the card to delete
     * @throws IllegalArgumentException if no card with the given identifier exists
     */
    void deleteCard(int id);

    /**
     * Changes the current page of the card list to the given page number.
     *
     * @param page the target page number
     * @throws IllegalArgumentException if {@code page} is not a valid page number
     */
    void changeCardPage(int page);

    /**
     * Changes the current page of the card list to the next one.
     */
    void previousCardPage();

    /**
     * Changes the current page of the card list to the previous one.
     */
    void nextCardPage();
}

