package it.unibo.cardhub.view.api;

/**
 * Represents the view of the deck manager section.
 */
public interface DeckManagerView {

    /**
     * Adds a card entry to the view.
     *
     * @param name card name
     * @param value value of the card
     */
    void addCard(String cardName, int value);

    /**
     * Removes all card entries from the view.
     */
    void clearCards();

    /**
     * Tells the navigator to redirect to the card manager screen.
     */
    void goToCardManager();

    /**
     * Tells the navigator the redirect to the decks screen.
     */
    void goToDecks();
}
