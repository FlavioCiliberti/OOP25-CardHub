package it.unibo.cardhub.io.api;

/**
 * Factory interface for creating instances of CardLoader for different card types.
 * 
 * @param <T> the type of the card content
 */
@FunctionalInterface
public interface CardLoaderFactory<T> {

    /**
     * Creates a CardLoader for the specified resource path and card creator.
     *
     * @param cardType     the type of the card to load
     * @param cardCreator  the CardCreator used to create card instances from the loaded data
     * @return a CardLoader that can load cards of type T
     */
    CardLoader<T> createLoader(CardType cardType, CardCreator<T> cardCreator);
}
