package it.unibo.cardhub.model.logic;

/**
<<<<<<< HEAD
 * An enumeration of game modes.
=======
 * An enumeration for game modes.
>>>>>>> c75dec7ac4fb1b0046c55c402d6cb41bc5b4ea64
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
<<<<<<< HEAD
     * Returns an a game mode.
=======
     * Returns a game mode.
>>>>>>> c75dec7ac4fb1b0046c55c402d6cb41bc5b4ea64
     * 
     * @return mode
     */
    public String getDisplayName() {
        return this.displayName;
    }
}
