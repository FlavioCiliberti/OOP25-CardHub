package it.unibo.cardhub.controller.api;

/**
 * Controller responsible for handling the actions available
 * from the "Load Match" screen of the application.
 * 
 * <p>
 * Exposes the operations available to the user for browsing, loading and deleting saved matches.
 * </p>
 */
public interface LoadMatch extends BackNavigableScreen, ItemPageNavigator {
    /**
     * Loads a saved match with the given identifier.
     * 
     * @param id identifier of the loadable match.
     * @throws IllegalArgumentException if no item with the given identifier exists
     */
    void load(int id);

    /**
     * Deletes a saved match with the given identifier.
     * 
     * @param id identifier of the loadable match.
     * @throws IllegalArgumentException if no item with the given identifier exists
     */
    void delete(int id);
}
