package it.unibo.cardhub.model.domain.api;

/**
 * Temporary non-dinamic enumeration of available decks.
 */
public enum DeckEnum {
        POKEMON("Pokemon", 0), 
        DRAGONBALL("Dragon Ball", 1), 
        YUGIOH("Yu Gi Oh", 2),
        ECARDS("E-Card", 3),
        ITALIAN("Italian", 4);

    private final String displayName;
    private final int id;

    /**
     * Creates a deck enum.
     * 
     * @param displayName the name of the deck
     * @param id the id of the deck
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

    /**
     * Enum getter.
     * 
     * @param id id of the deck
     * @return the enum corrisponding to the deck id
     */
    public static DeckEnum fromId(final int id) {
        for (final DeckEnum deck : values()) {
            if (deck.getId() == id) {
                return deck;
            }
        }
        throw new IllegalArgumentException("Unknown deck id: " + id);
    }
}
