package it.unibo.cardhub.model.logic;

/**
 * An enumeration for game modes.
 */
public enum GameMode {
    FREE_PLAY("Free Play"), CUSTOM("Custom"), FULL_GAME("Full Game");

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
