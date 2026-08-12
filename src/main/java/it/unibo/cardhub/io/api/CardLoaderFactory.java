package it.unibo.cardhub.io.api;

/**
 * Factory interface for creating instances of CardLoader for different card types.
 */
public interface CardLoaderFactory {

    /**
     * Creates a CardLoader for Yu-Gi-Oh cards.
     *
     * @return a CardLoader instance for Yu-Gi-Oh cards
     */
    CardLoader<YuGiOh> loadYuGiOh();

    /**
     * Creates a CardLoader for Dragon Ball cards.
     *
     * @return a CardLoader instance for Dragon Ball cards
     */
    CardLoader<DragonBall> loadDragonBall();

    /**
     * Creates a CardLoader for Pokemon cards.
     *
     * @return a CardLoader instance for Pokemon cards
     */
    CardLoader<Pokemon> loadPokemon();

    /**
     * Record representing a Yu-Gi-Oh card with its type and race.
     * 
     * @param type the type of the Yu-Gi-Oh card
     * @param race the race of the Yu-Gi-Oh card
     */
    record YuGiOh(String type, String race) { }

    /**
     * Record representing a Pokemon card with its type and rarity.
     * 
     * @param type the type of the Pokemon card
     * @param rarity the rarity of the Pokemon card
     */
    record Pokemon(String type, String rarity) { }

    /**
     * Record representing a Dragon Ball card with its type and rarity.
     * 
     * @param type the type of the Dragon Ball card
     * @param rarity the rarity of the Dragon Ball card
     */
    record DragonBall(String type, String rarity) { }
}
