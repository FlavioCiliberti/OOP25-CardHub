package it.unibo.cardhub.controller.api;

/**
 * Generic controller for a "manage <entity>" screen: browsing (with
 * pagination), creating, editing and deleting items of type identified by {@code T}.
 *
 * @param <T> the type of the identifier used to reference an item
 */

public interface EntityManager<T> extends ItemPageNavigator {

    /**
     * Creates the instances necessary for the item-editing screen
     * to create a new item.
     * Then navigates to it.
     */
    void createNew();

    /**
     * Creates the instances necessary for the item-editing screen
     * to edit the item with the given identifier.
     *
     * @param id the identifier of the item to edit
     * @throws IllegalArgumentException if no item with the given identifier exists
     */
    void edit(T id);

    /**
     * Deletes the item with the given identifier.
     *
     * @param id the identifier of the item to delete
     * @throws IllegalArgumentException if no item with the given identifier exists
     */
    void delete(T id);
}
