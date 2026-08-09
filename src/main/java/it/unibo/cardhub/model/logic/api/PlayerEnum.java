package it.unibo.cardhub.model.logic.api;

/**
 * An enumeration for player1 and player2
 */
public enum PlayerEnum {
    PLAYER_ONE(0),
    PLAYER_TWO(1);

    private final int index;

    /**
     * Creates a player.
     * 
     * @param index the index of the player
     */
    PlayerEnum(int index) {
        this.index = index;
    }

    /**
     * Returns an index.
     * 
     * @return the index of the player
     */
    public int getIndex() {
        return index;
    }
}
