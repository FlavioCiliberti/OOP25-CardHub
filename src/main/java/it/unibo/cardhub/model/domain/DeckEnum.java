package it.unibo.cardhub.model.domain;

public enum DeckEnum {
        POKEMON("Pokemon", 0), 
        DRAGONBALL("Dragon Ball", 1), 
        YUGIOH("Yu Gi Oh", 2);

    private final String displayName;
    private final int id;

    /**
     * Creates a deck enum.
     * 
     * @param displayName the mode
     */
    DeckEnum(final String displayName, final int id) {
        this.displayName = displayName;
        this.id = id;
    }

    /**
     * Returns the deck name.
     * 
     * @return deck name
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Returns the deck id.
     * 
     * @return deck id
     */
    public int getId() {
        return this.id;
    }

    public static DeckEnum fromId(final int id) {
        for (final DeckEnum deck : values()) {
            if (deck.getId() == id) {
                return deck;
            }
        }
        throw new IllegalArgumentException("Unknown deck id: " + id);
    }
}
