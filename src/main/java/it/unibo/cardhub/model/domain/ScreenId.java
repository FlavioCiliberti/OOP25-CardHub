package it.unibo.cardhub.model.domain;

/**
 * Identifiers for the screens (views) of the application.
 */
public enum ScreenId {
 
    /** The starting Home screen with the main menu. */
    HOME,
 
    /** The screen used to create a new match. */
    CREATE_MATCH,
 
    /** The screen used to load an existing match. */
    LOAD_MATCH,

    /** The screen showing an ongoing match. */
    MATCH,
 
    /** The screen listing the available decks. */
    DECKS,
 
    /** The screen used to create or edit a deck. */
    DECK_MANAGER,
 
    /** The screen used to create or edit cards. */
    CARD_MANAGER
}
