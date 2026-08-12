package it.unibo.cardhub.io.api;

/**
 * Enum representing the different types of cards and their corresponding resource paths.
 */
public enum CardType {
    POKEMON("/it/unibo/cardhub/model/pokemon.yaml"), 
    DRAGONBALL("/it/unibo/cardhub/model/dragonball.yaml"), 
    YUGIOH("/it/unibo/cardhub/model/yugioh.yaml");

    private final String resourcePath;

    CardType(final String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * Return the resource path.
     * 
     * @return the resource path
     */
    public String getResourcePath() {
        return this.resourcePath;
    }
}
