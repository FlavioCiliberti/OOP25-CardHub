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
public interface DeckManager extends EntityManager<Integer> {

    /**
     * Changes the deck's name to the given name.
     * 
     * @param name deck's new name
     */
    void renameDeck(String name);
}

