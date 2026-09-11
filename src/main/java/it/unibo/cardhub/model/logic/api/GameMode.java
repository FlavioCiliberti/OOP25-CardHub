package it.unibo.cardhub.model.logic.api;

/**
 * An enumeration for game modes.
 */
public enum GameMode {
    FREE_PLAY("Free Play"), CUSTOM("Custom"), E_CARD("E-Card");

    private final String displayName;

    /**
     * Creates a game mode.
     * 
     * @param displayName the mode
     */
    GameMode(final String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns a game mode.
     * 
     * @return mode
     */
    public String getDisplayName() {
        return this.displayName;
    }
}
